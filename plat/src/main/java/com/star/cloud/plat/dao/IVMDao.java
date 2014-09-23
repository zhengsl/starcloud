package com.star.cloud.plat.dao;

import java.util.List;

import com.star.cloud.plat.model.VMInstance;
import com.star.cloud.plat.model.VMProfile;

public interface IVMDao {
	
	VMInstance queryVMInstance(String id, String name);
	
	List<VMInstance> queryAllVMInstances();
	
	List<VMInstance> queryVMInstanceUnderCard(String cardId, String cardName);
	
	void insertVMInstance(VMInstance vmInstance);
	
	void deleteVMInstance(String id, String name);
	
	void updateVMInstanceStatus(String id, String status);

	List<VMProfile> queryAllVMProfiles();

	VMProfile queryVMProfile(String id, String name);
	
	void insertVMProfile(VMProfile vmProfile);

	void deleteVMProfile(String id, String name);

	void updateVMProfile(VMProfile vmProfile);

	List<VMInstance> queryVMInstanceUnderNetwork(String networkId, String networkName);

}
