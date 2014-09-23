package com.star.cloud.plat.model;

import java.util.List;

public class Image {
	
	private String id;
	private String name;
	private String provider;
	private OS os;
	private int minCPU;
	private int minRAM;
	private int minDisk;
	private int minNIC;
	
	private List<Application> applications;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public OS getOs() {
		return os;
	}

	public void setOs(OS os) {
		this.os = os;
	}

	public int getMinCPU() {
		return minCPU;
	}

	public void setMinCPU(int minCPU) {
		this.minCPU = minCPU;
	}

	public int getMinRAM() {
		return minRAM;
	}

	public void setMinRAM(int minRAM) {
		this.minRAM = minRAM;
	}

	public int getMinDisk() {
		return minDisk;
	}

	public void setMinDisk(int minDisk) {
		this.minDisk = minDisk;
	}

	public int getMinNIC() {
		return minNIC;
	}

	public void setMinNIC(int minNIC) {
		this.minNIC = minNIC;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	
}
