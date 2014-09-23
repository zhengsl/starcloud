package com.star.cloud.plat.dao;

import java.util.List;

import com.star.cloud.plat.model.VMInstance;
import com.star.cloud.plat.model.VMProfile;

public interface VMMapper {
	
	VMInstance getVMInstance(String id, String name);
	
	List<VMInstance> getVMInstanceUnderCard(String cardId, String cardName);
	
	List<VMInstance> getVMInstanceUnderNetwork(String networkId, String networkName);
	
	VMProfile getVMProfile(String id, String name);

	List<VMProfile> getAllVMProfiles();

	List<VMInstance> getAllVMInstances();

}
