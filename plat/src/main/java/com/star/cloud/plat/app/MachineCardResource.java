package com.star.cloud.plat.app;

import java.util.List;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.cloud.plat.model.Card;
import com.star.cloud.plat.service.IMachineService;
import com.star.cloud.plat.service.PlatServiceBus;

public class MachineCardResource extends ServerResource {
	
private static final Logger log = LoggerFactory.getLogger(MachineCardResource.class);
	
	private static final ObjectMapper objMapper = new ObjectMapper();
	
	@Get
	public String getMachineStructure() {
		try {
			String machineId = getAttribute("machineId");
			IMachineService mService = PlatServiceBus.getMachineService();
			List<Card> cardList = mService.getCardUnderMachine(machineId);
			return objMapper.writeValueAsString(cardList);
		} catch (Exception e) {
			log.error("To get Card List failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}

}
