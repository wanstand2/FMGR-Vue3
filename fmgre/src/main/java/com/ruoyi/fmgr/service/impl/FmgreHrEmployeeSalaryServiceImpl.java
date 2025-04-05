package com.ruoyi.fmgr.service.impl;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fmgr.mapper.FmgreHrEmployeeSalaryMapper;
import com.ruoyi.fmgr.domain.FmgreHrEmployeeSalary;
import com.ruoyi.fmgr.service.IFmgreHrEmployeeSalaryService;

/**
 * 员工薪酬Service业务层处理
 * 
 * @author terence
 * @date 2025-04-01
 */
@Service
public class FmgreHrEmployeeSalaryServiceImpl implements IFmgreHrEmployeeSalaryService 
{
    @Autowired
    private FmgreHrEmployeeSalaryMapper fmgreHrEmployeeSalaryMapper;

    /**
     * 查询员工薪酬
     * 
     * @param employeeId 员工薪酬主键
     * @return 员工薪酬
     */
    @Override
    public FmgreHrEmployeeSalary selectFmgreHrEmployeeSalaryByEmployeeId(Long employeeId)
    {
        return fmgreHrEmployeeSalaryMapper.selectFmgreHrEmployeeSalaryByEmployeeId(employeeId);
    }

    /**
     * 查询员工薪酬列表
     * 
     * @param fmgreHrEmployeeSalary 员工薪酬
     * @return 员工薪酬
     */
    @Override
    public List<FmgreHrEmployeeSalary> selectFmgreHrEmployeeSalaryList(FmgreHrEmployeeSalary fmgreHrEmployeeSalary)
    {
        return fmgreHrEmployeeSalaryMapper.selectFmgreHrEmployeeSalaryList(fmgreHrEmployeeSalary);
    }

    public List<FmgreHrEmployeeSalary> selectFmgreHrEmployeeSalaryListByIds(Collection<Long> ids) {
        return fmgreHrEmployeeSalaryMapper.selectFmgreHrEmployeeSalaryListByIds(ids);
    }

    /**
     * 新增员工薪酬
     * 
     * @param fmgreHrEmployeeSalary 员工薪酬
     * @return 结果
     */
    @Override
    public int insertFmgreHrEmployeeSalary(FmgreHrEmployeeSalary fmgreHrEmployeeSalary)
    {
        return fmgreHrEmployeeSalaryMapper.insertFmgreHrEmployeeSalary(fmgreHrEmployeeSalary);
    }

    /**
     * 修改员工薪酬
     * 
     * @param fmgreHrEmployeeSalary 员工薪酬
     * @return 结果
     */
    @Override
    public int updateFmgreHrEmployeeSalary(FmgreHrEmployeeSalary fmgreHrEmployeeSalary)
    {
        return fmgreHrEmployeeSalaryMapper.updateFmgreHrEmployeeSalary(fmgreHrEmployeeSalary);
    }

    /**
     * 批量删除员工薪酬
     * 
     * @param employeeIds 需要删除的员工薪酬主键
     * @return 结果
     */
    @Override
    public int deleteFmgreHrEmployeeSalaryByEmployeeIds(Long[] employeeIds)
    {
        return fmgreHrEmployeeSalaryMapper.deleteFmgreHrEmployeeSalaryByEmployeeIds(employeeIds);
    }

    /**
     * 删除员工薪酬信息
     * 
     * @param employeeId 员工薪酬主键
     * @return 结果
     */
    @Override
    public int deleteFmgreHrEmployeeSalaryByEmployeeId(Long employeeId)
    {
        return fmgreHrEmployeeSalaryMapper.deleteFmgreHrEmployeeSalaryByEmployeeId(employeeId);
    }
}
