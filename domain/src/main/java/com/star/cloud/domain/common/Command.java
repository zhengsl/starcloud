package com.star.cloud.domain.common;

import java.util.LinkedHashMap;
import java.util.Map;

public class Command {
	
	private String mainApp;
	
	private Map<String, String> arguments = new LinkedHashMap<String, String>();

	public String getMainApp() {
		return mainApp;
	}

	public void setMainApp(String mainApp) {
		this.mainApp = mainApp;
	}

	public Map<String, String> getArguments() {
		return arguments;
	}

	public void setArguments(Map<String, String> arguments) {
		this.arguments = arguments;
	}
	
}
