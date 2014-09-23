package com.star.cloud.monitor.daemon;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.star.cloud.monitor.service.IQFMachineService;
import com.star.cloud.monitor.service.MongoDefinition;
import com.star.cloud.monitor.service.MonitorServiceBus;


public class HostStatusTask extends ServiceDataCollectingTask {
	
	public static final String NAME = "hostStatusAggregation";
	
	private static final Map<String, String> fieldNameServiceKeyMap = new HashMap<String, String>();
	
	private static final Map<String, String> defaultValues = new HashMap<String, String>();
	
	static {
		fieldNameServiceKeyMap.put(MongoDefinition.QF.HOST.fCpuInUse, "base.cpuInUse");
		fieldNameServiceKeyMap.put(MongoDefinition.QF.HOST.fRamInUse, "base.ramInUse");
		fieldNameServiceKeyMap.put(MongoDefinition.QF.HOST.fDiskInUse, "base.diskInUse");
		fieldNameServiceKeyMap.put(MongoDefinition.QF.HOST.fNicInUse, "base.nicInUse");
		fieldNameServiceKeyMap.put(MongoDefinition.QF.HOST.fRunningApps, "base.runningapps");
		fieldNameServiceKeyMap.put(MongoDefinition.QF.HOST.fHealth, "servicestate");
		
		defaultValues.put(MongoDefinition.QF.HOST.fCpuInUse, "0.0");
		defaultValues.put(MongoDefinition.QF.HOST.fRamInUse, "0.0");
		defaultValues.put(MongoDefinition.QF.HOST.fDiskInUse, "0.0");
		defaultValues.put(MongoDefinition.QF.HOST.fNicInUse, "0.0");
		defaultValues.put(MongoDefinition.QF.HOST.fRunningApps, "0");
		defaultValues.put(MongoDefinition.QF.HOST.fHealth, "1");
	}

	@Override
	public String getTaskName() {
		return NAME;
	}

	@Override
	public MonitoringDataHandlerChain constructHandlerChain() {
		MonitoringDataHandlerChain chain = new MonitoringDataHandlerChain();
		chain.addHandler(new StructuredDataCollectingHandler(MongoDefinition.QF.HOST.fieldNames, fieldNameServiceKeyMap, defaultValues));
		chain.addHandler(new PersistHostStatusDataHandler());
		return chain;
	}
	
	public static class PersistHostStatusDataHandler implements MonitoringDataHandler {

		@Override
		public void handle(Map<String, Map<String, String>> serviceData, Map<String, Map<String, String>> middleData) {
			Set<String> hostNames = middleData.keySet();
			for (String hostName : hostNames) {
				IQFMachineService qfmService = MonitorServiceBus.getQFMachineService();
				qfmService.addHostStatusData(hostName, middleData.get(hostName));
			}
		}
		
	}
	
}
