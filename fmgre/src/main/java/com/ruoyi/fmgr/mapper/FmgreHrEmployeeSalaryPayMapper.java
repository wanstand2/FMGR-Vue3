package com.ruoyi.fmgr.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.fmgr.domain.FmgreHrEmployeeSalaryPay;

/**
 * 员工薪酬发放Mapper接口
 * 
 * @author terence
 * @date 2025-04-02
 */
public interface FmgreHrEmployeeSalaryPayMapper 
{
    /**
     * 查询员工薪酬发放
     * 
     * @param payId 员工薪酬发放主键
     * @return 员工薪酬发放
     */
    public FmgreHrEmployeeSalaryPay selectFmgreHrEmployeeSalaryPayByPayId(Long payId);

    /**
     * 查询员工薪酬发放列表
     * 
     * @param fmgreHrEmployeeSalaryPay 员工薪酬发放
     * @return 员工薪酬发放集合
     */
    public List<FmgreHrEmployeeSalaryPay> selectFmgreHrEmployeeSalaryPayList(FmgreHrEmployeeSalaryPay fmgreHrEmployeeSalaryPay);

    /**
     * 新增员工薪酬发放
     * 
     * @param fmgreHrEmployeeSalaryPay 员工薪酬发放
     * @return 结果
     */
    public int insertFmgreHrEmployeeSalaryPay(FmgreHrEmployeeSalaryPay fmgreHrEmployeeSalaryPay);

    /**
     * 修改员工薪酬发放
     * 
     * @param fmgreHrEmployeeSalaryPay 员工薪酬发放
     * @return 结果
     */
    public int updateFmgreHrEmployeeSalaryPay(FmgreHrEmployeeSalaryPay fmgreHrEmployeeSalaryPay);

    /**
     * 删除员工薪酬发放
     * 
     * @param payId 员工薪酬发放主键
     * @return 结果
     */
    public int deleteFmgreHrEmployeeSalaryPayByPayId(Long payId);

    /**
     * 批量删除员工薪酬发放
     * 
     * @param payIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFmgreHrEmployeeSalaryPayByPayIds(Long[] payIds);

	public List<FmgreHrEmployeeSalaryPay> selectFmgreHrEmployeeSalaryPayByEDS(@Param("employeeIds") Long[] employeeIds, @Param("dates") Date[] dates);
}
