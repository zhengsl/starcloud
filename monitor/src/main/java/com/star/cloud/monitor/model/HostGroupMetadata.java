package com.star.cloud.monitor.model;

public class HostGroupMetadata extends Metadata {

	private final String hostGroupId;
	
	public HostGroupMetadata(String hostGroupId) {
		super("hostgroup:" + hostGroupId, null, Metadata.ValueType.LIST);
		this.hostGroupId = hostGroupId;
	}
	
	public String getHostGroupId() {
		return hostGroupId;
	}

}
