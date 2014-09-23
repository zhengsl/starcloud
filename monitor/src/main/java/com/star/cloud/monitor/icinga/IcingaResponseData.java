package com.star.cloud.monitor.icinga;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.star.cloud.monitor.icinga.IcingaRestResponse.Result;


public class IcingaResponseData {
	
	private final IcingaRestResponse icingaData;
	private final List<IcingaPerfData> perfData = new ArrayList<IcingaPerfData>();
	
	public IcingaResponseData(IcingaRestResponse icingaData) {
		this.icingaData = icingaData;
		List<Result> results = getResults();
		for (Result result : results) {
			if (result.SERVICE_PERFDATA != null && !result.SERVICE_PERFDATA.isEmpty())
				perfData.add(IcingaPerfData.fromString(result.SERVICE_PERFDATA));
		}
	}
	
	public List<Result> getResults() {
		return icingaData.getResult();
	}
	
	public Map<String, List<String>> extractResults(List<String> keys) {
		Map<String, List<String>> result = new LinkedHashMap<String, List<String>>();
		int len1 = perfData.size();
		int len2 = keys.size();
		for (int i = 0; i < len1; ++i) {
			List<String> r = new ArrayList<String>();
			for (int j = 0; j < len2; ++j) {
				r.add(perfData.get(i).getValue(keys.get(j)));
			}
			result.put(getResults().get(i).HOST_NAME, r);
		}
		return result;
	}
	
	public Map<String, Map<String, String>> extractResult() {
		Map<String, Map<String, String>> result = new LinkedHashMap<String, Map<String, String>>();
		int len = perfData.size();
		for (int i = 0; i < len; ++i) {
			Map<String, String> dataMap = perfData.get(i).getMap();
			if (!dataMap.isEmpty()) {
				Map<String, String> m = new LinkedHashMap<String, String>();
				m.putAll(dataMap);
				m.put("servicestate", getResults().get(i).SERVICE_CURRENT_STATE);
				result.put(getResults().get(i).HOST_NAME, m);
			}
			
		}
		return result;
	}
	
	public Map<String, String> extractResultsUnderSameHost(List<String> keys) {
		Map<String, String> result = new LinkedHashMap<String, String>();
		for (IcingaPerfData pd : perfData) {
			for (String key : keys) {
				if (pd.keyExists(key)) {
					result.put(key, pd.getValue(key));
				}
			}
		}
		return result;
	}

}
