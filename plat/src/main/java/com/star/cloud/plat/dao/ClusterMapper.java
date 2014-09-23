package com.star.cloud.plat.dao;

import com.star.cloud.plat.model.Cluster;
import com.star.cloud.plat.model.Group;

public interface ClusterMapper {

	Cluster getCluster(String id, String name);

	Group getGroup(String id, String name);

}
