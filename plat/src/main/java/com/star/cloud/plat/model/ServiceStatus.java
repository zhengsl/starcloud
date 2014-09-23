package com.star.cloud.plat.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class ServiceStatus {
	
	private String serviceName;
	private float configuredCapacity;
	private float inusedCapacity;
	
	private Map<String, String> details = new LinkedHashMap<String, String>();
	
	private long timestamp;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public float getConfiguredCapacity() {
		return configuredCapacity;
	}

	public void setConfiguredCapacity(float configuredCapacity) {
		this.configuredCapacity = configuredCapacity;
	}

	public float getInusedCapacity() {
		return inusedCapacity;
	}

	public void setInusedCapacity(float inusedCapacity) {
		this.inusedCapacity = inusedCapacity;
	}

	public Map<String, String> getDetails() {
		return details;
	}

	public void setDetails(Map<String, String> details) {
		this.details = details;
	}
	
	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public static ServiceStatus fromMap(Map<String, String> m) {
		ServiceStatus ss = new ServiceStatus();
		for (String k : m.keySet()) {
			if (k.equals("serviceName")) {
				ss.setServiceName(m.get("serviceName"));
			} else if (k.equals("configuredCapacity")) {
				ss.setConfiguredCapacity(Float.parseFloat(m.get("configuredCapacity")));
			} else if (k.equals("inusedCapacity")) {
				ss.setInusedCapacity(Float.parseFloat(m.get("inusedCapacity")));
			} else if (k.equals("timestamp")) {
				ss.setTimestamp(Long.parseLong(m.get("timestamp")));
			} else {
				ss.getDetails().put(k, m.get(k));
			}
		}
		return ss;
	}
	
}
