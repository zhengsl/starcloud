package com.star.cloud.monitor.daemon;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.cloud.monitor.service.IQFMachineService;
import com.star.cloud.monitor.service.MongoDefinition;
import com.star.cloud.monitor.service.MonitorServiceBus;

public class MachineStatusTask extends ServiceDataCollectingTask {
	
	public static final String NAME = "machineStatusAggregation";
	
	private static final Map<String, String> fieldNameServiceKeyMap = new HashMap<String, String>();
	
	private static final Map<String, String> defaultValues = new HashMap<String, String>();
	
	static {
		fieldNameServiceKeyMap.put(MongoDefinition.QF.MACHINE.fTemperature, "env.temp");
		fieldNameServiceKeyMap.put(MongoDefinition.QF.MACHINE.fHumidity, "env.hum");
		fieldNameServiceKeyMap.put(MongoDefinition.QF.MACHINE.fPower, "env.power");
		fieldNameServiceKeyMap.put(MongoDefinition.QF.MACHINE.fTodayCost, "cost.today");
		fieldNameServiceKeyMap.put(MongoDefinition.QF.MACHINE.fTotalCost, "cost.total");
		fieldNameServiceKeyMap.put(MongoDefinition.QF.MACHINE.fUnitCost, "cost.unit");
		fieldNameServiceKeyMap.put(MongoDefinition.QF.MACHINE.fRealtimeCost, "cost.realtime");
		fieldNameServiceKeyMap.put(MongoDefinition.QF.MACHINE.fSavedCost, "cost.saved");
		fieldNameServiceKeyMap.put(MongoDefinition.QF.MACHINE.fSchedulingEvent, "plat.scheduling");
		fieldNameServiceKeyMap.put(MongoDefinition.QF.MACHINE.fTotalApp, "plat.totalapp");
		fieldNameServiceKeyMap.put(MongoDefinition.QF.MACHINE.fIndependentUser, "plat.independentuser");
		fieldNameServiceKeyMap.put(MongoDefinition.QF.MACHINE.fServedUser, "plat.serveduser");
		
		defaultValues.put(MongoDefinition.QF.MACHINE.fTemperature, "0");
		defaultValues.put(MongoDefinition.QF.MACHINE.fHumidity, "0");
		defaultValues.put(MongoDefinition.QF.MACHINE.fPower, "0");
		defaultValues.put(MongoDefinition.QF.MACHINE.fTodayCost, "0.0");
		defaultValues.put(MongoDefinition.QF.MACHINE.fTotalCost, "0.0");
		defaultValues.put(MongoDefinition.QF.MACHINE.fUnitCost, "0.0");
		defaultValues.put(MongoDefinition.QF.MACHINE.fRealtimeCost, "0");
		defaultValues.put(MongoDefinition.QF.MACHINE.fSavedCost, "0");
		defaultValues.put(MongoDefinition.QF.MACHINE.fSchedulingEvent, "0");
		defaultValues.put(MongoDefinition.QF.MACHINE.fTotalApp, "0");
		defaultValues.put(MongoDefinition.QF.MACHINE.fIndependentUser, "0");
		defaultValues.put(MongoDefinition.QF.MACHINE.fServedUser, "0");
	}
	
	@Override
	public String getTaskName() {
		return NAME;
	}

	@Override
	public MonitoringDataHandlerChain constructHandlerChain() {
		MonitoringDataHandlerChain chain = new MonitoringDataHandlerChain();
		chain.addHandler(new StructuredDataCollectingHandler(MongoDefinition.QF.MACHINE.fieldNames, fieldNameServiceKeyMap, defaultValues));
		chain.addHandler(new PersistMachineStatusDataHandler());
		return chain;
	}
	
	public static class PersistMachineStatusDataHandler implements MonitoringDataHandler {
		
		//private static final Logger log = LoggerFactory.getLogger(PersistMachineStatusDataHandler.class);

		@Override
		public void handle(Map<String, Map<String, String>> serviceData, Map<String, Map<String, String>> middleData) {
			Set<String> hostNames = middleData.keySet();
			for (String hostName : hostNames) {
				IQFMachineService qfmService = MonitorServiceBus.getQFMachineService();
				qfmService.addMachineStatusData(hostName, middleData.get(hostName));
			}
		}
		
	}
	

}
