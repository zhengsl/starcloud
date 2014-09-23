package com.star.cloud.domain.cost;

import com.star.cloud.domain.common.unit.CompositeUnit;
import com.star.cloud.domain.common.unit.ExpensePerTimeSpaceUnit;

public class SpaceModel extends CostModel {
	
	private final float price;

	public SpaceModel(CompositeUnit cunit, float price) {
		super(cunit);
		assert(cunit instanceof ExpensePerTimeSpaceUnit);
		this.price = price;
	}

	public float getPrice() {
		return price;
	}
}
