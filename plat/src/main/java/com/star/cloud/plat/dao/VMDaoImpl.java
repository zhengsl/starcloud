package com.star.cloud.plat.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.cloud.plat.model.StarCloudPlatException;
import com.star.cloud.plat.model.VMInstance;
import com.star.cloud.plat.model.VMProfile;
import com.star.cloud.plat.util.IDUtil;

public class VMDaoImpl implements IVMDao {
	
	private static final Logger log = LoggerFactory.getLogger(VMDaoImpl.class);
	
	private final SqlSessionFactory ssFactory;
	
	public VMDaoImpl(SqlSessionFactory ssFactory) {
		this.ssFactory = ssFactory;
	}
	
	@Override
	public VMInstance queryVMInstance(String id, String name) {
		IDUtil.checkIdAndName(id, name);
		SqlSession session = ssFactory.openSession();
		try {
			VMMapper machineMapper = session.getMapper(VMMapper.class);
			return machineMapper.getVMInstance(id, name);
		} finally {
			session.close();
		}
	}
	
	@Override
	public List<VMInstance> queryVMInstanceUnderCard(String cardId, String cardName) {
		SqlSession session = ssFactory.openSession();
		try {
			VMMapper machineMapper = session.getMapper(VMMapper.class);
			return machineMapper.getVMInstanceUnderCard(cardId, cardName);
		} finally {
			session.close();
		}
	}

	@Override
	public List<VMProfile> queryAllVMProfiles() {
		SqlSession session = ssFactory.openSession();
		try {
			VMMapper machineMapper = session.getMapper(VMMapper.class);
			return machineMapper.getAllVMProfiles();
		} finally {
			session.close();
		}
	}

	@Override
	public VMProfile queryVMProfile(String id, String name) {
		SqlSession session = ssFactory.openSession();
		try {
			VMMapper machineMapper = session.getMapper(VMMapper.class);
			return machineMapper.getVMProfile(id, name);
		} finally {
			session.close();
		}
	}

	@Override
	public void insertVMProfile(VMProfile vmProfile) {
		SqlSession session = ssFactory.openSession();
		try {
			session.insert("insertVMProfile", vmProfile);
			session.commit();
		} catch (Exception e) {
			log.error("Failed to insert VMProfile", e);
			session.rollback();
			throw new StarCloudPlatException(e);
		} finally {
			session.close();
		}
	}

	@Override
	public void deleteVMProfile(String id, String name) {
		IDUtil.checkIdAndName(id, name);
		SqlSession session = ssFactory.openSession();
		try {
			VMProfile vmProfile = new VMProfile();
			vmProfile.setId(id);
			vmProfile.setName(name);
			session.insert("deleteVMProfile", vmProfile);
			session.commit();
		} catch (Exception e) {
			log.error("Failed to delete VMProfile", e);
			session.rollback();
			throw new StarCloudPlatException(e);
		} finally {
			session.close();
		}
	}

	@Override
	public List<VMInstance> queryAllVMInstances() {
		SqlSession session = ssFactory.openSession();
		try {
			VMMapper machineMapper = session.getMapper(VMMapper.class);
			return machineMapper.getAllVMInstances();
		} finally {
			session.close();
		}
	}
	
	@Override
	public void insertVMInstance(VMInstance vmInstance) {
		SqlSession session = ssFactory.openSession();
		try {
			VMInstanceDTO mdto = VMInstanceDTO.fromVMInstance(vmInstance);
			session.insert("insertVMInstance", mdto);
			session.commit();
		} catch (Exception e) {
			log.error("Failed to insert VMInstance", e);
			session.rollback();
			throw new StarCloudPlatException(e);
		} finally {
			session.close();
		}
	}

	@Override
	public void deleteVMInstance(String id, String name) {
		IDUtil.checkIdAndName(id, name);
		SqlSession session = ssFactory.openSession();
		try {
			VMInstance vmInstance = new VMInstance();
			vmInstance.setId(id);
			vmInstance.setName(name);
			session.insert("deleteVMInstance", vmInstance);
			session.commit();
		} catch (Exception e) {
			log.error("Failed to delete VMInstance", e);
			session.rollback();
			throw new StarCloudPlatException(e);
		} finally {
			session.close();
		}
	}

	@Override
	public void updateVMProfile(VMProfile vmProfile) {
		IDUtil.checkId(vmProfile.getId());
		SqlSession session = ssFactory.openSession();
		try {
			session.update("updateVMProfile", vmProfile);
			session.commit();
		} catch (Exception e) {
			log.error("Failed to update VMProfile", e);
			session.rollback();
			throw new StarCloudPlatException(e);
		} finally {
			session.close();
		}
	}

	@Override
	public void updateVMInstanceStatus(String id, String status) {
		SqlSession session = ssFactory.openSession();
		try {
			VMInstanceDTO vminsDTO = new VMInstanceDTO();
			vminsDTO.setId(id);
			vminsDTO.setStatus(status);
			session.update("updateVMInstanceStatus", vminsDTO);
			session.commit();
		} catch (Exception e) {
			log.error("Failed to update VMInstanceStatus", e);
			session.rollback();
			throw new StarCloudPlatException(e);
		} finally {
			session.close();
		}
	}

	@Override
	public List<VMInstance> queryVMInstanceUnderNetwork(String networkId, String networkName) {
		SqlSession session = ssFactory.openSession();
		try {
			VMMapper machineMapper = session.getMapper(VMMapper.class);
			return machineMapper.getVMInstanceUnderNetwork(networkId, networkName);
		} finally {
			session.close();
		}
	}

}
