package com.star.cloud.plat.model;

public class VMInstance extends Host {
	
	private String status;
	
	private VMProfile profile;
	
	private IPv4 innerIp;
	private IPv4 outerIp;
	
	private String networkId;
	private String networkName;
	
	private Card card;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public VMProfile getProfile() {
		return profile;
	}
	
	public void setProfile(VMProfile profile) {
		this.profile = profile;
	}
	
	public IPv4 getInnerIp() {
		return innerIp;
	}
	
	public void setInnerIp(IPv4 innerIp) {
		this.innerIp = innerIp;
	}
	
	public IPv4 getOuterIp() {
		return outerIp;
	}
	
	public void setOuterIp(IPv4 outerIp) {
		this.outerIp = outerIp;
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

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
	
}
