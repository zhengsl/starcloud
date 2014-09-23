package com.star.cloud.monitor.service;

import java.util.List;
import java.util.Map;

public interface IQFMachineService {
	
	Map<String, String> getBasicMachineStatus(String hostName);
	
	Map<String, String> getBasicHostStatus(String hostName);
	
	Map<String, String> getDetailDiskStatus(String hostName);
	
	Map<String, String> getDetailNetworkStatus(String hostName);
	
	List<Map<String, String>> getBasicServiceStatus(String hostName);

	void addMachineStatusData(String hostName, Map<String, String> data);
	
	void addHostStatusData(String hostName, Map<String, String> data);
	
	void addServiceStatusData(String hostName, List<Map<String, String>> data);
	
	void addDiskStatusData(String hostName, Map<String, String> data);
	
	void addNetworkStatusData(String hostName, Map<String, String> data);
	
}
