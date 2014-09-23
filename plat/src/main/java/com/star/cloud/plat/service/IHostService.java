package com.star.cloud.plat.service;

import java.util.List;

import com.star.cloud.plat.model.Host;
import com.star.cloud.plat.model.StatusHistory;

public interface IHostService {
	
	Host getHost(String id, String name);
	
	List<StatusHistory> getEvents(String id, String name, long sinceWhen);

}
