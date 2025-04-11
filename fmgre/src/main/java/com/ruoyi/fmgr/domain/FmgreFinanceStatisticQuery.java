package com.ruoyi.fmgr.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FmgreFinanceStatisticQuery {

	private Long[] deptIds;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
	
    private String interval;
	
	private String type;

	public Long[] getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(Long[] deptIds) {
		this.deptIds = deptIds;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
