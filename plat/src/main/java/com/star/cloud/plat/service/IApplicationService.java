package com.star.cloud.plat.service;

import java.util.List;

import com.star.cloud.plat.model.Application;
import com.star.cloud.plat.model.Deployment;

public interface IApplicationService {
	
	List<Application> getAllApplications();
	
	Application getApplication(String id, String name);
	
	void registerApplication(Application application);
	
	void modifyApplication(Application application);
	
	void deleteApplication(String id, String name);
	
	void addDeployment(Deployment deployment);
	
	void deleteDeploymnet(String id);

}
