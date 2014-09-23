package com.star.cloud.plat.app;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class VirtualizationApp extends Application {
	
	@Override
	public synchronized Restlet createInboundRoot() {
		Router router = new Router(getContext());
		
		router.attach("/vmprofile/{idPlusName}", VMProfileResource.class);
		router.attach("/vminstance/{idPlusName}", VMInstanceResource.class);
		router.attach("/image/all", VMImageResource.class);
		router.attach("/network/all", VMNetworkResource.class);
		router.attach("/card/vminstance/{idPlusName}", CardVMInstanceResource.class);
		router.attach("/network/vminstance/{idPlusName}", NetworkVMInstanceResource.class);
		router.attach("/control/vminstance/{type}", VMInstanceControlResource.class);
		
		return router;
	}

}
