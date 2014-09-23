package com.star.cloud.domain.common;

import com.star.cloud.domain.common.unit.StorageUnit;


public class Disk {
	
	private int amount;
	private StorageUnit unit;
	
	private String type;
	
	private String otherInfo;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public StorageUnit getUnit() {
		return unit;
	}

	public void setUnit(StorageUnit unit) {
		this.unit = unit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	
}
