package com.star.cloud.monitor.service;

import java.util.List;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.star.cloud.monitor.model.IcingaTaskMetadata;
import com.star.cloud.monitor.model.StarCloudMonitorException;

public class DaemonManagementServiceImpl implements IDaemonManagementService {
	
	private final MongoClient mClient;
	
	public DaemonManagementServiceImpl(MongoClient mClient) {
		this.mClient = mClient;
	}
	
	private DBObject findDBObject(DBCollection coll, String taskName) {
		DBObject q = new BasicDBObject();
		q.put(MongoDefinition.QF.TASK.fTaskName, taskName);
		DBObject dbObj = coll.findOne(q);
		return dbObj;
	}
	
	@Override
	public boolean taskExists(String taskName) {
		DB db = this.mClient.getDB(MongoDefinition.QF.name);
		DBCollection coll = db.getCollection(MongoDefinition.QF.TASK.name);
		DBObject dbObj = findDBObject(coll, taskName);
		if (dbObj == null) return false;
		else return true;
	}

	/* @Override
	public void addHostsForTask(String taskName, List<String> hostNames) {
		DB db = this.mClient.getDB(MongoDefinition.QF.name);
		DBCollection coll = db.getCollection(MongoDefinition.QF.TASK.name);

		DBObject q = new BasicDBObject();
		q.put(MongoDefinition.QF.TASK.fTaskName, taskName);
		DBObject dbObj = coll.findOne(q);
		
		if (dbObj == null) {
			throw new StarCloudMonitorException("Task " + taskName + " has not been set.");
		} else {
			BasicDBList list = (BasicDBList) dbObj.get(MongoDefinition.QF.TASK.fHostNames);
			list.addAll(hostNames);
			coll.update(q, dbObj);
		}
	} */

	@Override
	public void addServiceForTask(String taskName, String serviceName) {
		DB db = this.mClient.getDB(MongoDefinition.QF.name);
		DBCollection coll = db.getCollection(MongoDefinition.QF.TASK.name);

		DBObject q = new BasicDBObject();
		q.put(MongoDefinition.QF.TASK.fTaskName, taskName);
		DBObject dbObj = coll.findOne(q);
		
		if (dbObj == null) {
			throw new StarCloudMonitorException("Task " + taskName + " has not been set.");
		} else {
			BasicDBList list = (BasicDBList) dbObj.get(MongoDefinition.QF.TASK.fServices);
			list.add(serviceName);
			coll.update(q, dbObj);
		}
	}
	
	@Override
	public void setServiceForTask(String taskName, String serviceName) {
		DB db = this.mClient.getDB(MongoDefinition.QF.name);
		DBCollection coll = db.getCollection(MongoDefinition.QF.TASK.name);

		DBObject dbObj = findDBObject(coll, taskName);
		
		if (dbObj == null) {
			throw new StarCloudMonitorException("Task " + taskName + " has not been set.");
		} else {
			BasicDBList list = (BasicDBList) dbObj.get(MongoDefinition.QF.TASK.fServices);
			list.clear();
			list.add(serviceName);
			coll.update(new BasicDBObject(MongoDefinition.QF.TASK.fTaskName, taskName), dbObj);
		}
	}

	@Override
	public IcingaTaskMetadata getTaskMetadata(String taskName) {
		DB db = this.mClient.getDB(MongoDefinition.QF.name);
		DBCollection coll = db.getCollection(MongoDefinition.QF.TASK.name);
		DBObject dbObj = findDBObject(coll, taskName);
		
		IcingaTaskMetadata data = new IcingaTaskMetadata();
		data.setTaskName(taskName);
		BasicDBList serviceNamelist = (BasicDBList) dbObj.get(MongoDefinition.QF.TASK.fServices);
		for (Object sname : serviceNamelist) {
			data.addService(String.valueOf(sname));
		}
		
		return data;
	}

	@Override
	public void addTask(String taskName) {
		DB db = this.mClient.getDB(MongoDefinition.QF.name);
		DBCollection coll = db.getCollection(MongoDefinition.QF.TASK.name);
		DBObject dbObj = findDBObject(coll, taskName);
		if (dbObj == null) {
			BasicDBObject taskObj = new BasicDBObject(MongoDefinition.QF.TASK.fTaskName, taskName);
			taskObj.put(MongoDefinition.QF.TASK.fServices, new BasicDBList());
			coll.insert(taskObj);
		}
	}

	@Override
	public void addServicesForTask(String taskName, List<String> serviceNames) {
		DB db = this.mClient.getDB(MongoDefinition.QF.name);
		DBCollection coll = db.getCollection(MongoDefinition.QF.TASK.name);

		DBObject dbObj = findDBObject(coll, taskName);
		
		if (dbObj == null) {
			throw new StarCloudMonitorException("Task " + taskName + " has not been set.");
		} else {
			BasicDBList list = (BasicDBList) dbObj.get(MongoDefinition.QF.TASK.fServices);
			list.addAll(serviceNames);
			coll.update(new BasicDBObject(MongoDefinition.QF.TASK.fTaskName, taskName), dbObj);
		}
	}

	@Override
	public void setServicesForTask(String taskName, List<String> serviceNames) {
		DB db = this.mClient.getDB(MongoDefinition.QF.name);
		DBCollection coll = db.getCollection(MongoDefinition.QF.TASK.name);

		DBObject dbObj = findDBObject(coll, taskName);
		
		if (dbObj == null) {
			throw new StarCloudMonitorException("Task " + taskName + " has not been set.");
		} else {
			dbObj.put(MongoDefinition.QF.TASK.fServices, serviceNames);
			coll.update(new BasicDBObject(MongoDefinition.QF.TASK.fTaskName, taskName), dbObj);
		}
	}

	

}
