package com.ruoyi.fmgr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.fmgr.domain.FmgreFinanceExpensesPay;
import com.ruoyi.fmgr.domain.FmgreFinanceExpensesPayBo;
import com.ruoyi.fmgr.mapper.FmgreFinanceExpensesPayMapper;
import com.ruoyi.fmgr.service.IFmgreFinanceExpensesPayService;

/**
 * 费用支付Service业务层处理
 * 
 * @author terence
 * @date 2025-04-01
 */
@Service
public class FmgreFinanceExpensesPayServiceImpl implements IFmgreFinanceExpensesPayService 
{
    @Autowired
    private FmgreFinanceExpensesPayMapper fmgreFinanceExpensesPayMapper;

    /**
     * 查询费用支付
     * 
     * @param payId 费用支付主键
     * @return 费用支付
     */
    @Override
    public FmgreFinanceExpensesPayBo selectFmgreFinanceExpensesPayByPayId(Long payId)
    {
        return fmgreFinanceExpensesPayMapper.selectFmgreFinanceExpensesPayByPayId(payId);
    }

    /**
     * 查询费用支付列表
     * 
     * @param fmgreFinanceExpensesPay 费用支付
     * @return 费用支付
     */
    @Override
    public List<FmgreFinanceExpensesPayBo> selectFmgreFinanceExpensesPayList(FmgreFinanceExpensesPay fmgreFinanceExpensesPay)
    {
        return fmgreFinanceExpensesPayMapper.selectFmgreFinanceExpensesPayList(fmgreFinanceExpensesPay);
    }

    /**
     * 新增费用支付
     * 
     * @param fmgreFinanceExpensesPay 费用支付
     * @return 结果
     */
    @Override
    public int insertFmgreFinanceExpensesPay(FmgreFinanceExpensesPay fmgreFinanceExpensesPay)
    {
        return fmgreFinanceExpensesPayMapper.insertFmgreFinanceExpensesPay(fmgreFinanceExpensesPay);
    }

    /**
     * 修改费用支付
     * 
     * @param fmgreFinanceExpensesPay 费用支付
     * @return 结果
     */
    @Override
    public int updateFmgreFinanceExpensesPay(FmgreFinanceExpensesPay fmgreFinanceExpensesPay)
    {
        return fmgreFinanceExpensesPayMapper.updateFmgreFinanceExpensesPay(fmgreFinanceExpensesPay);
    }

    /**
     * 批量删除费用支付
     * 
     * @param payIds 需要删除的费用支付主键
     * @return 结果
     */
    @Override
    public int deleteFmgreFinanceExpensesPayByPayIds(Long[] payIds)
    {
        return fmgreFinanceExpensesPayMapper.deleteFmgreFinanceExpensesPayByPayIds(payIds);
    }

    /**
     * 删除费用支付信息
     * 
     * @param payId 费用支付主键
     * @return 结果
     */
    @Override
    public int deleteFmgreFinanceExpensesPayByPayId(Long payId)
    {
        return fmgreFinanceExpensesPayMapper.deleteFmgreFinanceExpensesPayByPayId(payId);
    }
}
