package com.star.cloud.domain.cost;

import com.star.cloud.domain.common.unit.CompositeUnit;
import com.star.cloud.domain.common.unit.ExpensePerBandwidthUnit;

public class BandwidthRentalModel extends CostModel {
	
	private final float price;

	public BandwidthRentalModel(CompositeUnit cunit, float price) {
		super(cunit);
		assert(cunit instanceof ExpensePerBandwidthUnit);
		this.price = price;
	}

	public float getPrice() {
		return price;
	}
	
}
