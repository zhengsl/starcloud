package com.star.cloud.plat.app;

import java.util.List;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.cloud.virt.model.StarNetwork;
import com.star.cloud.virt.service.INetworkService;
import com.star.cloud.virt.service.VirtualizationServiceBus;

public class VMNetworkResource extends ServerResource {
	
	private static final Logger log = LoggerFactory.getLogger(VMNetworkResource.class);
	
	private static final ObjectMapper objMapper = new ObjectMapper();
	
	@Get
	public String getAllVMNetworks() {
		try {
			INetworkService serv = VirtualizationServiceBus.getNetworkService();
			List<StarNetwork> networks = serv.getAllNetworks();
			return objMapper.writeValueAsString(networks);
		} catch (Exception e) {
			log.error("To get all VMNetworks failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}

}
