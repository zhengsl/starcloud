package com.star.cloud.plat.model;

import java.util.List;

public class DiskStatus {
	
	private String hostName;
	
	private float totalRead;
	private float totalWrite;
	
	private List<DiskStatusDetail> details;

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public float getTotalRead() {
		return totalRead;
	}

	public void setTotalRead(float totalRead) {
		this.totalRead = totalRead;
	}

	public float getTotalWrite() {
		return totalWrite;
	}

	public void setTotalWrite(float totalWrite) {
		this.totalWrite = totalWrite;
	}

	public List<DiskStatusDetail> getDetails() {
		return details;
	}

	public void setDetails(List<DiskStatusDetail> details) {
		this.details = details;
	}
	
}
