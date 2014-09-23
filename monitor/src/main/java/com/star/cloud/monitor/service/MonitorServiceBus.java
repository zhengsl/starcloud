package com.star.cloud.monitor.service;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;

public class MonitorServiceBus {
	
	private static MongoClient mongoClient;
	
	private static IStreamingMonitorService streaming;
	
	private static IQFMachineService qf;
	
	private static IDaemonManagementService daemon;
	
	public static void init() throws UnknownHostException {
		String mongoIP = System.getProperty("mongoIP", "127.0.0.1");
		int mongoPort = Integer.parseInt(System.getProperty("mongoPort", "27017"));
		mongoClient = new MongoClient(mongoIP, mongoPort);
		
		streaming = new MongoStreamingMonitorService(mongoClient);
		qf = new MongoQFMachineService(mongoClient);
		daemon = new DaemonManagementServiceImpl(mongoClient);
	}
	
	public static IStreamingMonitorService getStreamingMonitorService() {
		return streaming;
	}
	
	public static IQFMachineService getQFMachineService() {
		return qf;
	}
	
	public static IDaemonManagementService getDaemonManagementService() {
		return daemon;
	}
	
}
