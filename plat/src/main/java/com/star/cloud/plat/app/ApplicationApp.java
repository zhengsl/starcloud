package com.star.cloud.plat.app;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class ApplicationApp extends Application {
	
	@Override
	public synchronized Restlet createInboundRoot() {
		Router router = new Router(getContext());
		
		router.attach("/provision/server/{idPlusName}", ServerProvisionResource.class);
		router.attach("/provision/service/{idPlusName}", ServiceProvisionResource.class);
		
		return router;
	}

}
