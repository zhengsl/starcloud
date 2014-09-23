package com.star.cloud.domain.perf;

import com.star.cloud.domain.common.unit.StorageUnit;

public class DiskStorageLoad extends Load {
	
	private final StorageUnit unit;
	
	public DiskStorageLoad(float current, float total, StorageUnit unit) {
		super(current, total);
		this.unit = unit;
	}

	public StorageUnit getUnit() {
		return unit;
	}
	
}
