package com.ruoyi.fmgr.service;

import java.util.List;
import com.ruoyi.fmgr.domain.FmgreFinanceAccount;

/**
 * 银行账户Service接口
 * 
 * @author terence
 * @date 2025-02-24
 */
public interface IFmgreFinanceAccountService 
{
    /**
     * 查询银行账户
     * 
     * @param accountId 银行账户主键
     * @return 银行账户
     */
    public FmgreFinanceAccount selectFmgreFinanceAccountByAccountId(Long accountId);

    /**
     * 查询银行账户列表
     * 
     * @param fmgreFinanceAccount 银行账户
     * @return 银行账户集合
     */
    public List<FmgreFinanceAccount> selectFmgreFinanceAccountList(FmgreFinanceAccount fmgreFinanceAccount);

    /**
     * 新增银行账户
     * 
     * @param fmgreFinanceAccount 银行账户
     * @return 结果
     */
    public int insertFmgreFinanceAccount(FmgreFinanceAccount fmgreFinanceAccount);

    /**
     * 修改银行账户
     * 
     * @param fmgreFinanceAccount 银行账户
     * @return 结果
     */
    public int updateFmgreFinanceAccount(FmgreFinanceAccount fmgreFinanceAccount);

    /**
     * 批量删除银行账户
     * 
     * @param accountIds 需要删除的银行账户主键集合
     * @return 结果
     */
    public int deleteFmgreFinanceAccountByAccountIds(Long[] accountIds);

    /**
     * 删除银行账户信息
     * 
     * @param accountId 银行账户主键
     * @return 结果
     */
    public int deleteFmgreFinanceAccountByAccountId(Long accountId);
}
