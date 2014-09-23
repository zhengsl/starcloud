package com.star.cloud.domain.resource;

import com.star.cloud.domain.common.unit.TimeUnit;

public class ComputingResource extends Resource {
	
	private int coreNum;
	
	private int duration;
	
	private TimeUnit durationUnit;

	public int getCoreNum() {
		return coreNum;
	}

	public void setCoreNum(int coreNum) {
		this.coreNum = coreNum;
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
