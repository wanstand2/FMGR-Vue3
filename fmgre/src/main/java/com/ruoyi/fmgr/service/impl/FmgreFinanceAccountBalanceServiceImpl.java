package com.ruoyi.fmgr.service.impl;

import java.util.List;
import java.math.BigDecimal;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fmgr.mapper.FmgreFinanceAccountBalanceMapper;
import com.ruoyi.fmgr.domain.FmgreFinanceAccountBalance;
import com.ruoyi.fmgr.service.IFmgreFinanceAccountBalanceService;

/**
 * 银行账户余额Service业务层处理
 * 
 * @author terence
 * @date 2025-02-24
 */
@Service
public class FmgreFinanceAccountBalanceServiceImpl implements IFmgreFinanceAccountBalanceService 
{
    @Autowired
    private FmgreFinanceAccountBalanceMapper fmgreFinanceAccountBalanceMapper;

    /**
     * 查询银行账户余额
     * 
     * @param accountId 银行账户余额主键
     * @return 银行账户余额
     */
    @Override
    public FmgreFinanceAccountBalance selectFmgreFinanceAccountBalanceByAccountId(Long accountId)
    {
        return fmgreFinanceAccountBalanceMapper.selectFmgreFinanceAccountBalanceByAccountId(accountId);
    }

    @Override
    public List<FmgreFinanceAccountBalance> selectFmgreFinanceAccountBalanceListByAccountIds(Collection<Long> accountIds) {
        return fmgreFinanceAccountBalanceMapper.selectFmgreFinanceAccountBalanceListByAccountIds(accountIds);
    }

    @Override
    public int updateFmgreFinanceAccountBalanceByAccountId(Long accountId) {
        return fmgreFinanceAccountBalanceMapper.updateFmgreFinanceAccountBalanceByAccountId(accountId);
    }

    /**
     * 查询银行账户余额列表
     * 
     * @param fmgreFinanceAccountBalance 银行账户余额
     * @return 银行账户余额
     */
    @Override
    public List<FmgreFinanceAccountBalance> selectFmgreFinanceAccountBalanceList(FmgreFinanceAccountBalance fmgreFinanceAccountBalance)
    {
        return fmgreFinanceAccountBalanceMapper.selectFmgreFinanceAccountBalanceList(fmgreFinanceAccountBalance);
    }

    /**
     * 新增银行账户余额
     * 
     * @param fmgreFinanceAccountBalance 银行账户余额
     * @return 结果
     */
    @Override
    public int insertFmgreFinanceAccountBalance(FmgreFinanceAccountBalance fmgreFinanceAccountBalance)
    {
        return fmgreFinanceAccountBalanceMapper.insertFmgreFinanceAccountBalance(fmgreFinanceAccountBalance);
    }

    /**
     * 修改银行账户余额
     * 
     * @param fmgreFinanceAccountBalance 银行账户余额
     * @return 结果
     */
    @Override
    public int updateFmgreFinanceAccountBalance(FmgreFinanceAccountBalance fmgreFinanceAccountBalance)
    {
        return fmgreFinanceAccountBalanceMapper.updateFmgreFinanceAccountBalance(fmgreFinanceAccountBalance);
    }

    /**
     * 批量删除银行账户余额
     * 
     * @param accountIds 需要删除的银行账户余额主键
     * @return 结果
     */
    @Override
    public int deleteFmgreFinanceAccountBalanceByAccountIds(Long[] accountIds)
    {
        return fmgreFinanceAccountBalanceMapper.deleteFmgreFinanceAccountBalanceByAccountIds(accountIds);
    }

    /**
     * 删除银行账户余额信息
     * 
     * @param accountId 银行账户余额主键
     * @return 结果
     */
    @Override
    public int deleteFmgreFinanceAccountBalanceByAccountId(Long accountId)
    {
        return fmgreFinanceAccountBalanceMapper.deleteFmgreFinanceAccountBalanceByAccountId(accountId);
    }
}
