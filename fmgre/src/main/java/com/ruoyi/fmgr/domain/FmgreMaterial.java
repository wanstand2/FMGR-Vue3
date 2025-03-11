package com.ruoyi.fmgr.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 原材料对象 fmgre_material
 * 
 * @author terence
 * @date 2025-02-24
 */
public class FmgreMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 原料id */
    private Long materailId;

    /** 原料类别（字典) */
    @Excel(name = "原料类别", readConverterExp = "原料类别（字典)")
    private String materailTypeDictid;

    /** 原料编码 */
    @Excel(name = "原料编码")
    private String materailCode;

    /** 原料名称 */
    @Excel(name = "原料名称")
    private String materailName;

    /** 基本单位（字典) */
    @Excel(name = "基本单位", readConverterExp = "基本单位（字典)")
    private String unitDictid;

    /** 计价量 */
    @Excel(name = "计价量")
    private BigDecimal valuationAmount;

    /** 销售价格 */
    @Excel(name = "销售价格")
    private BigDecimal priceSale;

    /** 存储条件(字典) */
    @Excel(name = "存储条件(字典)")
    private String storageDictid;

    /** 备注 */
    @Excel(name = "备注")
    private String comment;

    public void setMaterailId(Long materailId) 
    {
        this.materailId = materailId;
    }

    public Long getMaterailId() 
    {
        return materailId;
    }
    public void setMaterailTypeDictid(String materailTypeDictid) 
    {
        this.materailTypeDictid = materailTypeDictid;
    }

    public String getMaterailTypeDictid() 
    {
        return materailTypeDictid;
    }
    public void setMaterailCode(String materailCode) 
    {
        this.materailCode = materailCode;
    }

    public String getMaterailCode() 
    {
        return materailCode;
    }
    public void setMaterailName(String materailName) 
    {
        this.materailName = materailName;
    }

    public String getMaterailName() 
    {
        return materailName;
    }
    public void setUnitDictid(String unitDictid) 
    {
        this.unitDictid = unitDictid;
    }

    public String getUnitDictid() 
    {
        return unitDictid;
    }
    public void setValuationAmount(BigDecimal valuationAmount) 
    {
        this.valuationAmount = valuationAmount;
    }

    public BigDecimal getValuationAmount() 
    {
        return valuationAmount;
    }
    public void setPriceSale(BigDecimal priceSale) 
    {
        this.priceSale = priceSale;
    }

    public BigDecimal getPriceSale() 
    {
        return priceSale;
    }
    public void setStorageDictid(String storageDictid) 
    {
        this.storageDictid = storageDictid;
    }

    public String getStorageDictid() 
    {
        return storageDictid;
    }
    public void setComment(String comment) 
    {
        this.comment = comment;
    }

    public String getComment() 
    {
        return comment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("materailId", getMaterailId())
            .append("materailTypeDictid", getMaterailTypeDictid())
            .append("materailCode", getMaterailCode())
            .append("materailName", getMaterailName())
            .append("unitDictid", getUnitDictid())
            .append("valuationAmount", getValuationAmount())
            .append("priceSale", getPriceSale())
            .append("storageDictid", getStorageDictid())
            .append("comment", getComment())
            .toString();
    }
}
