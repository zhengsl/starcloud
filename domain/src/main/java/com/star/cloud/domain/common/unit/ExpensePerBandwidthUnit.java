package com.star.cloud.domain.common.unit;

public class ExpensePerBandwidthUnit extends CompositeUnit {
	
	private final ExpenseUnit expenseUnit;
	
	private final BandwidthUnit bandwidthUnit;
	
	public ExpensePerBandwidthUnit(ExpenseUnit expenseUnit, BandwidthUnit bandwidthUnit) {
		this.expenseUnit = expenseUnit;
		this.bandwidthUnit = bandwidthUnit;
	}
	
	public ExpenseUnit getExpenseUnit() {
		return expenseUnit;
	}

	public BandwidthUnit getBandwidthUnit() {
		return bandwidthUnit;
	}

	@Override
	public boolean isCompatibleWith(CompositeUnit cunit) {
		if (cunit == null) return false;
		if (!(cunit instanceof ExpensePerBandwidthUnit)) return false;
		
		ExpensePerBandwidthUnit epb = (ExpensePerBandwidthUnit) cunit;
		return this.expenseUnit.isCompatibleWith(epb.getExpenseUnit())
				&& this.bandwidthUnit.isCompatibleWith(epb.getBandwidthUnit());
	}

	@Override
	public boolean isEqualTo(CompositeUnit cunit) {
		if (cunit == null) return false;
		if (!(cunit instanceof ExpensePerBandwidthUnit)) return false;
		
		ExpensePerBandwidthUnit epb = (ExpensePerBandwidthUnit) cunit;
		return this.expenseUnit.isEqualTo(epb.getExpenseUnit())
				&& this.bandwidthUnit.isEqualTo(epb.getBandwidthUnit());
	}

}
