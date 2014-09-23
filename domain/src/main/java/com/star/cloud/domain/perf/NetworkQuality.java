package com.star.cloud.domain.perf;

import java.util.Date;

public class NetworkQuality extends Performance {
	
	private final int delay;
	
	private final int totalPackets;
	
	private final int lostPackets;
	
	private final Date fromTime;
	
	private final Date toTime;
	
	public NetworkQuality(int delay, int totalPackets, int lostPackets,
			Date fromTime, Date toTime) {
		super();
		this.delay = delay;
		this.totalPackets = totalPackets;
		this.lostPackets = lostPackets;
		this.fromTime = fromTime;
		this.toTime = toTime;
	}

	public int getDelay() {
		return delay;
	}

	public int getTotalPackets() {
		return totalPackets;
	}

	public int getLostPackets() {
		return lostPackets;
	}

	public Date getFromTime() {
		return fromTime;
	}

	public Date getToTime() {
		return toTime;
	}

	public float packetLostRate() {
		return this.lostPackets / this.totalPackets;
	}
	
	@Override
	public boolean isSatisfied(Performance perf) {
		assert(perf != null && perf instanceof NetworkQuality);
		
		NetworkQuality n = (NetworkQuality) perf;
		return this.delay >= n.getDelay() && this.packetLostRate() >= n.packetLostRate();
	}

}
