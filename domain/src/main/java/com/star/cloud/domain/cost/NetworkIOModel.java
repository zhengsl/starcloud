package com.star.cloud.domain.cost;

import com.star.cloud.domain.common.unit.CompositeUnit;
import com.star.cloud.domain.common.unit.ExpensePerBandwidthUnit;

public class NetworkIOModel extends CostModel {
	
	private final int resAmount;
	
	private final float price;

	public NetworkIOModel(CompositeUnit cunit, int resAmount, float price) {
		super(cunit);
		assert(cunit instanceof ExpensePerBandwidthUnit);
		this.resAmount = resAmount;
		this.price = price;
	}

	public int getResAmount() {
		return resAmount;
	}

	public float getPrice() {
		return price;
	}
	
}
