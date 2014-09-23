package com.star.cloud.plat.app;

import java.util.List;
import java.util.Map;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.cloud.plat.model.VMInstance;
import com.star.cloud.plat.service.IVirtualizationService;
import com.star.cloud.plat.service.PlatServiceBus;

public class VMInstanceResource extends ServerResource {
	
	private static final Logger log = LoggerFactory.getLogger(VMInstanceResource.class);
	
	private static final ObjectMapper objMapper = new ObjectMapper();
	
	@Get
	public String getVMInstance() {
		try {
			IVirtualizationService vmService = PlatServiceBus.getVirtualizationService();
			if (getAttribute("idPlusName").equals("all")) {
				List<VMInstance> vmInstances = vmService.getAllVMInstances();
				return objMapper.writeValueAsString(vmInstances);
			}
			
			String[] idPlusName = getAttribute("idPlusName").split("[+]");
			String id = idPlusName[0];
			String name = idPlusName.length == 2 ? idPlusName[1] : "";
			VMInstance vmInstance = vmService.getVMInstance(id, name);
			return objMapper.writeValueAsString(vmInstance);
		} catch (Exception e) {
			log.error("To get VMInstance failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}
	
	@Post
	public String newVMIntance(String json) {
		try {
			Map<String, String> parameter = objMapper.readValue(json, new TypeReference<Map<String, String>>() {});
			String name = parameter.get("name");
			String vmprofileId = parameter.get("vmprofileId");
			String imageId = parameter.get("imageId");
			String networkId = parameter.get("networkId");
			IVirtualizationService vmService = PlatServiceBus.getVirtualizationService();
			VMInstance vmins = vmService.newVMInstance(name, imageId, vmprofileId, networkId);
			return objMapper.writeValueAsString(vmins);
		} catch (Exception e) {
			log.error("To create new VMInstance failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
		
	}
	
	@Delete
	public String removeVMInstance() {
		try {
			String[] idPlusName = getAttribute("idPlusName").split("[+]");
			String id = idPlusName[0];
			String name = idPlusName.length == 2 ? idPlusName[1] : "";
			IVirtualizationService vmService = PlatServiceBus.getVirtualizationService();
			vmService.removeVMInstance(id, name);
			return new SuccessMessage("Removing VMInstance succeed!").toJsonString();
		} catch (Exception e) {
			log.error("To remove VMInstance failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}

}
