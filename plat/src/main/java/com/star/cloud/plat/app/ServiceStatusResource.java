package com.star.cloud.plat.app;

import java.util.List;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.cloud.plat.model.ServiceStatus;
import com.star.cloud.plat.service.IMachineService;
import com.star.cloud.plat.service.PlatServiceBus;

public class ServiceStatusResource extends ServerResource {
	
	private static final Logger log = LoggerFactory.getLogger(ServiceStatusResource.class);
	
	private static final ObjectMapper objMapper = new ObjectMapper();
	
	@Get
	public String getServiceStatus() {
		try {
			String[] idPlusName = getAttribute("idPlusName").split("[+]");
			String hostId = idPlusName[0];
			String hostName = idPlusName.length == 2 ? idPlusName[1] : "";
			IMachineService mService = PlatServiceBus.getMachineService();
			List<ServiceStatus> status = mService.getServiceStatus(hostId, hostName);
			return objMapper.writeValueAsString(status);
		} catch (Exception e) {
			log.info("To get Service Status failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}

}
