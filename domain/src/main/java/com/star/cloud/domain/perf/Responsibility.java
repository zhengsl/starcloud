package com.star.cloud.domain.perf;

import com.star.cloud.domain.common.unit.TimeUnit;
import com.star.cloud.utils.Factor;
import com.star.cloud.utils.UnitUtils;

public class Responsibility extends Performance {
	
	private final int value;
	
	private final TimeUnit unit;
	
	public Responsibility(int value, TimeUnit unit) {
		this.value = value;
		this.unit = unit;
	}

	@Override
	public boolean isSatisfied(Performance perf) {
		assert(perf != null && perf instanceof Responsibility);
		
		Responsibility r = (Responsibility) perf;
		Factor f = UnitUtils.generateConversionFactor(this.unit, r.getUnit());
		return f.num() * this.value >= r.getValue();
	}

	public int getValue() {
		return value;
	}

	public TimeUnit getUnit() {
		return unit;
	}
	
}
