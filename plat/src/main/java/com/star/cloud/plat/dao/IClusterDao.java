package com.star.cloud.plat.dao;

import com.star.cloud.plat.model.Cluster;
import com.star.cloud.plat.model.Group;

public interface IClusterDao {
	
	Cluster queryCluster(String id, String name);
	
	Group queryGroup(String id, String name);

	void insertGroup(Group group);

	void deleteGroupByIdName(String id, String name);
	
}
