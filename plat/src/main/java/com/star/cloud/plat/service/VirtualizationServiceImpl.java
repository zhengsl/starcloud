package com.star.cloud.plat.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.cloud.plat.dao.IMachineDao;
import com.star.cloud.plat.dao.IVMDao;
import com.star.cloud.plat.dao.PlatDaoBus;
import com.star.cloud.plat.model.Card;
import com.star.cloud.plat.model.Host;
import com.star.cloud.plat.model.IPv4;
import com.star.cloud.plat.model.StarCloudPlatException;
import com.star.cloud.plat.model.VMInstance;
import com.star.cloud.plat.model.VMProfile;
import com.star.cloud.plat.util.IDUtil;
import com.star.cloud.virt.model.StarFlavor;
import com.star.cloud.virt.model.StarNetwork;
import com.star.cloud.virt.model.StarServer;
import com.star.cloud.virt.service.IFlavorService;
import com.star.cloud.virt.service.INetworkService;
import com.star.cloud.virt.service.IServerService;
import com.star.cloud.virt.service.VirtualizationServiceBus;

public class VirtualizationServiceImpl implements IVirtualizationService {
	
	private static final Logger log = LoggerFactory.getLogger(VirtualizationServiceImpl.class);

	@Override
	public List<VMProfile> getAllVMProfiles() {
		IVMDao vmDao = PlatDaoBus.getVMDao();
		return vmDao.queryAllVMProfiles();
	}

	@Override
	public VMProfile getVMProfile(String id, String name) {
		IVMDao vmDao = PlatDaoBus.getVMDao();
		return vmDao.queryVMProfile(id, name);
	}
	
	private void addFlavor(VMProfile vmProfile) {
		StarFlavor sf = new StarFlavor();
		sf.setId(vmProfile.getId());
		sf.setName(vmProfile.getName());
		sf.setVcpu(vmProfile.getVcpu());
		sf.setRam(vmProfile.getRam());
		sf.setDisk(vmProfile.getDisk());
		
		IFlavorService flavorService = VirtualizationServiceBus.getFlavorService();
		flavorService.addFlavor(sf);
	}
	
	private void removeFlavor(String id) {
		IFlavorService flavorService = VirtualizationServiceBus.getFlavorService();
		flavorService.removeFlavor(id);
	}

	@Override
	public void addVMProfile(VMProfile vmProfile) {
		VMProfile vmp = getVMProfile("", vmProfile.getName());
		if (vmp != null) {
			String msg = "To add new VMProfie failed: VMProfile with name " + vmProfile.getName() + " already exists.";
			log.error(msg);
			throw new StarCloudPlatException(msg);
		}
		
		vmProfile.setId(IDUtil.generateID());
		
		addFlavor(vmProfile);
		
		IVMDao vmDao = PlatDaoBus.getVMDao();
		vmDao.insertVMProfile(vmProfile);
	}

	@Override
	public void removeVMProfile(String id, String name) {
		VMProfile vmp = getVMProfile(id, name);
		
		if (vmp == null) {
			log.warn("Tring to remove a nonexistent VMProfile: " + id + "+" + name);
			return;
		}
		
		removeFlavor(vmp.getId());
		
		IVMDao vmDao = PlatDaoBus.getVMDao();
		vmDao.deleteVMProfile(vmp.getId(), vmp.getName());
	}

	@Override
	public void modifyVMProfile(VMProfile vmProfile) {
		IDUtil.checkIdAndName(vmProfile.getId(), vmProfile.getName());
		
		VMProfile vmp = getVMProfile(vmProfile.getId(), "");
		if (vmp == null) {
			String msg = "To modify VMProfile failed: VMProfile with id " + vmProfile.getId() + " doesn't exist.";
			log.error(msg);
			throw new StarCloudPlatException(msg);
		}
		
		removeFlavor(vmProfile.getId());
		addFlavor(vmProfile);
		
		IVMDao vmDao = PlatDaoBus.getVMDao();
		vmDao.updateVMProfile(vmProfile);
	}

