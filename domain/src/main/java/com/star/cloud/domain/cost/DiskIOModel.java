package com.star.cloud.domain.cost;

import java.util.ArrayList;
import java.util.List;

import com.star.cloud.domain.common.unit.CompositeUnit;
import com.star.cloud.domain.common.unit.ExpensePerBandwidthUnit;

public class DiskIOModel extends CostModel {
	
	private final List<Integer> concurrentList = new ArrayList<Integer>();
	
	private final List<Float> throughputList = new ArrayList<Float>();
	
	private final List<Float> expenseList = new ArrayList<Float>();

	public DiskIOModel(CompositeUnit cunit) {
		super(cunit);
		assert(cunit instanceof ExpensePerBandwidthUnit);
	}

	public List<Integer> getConcurrentList() {
		return concurrentList;
	}

	public List<Float> getThroughputList() {
		return throughputList;
	}

	public List<Float> getExpenseList() {
		return expenseList;
	}
	
}
