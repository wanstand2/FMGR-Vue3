package com.ruoyi.fmgr.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 费用类目对象 fmgre_finance_expense
 * 
 * @author terence
 * @date 2025-04-01
 */
public class FmgreFinanceExpense extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 费用类目id */
    @Excel(name = "费用类目id")
    private Long expenseId;

    /** 费用类目名称 */
    @Excel(name = "费用类目名称")
    private String expenseName;

    /** 费用类目代码 */
    @Excel(name = "费用类目代码")
    private String expenseCode;

    /** 费用类目类型 */
    @Excel(name = "费用类目类型")
    private String expenseTypeDictid;

    /** 费用类目说明 */
    @Excel(name = "费用类目说明")
    private String expenseComment;

    public void setExpenseId(Long expenseId) 
    {
        this.expenseId = expenseId;
    }

    public Long getExpenseId() 
    {
        return expenseId;
    }
    public void setExpenseName(String expenseName) 
    {
        this.expenseName = expenseName;
    }

    public String getExpenseName() 
    {
        return expenseName;
    }
    public void setExpenseCode(String expenseCode) 
    {
        this.expenseCode = expenseCode;
    }

    public String getExpenseCode() 
    {
        return expenseCode;
    }
    public void setExpenseTypeDictid(String expenseTypeDictid) 
    {
        this.expenseTypeDictid = expenseTypeDictid;
    }

    public String getExpenseTypeDictid() 
    {
        return expenseTypeDictid;
    }
    public void setExpenseComment(String expenseComment) 
    {
        this.expenseComment = expenseComment;
    }

    public String getExpenseComment() 
    {
        return expenseComment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("expenseId", getExpenseId())
            .append("expenseName", getExpenseName())
            .append("expenseCode", getExpenseCode())
            .append("expenseTypeDictid", getExpenseTypeDictid())
            .append("expenseComment", getExpenseComment())
            .toString();
    }
}
