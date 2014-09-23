package com.star.cloud.plat.model;

public class RAM {
	
	private int amount;
	private StorageUnit unit;
	
	private String frequency;
	
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

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	
}
