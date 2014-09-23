package com.star.cloud.domain.common.unit;

import java.util.List;

public abstract class Unit {
	
	public final String name;
	
	public Unit(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract List<String> getNameSet();
	
	public abstract List<Float> getConversion();

	public boolean isCompatibleWith(Unit other) {
		return other != null 
				&& this.getClass().equals(other.getClass())
				&& getNameSet().contains(other.getName());
	}
	
	public boolean isEqualTo(Unit other) {
		return other != null
				&& this.getClass().equals(other.getClass())
				&& this.getName().equals(other.getName());
	}

}
