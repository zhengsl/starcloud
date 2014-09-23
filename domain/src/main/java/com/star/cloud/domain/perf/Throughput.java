package com.star.cloud.domain.perf;

import com.star.cloud.domain.common.unit.TrafficUnit;
import com.star.cloud.utils.Factor;
import com.star.cloud.utils.UnitUtils;

public class Throughput extends Performance {
	
	private final float value;
	
	private final TrafficUnit unit;
	
	public Throughput(float value, TrafficUnit unit) {
		this.value = value;
		this.unit = unit;
	}

	public float getValue() {
		return value;
	}

	public TrafficUnit getUnit() {
		return unit;
	}

	@Override
	public boolean isSatisfied(Performance perf) {
		assert(perf != null && perf instanceof Throughput);
		
		Throughput t = (Throughput) perf;
		Factor f = UnitUtils.generateConversionFactor(this.unit, t.getUnit());
		return f.num() * this.value <= t.getValue();
	}
	
}
