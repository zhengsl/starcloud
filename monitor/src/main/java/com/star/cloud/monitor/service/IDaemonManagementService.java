package com.star.cloud.monitor.service;

import java.util.List;

import com.star.cloud.monitor.model.IcingaTaskMetadata;

public interface IDaemonManagementService {
	
	boolean taskExists(String taskName);
	
	IcingaTaskMetadata getTaskMetadata(String taskName);
	
	void addTask(String taskName);
	
	//void addHostsForTask(String taskName, List<String> hostNames);
	
	void addServiceForTask(String taskName, String serviceName);
	
	void setServiceForTask(String taskName, String serviceName);
	
	void addServicesForTask(String taskName, List<String> serviceNames);
	
	void setServicesForTask(String taskName, List<String> serviceNames);

}
