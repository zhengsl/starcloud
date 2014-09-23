package com.star.cloud.virt.model;

import org.jclouds.openstack.nova.v2_0.domain.Flavor;

public class StarFlavor {
	
	private String id;	
	private String name;
	
	private int vcpu;
	private int ram;
	private int disk;
	
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
	
	public int getVcpu() {
		return vcpu;
	}
	
	public void setVcpu(int vcpu) {
		this.vcpu = vcpu;
	}
	
	public int getRam() {
		return ram;
	}
	
	public void setRam(int ram) {
		this.ram = ram;
	}
	
	public int getDisk() {
		return disk;
	}
	
	public void setDisk(int disk) {
		this.disk = disk;
	}

	public static StarFlavor fromFlavor(Flavor flavor) {
		StarFlavor sflavor = new StarFlavor();
		sflavor.setId(flavor.getId());
		sflavor.setName(flavor.getName());
		sflavor.setVcpu(flavor.getVcpus());
		sflavor.setRam(flavor.getRam());
		sflavor.setDisk(flavor.getDisk());
		return sflavor;
	}

}
