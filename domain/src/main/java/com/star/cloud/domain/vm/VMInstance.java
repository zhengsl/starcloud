package com.star.cloud.domain.vm;

import com.star.cloud.domain.app.Host;
import com.star.cloud.domain.common.IPv4;
import com.star.cloud.domain.hardware.Card;

public class VMInstance extends Host {
	
	private String id;
	private String name;
	
	private String status;
	
	private VMProfile profile;
	
	private IPv4 innerIp;
	private IPv4 outerIp;
	
	private String networkId;
	private String networkName;
	
	private Card card;
	
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
