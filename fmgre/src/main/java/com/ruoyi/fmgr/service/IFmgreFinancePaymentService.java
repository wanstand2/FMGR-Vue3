package com.ruoyi.fmgr.service;

import java.util.List;
import com.ruoyi.fmgr.domain.FmgreFinancePayment;

/**
 * 付款流水Service接口
 * 
 * @author terence
 * @date 2025-03-10
 */
public interface IFmgreFinancePaymentService 
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

    /**
     * 批量删除付款流水
     * 
     * @param paymentIds 需要删除的付款流水主键集合
     * @return 结果
     */
    public int deleteFmgreFinancePaymentByPaymentIds(Long[] paymentIds);

    /**
     * 删除付款流水信息
     * 
     * @param paymentId 付款流水主键
     * @return 结果
     */
    public int deleteFmgreFinancePaymentByPaymentId(Long paymentId);
}
