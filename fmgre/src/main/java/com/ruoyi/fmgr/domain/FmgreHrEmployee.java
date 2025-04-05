package com.ruoyi.fmgr.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 员工对象 fmgre_hr_employee
 * 
 * @author terence
 * @date 2025-04-01
 */
public class FmgreHrEmployee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 人员id */
    @Excel(name = "人员id")
    private Long employeeId;

    /** 系统用户（若有账号） */
    @Excel(name = "系统用户", readConverterExp = "若有账号")
    private Long userId;

    /** 人员工号 */
    @Excel(name = "人员工号")
    private String employeeCode;

    /** 人员名称 */
    @Excel(name = "人员名称")
    private String employeeName;

    /** 人员性别（0男 1女 2未知） */
    @Excel(name = "人员性别", readConverterExp = "0=男,1=女,2=未知")
    private String employeeSex;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String mobileNumber;

    /** 人员类型(雇员/小时工等) */
    @Excel(name = "人员类型(雇员/小时工等)")
    private String employeeTypeDictid;

    /** 归属门店 */
    @Excel(name = "归属门店")
    private Long deptId;

    /** 加入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "加入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date joinTime;

    /** 离职时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "离职时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date resignTime;

    private FmgreHrEmployeeSalary salary;
    
    public void setEmployeeId(Long employeeId) 
    {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() 
    {
        return employeeId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setEmployeeCode(String employeeCode) 
    {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeCode() 
    {
        return employeeCode;
    }
    public void setEmployeeName(String employeeName) 
    {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() 
    {
        return employeeName;
    }
    public void setEmployeeSex(String employeeSex) 
    {
        this.employeeSex = employeeSex;
    }

    public String getEmployeeSex() 
    {
        return employeeSex;
    }
    public void setMobileNumber(String mobileNumber) 
    {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() 
    {
        return mobileNumber;
    }
    public void setEmployeeTypeDictid(String employeeTypeDictid) 
    {
        this.employeeTypeDictid = employeeTypeDictid;
    }

    public String getEmployeeTypeDictid() 
    {
        return employeeTypeDictid;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setJoinTime(Date joinTime) 
    {
        this.joinTime = joinTime;
    }

    public Date getJoinTime() 
    {
        return joinTime;
    }
    public void setResignTime(Date resignTime) 
    {
        this.resignTime = resignTime;
    }

    public Date getResignTime() 
    {
        return resignTime;
    }

    public FmgreHrEmployeeSalary getSalary() {
		return salary;
	}

	public void setSalary(FmgreHrEmployeeSalary salary) {
		this.salary = salary;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("employeeId", getEmployeeId())
            .append("userId", getUserId())
            .append("employeeCode", getEmployeeCode())
            .append("employeeName", getEmployeeName())
            .append("employeeSex", getEmployeeSex())
            .append("mobileNumber", getMobileNumber())
            .append("employeeTypeDictid", getEmployeeTypeDictid())
            .append("deptId", getDeptId())
            .append("joinTime", getJoinTime())
            .append("resignTime", getResignTime())
            .toString();
    }
}
