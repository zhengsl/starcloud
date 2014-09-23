package com.star.cloud.virt.service;

import java.util.List;

import com.star.cloud.virt.model.StarNetwork;

public interface INetworkService {
	
	StarNetwork	getNetwork(String id);
	
	List<StarNetwork> getAllNetworks();

}
