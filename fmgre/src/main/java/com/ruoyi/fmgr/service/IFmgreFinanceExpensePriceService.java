package com.ruoyi.fmgr.service;

import java.util.List;
import com.ruoyi.fmgr.domain.FmgreFinanceExpensePrice;

/**
 * 费用类目价格Service接口
 * 
 * @author terence
 * @date 2025-04-01
 */
public interface IFmgreFinanceExpensePriceService 
{
    /**
     * 查询费用类目价格
     * 
     * @param priceId 费用类目价格主键
     * @return 费用类目价格
     */
    public FmgreFinanceExpensePrice selectFmgreFinanceExpensePriceByPriceId(Long priceId);

    /**
     * 查询费用类目价格列表
     * 
     * @param fmgreFinanceExpensePrice 费用类目价格
     * @return 费用类目价格集合
     */
    public List<FmgreFinanceExpensePrice> selectFmgreFinanceExpensePriceList(FmgreFinanceExpensePrice fmgreFinanceExpensePrice);

    /**
     * 新增费用类目价格
     * 
     * @param fmgreFinanceExpensePrice 费用类目价格
     * @return 结果
     */
    public int insertFmgreFinanceExpensePrice(FmgreFinanceExpensePrice fmgreFinanceExpensePrice);

    /**
     * 修改费用类目价格
     * 
     * @param fmgreFinanceExpensePrice 费用类目价格
     * @return 结果
     */
    public int updateFmgreFinanceExpensePrice(FmgreFinanceExpensePrice fmgreFinanceExpensePrice);

    /**
     * 批量删除费用类目价格
     * 
     * @param priceIds 需要删除的费用类目价格主键集合
     * @return 结果
     */
    public int deleteFmgreFinanceExpensePriceByPriceIds(Long[] priceIds);

    /**
     * 删除费用类目价格信息
     * 
     * @param priceId 费用类目价格主键
     * @return 结果
     */
    public int deleteFmgreFinanceExpensePriceByPriceId(Long priceId);
}
