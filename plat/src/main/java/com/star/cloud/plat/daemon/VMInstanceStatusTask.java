package com.star.cloud.plat.daemon;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.cloud.monitor.daemon.Task;
import com.star.cloud.plat.model.VMInstance;
import com.star.cloud.plat.service.IVirtualizationService;
import com.star.cloud.plat.service.PlatServiceBus;
import com.star.cloud.virt.model.StarServer;
import com.star.cloud.virt.service.IServerService;
import com.star.cloud.virt.service.VirtualizationServiceBus;

public class VMInstanceStatusTask extends Task {
	
	private static final Logger log = LoggerFactory.getLogger(VMInstanceStatusTask.class);
	
	private static final String NAME = "VMInstanceSynchronization";

	@Override
	public String getTaskName() {
		return NAME;
	}

	@Override
	public void init() {
		
	}

	@Override
	public void execute() throws Exception {
		log.debug("Daemon task " + NAME + " is going to start...");
		long taskInterval = Long.parseLong(System.getProperty("taskInterval", "60000"));
		long times = 0;
		IServerService sService = VirtualizationServiceBus.getServerService();
		IVirtualizationService vService = PlatServiceBus.getVirtualizationService();
		while (true) {
			log.debug("Daemon task " + NAME + " is doing the job: " + (++times));
			List<StarServer> ssList = sService.getAllServers();
			log.debug("Got " + ssList.size() + " Servers.");
			for (StarServer ss : ssList) {
				VMInstance vmi = vService.getVMInstance(ss.getId(), ss.getName());
				if (vmi != null && !vmi.getStatus().equals(ss.getStatus()))
					log.debug("Get a VMInstance need to be update status: " + vmi.getId() + ":" + vmi.getStatus());
					log.debug("Updating VMInstance Status: " + ss.getId() + ":" + ss.getStatus());
					vService.updateVMInstanceStatus(ss.getId(), ss.getStatus());
			}
			Thread.sleep(taskInterval);
		}
	}

}
