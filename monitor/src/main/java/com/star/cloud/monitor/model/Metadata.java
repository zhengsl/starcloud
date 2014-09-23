package com.star.cloud.monitor.model;

public class Metadata {
	
	private final String key;
	private final String field;
	private final ValueType valueType;

	public enum ValueType {
		SINGLE,
		LIST,
		MAP
	}
	
	public Metadata(String key, String field, ValueType valueType) {
		this.key = key;
		this.field = field;
		this.valueType = valueType;
	}

	public String getKey() {
		return key;
	}

	public String getField() {
		return field;
	}

	public ValueType getValueType() {
		return valueType;
	}
	
}
