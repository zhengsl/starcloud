package com.star.cloud.domain.perf;

public class CPULoad extends Load {
	
	private final float a1mLoad;
	
	private final float a5mLoad;
	
	private final float a15mLoad;
	
	public CPULoad(float current, float total, float a1mLoad, float a5mLoad, float a15mLoad) {
		super(current, total);
		this.a1mLoad = a1mLoad;
		this.a5mLoad = a5mLoad;
		this.a15mLoad = a15mLoad;
	}

	public float getA1mLoad() {
		return a1mLoad;
	}

	public float getA5mLoad() {
		return a5mLoad;
	}

	public float getA15mLoad() {
		return a15mLoad;
	}
	
}
