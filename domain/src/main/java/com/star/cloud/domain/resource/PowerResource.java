package com.star.cloud.domain.resource;

import com.star.cloud.domain.common.unit.TimeUnit;

public class PowerResource extends Resource {
	
	private int load;
	
	private int duration;
	
	private TimeUnit durationUnit;

	public int getLoad() {
		return load;
	}

	public void setLoad(int load) {
		this.load = load;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public TimeUnit getDurationUnit() {
		return durationUnit;
	}

	public void setDurationUnit(TimeUnit durationUnit) {
		this.durationUnit = durationUnit;
	}
	
}
