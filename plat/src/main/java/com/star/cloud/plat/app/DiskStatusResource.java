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
import com.star.cloud.plat.model.DiskStatus;
import com.star.cloud.plat.model.DiskStatusDetail;
import com.star.cloud.plat.model.Host;
import com.star.cloud.plat.service.IHostService;
import com.star.cloud.plat.service.PlatServiceBus;

public class DiskStatusResource extends ServerResource {
	
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
			Map<String, String> diskStatusData = qfmService.getDetailDiskStatus(host.getName());
			
			DiskStatus diskStatus = new DiskStatus();
			diskStatus.setHostName(host.getName());
			diskStatus.setTotalRead(Float.parseFloat(diskStatusData.get("total:read")));
			diskStatus.setTotalWrite(Float.parseFloat(diskStatusData.get("total:write")));
			
			List<DiskStatusDetail> details = new ArrayList<DiskStatusDetail>();
			
			for (int i = 0; diskStatusData.containsKey("disk" + i + ":name"); ++i) {
				DiskStatusDetail dsd = new DiskStatusDetail();
				dsd.setName(diskStatusData.get("disk" + i + ":name"));
				dsd.setSize(Float.parseFloat(diskStatusData.get("disk" + i + ":size")));
				dsd.setInuse(Float.parseFloat(diskStatusData.get("disk" + i + ":inuse")));
				dsd.setMount(diskStatusData.get("disk" + i + ":mount"));
				details.add(dsd);
			}
			diskStatus.setDetails(details);
			
			return objMapper.writeValueAsString(diskStatus);
		} catch (Exception e) {
			log.error("To get DiskStatus failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}

}
