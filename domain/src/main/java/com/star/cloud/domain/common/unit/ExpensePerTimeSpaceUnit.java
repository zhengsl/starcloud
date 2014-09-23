package com.star.cloud.domain.common.unit;

public class ExpensePerTimeSpaceUnit extends CompositeUnit {
	
	private final ExpenseUnit expenseUnit;
	
	private final TimeUnit timeUnit;
	
	private final SpaceUnit spaceUnit;
	
	public ExpensePerTimeSpaceUnit(ExpenseUnit expenseUnit, TimeUnit timeUnit, SpaceUnit spaceUnit) {
		this.expenseUnit = expenseUnit;
		this.timeUnit = timeUnit;
		this.spaceUnit = spaceUnit;
	}
	
	public ExpenseUnit getExpenseUnit() {
		return expenseUnit;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public SpaceUnit getSpaceUnit() {
		return spaceUnit;
	}

	@Override
	public boolean isCompatibleWith(CompositeUnit cunit) {
		if (cunit == null) return false;
		if (!(cunit instanceof CompositeUnit)) return false;
		
		ExpensePerTimeSpaceUnit epts = (ExpensePerTimeSpaceUnit) cunit;
		return this.expenseUnit.isCompatibleWith(epts.getExpenseUnit())
				&& this.timeUnit.isCompatibleWith(epts.getTimeUnit())
				&& this.spaceUnit.isCompatibleWith(epts.getSpaceUnit());
	}

	@Override
	public boolean isEqualTo(CompositeUnit cunit) {
		if (cunit == null) return false;
		if (!(cunit instanceof CompositeUnit)) return false;
		
		ExpensePerTimeSpaceUnit epts = (ExpensePerTimeSpaceUnit) cunit;
		return this.expenseUnit.isEqualTo(epts.getExpenseUnit())
				&& this.timeUnit.isEqualTo(epts.getTimeUnit())
				&& this.spaceUnit.isEqualTo(epts.getSpaceUnit());
	}

}
