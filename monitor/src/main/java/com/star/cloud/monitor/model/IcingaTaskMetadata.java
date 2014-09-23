package com.star.cloud.monitor.model;

import java.util.LinkedList;
import java.util.List;

public class IcingaTaskMetadata {
	
	private String taskName;
	
	private List<String> services = new LinkedList<String>();
	
	public void addService(String serviceName) {
		services.add(serviceName);
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public List<String> getServices() {
		return services;
	}

	public void setServices(List<String> services) {
		this.services = services;
	}
	
}
