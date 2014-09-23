package com.star.cloud.plat.app;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.cloud.plat.model.Cluster;
import com.star.cloud.plat.service.IClusterService;
import com.star.cloud.plat.service.PlatServiceBus;

public class ClusterStructureResource extends ServerResource {
	
	private static final Logger log = LoggerFactory.getLogger(ClusterStructureResource.class);
	
	private static final ObjectMapper objMapper = new ObjectMapper();
	
	@Get
	public String getClusterStructure() {
		try {
			String[] idPlusName = getAttribute("idPlusName").split("[+]");
			String id = idPlusName[0];
			String name = idPlusName.length == 2 ? idPlusName[1] : "";
			IClusterService cService = PlatServiceBus.getClusterService();
			Cluster cluster = cService.getCluster(id, name);
			return objMapper.writeValueAsString(cluster);
		} catch (Exception e) {
			log.error("To get Cluster failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}
	
	@Post
	public String addCluster(String clusterJson) {
		try {
			Cluster cluster = objMapper.readValue(clusterJson, Cluster.class);
			IClusterService cService = PlatServiceBus.getClusterService();
			cService.addCluster(cluster);
			return new SuccessMessage("Adding cluster succeed!").toJsonString();
		} catch (Exception e) {
			log.error("To add Cluster failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}


}
