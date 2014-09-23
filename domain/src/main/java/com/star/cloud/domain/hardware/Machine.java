package com.star.cloud.domain.hardware;

import java.util.ArrayList;
import java.util.List;

import com.star.cloud.domain.common.IPv4;

public class Machine {
	
	private Group group;
	
	private String id;
	private String name;
	
	private String model;
	
	private String hostName;
	private IPv4 adminIP;
	
	private Card master;
	private List<Card> workers = new ArrayList<Card>();
	
//	public static Machine fromMachineInfo(MachineInfo machineInfo) {
//		Cluster c = new Cluster();
//		c.setId(machineInfo.getClusterId());
//		c.setName(machineInfo.getClusterName());
//		
//		Group g = new Group();
//		g.setId(machineInfo.getGroupId());
//		g.setName(machineInfo.getGroupName());
//		g.setCluster(c);
//		
//		Machine m = new Machine();
//		m.setGroup(g);
//		m.setName(machineInfo.getName());
//		m.setModel(machineInfo.getModel());
//		
//		List<CardInfo> ciList = machineInfo.getCards();
//		Card cm = Card.fromCardInfo(ciList.get(0));
//		m.setMaster(cm);
//		m.setHostName(cm.getName());
//		m.setAdminIP(cm.getIp());
//		
//		int len = ciList.size();
//		for (int i = 1; i < len; ++i) {
//			Card cw = Card.fromCardInfo(ciList.get(i));
//			m.getWorkers().add(cw);
//		}
//		
//		return m;
//	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
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
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public IPv4 getAdminIP() {
		return adminIP;
	}

	public void setAdminIP(IPv4 adminIP) {
		this.adminIP = adminIP;
	}

	public Card getMaster() {
		return master;
	}

	public void setMaster(Card master) {
		this.master = master;
	}

	public List<Card> getWorkers() {
		return workers;
	}

	public void setWorkers(List<Card> workers) {
		this.workers = workers;
	}

}
