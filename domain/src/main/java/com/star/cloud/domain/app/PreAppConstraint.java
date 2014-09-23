package com.star.cloud.domain.app;

import java.util.ArrayList;
import java.util.List;

public class PreAppConstraint extends Constraint {
	
	private List<String> appNames = new ArrayList<String>();

	public List<String> getAppNames() {
		return appNames;
	}

	public void setAppNames(List<String> appNames) {
		this.appNames = appNames;
	}
	
}
