package com.star.cloud.plat.dao;

import com.star.cloud.plat.model.VMInstance;

public class VMInstanceDTO {
	
	private String id;
	private String name;
	private String inneripAddress;
	private String outeripAddress;
	private String vmprofileId;
	private String vmprofileName;
	private String cardId;
	private String cardName;
	private String networkId;
	private String networkName;
	private String status;
	
	public static VMInstanceDTO fromVMInstance(VMInstance vmins) {
		VMInstanceDTO vmDto = new VMInstanceDTO();
		vmDto.setId(vmins.getId());
		vmDto.setName(vmins.getName());
		vmDto.setInneripAddress(vmins.getInnerIp().getAddress());
		String oip = vmins.getOuterIp() == null ? null : vmins.getOuterIp().getAddress();
		vmDto.setOuteripAddress(oip);
		vmDto.setVmprofileId(vmins.getProfile().getId());
		vmDto.setVmprofileName(vmins.getProfile().getName());
		vmDto.setCardId(vmins.getCard().getId());
		vmDto.setCardName(vmins.getCard().getName());
		vmDto.setNetworkId(vmins.getNetworkId());
		vmDto.setNetworkName(vmins.getNetworkName());
		vmDto.setStatus(vmins.getStatus());
		return vmDto;
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
	
	public String getInneripAddress() {
		return inneripAddress;
	}
	
	public void setInneripAddress(String inneripAddress) {
		this.inneripAddress = inneripAddress;
	}
	
	public String getOuteripAddress() {
		return outeripAddress;
	}
	
	public void setOuteripAddress(String outeripAddress) {
		this.outeripAddress = outeripAddress;
	}
	
	public String getVmprofileId() {
		return vmprofileId;
	}
	
	public void setVmprofileId(String vmprofileId) {
		this.vmprofileId = vmprofileId;
	}
	
	public String getVmprofileName() {
		return vmprofileName;
	}
	
	public void setVmprofileName(String vmprofileName) {
		this.vmprofileName = vmprofileName;
	}
	
	public String getCardId() {
		return cardId;
	}
	
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	public String getCardName() {
		return cardName;
	}
	
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	
	public String getNetworkId() {
		return networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
}
