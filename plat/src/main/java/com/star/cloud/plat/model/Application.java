package com.star.cloud.plat.model;

import java.util.ArrayList;
import java.util.List;

public class Application {
	
	private String id;
	private String name;
	private String description;
	
	private AppType type;
	
	private Command command;
	private List<Application> dependency = new ArrayList<Application>();
	
	private List<Machine> machines = new ArrayList<Machine>();
	
	public enum AppType {
		AS, JOB, DATABASE
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public AppType getType() {
		return type;
	}

	public void setType(AppType type) {
		this.type = type;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public List<Application> getDependency() {
		return dependency;
	}

	public void setDependency(List<Application> dependency) {
		this.dependency = dependency;
	}

	public List<Machine> getMachines() {
		return machines;
	}

	public void setMachines(List<Machine> machines) {
		this.machines = machines;
	}
	
}
