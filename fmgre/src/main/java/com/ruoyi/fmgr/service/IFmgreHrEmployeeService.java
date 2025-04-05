package com.ruoyi.fmgr.service;

import java.util.List;
import com.ruoyi.fmgr.domain.FmgreHrEmployee;

/**
 * 员工Service接口
 * 
 * @author terence
 * @date 2025-04-01
 */
public interface IFmgreHrEmployeeService 
{
    /**
     * 查询员工
     * 
     * @param employeeId 员工主键
     * @return 员工
     */
    public FmgreHrEmployee selectFmgreHrEmployeeByEmployeeId(Long employeeId);

    /**
     * 查询员工列表
     * 
     * @param fmgreHrEmployee 员工
     * @return 员工集合
     */
    public List<FmgreHrEmployee> selectFmgreHrEmployeeList(FmgreHrEmployee fmgreHrEmployee);

    /**
     * 新增员工
     * 
     * @param fmgreHrEmployee 员工
     * @return 结果
     */
    public int insertFmgreHrEmployee(FmgreHrEmployee fmgreHrEmployee);

    /**
     * 修改员工
     * 
     * @param fmgreHrEmployee 员工
     * @return 结果
     */
    public int updateFmgreHrEmployee(FmgreHrEmployee fmgreHrEmployee);

    /**
     * 批量删除员工
     * 
     * @param employeeIds 需要删除的员工主键集合
     * @return 结果
     */
    public int deleteFmgreHrEmployeeByEmployeeIds(Long[] employeeIds);

    /**
     * 删除员工信息
     * 
     * @param employeeId 员工主键
     * @return 结果
     */
    public int deleteFmgreHrEmployeeByEmployeeId(Long employeeId);
}
