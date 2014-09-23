package com.star.cloud.virt.model;

public class StarCloudVirtualizationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7852945324015365851L;

	public StarCloudVirtualizationException(String msg) {
		super(msg);
	}
	
	public StarCloudVirtualizationException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public StarCloudVirtualizationException(Throwable cause) {
		super(cause);
	}

}
