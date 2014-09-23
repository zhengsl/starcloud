package com.star.cloud.domain.hardware;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
	
	private String id;
	private String name;
	
	private List<Group> groupList = new ArrayList<Group>();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}
	
}
