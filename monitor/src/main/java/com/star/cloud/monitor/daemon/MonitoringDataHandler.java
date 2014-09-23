package com.star.cloud.monitor.daemon;

import java.util.Map;

public interface MonitoringDataHandler {
	
	void handle(Map<String, Map<String, String>> serviceData, Map<String, Map<String, String>> middleData);
	
}
