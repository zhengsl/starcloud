package com.star.cloud.plat.service;

import com.star.cloud.plat.model.Cluster;
import com.star.cloud.plat.model.Group;
import com.star.cloud.plat.model.Machine;

public interface IClusterService {
	
	Cluster getCluster(String id, String name);
	
	Group getGroup(String id, String name);
	
	Machine getMachine(String id, String name);
	
	void addCluster(Cluster cluster);
	
	void addGroup(Group group);
	
	void addMachine(Machine machine);
	
	void removeCluster(String id, String name);
	
	void removeGroup(String id, String name);
	
	void removeMachine(String id, String name);
	
}
