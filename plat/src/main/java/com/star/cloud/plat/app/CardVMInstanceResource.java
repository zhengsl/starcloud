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

public class CardVMInstanceResource extends ServerResource {
	
	private static final Logger log = LoggerFactory.getLogger(CardVMInstanceResource.class);
	
	private static final ObjectMapper objMapper = new ObjectMapper();
	
	@Get
	public String getVMInstanceUnderCard() {
		try {
			String[] idPlusName = getAttribute("idPlusName").split("[+]");
			String cardId = idPlusName[0];
			String cardName = idPlusName.length == 2 ? idPlusName[1] : "";
			IVirtualizationService vmService = PlatServiceBus.getVirtualizationService();
			List<VMInstance> vmInstances = vmService.getVMInstanceUnderCard(cardId, cardName);
			return objMapper.writeValueAsString(vmInstances);
		} catch (Exception e) {
			log.error("To get VMInstance under card failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}

}
