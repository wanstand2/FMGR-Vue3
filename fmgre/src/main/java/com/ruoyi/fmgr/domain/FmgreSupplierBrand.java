package com.ruoyi.fmgr.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 供应商品牌对象 fmgre_supplier_brand
 * 
 * @author terence
 * @date 2025-02-24
 */
public class FmgreSupplierBrand extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 供应品牌id */
    private Long brandId;

    /** 供应品牌名称 */
    @Excel(name = "供应品牌名称")
    private String brandName;

    /** 供应品牌描述 */
    @Excel(name = "供应品牌描述")
    private String brandDetail;

    public void setBrandId(Long brandId) 
    {
        this.brandId = brandId;
    }

    public Long getBrandId() 
    {
        return brandId;
    }
    public void setBrandName(String brandName) 
    {
        this.brandName = brandName;
    }

    public String getBrandName() 
    {
        return brandName;
    }
    public void setBrandDetail(String brandDetail) 
    {
        this.brandDetail = brandDetail;
    }

    public String getBrandDetail() 
    {
        return brandDetail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("brandId", getBrandId())
            .append("brandName", getBrandName())
            .append("brandDetail", getBrandDetail())
            .toString();
    }
}
