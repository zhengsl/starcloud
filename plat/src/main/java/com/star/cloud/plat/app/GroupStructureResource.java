package com.star.cloud.plat.app;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.cloud.plat.model.Group;
import com.star.cloud.plat.service.IClusterService;
import com.star.cloud.plat.service.PlatServiceBus;

public class GroupStructureResource extends ServerResource {
	
	private static final Logger log = LoggerFactory.getLogger(GroupStructureResource.class);
	
	private static final ObjectMapper objMapper = new ObjectMapper();
	
	@Get
	public String getGroupStructure() {
		try {
			String[] idPlusName = getAttribute("idPlusName").split("[+]");
			String id = idPlusName[0];
			String name = idPlusName.length == 2 ? idPlusName[1] : "";
			IClusterService cService = PlatServiceBus.getClusterService();
			Group group = cService.getGroup(id, name);
			return objMapper.writeValueAsString(group);
		} catch (Exception e) {
			log.error("To get Group failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}
	
	@Post
	public String addGroup(String groupJson) {
		try {
			Group group = objMapper.readValue(groupJson, Group.class);
			IClusterService cService = PlatServiceBus.getClusterService();
			cService.addGroup(group);
			return new SuccessMessage("Adding group succeed!").toJsonString();
		} catch (Exception e) {
			log.error("To add Group failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}
	
	@Delete
	public String removeGroup() {
		try {
			String[] idPlusName = getAttribute("idPlusName").split("[+]");
			String id = idPlusName[0];
			String name = idPlusName.length == 2 ? idPlusName[1] : "";
			IClusterService cService = PlatServiceBus.getClusterService();
			cService.removeGroup(id, name);
			return new SuccessMessage("Removing group succeed!").toJsonString();
		} catch (Exception e) {
			log.error("To remove Group failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}

}
