package com.star.cloud.plat.model;

import java.util.Map;

public class MachineStatus {
	
	private int temperature;
	private int humidity;
	private int power;
	
	private float todayCost;
	private float totalCost;
	private float unitCost;
	
	private int realtimeCost;
	private int schedulingEvent;
	private int savedCost;
	
	private int totalApp;
	private int independentUser;
	private int servedUser;
	
	private long timestamp;
	
	public static MachineStatus fromMap(Map<String, String> map) {
		MachineStatus status = new MachineStatus();
		status.setTemperature(Integer.parseInt(map.get("temperature")));
		status.setHumidity(Integer.parseInt(map.get("humidity")));
		status.setPower(Integer.parseInt(map.get("power")));
		status.setTodayCost(Float.parseFloat(map.get("todayCost")));
		status.setTotalCost(Float.parseFloat(map.get("totalCost")));
		status.setUnitCost(Float.parseFloat(map.get("unitCost")));
		status.setRealtimeCost(Integer.parseInt(map.get("realtimeCost")));
		status.setSchedulingEvent(Integer.parseInt(map.get("schedulingEvent")));
		status.setTotalApp(Integer.parseInt(map.get("totalApp")));
		status.setIndependentUser(Integer.parseInt(map.get("independentUser")));
		status.setServedUser(Integer.parseInt(map.get("servedUser")));
		status.setTimestamp(Long.parseLong(map.get("timestamp")));
		return status;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public float getTodayCost() {
		return todayCost;
	}

	public void setTodayCost(float todayCost) {
		this.todayCost = todayCost;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	public float getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(float unitCost) {
		this.unitCost = unitCost;
	}

	public int getRealtimeCost() {
		return realtimeCost;
	}

	public void setRealtimeCost(int realtimeCost) {
		this.realtimeCost = realtimeCost;
	}

	public int getSchedulingEvent() {
		return schedulingEvent;
	}

	public void setSchedulingEvent(int schedulingEvent) {
		this.schedulingEvent = schedulingEvent;
	}

	public int getSavedCost() {
		return savedCost;
	}

	public void setSavedCost(int savedCost) {
		this.savedCost = savedCost;
	}

	public int getTotalApp() {
		return totalApp;
	}

	public void setTotalApp(int totalApp) {
		this.totalApp = totalApp;
	}

	public int getIndependentUser() {
		return independentUser;
	}

	public void setIndependentUser(int independentUser) {
		this.independentUser = independentUser;
	}

	public int getServedUser() {
		return servedUser;
	}

	public void setServedUser(int servedUser) {
		this.servedUser = servedUser;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
}
