package com.star.cloud.utils;

public class Factor {
	
	private final float base;
	
	private final int power;
	
	public Factor(float base, int power) {
		this.base = base;
		this.power = power;
	}

	public float getBase() {
		return base;
	}

	public int getPower() {
		return power;
	}
	
	public float num() {
		if (power == 1) return base;
		if (power == -1) return 1f / base;
		return (float) Math.pow(base, power);
	}
	
}
