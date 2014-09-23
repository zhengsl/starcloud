package com.star.cloud.monitor.model;

public class StarCloudMonitorException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8046458505407683366L;

	public StarCloudMonitorException(String msg) {
		super(msg);
	}
	
	public StarCloudMonitorException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public StarCloudMonitorException(Throwable cause) {
		super(cause);
	}

}
