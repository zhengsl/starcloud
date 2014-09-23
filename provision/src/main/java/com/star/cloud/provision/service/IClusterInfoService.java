package com.star.cloud.provision.service;

import java.util.List;

import com.star.cloud.provision.model.MachineInfo;

public interface IClusterInfoService {
	
	List<MachineInfo> getMachineInfo();
	
	String getMachineInfoJson();

}
