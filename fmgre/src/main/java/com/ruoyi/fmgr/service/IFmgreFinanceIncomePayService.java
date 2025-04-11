package com.ruoyi.fmgr.service;

import java.util.List;
import com.ruoyi.fmgr.domain.FmgreFinanceIncomePay;

/**
 * 收入Service接口
 * 
 * @author terence
 * @date 2025-04-09
 */
public interface IFmgreFinanceIncomePayService 
{
    /**
     * 查询收入
     * 
     * @param incomeId 收入主键
     * @return 收入
     */
    public FmgreFinanceIncomePay selectFmgreFinanceIncomePayByIncomeId(Long incomeId);

    /**
     * 查询收入列表
     * 
     * @param fmgreFinanceIncomePay 收入
     * @return 收入集合
     */
    public List<FmgreFinanceIncomePay> selectFmgreFinanceIncomePayList(FmgreFinanceIncomePay fmgreFinanceIncomePay);

    /**
     * 新增收入
     * 
     * @param fmgreFinanceIncomePay 收入
     * @return 结果
     */
    public int insertFmgreFinanceIncomePay(FmgreFinanceIncomePay fmgreFinanceIncomePay);

    /**
     * 修改收入
     * 
     * @param fmgreFinanceIncomePay 收入
     * @return 结果
     */
    public int updateFmgreFinanceIncomePay(FmgreFinanceIncomePay fmgreFinanceIncomePay);

    /**
     * 批量删除收入
     * 
     * @param incomeIds 需要删除的收入主键集合
     * @return 结果
     */
    public int deleteFmgreFinanceIncomePayByIncomeIds(Long[] incomeIds);

    /**
     * 删除收入信息
     * 
     * @param incomeId 收入主键
     * @return 结果
     */
    public int deleteFmgreFinanceIncomePayByIncomeId(Long incomeId);
}
