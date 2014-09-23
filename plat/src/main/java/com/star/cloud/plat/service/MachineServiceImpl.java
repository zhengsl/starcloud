package com.star.cloud.plat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.cloud.monitor.service.IQFMachineService;
import com.star.cloud.monitor.service.MonitorServiceBus;
import com.star.cloud.plat.dao.IMachineDao;
import com.star.cloud.plat.dao.PlatDaoBus;
import com.star.cloud.plat.model.Card;
import com.star.cloud.plat.model.DeploymentStatus;
import com.star.cloud.plat.model.Host;
import com.star.cloud.plat.model.HostStatus;
import com.star.cloud.plat.model.Machine;
import com.star.cloud.plat.model.MachineStatus;
import com.star.cloud.plat.model.ServiceStatus;
import com.star.cloud.plat.model.StarCloudPlatException;

public class MachineServiceImpl implements IMachineService {
	
	private static final Logger log = LoggerFactory.getLogger(MachineServiceImpl.class);

	@Override
	public MachineStatus getMachineStatus(String id, String name) {
		IMachineDao machineDao = PlatDaoBus.getMachineDao();
		Machine machine = machineDao.queryMachine(id, name);
		if (machine == null) {
			String msg = "Machine does not exists with " + id + "+" + name;
			log.error(msg);
			throw new StarCloudPlatException(msg);
		}
		
		IQFMachineService qfService = MonitorServiceBus.getQFMachineService();
		Map<String, String> status = qfService.getBasicMachineStatus(machine.getHostName());
		return MachineStatus.fromMap(status);
	}

	@Override
	public HostStatus getHostStatus(String id, String name) {
		IHostService hService = PlatServiceBus.getHostService();
		Host host = hService.getHost(id, name);
		if (host == null) {
			String msg = "Host does not exists: " + id + "+" + name;
			log.error(msg);
			throw new StarCloudPlatException(msg);
		}
		
		IQFMachineService qfService = MonitorServiceBus.getQFMachineService();
		Map<String, String> status = qfService.getBasicHostStatus(host.getName());
		return HostStatus.fromMap(status);
	}

	@Override
	public List<DeploymentStatus> getDeploymentStatus(String id, String name) {
		throw new StarCloudPlatException("Has not been supported yet!");
	}

	@Override
	public List<ServiceStatus> getServiceStatus(String id, String name) {
		IHostService hService = PlatServiceBus.getHostService();
		Host host = hService.getHost(id, name);
		if (host == null) {
			String msg = "Host does not exists: " + id + "+" + name;
			log.error(msg);
			throw new StarCloudPlatException(msg);
		}
		
		IQFMachineService qfService = MonitorServiceBus.getQFMachineService();
		List<Map<String, String>> mList = qfService.getBasicServiceStatus(host.getName());
		List<ServiceStatus> ssList = new ArrayList<ServiceStatus>();
		for (Map<String, String> m : mList) {
			ssList.add(ServiceStatus.fromMap(m));
		}
		return ssList;
	}

	@Override
	public List<Card> getCardUnderMachine(String machineId) {
		IMachineDao machineDao = PlatDaoBus.getMachineDao();
		Machine machine = machineDao.queryMachine(machineId, "");
		if (machine == null) {
			String msg = "Machine does not exists with " + machineId + "+";
			log.error(msg);
			throw new StarCloudPlatException(msg);
		}
		
		IMachineDao mdao = PlatDaoBus.getMachineDao();
		return mdao.queryCardUnderMachine(machineId);
	}

}
