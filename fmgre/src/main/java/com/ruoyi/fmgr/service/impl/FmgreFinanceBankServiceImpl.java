package com.ruoyi.fmgr.service.impl;

import java.util.List;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fmgr.mapper.FmgreFinanceBankMapper;
import com.ruoyi.fmgr.domain.FmgreFinanceBank;
import com.ruoyi.fmgr.service.IFmgreFinanceBankService;

/**
 * 银行Service业务层处理
 * 
 * @author terence
 * @date 2025-02-24
 */
@Service
public class FmgreFinanceBankServiceImpl implements IFmgreFinanceBankService 
{
    @Autowired
    private FmgreFinanceBankMapper fmgreFinanceBankMapper;

    /**
     * 查询银行
     * 
     * @param bankId 银行主键
     * @return 银行
     */
    @Override
    public FmgreFinanceBank selectFmgreFinanceBankBybankId(Long bankId)
    {
        return fmgreFinanceBankMapper.selectFmgreFinanceBankBybankId(bankId);
    }

    /**
     * 查询银行列表
     * 
     * @param fmgreFinanceBank 银行
     * @return 银行
     */
    @Override
    public List<FmgreFinanceBank> selectFmgreFinanceBankList(FmgreFinanceBank fmgreFinanceBank)
    {
        return fmgreFinanceBankMapper.selectFmgreFinanceBankList(fmgreFinanceBank);
    }

    @Override
    public List<FmgreFinanceBank> selectFmgreFinanceBankListByIds(Collection<Long> bankIds) {
        return fmgreFinanceBankMapper.selectFmgreFinanceBankListByIds(bankIds);
    }

    /**
     * 新增银行
     * 
     * @param fmgreFinanceBank 银行
     * @return 结果
     */
    @Override
    public int insertFmgreFinanceBank(FmgreFinanceBank fmgreFinanceBank)
    {
        return fmgreFinanceBankMapper.insertFmgreFinanceBank(fmgreFinanceBank);
    }

    /**
     * 修改银行
     * 
     * @param fmgreFinanceBank 银行
     * @return 结果
     */
    @Override
    public int updateFmgreFinanceBank(FmgreFinanceBank fmgreFinanceBank)
    {
        return fmgreFinanceBankMapper.updateFmgreFinanceBank(fmgreFinanceBank);
    }

    /**
     * 批量删除银行
     * 
     * @param bankIds 需要删除的银行主键
     * @return 结果
     */
    @Override
    public int deleteFmgreFinanceBankBybankIds(Long[] bankIds)
    {
        return fmgreFinanceBankMapper.deleteFmgreFinanceBankBybankIds(bankIds);
    }

    /**
     * 删除银行信息
     * 
     * @param bankId 银行主键
     * @return 结果
     */
    @Override
    public int deleteFmgreFinanceBankBybankId(Long bankId)
    {
        return fmgreFinanceBankMapper.deleteFmgreFinanceBankBybankId(bankId);
    }
}
