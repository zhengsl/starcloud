package com.star.cloud.domain.resource;

import com.star.cloud.domain.common.unit.StorageUnit;
import com.star.cloud.domain.common.unit.TimeUnit;

public class StorageResource extends Resource {
	
	private int storage;
	
	private StorageUnit storageUnit;
	
	private int duration;
	
	private TimeUnit timeUnit;

	public int getStorage() {
		return storage;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

	public StorageUnit getStorageUnit() {
		return storageUnit;
	}

	public void setStorageUnit(StorageUnit storageUnit) {
		this.storageUnit = storageUnit;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}
	
}
