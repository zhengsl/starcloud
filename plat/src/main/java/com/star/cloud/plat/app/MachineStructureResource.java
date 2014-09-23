package com.star.cloud.plat.app;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.cloud.plat.model.Machine;
import com.star.cloud.plat.service.IClusterService;
import com.star.cloud.plat.service.PlatServiceBus;
import com.star.cloud.provision.model.MachineInfo;

public class MachineStructureResource extends ServerResource {
	
	private static final Logger log = LoggerFactory.getLogger(MachineStructureResource.class);
	
	private static final ObjectMapper objMapper = new ObjectMapper();
	
	@Get
	public String getMachineStructure() {
		try {
			String[] idPlusName = getAttribute("idPlusName").split("[+]");
			String id = idPlusName[0];
			String name = idPlusName.length == 2 ? idPlusName[1] : "";
			IClusterService cService = PlatServiceBus.getClusterService();
			Machine machine = cService.getMachine(id, name);
			return objMapper.writeValueAsString(machine);
		} catch (Exception e) {
			log.error("To get Machine failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}
	
	@Post
	public String addMachine(String machineJson) {
		try {
			MachineInfo machineInfo = objMapper.readValue(machineJson, MachineInfo.class);
			Machine machine = Machine.fromMachineInfo(machineInfo);
			IClusterService cService = PlatServiceBus.getClusterService();
			cService.addMachine(machine);
			return new SuccessMessage("Adding machine succeed!").toJsonString();
		} catch (Exception e) {
			log.error("To add Machine failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}
	
	@Delete
	public String removeMachine() {
		try {
			String[] idPlusName = getAttribute("idPlusName").split("[+]");
			String id = idPlusName[0];
			String name = idPlusName.length == 2 ? idPlusName[1] : "";
			IClusterService cService = PlatServiceBus.getClusterService();
			cService.removeMachine(id, name);
			return new SuccessMessage("Removing machine succeed!").toJsonString();
		} catch (Exception e) {
			log.error("To remove Machine failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}

}
