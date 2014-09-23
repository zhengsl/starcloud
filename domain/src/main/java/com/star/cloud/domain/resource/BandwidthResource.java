package com.star.cloud.domain.resource;

import com.star.cloud.domain.common.unit.BandwidthUnit;

public class BandwidthResource extends Resource {
	
	private int bandwidth;
	
	private BandwidthUnit bandwidthUnit;

	public int getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(int bandwidth) {
		this.bandwidth = bandwidth;
	}

	public BandwidthUnit getBandwidthUnit() {
		return bandwidthUnit;
	}

	public void setBandwidthUnit(BandwidthUnit bandwidthUnit) {
		this.bandwidthUnit = bandwidthUnit;
	}
	
}
