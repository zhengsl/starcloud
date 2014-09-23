package com.star.cloud.provision.service;

public interface IProvisionService {
	
	void startHost(String hostName);
	
	void stopHost(String hostName);
	
	void startService(String hostName, String serviceName);
	
	void stopService(String hostName, String serviceName);
	
	void startServices(String serviceName);
	
	void stopServices(String serviceName);

	void restartServices(String serviceName);

	void restartService(String hostName, String serviceName);

	void restartHost(String hostName);

}
