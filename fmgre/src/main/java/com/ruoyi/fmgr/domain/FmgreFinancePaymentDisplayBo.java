package com.ruoyi.fmgr.domain;

import com.ruoyi.common.core.domain.entity.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class FmgreFinancePaymentDisplayBo extends FmgreFinancePayment {

    private SysUser user;

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
        .append(super.toString())
        .append("user", getUser().toString())
        .toString();
    }
    
    public FmgreFinancePaymentDisplayBo() {
        super();
    }

    public FmgreFinancePaymentDisplayBo(FmgreFinancePayment payment) {
        super();
        this.setPaymentId(payment.getPaymentId());
        this.setPaymentTime(payment.getPaymentTime());
        this.setPaymentAmount(payment.getPaymentAmount());
        this.setPaymentComment(payment.getPaymentComment());
        this.setOutAccId(payment.getOutAccId());
        this.setInAccId(payment.getInAccId());
        this.setUserId(payment.getUserId());
        this.setOrderId(payment.getOrderId());
    }
}
