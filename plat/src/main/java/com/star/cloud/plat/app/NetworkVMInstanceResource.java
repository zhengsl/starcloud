package com.star.cloud.plat.app;

import java.util.List;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.cloud.plat.model.VMInstance;
import com.star.cloud.plat.service.IVirtualizationService;
import com.star.cloud.plat.service.PlatServiceBus;

public class NetworkVMInstanceResource extends ServerResource {
	
	private static final Logger log = LoggerFactory.getLogger(NetworkVMInstanceResource.class);
	
	private static final ObjectMapper objMapper = new ObjectMapper();
	
	@Get
	public String getVMInstanceUnderNetwork() {
		try {
			String[] idPlusName = getAttribute("idPlusName").split("[+]");
			String networkId = idPlusName[0];
			String networkName = idPlusName.length == 2 ? idPlusName[1] : "";
			IVirtualizationService vmService = PlatServiceBus.getVirtualizationService();
			List<VMInstance> vmInstances = vmService.getVMInstanceUnderNetwork(networkId, networkName);
			return objMapper.writeValueAsString(vmInstances);
		} catch (Exception e) {
			log.error("To get VMInstance under card failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}

}
