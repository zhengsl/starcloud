package com.star.cloud.monitor.service;

import java.util.List;
import java.util.Map;

import com.star.cloud.monitor.model.HostStatus;
import com.star.cloud.monitor.model.PerfValue;

public interface IStreamingMonitorService {
	
	Map<String, HostStatus> getHostStatus(List<String> hostNames);
	
	List<PerfValue> getBasicPerfValues(String hostName);
	
	Map<String, PerfValue> getAggregatedPerfValues(List<String> hostNames);

}
