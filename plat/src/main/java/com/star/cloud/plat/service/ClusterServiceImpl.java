package com.star.cloud.plat.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.cloud.plat.dao.IClusterDao;
import com.star.cloud.plat.dao.IMachineDao;
import com.star.cloud.plat.dao.PlatDaoBus;
import com.star.cloud.plat.model.Card;
import com.star.cloud.plat.model.Cluster;
import com.star.cloud.plat.model.Group;
import com.star.cloud.plat.model.Machine;
import com.star.cloud.plat.model.MachineModel;
import com.star.cloud.plat.model.StarCloudPlatException;
import com.star.cloud.plat.util.IDUtil;

public class ClusterServiceImpl implements IClusterService {
	
	private static final Logger log = LoggerFactory.getLogger(ClusterServiceImpl.class);

	@Override
	public Cluster getCluster(String id, String name) {
		IClusterDao clusterDao = PlatDaoBus.getClusterDao();
		return clusterDao.queryCluster(id, name);
	}

	@Override
	public Group getGroup(String id, String name) {
		IClusterDao clusterDao = PlatDaoBus.getClusterDao();
		return clusterDao.queryGroup(id, name);
	}

	@Override
	public Machine getMachine(String id, String name) {
		IMachineDao machineDao = PlatDaoBus.getMachineDao();
		return machineDao.queryMachine(id, name);
	}

	@Override
	public void addCluster(Cluster cluster) {
		throw new StarCloudPlatException("Has not been supported yet!");
	}

	@Override
	public void addGroup(Group group) {
		Group g = getGroup("", group.getName());
		if (g != null) {
			String msg = "To add Group failed: Group already exists:+" + group.getName();
			log.error(msg);
			throw new StarCloudPlatException(msg);
		}
		
		IMachineDao machineDao = PlatDaoBus.getMachineDao();
		for (Machine machine : group.getMachines()) {
			machine.setId(IDUtil.generateID());
			
			MachineModel mm = new MachineModel(machine);
			List<Card> cards = mm.getCards();
			machineDao.insertCards(cards);
		}
		IClusterDao clusterDao = PlatDaoBus.getClusterDao();
		clusterDao.insertGroup(group);
	}

	@Override
	public void addMachine(Machine machine) {
		Machine m = getMachine("", machine.getName());
		if (m != null) {
			String msg = "To add Machine failed: Machine already exists:+" + machine.getName();
			log.error(msg);
			throw new StarCloudPlatException(msg);
		}
		
		machine.setId(IDUtil.generateID());
		
		IMachineDao machineDao = PlatDaoBus.getMachineDao();
		machineDao.insertMachine(machine);
		
		MachineModel mm = new MachineModel(machine);
		List<Card> cards = mm.getCards();
		machineDao.insertCards(cards);
	}

	@Override
	public void removeCluster(String id, String name) {
		throw new StarCloudPlatException("Has not been supported yet!");
	}

	@Override
	public void removeGroup(String id, String name) {
		Group g = getGroup(id, name);
		if (g == null) {
			String msg = "Trying to remove a nonexistent Group: " + id + "+" + name;
			log.warn(msg);
			return;
		}
		IClusterDao clusterDao = PlatDaoBus.getClusterDao();
		clusterDao.deleteGroupByIdName(id, name);
	}

	@Override
	public void removeMachine(String id, String name) {
		Machine m = getMachine(id, name);
		if (m == null) {
			String msg = "Trying to remove a nonexistent Machine: " + id + "+" + name;
			log.warn(msg);
			return;
		}
		IMachineDao machineDao = PlatDaoBus.getMachineDao();
		machineDao.deleteMachineByIdName(id, name);
	}

}
