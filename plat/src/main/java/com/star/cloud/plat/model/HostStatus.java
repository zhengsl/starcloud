package com.star.cloud.plat.model;

import java.util.Map;

public class HostStatus {
	
	private float cpuInUse;
	private float ramInUse;
	private float diskInUse;
	private float nicInUse;
	
	private int runningApps;
	
	private HealthStatus health;
	
	private long timestamp;
	
	public static HostStatus fromMap(Map<String, String> status) {
		HostStatus ss = new HostStatus();
		ss.setCpuInUse(Float.parseFloat(status.get("cpuInUse")));
		ss.setRamInUse(Float.parseFloat(status.get("ramInUse")));
		ss.setDiskInUse(Float.parseFloat(status.get("diskInUse")));
		ss.setNicInUse(Float.parseFloat(status.get("nicInUse")));
		ss.setRunningApps(Integer.parseInt(status.get("runningApps")));
		ss.setHealth((HealthStatus.values())[Integer.parseInt(status.get("health"))]);
		ss.setTimestamp(Long.parseLong(status.get("timestamp")));
		return ss;
	}

	public float getCpuInUse() {
		return cpuInUse;
	}

	public void setCpuInUse(float cpuInUse) {
		this.cpuInUse = cpuInUse;
	}

	public float getRamInUse() {
		return ramInUse;
	}

	public void setRamInUse(float ramInUse) {
		this.ramInUse = ramInUse;
	}

	public float getDiskInUse() {
		return diskInUse;
	}

	public void setDiskInUse(float diskInUse) {
		this.diskInUse = diskInUse;
	}

	public float getNicInUse() {
		return nicInUse;
	}

	public void setNicInUse(float nicInUse) {
		this.nicInUse = nicInUse;
	}

	public int getRunningApps() {
		return runningApps;
	}

	public void setRunningApps(int runningApps) {
		this.runningApps = runningApps;
	}

	public HealthStatus getHealth() {
		return health;
	}

	public void setHealth(HealthStatus health) {
		this.health = health;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
}
