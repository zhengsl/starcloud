package com.star.cloud.domain.common;

public class CPU {
	
	private int socketNum;
	private int coreNum;
	private int threadNum;
	
	private String frequency;
	
	private String model;
	
	private String otherInfo;

	public int getSocketNum() {
		return socketNum;
	}

	public void setSocketNum(int socketNum) {
		this.socketNum = socketNum;
	}

	public int getCoreNum() {
		return coreNum;
	}

	public void setCoreNum(int coreNum) {
		this.coreNum = coreNum;
	}

	public int getThreadNum() {
		return threadNum;
	}

	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	
}
