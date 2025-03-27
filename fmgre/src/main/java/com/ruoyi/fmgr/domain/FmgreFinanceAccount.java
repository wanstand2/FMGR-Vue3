package com.ruoyi.fmgr.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;
/**
 * 银行账户对象 fmgre_finance_account
 * 
 * @author terence
 * @date 2025-02-24
 */
public class FmgreFinanceAccount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 账户id */
    private Long accountId;

    /** 账户拥有者id(私人) */
    @Excel(name = "账户拥有者id(私人)")
    private Long userId;

    /** 账户拥有者id(部门) */
    @Excel(name = "账户拥有者id(部门)")
    private Long deptId;

    /** 账户类型（字典) */
    @Excel(name = "账户类型", readConverterExp = "账户类型（字典)")
    private String accountDictid;

    /** 账户名称 */
    @Excel(name = "账户名称")
    private String accountName;

    /** 账号 */
    @Excel(name = "账号")
    private String accountNumber;

    /** 开户行id */
    @Excel(name = "开户行id")
    private Long bankId;

    /** 开户行信息 */
    @Excel(name = "开户行信息")
    private String bankInfo;

    /** 账户余额 */
    @Excel(name = "账户余额")
    private BigDecimal accountBalance;

    /** 账户显示别名 */
    @Excel(name = "账户显示别名")
    private String accountAlias;

    /** 账户显示名称 */
    @Excel(name = "账户显示名称")
    private String accountDisplay;

    public void setAccountId(Long accountId) 
    {
        this.accountId = accountId;
    }

    public Long getAccountId() 
    {
        return accountId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setAccountDictid(String accountDictid) 
    {
        this.accountDictid = accountDictid;
    }

    public String getAccountDictid() 
    {
        return accountDictid;
    }
    public void setAccountName(String accountName) 
    {
        this.accountName = accountName;
    }

    public String getAccountName() 
    {
        return accountName;
    }
    public void setAccountNumber(String accountNumber) 
    {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() 
    {
        return accountNumber;
    }
    public void setBankId(Long bankId) 
    {
        this.bankId = bankId;
    }

    public Long getBankId() 
    {
        return bankId;
    }
    public void setBankInfo(String bankInfo) 
    {
        this.bankInfo = bankInfo;
    }

    public String getBankInfo() 
    {
        return bankInfo;
    }

    public void setAccountBalance(BigDecimal accountBalance) 
    {
        this.accountBalance = accountBalance;
    }

    public BigDecimal getAccountBalance() 
    {
        return accountBalance;
    }

    public void setAccountAlias(String accountAlias) 
    {
        this.accountAlias = accountAlias;
    }   

    public String getAccountAlias() 
    {
        return accountAlias;
    }

    public void setAccountDisplay(String accountDisplay) 
    {
        this.accountDisplay = accountDisplay;
    }

    public String getAccountDisplay() 
    {
        return accountDisplay;
    }   

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("accountId", getAccountId())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("accountDictid", getAccountDictid())
            .append("accountName", getAccountName())
            .append("accountNumber", getAccountNumber())
            .append("bankId", getBankId())
            .append("bankInfo", getBankInfo())
            .append("accountBalance", getAccountBalance())
            .append("accountAlias", getAccountAlias())
            .append("accountDisplay", getAccountDisplay())
            .toString();
    }
}
