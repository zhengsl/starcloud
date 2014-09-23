package com.star.cloud.domain.cost;

import com.star.cloud.domain.common.unit.CompositeUnit;
import com.star.cloud.domain.common.unit.ExpensePerTimeUnit;

public class OneCoreCPUModel extends CostModel {
	
	private final float price;

	public OneCoreCPUModel(CompositeUnit cunit, float price) {
		super(cunit);
		assert(cunit instanceof ExpensePerTimeUnit);
		this.price = price;
	}

	public float getPrice() {
		return price;
	}
	
}
