package com.star.cloud.domain.common;

public class StarCloudException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6099595744674581385L;

	public StarCloudException(String msg) {
		super(msg);
	}
	
	public StarCloudException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public StarCloudException(Throwable cause) {
		super(cause);
	}

}
