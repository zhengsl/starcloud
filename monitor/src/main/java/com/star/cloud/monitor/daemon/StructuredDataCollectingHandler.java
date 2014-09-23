package com.star.cloud.monitor.daemon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StructuredDataCollectingHandler implements MonitoringDataHandler {

	private final Map<String, String> fieldNameServiceKeyMap;
	
	private final Map<String, String> defaultValues;
	
	private final List<String> fieldNames;
	
	public StructuredDataCollectingHandler(List<String> fieldNames, Map<String, String> fieldNameServiceKeyMap, 
			Map<String, String> defaultValues) {
		this.fieldNames = fieldNames;
		this.fieldNameServiceKeyMap = fieldNameServiceKeyMap;
		this.defaultValues = defaultValues;
	}

	@Override
	public void handle(Map<String, Map<String, String>> serviceData, Map<String, Map<String, String>> middleData) {
		Set<String> hostNames = serviceData.keySet();
		for (String hostName : hostNames) {
			Map<String, String> middleResult = new HashMap<String, String>();
			for (String fieldName : fieldNames) {
				String serviceKey = fieldNameServiceKeyMap.get(fieldName);
				String data = serviceData.get(hostName).get(serviceKey);
				if (data == null || data.isEmpty()) {
					middleResult.put(fieldName, defaultValues.get(fieldName));
				} else {
					middleResult.put(fieldName, data);
				}
			}
			middleData.put(hostName, middleResult);
		}
	}


}

