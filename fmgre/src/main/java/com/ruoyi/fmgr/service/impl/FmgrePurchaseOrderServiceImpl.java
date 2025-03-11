package com.ruoyi.fmgr.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fmgr.mapper.FmgrePurchaseOrderMapper;
import com.ruoyi.fmgr.domain.FmgrePurchaseOrder;
import com.ruoyi.fmgr.service.IFmgrePurchaseOrderService;

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
}
