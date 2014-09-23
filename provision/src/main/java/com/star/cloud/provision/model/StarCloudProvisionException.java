package com.star.cloud.provision.model;

public class StarCloudProvisionException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7852945324015365851L;

	public StarCloudProvisionException(String msg) {
		super(msg);
	}
	
	public StarCloudProvisionException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public StarCloudProvisionException(Throwable cause) {
		super(cause);
	}

}
