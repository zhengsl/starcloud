package com.star.cloud.monitor.icinga;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class IcingaPerfData {
	
	private final Map<String, String> dataMap = new LinkedHashMap<String, String>();
	
	private IcingaPerfData() {}
	
	public static IcingaPerfData fromString(String perfDataStr) {
		IcingaPerfData instance = new IcingaPerfData();
		String[] entries = perfDataStr.split("[ ]");
		for (String entry : entries) {
			String[] items = entry.split("[=]");
			if (items.length >= 2) {
				instance.addData(items[0], items[1]);
			} else {
				instance.addData(items[0], "0");
			}
		}
		return instance;
	}
	
	public void addData(String key, String value) {
		this.dataMap.put(key, value);
	}
	
	public Set<String> getKeys() {
		return dataMap.keySet();
	}
	
	public String getValue(String key) {
		return dataMap.get(key);
	}
	
	public boolean keyExists(String key) {
		return dataMap.containsKey(key);
	}
	
	public Map<String, String> getMap() {
		return dataMap;
	}

}
