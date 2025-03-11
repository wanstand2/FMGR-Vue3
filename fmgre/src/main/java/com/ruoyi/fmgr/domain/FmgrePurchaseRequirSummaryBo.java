package com.ruoyi.fmgr.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class FmgrePurchaseRequirSummaryBo {

    private Long requirId;
    
    private Integer itemNum;

    private BigDecimal itemTotalPrice;

    private Integer orderNum;

    private Integer supplierNum;

    public Long getRequirId() {
        return requirId;
    }

    public void setRequirId(Long requirId) {
        this.requirId = requirId;
    }

    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    public BigDecimal getItemTotalPrice() {
        return itemTotalPrice;
    }

    public void setItemTotalPrice(BigDecimal itemTotalPrice) {
        this.itemTotalPrice = itemTotalPrice;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getSupplierNum() {
        return supplierNum;
    }

    public void setSupplierNum(Integer supplierNum) {
        this.supplierNum = supplierNum;
    }
    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("requirId", getRequirId())
            .append("itemNum", getItemNum())
            .append("itemTotalPrice", getItemTotalPrice())
            .append("orderNum", getOrderNum())
            .append("supplierNum", getSupplierNum())
            .toString();
    }
}
