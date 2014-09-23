package com.star.cloud.plat.app;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class MonitorApp extends Application {
	
	@Override
	public synchronized Restlet createInboundRoot() {
		Router router = new Router(getContext());
		
		router.attach("/status/machine/{idPlusName}", MachineStatusResource.class);
		router.attach("/status/host/{idPlusName}", HostStatusResource.class);
		router.attach("/status/hosts/{namearray}", HostStatusArrayResource.class);
		router.attach("/status/deployment/{idPlusName}", DeploymentStatusResource.class);
		router.attach("/status/service/{idPlusName}", ServiceStatusResource.class);
		router.attach("/status/disk/{idPlusName}", DiskStatusResource.class);
		router.attach("/status/network/{idPlusName}", NICStatusResource.class);
		router.attach("/event/host/{idPlusName}/{sinceWhen}", HostEventResource.class);
		
		return router;
	}

}
