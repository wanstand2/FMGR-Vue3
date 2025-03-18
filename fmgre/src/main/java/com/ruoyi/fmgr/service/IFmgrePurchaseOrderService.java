package com.ruoyi.fmgr.service;

import java.util.List;
import java.math.BigDecimal;
import java.util.Collection;
import com.ruoyi.fmgr.domain.FmgrePurchaseOrder;
import com.ruoyi.fmgr.domain.FmgreSupplierFinanceBo;

/**
 * 采购订单Service接口
 * 
 * @author terence
 * @date 2025-02-24
 */
public interface IFmgrePurchaseOrderService 
{
    /**
     * 查询采购订单
     * 
     * @param orderId 采购订单主键
     * @return 采购订单
     */
    public FmgrePurchaseOrder selectFmgrePurchaseOrderByOrderId(Long orderId);

    /**
     * 查询采购订单列表
     * 
     * @param fmgrePurchaseOrder 采购订单
     * @return 采购订单集合
     */
    public List<FmgrePurchaseOrder> selectFmgrePurchaseOrderList(FmgrePurchaseOrder fmgrePurchaseOrder);

    /**
     * 新增采购订单
     * 
     * @param fmgrePurchaseOrder 采购订单
     * @return 结果
     */
    public int insertFmgrePurchaseOrder(FmgrePurchaseOrder fmgrePurchaseOrder);

    /**
     * 修改采购订单
     * 
     * @param fmgrePurchaseOrder 采购订单
     * @return 结果
     */
    public int updateFmgrePurchaseOrder(FmgrePurchaseOrder fmgrePurchaseOrder);

    /**
     * 批量删除采购订单
     * 
     * @param orderIds 需要删除的采购订单主键集合
     * @return 结果
     */
    public int deleteFmgrePurchaseOrderByOrderIds(Long[] orderIds);

    /**
     * 删除采购订单信息
     * 
     * @param orderId 采购订单主键
     * @return 结果
     */
    public int deleteFmgrePurchaseOrderByOrderId(Long orderId);

	/**
	 * 查询供应商付款情况列表
	 * 
	 * @param fmgrePurchaseOrder 采购订单
	 * @return 采购订单
	 */
	List<FmgreSupplierFinanceBo> selectFmgrePurchaseOrderSupplierFinanceList(Collection<Long> supplierIds, FmgrePurchaseOrder fmgrePurchaseOrder);

    public BigDecimal getOrdersTotalPrice(List<Long> orderIds);

    public void updateFmgrePurchaseOrderPaymentId(List<Long> orderIds, Long paymentId);
}
