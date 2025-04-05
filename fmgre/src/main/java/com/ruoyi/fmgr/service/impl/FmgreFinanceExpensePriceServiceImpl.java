package com.ruoyi.fmgr.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fmgr.mapper.FmgreFinanceExpensePriceMapper;
import com.ruoyi.fmgr.domain.FmgreFinanceExpensePrice;
import com.ruoyi.fmgr.service.IFmgreFinanceExpensePriceService;

/**
 * 费用类目价格Service业务层处理
 * 
 * @author terence
 * @date 2025-04-01
 */
@Service
public class FmgreFinanceExpensePriceServiceImpl implements IFmgreFinanceExpensePriceService 
{
    @Autowired
    private FmgreFinanceExpensePriceMapper fmgreFinanceExpensePriceMapper;

    /**
     * 查询费用类目价格
     * 
     * @param priceId 费用类目价格主键
     * @return 费用类目价格
     */
    @Override
    public FmgreFinanceExpensePrice selectFmgreFinanceExpensePriceByPriceId(Long priceId)
    {
        return fmgreFinanceExpensePriceMapper.selectFmgreFinanceExpensePriceByPriceId(priceId);
    }

    /**
     * 查询费用类目价格列表
     * 
     * @param fmgreFinanceExpensePrice 费用类目价格
     * @return 费用类目价格
     */
    @Override
    public List<FmgreFinanceExpensePrice> selectFmgreFinanceExpensePriceList(FmgreFinanceExpensePrice fmgreFinanceExpensePrice)
    {
        return fmgreFinanceExpensePriceMapper.selectFmgreFinanceExpensePriceList(fmgreFinanceExpensePrice);
    }

    /**
     * 新增费用类目价格
     * 
     * @param fmgreFinanceExpensePrice 费用类目价格
     * @return 结果
     */
    @Override
    public int insertFmgreFinanceExpensePrice(FmgreFinanceExpensePrice fmgreFinanceExpensePrice)
    {
        return fmgreFinanceExpensePriceMapper.insertFmgreFinanceExpensePrice(fmgreFinanceExpensePrice);
    }

    /**
     * 修改费用类目价格
     * 
     * @param fmgreFinanceExpensePrice 费用类目价格
     * @return 结果
     */
    @Override
    public int updateFmgreFinanceExpensePrice(FmgreFinanceExpensePrice fmgreFinanceExpensePrice)
    {
        return fmgreFinanceExpensePriceMapper.updateFmgreFinanceExpensePrice(fmgreFinanceExpensePrice);
    }

    /**
     * 批量删除费用类目价格
     * 
     * @param priceIds 需要删除的费用类目价格主键
     * @return 结果
     */
    @Override
    public int deleteFmgreFinanceExpensePriceByPriceIds(Long[] priceIds)
    {
        return fmgreFinanceExpensePriceMapper.deleteFmgreFinanceExpensePriceByPriceIds(priceIds);
    }

    /**
     * 删除费用类目价格信息
     * 
     * @param priceId 费用类目价格主键
     * @return 结果
     */
    @Override
    public int deleteFmgreFinanceExpensePriceByPriceId(Long priceId)
    {
        return fmgreFinanceExpensePriceMapper.deleteFmgreFinanceExpensePriceByPriceId(priceId);
    }
}
