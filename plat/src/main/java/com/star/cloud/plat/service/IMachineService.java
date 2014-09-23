package com.star.cloud.plat.service;

import java.util.List;

import com.star.cloud.plat.model.Card;
import com.star.cloud.plat.model.DeploymentStatus;
import com.star.cloud.plat.model.MachineStatus;
import com.star.cloud.plat.model.HostStatus;
import com.star.cloud.plat.model.ServiceStatus;

public interface IMachineService {
	
	MachineStatus getMachineStatus(String id, String name);
	
	HostStatus getHostStatus(String id, String name);
	
	List<DeploymentStatus> getDeploymentStatus(String id, String name);
	
	List<ServiceStatus> getServiceStatus(String hostId, String hostName);
	
	List<Card> getCardUnderMachine(String machineId);

}
