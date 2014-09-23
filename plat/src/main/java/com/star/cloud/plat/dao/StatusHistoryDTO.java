package com.star.cloud.plat.dao;

import java.util.Date;

import com.star.cloud.plat.model.StatusHistory;

public class StatusHistoryDTO {
	
	private Date timestamp;
	
	private String hostName;
	
	private String serviceName;
	
	private int fromStatus;
	
	private int toStatus;
	
	private String content;
	
	public static final String[] ServiceStatus = {"UNAVAILABLE", "OK", "WARNING", "CRITICAL", "UNKNOWN"};
	public static final String[] HostStatus = {"UNAVAILABLE", "UP", "DOWN", "UNREACHABLE"};
	
	public StatusHistory toStatusHistory() {
		StatusHistory sh = new StatusHistory();
		sh.setTimestamp(timestamp.getTime());
		sh.setHostName(hostName);
		sh.setServiceName(serviceName);
		if (serviceName == null) {
			sh.setFromStatus(HostStatus[fromStatus+1]);
			sh.setToStatus(HostStatus[toStatus+1]);
		} else {
			sh.setFromStatus(ServiceStatus[fromStatus+1]);
			sh.setToStatus(ServiceStatus[toStatus+1]);
		}
		sh.setContent(content);
		return sh;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public int getFromStatus() {
		return fromStatus;
	}

	public void setFromStatus(int fromStatus) {
		this.fromStatus = fromStatus;
	}

	public int getToStatus() {
		return toStatus;
	}

	public void setToStatus(int toStatus) {
		this.toStatus = toStatus;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
