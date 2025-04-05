package com.ruoyi.fmgr.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.fmgr.domain.FmgreHrEmployeeSalaryPay;
import com.ruoyi.fmgr.mapper.FmgreHrEmployeeSalaryPayMapper;
import com.ruoyi.fmgr.service.IFmgreHrEmployeeSalaryPayService;

/**
 * 员工薪酬发放Service业务层处理
 * 
 * @author terence
 * @date 2025-04-02
 */
@Service
public class FmgreHrEmployeeSalaryPayServiceImpl implements IFmgreHrEmployeeSalaryPayService 
{
    @Autowired
    private FmgreHrEmployeeSalaryPayMapper fmgreHrEmployeeSalaryPayMapper;

    /**
     * 查询员工薪酬发放
     * 
     * @param payId 员工薪酬发放主键
     * @return 员工薪酬发放
     */
    @Override
    public FmgreHrEmployeeSalaryPay selectFmgreHrEmployeeSalaryPayByPayId(Long payId)
    {
        return fmgreHrEmployeeSalaryPayMapper.selectFmgreHrEmployeeSalaryPayByPayId(payId);
    }

    /**
     * 查询员工薪酬发放列表
     * 
     * @param fmgreHrEmployeeSalaryPay 员工薪酬发放
     * @return 员工薪酬发放
     */
    @Override
    public List<FmgreHrEmployeeSalaryPay> selectFmgreHrEmployeeSalaryPayList(FmgreHrEmployeeSalaryPay fmgreHrEmployeeSalaryPay)
    {
        return fmgreHrEmployeeSalaryPayMapper.selectFmgreHrEmployeeSalaryPayList(fmgreHrEmployeeSalaryPay);
    }

    /**
     * 新增员工薪酬发放
     * 
     * @param fmgreHrEmployeeSalaryPay 员工薪酬发放
     * @return 结果
     */
    @Override
    public int insertFmgreHrEmployeeSalaryPay(FmgreHrEmployeeSalaryPay fmgreHrEmployeeSalaryPay)
    {
        return fmgreHrEmployeeSalaryPayMapper.insertFmgreHrEmployeeSalaryPay(fmgreHrEmployeeSalaryPay);
    }

    /**
     * 修改员工薪酬发放
     * 
     * @param fmgreHrEmployeeSalaryPay 员工薪酬发放
     * @return 结果
     */
    @Override
    public int updateFmgreHrEmployeeSalaryPay(FmgreHrEmployeeSalaryPay fmgreHrEmployeeSalaryPay)
    {
        return fmgreHrEmployeeSalaryPayMapper.updateFmgreHrEmployeeSalaryPay(fmgreHrEmployeeSalaryPay);
    }

    /**
     * 批量删除员工薪酬发放
     * 
     * @param payIds 需要删除的员工薪酬发放主键
     * @return 结果
     */
    @Override
    public int deleteFmgreHrEmployeeSalaryPayByPayIds(Long[] payIds)
    {
        return fmgreHrEmployeeSalaryPayMapper.deleteFmgreHrEmployeeSalaryPayByPayIds(payIds);
    }

    /**
     * 删除员工薪酬发放信息
     * 
     * @param payId 员工薪酬发放主键
     * @return 结果
     */
    @Override
    public int deleteFmgreHrEmployeeSalaryPayByPayId(Long payId)
    {
        return fmgreHrEmployeeSalaryPayMapper.deleteFmgreHrEmployeeSalaryPayByPayId(payId);
    }

	@Override
	public List<FmgreHrEmployeeSalaryPay> selectFmgreHrEmployeeSalaryPayByEDS(Long[] employeeIds, Date[] dates) {
        System.out.println(dates);
        System.out.println(dates[0]);
		return fmgreHrEmployeeSalaryPayMapper.selectFmgreHrEmployeeSalaryPayByEDS(employeeIds, dates);
	}
}
