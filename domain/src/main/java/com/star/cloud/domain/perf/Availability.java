package com.star.cloud.domain.perf;

public class Availability extends Performance {
	
	private final float value;
	
	private final int base;
	
	public Availability(float value, int base) {
		this.value = value;
		this.base = base;
	}
	
	public int numOfNine() {
		if (this.value * 10 <= this.base) {
			return 0;
		}
		String valueStr = String.valueOf(value).replace(".", "");
		int i = 0;
		int len = valueStr.length();
		while (i < len) {
			if (valueStr.charAt(i) != '9') {
				break;
			} else {
				i = i + 1;
			}
		}
		return i;
	}

	@Override
	public boolean isSatisfied(Performance perf) {
		assert(perf != null && perf instanceof Availability);
		Availability a = (Availability) perf;
		if (this.base == a.getBase())
			return this.value <= a.getValue();
		else
			return (this.value / this.base) <= (a.getValue() / a.getBase());
	}

	public float getValue() {
		return value;
	}

	public int getBase() {
		return base;
	}
	
}
