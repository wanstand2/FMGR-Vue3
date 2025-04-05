package com.ruoyi.fmgr.service;

import java.util.List;
import com.ruoyi.fmgr.domain.FmgreFinanceExpense;

/**
 * 费用类目Service接口
 * 
 * @author terence
 * @date 2025-04-01
 */
public interface IFmgreFinanceExpenseService 
{
    /**
     * 查询费用类目
     * 
     * @param expenseId 费用类目主键
     * @return 费用类目
     */
    public FmgreFinanceExpense selectFmgreFinanceExpenseByExpenseId(Long expenseId);

    /**
     * 查询费用类目列表
     * 
     * @param fmgreFinanceExpense 费用类目
     * @return 费用类目集合
     */
    public List<FmgreFinanceExpense> selectFmgreFinanceExpenseList(FmgreFinanceExpense fmgreFinanceExpense);

    /**
     * 新增费用类目
     * 
     * @param fmgreFinanceExpense 费用类目
     * @return 结果
     */
    public int insertFmgreFinanceExpense(FmgreFinanceExpense fmgreFinanceExpense);

    /**
     * 修改费用类目
     * 
     * @param fmgreFinanceExpense 费用类目
     * @return 结果
     */
    public int updateFmgreFinanceExpense(FmgreFinanceExpense fmgreFinanceExpense);

    /**
     * 批量删除费用类目
     * 
     * @param expenseIds 需要删除的费用类目主键集合
     * @return 结果
     */
    public int deleteFmgreFinanceExpenseByExpenseIds(Long[] expenseIds);

    /**
     * 删除费用类目信息
     * 
     * @param expenseId 费用类目主键
     * @return 结果
     */
    public int deleteFmgreFinanceExpenseByExpenseId(Long expenseId);
}
