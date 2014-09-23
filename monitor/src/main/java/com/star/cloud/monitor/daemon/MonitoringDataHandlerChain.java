package com.star.cloud.monitor.daemon;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MonitoringDataHandlerChain {
	
	private final List<MonitoringDataHandler> handlers = new LinkedList<MonitoringDataHandler>();
	
	public void addHandler(MonitoringDataHandler handler) {
		this.handlers.add(handler);
	}
	
	public void process(Map<String, Map<String, String>> serviceData) {
		Map<String, Map<String, String>> middleData = new LinkedHashMap<String,  Map<String, String>>();
		for (MonitoringDataHandler sdh : handlers) {
			sdh.handle(serviceData, middleData);
		}
	}

}
