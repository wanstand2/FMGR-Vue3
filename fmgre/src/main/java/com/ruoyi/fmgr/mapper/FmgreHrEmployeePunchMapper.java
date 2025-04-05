package com.ruoyi.fmgr.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.fmgr.domain.FmgreHrEmployeePunch;
import com.ruoyi.fmgr.domain.FmgreHrEmployeeSalaryPayCalcBo;

/**
 * 员工打卡Mapper接口
 * 
 * @author terence
 * @date 2025-04-01
 */
public interface FmgreHrEmployeePunchMapper 
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
    public List<FmgreHrEmployeeSalaryPayCalcBo.PunchSummay> selectFmgreHrEmployeePunchSummaryList(@Param("employeeId") Long employeeId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
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
     * 删除员工打卡
     * 
     * @param punchId 员工打卡主键
     * @return 结果
     */
    public int deleteFmgreHrEmployeePunchByPunchId(Long punchId);

    /**
     * 批量删除员工打卡
     * 
     * @param punchIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFmgreHrEmployeePunchByPunchIds(Long[] punchIds);
}
