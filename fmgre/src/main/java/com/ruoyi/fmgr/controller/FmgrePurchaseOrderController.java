package com.ruoyi.fmgr.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.fmgr.domain.FmgrePurchaseOrder;
import com.ruoyi.fmgr.domain.FmgrePurchaseOrderSubmitBo;
import com.ruoyi.fmgr.service.IFmgrePurchaseOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.fmgr.domain.FmgrePurchaseItem;
import com.ruoyi.fmgr.service.IFmgrePurchaseItemService;
import com.ruoyi.fmgr.domain.FmgreFinancePayment;
import com.ruoyi.fmgr.domain.FmgreFinancePaymentDisplayBo;
import com.ruoyi.fmgr.service.IFmgreFinancePaymentService;
import com.ruoyi.fmgr.domain.FmgreSupplierQuote;
import com.ruoyi.fmgr.service.IFmgreSupplierQuoteService;
import com.ruoyi.fmgr.domain.FmgrePurchaseItemSubmitBo;
import com.ruoyi.fmgr.domain.FmgrePurchaseOrderDisplayBo;
import com.ruoyi.fmgr.domain.FmgrePurchaseItemDisplayBo;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;
import com.ruoyi.fmgr.domain.FmgreSupplier;
import com.ruoyi.fmgr.service.IFmgreSupplierService;
import com.ruoyi.fmgr.domain.FmgreMaterial;
import com.ruoyi.fmgr.service.IFmgreMaterialService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.fmgr.domain.FmgrePurchaseOrderSummaryBo;
import com.ruoyi.fmgr.domain.FmgreFinancePaymentPayBo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
/**
 * 采购订单Controller
 * 
 * @author terence
 * @date 2025-02-24
 */
@RestController
@RequestMapping("/purchase/order")
public class FmgrePurchaseOrderController extends BaseController
{
    @Autowired
    private IFmgrePurchaseItemService fmgrePurchaseItemService;

    @Autowired
    private IFmgrePurchaseOrderService fmgrePurchaseOrderService;

    @Autowired
    private IFmgreFinancePaymentService fmgreFinancePaymentService;

    @Autowired
    private IFmgreSupplierQuoteService fmgreSupplierQuoteService;

    @Autowired
    private IFmgreSupplierService fmgreSupplierService;

    @Autowired
    private IFmgreMaterialService fmgreMaterialService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private FmgreFinancePaymentController fmgreFinancePaymentController;

