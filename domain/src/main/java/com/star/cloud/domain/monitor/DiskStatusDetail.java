package com.star.cloud.domain.monitor;

public class DiskStatusDetail {
	
	private String name;
	
	private float size;
	
	private float inuse;
	
	private String mount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public float getInuse() {
		return inuse;
	}

	public void setInuse(float inuse) {
		this.inuse = inuse;
	}

	public String getMount() {
		return mount;
	}

	public void setMount(String mount) {
		this.mount = mount;
	}
	
}
