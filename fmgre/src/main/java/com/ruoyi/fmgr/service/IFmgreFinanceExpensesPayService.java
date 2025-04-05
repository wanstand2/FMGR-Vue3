package com.ruoyi.fmgr.service;

import java.util.List;

import com.ruoyi.fmgr.domain.FmgreFinanceExpensesPay;
import com.ruoyi.fmgr.domain.FmgreFinanceExpensesPayBo;

/**
 * 费用支付Service接口
 * 
 * @author terence
 * @date 2025-04-01
 */
public interface IFmgreFinanceExpensesPayService 
{
    /**
     * 查询费用支付
     * 
     * @param payId 费用支付主键
     * @return 费用支付
     */
    public FmgreFinanceExpensesPayBo selectFmgreFinanceExpensesPayByPayId(Long payId);

    /**
     * 查询费用支付列表
     * 
     * @param fmgreFinanceExpensesPay 费用支付
     * @return 费用支付集合
     */
    public List<FmgreFinanceExpensesPayBo> selectFmgreFinanceExpensesPayList(FmgreFinanceExpensesPay fmgreFinanceExpensesPay);

    /**
     * 新增费用支付
     * 
     * @param fmgreFinanceExpensesPay 费用支付
     * @return 结果
     */
    public int insertFmgreFinanceExpensesPay(FmgreFinanceExpensesPay fmgreFinanceExpensesPay);

    /**
     * 修改费用支付
     * 
     * @param fmgreFinanceExpensesPay 费用支付
     * @return 结果
     */
    public int updateFmgreFinanceExpensesPay(FmgreFinanceExpensesPay fmgreFinanceExpensesPay);

    /**
     * 批量删除费用支付
     * 
     * @param payIds 需要删除的费用支付主键集合
     * @return 结果
     */
    public int deleteFmgreFinanceExpensesPayByPayIds(Long[] payIds);

    /**
     * 删除费用支付信息
     * 
     * @param payId 费用支付主键
     * @return 结果
     */
    public int deleteFmgreFinanceExpensesPayByPayId(Long payId);
}
