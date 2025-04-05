package com.ruoyi.fmgr.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 员工薪酬发放对象 fmgre_hr_employee_salary_pay
 * 
 * @author terence
 * @date 2025-04-02
 */
public class FmgreHrEmployeeSalaryPay extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 薪资发放id */
    @Excel(name = "薪资发放id")
    private Long payId;

    /** 付款id */
    @Excel(name = "付款id")
    private Long paymentId;

    /** 人员id */
    @Excel(name = "人员id")
    private Long employeeId;

    /** 发放时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发放时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payTime;

    /** 计薪起时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计薪起时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 计薪结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计薪结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 计薪周期工作日数量 */
    @Excel(name = "计薪周期工作日数量")
    private BigDecimal pieriodWorkDay;

    /** 计薪周期实际工作数量 */
    @Excel(name = "计薪周期实际工作数量")
    private BigDecimal employeeWorkTime;

    /** 应发工资 */
    @Excel(name = "应发工资")
    private BigDecimal timeSalary;

    /** 调整工资 */
    @Excel(name = "调整工资")
    private BigDecimal adjustSalary;

    /** 发放名称 */
    @Excel(name = "发放名称")
    private String payName;

    /** 发放说明 */
    @Excel(name = "发放说明")
    private String payComment;

    public void setPayId(Long payId) 
    {
        this.payId = payId;
    }

    public Long getPayId() 
    {
        return payId;
    }
    public void setPaymentId(Long paymentId) 
    {
        this.paymentId = paymentId;
    }

    public Long getPaymentId() 
    {
        return paymentId;
    }
    public void setEmployeeId(Long employeeId) 
    {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() 
    {
        return employeeId;
    }
    public void setPayTime(Date payTime) 
    {
        this.payTime = payTime;
    }

    public Date getPayTime() 
    {
        return payTime;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setPieriodWorkDay(BigDecimal pieriodWorkDay) 
    {
        this.pieriodWorkDay = pieriodWorkDay;
    }

    public BigDecimal getPieriodWorkDay() 
    {
        return pieriodWorkDay;
    }
    public void setEmployeeWorkTime(BigDecimal employeeWorkTime) 
    {
        this.employeeWorkTime = employeeWorkTime;
    }

    public BigDecimal getEmployeeWorkTime() 
    {
        return employeeWorkTime;
    }
    public void setTimeSalary(BigDecimal timeSalary) 
    {
        this.timeSalary = timeSalary;
    }

    public BigDecimal getTimeSalary() 
    {
        return timeSalary;
    }
    public void setAdjustSalary(BigDecimal adjustSalary) 
    {
        this.adjustSalary = adjustSalary;
    }

    public BigDecimal getAdjustSalary() 
    {
        return adjustSalary;
    }
    public void setPayName(String payName) 
    {
        this.payName = payName;
    }

    public String getPayName() 
    {
        return payName;
    }
    public void setPayComment(String payComment) 
    {
        this.payComment = payComment;
    }

    public String getPayComment() 
    {
        return payComment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("payId", getPayId())
            .append("paymentId", getPaymentId())
            .append("employeeId", getEmployeeId())
            .append("payTime", getPayTime())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("pieriodWorkDay", getPieriodWorkDay())
            .append("employeeWorkTime", getEmployeeWorkTime())
            .append("timeSalary", getTimeSalary())
            .append("adjustSalary", getAdjustSalary())
            .append("payName", getPayName())
            .append("payComment", getPayComment())
            .toString();
    }
}
