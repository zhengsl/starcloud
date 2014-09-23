package com.star.cloud.virt.service;

import org.jclouds.ContextBuilder;
import org.jclouds.openstack.neutron.v2_0.NeutronApi;
import org.jclouds.openstack.nova.v2_0.NovaApi;

public class VirtualizationServiceBus {
	
	private static NovaApi novaApi;
	
	private static NeutronApi neutronApi; 
	
	private static IServerService serverService;
	
	private static IFlavorService flavorService;
	
	private static IImageService imageService;
	
	private static INetworkService networkService;
	
	private static final String ZONE = "regionOne";
	
	public static void init() {
		String novaProvider = "openstack-nova";
		String neutronProvider = "openstack-neutron";
        String identity = "demo:admin"; // tenantName:userName
        String credential = "admin";
        String keystoneIp = System.getProperty("keystoneIP", "127.0.0.1");
        String keystonePort = System.getProperty("keystonePort", "5000");
		novaApi = ContextBuilder.newBuilder(novaProvider)
                .endpoint("http://" + keystoneIp + ":" + keystonePort + "/v2.0/")
                .credentials(identity, credential)
                .buildApi(NovaApi.class);
		neutronApi = ContextBuilder.newBuilder(neutronProvider)
                .endpoint("http://" + keystoneIp + ":" + keystonePort + "/v2.0/")
                .credentials(identity, credential)
                .buildApi(NeutronApi.class);
		serverService = new ServerServiceImpl(novaApi.getServerApiForZone(ZONE));
		flavorService = new FlavorServiceImpl(novaApi.getFlavorApiForZone(ZONE));
		imageService = new ImageServiceImpl(novaApi.getImageApiForZone(ZONE));
		networkService = new NetworkServiceImpl(neutronApi.getNetworkApiForZone(ZONE));
	}
	
	public static IServerService getServerService() {
		return serverService;
	}
	
	public static IFlavorService getFlavorService() {
		return flavorService;
	}
	
	public static IImageService getImageService() {
		return imageService;
	}
	
	public static INetworkService getNetworkService() {
		return networkService;
	}

}
