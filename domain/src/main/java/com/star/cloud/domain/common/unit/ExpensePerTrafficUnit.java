package com.star.cloud.domain.common.unit;

public class ExpensePerTrafficUnit extends CompositeUnit {
	
	private final ExpenseUnit expenseUnit;
	
	private final TrafficUnit trafficUnit;
	
	public ExpensePerTrafficUnit(ExpenseUnit expenseUnit, TrafficUnit trafficUnit) {
		this.expenseUnit = expenseUnit;
		this.trafficUnit = trafficUnit;
	}
	
	public ExpenseUnit getExpenseUnit() {
		return expenseUnit;
	}

	public TrafficUnit getTrafficUnit() {
		return trafficUnit;
	}

	@Override
	public boolean isCompatibleWith(CompositeUnit cunit) {
		if (cunit == null) return false;
		if (!(cunit instanceof ExpensePerTrafficUnit)) return false;
		ExpensePerTrafficUnit ept = (ExpensePerTrafficUnit) cunit;
		return this.expenseUnit.isCompatibleWith(ept.getExpenseUnit())
				&& this.trafficUnit.isCompatibleWith(ept.getTrafficUnit());
	}

	@Override
	public boolean isEqualTo(CompositeUnit cunit) {
		if (cunit == null) return false;
		if (!(cunit instanceof ExpensePerTrafficUnit)) return false;
		ExpensePerTrafficUnit ept = (ExpensePerTrafficUnit) cunit;
		return this.expenseUnit.isEqualTo(ept.getExpenseUnit())
				&& this.trafficUnit.isEqualTo(ept.getTrafficUnit());
	}

}
