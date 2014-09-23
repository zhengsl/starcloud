package com.star.cloud.plat.app;

import java.util.ArrayList;
import java.util.List;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.cloud.plat.model.HostStatus;
import com.star.cloud.plat.service.IMachineService;
import com.star.cloud.plat.service.PlatServiceBus;

public class HostStatusArrayResource extends ServerResource {
	
private static final Logger log = LoggerFactory.getLogger(HostStatusResource.class);
	
	private static final ObjectMapper objMapper = new ObjectMapper();
	
	@Get
	public String getHostStatusArray() {
		try {
			String[] hostnames = getAttribute("namearray").split("[+]");
			List<HostStatus> statusList = new ArrayList<HostStatus>();
			IMachineService mService = PlatServiceBus.getMachineService();
			for (String hostname : hostnames) {
				HostStatus status = mService.getHostStatus("", hostname);
				statusList.add(status);
			}
			return objMapper.writeValueAsString(statusList);
		} catch (Exception e) {
			log.error("To get HostStatus failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}

}
