package com.star.cloud.monitor.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.star.cloud.monitor.model.HostStatus;
import com.star.cloud.monitor.model.PerfValue;

public class MongoStreamingMonitorService implements IStreamingMonitorService {
	
	private static final Logger log = LoggerFactory.getLogger(MongoStreamingMonitorService.class);
	
	private final MongoClient mClient;
	
	public MongoStreamingMonitorService(MongoClient mClient) {
		this.mClient = mClient;
	}

	@Override
	public Map<String, HostStatus> getHostStatus(List<String> hostNames) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PerfValue> getBasicPerfValues(String hostName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, PerfValue> getAggregatedPerfValues(List<String> hostNames) {
		// TODO Auto-generated method stub
		return null;
	}

}
