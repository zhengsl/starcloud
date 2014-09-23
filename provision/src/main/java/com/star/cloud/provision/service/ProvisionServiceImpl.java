package com.star.cloud.provision.service;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.cloud.provision.model.StarCloudProvisionException;


public class ProvisionServiceImpl implements IProvisionService {
	
	private static final String COMMAND_START_HOST = "qfhost.start";
	private static final String COMMAND_STOP_HOST = "qfhost.stop";
	private static final String COMMAND_RESTART_HOST = "qfhost.restart";
	private static final String COMMAND_START_SERVICE = "qfservice.start";
	private static final String COMMAND_STOP_SERVICE = "qfservice.stop";
	private static final String COMMAND_RESTART_SERVICE = "qfservice.restart";
	private static final String ALL_HOSTNAME = "*";
	private static final String MASTER_TARGET = "qf_role:master";
	private static final String EXPR_FORM = "grain";
	
	private final SaltRunHelper saltRun;
	
	static class ProvisionRunCallback implements ISaltCallback {
		
		private final String target;
		private final String func;
		
		private final ObjectMapper objMapper = new ObjectMapper();
		
		public ProvisionRunCallback(String target, String func) {
			this.target = target;
			this.func = func;
		}

		@Override
		public Object afterResponse(String jsonResponse) {
			Map<String, Boolean> result = null;
			try {
				result = objMapper.readValue(jsonResponse, new TypeReference<Map<String, Boolean>>() {});
				for (String key : result.keySet()) {
					if (!result.get(key))
						throw new StarCloudProvisionException("Error happened while execute " + func + " on " + target);
				}
			} catch (Exception e) {
				throw new StarCloudProvisionException(e);
			}
			return result;
		}
		
	}
	
	public ProvisionServiceImpl(String saltStackIp, String saltStackPort, 
			String saltUserName, String saltPassword) {
		saltRun = new SaltRunHelper(saltStackIp, saltStackPort, saltUserName, saltPassword, new ProvisionRunCallback("*", "*"));
	}
	
	@Override
	public void startHost(String hostName) {
		saltRun.runFunc(MASTER_TARGET, COMMAND_START_HOST, hostName, EXPR_FORM, new ProvisionRunCallback(hostName, COMMAND_START_HOST));
	}

	@Override
	public void stopHost(String hostName) {
		saltRun.runFunc(MASTER_TARGET, COMMAND_STOP_HOST, hostName, EXPR_FORM, new ProvisionRunCallback(hostName, COMMAND_STOP_HOST));
	}

	@Override
	public void restartHost(String hostName) {
		saltRun.runFunc(MASTER_TARGET, COMMAND_RESTART_HOST, hostName, EXPR_FORM, new ProvisionRunCallback(hostName, COMMAND_RESTART_HOST));
	}
	
	@Override
	public void startService(String hostName, String serviceName) {
		saltRun.runFunc(hostName, COMMAND_START_SERVICE, serviceName, EXPR_FORM,
				new ProvisionRunCallback(hostName, COMMAND_START_SERVICE + "." + serviceName));
	}

	@Override
	public void stopService(String hostName, String serviceName) {
		saltRun.runFunc(hostName, COMMAND_STOP_SERVICE , serviceName, EXPR_FORM,
				new ProvisionRunCallback(hostName, COMMAND_STOP_SERVICE + "." + serviceName));
	}
	
	@Override
	public void restartService(String hostName, String serviceName) {
		saltRun.runFunc(hostName, COMMAND_RESTART_SERVICE , serviceName, EXPR_FORM,
				new ProvisionRunCallback(hostName, COMMAND_RESTART_SERVICE + "." + serviceName));
	}

	@Override
	public void startServices(String serviceName) {
		startService(ALL_HOSTNAME, serviceName);
	}

	@Override
	public void stopServices(String serviceName) {
		stopService(ALL_HOSTNAME, serviceName);
	}
	
	@Override
	public void restartServices(String serviceName) {
		restartService(ALL_HOSTNAME, serviceName);
	}

}
