package com.star.cloud.monitor.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.star.cloud.monitor.model.StarCloudMonitorException;

public class MongoQFMachineService implements IQFMachineService {

	private final MongoClient mClient;
	
	public MongoQFMachineService(MongoClient mClient) {
		this.mClient = mClient;
	}
	
	private Map<String, String> getStatusData(String hostName, MongoDefinition.NS ns) {
		DB db = this.mClient.getDB(MongoDefinition.QF.name);
		DBCollection coll = db.getCollection(ns.getName());
		
		DBObject q = new BasicDBObject();
		q.put("hostName", hostName);
		BasicDBObject sortObj = new BasicDBObject("timestamp", -1);
		DBObject dbObj = coll.find(q).sort(sortObj).limit(1).next();
		
		if (dbObj == null) {
			throw new StarCloudMonitorException("No status data for: " + hostName);
		} else {
			Map<String, String> result = new LinkedHashMap<String, String>();
			for (String key : ns.getFieldNames()) {
				result.put(key, String.valueOf(dbObj.get(key)));
			}
			return result;
		}
	}
	
	@Override
	public Map<String, String> getBasicMachineStatus(String hostName) {
		return getStatusData(hostName, MongoDefinition.QF.MACHINE.ins);
	}

	@Override
	public Map<String, String> getBasicHostStatus(String hostName) {
		return getStatusData(hostName, MongoDefinition.QF.HOST.ins);
	}
	
	private void addStatusData(String hostName, Map<String, String> data, MongoDefinition.NS ns) {
		DB db = this.mClient.getDB(MongoDefinition.QF.name);
		DBCollection coll = db.getCollection(ns.getName());
		
		DBObject o = new BasicDBObject("hostName", hostName);
		for (String fieldName : ns.getFieldNames()) {
			o.put(fieldName, data.get(fieldName));
		}
		o.put("timestamp", System.currentTimeMillis());
		
		coll.insert(o);
	}

	@Override
	public void addMachineStatusData(String hostName, Map<String, String> data) {
		addStatusData(hostName, data, MongoDefinition.QF.MACHINE.ins);
	}

	@Override
	public void addHostStatusData(String hostName, Map<String, String> data) {
		addStatusData(hostName, data, MongoDefinition.QF.HOST.ins);
	}

	@Override
	public List<Map<String, String>> getBasicServiceStatus(String hostName) {
		DB db = this.mClient.getDB(MongoDefinition.QF.name);
		DBCollection coll = db.getCollection(MongoDefinition.QF.SERVICE.ins.getName());
		
		DBObject q = new BasicDBObject();
		q.put("hostName", hostName);
		
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		List<String> snames = new ArrayList<String>();
		DBCursor cursor = coll.find(q).sort(new BasicDBObject("timestamp", -1)).limit(50);
		while (cursor.hasNext()) {
			DBObject dbObj = cursor.next();
			String serviceName = String.valueOf(dbObj.get(MongoDefinition.QF.SERVICE.fServiceName));
			if (!snames.contains(serviceName)) {
				Map<String, String> objMap = new LinkedHashMap<String, String>();
				for (String key : dbObj.keySet()) {
					if (!key.equals("_id") && !key.equals("hostName")) {
						objMap.put(key, String.valueOf(dbObj.get(key)));
					}
				}
				result.add(objMap);
				snames.add(serviceName);
			}
		}
		return result;
	}

	@Override
	public void addServiceStatusData(String hostName, List<Map<String, String>> data) {
		for (Map<String, String> d : data) {
			addStatusData(hostName, d, MongoDefinition.QF.SERVICE.name);
		}
	}
	
	private Map<String, String> getStatusData(String hostName, String collName) {
		DB db = this.mClient.getDB(MongoDefinition.QF.name);
		DBCollection coll = db.getCollection(collName);
		
		DBObject q = new BasicDBObject();
		q.put("hostName", hostName);
		BasicDBObject sortObj = new BasicDBObject("timestamp", -1);
		DBObject dbObj = coll.find(q).sort(sortObj).limit(1).next();
		
		if (dbObj == null) {
			throw new StarCloudMonitorException("No status data for: " + hostName);
		} else {
			Map<String, String> result = new LinkedHashMap<String, String>();
			
			for (Object key : dbObj.toMap().keySet()) {
				String skey = String.valueOf(key);
				result.put(skey, String.valueOf(dbObj.get(skey)));
			}
			return result;
		}
	}

	@Override
	public Map<String, String> getDetailDiskStatus(String hostName) {
		return getStatusData(hostName, MongoDefinition.QF.DISK.name);
	}

	@Override
	public Map<String, String> getDetailNetworkStatus(String hostName) {
		return getStatusData(hostName, MongoDefinition.QF.NETWORK.name);
	}
	
	private void addStatusData(String hostName, Map<String, String> data, String collName) {
		DB db = this.mClient.getDB(MongoDefinition.QF.name);
		DBCollection coll = db.getCollection(collName);
		
		DBObject o = new BasicDBObject("hostName", hostName);
		o.put("timestamp", System.currentTimeMillis());
		o.putAll(data);
		
		coll.insert(o);
	}

	@Override
	public void addDiskStatusData(String hostName, Map<String, String> data) {
		addStatusData(hostName, data, MongoDefinition.QF.DISK.name);
	}

	@Override
	public void addNetworkStatusData(String hostName, Map<String, String> data) {
		addStatusData(hostName, data, MongoDefinition.QF.NETWORK.name);
	}
	
}
