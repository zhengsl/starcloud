package com.star.cloud.domain.common;

import com.star.cloud.domain.common.unit.BandwidthUnit;


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
