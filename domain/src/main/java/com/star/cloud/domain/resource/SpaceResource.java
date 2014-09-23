package com.star.cloud.domain.resource;

import com.star.cloud.domain.common.unit.SpaceUnit;
import com.star.cloud.domain.common.unit.TimeUnit;

public class SpaceResource extends Resource {
	
	private int space;
	
	private SpaceUnit spaceUnit;
	
	private int duration;
	
	private TimeUnit durationUnit;

	public int getSpace() {
		return space;
	}

	public void setSpace(int space) {
		this.space = space;
	}

	public SpaceUnit getSpaceUnit() {
		return spaceUnit;
	}

	public void setSpaceUnit(SpaceUnit spaceUnit) {
		this.spaceUnit = spaceUnit;
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
