package com.star.cloud.monitor.daemon;

import java.util.Map;
import java.util.Set;

import com.star.cloud.monitor.service.IQFMachineService;
import com.star.cloud.monitor.service.MonitorServiceBus;

public class NetworkStatusTask extends ServiceDataCollectingTask {
	
	public static final String NAME = "networkStatusAggregation";
	
	@Override
	public String getTaskName() {
		return NAME;
	}

	@Override
	public MonitoringDataHandlerChain constructHandlerChain() {
		MonitoringDataHandlerChain chain = new MonitoringDataHandlerChain();
		chain.addHandler(new PassthroughCollectingHandler());
		chain.addHandler(new PersistNetworkStatusHandler());
		return chain;
	}
	
	public static class PersistNetworkStatusHandler implements MonitoringDataHandler {

		@Override
		public void handle(Map<String, Map<String, String>> serviceData, Map<String, Map<String, String>> middleData) {
			Set<String> hostNames = middleData.keySet();
			for (String hostName : hostNames) {
				IQFMachineService qfmService = MonitorServiceBus.getQFMachineService();
				qfmService.addNetworkStatusData(hostName, middleData.get(hostName));
			}
		}
		
	}

}
