package com.star.cloud.domain.resource;

import java.util.HashMap;
import java.util.Map;

public class ResourceGroupView {
	
	private String id;
	
	private String name;
	
	private Map<String, Resource> resources = new HashMap<String, Resource>();
	
	public void addResource(Resource res) {
		resources.put(res.getId(), res);
	}
	
	public void removeResource(Resource res) {
		resources.remove(res.getId());
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

	public Map<String, Resource> getResources() {
		return resources;
	}

	public void setResources(Map<String, Resource> resources) {
		this.resources = resources;
	}
	
}
