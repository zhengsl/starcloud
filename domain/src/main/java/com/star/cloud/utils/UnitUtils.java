package com.star.cloud.utils;

import java.util.List;

import com.star.cloud.domain.common.unit.Unit;

public class UnitUtils {
	
	public static Factor generateConversionFactor(Unit from, Unit to) {
		assert(from.isCompatibleWith(to));
		List<String> nameSet = from.getNameSet();
		int fi = nameSet.indexOf(from.getName());
		int ti = nameSet.indexOf(to.getName());
		float base = 1f;
		int power = 1;
		if (fi > ti) {
			int tmp = fi;
			fi = ti;
			ti = tmp;
			power = -1;
		}
		List<Float> conversion = from.getConversion();
		for (int i = fi; i < ti; ++i) {
			base = base * conversion.get(i);
		}
		return new Factor(base, power);
	}

}