	@Override
	public VMInstance newVMInstance(String name, String imageId, String profileId, String networkId) {
		VMInstance vmi = getVMInstance("", name);
		if (vmi != null) {
			String msg = "To create new VMInstance failed: VMInstance with name " + name + " already exists.";
			log.error(msg);
			throw new StarCloudPlatException(msg);
		}
		
		IServerService serverService = VirtualizationServiceBus.getServerService();
		StarServer ss = serverService.newServer(name, imageId, profileId, networkId);
		
		VMInstance vmInstance = new VMInstance();
		vmInstance.setId(ss.getId());
		vmInstance.setName(ss.getName());
		vmInstance.setStatus(ss.getStatus());
		
		IPv4 innerIP = new IPv4();
		innerIP.setAddress(ss.getFixedIp());
		vmInstance.setInnerIp(innerIP);
		
		IMachineDao mdao = PlatDaoBus.getMachineDao();
		Card card = mdao.queryCard("", ss.getHostName());
		vmInstance.setCard(card);
		
		VMProfile vmp = new VMProfile();
		vmp.setId(ss.getFlavorId());
		vmp.setName(ss.getFlavorName());
		vmInstance.setProfile(vmp);
		
		vmInstance.setNetworkId(networkId);
		INetworkService networkService = VirtualizationServiceBus.getNetworkService();
		StarNetwork sn = networkService.getNetwork(networkId);
		vmInstance.setNetworkName(sn.getName());
		
		IVMDao vmDao = PlatDaoBus.getVMDao();
		vmDao.insertVMInstance(vmInstance);
		
		return vmInstance;
	}

	@Override
	public void removeVMInstance(String id, String name) {
		VMInstance vmi = getVMInstance(id, name);
		if (vmi == null) {
			log.warn("Tring to remove a nonexistent VMInstance: " + id + "+" + name);
			return;
		}
		
		IServerService serverService = VirtualizationServiceBus.getServerService();
		serverService.removeServer(id);
		
		IVMDao vmDao = PlatDaoBus.getVMDao();
		vmDao.deleteVMInstance(id, name);
	}

	@Override
	public void migrateVMInstance(String id, Host host) {
		throw new StarCloudPlatException("Has not been supported yet!");
	}

	@Override
	public List<VMInstance> getAllVMInstances() {
		IVMDao vmDao = PlatDaoBus.getVMDao();
		return vmDao.queryAllVMInstances();
	}

	@Override
	public VMInstance getVMInstance(String id, String name) {
		IVMDao vmDao = PlatDaoBus.getVMDao();
		return vmDao.queryVMInstance(id, name);
	}
	
	@Override
	public List<VMInstance> getVMInstanceUnderCard(String cardId, String cardName) {
		IVMDao vmdao = PlatDaoBus.getVMDao();
		return vmdao.queryVMInstanceUnderCard(cardId, cardName);
	}

	@Override
	public VMInstance startVMInstance(String vminsId) {
		VMInstance vmi = getVMInstance(vminsId, "");
		if (vmi == null) {
			String msg = "Tring to start a nonexistent VMInstance: " + vminsId + "+";
			log.error(msg);
			throw new StarCloudPlatException(msg);
		}
		IServerService serverService = VirtualizationServiceBus.getServerService();
		serverService.startServer(vminsId);
		StarServer ss = serverService.getServer(vminsId);
		updateVMInstanceStatus(vminsId, ss.getStatus());
		return getVMInstance(vminsId, null);
	}

	@Override
	public VMInstance stopVMInstance(String vminsId) {
		VMInstance vmi = getVMInstance(vminsId, "");
		if (vmi == null) {
			String msg = "Tring to stop a nonexistent VMInstance: " + vminsId + "+";
			log.error(msg);
			throw new StarCloudPlatException(msg);
		}
		IServerService serverService = VirtualizationServiceBus.getServerService();
		serverService.stopServer(vminsId);
		StarServer ss = serverService.getServer(vminsId);
		updateVMInstanceStatus(vminsId, ss.getStatus());
		return getVMInstance(vminsId, null);
	}

	@Override
	public void updateVMInstanceStatus(String id, String status) {
		VMInstance vmi = getVMInstance(id, "");
		if (vmi == null) {
			log.warn("Tring to update the status of a nonexistent VMInstance: " + id + "+" + "");
			return;
		}
		IVMDao vmDao = PlatDaoBus.getVMDao();
		vmDao.updateVMInstanceStatus(id, status);
	}

	@Override
	public List<VMInstance> getVMInstanceUnderNetwork(String networkId, String networkName) {
		IVMDao vmdao = PlatDaoBus.getVMDao();
		return vmdao.queryVMInstanceUnderNetwork(networkId, networkName);
	}

}
