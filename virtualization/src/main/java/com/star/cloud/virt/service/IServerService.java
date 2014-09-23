package com.star.cloud.virt.service;

import java.util.List;

import com.star.cloud.virt.model.StarServer;

public interface IServerService {
	
	StarServer newServer(String name, String imageId, String flavorId, String networkId);
	
	void removeServer(String id);
	
	void startServer(String id);
	
	void stopServer(String id);
	
	StarServer getServer(String id);
	
	List<StarServer> getAllServers();

}
