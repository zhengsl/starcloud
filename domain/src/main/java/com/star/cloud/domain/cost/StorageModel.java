package com.star.cloud.domain.cost;

import com.star.cloud.domain.common.unit.CompositeUnit;
import com.star.cloud.domain.common.unit.ExpensePerTimeStorageUnit;

public class StorageModel extends CostModel {
	
	private final float price;

	public StorageModel(CompositeUnit cunit, float price) {
		super(cunit);
		assert(cunit instanceof ExpensePerTimeStorageUnit);
		this.price = price;
	}

	public float getPrice() {
		return price;
	}
	
}
