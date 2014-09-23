package com.star.cloud.plat.model;

public class DeploymentStatus {
	
	private Deployment deployment;
	
	private long startTime;
	private long duration;
	
	private float completeness;
	private Priority priority;
	
	public Deployment getDeployment() {
		return deployment;
	}
	
	public void setDeployment(Deployment deployment) {
		this.deployment = deployment;
	}
	
	public long getStartTime() {
		return startTime;
	}
	
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	public long getDuration() {
		return duration;
	}
	
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	public float getCompleteness() {
		return completeness;
	}
	
	public void setCompleteness(float completeness) {
		this.completeness = completeness;
	}
	
	public Priority getPriority() {
		return priority;
	}
	
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
}
