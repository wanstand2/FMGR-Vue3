package com.ruoyi.fmgr.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 银行账户余额对象 fmgre_finance_account_balance
 * 
 * @author terence
 * @date 2025-02-24
 */
public class FmgreFinanceAccountBalance extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 账户id */
    private Long accountId;

    /** 账户余额 */
    @Excel(name = "账户余额")
    private BigDecimal accountBalance;

    public void setAccountId(Long accountId) 
    {
        this.accountId = accountId;
    }

    public Long getAccountId() 
    {
        return accountId;
    }
    public void setAccountBalance(BigDecimal accountBalance) 
    {
        this.accountBalance = accountBalance;
    }

    public BigDecimal getAccountBalance() 
    {
        return accountBalance;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("accountId", getAccountId())
            .append("accountBalance", getAccountBalance())
            .toString();
    }
}
