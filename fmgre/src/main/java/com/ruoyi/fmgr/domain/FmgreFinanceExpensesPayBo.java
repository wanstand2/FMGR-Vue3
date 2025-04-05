package com.ruoyi.fmgr.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FmgreFinanceExpensesPayBo extends FmgreFinanceExpensesPay {
    
    private Long accountId;
    
    private Long userId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

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
		return "FmgreHrEmployeeSalaryPayBo [super=" + super.toString() + ", accountId=" + accountId + ", userId=" + userId
				+ ", payTime=" + payTime + "]";
	}

}
