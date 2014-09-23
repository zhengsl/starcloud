package com.star.cloud.plat.model;

public class NIC {
	
	private int bandwidth;
	private BandwidthUnit unit;
	
	public int getBandwidth() {
		return bandwidth;
	}
	
	public void setBandwidth(int bandwidth) {
		this.bandwidth = bandwidth;
	}
	
	public BandwidthUnit getUnit() {
		return unit;
	}
	
	public void setUnit(BandwidthUnit unit) {
		this.unit = unit;
	}

}
