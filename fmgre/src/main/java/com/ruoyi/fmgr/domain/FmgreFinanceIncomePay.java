package com.ruoyi.fmgr.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 收入对象 fmgre_finance_income_pay
 * 
 * @author terence
 * @date 2025-04-09
 */
public class FmgreFinanceIncomePay extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 收入id */
    @Excel(name = "收入id")
    private Long incomeId;

    /** 销售账单 */
    @Excel(name = "销售账单")
    private Long billId;

    /** 收款id */
    @Excel(name = "收款id")
    private Long paymentId;

    /** 收入类型 */
    @Excel(name = "收入类型")
    private String incomeTypeDictid;

    /** 收入合计 */
    @Excel(name = "收入合计")
    private BigDecimal incomeSubtotal;

    /** 收入名称 */
    @Excel(name = "收入名称")
    private String incomeName;

    /** 收入说明 */
    @Excel(name = "收入说明")
    private String incomeComment;

    /** 收入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "收入时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date incomeTime;

    /** 门店Id */
    @Excel(name = "门店Id")
    private Long deptId;

    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }

    public void setIncomeId(Long incomeId) 
    {
        this.incomeId = incomeId;
    }

    public Long getIncomeId() 
    {
        return incomeId;
    }
    public void setBillId(Long billId) 
    {
        this.billId = billId;
    }

    public Long getBillId() 
    {
        return billId;
    }
    public void setPaymentId(Long paymentId) 
    {
        this.paymentId = paymentId;
    }

    public Long getPaymentId() 
    {
        return paymentId;
    }
    public void setIncomeTypeDictid(String incomeTypeDictid) 
    {
        this.incomeTypeDictid = incomeTypeDictid;
    }

    public String getIncomeTypeDictid() 
    {
        return incomeTypeDictid;
    }
    public void setIncomeSubtotal(BigDecimal incomeSubtotal) 
    {
        this.incomeSubtotal = incomeSubtotal;
    }

    public BigDecimal getIncomeSubtotal() 
    {
        return incomeSubtotal;
    }
    public void setIncomeName(String incomeName) 
    {
        this.incomeName = incomeName;
    }

    public String getIncomeName() 
    {
        return incomeName;
    }
    public void setIncomeComment(String incomeComment) 
    {
        this.incomeComment = incomeComment;
    }

    public String getIncomeComment() 
    {
        return incomeComment;
    }
    public void setIncomeTime(Date incomeTime) 
    {
        this.incomeTime = incomeTime;
    }

    public Date getIncomeTime() 
    {
        return incomeTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("incomeId", getIncomeId())
            .append("billId", getBillId())
            .append("paymentId", getPaymentId())
            .append("incomeTypeDictid", getIncomeTypeDictid())
            .append("incomeSubtotal", getIncomeSubtotal())
            .append("incomeName", getIncomeName())
            .append("incomeComment", getIncomeComment())
            .append("incomeTime", getIncomeTime())
            .toString();
    }
}
