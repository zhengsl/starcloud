package com.star.cloud.domain.common.unit;

import java.util.Arrays;
import java.util.List;

public class TrafficUnit extends Unit {
	
	private static final List<String> nameSet = Arrays.asList("Kb", "Mb", "Gb");
	
	private static final List<Float> conversion = Arrays.asList(1000f, 1000f);

	public TrafficUnit(String name) {
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
