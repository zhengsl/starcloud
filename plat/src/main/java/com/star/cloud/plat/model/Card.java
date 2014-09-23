package com.star.cloud.plat.model;

import java.util.ArrayList;
import java.util.List;

import com.star.cloud.provision.model.CardInfo;

public class Card extends Host {
	
	private Machine machine;
	
	private String id;
	private String name;
	
	private String type;
	
	private OS os;
	private IPv4 ip;
	private MAC mac;
	
	private CPU cpu;
	private RAM ram;
	private NIC nic;
	private Disk disk;
	
	private List<VMInstance> vHostList = new ArrayList<VMInstance>();
	
	public static Card fromCardInfo(CardInfo ci) {
		Card c = new Card();
		c.setName(ci.getHostName());
		c.setType(ci.getType());
		
		OS os = new OS();
		os.setName(ci.getOsName());
		c.setOs(os);
		
		IPv4 ip = new IPv4();
		ip.setAddress(ci.getIpAddress());
		c.setIp(ip);
		
		MAC mac = new MAC();
		mac.setAddress(ci.getMacAddress());
		c.setMac(mac);
		
		CPU cpu = new CPU();
		cpu.setSocketNum(ci.getCpuSocketNum());
		cpu.setCoreNum(ci.getCpuCoreNum());
		cpu.setThreadNum(ci.getCpuThreadNum());
		cpu.setFrequency(ci.getCpuFrequency());
		cpu.setModel(ci.getCpuModel());
		c.setCpu(cpu);
		
		RAM ram = new RAM();
		ram.setAmount(ci.getRamAmount());
		ram.setUnit(StorageUnit.valueOf(ci.getRamUnit()));
		ram.setFrequency(ci.getRamFrequency());
		c.setRam(ram);
		
		NIC nic = new NIC();
		nic.setBandwidth(ci.getNicBandwidth());
		nic.setUnit(BandwidthUnit.valueOf(ci.getNicUnit()));
		c.setNic(nic);
		
		Disk disk = new Disk();
		disk.setAmount(ci.getDiskAmount());
		disk.setUnit(StorageUnit.valueOf(ci.getDiskUnit()));
		disk.setType(ci.getDiskType());
		c.setDisk(disk);
		
		return c;
	}
	
	public Machine getMachine() {
		return machine;
	}
	
	public void setMachine(Machine machine) {
		this.machine = machine;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public OS getOs() {
		return os;
	}

	public void setOs(OS os) {
		this.os = os;
	}

	public IPv4 getIp() {
		return ip;
	}

	public void setIp(IPv4 ip) {
		this.ip = ip;
	}

	public MAC getMac() {
		return mac;
	}

	public void setMac(MAC mac) {
		this.mac = mac;
	}

	public CPU getCpu() {
		return cpu;
	}

	public void setCpu(CPU cpu) {
		this.cpu = cpu;
	}

	public RAM getRam() {
		return ram;
	}

	public void setRam(RAM ram) {
		this.ram = ram;
	}

	public NIC getNic() {
		return nic;
	}

	public void setNic(NIC nic) {
		this.nic = nic;
	}

	public Disk getDisk() {
		return disk;
	}

	public void setDisk(Disk disk) {
		this.disk = disk;
	}

	public List<VMInstance> getvHostList() {
		return vHostList;
	}

	public void setvHostList(List<VMInstance> vHostList) {
		this.vHostList = vHostList;
	}
	
}
