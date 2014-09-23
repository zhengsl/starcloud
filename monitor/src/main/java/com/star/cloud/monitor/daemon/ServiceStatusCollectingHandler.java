package com.star.cloud.monitor.daemon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.cloud.monitor.service.IQFMachineService;
import com.star.cloud.monitor.service.MonitorServiceBus;

public class ServiceStatusCollectingHandler implements MonitoringDataHandler {
	
	private static final Logger log = LoggerFactory.getLogger(ServiceStatusCollectingHandler.class);

	@Override
	public void handle(Map<String, Map<String, String>> serviceData, Map<String, Map<String, String>> middleData) {
		log.debug("Starting to handle ServiceStatusData...");
		Set<String> hostNames = serviceData.keySet();
		for (String hostName : hostNames) {
			log.debug("For host: " + hostName);
			Map<String, Map<String, String>> serviceDataMap = new HashMap<String, Map<String, String>>();
			Map<String, String> serviceDataForOneHost = serviceData.get(hostName);
			for (String sKey : serviceDataForOneHost.keySet()) {
				log.debug("PerfData: " + sKey);
				String[] snameAndKey = sKey.split("[.]");
				if (snameAndKey.length == 2) { // ignore noise data.
					String sname = snameAndKey[0];
					String skey = snameAndKey[1];
					Map<String, String> dataMap = serviceDataMap.get(sname);
					if (dataMap == null) {
						dataMap = new HashMap<String, String>();
						dataMap.put("serviceName", sname);
						dataMap.put(skey, serviceDataForOneHost.get(sKey));
						serviceDataMap.put(sname, dataMap);
					} else {
						dataMap.put(skey, serviceDataForOneHost.get(sKey));
					}
				}
			}
			
			IQFMachineService qfmService = MonitorServiceBus.getQFMachineService();
			qfmService.addServiceStatusData(hostName, new ArrayList<Map<String, String>>(serviceDataMap.values()));
		}
	}


}
