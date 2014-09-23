package com.star.cloud.domain.qos;

import com.star.cloud.domain.perf.Availability;
import com.star.cloud.domain.perf.ContentQuality;
import com.star.cloud.domain.perf.NetworkQuality;
import com.star.cloud.domain.perf.Responsibility;
import com.star.cloud.domain.perf.Throughput;

public class PerfPackage {
	
	private final Availability availability;
	
	private final Responsibility responsibility;
	
	private final ContentQuality contentQuality;
	
	private final Throughput throughput;
	
	private final NetworkQuality networkQuality;

	public PerfPackage(Availability availability,
			Responsibility responsibility, ContentQuality contentQuality,
			Throughput throughput, NetworkQuality networkQuality) {
		super();
		this.availability = availability;
		this.responsibility = responsibility;
		this.contentQuality = contentQuality;
		this.throughput = throughput;
		this.networkQuality = networkQuality;
	}

	public boolean isSatisfied(PerfPackage perfPackage) {
		return this.availability.isSatisfied(perfPackage.getAvailability())
				&& this.responsibility.isSatisfied(perfPackage.getResponsibility())
				&& this.contentQuality.isSatisfied(perfPackage.getContentQuality())
				&& this.throughput.isSatisfied(perfPackage.getThroughput())
				&& this.networkQuality.isSatisfied(perfPackage.getNetworkQuality());
	}
	
	public Availability getAvailability() {
		return availability;
	}

	public Responsibility getResponsibility() {
		return responsibility;
	}

	public ContentQuality getContentQuality() {
		return contentQuality;
	}

	public Throughput getThroughput() {
		return throughput;
	}

	public NetworkQuality getNetworkQuality() {
		return networkQuality;
	}
	
}
