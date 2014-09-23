package com.star.cloud.plat.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.cloud.plat.model.Card;
import com.star.cloud.plat.model.Cluster;
import com.star.cloud.plat.model.Group;
import com.star.cloud.plat.model.Machine;
import com.star.cloud.plat.model.StarCloudPlatException;
import com.star.cloud.plat.util.IDUtil;

public class MachineDaoImpl implements IMachineDao {
	
	private static final Logger log = LoggerFactory.getLogger(MachineDaoImpl.class);
	
	private final SqlSessionFactory ssFactory;
	
	public MachineDaoImpl(SqlSessionFactory ssFactory) {
		this.ssFactory = ssFactory;
	}
	
	@Override
	public Machine queryMachine(String id, String name) {
		IDUtil.checkIdAndName(id, name);
		SqlSession session = ssFactory.openSession();
		try {
			MachineMapper machineMapper = session.getMapper(MachineMapper.class);
			return machineMapper.getMachine(id, name);
		} finally {
			session.close();
		}
	}

	@Override
	public Card queryCard(String id, String name) {
		IDUtil.checkIdAndName(id, name);
		SqlSession session = ssFactory.openSession();
		try {
			MachineMapper machineMapper = session.getMapper(MachineMapper.class);
			return machineMapper.getCard(id, name);
		} finally {
			session.close();
		}
	}
	
	@Override
	public List<Card> queryCardUnderMachine(String machineId) {
		SqlSession session = ssFactory.openSession();
		try {
			MachineMapper machineMapper = session.getMapper(MachineMapper.class);
			return machineMapper.getCardsUnderMachine(machineId);
		} finally {
			session.close();
		}
	}

	private void checkMachineIntegrity(Machine machine) {
		if (machine.getName() == null || machine.getName().isEmpty()) {
			throw new StarCloudPlatException("Machine integrity problem: "
					+ "Machine doesn't have a name.");
		}
		if (machine.getAdminIP() == null) {
			throw new StarCloudPlatException("Machine integrity problem: "
					+ "Machine doesn't have a IP for administration.");
		}
		Group group = machine.getGroup();
		if (group == null || group.getId() == null || group.getName() == null) {
			throw new StarCloudPlatException("Machine integrity problem: "
					+ "Machine should be belong to a group.");
		}
		Cluster cluster = machine.getGroup().getCluster();
		if (cluster == null || cluster.getId() == null || cluster.getName() == null) {
			throw new StarCloudPlatException("Machine integrity problem: "
					+ "Machine should be belong to a cluster.");
		}
	}

	@Override
	public void insertMachine(Machine machine) {
		checkMachineIntegrity(machine);
		SqlSession session = ssFactory.openSession();
		try {
			MachineDTO mdto = new MachineDTO();
			mdto.setId(machine.getId());
			mdto.setName(machine.getName());
			mdto.setModel(machine.getModel());
			mdto.setClusterId(machine.getGroup().getCluster().getId());
			mdto.setClusterName(machine.getGroup().getCluster().getName());
			mdto.setGroupId(machine.getGroup().getId());
			mdto.setGroupName(machine.getGroup().getName());
			mdto.setIpAddress(machine.getAdminIP().getAddress());
			mdto.setHostName(machine.getHostName());
			session.insert("insertMachine", mdto);
			session.commit();
		} catch (Exception e) {
			log.error("To insert machine failed!", e);
			session.rollback();
			throw new StarCloudPlatException(e);
		} finally {
			session.close();
		}
	}

	@Override
	public void deleteMachineByIdName(String id, String name) {
		IDUtil.checkIdAndName(id, name);
		SqlSession session = ssFactory.openSession();
		try {
			MachineDTO mdto = new MachineDTO();
			mdto.setId(id);
			mdto.setName(name);
			session.delete("deleteMachineByIdName", mdto);
			session.delete("deleteCardUnderMachine", mdto);
			session.commit();
		} catch (Exception e) {
			log.error("To delete machine failed!", e);
			session.rollback();
			throw new StarCloudPlatException(e);
		} finally {
			session.close();
		}
	}

	@Override
	public void insertCard(Card card) {
		SqlSession session = ssFactory.openSession();
		try {
			CardDTO cdto = CardDTO.fromCard(card);
			session.insert("insertCard", cdto);
			session.commit();
		} catch (Exception e) {
			log.error("To insert Card failed!", e);
			session.rollback();
			throw new StarCloudPlatException(e);
		} finally {
			session.close();
		}
	}

	@Override
	public void insertCards(List<Card> cards) {
		SqlSession session = ssFactory.openSession();
		try {
			for (Card card : cards) {
				CardDTO cdto = CardDTO.fromCard(card);
				session.insert("insertCard", cdto);
			}
			session.commit();
		} catch (Exception e) {
			log.error("To insert cards failed!", e);
			session.rollback();
			throw new StarCloudPlatException(e);
		} finally {
			session.close();
		}
	}
	
	@Override
	public void updateCard(Card card) {
		SqlSession session = ssFactory.openSession();
		try {
			CardDTO cdto = CardDTO.fromCard(card);
			session.update("updateCard", cdto);
			session.commit();
		} catch (Exception e) {
			log.error("To update card failed!", e);
			session.rollback();
			throw new StarCloudPlatException(e);
		} finally {
			session.close();
		}
	}
	
	@Override
	public void updateCards(List<Card> cards) {
		SqlSession session = ssFactory.openSession();
		try {
			for (Card card : cards) {
				CardDTO cdto = CardDTO.fromCard(card);
				session.update("updateCard", cdto);
			}
			session.commit();
		} catch (Exception e) {
			log.error("To update cards failed!", e);
			session.rollback();
			throw new StarCloudPlatException(e);
		} finally {
			session.close();
		}
	}

}

