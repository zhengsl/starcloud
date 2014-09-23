package com.star.cloud.plat.dao;

import java.util.List;

public interface IIcingaDao {
	
	List<StatusHistoryDTO> queryStatusHistory(String hostName, long fromTime);

}
