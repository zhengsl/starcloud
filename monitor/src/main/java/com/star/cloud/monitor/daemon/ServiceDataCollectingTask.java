package com.star.cloud.monitor.daemon;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.star.cloud.monitor.icinga.IcingaRestRequest;
import com.star.cloud.monitor.model.IcingaTaskMetadata;
import com.star.cloud.monitor.model.StarCloudMonitorException;
import com.star.cloud.monitor.service.IDaemonManagementService;
import com.star.cloud.monitor.service.MonitorServiceBus;

public abstract class ServiceDataCollectingTask extends Task {
	
	private final MonitoringDataHandlerChain handlerChain = constructHandlerChain();
	
	public abstract MonitoringDataHandlerChain constructHandlerChain();

	@Override
	public void init() {
		IDaemonManagementService dmService = MonitorServiceBus.getDaemonManagementService();
		if (!dmService.taskExists(getTaskName())) {
			throw new StarCloudMonitorException("Task " + getTaskName() + " does not exists!");
		}
	}

	@Override
	public void execute() throws Exception {
		String icingaIp = System.getProperty("icingaIP", "127.0.0.1");
		int icingaPort = Integer.parseInt(System.getProperty("icingaPort", "80"));
		String authKey = System.getProperty("icingaAuthKey", "star123456");
		long taskInterval = Long.parseLong(System.getProperty("taskInterval", "60000"));
		
		IDaemonManagementService dmService = MonitorServiceBus.getDaemonManagementService();
		IcingaTaskMetadata tm = dmService.getTaskMetadata(getTaskName());
		List<String> serviceNames = tm.getServices();
		
		while (true) {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			
			List<String> urls = new ArrayList<String>();
			
			for (String sname : serviceNames) {
				IcingaRestRequest request = new IcingaRestRequest(sname, authKey);
				String url = request.generateRequestURL(icingaIp, icingaPort);
				urls.add(url);
			}
			
			DataCollectionThread t = new DataCollectionThread(httpClient, urls, handlerChain, getTaskName());
			t.run();
			
			httpClient.close();
			
			Thread.sleep(taskInterval);
		}
		
	}

}
