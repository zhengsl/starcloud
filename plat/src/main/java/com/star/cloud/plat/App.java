package com.star.cloud.plat;

import java.util.LinkedList;
import java.util.List;

import org.restlet.Component;
import org.restlet.data.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.cloud.monitor.daemon.DiskStatusTask;
import com.star.cloud.monitor.daemon.HostStatusTask;
import com.star.cloud.monitor.daemon.MachineStatusTask;
import com.star.cloud.monitor.daemon.NetworkStatusTask;
import com.star.cloud.monitor.daemon.ServiceStatusTask;
import com.star.cloud.monitor.daemon.TaskManager;
import com.star.cloud.monitor.service.IDaemonManagementService;
import com.star.cloud.monitor.service.MonitorServiceBus;
import com.star.cloud.plat.app.ApplicationApp;
import com.star.cloud.plat.app.ClusterApp;
import com.star.cloud.plat.app.MonitorApp;
import com.star.cloud.plat.app.VirtualizationApp;
import com.star.cloud.plat.daemon.VMInstanceStatusTask;
import com.star.cloud.plat.dao.PlatDaoBus;
import com.star.cloud.plat.service.PlatServiceBus;
import com.star.cloud.provision.service.ProvisionServiceBus;
import com.star.cloud.virt.service.VirtualizationServiceBus;

public class App {
	
	private static final Logger log = LoggerFactory.getLogger(App.class);
    
	public static void main(String[] args) {
		try {
			startApiServer();
			startApiBus();
			initializeData();
			startDaemonTask();
		} catch (Exception e) {
			log.error("System failed to start!", e);
			System.exit(1);
		}
    }
	
	private static void startApiServer() throws Exception {
		Component component = new Component();
		String hostAddress = System.getProperty("host", "127.0.0.1");
		int hostPort = Integer.parseInt(System.getProperty("port", "9090"));
		component.getServers().add(Protocol.HTTP, hostAddress, hostPort);
		component.getDefaultHost().attach("/plat/api/cluster", new ClusterApp());
		component.getDefaultHost().attach("/plat/api/monitor", new MonitorApp());
		component.getDefaultHost().attach("/plat/api/virtual", new VirtualizationApp());
		component.getDefaultHost().attach("/plat/api/app", new ApplicationApp());
		component.start();
	}
	
	private static void startApiBus() throws Exception {
		log.info("Initializing PlatServiceBus ...");
		PlatServiceBus.init();
		
		log.info("Initializing PlatDaoBus ...");
		PlatDaoBus.init();
		
		if (Boolean.parseBoolean(System.getProperty("icinga", "true"))) {
			log.info("Initializing MonitorServiceBus ...");
			MonitorServiceBus.init();
		}
		
		if (Boolean.parseBoolean(System.getProperty("openstack", "true"))) {
			log.info("Initializing VirtualizationServiceBus ...");
			VirtualizationServiceBus.init();
		}
		
		if (Boolean.parseBoolean(System.getProperty("saltstack", "true"))) {
			log.info("Initializing ProvisionServiceBus ...");
			ProvisionServiceBus.init();
		}
		
		log.info("All the service buses have been initialized.");
	}
	
	private static void initializeData() throws Exception {
		if (Boolean.parseBoolean(System.getProperty("icinga", "true"))) {
			IDaemonManagementService dms = MonitorServiceBus.getDaemonManagementService();
			
			dms.addTask(MachineStatusTask.NAME);
			dms.addTask(HostStatusTask.NAME);
			dms.addTask(ServiceStatusTask.NAME);
			dms.addTask(DiskStatusTask.NAME);
			dms.addTask(NetworkStatusTask.NAME);
			
			List<String> serviceNames1 = new LinkedList<String>();
			serviceNames1.add("env");
			serviceNames1.add("cost");
			serviceNames1.add("plat");
			dms.setServicesForTask(MachineStatusTask.NAME, serviceNames1);
			dms.setServiceForTask(HostStatusTask.NAME, "base");
			dms.setServiceForTask(ServiceStatusTask.NAME, "app");
			dms.setServiceForTask(DiskStatusTask.NAME, "disk");
			dms.setServiceForTask(NetworkStatusTask.NAME, "network");
		}
	}
	
	private static void startDaemonTask() throws Exception {
		TaskManager tm = new TaskManager();
		if (Boolean.parseBoolean(System.getProperty("icinga", "true"))) {
			tm.registerTask(new MachineStatusTask());
			tm.registerTask(new HostStatusTask());
			tm.registerTask(new ServiceStatusTask());
			tm.registerTask(new DiskStatusTask());
			tm.registerTask(new NetworkStatusTask());
		}
		if (Boolean.parseBoolean(System.getProperty("openstack", "true"))) {
			tm.registerTask(new VMInstanceStatusTask());
		}
		tm.startAllTask();
	}
}
