package com.ruoyi.fmgr.mapper;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.fmgr.domain.FmgreFinancePayment;

/**
 * 付款流水Mapper接口
 * 
 * @author terence
 * @date 2025-03-10
 */
public interface FmgreFinancePaymentMapper 
{
    /**
     * 查询付款流水
     * 
     * @param paymentId 付款流水主键
     * @return 付款流水
     */
    public FmgreFinancePayment selectFmgreFinancePaymentByPaymentId(Long paymentId);

    /**
     * 查询付款流水列表
     * 
     * @param fmgreFinancePayment 付款流水
     * @return 付款流水集合
     */
    public List<FmgreFinancePayment> selectFmgreFinancePaymentList(FmgreFinancePayment fmgreFinancePayment);
    
    public List<FmgreFinancePayment> selectFmgreFinancePaymentIdsLater(FmgreFinancePayment fmgreFinancePayment);

    /**
     * 新增付款流水
     * 
     * @param fmgreFinancePayment 付款流水
     * @return 结果
     */
    public int insertFmgreFinancePayment(FmgreFinancePayment fmgreFinancePayment);

    /**
     * 修改付款流水
     * 
     * @param fmgreFinancePayment 付款流水
     * @return 结果
     */
    public int updateFmgreFinancePayment(FmgreFinancePayment fmgreFinancePayment);
    
    public int updateFmgreFinancePaymentBanlance(FmgreFinancePayment fmgreFinancePayment);

    public int updateFmgreFinancePaymentBanlancesOutOutAcc(FmgreFinancePayment fmgreFinancePayment);
    public int updateFmgreFinancePaymentBanlancesOutInAcc(FmgreFinancePayment fmgreFinancePayment);
    public int updateFmgreFinancePaymentBanlancesInInAcc(FmgreFinancePayment fmgreFinancePayment);
    public int updateFmgreFinancePaymentBanlancesInOutAcc(FmgreFinancePayment fmgreFinancePayment);
    
    /**
     * 删除付款流水
     * 
     * @param paymentId 付款流水主键
     * @return 结果
     */
    public int deleteFmgreFinancePaymentByPaymentId(Long paymentId);

    /**
     * 批量删除付款流水
     * 
     * @param paymentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFmgreFinancePaymentByPaymentIds(Long[] paymentIds);

    public BigDecimal getPaymentAmountOutSum(Long accountId);

    public BigDecimal getPaymentAmountInSum(Long accountId);
}
