package com.ruoyi.fmgr.mapper;

import java.util.List;
import java.util.Collection;
import com.ruoyi.fmgr.domain.FmgreFinanceAccountBalance;

/**
 * 银行账户余额Mapper接口
 * 
 * @author terence
 * @date 2025-02-24
 */
public interface FmgreFinanceAccountBalanceMapper 
{
    /**
     * 查询银行账户余额
     * 
     * @param accountId 银行账户余额主键
     * @return 银行账户余额
     */
    public FmgreFinanceAccountBalance selectFmgreFinanceAccountBalanceByAccountId(Long accountId);

    /**
     * 查询银行账户余额列表
     * 
     * @param fmgreFinanceAccountBalance 银行账户余额
     * @return 银行账户余额集合
     */
    public List<FmgreFinanceAccountBalance> selectFmgreFinanceAccountBalanceList(FmgreFinanceAccountBalance fmgreFinanceAccountBalance);

    /**
     * 新增银行账户余额
     * 
     * @param fmgreFinanceAccountBalance 银行账户余额
     * @return 结果
     */
    public int insertFmgreFinanceAccountBalance(FmgreFinanceAccountBalance fmgreFinanceAccountBalance);

    /**
     * 修改银行账户余额
     * 
     * @param fmgreFinanceAccountBalance 银行账户余额
     * @return 结果
     */
    public int updateFmgreFinanceAccountBalance(FmgreFinanceAccountBalance fmgreFinanceAccountBalance);

    /**
     * 删除银行账户余额
     * 
     * @param accountId 银行账户余额主键
     * @return 结果
     */
    public int deleteFmgreFinanceAccountBalanceByAccountId(Long accountId);

    /**
     * 批量删除银行账户余额
     * 
     * @param accountIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFmgreFinanceAccountBalanceByAccountIds(Long[] accountIds);

    /**
     * 根据账户ID列表查询余额列表
     * 
     * @param accountIds 账户ID集合
     * @return 余额列表
     */
    public List<FmgreFinanceAccountBalance> selectFmgreFinanceAccountBalanceListByAccountIds(Collection<Long> accountIds);

    public int updateFmgreFinanceAccountBalanceByAccountId(Long accountId);
}
