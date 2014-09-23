package com.star.cloud.provision.service;

public class ProvisionServiceBus {
	
	private static IProvisionService provisionServ;
	
	private static IClusterInfoService clusterInfoServ;
	
	public static void init() {
		String saltStackIp = System.getProperty("saltIP", "127.0.0.1");
		String saltStackPort = System.getProperty("saltPort", "8999");
		String saltUserName = System.getProperty("saltUserName", "saltadmin");
		String saltPassword = System.getProperty("saltPassword", "star123");
		
		provisionServ = new ProvisionServiceImpl(saltStackIp, saltStackPort, saltUserName, saltPassword);
		clusterInfoServ = new ClusterInfoServiceImpl(saltStackIp, saltStackPort, saltUserName, saltPassword);
	}
	
	public static IProvisionService getProvisionService() {
		return provisionServ;
	}
	
	public static IClusterInfoService getClusterInfoService() {
		return clusterInfoServ;
	}

}
