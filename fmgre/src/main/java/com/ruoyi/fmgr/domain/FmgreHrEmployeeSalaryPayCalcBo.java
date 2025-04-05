package com.ruoyi.fmgr.domain;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.common.core.domain.BaseEntity;

public class FmgreHrEmployeeSalaryPayCalcBo extends FmgreHrEmployeeSalaryPay{

    static public class PunchSummay extends BaseEntity {
        private BigDecimal sumTime;
        private String date;
        private String minTime;
        private String maxTime;
        private Integer punchTimes;
        private BigDecimal salaryCount;
		public BigDecimal getSumTime() {
			return sumTime;
		}
		public void setSumTime(BigDecimal sumTime) {
			this.sumTime = sumTime;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getMinTime() {
			return minTime;
		}
		public void setMinTime(String minTime) {
			this.minTime = minTime;
		}
		public String getMaxTime() {
			return maxTime;
		}
		public void setMaxTime(String maxTime) {
			this.maxTime = maxTime;
		}
		public Integer getPunchTimes() {
			return punchTimes;
		}
		public void setPunchTimes(Integer punchTimes) {
			this.punchTimes = punchTimes;
		}
		public BigDecimal getSalaryCount() {
			return salaryCount;
		}
		public void setSalaryCount(BigDecimal salaryCount) {
			this.salaryCount = salaryCount;
		}
        
    }
	
	private boolean lockMonth = false;
	
    public boolean isLockMonth() {
		return lockMonth;
	}

	public void setLockMonth(boolean lockMonth) {
		this.lockMonth = lockMonth;
	}
    
    private List<PunchSummay> punchSummays;

	public List<PunchSummay> getPunchSummays() {
		return punchSummays;
	}

	public void setPunchSummays(List<PunchSummay> punchSummays) {
		this.punchSummays = punchSummays;
	}
    
    
}
