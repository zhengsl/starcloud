package com.star.cloud.plat.model;

public class StarCloudPlatException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6099595744674581385L;

	public StarCloudPlatException(String msg) {
		super(msg);
	}
	
	public StarCloudPlatException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public StarCloudPlatException(Throwable cause) {
		super(cause);
	}

}
