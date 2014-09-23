package com.star.cloud.domain.cost;

import java.util.ArrayList;
import java.util.List;

import com.star.cloud.domain.common.unit.CompositeUnit;
import com.star.cloud.domain.common.unit.ExpensePerTimeUnit;

public class PowerEfficiencyModel extends CostModel {
	
	private final List<Float> loadList = new ArrayList<Float>();
	
	private final List<Float> powerList = new ArrayList<Float>();
	
	private final List<Float> expenseList = new ArrayList<Float>();

	public PowerEfficiencyModel(CompositeUnit cunit) {
		super(cunit);
		assert(cunit instanceof ExpensePerTimeUnit);
	}

	public List<Float> getLoadList() {
		return loadList;
	}

	public List<Float> getPowerList() {
		return powerList;
	}

	public List<Float> getExpenseList() {
		return expenseList;
	}
	
}
