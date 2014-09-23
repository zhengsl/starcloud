package com.star.cloud.plat.model;

import java.util.ArrayList;
import java.util.List;

public class Group {
	
	private String id;
	private String name;
	
	private Cluster cluster;
	
	private List<Machine> machines = new ArrayList<Machine>();
	
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

	public Cluster getCluster() {
		return cluster;
	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public List<Machine> getMachines() {
		return machines;
	}

	public void setMachines(List<Machine> machines) {
		this.machines = machines;
	}
	
}
