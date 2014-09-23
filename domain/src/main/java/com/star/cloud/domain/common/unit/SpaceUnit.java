package com.star.cloud.domain.common.unit;

import java.util.Arrays;
import java.util.List;

public class SpaceUnit extends Unit {
	
	private static final List<String> nameSet = Arrays.asList("RU");
	
	private static final List<Float> conversion = Arrays.asList();

	public SpaceUnit(String name) {
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