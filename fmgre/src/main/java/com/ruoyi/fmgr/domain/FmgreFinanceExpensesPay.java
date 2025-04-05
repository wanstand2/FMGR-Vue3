package com.ruoyi.fmgr.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 费用支付对象 fmgre_finance_expenses_pay
 * 
 * @author terence
 * @date 2025-04-01
 */
public class FmgreFinanceExpensesPay extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 费用支出id */
    @Excel(name = "费用支出id")
    private Long payId;

    /** 付款id */
    @Excel(name = "付款id")
    private Long paymentId;

    /** 费用类目id */
    @Excel(name = "费用类目id")
    private Long expenseId;

    /** 费用类目价格id */
    @Excel(name = "费用类目价格id")
    private Long priceId;

    /** 发生数量 */
    @Excel(name = "发生数量")
    private BigDecimal payAmount;

    /** 合计费用 */
    @Excel(name = "合计费用")
    private BigDecimal paySubtotal;

    /** 支付名称 */
    @Excel(name = "支付名称")
    private String payName;

    /** 支付说明 */
    @Excel(name = "支付说明")
    private String payComment;

    public void setExpensesId(Long payId) 
    {
        this.payId = payId;
    }

    public Long getExpensesId() 
    {
        return payId;
    }
    public void setPaymentId(Long paymentId) 
    {
        this.paymentId = paymentId;
    }

    public Long getPaymentId() 
    {
        return paymentId;
    }
    
    public Long getPayId() {
		return payId;
	}

	public void setPayId(Long payId) {
		this.payId = payId;
	}

	public Long getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(Long expenseId) {
		this.expenseId = expenseId;
	}

	public void setPriceId(Long priceId) 
    {
        this.priceId = priceId;
    }

    public Long getPriceId() 
    {
        return priceId;
    }
    public void setPayAmount(BigDecimal payAmount) 
    {
        this.payAmount = payAmount;
    }

    public BigDecimal getPayAmount() 
    {
        return payAmount;
    }
    public void setPaySubtotal(BigDecimal paySubtotal) 
    {
        this.paySubtotal = paySubtotal;
    }

    public BigDecimal getPaySubtotal() 
    {
        return paySubtotal;
    }
    public void setPayName(String payName) 
    {
        this.payName = payName;
    }

    public String getPayName() 
    {
        return payName;
    }
    public void setPayComment(String payComment) 
    {
        this.payComment = payComment;
    }

    public String getPayComment() 
    {
        return payComment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("payId", getExpensesId())
            .append("paymentId", getPaymentId())
            .append("expenseId", getExpenseId())
            .append("priceId", getPriceId())
            .append("payAmount", getPayAmount())
            .append("paySubtotal", getPaySubtotal())
            .append("payName", getPayName())
            .append("payComment", getPayComment())
            .toString();
    }
}
