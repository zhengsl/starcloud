package com.star.cloud.domain.perf;

public abstract class Load {
	
	private final float current;
	
	private final float total;
	
	public Load(float current, float total) {
		super();
		this.current = current;
		this.total = total;
	}

	public float getCurrent() {
		return current;
	}

	public float getTotal() {
		return total;
	}
	
	public float percent() {
		return current / total;
	}

}
