package com.star.cloud.plat.model;

import java.util.ArrayList;
import java.util.List;

public class Deployment {
	
	private String id;
	
	private long start;
	
	private List<Host> hosts = new ArrayList<Host>();
	private Application application;
	
	private Script launch;
	private Script stop;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public long getStart() {
		return start;
	}
	
	public void setStart(long start) {
		this.start = start;
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

	public Script getLaunch() {
		return launch;
	}

	public void setLaunch(Script launch) {
		this.launch = launch;
	}

	public Script getStop() {
		return stop;
	}

	public void setStop(Script stop) {
		this.stop = stop;
	}
	
}