    /**
     * 查询采购订单列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmgrePurchaseOrder fmgrePurchaseOrder)
    {
        startPage();
        List<FmgrePurchaseOrder> list = fmgrePurchaseOrderService.selectFmgrePurchaseOrderList(fmgrePurchaseOrder);
        List<Long> orderIds = list.stream().map(FmgrePurchaseOrder::getOrderId).collect(Collectors.toList());
        if(orderIds.size() > 0) {
            List<FmgrePurchaseOrderSummaryBo> summaryList = fmgrePurchaseItemService.selectFmgrePurchaseOrderSummaryByOrderIds(orderIds);
            for(FmgrePurchaseOrder order : list) {
                order.setSummary(summaryList.stream().filter(s -> s.getOrderId().equals(order.getOrderId())).findFirst().orElse(null));
            }
        }
        return getDataTable(list);      
    }

    /**
     * 导出采购订单列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:order:export')")
    @Log(title = "采购订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FmgrePurchaseOrder fmgrePurchaseOrder)
    {
        List<FmgrePurchaseOrder> list = fmgrePurchaseOrderService.selectFmgrePurchaseOrderList(fmgrePurchaseOrder);
        ExcelUtil<FmgrePurchaseOrder> util = new ExcelUtil<FmgrePurchaseOrder>(FmgrePurchaseOrder.class);
        util.exportExcel(response, list, "采购订单数据");
    }

    /**
     * 获取采购订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:order:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId)
    {
        FmgrePurchaseOrderDisplayBo bo = new FmgrePurchaseOrderDisplayBo(fmgrePurchaseOrderService.selectFmgrePurchaseOrderByOrderId(orderId));
        FmgrePurchaseItem searchItem = new FmgrePurchaseItem();
        searchItem.setOrderId(orderId);
        List<FmgrePurchaseItem> items = fmgrePurchaseItemService.selectFmgrePurchaseItemList(searchItem);
        bo.setItems(items.stream().map(item -> {
            return new FmgrePurchaseItemDisplayBo(item);
        }).collect(Collectors.toList()));
        List<Long> materailIds = items.stream().map(FmgrePurchaseItem::getMaterailId).collect(Collectors.toList());
        List<FmgreMaterial> materials = fmgreMaterialService.selectFmgreMaterialByMaterailIds(materailIds);
        bo.getItems().forEach(item -> {
            FmgreMaterial materail = materials.stream().filter(material -> material.getMaterailId().equals(item.getMaterailId())).findFirst().orElse(null);
            item.setMaterail(materail);
        });
        if(bo.getSupplierId() != null && bo.getSupplierId() != 0) { 
            FmgreSupplier supplier = fmgreSupplierService.selectFmgreSupplierBySupplierId(bo.getSupplierId());
            bo.setSupplier(supplier);
        }
        if(bo.getPaymentId() != null && bo.getPaymentId() != 0) {
            FmgreFinancePaymentDisplayBo payment = new FmgreFinancePaymentDisplayBo(fmgreFinancePaymentService.selectFmgreFinancePaymentByPaymentId(bo.getPaymentId()));
            bo.setPayment(payment);
            payment.setUser(sysUserService.selectUserById(payment.getUserId()));
        }
        return success(bo);
    }

    /**
     * 新增采购订单
     */
    @PreAuthorize("@ss.hasPermi('purchase:order:add')")
    @Log(title = "采购订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmgrePurchaseOrder fmgrePurchaseOrder)
    {
        return toAjax(fmgrePurchaseOrderService.insertFmgrePurchaseOrder(fmgrePurchaseOrder));
    }

    /**
     * 提交采购订单
     */
    @PreAuthorize("@ss.hasPermi('purchase:act:doorder')")
    @Log(title = "采购订单", businessType = BusinessType.UPDATE)
    @PostMapping("/submit")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult submit(@RequestBody FmgrePurchaseOrderSubmitBo fmgrePurchaseOrderSubmitBo)
    {
        if((fmgrePurchaseOrderSubmitBo.getOrderId() == null || fmgrePurchaseOrderSubmitBo.getOrderId() == 0)
        		&& (fmgrePurchaseOrderSubmitBo.getItems() == null || fmgrePurchaseOrderSubmitBo.getItems().isEmpty())) {
            return toAjax(0);
        }
        System.out.println("ordercode=" + fmgrePurchaseOrderSubmitBo.getOrderCode());
        if(fmgrePurchaseOrderSubmitBo.getOrderId() == null || fmgrePurchaseOrderSubmitBo.getOrderId() == 0) {
            BigDecimal totalPrice = fmgrePurchaseOrderSubmitBo.getItems().stream()
                .filter(fmgrePurchaseItem -> fmgrePurchaseItem.getItemTotalPrice() != null && (fmgrePurchaseItem.getOrderId() == null || fmgrePurchaseItem.getOrderId() == 0))
                .map(FmgrePurchaseItem::getItemTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
            fmgrePurchaseOrderSubmitBo.setOrderTotalPrice(totalPrice);
            if(
                getUserId().equals(1L) && fmgrePurchaseOrderSubmitBo.getUserId() != null && 
                    fmgrePurchaseOrderSubmitBo.getUserId() != 0) {
                        //管理员可以保留设置
            } else {
                fmgrePurchaseOrderSubmitBo.setUserId(getUserId());
            }
            fmgrePurchaseOrderSubmitBo.setSupplierId(fmgrePurchaseOrderSubmitBo.getItems().get(0).getSupplierId());
            fmgrePurchaseOrderService.insertFmgrePurchaseOrder(fmgrePurchaseOrderSubmitBo);
        } else {
            if(fmgrePurchaseOrderSubmitBo.getItems() == null) {
            	fmgrePurchaseOrderSubmitBo.setItems(new ArrayList<>());
            }
            FmgrePurchaseItem selectFmgrePurchaseItem = new FmgrePurchaseItem();
            selectFmgrePurchaseItem.setOrderId(fmgrePurchaseOrderSubmitBo.getOrderId());
            List<FmgrePurchaseItem> fmgrePurchaseItems = fmgrePurchaseItemService.selectFmgrePurchaseItemList(selectFmgrePurchaseItem);
            BigDecimal totalPrice = fmgrePurchaseItems.stream().map(FmgrePurchaseItem::getItemTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
            totalPrice = fmgrePurchaseOrderSubmitBo.getItems().stream()
                .filter(fmgrePurchaseItem -> fmgrePurchaseItem.getItemTotalPrice() != null && (fmgrePurchaseItem.getOrderId() == null || fmgrePurchaseItem.getOrderId() == 0))
                .map(FmgrePurchaseItem::getItemTotalPrice).reduce(totalPrice, BigDecimal::add);
            fmgrePurchaseOrderSubmitBo.setOrderTotalPrice(totalPrice);
            fmgrePurchaseOrderService.updateFmgrePurchaseOrder(fmgrePurchaseOrderSubmitBo);
            FmgrePurchaseOrder old = fmgrePurchaseOrderService.selectFmgrePurchaseOrderByOrderId(fmgrePurchaseOrderSubmitBo.getOrderId());
            if(fmgrePurchaseOrderSubmitBo.getOrderTime() == null) {
            	fmgrePurchaseOrderSubmitBo.setOrderTime(old.getOrderTime());
            }
            if(fmgrePurchaseOrderSubmitBo.getPaymentId() == null || fmgrePurchaseOrderSubmitBo.getPaymentId() == 0) {
            	fmgrePurchaseOrderSubmitBo.setPaymentId(old.getPaymentId());
            }
        }
        Date orderTime = fmgrePurchaseOrderSubmitBo.getOrderTime();
        if(orderTime == null) {
            orderTime = new Date();
            orderTime.setHours(0);
            orderTime.setMinutes(0);
            orderTime.setSeconds(0);
        }
        Map<Long, FmgreSupplierQuote> fmgreSupplierQuoteMap = new HashMap<>();
        fmgreSupplierQuoteService.selectFmgreSupplierQuoteListByQuoteIds(
            fmgrePurchaseOrderSubmitBo.getItems().stream().map(FmgrePurchaseItemSubmitBo::getQuotaId).collect(Collectors.toList()))
            .forEach(quote -> {
                fmgreSupplierQuoteMap.put(quote.getQuoteId(), quote);
            });
        for(FmgrePurchaseItemSubmitBo fmgrePurchaseItem : fmgrePurchaseOrderSubmitBo.getItems()) {
            FmgreSupplierQuote fmgreSupplierQuote = fmgreSupplierQuoteMap.get(fmgrePurchaseItem.getQuotaId());
            if(fmgreSupplierQuote == null) {
                throw new RuntimeException("供应商报价不存在");
            }
            if(!fmgreSupplierQuote.getMaterailId().equals(fmgrePurchaseItem.getMaterailId())
            || !fmgreSupplierQuote.getSupplierId().equals(fmgrePurchaseItem.getSupplierId())) {
                throw new RuntimeException("报价和原始需求不匹配");
            }
            if(fmgreSupplierQuote.getQuotaPrice().compareTo(fmgrePurchaseItem.getQuotePrice()) != 0) {
                fmgreSupplierQuote.setQuotaPrice(fmgrePurchaseItem.getQuotePrice());
                fmgreSupplierQuote.setQuoteId(null);
                fmgreSupplierQuote.setQuotaTime(orderTime);
                fmgreSupplierQuoteService.insertFmgreSupplierQuote(fmgreSupplierQuote);
            }
            fmgrePurchaseItem.setOrderId(fmgrePurchaseOrderSubmitBo.getOrderId());
            fmgrePurchaseItem.setQuotaId(fmgreSupplierQuote.getQuoteId());
            //重新计算采购数量和总价
            fmgrePurchaseItem.setOrderUnitDictid(fmgreSupplierQuote.getPackUnitDictid());
            fmgrePurchaseItem.setOrderAmount(fmgrePurchaseItem.getOrderNum().multiply(fmgreSupplierQuote.getPackSize()));
            fmgrePurchaseItem.setItemTotalPrice(fmgrePurchaseItem.getOrderNum().multiply(fmgrePurchaseItem.getQuotePrice()));
            fmgrePurchaseItemService.updateFmgrePurchaseItem(fmgrePurchaseItem);
        }
        BigDecimal orderTotalPrice = fmgrePurchaseOrderService.getOrdersTotalPrice(Arrays.asList(fmgrePurchaseOrderSubmitBo.getOrderId()));

        //总价以计算为准
        if(orderTotalPrice.compareTo(fmgrePurchaseOrderSubmitBo.getOrderTotalPrice()) != 0) {
            FmgrePurchaseOrder qorder = new FmgrePurchaseOrder();
            qorder.setOrderId(fmgrePurchaseOrderSubmitBo.getOrderId());
            qorder.setOrderTotalPrice(orderTotalPrice);
            fmgrePurchaseOrderService.updateFmgrePurchaseOrder(qorder);
        }
        if(fmgrePurchaseOrderSubmitBo.getPaymentId() != null && fmgrePurchaseOrderSubmitBo.getPaymentId() != 0) {
            FmgreFinancePayment old = fmgreFinancePaymentService.selectFmgreFinancePaymentByPaymentId(fmgrePurchaseOrderSubmitBo.getPaymentId());
            FmgreFinancePayment payment = new FmgreFinancePayment();
            payment.setPaymentId(old.getPaymentId());
            payment.setInAccId(old.getInAccId());
            payment.setOutAccId(old.getOutAccId());
            payment.setPaymentAmount(orderTotalPrice);
            payment.setPaymentTime(fmgrePurchaseOrderSubmitBo.getOrderTime());
            fmgreFinancePaymentService.updateFmgreFinancePayment(payment);
        } else if(fmgrePurchaseOrderSubmitBo.getAccountId() != null && fmgrePurchaseOrderSubmitBo.getAccountId() != 0) {
            FmgreFinancePaymentPayBo payBo = new FmgreFinancePaymentPayBo();
            payBo.setOrderIds(Arrays.asList(fmgrePurchaseOrderSubmitBo.getOrderId()));
            payBo.setAccountId(fmgrePurchaseOrderSubmitBo.getAccountId());
            payBo.setPaymentTime(fmgrePurchaseOrderSubmitBo.getOrderTime());
            fmgreFinancePaymentController._pay(payBo);
        }
        return toAjax(1);
    }

    /**
     * 修改采购订单
     */
    @PreAuthorize("@ss.hasPermi('purchase:order:edit')")
    @Log(title = "采购订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmgrePurchaseOrder fmgrePurchaseOrder)
    {
        return toAjax(fmgrePurchaseOrderService.updateFmgrePurchaseOrder(fmgrePurchaseOrder));
    }

    /**
     * 删除采购订单
     */
    @PreAuthorize("@ss.hasPermi('purchase:order:remove')")
    @Log(title = "采购订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds)
    {
        return toAjax(fmgrePurchaseOrderService.deleteFmgrePurchaseOrderByOrderIds(orderIds));
    }
}
