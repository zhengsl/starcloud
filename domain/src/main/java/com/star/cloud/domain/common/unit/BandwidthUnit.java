package com.star.cloud.domain.common.unit;

import java.util.Arrays;
import java.util.List;

public class BandwidthUnit extends Unit {
	
	private static final List<String> nameSet = Arrays.asList("Kbps", "Mbps", "Gbps");
	
	private static final List<Float> conversion = Arrays.asList(1000f, 1000f);

	public BandwidthUnit(String name) {
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
