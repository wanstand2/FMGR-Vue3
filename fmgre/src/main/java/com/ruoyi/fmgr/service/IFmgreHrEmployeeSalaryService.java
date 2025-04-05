package com.ruoyi.fmgr.service;

import java.util.Collection;
import java.util.List;
import com.ruoyi.fmgr.domain.FmgreHrEmployeeSalary;

/**
 * 员工薪酬Service接口
 * 
 * @author terence
 * @date 2025-04-01
 */
public interface IFmgreHrEmployeeSalaryService 
{
    /**
     * 查询员工薪酬
     * 
     * @param employeeId 员工薪酬主键
     * @return 员工薪酬
     */
    public FmgreHrEmployeeSalary selectFmgreHrEmployeeSalaryByEmployeeId(Long employeeId);

    /**
     * 查询员工薪酬列表
     * 
     * @param fmgreHrEmployeeSalary 员工薪酬
     * @return 员工薪酬集合
     */
    public List<FmgreHrEmployeeSalary> selectFmgreHrEmployeeSalaryList(FmgreHrEmployeeSalary fmgreHrEmployeeSalary);
    public List<FmgreHrEmployeeSalary> selectFmgreHrEmployeeSalaryListByIds(Collection<Long> ids);

    /**
     * 新增员工薪酬
     * 
     * @param fmgreHrEmployeeSalary 员工薪酬
     * @return 结果
     */
    public int insertFmgreHrEmployeeSalary(FmgreHrEmployeeSalary fmgreHrEmployeeSalary);

    /**
     * 修改员工薪酬
     * 
     * @param fmgreHrEmployeeSalary 员工薪酬
     * @return 结果
     */
    public int updateFmgreHrEmployeeSalary(FmgreHrEmployeeSalary fmgreHrEmployeeSalary);

    /**
     * 批量删除员工薪酬
     * 
     * @param employeeIds 需要删除的员工薪酬主键集合
     * @return 结果
     */
    public int deleteFmgreHrEmployeeSalaryByEmployeeIds(Long[] employeeIds);

    /**
     * 删除员工薪酬信息
     * 
     * @param employeeId 员工薪酬主键
     * @return 结果
     */
    public int deleteFmgreHrEmployeeSalaryByEmployeeId(Long employeeId);
}
