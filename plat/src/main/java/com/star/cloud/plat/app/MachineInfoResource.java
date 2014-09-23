package com.star.cloud.plat.app;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.cloud.provision.service.IClusterInfoService;
import com.star.cloud.provision.service.ProvisionServiceBus;

public class MachineInfoResource extends ServerResource {
	
	private static final Logger log = LoggerFactory.getLogger(MachineInfoResource.class);
	
	@Get
	public String getMachineInfo() {
		try {
			IClusterInfoService ciService = ProvisionServiceBus.getClusterInfoService();
			return ciService.getMachineInfoJson();
		} catch (Exception e) {
			log.error("To get MachineInfo failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}

}
