package com.ruoyi.fmgr.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fmgr.mapper.FmgreFinanceExpenseMapper;
import com.ruoyi.fmgr.domain.FmgreFinanceExpense;
import com.ruoyi.fmgr.service.IFmgreFinanceExpenseService;

/**
 * 费用类目Service业务层处理
 * 
 * @author terence
 * @date 2025-04-01
 */
@Service
public class FmgreFinanceExpenseServiceImpl implements IFmgreFinanceExpenseService 
{
    @Autowired
    private FmgreFinanceExpenseMapper fmgreFinanceExpenseMapper;

    /**
     * 查询费用类目
     * 
     * @param expenseId 费用类目主键
     * @return 费用类目
     */
    @Override
    public FmgreFinanceExpense selectFmgreFinanceExpenseByExpenseId(Long expenseId)
    {
        return fmgreFinanceExpenseMapper.selectFmgreFinanceExpenseByExpenseId(expenseId);
    }

    /**
     * 查询费用类目列表
     * 
     * @param fmgreFinanceExpense 费用类目
     * @return 费用类目
     */
    @Override
    public List<FmgreFinanceExpense> selectFmgreFinanceExpenseList(FmgreFinanceExpense fmgreFinanceExpense)
    {
        return fmgreFinanceExpenseMapper.selectFmgreFinanceExpenseList(fmgreFinanceExpense);
    }

    /**
     * 新增费用类目
     * 
     * @param fmgreFinanceExpense 费用类目
     * @return 结果
     */
    @Override
    public int insertFmgreFinanceExpense(FmgreFinanceExpense fmgreFinanceExpense)
    {
        return fmgreFinanceExpenseMapper.insertFmgreFinanceExpense(fmgreFinanceExpense);
    }

    /**
     * 修改费用类目
     * 
     * @param fmgreFinanceExpense 费用类目
     * @return 结果
     */
    @Override
    public int updateFmgreFinanceExpense(FmgreFinanceExpense fmgreFinanceExpense)
    {
        return fmgreFinanceExpenseMapper.updateFmgreFinanceExpense(fmgreFinanceExpense);
    }

    /**
     * 批量删除费用类目
     * 
     * @param expenseIds 需要删除的费用类目主键
     * @return 结果
     */
    @Override
    public int deleteFmgreFinanceExpenseByExpenseIds(Long[] expenseIds)
    {
        return fmgreFinanceExpenseMapper.deleteFmgreFinanceExpenseByExpenseIds(expenseIds);
    }

    /**
     * 删除费用类目信息
     * 
     * @param expenseId 费用类目主键
     * @return 结果
     */
    @Override
    public int deleteFmgreFinanceExpenseByExpenseId(Long expenseId)
    {
        return fmgreFinanceExpenseMapper.deleteFmgreFinanceExpenseByExpenseId(expenseId);
    }
}
