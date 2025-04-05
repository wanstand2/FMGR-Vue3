package com.ruoyi.fmgr.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.fmgr.domain.FmgreFinancePayment;
import com.ruoyi.fmgr.mapper.FmgreFinanceAccountBalanceMapper;
import com.ruoyi.fmgr.mapper.FmgreFinancePaymentMapper;
import com.ruoyi.fmgr.service.IFmgreFinancePaymentService;

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
        int ret = fmgreFinancePaymentMapper.insertFmgreFinancePayment(fmgreFinancePayment);
        if (ret > 0) {
            afterFmgreFinancePayment(fmgreFinancePayment);
        }
        return ret;
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
    	FmgreFinancePayment old = fmgreFinancePaymentMapper.selectFmgreFinancePaymentByPaymentId(fmgreFinancePayment.getPaymentId());
    	if(fmgreFinancePayment.getPaymentAmount() == null) fmgreFinancePayment.setPaymentAmount(old.getPaymentAmount());
    	if(fmgreFinancePayment.getPaymentTime() == null) fmgreFinancePayment.setPaymentTime(old.getPaymentTime());
    	if(fmgreFinancePayment.getPaymentTime() != null && !old.getPaymentTime().equals(fmgreFinancePayment.getPaymentTime())) {
    		BigDecimal bias = BigDecimal.ZERO.subtract(old.getPaymentAmount());
    		old.setPaymentAmount(BigDecimal.ZERO);
    		fmgreFinancePaymentMapper.updateFmgreFinancePayment(old);
    		FmgreFinancePayment payment = new FmgreFinancePayment();
            payment.setPaymentId(old.getPaymentId());
            payment.setOutAccId(old.getOutAccId());
            payment.setInAccId(old.getInAccId());
            payment.setPaymentTime(old.getPaymentTime());
            payment.setPaymentAmount(bias);
            afterFmgreFinancePayment(payment);
    	}
        BigDecimal bias = fmgreFinancePayment.getPaymentAmount().subtract(
            old.getPaymentAmount());
        if(fmgreFinancePayment.getInAccId() == null) fmgreFinancePayment.setInAccId(old.getInAccId());
        if(fmgreFinancePayment.getOutAccId() == null) fmgreFinancePayment.setOutAccId(old.getOutAccId());
    	int ret = fmgreFinancePaymentMapper.updateFmgreFinancePayment(fmgreFinancePayment);
        if (ret > 0) {
            FmgreFinancePayment payment = new FmgreFinancePayment();
            payment.setPaymentId(old.getPaymentId());
            payment.setOutAccId(old.getOutAccId());
            payment.setInAccId(old.getInAccId());
            payment.setPaymentTime(fmgreFinancePayment.getPaymentTime());
            payment.setPaymentAmount(bias);
            afterFmgreFinancePayment(payment);
        }
        return ret;
    }

    private void afterFmgreFinancePayment(FmgreFinancePayment fmgreFinancePayment) {
        Long inAccId = fmgreFinancePayment.getInAccId();
        if (inAccId != null && inAccId != 0) {
        	fmgreFinanceAccountBalanceMapper.updateFmgreFinanceAccountBalanceByAccountId(inAccId);
            fmgreFinancePaymentMapper.updateFmgreFinancePaymentBanlancesOutInAcc(fmgreFinancePayment);
            fmgreFinancePaymentMapper.updateFmgreFinancePaymentBanlancesInInAcc(fmgreFinancePayment);
        }
        Long outAccId = fmgreFinancePayment.getOutAccId();
        if (outAccId != null && outAccId != 0) {
        	fmgreFinanceAccountBalanceMapper.updateFmgreFinanceAccountBalanceByAccountId(outAccId);
            fmgreFinancePaymentMapper.updateFmgreFinancePaymentBanlancesOutOutAcc(fmgreFinancePayment);
            fmgreFinancePaymentMapper.updateFmgreFinancePaymentBanlancesInOutAcc(fmgreFinancePayment);
        }
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
