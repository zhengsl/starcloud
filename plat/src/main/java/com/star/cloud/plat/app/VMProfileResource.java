package com.star.cloud.plat.app;

import java.util.List;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.cloud.plat.model.VMProfile;
import com.star.cloud.plat.service.IVirtualizationService;
import com.star.cloud.plat.service.PlatServiceBus;

public class VMProfileResource extends ServerResource {
	
	private static final Logger log = LoggerFactory.getLogger(VMProfileResource.class);
	
	private static final ObjectMapper objMapper = new ObjectMapper();
	
	@Get
	public String getVMProfile() {
		try {
			IVirtualizationService vmService = PlatServiceBus.getVirtualizationService();
			
			if (getAttribute("idPlusName").equals("all")) {
				List<VMProfile> vmProfiles = vmService.getAllVMProfiles();
				return objMapper.writeValueAsString(vmProfiles);
			}
			
			String[] idPlusName = getAttribute("idPlusName").split("[+]");
			String id = idPlusName[0];
			String name = idPlusName.length == 2 ? idPlusName[1] : "";
			VMProfile vmprofile = vmService.getVMProfile(id, name);
			return objMapper.writeValueAsString(vmprofile);
		} catch (Exception e) {
			log.error("To get VMProfile failed!", e);
			return new ErrorMessage(e.toString()).toJsonString();
		}
	}
	
	@Post
	public String addVMProfile(String vmProfileJson) {
		try {
			VMProfile vmProfile = objMapper.readValue(vmProfileJson, VMProfile.class);
			IVirtualizationService vmService = PlatServiceBus.getVirtualizationService();
			vmService.addVMProfile(vmProfile);
			return new SuccessMessage("Adding VMProfile succeed!").toJsonString();
		} catch (Exception e) {
			log.error("To add VMProfile failed!", e);
			return new ErrorMessage(e.toString()).toJsonString();
		}
	}
	
	@Put
	public String modifyVMProfile(String vmProfileJson) {
		try {
			VMProfile vmProfile = objMapper.readValue(vmProfileJson, VMProfile.class);
			IVirtualizationService vmService = PlatServiceBus.getVirtualizationService();
			vmService.modifyVMProfile(vmProfile);
			return new SuccessMessage("Modifying VMProfile succeed!").toJsonString();
		} catch (Exception e) {
			log.error("To modify VMProfile failed!", e);
			return new ErrorMessage(e.toString()).toJsonString();
		}
	}
	
	@Delete
	public String removeVMProfile() {
		try {
			String[] idPlusName = getAttribute("idPlusName").split("[+]");
			String id = idPlusName[0];
			String name = idPlusName.length == 2 ? idPlusName[1] : "";
			IVirtualizationService vmService = PlatServiceBus.getVirtualizationService();
			vmService.removeVMProfile(id, name);
			return new SuccessMessage("Removing VMProfile succeed!").toJsonString();
		} catch (Exception e) {
			log.error("To remove VMProfile failed!", e);
			return new ErrorMessage(e.toString()).toJsonString();
		}
	}

}
