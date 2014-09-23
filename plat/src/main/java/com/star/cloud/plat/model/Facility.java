package com.star.cloud.plat.model;

public class Facility {
	
	private String id;
	private String name;
	
	private Application application;
	
	private FacilityStatus status;
	
	private Host host;

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

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public FacilityStatus getStatus() {
		return status;
	}

	public void setStatus(FacilityStatus status) {
		this.status = status;
	}

	public Host getHost() {
		return host;
	}

	public void setHost(Host host) {
		this.host = host;
	}
	
}
