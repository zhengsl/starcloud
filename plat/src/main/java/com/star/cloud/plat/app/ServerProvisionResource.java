package com.star.cloud.plat.app;

import java.util.Map;

import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.cloud.plat.model.Host;
import com.star.cloud.plat.service.IHostService;
import com.star.cloud.plat.service.PlatServiceBus;
import com.star.cloud.plat.util.IDUtil;
import com.star.cloud.provision.service.IProvisionService;
import com.star.cloud.provision.service.ProvisionServiceBus;

public class ServerProvisionResource extends ServerResource {
	
	private static final Logger log = LoggerFactory.getLogger(ServerProvisionResource.class);
	
	private final ObjectMapper objMapper = new ObjectMapper();
	
	@Post
	public String doServerProvision(String entity) {
		try {
			Map<String, String> entityMap = objMapper.readValue(entity, new TypeReference<Map<String, String>>() {});
			String op = entityMap.get("op");
			if (!IDUtil.exists(op) || (!op.equals("start") && !op.equals("stop"))) {
				return new ErrorMessage("No valid op data in the entity.").toJsonString();
			}
			String[] idPlusName = getAttribute("idPlusName").split("[+]");
			String id = idPlusName[0];
			String name = idPlusName.length == 2 ? idPlusName[1] : "";
			IHostService hs = PlatServiceBus.getHostService();
			Host host = hs.getHost(id, name);
			if (host == null) {
				return new ErrorMessage("No Host with: " + id + "+" + name).toJsonString();
			}
			String hostName = host.getName();
			IProvisionService ps = ProvisionServiceBus.getProvisionService();
			if (op.equals("start")) {
				ps.startHost(hostName);
			} else if (op.equals("stop")) {
				ps.stopHost(hostName);
			}
			return new SuccessMessage(op + " " + hostName + "succeed!").toJsonString();
		} catch (Exception e) {
			log.error("To control server failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}

}
