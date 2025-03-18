package com.ruoyi.fmgr.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class FmgrePurchaseItemDisplayBo extends FmgrePurchaseItem {

    private FmgreMaterial materail;

    public FmgreMaterial getMaterail() {
        return materail;
    }

    public void setMaterail(FmgreMaterial materail) {
        this.materail = materail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append(super.toString())
            .append("materail", getMaterail())
            .toString();
    }

    public FmgrePurchaseItemDisplayBo() {
        super();
    }

    public FmgrePurchaseItemDisplayBo(FmgrePurchaseItem item) {
        super();
        this.setOrderId(item.getOrderId());
        this.setRequirId(item.getRequirId());
        this.setMaterailId(item.getMaterailId());
        this.setRequirNum(item.getRequirNum());
        this.setRequirUnitDictid(item.getRequirUnitDictid());
        this.setOrderUnitDictid(item.getOrderUnitDictid());
        this.setOrderNum(item.getOrderNum());
        this.setOrderAmount(item.getOrderAmount());
        this.setQuotaId(item.getQuotaId());
        this.setSupplierId(item.getSupplierId());
        this.setItemTotalPrice(item.getItemTotalPrice());
    }
}
