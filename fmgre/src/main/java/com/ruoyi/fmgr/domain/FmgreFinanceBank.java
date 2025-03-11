package com.ruoyi.fmgr.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 银行对象 fmgre_finance_bank
 * 
 * @author terence
 * @date 2025-02-24
 */
public class FmgreFinanceBank extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 银行id */
    private Long bankId;

    /** 银行名称 */
    @Excel(name = "银行名称")
    private String bankName;

    /** 银行描述 */
    @Excel(name = "银行描述")
    private String bankDesc;

    /** SWIFT代码 */
    @Excel(name = "SWIFT代码")
    private String swiftCode;

    public void setbankId(Long bankId) 
    {
        this.bankId = bankId;
    }

    public Long getbankId() 
    {
        return bankId;
    }
    public void setBankName(String bankName) 
    {
        this.bankName = bankName;
    }

    public String getBankName() 
    {
        return bankName;
    }
    public void setBankDesc(String bankDesc) 
    {
        this.bankDesc = bankDesc;
    }

    public String getBankDesc() 
    {
        return bankDesc;
    }
    public void setSwiftCode(String swiftCode) 
    {
        this.swiftCode = swiftCode;
    }

    public String getSwiftCode() 
    {
        return swiftCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("bankId", getbankId())
            .append("bankName", getBankName())
            .append("bankDesc", getBankDesc())
            .append("swiftCode", getSwiftCode())
            .toString();
    }
}
