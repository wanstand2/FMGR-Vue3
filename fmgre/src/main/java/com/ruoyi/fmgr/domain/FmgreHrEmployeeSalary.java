package com.ruoyi.fmgr.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 员工薪酬对象 fmgre_hr_employee_salary
 * 
 * @author terence
 * @date 2025-04-01
 */
public class FmgreHrEmployeeSalary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 人员id */
    @Excel(name = "人员id")
    private Long employeeId;

    /** 薪酬类型 */
    @Excel(name = "薪酬类型")
    private String salaryTypeDictid;

    /** 薪酬金额 */
    @Excel(name = "薪酬金额")
    private BigDecimal salaryAmount;

    /** 薪酬说明 */
    @Excel(name = "薪酬说明")
    private String salaryComment;

    public void setEmployeeId(Long employeeId) 
    {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() 
    {
        return employeeId;
    }
    public void setSalaryTypeDictid(String salaryTypeDictid) 
    {
        this.salaryTypeDictid = salaryTypeDictid;
    }

    public String getSalaryTypeDictid() 
    {
        return salaryTypeDictid;
    }
    public void setSalaryAmount(BigDecimal salaryAmount) 
    {
        this.salaryAmount = salaryAmount;
    }

    public BigDecimal getSalaryAmount() 
    {
        return salaryAmount;
    }
    public void setSalaryComment(String salaryComment) 
    {
        this.salaryComment = salaryComment;
    }

    public String getSalaryComment() 
    {
        return salaryComment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("employeeId", getEmployeeId())
            .append("salaryTypeDictid", getSalaryTypeDictid())
            .append("salaryAmount", getSalaryAmount())
            .append("salaryComment", getSalaryComment())
            .toString();
    }
}
