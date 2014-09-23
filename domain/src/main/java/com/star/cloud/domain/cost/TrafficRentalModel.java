package com.star.cloud.domain.cost;

import com.star.cloud.domain.common.unit.CompositeUnit;
import com.star.cloud.domain.common.unit.ExpensePerTrafficUnit;

public class TrafficRentalModel extends CostModel {
	
	private final float price;

	public TrafficRentalModel(CompositeUnit cunit, float price) {
		super(cunit);
		assert(cunit instanceof ExpensePerTrafficUnit);
		this.price = price;
	}

	public float getPrice() {
		return price;
	}
	
}
