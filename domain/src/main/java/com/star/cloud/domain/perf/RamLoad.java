package com.star.cloud.domain.perf;

import com.star.cloud.domain.common.unit.StorageUnit;

public class RamLoad extends Load {
	
	private StorageUnit unit;

	public RamLoad(float current, float total, StorageUnit unit) {
		super(current, total);
		this.unit = unit;
	}

	public StorageUnit getUnit() {
		return unit;
	}

	public void setUnit(StorageUnit unit) {
		this.unit = unit;
	}
	
}
