package com.star.cloud.plat.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.cloud.plat.dao.IIcingaDao;
import com.star.cloud.plat.dao.IMachineDao;
import com.star.cloud.plat.dao.IVMDao;
import com.star.cloud.plat.dao.PlatDaoBus;
import com.star.cloud.plat.dao.StatusHistoryDTO;
import com.star.cloud.plat.model.Card;
import com.star.cloud.plat.model.Host;
import com.star.cloud.plat.model.StarCloudPlatException;
import com.star.cloud.plat.model.StatusHistory;
import com.star.cloud.plat.model.VMInstance;

public class HostServiceImpl implements IHostService {
	
	private static final Logger log = LoggerFactory.getLogger(HostServiceImpl.class);

	@Override
	public Host getHost(String id, String name) {
		IMachineDao mdao = PlatDaoBus.getMachineDao();
		Card c = mdao.queryCard(id, name);
		if (c != null) return c;
		
		IVMDao vdao = PlatDaoBus.getVMDao();
		VMInstance v = vdao.queryVMInstance(id, name);
		return v;
	}

	@Override
	public List<StatusHistory> getEvents(String id, String name, long sinceWhen) {
		Host h = getHost(id, name);
		if (h == null) {
			String msg = "Host doesn't exist: " + id + "+" + name;
			log.error(msg);
			throw new StarCloudPlatException(msg);
		}
		IIcingaDao dao = PlatDaoBus.getIcingaDao();
		List<StatusHistoryDTO> list = dao.queryStatusHistory(h.getName(), sinceWhen);
		List<StatusHistory> result = new ArrayList<StatusHistory>();
		for (StatusHistoryDTO dto : list) {
			result.add(dto.toStatusHistory());
		}
		return result;
	}

}
