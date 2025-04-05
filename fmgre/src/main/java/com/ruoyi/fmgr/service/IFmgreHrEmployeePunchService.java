package com.ruoyi.fmgr.service;

import java.util.Date;
import java.util.List;
import com.ruoyi.fmgr.domain.FmgreHrEmployeePunch;
import com.ruoyi.fmgr.domain.FmgreHrEmployeeSalaryPayCalcBo;

/**
 * 员工打卡Service接口
 * 
 * @author terence
 * @date 2025-04-01
 */
public interface IFmgreHrEmployeePunchService 
{
    /**
     * 查询员工打卡
     * 
     * @param punchId 员工打卡主键
     * @return 员工打卡
     */
    public FmgreHrEmployeePunch selectFmgreHrEmployeePunchByPunchId(Long punchId);

    /**
     * 查询员工打卡列表
     * 
     * @param fmgreHrEmployeePunch 员工打卡
     * @return 员工打卡集合
     */
    public List<FmgreHrEmployeePunch> selectFmgreHrEmployeePunchList(FmgreHrEmployeePunch fmgreHrEmployeePunch);
    public List<FmgreHrEmployeeSalaryPayCalcBo.PunchSummay> selectFmgreHrEmployeePunchSummaryList(Long employeeId, Date startTime, Date endTime);

    /**
     * 新增员工打卡
     * 
     * @param fmgreHrEmployeePunch 员工打卡
     * @return 结果
     */
    public int insertFmgreHrEmployeePunch(FmgreHrEmployeePunch fmgreHrEmployeePunch);

    /**
     * 修改员工打卡
     * 
     * @param fmgreHrEmployeePunch 员工打卡
     * @return 结果
     */
    public int updateFmgreHrEmployeePunch(FmgreHrEmployeePunch fmgreHrEmployeePunch);

    /**
     * 批量删除员工打卡
     * 
     * @param punchIds 需要删除的员工打卡主键集合
     * @return 结果
     */
    public int deleteFmgreHrEmployeePunchByPunchIds(Long[] punchIds);

    /**
     * 删除员工打卡信息
     * 
     * @param punchId 员工打卡主键
     * @return 结果
     */
    public int deleteFmgreHrEmployeePunchByPunchId(Long punchId);
}
