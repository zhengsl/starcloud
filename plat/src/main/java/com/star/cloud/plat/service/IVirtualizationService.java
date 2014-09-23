package com.star.cloud.plat.service;

import java.util.List;

import com.star.cloud.plat.model.Host;
import com.star.cloud.plat.model.VMInstance;
import com.star.cloud.plat.model.VMProfile;

public interface IVirtualizationService {
	
	List<VMProfile> getAllVMProfiles();
	
	VMProfile getVMProfile(String id, String name);
	
	void addVMProfile(VMProfile vmProfile);
	
	void removeVMProfile(String id, String name);
	
	void modifyVMProfile(VMProfile vmProfile);
	
	List<VMInstance> getAllVMInstances();
	
	VMInstance getVMInstance(String id, String name);
	
	List<VMInstance> getVMInstanceUnderCard(String cardId, String cardName);
	
	VMInstance newVMInstance(String name, String imageId, String profileId, String networkId);
	
	void removeVMInstance(String id, String name);
	
	void migrateVMInstance(String id, Host host);

	VMInstance startVMInstance(String vminsId);
	
	VMInstance stopVMInstance(String vminsId);
	
	void updateVMInstanceStatus(String id, String status);

	List<VMInstance> getVMInstanceUnderNetwork(String networkId, String networkName);

}
