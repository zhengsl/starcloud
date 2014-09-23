package com.star.cloud.monitor.daemon;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.cloud.monitor.icinga.IcingaResponseData;
import com.star.cloud.monitor.icinga.IcingaRestResponse;
import com.star.cloud.monitor.model.StarCloudMonitorException;

public class DataCollectionThread extends Thread {
	
	private static final Logger log = LoggerFactory.getLogger(DataCollectionThread.class);
	
	private static final ObjectMapper objMapper = new ObjectMapper();
	
	private final CloseableHttpClient httpClient; 
	private final List<String> urls;
	private final MonitoringDataHandlerChain handlerChain;
	private final String taskName;
	
	public DataCollectionThread(CloseableHttpClient httpClient, List<String> urls, MonitoringDataHandlerChain handlerChain,
			String taskName) {
		this.httpClient = httpClient;
		this.urls = urls;
		this.handlerChain = handlerChain;
		this.taskName = taskName;
	}
	
	@Override
	public void run() {
		CloseableHttpResponse response = null;
		try {
			Map<String, Map<String, String>> resultAll = new HashMap<String, Map<String, String>>();
			for (String url : urls) {
				HttpGet httpget = new HttpGet(url);
				response = httpClient.execute(httpget);
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String jsonResponse = EntityUtils.toString(entity);
					
					log.debug("Has got data from Icinga: " + jsonResponse);
					
					Map<String, Object> resultMap = objMapper.readValue(jsonResponse, Map.class);
					IcingaRestResponse icingaResponse = new IcingaRestResponse(resultMap);
					IcingaResponseData icingaResponseData = new IcingaResponseData(icingaResponse);
					
					Map<String, Map<String, String>> result = icingaResponseData.extractResult();
					
					for (String hostName : result.keySet()) {
						Map<String, String> perfData = result.get(hostName);
						if (resultAll.containsKey(hostName)) {
							resultAll.get(hostName).putAll(perfData);
						} else {
							resultAll.putAll(result);
						}
					}
				}
			}
			handlerChain.process(resultAll);
		} catch (Exception e) {
			log.error("Error happened while requesting Icinga in " + taskName + ".", e);
			throw new StarCloudMonitorException(e);
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				log.error("Error happened while closing response from Icinga in " + taskName + ".", e);
				throw new StarCloudMonitorException(e);
			}
		}
	}

}
