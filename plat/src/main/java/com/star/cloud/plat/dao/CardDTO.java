package com.star.cloud.plat.dao;

import com.star.cloud.plat.model.Card;

public class CardDTO {
	
	private String id;
	private String name;
	private String machineId;
	
	private String type;
	
	private String osName;
	
	private String ipAddress;
	private String ipNetmask;
	private String ipGateway;
	
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
	
	public static CardDTO fromCard(Card card) {
		CardDTO cdto = new CardDTO();
		cdto.setId(card.getId());
		cdto.setName(card.getName());
		cdto.setMachineId(card.getMachine().getId());
		cdto.setType(card.getType());
		cdto.setOsName(card.getOs().getName());
		cdto.setIpAddress(card.getIp().getAddress());
		cdto.setMacAddress(card.getMac().getAddress());
		cdto.setCpuSocketNum(card.getCpu().getSocketNum());
		cdto.setCpuCoreNum(card.getCpu().getCoreNum());
		cdto.setCpuThreadNum(card.getCpu().getThreadNum());
		cdto.setCpuFrequency(card.getCpu().getFrequency());
		cdto.setCpuModel(card.getCpu().getModel());
		cdto.setRamAmount(card.getRam().getAmount());
		cdto.setRamUnit(String.valueOf(card.getRam().getUnit()));
		cdto.setRamFrequency(card.getRam().getFrequency());
		cdto.setNicBandwidth(card.getNic().getBandwidth());
		cdto.setNicUnit(String.valueOf(card.getNic().getUnit()));
		cdto.setDiskAmount(card.getDisk().getAmount());
		cdto.setDiskUnit(String.valueOf(card.getDisk().getUnit()));
		cdto.setDiskType(card.getDisk().getType());
		return cdto;
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
	
	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
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
	
	public String getIpNetmask() {
		return ipNetmask;
	}
	
	public void setIpNetmask(String ipNetmask) {
		this.ipNetmask = ipNetmask;
	}
	
	public String getIpGateway() {
		return ipGateway;
	}
	
	public void setIpGateway(String ipGateway) {
		this.ipGateway = ipGateway;
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
