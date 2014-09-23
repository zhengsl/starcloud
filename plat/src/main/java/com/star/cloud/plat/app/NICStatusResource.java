package com.star.cloud.plat.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.cloud.monitor.service.IQFMachineService;
import com.star.cloud.monitor.service.MonitorServiceBus;
import com.star.cloud.plat.model.Host;
import com.star.cloud.plat.model.NICStatus;
import com.star.cloud.plat.model.NICStatusDetail;
import com.star.cloud.plat.service.IHostService;
import com.star.cloud.plat.service.PlatServiceBus;

public class NICStatusResource extends ServerResource {
	
	private static final Logger log = LoggerFactory.getLogger(DiskStatusResource.class);
	
	private static final ObjectMapper objMapper = new ObjectMapper();
	
	@Get
	public String getDiskStatus() {
		try {
			String[] idPlusName = getAttribute("idPlusName").split("[+]");
			String id = idPlusName[0];
			String name = idPlusName.length == 2 ? idPlusName[1] : "";
			IHostService hService = PlatServiceBus.getHostService();
			Host host = hService.getHost(id, name);
			IQFMachineService qfmService = MonitorServiceBus.getQFMachineService();
			Map<String, String> nicStatusData = qfmService.getDetailNetworkStatus(host.getName());
			
			NICStatus nicStatus = new NICStatus();
			nicStatus.setHostName(host.getName());
			nicStatus.setTotalIn(Float.parseFloat(nicStatusData.get("total:in")));
			nicStatus.setTotalOut(Float.parseFloat(nicStatusData.get("total:out")));
			
			List<NICStatusDetail> details = new ArrayList<NICStatusDetail>();
			for (int i = 0; nicStatusData.containsKey("nic" + i + ":name"); ++i) {
				NICStatusDetail nsd = new NICStatusDetail();
				nsd.setName(nicStatusData.get("nic" + i + ":name"));
				nsd.setIp(nicStatusData.get("nic" + i + ":ip"));
				nsd.setMac(nicStatusData.get("nic" + i + ":mac"));
				nsd.setIn(Float.parseFloat(nicStatusData.get("nic" + i + ":in")));
				nsd.setOut(Float.parseFloat(nicStatusData.get("nic" + i + ":out")));
				details.add(nsd);
			}
			nicStatus.setDetails(details);
			
			return objMapper.writeValueAsString(nicStatus);
		} catch (Exception e) {
			log.error("To get NICStatus failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}

}
