package com.star.cloud.domain.app;

import java.util.ArrayList;
import java.util.List;

public class GroupDeploymentRule extends DeploymentRule {
	
	private List<String> groupNames = new ArrayList<String>();

	public List<String> getGroupNames() {
		return groupNames;
	}

	public void setGroupNames(List<String> groupNames) {
		this.groupNames = groupNames;
	}
	
}
