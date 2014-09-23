package com.star.cloud.plat.model;

public class NICStatusDetail {
	
	private String name;
	
	private String ip;
	
	private String mac;
	
	private float in;
	
	private float out;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public float getIn() {
		return in;
	}

	public void setIn(float in) {
		this.in = in;
	}

	public float getOut() {
		return out;
	}

	public void setOut(float out) {
		this.out = out;
	}
	
}
