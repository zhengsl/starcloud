package com.star.cloud.plat.app;

import java.util.List;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.cloud.plat.model.StatusHistory;
import com.star.cloud.plat.service.IHostService;
import com.star.cloud.plat.service.PlatServiceBus;

public class HostEventResource extends ServerResource {
	
	private static final Logger log = LoggerFactory.getLogger(HostEventResource.class);
	
	private static final ObjectMapper objMapper = new ObjectMapper();
	
	@Get
	public String getHostEvent() {
		try {
			String[] idPlusName = getAttribute("idPlusName").split("[+]");
			String id = idPlusName[0];
			String name = idPlusName.length == 2 ? idPlusName[1] : "";
			long since = Long.parseLong(getAttribute("sinceWhen"));
			IHostService hService = PlatServiceBus.getHostService();
			List<StatusHistory> events = hService.getEvents(id, name, since);
			return objMapper.writeValueAsString(events);
		} catch (Exception e) {
			log.error("To get StatusHistory failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}

}
