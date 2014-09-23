package com.star.cloud.monitor.model;

public class StreamerHostStatus extends HostStatus {
	
	private final int streamCount;

	public StreamerHostStatus(String value, int streamCount) {
		super(value);
		this.streamCount = streamCount;
	}
	
	public int getStreamCount() {
		return streamCount;
	}

}
