package com.star.cloud.domain.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Deployment {
	
	private String id;
	
	private Application application;
	
	private List<Host> hosts = new ArrayList<Host>();
	
	private List<DeploymentRule> rules = new ArrayList<DeploymentRule>();
	
	private Date timestamp;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public List<Host> getHosts() {
		return hosts;
	}

	public void setHosts(List<Host> hosts) {
		this.hosts = hosts;
	}

	public Application getApplication() {
		return application;
	}
	
	public void setApplication(Application application) {
		this.application = application;
	}

	public List<DeploymentRule> getRules() {
		return rules;
	}

	public void setRules(List<DeploymentRule> rules) {
		this.rules = rules;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
}
