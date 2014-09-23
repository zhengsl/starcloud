package com.star.cloud.monitor.icinga;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.star.cloud.monitor.model.StarCloudMonitorException;

public class IcingaRestRequest {
	
	private static final String home = "/icinga-web/web/api/";
	private static final String[] columns = {
		"instance_name",
		"service_id",
		"service_display_name",
		"service_output",
		"service_long_output",
		"service_perfdata",
		"service_current_state",
		"service_last_check",
		"service_status_update_time",
		"host_id",
		"host_name",
		"host_latency"
	};
	
	private String target = "service";
	private String serviceDisplayName;
	private String hostName;
	private String authKey;
	private String format = "json";
	
	public IcingaRestRequest() {
		
	}
	
	public IcingaRestRequest(String serviceDisplayName, String authKey) {
		this.serviceDisplayName = serviceDisplayName;
		this.authKey = authKey;
	}
	
	public IcingaRestRequest(String hostName, String serviceDisplayName, String authKey) {
		this.hostName = hostName;
		this.serviceDisplayName = serviceDisplayName;
		this.authKey = authKey;
	}
	
	public IcingaRestRequest(String target, String serviceDisplayName, 
			String hostName, String authKey, String format) {
		this.target = target;
		this.serviceDisplayName = serviceDisplayName;
		this.hostName = hostName;
		this.authKey = authKey;
		this.format = format;
	}
	
	public String generateRequestURL(String icingaIp, int port) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://" + icingaIp);
		sb.append(":" + port);
		sb.append(home);
		sb.append(target);
		sb.append("/");
		String filterPart = "filter[BAND(";
		if (serviceDisplayName != null) {
			filterPart = filterPart + "service_display_name|=|" + serviceDisplayName;
		}
		if (serviceDisplayName != null && hostName != null) {
			filterPart = filterPart + ";";
		}
		if (hostName != null) {
			filterPart = filterPart + "host_name|=|" + hostName;
		}
		filterPart = filterPart + ")]";
		try {
			sb.append(URLEncoder.encode(filterPart, "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			throw new StarCloudMonitorException(e1);
		}
		sb.append("/");
		String columnsPart = "columns[" + columns[0];
		for (int i = 1; i < columns.length; ++i) {
			columnsPart = columnsPart + "|" + columns[i];
		}
		columnsPart = columnsPart + "]";
		try {
			sb.append(URLEncoder.encode(columnsPart, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			throw new StarCloudMonitorException(e);
		}
		sb.append("/authkey=");
		sb.append(authKey);
		sb.append("/");
		sb.append(format);
		return sb.toString();
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getServiceDisplayName() {
		return serviceDisplayName;
	}

	public void setServiceDisplayName(String serviceDisplayName) {
		this.serviceDisplayName = serviceDisplayName;
	}
	
	public String getHostName() {
		return hostName;
	}
	
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public static String[] getColumns() {
		return columns;
	}

}
