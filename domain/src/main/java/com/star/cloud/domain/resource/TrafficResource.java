package com.star.cloud.domain.resource;

import com.star.cloud.domain.common.unit.TrafficUnit;

public class TrafficResource extends Resource {
	
	private int traffic;
	
	private TrafficUnit trafficUnit;

	public int getTraffic() {
		return traffic;
	}

	public void setTraffic(int traffic) {
		this.traffic = traffic;
	}

	public TrafficUnit getTrafficUnit() {
		return trafficUnit;
	}

	public void setTrafficUnit(TrafficUnit trafficUnit) {
		this.trafficUnit = trafficUnit;
	}
	
}
