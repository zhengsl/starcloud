package com.star.cloud.domain.resource;

import com.star.cloud.domain.common.unit.BandwidthUnit;

public class DiskIOResource extends Resource {
	
	private int concurrent;
	
	private int throughput;
	
	private BandwidthUnit throughputUnit;

	public int getConcurrent() {
		return concurrent;
	}

	public void setConcurrent(int concurrent) {
		this.concurrent = concurrent;
	}

	public int getThroughput() {
		return throughput;
	}

	public void setThroughput(int throughput) {
		this.throughput = throughput;
	}

	public BandwidthUnit getThroughputUnit() {
		return throughputUnit;
	}

	public void setThroughputUnit(BandwidthUnit throughputUnit) {
		this.throughputUnit = throughputUnit;
	}
	
}
