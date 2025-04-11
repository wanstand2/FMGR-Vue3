package com.ruoyi.fmgr.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fmgr.mapper.FmgreFinanceIncomePayMapper;
import com.ruoyi.fmgr.domain.FmgreFinanceIncomePay;
import com.ruoyi.fmgr.service.IFmgreFinanceIncomePayService;

/**
 * 收入Service业务层处理
 * 
 * @author terence
 * @date 2025-04-09
 */
@Service
public class FmgreFinanceIncomePayServiceImpl implements IFmgreFinanceIncomePayService 
{
    @Autowired
    private FmgreFinanceIncomePayMapper fmgreFinanceIncomePayMapper;

    /**
     * 查询收入
     * 
     * @param incomeId 收入主键
     * @return 收入
     */
    @Override
    public FmgreFinanceIncomePay selectFmgreFinanceIncomePayByIncomeId(Long incomeId)
    {
        return fmgreFinanceIncomePayMapper.selectFmgreFinanceIncomePayByIncomeId(incomeId);
    }

    /**
     * 查询收入列表
     * 
     * @param fmgreFinanceIncomePay 收入
     * @return 收入
     */
    @Override
    public List<FmgreFinanceIncomePay> selectFmgreFinanceIncomePayList(FmgreFinanceIncomePay fmgreFinanceIncomePay)
    {
        return fmgreFinanceIncomePayMapper.selectFmgreFinanceIncomePayList(fmgreFinanceIncomePay);
    }

    /**
     * 新增收入
     * 
     * @param fmgreFinanceIncomePay 收入
     * @return 结果
     */
    @Override
    public int insertFmgreFinanceIncomePay(FmgreFinanceIncomePay fmgreFinanceIncomePay)
    {
        return fmgreFinanceIncomePayMapper.insertFmgreFinanceIncomePay(fmgreFinanceIncomePay);
    }

    /**
     * 修改收入
     * 
     * @param fmgreFinanceIncomePay 收入
     * @return 结果
     */
    @Override
    public int updateFmgreFinanceIncomePay(FmgreFinanceIncomePay fmgreFinanceIncomePay)
    {
        return fmgreFinanceIncomePayMapper.updateFmgreFinanceIncomePay(fmgreFinanceIncomePay);
    }

    /**
     * 批量删除收入
     * 
     * @param incomeIds 需要删除的收入主键
     * @return 结果
     */
    @Override
    public int deleteFmgreFinanceIncomePayByIncomeIds(Long[] incomeIds)
    {
        return fmgreFinanceIncomePayMapper.deleteFmgreFinanceIncomePayByIncomeIds(incomeIds);
    }

    /**
     * 删除收入信息
     * 
     * @param incomeId 收入主键
     * @return 结果
     */
    @Override
    public int deleteFmgreFinanceIncomePayByIncomeId(Long incomeId)
    {
        return fmgreFinanceIncomePayMapper.deleteFmgreFinanceIncomePayByIncomeId(incomeId);
    }
}
