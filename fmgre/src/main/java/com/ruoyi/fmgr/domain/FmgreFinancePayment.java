package com.ruoyi.fmgr.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 付款流水对象 fmgre_finance_payment
 * 
 * @author terence
 * @date 2025-03-10
 */
public class FmgreFinancePayment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 支付id */
    private Long paymentId;

    /** 付款账户id */
    @Excel(name = "付款账户id")
    private Long outAccId;

    /** 收款账户id */
    @Excel(name = "收款账户id")
    private Long inAccId;

    /** 操作用户id */
    @Excel(name = "操作用户id")
    private Long userId;

    /** 订单id(若有) */
    @Excel(name = "订单id(若有)")
    private Long orderId;

    /** 支付说明 */
    @Excel(name = "支付说明")
    private String paymentComment;

    /** 支付金额 */
    @Excel(name = "支付金额")
    private BigDecimal paymentAmount;

    /** 支付时间 */
    @Excel(name = "支付时间")
    private Date paymentTime;

    /** 付款账户余额 */
    @Excel(name = "付款账户余额 (付款后)")
    private BigDecimal outAccBanlance;

    /** 收款账户余额 */
    @Excel(name = "收款账户余额 (收款后)")
    private BigDecimal inAccBanlance;

    /** 查询时使用的账户id */
    private Long accountId;

    public void setPaymentId(Long paymentId) 
    {
        this.paymentId = paymentId;
    }

    public Long getPaymentId() 
    {
        return paymentId;
    }
    public void setOutAccId(Long outAccId) 
    {
        this.outAccId = outAccId;
    }

    public Long getOutAccId() 
    {
        return outAccId;
    }
    public void setInAccId(Long inAccId) 
    {
        this.inAccId = inAccId;
    }

    public Long getInAccId() 
    {
        return inAccId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setPaymentComment(String paymentComment) 
    {
        this.paymentComment = paymentComment;
    }

    public String getPaymentComment() 
    {
        return paymentComment;
    }
    public void setPaymentAmount(BigDecimal paymentAmount) 
    {
        this.paymentAmount = paymentAmount;
    }

    public BigDecimal getPaymentAmount() 
    {
        return paymentAmount;
    }
    public void setPaymentTime(Date paymentTime) 
    {
        this.paymentTime = paymentTime;
    }

    public Date getPaymentTime() 
    {
        return paymentTime;
    }
    public void setOutAccBanlance(BigDecimal outAccBanlance) 
    {
        this.outAccBanlance = outAccBanlance;
    }

    public BigDecimal getOutAccBanlance()    
    {
        return outAccBanlance;
    }
    public void setInAccBanlance(BigDecimal inAccBanlance) 
    {
        this.inAccBanlance = inAccBanlance;
    }   

    public BigDecimal getInAccBanlance() 
    {
        return inAccBanlance;
    }
    public void setAccountId(Long accountId) 
    {
        this.accountId = accountId;
    }

    public Long getAccountId() 
    {
        return accountId;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("paymentId", getPaymentId())
            .append("outAccId", getOutAccId())
            .append("inAccId", getInAccId())
            .append("userId", getUserId())
            .append("orderId", getOrderId())
            .append("paymentComment", getPaymentComment())
            .append("paymentAmount", getPaymentAmount())
            .append("paymentTime", getPaymentTime())
            .append("outAccBanlance", getOutAccBanlance())
            .append("inAccBanlance", getInAccBanlance())
            .toString();
    }
}
