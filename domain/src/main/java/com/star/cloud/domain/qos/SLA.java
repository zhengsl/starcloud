package com.star.cloud.domain.qos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SLA {
	
	private final String userGroupId;
	
	private final Date validTo;
	
	private final List<TimePerf> tpList = new ArrayList<TimePerf>();
	
	public static class TimePerf {
		private final DateTimeTemplate dt;
		private final PerfPackage pp;
		
		public TimePerf(DateTimeTemplate dt, PerfPackage pp) {
			this.dt = dt;
			this.pp = pp;
		}
		
		public boolean isSatisfied(TimePerf tp) {
			return this.dt.isMatched(tp.getDt()) && this.pp.isSatisfied(tp.getPp());
		}
		
		public DateTimeTemplate getDt() {
			return dt;
		}
	
		public PerfPackage getPp() {
			return pp;
		}
		
	}
	
	public SLA(String userGroupId, Date validTo) {
		this.userGroupId = userGroupId;
		this.validTo = validTo;
	}
	
	public boolean isSatisfied(TimePerf timePerf) {
		for (TimePerf tp : tpList) {
			if (tp.isSatisfied(timePerf))
				return true;
		}
		return false;
	}
	
	public void addTimePerf(TimePerf tp) {
		this.tpList.add(tp);
	}
	
	public void addTimePerf(DateTimeTemplate dt, PerfPackage pp) {
		this.tpList.add(new TimePerf(dt, pp));
	}

	public String getUserGroupId() {
		return userGroupId;
	}

	public Date getValidTo() {
		return validTo;
	}

	public List<TimePerf> getTpList() {
		return tpList;
	}
	
}
