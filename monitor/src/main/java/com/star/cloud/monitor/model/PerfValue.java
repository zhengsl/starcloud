package com.star.cloud.monitor.model;

public class PerfValue {
	
	private final String key;
	private final String value;
	private final ValueType type;
	
	public enum ValueType {
		STRING,
		NUMBER
	}
	
	public PerfValue(String key, String value, ValueType type) {
		this.key = key;
		this.value = value;
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public ValueType getType() {
		return type;
	}

}
