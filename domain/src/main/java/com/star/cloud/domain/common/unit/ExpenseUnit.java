package com.star.cloud.domain.common.unit;

import java.util.Arrays;
import java.util.List;

public class ExpenseUnit extends Unit {
	
	private static final List<String> nameSet = Arrays.asList("USDollar", "RMB");
	
	private static final List<Float> conversion = Arrays.asList(6.2f);

	public ExpenseUnit(String name) {
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
