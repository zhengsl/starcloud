package com.star.cloud.plat.app;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.cloud.plat.model.HostStatus;
import com.star.cloud.plat.service.IMachineService;
import com.star.cloud.plat.service.PlatServiceBus;

public class HostStatusResource extends ServerResource {
	
	private static final Logger log = LoggerFactory.getLogger(HostStatusResource.class);
	
	private static final ObjectMapper objMapper = new ObjectMapper();
	
	@Get
	public String getHostStatus() {
		try {
			String[] idPlusName = getAttribute("idPlusName").split("[+]");
			String id = idPlusName[0];
			String name = idPlusName.length == 2 ? idPlusName[1] : "";
			IMachineService mService = PlatServiceBus.getMachineService();
			HostStatus status = mService.getHostStatus(id, name);
			return objMapper.writeValueAsString(status);
		} catch (Exception e) {
			log.error("To get HostStatus failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}

}
