package com.star.cloud.domain.cost;

import com.star.cloud.domain.common.unit.CompositeUnit;

public abstract class CostModel {
	
	private final CompositeUnit cunit;
	
	public CostModel(CompositeUnit cunit) {
		this.cunit = cunit;
	}

	public CompositeUnit getCunit() {
		return cunit;
	}
	
}
