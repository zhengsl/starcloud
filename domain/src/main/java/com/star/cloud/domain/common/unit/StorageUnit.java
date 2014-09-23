package com.star.cloud.domain.common.unit;

import java.util.Arrays;
import java.util.List;

public class StorageUnit extends Unit {
	
	private static final List<String> nameSet = Arrays.asList("KB", "MB", "GB", "TB");
	
	private static final List<Float> conversion = Arrays.asList(1024f, 1024f, 1024f);

	public StorageUnit(String name) {
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
