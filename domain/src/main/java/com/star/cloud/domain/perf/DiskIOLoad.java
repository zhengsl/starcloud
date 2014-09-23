package com.star.cloud.domain.perf;

import com.star.cloud.domain.common.unit.BandwidthUnit;

public class DiskIOLoad extends Load {
	
	private BandwidthUnit unit;
	
	public DiskIOLoad(float current, float total, BandwidthUnit unit) {
		super(current, total);
		this.unit = unit;
	}

	public BandwidthUnit getUnit() {
		return unit;
	}

	public void setUnit(BandwidthUnit unit) {
		this.unit = unit;
	}
	
}
