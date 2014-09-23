package com.star.cloud.plat.dao;

import java.util.List;

public interface IcingaMapper {

	List<StatusHistoryDTO> getStatusHistory(String hostName, String fromTime);

}
