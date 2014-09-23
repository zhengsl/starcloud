package com.star.cloud.plat.app;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.cloud.plat.model.Card;
import com.star.cloud.plat.model.Host;
import com.star.cloud.plat.service.IHostService;
import com.star.cloud.plat.service.PlatServiceBus;

public class CardResource extends ServerResource {
	
	private static final Logger log = LoggerFactory.getLogger(CardResource.class);
	
	private static final ObjectMapper objMapper = new ObjectMapper();
	
	@Get
	public String getClusterStructure() {
		try {
			String[] idPlusName = getAttribute("idPlusName").split("[+]");
			String id = idPlusName[0];
			String name = idPlusName.length == 2 ? idPlusName[1] : "";
			IHostService hService = PlatServiceBus.getHostService();
			Host host = hService.getHost(id, name);
			if (host instanceof Card)
				return objMapper.writeValueAsString((Card)host);
			else {
				String msg = "Card doesn't exist with " + id + "+" + name;
				log.error(msg);
				return new ErrorMessage(msg).toJsonString();
			}
		} catch (Exception e) {
			log.error("To get Cluster failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}

}
