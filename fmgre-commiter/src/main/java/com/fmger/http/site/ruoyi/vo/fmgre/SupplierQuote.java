package com.fmger.http.site.ruoyi.vo.fmgre;

import java.math.BigDecimal;

import com.ruoyi.fmgr.domain.FmgreSupplierQuote;

public class SupplierQuote extends FmgreSupplierQuote {

    public String materaiCode;
    public String materaiName;
    public String supplierCode;
    public String supplierName;
    public String quoteUnit;


    public String brandName;

    public BigDecimal salesPrice;

    public String ratio;

/*
    public Date quotaTime;

    public BigDecimal quotaPrice;

    public BigDecimal packSize;
    
    public String quotaComment;

    public Long materailId;

    public Long supplierId;

    public Long supplierBrandId;

    public String packUnitDictid;

    public BigDecimal packNum;

    public String subPackUnitDictid;

    public Long subPackNum;

    public BigDecimal subPackSize;
 */

	@Override
	public String toString() {
		return "SupplierQuote [materaiCode=" + materaiCode + ", materaiName=" + materaiName + ", supplierCode="
				+ supplierCode + ", supplierName=" + supplierName + ", quoteUnit=" + quoteUnit + ", quotaTime="
				+ getQuotaTime() + ", quotaPrice=" + getQuotaPrice() + ", salesPrice=" + salesPrice + ", ratio=" + ratio
				+ ", quotaComment=" + getQuotaComment() + ", materailId=" + getMaterailId() + ", supplierId=" + getSupplierId()
				+ ", supplierBrandId=" + getSupplierBrandId() + ", packUnitDictid=" + getPackUnitDictid() + ", packNum=" + getPackNum()
				+ ", packSize=" + getPackSize() + ", subPackUnitDictid=" + getSubPackUnitDictid() + ", subPackNum=" + getSubPackNum()
				+ ", subPackSize=" + getSubPackSize() + "]";
	}

}
