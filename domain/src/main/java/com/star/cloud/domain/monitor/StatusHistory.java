package com.star.cloud.domain.monitor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StatusHistory {
	
	private long timestamp;
	
	private String hostName;
	
	private String serviceName;
	
	private String fromStatus;
	
	private String toStatus;
	
	private String content;

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
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

	public String getFromStatus() {
		return fromStatus;
	}

	public void setFromStatus(String fromStatus) {
		this.fromStatus = fromStatus;
	}

	public String getToStatus() {
		return toStatus;
	}

	public void setToStatus(String toStatus) {
		this.toStatus = toStatus;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sb.append(formatter.format(new Date(timestamp)));
		sb.append(" " + hostName);
		sb.append(" " + serviceName);
		sb.append(" " + fromStatus);
		sb.append(" " + toStatus);
		sb.append(" " + content);
		return sb.toString();
	}

}
