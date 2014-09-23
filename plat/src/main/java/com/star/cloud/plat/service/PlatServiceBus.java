package com.star.cloud.plat.service;

public class PlatServiceBus {
	
	private static IApplicationService appServ;
	
	private static IClusterService clusterServ;
	
	private static IMachineService machineServ;
	
	private static IVirtualizationService virtServ;
	
	private static IHostService hostServ;
	
	public static void init() {
		appServ = new ApplicationServiceImpl();
		clusterServ = new ClusterServiceImpl();
		machineServ = new MachineServiceImpl();
		virtServ = new VirtualizationServiceImpl();
		hostServ = new HostServiceImpl();
	}
	
	public static IApplicationService getApplicationService() {
		return appServ;
	}
	
	public static IClusterService getClusterService() {
		return clusterServ;
	}
	
	public static IMachineService getMachineService() {
		return machineServ;
	}
	
	public static IVirtualizationService getVirtualizationService() {
		return virtServ;
	}
	
	public static IHostService getHostService() {
		return hostServ;
	}

}
