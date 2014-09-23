package com.star.cloud.domain.common.unit;

public abstract class CompositeUnit {
	
	public abstract boolean isCompatibleWith(CompositeUnit cunit);
	
	public abstract boolean isEqualTo(CompositeUnit cunit);

}
