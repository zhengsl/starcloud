package com.star.cloud.plat.app;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class ClusterApp extends Application {
	
	@Override
	public synchronized Restlet createInboundRoot() {
		Router router = new Router(getContext());
		
		router.attach("/structure/cluster/{idPlusName}", ClusterStructureResource.class);
		router.attach("/structure/group/{idPlusName}", GroupStructureResource.class);
		router.attach("/structure/machine/{idPlusName}", MachineStructureResource.class);
		router.attach("/structure/machinecard/{machineId}", MachineCardResource.class);
		router.attach("/structure/card/{idPlusName}", CardResource.class);
		router.attach("/info/machine/all", MachineInfoResource.class);
		
		return router;
	}

}
