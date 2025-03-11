package com.ruoyi.fmgr.controller;

import java.math.BigDecimal;
import java.util.List;
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
import com.ruoyi.fmgr.service.IFmgreFinancePaymentService;
import com.ruoyi.fmgr.domain.FmgreSupplierQuote;
import com.ruoyi.fmgr.service.IFmgreSupplierQuoteService;
import com.ruoyi.fmgr.domain.FmgrePurchaseItemBo;
import org.springframework.transaction.annotation.Transactional;
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

    /**
     * 查询采购订单列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmgrePurchaseOrder fmgrePurchaseOrder)
    {
        startPage();
        List<FmgrePurchaseOrder> list = fmgrePurchaseOrderService.selectFmgrePurchaseOrderList(fmgrePurchaseOrder);
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
        return success(fmgrePurchaseOrderService.selectFmgrePurchaseOrderByOrderId(orderId));
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
    @Log(title = "采购订单", businessType = BusinessType.INSERT)
    @PostMapping("/submit")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult submit(@RequestBody FmgrePurchaseOrderSubmitBo fmgrePurchaseOrderSubmitBo)
    {
        if(fmgrePurchaseOrderSubmitBo.getItems() == null || fmgrePurchaseOrderSubmitBo.getItems().isEmpty()) {
            return toAjax(0);
        }
        if(fmgrePurchaseOrderSubmitBo.getOrderId() == null || fmgrePurchaseOrderSubmitBo.getOrderId() == 0) {
            BigDecimal totalPrice = fmgrePurchaseOrderSubmitBo.getItems().stream()
                .filter(fmgrePurchaseItem -> fmgrePurchaseItem.getItemTotalPrice() != null && (fmgrePurchaseItem.getOrderId() == null || fmgrePurchaseItem.getOrderId() == 0))
                .map(FmgrePurchaseItem::getItemTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
            fmgrePurchaseOrderSubmitBo.setOrderTotalPrice(totalPrice);
            fmgrePurchaseOrderSubmitBo.setUserId(getUserId());
            fmgrePurchaseOrderSubmitBo.setSupplierId(fmgrePurchaseOrderSubmitBo.getItems().get(0).getSupplierId());
            fmgrePurchaseOrderService.insertFmgrePurchaseOrder(fmgrePurchaseOrderSubmitBo);
        } else {
            FmgrePurchaseItem selectFmgrePurchaseItem = new FmgrePurchaseItem();
            selectFmgrePurchaseItem.setOrderId(fmgrePurchaseOrderSubmitBo.getOrderId());
            List<FmgrePurchaseItem> fmgrePurchaseItems = fmgrePurchaseItemService.selectFmgrePurchaseItemList(selectFmgrePurchaseItem);
            BigDecimal totalPrice = fmgrePurchaseItems.stream().map(FmgrePurchaseItem::getItemTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
            totalPrice = fmgrePurchaseOrderSubmitBo.getItems().stream()
                .filter(fmgrePurchaseItem -> fmgrePurchaseItem.getItemTotalPrice() != null && (fmgrePurchaseItem.getOrderId() == null || fmgrePurchaseItem.getOrderId() == 0))
                .map(FmgrePurchaseItem::getItemTotalPrice).reduce(totalPrice, BigDecimal::add);
            fmgrePurchaseOrderSubmitBo.setOrderTotalPrice(totalPrice);
            fmgrePurchaseOrderService.updateFmgrePurchaseOrder(fmgrePurchaseOrderSubmitBo);
        }
        List<FmgreSupplierQuote> fmgreSupplierQuotes = fmgreSupplierQuoteService.selectFmgreSupplierQuoteListByMaterailIdPackUnitDictidLatest(fmgrePurchaseOrderSubmitBo.getSupplierId());
        for(FmgrePurchaseItemBo fmgrePurchaseItem : fmgrePurchaseOrderSubmitBo.getItems()) {
            FmgreSupplierQuote fmgreSupplierQuote = fmgreSupplierQuotes.stream().filter(quote -> 
            quote.getMaterailId().equals(fmgrePurchaseItem.getMaterailId())
                && quote.getPackUnitDictid().equals(fmgrePurchaseItem.getQuoteUnitDictid())).findFirst().orElse(null);
            if(fmgreSupplierQuote == null ||
                !fmgreSupplierQuote.getQuotaPrice().equals(fmgrePurchaseItem.getQuotePrice())) {
                FmgreSupplierQuote newQuote = new FmgreSupplierQuote();
                newQuote.setMaterailId(fmgrePurchaseItem.getMaterailId());
                newQuote.setPackUnitDictid(fmgrePurchaseItem.getQuoteUnitDictid());
                newQuote.setSupplierId(fmgrePurchaseOrderSubmitBo.getSupplierId());
                newQuote.setSupplierBrandId(fmgrePurchaseItem.getBrandId());
                newQuote.setQuotaPrice(fmgrePurchaseItem.getQuotePrice());
                newQuote.setQuotaTime(fmgrePurchaseOrderSubmitBo.getOrderTime());
                if(fmgreSupplierQuote != null) {
                    //已有过同类报价，则使用同类报价的规格
                    newQuote.setPackSize(fmgreSupplierQuote.getPackSize());
                    newQuote.setSubPackNum(fmgreSupplierQuote.getSubPackNum());
                    newQuote.setSubPackUnitDictid(fmgreSupplierQuote.getSubPackUnitDictid());
                    newQuote.setSubPackSize(fmgreSupplierQuote.getSubPackSize());
                }
                fmgreSupplierQuoteService.insertFmgreSupplierQuote(newQuote);
                fmgreSupplierQuote = newQuote;
            }
            fmgrePurchaseItem.setOrderId(fmgrePurchaseOrderSubmitBo.getOrderId());
            fmgrePurchaseItem.setQuotaId(fmgreSupplierQuote.getQuoteId());
            fmgrePurchaseItemService.updateFmgrePurchaseItem(fmgrePurchaseItem);
        }
        if(fmgrePurchaseOrderSubmitBo.getAccountId() != null && fmgrePurchaseOrderSubmitBo.getAccountId() != 0) {
            FmgreFinancePayment fmgreFinancePayment = new FmgreFinancePayment();
            fmgreFinancePayment.setOutAccId(fmgrePurchaseOrderSubmitBo.getAccountId());
            fmgreFinancePayment.setPaymentAmount(fmgrePurchaseOrderSubmitBo.getOrderTotalPrice());
            fmgreFinancePayment.setUserId(getUserId());
            fmgreFinancePayment.setOrderId(fmgrePurchaseOrderSubmitBo.getOrderId());
            fmgreFinancePaymentService.insertFmgreFinancePayment(fmgreFinancePayment);
            FmgrePurchaseOrder upOrder = new FmgrePurchaseOrder();
            upOrder.setOrderId(fmgrePurchaseOrderSubmitBo.getOrderId());
            upOrder.setPaymentId(fmgreFinancePayment.getPaymentId());
            fmgrePurchaseOrderService.updateFmgrePurchaseOrder(upOrder);
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
