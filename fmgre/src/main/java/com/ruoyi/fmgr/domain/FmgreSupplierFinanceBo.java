package com.ruoyi.fmgr.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 供应商财务统计
 * 
 * @author terence
 * @date 2025-02-24
 */
public class FmgreSupplierFinanceBo {
    
	private Long supplierId;
	
    private Date orderTime;
    
    private BigDecimal debtAmount;

    private BigDecimal debtNum;

	public BigDecimal getDebtAmount() {
		return debtAmount;
	}

	public void setDebtAmount(BigDecimal debtAmount) {
		this.debtAmount = debtAmount;
	}

	public BigDecimal getDebtNum() {
		return debtNum;
	}

	public void setDebtNum(BigDecimal debtNum) {
		this.debtNum = debtNum;
	}

    public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("supplierId", getSupplierId())
			.append("orderTime", getOrderTime())
            .append("debtAmount", getDebtAmount())
            .append("debtNum", getDebtNum())
            .toString();
    }
}
