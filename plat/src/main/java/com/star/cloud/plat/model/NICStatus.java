package com.star.cloud.plat.model;

import java.util.List;

public class NICStatus {
	
	private String hostName;
	
	private float totalIn;
	
	private float totalOut;
	
	private List<NICStatusDetail> details;

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public float getTotalIn() {
		return totalIn;
	}

	public void setTotalIn(float totalIn) {
		this.totalIn = totalIn;
	}

	public float getTotalOut() {
		return totalOut;
	}

	public void setTotalOut(float totalOut) {
		this.totalOut = totalOut;
	}

	public List<NICStatusDetail> getDetails() {
		return details;
	}

	public void setDetails(List<NICStatusDetail> details) {
		this.details = details;
	}
	
}
