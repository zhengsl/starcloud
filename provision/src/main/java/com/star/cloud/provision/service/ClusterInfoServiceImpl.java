package com.star.cloud.provision.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.cloud.provision.model.MachineInfo;
import com.star.cloud.provision.model.StarCloudProvisionException;

public class ClusterInfoServiceImpl implements IClusterInfoService {

	private static final String COMMAND_GET_MACHINEINFO = "machineinfo.get";
	private static final String MASTER_TARGET = "qf_role:master";
	private static final String EXPR_FORM = "grain";
	
	private final SaltRunHelper saltRun;
	
	private final ObjectMapper objMapper = new ObjectMapper();
	
	static class ClusterInfoCallback implements ISaltCallback {
		
		private final ObjectMapper objMapper;
		
		public ClusterInfoCallback(ObjectMapper objMapper) {
			this.objMapper = objMapper;
		}

		@Override
		public Object afterResponse(String jsonResponse) {
			List<MachineInfo> result = new ArrayList<MachineInfo>();
			try {
				String json = jsonResponse.substring(11, jsonResponse.lastIndexOf("]")+1);
				List<Map<String, MachineInfo>> tmpResult = objMapper.readValue(json, new TypeReference<List<Map<String, MachineInfo>>>() {});
				for (Map<String, MachineInfo> m : tmpResult) {
					result.addAll(m.values());
				}
			} catch (Exception e) {
				throw new StarCloudProvisionException(e);
			}
			return result;
		}
		
	}
	
	public ClusterInfoServiceImpl(String saltStackIp, String saltStackPort, String saltUserName, String saltPassword) {
		saltRun = new SaltRunHelper(saltStackIp, saltStackPort, saltUserName, saltPassword, new ClusterInfoCallback(objMapper));
	}

	@Override
	public List<MachineInfo> getMachineInfo() {
		return (List<MachineInfo>) saltRun.runFunc(MASTER_TARGET, COMMAND_GET_MACHINEINFO, null, EXPR_FORM, new ClusterInfoCallback(objMapper));
	}

	@Override
	public String getMachineInfoJson() {
		List<MachineInfo> mList = getMachineInfo();
		try {
			return objMapper.writeValueAsString(mList);
		} catch (Exception e) {
			throw new StarCloudProvisionException(e);
		}
	}
	
}
