package com.ruoyi.fmgr.service;

import java.util.List;
import java.util.Collection;
import com.ruoyi.fmgr.domain.FmgreFinanceBank;

/**
 * 银行Service接口
 * 
 * @author terence
 * @date 2025-02-24
 */
public interface IFmgreFinanceBankService 
{
    /**
     * 查询银行
     * 
     * @param bankId 银行主键
     * @return 银行
     */
    public FmgreFinanceBank selectFmgreFinanceBankBybankId(Long bankId);

    /**
     * 查询银行列表
     * 
     * @param fmgreFinanceBank 银行
     * @return 银行集合
     */
    public List<FmgreFinanceBank> selectFmgreFinanceBankList(FmgreFinanceBank fmgreFinanceBank);

    public List<FmgreFinanceBank> selectFmgreFinanceBankListByIds(Collection<Long> bankIds);

    /**
     * 新增银行
     * 
     * @param fmgreFinanceBank 银行
     * @return 结果
     */
    public int insertFmgreFinanceBank(FmgreFinanceBank fmgreFinanceBank);

    /**
     * 修改银行
     * 
     * @param fmgreFinanceBank 银行
     * @return 结果
     */
    public int updateFmgreFinanceBank(FmgreFinanceBank fmgreFinanceBank);

    /**
     * 批量删除银行
     * 
     * @param bankIds 需要删除的银行主键集合
     * @return 结果
     */
    public int deleteFmgreFinanceBankBybankIds(Long[] bankIds);

    /**
     * 删除银行信息
     * 
     * @param bankId 银行主键
     * @return 结果
     */
    public int deleteFmgreFinanceBankBybankId(Long bankId);
}
