package com.star.cloud.monitor.daemon;


public class ServiceStatusTask extends ServiceDataCollectingTask {
	
	public static final String NAME = "serviceStatusAggregation";
	
	@Override
	public String getTaskName() {
		return NAME;
	}

	@Override
	public MonitoringDataHandlerChain constructHandlerChain() {
		MonitoringDataHandlerChain chain = new MonitoringDataHandlerChain();
		chain.addHandler(new ServiceStatusCollectingHandler());
		return chain;
	}
	
}
