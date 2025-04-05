package com.ruoyi.fmgr.mapper;

import java.util.List;
import com.ruoyi.fmgr.domain.FmgreFinanceExpense;

/**
 * 费用类目Mapper接口
 * 
 * @author terence
 * @date 2025-04-01
 */
public interface FmgreFinanceExpenseMapper 
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
     * 删除费用类目
     * 
     * @param expenseId 费用类目主键
     * @return 结果
     */
    public int deleteFmgreFinanceExpenseByExpenseId(Long expenseId);

    /**
     * 批量删除费用类目
     * 
     * @param expenseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFmgreFinanceExpenseByExpenseIds(Long[] expenseIds);
}
