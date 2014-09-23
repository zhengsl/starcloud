package com.star.cloud.provision.model;

public class CardInfo {
	
	private String hostName;
	
	private String type;
	
	private String osName;
	
	private String ipAddress;
	
	private String macAddress;
	
	private int cpuSocketNum;
	private int cpuCoreNum;
	private int cpuThreadNum;
	private String cpuFrequency;
	private String cpuModel;
	
	private int ramAmount;
	private String ramUnit;
	private String ramFrequency;
	
	private int nicBandwidth;
	private String nicUnit;
	
	private int diskAmount;
	private String diskUnit;
	private String diskType;

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public int getCpuSocketNum() {
		return cpuSocketNum;
	}

	public void setCpuSocketNum(int cpuSocketNum) {
		this.cpuSocketNum = cpuSocketNum;
	}

	public int getCpuCoreNum() {
		return cpuCoreNum;
	}

	public void setCpuCoreNum(int cpuCoreNum) {
		this.cpuCoreNum = cpuCoreNum;
	}

	public int getCpuThreadNum() {
		return cpuThreadNum;
	}

	public void setCpuThreadNum(int cpuThreadNum) {
		this.cpuThreadNum = cpuThreadNum;
	}

	public String getCpuFrequency() {
		return cpuFrequency;
	}

	public void setCpuFrequency(String cpuFrequency) {
		this.cpuFrequency = cpuFrequency;
	}

	public String getCpuModel() {
		return cpuModel;
	}

	public void setCpuModel(String cpuModel) {
		this.cpuModel = cpuModel;
	}

	public int getRamAmount() {
		return ramAmount;
	}

	public void setRamAmount(int ramAmount) {
		this.ramAmount = ramAmount;
	}

	public String getRamUnit() {
		return ramUnit;
	}

	public void setRamUnit(String ramUnit) {
		this.ramUnit = ramUnit;
	}

	public String getRamFrequency() {
		return ramFrequency;
	}

	public void setRamFrequency(String ramFrequency) {
		this.ramFrequency = ramFrequency;
	}

	public int getNicBandwidth() {
		return nicBandwidth;
	}

	public void setNicBandwidth(int nicBandwidth) {
		this.nicBandwidth = nicBandwidth;
	}

	public String getNicUnit() {
		return nicUnit;
	}

	public void setNicUnit(String nicUnit) {
		this.nicUnit = nicUnit;
	}

	public int getDiskAmount() {
		return diskAmount;
	}

	public void setDiskAmount(int diskAmount) {
		this.diskAmount = diskAmount;
	}

	public String getDiskUnit() {
		return diskUnit;
	}

	public void setDiskUnit(String diskUnit) {
		this.diskUnit = diskUnit;
	}

	public String getDiskType() {
		return diskType;
	}

	public void setDiskType(String diskType) {
		this.diskType = diskType;
	}

}
