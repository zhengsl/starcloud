package com.star.cloud.domain.common.unit;

import java.util.Arrays;
import java.util.List;

public class TimeUnit extends Unit {
	
	private static final List<String> nameSet = Arrays.asList("millisecond", "second", "minute", "hour", "day", "month");
	
	private static final List<Float> conversion = Arrays.asList(1000f, 60f, 60f, 24f, 30f);

	public TimeUnit(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<String> getNameSet() {
		return nameSet;
	}

	@Override
	public List<Float> getConversion() {
		return conversion;
	}

}
