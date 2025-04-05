package com.ruoyi.fmgr.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 费用类目价格对象 fmgre_finance_expense_price
 * 
 * @author terence
 * @date 2025-04-01
 */
public class FmgreFinanceExpensePrice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 费用类目价格id */
    @Excel(name = "费用类目价格id")
    private Long priceId;

    /** 所属组织id */
    @Excel(name = "所属组织id")
    private Long deptId;

    @Excel(name = "费用类目id")
    private Long expenseId;

    /** 费用类目价格名称 */
    @Excel(name = "费用类目价格名称")
    private String priceName;

    /** 费用类目价格 */
    @Excel(name = "费用类目价格")
    private BigDecimal pricePrice;

    /** 费用类目价格说明 */
    @Excel(name = "费用类目价格说明")
    private String priceComment;

    /** 定价时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "定价时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date priceTime;

    public void setPriceId(Long priceId) 
    {
        this.priceId = priceId;
    }

    public Long getPriceId() 
    {
        return priceId;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setExpenseId(Long expenseId) 
    {
        this.expenseId = expenseId;
    }

    public Long getExpenseId() 
    {
        return expenseId;
    }
    public void setPriceName(String priceName) 
    {
        this.priceName = priceName;
    }

    public String getPriceName() 
    {
        return priceName;
    }
    public void setPricePrice(BigDecimal pricePrice) 
    {
        this.pricePrice = pricePrice;
    }

    public BigDecimal getPricePrice() 
    {
        return pricePrice;
    }
    public void setPriceComment(String priceComment) 
    {
        this.priceComment = priceComment;
    }

    public String getPriceComment() 
    {
        return priceComment;
    }
    public void setPriceTime(Date priceTime) 
    {
        this.priceTime = priceTime;
    }

    public Date getPriceTime() 
    {
        return priceTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("priceId", getPriceId())
            .append("deptId", getDeptId())
            .append("expenseId", getExpenseId())
            .append("priceName", getPriceName())
            .append("pricePrice", getPricePrice())
            .append("priceComment", getPriceComment())
            .append("priceTime", getPriceTime())
            .toString();
    }
}
