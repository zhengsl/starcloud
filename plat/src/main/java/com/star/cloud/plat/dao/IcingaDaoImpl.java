package com.star.cloud.plat.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class IcingaDaoImpl implements IIcingaDao {
	
	private final SqlSessionFactory ssFactory;
	
	public IcingaDaoImpl(SqlSessionFactory ssFactory) {
		this.ssFactory = ssFactory;
	}

	@Override
	public List<StatusHistoryDTO> queryStatusHistory(String hostName, long fromTime) {
		SqlSession session = ssFactory.openSession();
		try {
			Date d = new Date(fromTime);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			IcingaMapper mapper = session.getMapper(IcingaMapper.class);
			return mapper.getStatusHistory(hostName, formatter.format(d));
		} finally {
			session.close();
		}
	}

}
