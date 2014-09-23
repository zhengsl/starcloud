package com.star.cloud.plat.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.cloud.plat.model.Cluster;
import com.star.cloud.plat.model.Group;
import com.star.cloud.plat.model.Machine;
import com.star.cloud.plat.model.StarCloudPlatException;
import com.star.cloud.plat.util.IDUtil;

public class ClusterDaoImpl implements IClusterDao {
	
	private static final Logger log = LoggerFactory.getLogger(ClusterDaoImpl.class);
	
	private final SqlSessionFactory ssFactory;
	
	public ClusterDaoImpl(SqlSessionFactory ssFactory) {
		this.ssFactory = ssFactory;
	}
	
	@Override
	public Cluster queryCluster(String id, String name) {
		IDUtil.checkIdAndName(id, name);
		SqlSession session = ssFactory.openSession();
		try {
			ClusterMapper clusterMapper = session.getMapper(ClusterMapper.class);
			return clusterMapper.getCluster(id, name);
		} finally {
			session.close();
		}
	}

	@Override
	public Group queryGroup(String id, String name) {
		IDUtil.checkIdAndName(id, name);
		SqlSession session = ssFactory.openSession();
		try {
			ClusterMapper clusterMapper = session.getMapper(ClusterMapper.class);
			return clusterMapper.getGroup(id, name);
		} finally {
			session.close();
		}
	}

	private void checkGroupIntegrity(Group group) {
		if (group.getName() == null || group.getName().isEmpty()) {
			throw new StarCloudPlatException("Group integrity problem: "
					+ "Group doesn't have a name.");
		}
		Cluster c = group.getCluster();
		if (c == null || !IDUtil.exists(c.getId()) || !IDUtil.exists(c.getName())) {
			throw new StarCloudPlatException("Group integrity problem: "
					+ "Cluster has not been set for group.");
		}
		if (group.getMachines().isEmpty()) {
			throw new StarCloudPlatException("Group integrity problem: "
					+ "At least one Machine should be included.");
		}
	}
	
	@Override
	public void insertGroup(Group group) {
		checkGroupIntegrity(group);
		SqlSession session = ssFactory.openSession();
		try {
			String groupId = IDUtil.generateID();
			for (Machine m : group.getMachines()) {
				MachineDTO mdto = new MachineDTO();
				mdto.setName(m.getName());
				mdto.setClusterId(group.getCluster().getId());
				mdto.setClusterName(group.getCluster().getName());
				mdto.setGroupId(groupId);
				mdto.setGroupName(group.getName());
				mdto.setIpAddress(m.getAdminIP().getAddress());
				mdto.setHostName(m.getHostName());
				if (IDUtil.exists(m.getId())) {
					session.insert("insertMachine", mdto);
				} else {
					mdto.setId(m.getId());
					session.update("updateMachine", mdto);
				}
			}
			session.commit();
		} catch (Exception e) {
			log.error("To insert Group failed!", e);
			session.rollback();
			throw new StarCloudPlatException(e);
		} finally {
			session.close();
		}
	}

	@Override
	public void deleteGroupByIdName(String id, String name) {
		IDUtil.checkIdAndName(id, name);
		SqlSession session = ssFactory.openSession();
		try {
			MachineDTO mdto = new MachineDTO();
			mdto.setGroupId(id);
			mdto.setGroupName(name);
			session.delete("deleteGroupByIdName", mdto);
			session.commit();
		} catch (Exception e) {
			log.error("To delete Group failed!", e);
			session.rollback();
			throw new StarCloudPlatException(e);
		} finally {
			session.close();
		}
	}

}
