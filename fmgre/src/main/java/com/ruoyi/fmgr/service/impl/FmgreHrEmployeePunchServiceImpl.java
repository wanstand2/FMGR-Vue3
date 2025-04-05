package com.ruoyi.fmgr.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.fmgr.domain.FmgreHrEmployeePunch;
import com.ruoyi.fmgr.domain.FmgreHrEmployeeSalaryPayCalcBo;
import com.ruoyi.fmgr.mapper.FmgreHrEmployeePunchMapper;
import com.ruoyi.fmgr.service.IFmgreHrEmployeePunchService;

/**
 * 员工打卡Service业务层处理
 * 
 * @author terence
 * @date 2025-04-01
 */
@Service
public class FmgreHrEmployeePunchServiceImpl implements IFmgreHrEmployeePunchService 
{
    @Autowired
    private FmgreHrEmployeePunchMapper fmgreHrEmployeePunchMapper;

    /**
     * 查询员工打卡
     * 
     * @param punchId 员工打卡主键
     * @return 员工打卡
     */
    @Override
    public FmgreHrEmployeePunch selectFmgreHrEmployeePunchByPunchId(Long punchId)
    {
        return fmgreHrEmployeePunchMapper.selectFmgreHrEmployeePunchByPunchId(punchId);
    }

    /**
     * 查询员工打卡列表
     * 
     * @param fmgreHrEmployeePunch 员工打卡
     * @return 员工打卡
     */
    @Override
    public List<FmgreHrEmployeePunch> selectFmgreHrEmployeePunchList(FmgreHrEmployeePunch fmgreHrEmployeePunch)
    {
        return fmgreHrEmployeePunchMapper.selectFmgreHrEmployeePunchList(fmgreHrEmployeePunch);
    }

    public List<FmgreHrEmployeeSalaryPayCalcBo.PunchSummay> selectFmgreHrEmployeePunchSummaryList(Long employeeId, Date startTime, Date endTime) {
        return fmgreHrEmployeePunchMapper.selectFmgreHrEmployeePunchSummaryList(employeeId, startTime, endTime);
    }

    /**
     * 新增员工打卡
     * 
     * @param fmgreHrEmployeePunch 员工打卡
     * @return 结果
     */
    @Override
    public int insertFmgreHrEmployeePunch(FmgreHrEmployeePunch fmgreHrEmployeePunch)
    {
        return fmgreHrEmployeePunchMapper.insertFmgreHrEmployeePunch(fmgreHrEmployeePunch);
    }

    /**
     * 修改员工打卡
     * 
     * @param fmgreHrEmployeePunch 员工打卡
     * @return 结果
     */
    @Override
    public int updateFmgreHrEmployeePunch(FmgreHrEmployeePunch fmgreHrEmployeePunch)
    {
        return fmgreHrEmployeePunchMapper.updateFmgreHrEmployeePunch(fmgreHrEmployeePunch);
    }

    /**
     * 批量删除员工打卡
     * 
     * @param punchIds 需要删除的员工打卡主键
     * @return 结果
     */
    @Override
    public int deleteFmgreHrEmployeePunchByPunchIds(Long[] punchIds)
    {
        return fmgreHrEmployeePunchMapper.deleteFmgreHrEmployeePunchByPunchIds(punchIds);
    }

    /**
     * 删除员工打卡信息
     * 
     * @param punchId 员工打卡主键
     * @return 结果
     */
    @Override
    public int deleteFmgreHrEmployeePunchByPunchId(Long punchId)
    {
        return fmgreHrEmployeePunchMapper.deleteFmgreHrEmployeePunchByPunchId(punchId);
    }
}
