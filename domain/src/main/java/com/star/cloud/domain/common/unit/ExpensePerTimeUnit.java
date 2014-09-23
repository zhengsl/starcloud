package com.star.cloud.domain.common.unit;

public class ExpensePerTimeUnit extends CompositeUnit {
	
	private final ExpenseUnit expenseUnit;
	
	private final TimeUnit timeUnit;
	
	public ExpensePerTimeUnit(ExpenseUnit expenseUnit, TimeUnit timeUnit) {
		this.expenseUnit = expenseUnit;
		this.timeUnit = timeUnit;
	}
	
	public ExpenseUnit getExpenseUnit() {
		return expenseUnit;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	@Override
	public boolean isCompatibleWith(CompositeUnit cunit) {
		if (cunit == null) return false;
		if (!(cunit instanceof ExpensePerTimeUnit)) return false;
		
		ExpensePerTimeUnit ept = (ExpensePerTimeUnit) cunit;
		return ept.getExpenseUnit().isCompatibleWith(ept.getExpenseUnit())
				&& ept.getTimeUnit().isCompatibleWith(ept.getTimeUnit());
	}

	@Override
	public boolean isEqualTo(CompositeUnit cunit) {
		if (cunit == null) return false;
		if (!(cunit instanceof ExpensePerTimeUnit)) return false;
		
		ExpensePerTimeUnit ept = (ExpensePerTimeUnit) cunit;
		return ept.getExpenseUnit().isEqualTo(ept.getExpenseUnit())
				&& ept.getTimeUnit().isEqualTo(ept.getTimeUnit());
	}

}
