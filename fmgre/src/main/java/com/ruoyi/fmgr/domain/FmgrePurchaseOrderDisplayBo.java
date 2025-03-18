package com.ruoyi.fmgr.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class FmgrePurchaseOrderDisplayBo extends FmgrePurchaseOrder {

    private List<FmgrePurchaseItemDisplayBo> items;

    private FmgreSupplier supplier;

    private FmgreFinancePaymentDisplayBo payment;

    public List<FmgrePurchaseItemDisplayBo> getItems() {
        return items;
    }

    public void setItems(List<FmgrePurchaseItemDisplayBo> items) {
        this.items = items;
    }

    public FmgreSupplier getSupplier() {
        return supplier;
    }

    public void setSupplier(FmgreSupplier supplier) {
        this.supplier = supplier;
    }

    public FmgreFinancePaymentDisplayBo getPayment() {
        return payment;
    }

    public void setPayment(FmgreFinancePaymentDisplayBo payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append(super.toString())
            .append("items", getItems())
            .append("supplier", getSupplier())
            .append("payment", getPayment())
            .toString();
    }

    public FmgrePurchaseOrderDisplayBo() {
        super();
    }

    public FmgrePurchaseOrderDisplayBo(FmgrePurchaseOrder order) {
        super();
        this.setOrderId(order.getOrderId());
        this.setOrderCode(order.getOrderCode());
        this.setOrderTime(order.getOrderTime());
        this.setOrderTotalPrice(order.getOrderTotalPrice());
        this.setOrderComment(order.getOrderComment());
        this.setPaymentId(order.getPaymentId());
        this.setSupplierId(order.getSupplierId());
        this.setUserId(order.getUserId());
        this.setDeliveryDictid(order.getDeliveryDictid());
    }
}
