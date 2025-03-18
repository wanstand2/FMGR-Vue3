package com.ruoyi.fmgr.service.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fmgr.mapper.FmgrePurchaseOrderMapper;
import com.ruoyi.fmgr.domain.FmgrePurchaseOrder;
import com.ruoyi.fmgr.domain.FmgreSupplierFinanceBo;
import com.ruoyi.fmgr.service.IFmgrePurchaseOrderService;
import com.ruoyi.fmgr.service.utils.CodeGenerater;
/**
 * 采购订单Service业务层处理
 * 
 * @author terence
 * @date 2025-02-24
 */
@Service
public class FmgrePurchaseOrderServiceImpl implements IFmgrePurchaseOrderService 
{
    @Autowired
    private FmgrePurchaseOrderMapper fmgrePurchaseOrderMapper;

    @Autowired
    private CodeGenerater codeGenerater;

    /**
     * 查询采购订单
     * 
     * @param orderId 采购订单主键
     * @return 采购订单
     */
    @Override
    public FmgrePurchaseOrder selectFmgrePurchaseOrderByOrderId(Long orderId)
    {
        return fmgrePurchaseOrderMapper.selectFmgrePurchaseOrderByOrderId(orderId);
    }

    /**
     * 查询采购订单列表
     * 
     * @param fmgrePurchaseOrder 采购订单
     * @return 采购订单
     */
    @Override
    public List<FmgrePurchaseOrder> selectFmgrePurchaseOrderList(FmgrePurchaseOrder fmgrePurchaseOrder)
    {
        return fmgrePurchaseOrderMapper.selectFmgrePurchaseOrderList(fmgrePurchaseOrder);
    }

    /**
     * 新增采购订单
     * 
     * @param fmgrePurchaseOrder 采购订单
     * @return 结果
     */
    @Override
    public int insertFmgrePurchaseOrder(FmgrePurchaseOrder fmgrePurchaseOrder)
    {
        String code = codeGenerater.generateCode("fmgre_purchase_order", 4, fmgrePurchaseOrder.getOrderTime());
        fmgrePurchaseOrder.setOrderCode(code);
        return fmgrePurchaseOrderMapper.insertFmgrePurchaseOrder(fmgrePurchaseOrder);
    }

    /**
     * 修改采购订单
     * 
     * @param fmgrePurchaseOrder 采购订单
     * @return 结果
     */
    @Override
    public int updateFmgrePurchaseOrder(FmgrePurchaseOrder fmgrePurchaseOrder)
    {
        return fmgrePurchaseOrderMapper.updateFmgrePurchaseOrder(fmgrePurchaseOrder);
    }

    /**
     * 批量删除采购订单
     * 
     * @param orderIds 需要删除的采购订单主键
     * @return 结果
     */
    @Override
    public int deleteFmgrePurchaseOrderByOrderIds(Long[] orderIds)
    {
        return fmgrePurchaseOrderMapper.deleteFmgrePurchaseOrderByOrderIds(orderIds);
    }

    /**
     * 删除采购订单信息
     * 
     * @param orderId 采购订单主键
     * @return 结果
     */
    @Override
    public int deleteFmgrePurchaseOrderByOrderId(Long orderId)
    {
        return fmgrePurchaseOrderMapper.deleteFmgrePurchaseOrderByOrderId(orderId);
    }
    

    /**
     * 查询供应商付款情况列表
     * 
     * @param fmgrePurchaseOrder 采购订单
     * @return 采购订单
     */
    @Override
    public List<FmgreSupplierFinanceBo> selectFmgrePurchaseOrderSupplierFinanceList(Collection<Long> supplierIds, FmgrePurchaseOrder fmgrePurchaseOrder)
    {
        return fmgrePurchaseOrderMapper.selectFmgrePurchaseOrderSupplierFinanceList(supplierIds, fmgrePurchaseOrder);
    }

    @Override
    public BigDecimal getOrdersTotalPrice(List<Long> orderIds) {
        return fmgrePurchaseOrderMapper.getOrdersTotalPrice(orderIds);
    }

    @Override
    public void updateFmgrePurchaseOrderPaymentId(List<Long> orderIds, Long paymentId) {
        fmgrePurchaseOrderMapper.updateFmgrePurchaseOrderPaymentId(orderIds, paymentId);
    }
}
