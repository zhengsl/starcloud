package com.star.cloud.plat.app;

import java.util.Map;

import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.cloud.plat.model.VMInstance;
import com.star.cloud.plat.service.IVirtualizationService;
import com.star.cloud.plat.service.PlatServiceBus;

public class VMInstanceControlResource extends ServerResource {
	
	private static final Logger log = LoggerFactory.getLogger(VMInstanceControlResource.class);
	
	private static final ObjectMapper objMapper = new ObjectMapper();
	
	@Post
	public String control(String json) {
		try {
			Map<String, String> parameter = objMapper.readValue(json, new TypeReference<Map<String, String>>() {});
			String vminsId = parameter.get("vminstanceId");
			String type = getAttribute("type");
			IVirtualizationService vs = PlatServiceBus.getVirtualizationService();
			VMInstance vmins = null;
			if (type.equals("start")) {
				vmins = vs.startVMInstance(vminsId);
			} else if (type.equals("stop")) {
				vmins = vs.stopVMInstance(vminsId);
			} else {
				String msg = "Unsupported control type for VMInstance: " + type;
				log.error(msg);
				return new ErrorMessage(msg).toJsonString();
			}
			return objMapper.writeValueAsString(vmins);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}

}
