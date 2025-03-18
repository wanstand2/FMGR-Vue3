package com.ruoyi.fmgr.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采购记录对象 fmgre_purchase_item
 * 
 * @author terence
 * @date 2025-02-24
 */
public class FmgrePurchaseItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 采购记录id */
    private Long itemId;

    /** 采购需求id */
    @Excel(name = "采购需求id")
    private Long requirId;

    /** 采购订单id */
    @Excel(name = "采购订单id")
    private Long orderId;

    /** 原料id */
    @Excel(name = "原料id")
    private Long materailId;

    /** 希望采购数 */
    @Excel(name = "希望采购数")
    private BigDecimal requirNum;

    /** 希望采购基本单位（字典) */
    @Excel(name = "希望采购基本单位", readConverterExp = "希望采购基本单位（字典)")
    private String requirUnitDictid;

    /** 实际采购基本单位（字典) */
    @Excel(name = "实际采购基本单位", readConverterExp = "实际采购基本单位（字典)")
    private String orderUnitDictid;

    /** 实际采购数 */
    @Excel(name = "实际采购数")
    private BigDecimal orderNum;

    /** 实际采购量 */
    @Excel(name = "实际采购量")
    private BigDecimal orderAmount;

    /** 采购适用报价id */
    @Excel(name = "采购适用报价id")
    private Long quotaId;

    @Excel(name = "供应商id")
    private Long supplierId;

    /** 单项采购总价 */
    @Excel(name = "单项采购总价")
    private BigDecimal itemTotalPrice;

    private FmgreMaterial materail = new FmgreMaterial();
    
    private FmgreSupplierQuote quote = new FmgreSupplierQuote();

    public void setItemId(Long itemId) 
    {
        this.itemId = itemId;
    }

    public Long getItemId() 
    {
        return itemId;
    }
    public void setRequirId(Long requirId) 
    {
        this.requirId = requirId;
    }

    public Long getRequirId() 
    {
        return requirId;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setMaterailId(Long materailId) 
    {
        this.materailId = materailId;
    }

    public Long getMaterailId() 
    {
        return materailId;
    }
    public void setRequirNum(BigDecimal requirNum) 
    {
        this.requirNum = requirNum;
    }

    public BigDecimal getRequirNum() 
    {
        return requirNum;
    }
    public void setRequirUnitDictid(String requirUnitDictid) 
    {
        this.requirUnitDictid = requirUnitDictid;
    }

    public String getRequirUnitDictid() 
    {
        return requirUnitDictid;
    }
    public void setOrderNum(BigDecimal orderNum) 
    {
        this.orderNum = orderNum;
    }

    public BigDecimal getOrderNum() 
    {
        return orderNum;
    }
    public void setOrderAmount(BigDecimal orderAmount) 
    {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getOrderAmount() 
    {
        return orderAmount;
    }
    public void setQuotaId(Long quotaId) 
    {
        this.quotaId = quotaId;
    }

    public Long getQuotaId() 
    {
        return quotaId;
    }

    public void setMaterail(FmgreMaterial materail) 
    {
        this.materail = materail;
    }

    public FmgreMaterial getMaterail() 
    {
        return materail;
    }

    public void setItemTotalPrice(BigDecimal itemTotalPrice) 
    {
        this.itemTotalPrice = itemTotalPrice;
    }

    public BigDecimal getItemTotalPrice() 
    {
        return itemTotalPrice;
    }

    public void setSupplierId(Long supplierId) 
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId() 
    {
        return supplierId;
    }

    public void setOrderUnitDictid(String orderUnitDictid) 
    {
        this.orderUnitDictid = orderUnitDictid;
    }

    public String getOrderUnitDictid() 
    {   
        return orderUnitDictid;
    }

    public FmgreSupplierQuote getQuote() {
		return quote;
	}

	public void setQuote(FmgreSupplierQuote quote) {
		this.quote = quote;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("itemId", getItemId())
            .append("requirId", getRequirId())
            .append("orderId", getOrderId())
            .append("materailId", getMaterailId())
            .append("requirNum", getRequirNum())
            .append("requirUnitDictid", getRequirUnitDictid())
            .append("orderNum", getOrderNum())
            .append("orderAmount", getOrderAmount())
            .append("orderUnitDictid", getOrderUnitDictid())
            .append("quotaId", getQuotaId())
            .append("supplierId", getSupplierId())
            .append("itemTotalPrice", getItemTotalPrice())
            .append("materail", getMaterail())
            .append("quote", getQuote())
            .toString();
    }
}
