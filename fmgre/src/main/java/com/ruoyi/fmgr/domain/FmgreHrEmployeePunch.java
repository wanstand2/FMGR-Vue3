package com.ruoyi.fmgr.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 员工打卡对象 fmgre_hr_employee_punch
 * 
 * @author terence
 * @date 2025-04-01
 */
public class FmgreHrEmployeePunch extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 打卡id */
    @Excel(name = "打卡id")
    private Long punchId;

    /** 人员id */
    @Excel(name = "人员id")
    private Long employeeId;

    /** 打卡上班时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "打卡上班时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date punchInTime;

    /** 打卡下班时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "打卡下班时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date punchOutTime;

    /** 工作时长 */
    @Excel(name = "工作时长")
    private BigDecimal workDuration;

    /** 打卡类型(正常/加班/补卡等) */
    @Excel(name = "打卡类型(正常/加班/补卡等)")
    private String punchTypeDictid;

    /** 打卡说明 */
    @Excel(name = "打卡说明")
    private String punchComment;

    public void setPunchId(Long punchId) 
    {
        this.punchId = punchId;
    }

    public Long getPunchId() 
    {
        return punchId;
    }
    public void setEmployeeId(Long employeeId) 
    {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() 
    {
        return employeeId;
    }
    public void setPunchInTime(Date punchInTime) 
    {
        this.punchInTime = punchInTime;
    }

    public Date getPunchInTime() 
    {
        return punchInTime;
    }
    public void setPunchOutTime(Date punchOutTime) 
    {
        this.punchOutTime = punchOutTime;
    }

    public Date getPunchOutTime() 
    {
        return punchOutTime;
    }
    public void setWorkDuration(BigDecimal workDuration) 
    {
        this.workDuration = workDuration;
    }

    public BigDecimal getWorkDuration() 
    {
        return workDuration;
    }
    public void setPunchTypeDictid(String punchTypeDictid) 
    {
        this.punchTypeDictid = punchTypeDictid;
    }

    public String getPunchTypeDictid() 
    {
        return punchTypeDictid;
    }
    public void setPunchComment(String punchComment) 
    {
        this.punchComment = punchComment;
    }

    public String getPunchComment() 
    {
        return punchComment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("punchId", getPunchId())
            .append("employeeId", getEmployeeId())
            .append("punchInTime", getPunchInTime())
            .append("punchOutTime", getPunchOutTime())
            .append("workDuration", getWorkDuration())
            .append("punchTypeDictid", getPunchTypeDictid())
            .append("punchComment", getPunchComment())
            .toString();
    }
}
