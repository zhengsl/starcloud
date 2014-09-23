package com.star.cloud.monitor.model;

public class HostStatus extends PerfValue {
	
	public HostStatus(String value) {
		super("hostStatus", value, PerfValue.ValueType.STRING);
	}
	
	public class HSValues {
		public static final String HS_NORMAL = "HS:normal";
		public static final String HS_WARNING = "HS:warning";
		public static final String HS_ERROR = "HS:error";
		public static final String HS_UNKNOWN = "HS:unknown";
		public static final String HS_CLOSED = "HS:closed";
	}

}
