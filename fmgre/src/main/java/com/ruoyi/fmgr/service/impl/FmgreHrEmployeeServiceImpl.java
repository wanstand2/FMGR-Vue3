package com.ruoyi.fmgr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.fmgr.domain.FmgreHrEmployee;
import com.ruoyi.fmgr.mapper.FmgreHrEmployeeMapper;
import com.ruoyi.fmgr.service.IFmgreHrEmployeeService;

/**
 * 员工Service业务层处理
 * 
 * @author terence
 * @date 2025-04-01
 */
@Service
public class FmgreHrEmployeeServiceImpl implements IFmgreHrEmployeeService 
{
    @Autowired
    private FmgreHrEmployeeMapper fmgreHrEmployeeMapper;

    /**
     * 查询员工
     * 
     * @param employeeId 员工主键
     * @return 员工
     */
    @Override
    public FmgreHrEmployee selectFmgreHrEmployeeByEmployeeId(Long employeeId)
    {
        return fmgreHrEmployeeMapper.selectFmgreHrEmployeeByEmployeeId(employeeId);
    }

    /**
     * 查询员工列表
     * 
     * @param fmgreHrEmployee 员工
     * @return 员工
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<FmgreHrEmployee> selectFmgreHrEmployeeList(FmgreHrEmployee fmgreHrEmployee)
    {
        return fmgreHrEmployeeMapper.selectFmgreHrEmployeeList(fmgreHrEmployee);
    }

    /**
     * 新增员工
     * 
     * @param fmgreHrEmployee 员工
     * @return 结果
     */
    @Override
    public int insertFmgreHrEmployee(FmgreHrEmployee fmgreHrEmployee)
    {
        return fmgreHrEmployeeMapper.insertFmgreHrEmployee(fmgreHrEmployee);
    }

    /**
     * 修改员工
     * 
     * @param fmgreHrEmployee 员工
     * @return 结果
     */
    @Override
    public int updateFmgreHrEmployee(FmgreHrEmployee fmgreHrEmployee)
    {
        return fmgreHrEmployeeMapper.updateFmgreHrEmployee(fmgreHrEmployee);
    }

    /**
     * 批量删除员工
     * 
     * @param employeeIds 需要删除的员工主键
     * @return 结果
     */
    @Override
    public int deleteFmgreHrEmployeeByEmployeeIds(Long[] employeeIds)
    {
        return fmgreHrEmployeeMapper.deleteFmgreHrEmployeeByEmployeeIds(employeeIds);
    }

    /**
     * 删除员工信息
     * 
     * @param employeeId 员工主键
     * @return 结果
     */
    @Override
    public int deleteFmgreHrEmployeeByEmployeeId(Long employeeId)
    {
        return fmgreHrEmployeeMapper.deleteFmgreHrEmployeeByEmployeeId(employeeId);
    }
}
