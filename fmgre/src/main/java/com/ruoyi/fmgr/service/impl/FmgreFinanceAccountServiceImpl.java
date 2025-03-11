package com.ruoyi.fmgr.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fmgr.mapper.FmgreFinanceAccountMapper;
import com.ruoyi.fmgr.domain.FmgreFinanceAccount;
import com.ruoyi.fmgr.domain.FmgreFinanceAccountBalance;
import com.ruoyi.fmgr.service.IFmgreFinanceAccountService;
import com.ruoyi.fmgr.service.IFmgreFinanceAccountBalanceService;
import java.math.BigDecimal;
import java.util.stream.Collectors;

/**
 * 银行账户Service业务层处理
 * 
 * @author terence
 * @date 2025-02-24
 */
@Service
public class FmgreFinanceAccountServiceImpl implements IFmgreFinanceAccountService 
{
    @Autowired
    private FmgreFinanceAccountMapper fmgreFinanceAccountMapper;

    @Autowired
    private IFmgreFinanceAccountBalanceService fmgreFinanceAccountBalanceService;

    /**
     * 查询银行账户
     * 
     * @param accountId 银行账户主键
     * @return 银行账户
     */
    @Override
    public FmgreFinanceAccount selectFmgreFinanceAccountByAccountId(Long accountId)
    {
        FmgreFinanceAccount account = fmgreFinanceAccountMapper.selectFmgreFinanceAccountByAccountId(accountId);
        if (account != null) {
            account.setAccountBalance(fmgreFinanceAccountBalanceService.selectFmgreFinanceAccountBalanceByAccountId(accountId).getAccountBalance());
        }
        return account;
    }

    /**
     * 查询银行账户列表
     * 
     * @param fmgreFinanceAccount 银行账户
     * @return 银行账户
     */
    @Override
    public List<FmgreFinanceAccount> selectFmgreFinanceAccountList(FmgreFinanceAccount fmgreFinanceAccount)
    {
        List<FmgreFinanceAccount> accounts = fmgreFinanceAccountMapper.selectFmgreFinanceAccountList(fmgreFinanceAccount);
        if (accounts.size() > 0) {
            List<FmgreFinanceAccountBalance> balances = fmgreFinanceAccountBalanceService.selectFmgreFinanceAccountBalanceListByAccountIds(accounts.stream().map(FmgreFinanceAccount::getAccountId).collect(Collectors.toList()));
            for (FmgreFinanceAccount account : accounts) {
                account.setAccountBalance(balances.stream().filter(balance -> balance.getAccountId().equals(account.getAccountId())).findFirst().orElse(new FmgreFinanceAccountBalance()).getAccountBalance());
            }
        }
        return accounts;
    }

    /**
     * 新增银行账户
     * 
     * @param fmgreFinanceAccount 银行账户
     * @return 结果
     */
    @Override
    public int insertFmgreFinanceAccount(FmgreFinanceAccount fmgreFinanceAccount)
    {
        int ret = fmgreFinanceAccountMapper.insertFmgreFinanceAccount(fmgreFinanceAccount);
        if (fmgreFinanceAccount.getAccountBalance() == null) {
            fmgreFinanceAccount.setAccountBalance(BigDecimal.ZERO);
        }
        FmgreFinanceAccountBalance balance = new FmgreFinanceAccountBalance();
        balance.setAccountId(fmgreFinanceAccount.getAccountId());
        balance.setAccountBalance(fmgreFinanceAccount.getAccountBalance());
        fmgreFinanceAccountBalanceService.insertFmgreFinanceAccountBalance(balance);
        return ret;
    }

    /**
     * 修改银行账户
     * 
     * @param fmgreFinanceAccount 银行账户
     * @return 结果
     */
    @Override
    public int updateFmgreFinanceAccount(FmgreFinanceAccount fmgreFinanceAccount)
    {
        int ret = fmgreFinanceAccountMapper.updateFmgreFinanceAccount(fmgreFinanceAccount);
        if (fmgreFinanceAccount.getAccountBalance() != null) {
            FmgreFinanceAccountBalance balance = new FmgreFinanceAccountBalance();
            balance.setAccountId(fmgreFinanceAccount.getAccountId());
            balance.setAccountBalance(fmgreFinanceAccount.getAccountBalance());
            fmgreFinanceAccountBalanceService.updateFmgreFinanceAccountBalance(balance);
        }
        return ret;
    }

    /**
     * 批量删除银行账户
     * 
     * @param accountIds 需要删除的银行账户主键
     * @return 结果
     */
    @Override
    public int deleteFmgreFinanceAccountByAccountIds(Long[] accountIds)
    {
        int ret = fmgreFinanceAccountMapper.deleteFmgreFinanceAccountByAccountIds(accountIds);
        fmgreFinanceAccountBalanceService.deleteFmgreFinanceAccountBalanceByAccountIds(accountIds);
        return ret;
    }

    /**
     * 删除银行账户信息
     * 
     * @param accountId 银行账户主键
     * @return 结果
     */
    @Override
    public int deleteFmgreFinanceAccountByAccountId(Long accountId)
    {
        int ret = fmgreFinanceAccountMapper.deleteFmgreFinanceAccountByAccountId(accountId);
        fmgreFinanceAccountBalanceService.deleteFmgreFinanceAccountBalanceByAccountId(accountId);
        return ret;
    }
}
