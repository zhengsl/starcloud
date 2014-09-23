package com.star.cloud.monitor.daemon;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PassthroughCollectingHandler implements MonitoringDataHandler {

	@Override
	public void handle(Map<String, Map<String, String>> serviceData, Map<String, Map<String, String>> middleData) {
		Set<String> hostNames = serviceData.keySet();
		for (String hostName : hostNames) {
			Map<String, String> middleResult = new HashMap<String, String>();
			Map<String, String> data = serviceData.get(hostName);
			Set<String> keys = data.keySet();
			for (String key : keys) {
				if (key.contains(".")) {
					middleResult.put(key.replace(".", ":"), data.get(key));
				}
			}
			middleData.put(hostName, middleResult);
		}
	}

}
