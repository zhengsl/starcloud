package com.star.cloud.monitor.icinga;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class IcingaRestResponse {
	
	private List<Result> result = new ArrayList<Result>();
	private String success;
	
	public static class Result {
		public String SERVICE_OUTPUT;
		public String SERVICE_LONG_OUTPUT;
		public String SERVICE_PERFDATA;
		public String INSTANCE_NAME;
		public String SERVICE_DISPLAY_NAME;
		public String SERVICE_CURRENT_STATE;
		public String SERVICE_LAST_CHECK;
		public String SERVICE_STATUS_UPDATE_TIME;
		public String HOST_ID;
		public String HOST_NAME;
		public String HOST_LATENCY; 
		public String SERVICE_IS_PENDING;
		
		public Result(Map<String, String> rObj) {
			this.SERVICE_OUTPUT = rObj.get("SERVICE_OUTPUT");
			this.SERVICE_LONG_OUTPUT = rObj.get("SERVICE_LONG_OUTPUT");
			this.SERVICE_PERFDATA = rObj.get("SERVICE_PERFDATA");
			this.INSTANCE_NAME = rObj.get("INSTANCE_NAME");
			this.SERVICE_DISPLAY_NAME = rObj.get("SERVICE_DISPLAY_NAME");
			this.SERVICE_CURRENT_STATE = rObj.get("SERVICE_CURRENT_STATE");
			this.SERVICE_LAST_CHECK = rObj.get("SERVICE_LAST_CHECK");
			this.SERVICE_STATUS_UPDATE_TIME = rObj.get("SERVICE_STATUS_UPDATE_TIME");
			this.HOST_ID = rObj.get("HOST_ID");
			this.HOST_NAME = rObj.get("HOST_NAME");
			this.HOST_LATENCY = rObj.get("HOST_LATENCY");
			this.SERVICE_IS_PENDING = rObj.get("SERVICE_IS_PENDING");
		}
	}
	
	public IcingaRestResponse(Map<String, Object> resultMap) {
		this.success = resultMap.get("success").toString();
		List<Map<String, String>> rArray = (List<Map<String, String>>)resultMap.get("result");
		for (Map<String, String> rObj : rArray) {
			Result r = new Result(rObj);
			result.add(r);
		}
	}

	public List<Result> getResult() {
		return result;
	}

	public void setResult(List<Result> result) {
		this.result = result;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}
	
	public Map<String, String> getServiceStatus(List<String> sdns) {
		Map<String, String> status = new LinkedHashMap<String, String>();
		for (Result r : result) {
			if (sdns.contains(r.SERVICE_DISPLAY_NAME)) {
				status.put(r.SERVICE_DISPLAY_NAME, r.SERVICE_CURRENT_STATE);
			}
		}
		return status;
	}
	
}
