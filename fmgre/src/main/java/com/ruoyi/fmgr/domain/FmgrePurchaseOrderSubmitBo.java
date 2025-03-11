package com.ruoyi.fmgr.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class FmgrePurchaseOrderSubmitBo extends FmgrePurchaseOrder {

    private List<FmgrePurchaseItemBo> items;

    private Long accountId;

    public List<FmgrePurchaseItemBo> getItems() {
        return items;
    }

    public void setItems(List<FmgrePurchaseItemBo> items) {
        this.items = items;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append(super.toString())
            .append("fmgrePurchaseItems", getItems())
            .append("accountId", getAccountId())
            .toString();
    }
}

