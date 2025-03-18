package com.ruoyi.fmgr.service.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fmgr.mapper.FmgreFinancePaymentMapper;
import com.ruoyi.fmgr.domain.FmgreFinancePayment;
import com.ruoyi.fmgr.domain.FmgreFinanceAccountBalance;
import com.ruoyi.fmgr.service.IFmgreFinancePaymentService;
import com.ruoyi.fmgr.mapper.FmgreFinanceAccountBalanceMapper;

/**
 * 付款流水Service业务层处理
 * 
 * @author terence
 * @date 2025-03-10
 */
@Service
public class FmgreFinancePaymentServiceImpl implements IFmgreFinancePaymentService 
{
    @Autowired
    private FmgreFinanceAccountBalanceMapper fmgreFinanceAccountBalanceMapper;

    @Autowired
    private FmgreFinancePaymentMapper fmgreFinancePaymentMapper;

    /**
     * 查询付款流水
     * 
     * @param paymentId 付款流水主键
     * @return 付款流水
     */
    @Override
    public FmgreFinancePayment selectFmgreFinancePaymentByPaymentId(Long paymentId)
    {
        return fmgreFinancePaymentMapper.selectFmgreFinancePaymentByPaymentId(paymentId);
    }

    /**
     * 查询付款流水列表
     * 
     * @param fmgreFinancePayment 付款流水
     * @return 付款流水
     */
    @Override
    public List<FmgreFinancePayment> selectFmgreFinancePaymentList(FmgreFinancePayment fmgreFinancePayment)
    {
        return fmgreFinancePaymentMapper.selectFmgreFinancePaymentList(fmgreFinancePayment);
    }

    /**
     * 新增付款流水
     * 
     * @param fmgreFinancePayment 付款流水
     * @return 结果
     */
    @Override
    public int insertFmgreFinancePayment(FmgreFinancePayment fmgreFinancePayment)
    {
        Long accountId = fmgreFinancePayment.getInAccId();
        if (accountId != null && accountId != 0) {
            updateAccountBalance(accountId, fmgreFinancePayment.getPaymentAmount());
        }
        accountId = fmgreFinancePayment.getOutAccId();
        if (accountId != null && accountId != 0) {
            updateAccountBalance(accountId, BigDecimal.ZERO.subtract(fmgreFinancePayment.getPaymentAmount()));
        }
        return fmgreFinancePaymentMapper.insertFmgreFinancePayment(fmgreFinancePayment);
    }

    private void updateAccountBalance(Long accountId, BigDecimal paymentAmount) {
        BigDecimal outAmount = fmgreFinancePaymentMapper.getPaymentAmountOutSum(accountId);
        BigDecimal inAmount = fmgreFinancePaymentMapper.getPaymentAmountInSum(accountId);
        if(outAmount == null) {
            outAmount = BigDecimal.ZERO;
        }
        if(inAmount == null) {
            inAmount = BigDecimal.ZERO;
        }
        FmgreFinanceAccountBalance balance = new FmgreFinanceAccountBalance();
        balance.setAccountId(accountId);
        balance.setAccountBalance(inAmount.subtract(outAmount).add(paymentAmount));
        fmgreFinanceAccountBalanceMapper.updateFmgreFinanceAccountBalance(balance);
    }

    /**
     * 修改付款流水
     * 
     * @param fmgreFinancePayment 付款流水
     * @return 结果
     */
    @Override
    public int updateFmgreFinancePayment(FmgreFinancePayment fmgreFinancePayment)
    {
        return fmgreFinancePaymentMapper.updateFmgreFinancePayment(fmgreFinancePayment);
    }

    /**
     * 批量删除付款流水
     * 
     * @param paymentIds 需要删除的付款流水主键
     * @return 结果
     */
    @Override
    public int deleteFmgreFinancePaymentByPaymentIds(Long[] paymentIds)
    {
        return fmgreFinancePaymentMapper.deleteFmgreFinancePaymentByPaymentIds(paymentIds);
    }

    /**
     * 删除付款流水信息
     * 
     * @param paymentId 付款流水主键
     * @return 结果
     */
    @Override
    public int deleteFmgreFinancePaymentByPaymentId(Long paymentId)
    {
        return fmgreFinancePaymentMapper.deleteFmgreFinancePaymentByPaymentId(paymentId);
    }
}
