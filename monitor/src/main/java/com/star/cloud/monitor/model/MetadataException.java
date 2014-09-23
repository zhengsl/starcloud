package com.star.cloud.monitor.model;

public class MetadataException extends StarCloudMonitorException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -99658589377459058L;
	
	private final Metadata mdata;

	public MetadataException(String msg, Metadata mdata) {
		super(msg);
		this.mdata = mdata;
	}
	
	public Metadata getMetadata() {
		return this.mdata;
	}

}
