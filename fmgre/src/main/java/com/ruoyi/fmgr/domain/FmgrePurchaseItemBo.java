package com.ruoyi.fmgr.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class FmgrePurchaseItemBo extends FmgrePurchaseItem  {

    private String quoteUnitDictid;

    private BigDecimal quotePrice;

    private Long brandId;

    public String getQuoteUnitDictid() {
        return quoteUnitDictid;
    }

    public void setQuoteUnitDictid(String quoteUnitDictid) {
        this.quoteUnitDictid = quoteUnitDictid;
    }

    public BigDecimal getQuotePrice() {
        return quotePrice;
    }

    public void setQuotePrice(BigDecimal quotePrice) {
        this.quotePrice = quotePrice;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append(super.toString())
            .append("quoteUnitDictid", getQuoteUnitDictid())
            .append("quotePrice", getQuotePrice())
            .append("brandId", getBrandId())
            .toString();
    }
}
