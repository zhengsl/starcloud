package com.star.cloud.plat.model;

import java.util.Map;

public class Application {
	
	private String id;
	private String name;
	private String provider;
	
	private AppType type;
	
	private ResType resource;
	
	private int minCPU;
	private int minRAM;
	private int minDisk;
	private int minNIC;
	
	private OS os;
	
	private DeployType deployType;
	
	private Architecture architecture;
	
	private Map<String, String> parameters;
	
	public enum AppType {
		Encoder, Transcoder, Streamer, DRM
	}
	
	public enum ResType {
		Physical, Virtual, Hybrid
	}
	
	public enum DeployType {
		Distributed, Centralized
	}

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

	public AppType getType() {
		return type;
	}

	public void setType(AppType type) {
		this.type = type;
	}

	public ResType getResource() {
		return resource;
	}

	public void setResource(ResType resource) {
		this.resource = resource;
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

	public OS getOs() {
		return os;
	}

	public void setOs(OS os) {
		this.os = os;
	}

	public DeployType getDeployType() {
		return deployType;
	}

	public void setDeployType(DeployType deployType) {
		this.deployType = deployType;
	}
	
	public Architecture getArchitecture() {
		return architecture;
	}

	public void setArchitecture(Architecture architecture) {
		this.architecture = architecture;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}
	
}
