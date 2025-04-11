package com.ruoyi.fmgr.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FmgreFinanceIncomePayBo extends FmgreFinanceIncomePay {
    
    private Long accountId;
    
    private Long userId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date paymentTime;

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

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	@Override
	public String toString() {
		return "FmgreHrEmployeeSalaryPayBo [super=" + super.toString() + ", accountId=" + accountId + ", userId=" + userId
				+ ", paymentTime=" + paymentTime + "]";
	}

}
