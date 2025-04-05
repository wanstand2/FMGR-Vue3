package com.ruoyi.fmgr.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

public class FmgreHrEmployeeSalaryPayBo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 薪资发放id */
    private Long payId;
    
    private Long accountId;
    
    private Long userId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;
    
	public Long getPayId() {
		return payId;
	}

	public void setPayId(Long payId) {
		this.payId = payId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@Override
	public String toString() {
		return "FmgreHrEmployeeSalaryPayBo [payId=" + payId + ", accountId=" + accountId + ", userId=" + userId
				+ ", payTime=" + payTime + "]";
	}

}
