package com.star.cloud.monitor.service;

import java.util.LinkedList;
import java.util.List;

public class MongoDefinition {
	
	public interface NS {
		
		String getName();
		
		List<String> getFieldNames();
	}
	
	public static class QF {
		public static final String name = "qfDb";
		
		public static class TASK {
			public static final String name = "taskColl";
			
			public static final String fTaskName = "taskName";
			//public static final String fHostNames = "hostNames";
			public static final String fServices = "services";
			//public static final String fServiceName = "serviceName";
			//public static final String fServiceKeys = "serviceKeys";
		}
		
		public static class MACHINE implements NS {
			public static final String name = "machineColl";
			
			public static final String fTemperature = "temperature";
			public static final String fHumidity = "humidity";
			public static final String fPower = "power";
			
			public static final String fTodayCost = "todayCost";
			public static final String fTotalCost = "totalCost";
			public static final String fUnitCost = "unitCost";
			
			public static final String fRealtimeCost = "realtimeCost";
			public static final String fSchedulingEvent = "schedulingEvent";
			public static final String fSavedCost = "savedCost";
			
			public static final String fTotalApp = "totalApp";
			public static final String fIndependentUser = "independentUser";
			public static final String fServedUser = "servedUser";
			
			public static final String fTimestamp = "timestamp";
			
			public static final List<String> fieldNames = new LinkedList<String>();
			
			static {
				fieldNames.add(fTemperature);
				fieldNames.add(fHumidity);
				fieldNames.add(fPower);
				fieldNames.add(fTodayCost);
				fieldNames.add(fTotalCost);
				fieldNames.add(fUnitCost);
				fieldNames.add(fRealtimeCost);
				fieldNames.add(fSchedulingEvent);
				fieldNames.add(fSavedCost);
				fieldNames.add(fTotalApp);
				fieldNames.add(fIndependentUser);
				fieldNames.add(fServedUser);
				fieldNames.add(fTimestamp);
			}
			
			public static final MACHINE ins = new MACHINE();
			
			private MACHINE() {}
			
			public String getName() {
				return name;
			}
			
			public List<String> getFieldNames() {
				return fieldNames;
			}
			
		}
		
		public static class HOST implements NS {
			public static final String name = "hostColl";
			
			public static final String fCpuInUse = "cpuInUse";
			public static final String fRamInUse = "ramInUse";
			public static final String fDiskInUse = "diskInUse";
			public static final String fNicInUse = "nicInUse";
			
			public static final String fRunningApps = "runningApps";
			
			public static final String fHealth = "health";
			
			public static final String fTimestamp = "timestamp";
			
			public static final List<String> fieldNames = new LinkedList<String>();
			
			static {
				fieldNames.add(fCpuInUse);
				fieldNames.add(fRamInUse);
				fieldNames.add(fDiskInUse);
				fieldNames.add(fNicInUse);
				fieldNames.add(fRunningApps);
				fieldNames.add(fHealth);
				fieldNames.add(fTimestamp);
			}
			
			public static final HOST ins = new HOST();
			
			private HOST() {}
			
			public String getName() {
				return name;
			}
			
			public List<String> getFieldNames() {
				return fieldNames;
			}
			
		}
		
		public static class SERVICE implements NS {
			public static final String name = "serviceColl";
			
			public static final String fServiceName = "serviceName";
			public static final String fConfiguredCapacity = "configuredCapacity";
			public static final String fInusedCapacity = "inusedCapacity";
			
			public static final String fTimestamp = "timestamp";
			
			public static final List<String> fieldNames = new LinkedList<String>();
			
			static {
				fieldNames.add(fServiceName);
				fieldNames.add(fConfiguredCapacity);
				fieldNames.add(fInusedCapacity);
				fieldNames.add(fTimestamp);
			}
			
			public static final SERVICE ins = new SERVICE();
			
			private SERVICE() {}
			
			public String getName() {
				return name;
			}
			
			public List<String> getFieldNames() {
				return fieldNames;
			}
			
		}
		
		public static class DISK {
			public static final String name = "diskColl";
		}
		
		public static class NETWORK {
			public static final String name = "networkColl";
		}
	}
	
}
