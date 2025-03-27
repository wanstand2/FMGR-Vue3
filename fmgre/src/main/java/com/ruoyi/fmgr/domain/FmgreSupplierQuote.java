package com.ruoyi.fmgr.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 供应商报价对象 fmgre_supplier_quote
 * 
 * @author terence
 * @date 2025-02-24
 */
public class FmgreSupplierQuote extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报价id */
    private Long quoteId;

    /** 原料id */
    @Excel(name = "原料id")
    private Long materailId;

    /** 供应商id */
    @Excel(name = "供应商id")
    private Long supplierId;

    /** 供应品牌id(0为散装) */
    @Excel(name = "供应品牌id(0为散装)")
    private Long supplierBrandId;

    /** 包装单位（字典) */
    @Excel(name = "包装单位", readConverterExp = "包装单位（字典)")
    private String packUnitDictid;

    /** 包装数量(子包装数) */
    @Excel(name = "包装数量(子包装数)(弃用)")
    private BigDecimal packNum;

    /** 包装大小(包装总和) */
    @Excel(name = "包装大小(包装总和)")
    private BigDecimal packSize;

    /** 子包装单位（字典) */
    @Excel(name = "子包装单位", readConverterExp = "子包装单位（字典)")
    private String subPackUnitDictid;

    /** 子包装数量(子包装个数) */
    @Excel(name = "子包装数量(子包装个数)")
    private Long subPackNum;

    /** 子包装大小(子包装总和) */
    @Excel(name = "子包装大小(子包装总和)")
    private BigDecimal subPackSize;

    /** 报价时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报价时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date quotaTime;

    /** 报价价格 */
    @Excel(name = "报价价格")
    private BigDecimal quotaPrice;

    /** 报价说明 */
    @Excel(name = "报价说明")
    private String quotaComment;

    public void setQuoteId(Long quoteId) 
    {
        this.quoteId = quoteId;
    }

    public Long getQuoteId() 
    {
        return quoteId;
    }
    public void setMaterailId(Long materailId) 
    {
        this.materailId = materailId;
    }

    public Long getMaterailId() 
    {
        return materailId;
    }
    public void setSupplierId(Long supplierId) 
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId() 
    {
        return supplierId;
    }
    public void setSupplierBrandId(Long supplierBrandId) 
    {
        this.supplierBrandId = supplierBrandId;
    }

    public Long getSupplierBrandId() 
    {
        return supplierBrandId;
    }
    public void setPackUnitDictid(String packUnitDictid) 
    {
        this.packUnitDictid = packUnitDictid;
    }

    public String getPackUnitDictid() 
    {
        return packUnitDictid;
    }
    public void setPackNum(BigDecimal packNum) 
    {
        this.packNum = packNum;
    }

    public BigDecimal getPackNum() 
    {
        return packNum;
    }
    public void setPackSize(BigDecimal packSize) 
    {
        this.packSize = packSize;
    }

    public BigDecimal getPackSize() 
    {
        return packSize;
    }
    public void setSubPackUnitDictid(String subPackUnitDictid) 
    {
        this.subPackUnitDictid = subPackUnitDictid;
    }

    public String getSubPackUnitDictid() 
    {
        return subPackUnitDictid;
    }
    public void setSubPackNum(Long subPackNum) 
    {
        this.subPackNum = subPackNum;
    }

    public Long getSubPackNum() 
    {
        return subPackNum;
    }
    public void setSubPackSize(BigDecimal subPackSize) 
    {
        this.subPackSize = subPackSize;
    }

    public BigDecimal getSubPackSize() 
    {
        return subPackSize;
    }
    public void setQuotaTime(Date quotaTime) 
    {
        this.quotaTime = quotaTime;
    }

    public Date getQuotaTime() 
    {
        return quotaTime;
    }
    public void setQuotaPrice(BigDecimal quotaPrice) 
    {
        this.quotaPrice = quotaPrice;
    }

    public BigDecimal getQuotaPrice() 
    {
        return quotaPrice;
    }
    public void setQuotaComment(String quotaComment) 
    {
        this.quotaComment = quotaComment;
    }

    public String getQuotaComment() 
    {
        return quotaComment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("quoteId", getQuoteId())
            .append("materailId", getMaterailId())
            .append("supplierId", getSupplierId())
            .append("supplierBrandId", getSupplierBrandId())
            .append("packUnitDictid", getPackUnitDictid())
            .append("packNum", getPackNum())
            .append("packSize", getPackSize())
            .append("subPackUnitDictid", getSubPackUnitDictid())
            .append("subPackNum", getSubPackNum())
            .append("subPackSize", getSubPackSize())
            .append("quotaTime", getQuotaTime())
            .append("quotaPrice", getQuotaPrice())
            .append("quotaComment", getQuotaComment())
            .toString();
    }
}
