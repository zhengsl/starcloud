package com.star.cloud.domain.resource;

import java.util.HashMap;
import java.util.Map;

public class ResourceClusterView {
	
	private String id;
	
	private String name;
	
	private Map<String, ResourceGroupView> groups = new HashMap<String, ResourceGroupView>();
	
	public void addResourceGroup(ResourceGroupView rgv) {
		groups.put(rgv.getId(), rgv);
	}
	
	public void removeResourceGroup(String gid) {
		groups.remove(gid);
	}

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

	public Map<String, ResourceGroupView> getGroups() {
		return groups;
	}

	public void setGroups(Map<String, ResourceGroupView> groups) {
		this.groups = groups;
	}
	
}
