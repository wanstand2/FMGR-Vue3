package com.ruoyi.fmgr.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采购订单对象 fmgre_purchase_order
 * 
 * @author terence
 * @date 2025-02-24
 */
public class FmgrePurchaseOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 采购订单id */
    private Long orderId;

    /** 采购者id */
    @Excel(name = "采购者id")
    private Long userId;

    /** 供应商id */
    @Excel(name = "供应商id")
    private Long supplierId;

    /** 原料编码 */
    @Excel(name = "订单编码")
    private String orderCode;

    /** 交付方式（字典) */
    @Excel(name = "交付方式", readConverterExp = "交付方式（字典)")
    private String deliveryDictid;

    /** 采购时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "采购时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderTime;

    /** 采购总价 */
    @Excel(name = "采购总价")
    private BigDecimal orderTotalPrice;

    /** 采购说明 */
    @Excel(name = "采购说明")
    private String orderComment;

    /** 付款id(0未支付) */
    @Excel(name = "付款id(0未支付)")
    private Long paymentId;

    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setSupplierId(Long supplierId) 
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId() 
    {
        return supplierId;
    }

    public void setDeliveryDictid(String deliveryDictid) 
    {
        this.deliveryDictid = deliveryDictid;
    }

    public String getDeliveryDictid() 
    {
        return deliveryDictid;
    }
    public void setOrderTime(Date orderTime) 
    {
        this.orderTime = orderTime;
    }

    public Date getOrderTime() 
    {
        return orderTime;
    }
    public void setOrderTotalPrice(BigDecimal orderTotalPrice) 
    {
        this.orderTotalPrice = orderTotalPrice;
    }

    public BigDecimal getOrderTotalPrice() 
    {
        return orderTotalPrice;
    }
    public void setOrderComment(String orderComment) 
    {
        this.orderComment = orderComment;
    }

    public String getOrderComment() 
    {
        return orderComment;
    }
    public void setPaymentId(Long paymentId) 
    {
        this.paymentId = paymentId;
    }

    public Long getPaymentId() 
    {
        return paymentId;
    }

    public void setOrderCode(String orderCode) 
    {
        this.orderCode = orderCode;
    }

    public String getOrderCode() 
    {
        return orderCode;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("userId", getUserId())
            .append("supplierId", getSupplierId())
            .append("orderCode", getOrderCode())
            .append("deliveryDictid", getDeliveryDictid())
            .append("orderTime", getOrderTime())
            .append("orderTotalPrice", getOrderTotalPrice())
            .append("orderComment", getOrderComment())
            .append("paymentId", getPaymentId())
            .toString();
    }
}
