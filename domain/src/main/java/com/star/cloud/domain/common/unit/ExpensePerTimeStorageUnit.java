package com.star.cloud.domain.common.unit;

public class ExpensePerTimeStorageUnit extends CompositeUnit {
	
	private final ExpenseUnit expenseUnit;
	
	private final TimeUnit timeUnit;
	
	private final StorageUnit storageUnit;
	
	public ExpensePerTimeStorageUnit(ExpenseUnit expenseUnit, TimeUnit timeUnit, StorageUnit storageUnit) {
		this.expenseUnit = expenseUnit;
		this.timeUnit = timeUnit;
		this.storageUnit = storageUnit;
	}
	
	public ExpenseUnit getExpenseUnit() {
		return expenseUnit;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public StorageUnit getStorageUnit() {
		return storageUnit;
	}

	@Override
	public boolean isCompatibleWith(CompositeUnit cunit) {
		if (cunit == null) return false;
		if (!(cunit instanceof CompositeUnit)) return false;
		
		ExpensePerTimeStorageUnit epts = (ExpensePerTimeStorageUnit) cunit;
		return this.expenseUnit.isCompatibleWith(epts.getExpenseUnit())
				&& this.timeUnit.isCompatibleWith(epts.getTimeUnit())
				&& this.storageUnit.isCompatibleWith(epts.getStorageUnit());
	}

	@Override
	public boolean isEqualTo(CompositeUnit cunit) {
		if (cunit == null) return false;
		if (!(cunit instanceof CompositeUnit)) return false;
		
		ExpensePerTimeStorageUnit epts = (ExpensePerTimeStorageUnit) cunit;
		return this.expenseUnit.isEqualTo(epts.getExpenseUnit())
				&& this.timeUnit.isEqualTo(epts.getTimeUnit())
				&& this.storageUnit.isEqualTo(epts.getStorageUnit());
	}

}
