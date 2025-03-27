package com.ruoyi.fmgr.controller;

import java.util.List;
import java.math.BigDecimal;
import java.util.Date;
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
import com.ruoyi.fmgr.domain.FmgreFinancePayment;
import com.ruoyi.fmgr.domain.FmgreFinancePaymentPayBo;
import com.ruoyi.fmgr.service.IFmgreFinancePaymentService;
import com.ruoyi.fmgr.domain.FmgrePurchaseOrder;
import com.ruoyi.fmgr.service.IFmgrePurchaseOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.fmgr.service.IFmgreFinanceAccountBalanceService;

/**
 * 付款流水Controller
 * 
 * @author terence
 * @date 2025-03-10
 */
@RestController
@RequestMapping("/finance/payment")
public class FmgreFinancePaymentController extends BaseController
{
    @Autowired
    private IFmgreFinancePaymentService fmgreFinancePaymentService;

    @Autowired
    private IFmgrePurchaseOrderService fmgrePurchaseOrderService;

    @Autowired
    private IFmgreFinanceAccountBalanceService fmgreFinanceAccountBalanceService;

    /**
     * 查询付款流水列表
     */
    @PreAuthorize("@ss.hasPermi('finance:payment:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmgreFinancePayment fmgreFinancePayment)
    {
        startPage();
        List<FmgreFinancePayment> list = fmgreFinancePaymentService.selectFmgreFinancePaymentList(fmgreFinancePayment);
        return getDataTable(list);
    }

    /**
     * 导出付款流水列表
     */
    @PreAuthorize("@ss.hasPermi('finance:payment:export')")
    @Log(title = "付款流水", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FmgreFinancePayment fmgreFinancePayment)
    {
        List<FmgreFinancePayment> list = fmgreFinancePaymentService.selectFmgreFinancePaymentList(fmgreFinancePayment);
        ExcelUtil<FmgreFinancePayment> util = new ExcelUtil<FmgreFinancePayment>(FmgreFinancePayment.class);
        util.exportExcel(response, list, "付款流水数据");
    }

    /**
     * 获取付款流水详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:payment:query')")
    @GetMapping(value = "/{paymentId}")
    public AjaxResult getInfo(@PathVariable("paymentId") Long paymentId)
    {
        return success(fmgreFinancePaymentService.selectFmgreFinancePaymentByPaymentId(paymentId));
    }

    /**
     * 新增付款流水
     */
    @PreAuthorize("@ss.hasPermi('finance:payment:add')")
    @Log(title = "付款流水", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmgreFinancePayment fmgreFinancePayment)
    {
        if(fmgreFinancePayment.getPaymentTime() == null) {
            fmgreFinancePayment.setPaymentTime(new Date());
        }
        int ret = fmgreFinancePaymentService.insertFmgreFinancePayment(fmgreFinancePayment);
        if(ret > 0 && fmgreFinancePayment.getOrderId() != null) {
            FmgrePurchaseOrder order = new FmgrePurchaseOrder();
            order.setOrderId(fmgreFinancePayment.getOrderId());
            order.setPaymentId(fmgreFinancePayment.getPaymentId());
            fmgrePurchaseOrderService.updateFmgrePurchaseOrder(order);
        }
        if(fmgreFinancePayment.getOutAccId() != null) { 
            fmgreFinanceAccountBalanceService.updateFmgreFinanceAccountBalanceByAccountId(fmgreFinancePayment.getOutAccId());
        }
        if(fmgreFinancePayment.getInAccId() != null) {
            fmgreFinanceAccountBalanceService.updateFmgreFinanceAccountBalanceByAccountId(fmgreFinancePayment.getInAccId());
        }
        FmgreFinancePayment updPayment = new FmgreFinancePayment();
        updPayment.setPaymentId(fmgreFinancePayment.getPaymentId());
        if(fmgreFinancePayment.getOutAccId() != null) { 
            updPayment.setOutAccBanlance(fmgreFinanceAccountBalanceService.selectFmgreFinanceAccountBalanceByAccountId(fmgreFinancePayment.getOutAccId()).getAccountBalance());
        }
        if(fmgreFinancePayment.getInAccId() != null) {
            updPayment.setInAccBanlance(fmgreFinanceAccountBalanceService.selectFmgreFinanceAccountBalanceByAccountId(fmgreFinancePayment.getInAccId()).getAccountBalance());
        }
        fmgreFinancePaymentService.updateFmgreFinancePayment(updPayment);
        return toAjax(ret);
    }

    /**
     * 向订单付款
     */
    @PreAuthorize("@ss.hasPermi('finance:payment:add')")
    @Log(title = "付款流水", businessType = BusinessType.INSERT)
    @PostMapping(value = "/pay")
    public AjaxResult pay(@RequestBody FmgreFinancePaymentPayBo fmgreFinancePaymentPayBo) {
    	return this._pay(fmgreFinancePaymentPayBo);
    }
    
    public AjaxResult _pay(@RequestBody FmgreFinancePaymentPayBo fmgreFinancePaymentPayBo) {
        if(fmgreFinancePaymentPayBo.getOrderIds() == null || fmgreFinancePaymentPayBo.getOrderIds().size() == 0) {
            return toAjax(0);
        }
        BigDecimal total = fmgrePurchaseOrderService.getOrdersTotalPrice(fmgreFinancePaymentPayBo.getOrderIds());
        FmgreFinancePayment fmgreFinancePayment = new FmgreFinancePayment();
        fmgreFinancePayment.setOutAccId(fmgreFinancePaymentPayBo.getAccountId());
        fmgreFinancePayment.setOrderId(fmgreFinancePaymentPayBo.getOrderIds().get(0));
        fmgreFinancePayment.setPaymentTime(fmgreFinancePaymentPayBo.getPaymentTime()); 
        fmgreFinancePayment.setPaymentAmount(total);
        FmgrePurchaseOrder order = fmgrePurchaseOrderService.selectFmgrePurchaseOrderByOrderId(fmgreFinancePaymentPayBo.getOrderIds().get(0));
        if(fmgreFinancePaymentPayBo.getComment() == null || fmgreFinancePaymentPayBo.getComment().length() == 0) {
            if(fmgreFinancePaymentPayBo.getOrderIds().size() < 2) {
                fmgreFinancePaymentPayBo.setComment("支付订单："+order.getOrderCode());
            } else {
                fmgreFinancePaymentPayBo.setComment("支付多个订单（"+fmgreFinancePaymentPayBo.getOrderIds().size()+"）："+order.getOrderCode());
            }
        }
        fmgreFinancePayment.setPaymentComment(fmgreFinancePaymentPayBo.getComment() == null ? "订单付款" : fmgreFinancePaymentPayBo.getComment());
        fmgreFinancePayment.setUserId(getUserId());
        this.add(fmgreFinancePayment);
        fmgrePurchaseOrderService.updateFmgrePurchaseOrderPaymentId(fmgreFinancePaymentPayBo.getOrderIds(), fmgreFinancePayment.getPaymentId());
        return toAjax(fmgreFinancePaymentPayBo.getOrderIds().size());
    }

    /**
     * 修改付款流水
     */
    @PreAuthorize("@ss.hasPermi('finance:payment:edit')")
    @Log(title = "付款流水", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmgreFinancePayment fmgreFinancePayment)
    {
        return toAjax(fmgreFinancePaymentService.updateFmgreFinancePayment(fmgreFinancePayment));
    }

    /**
     * 删除付款流水
     */
    @PreAuthorize("@ss.hasPermi('finance:payment:remove')")
    @Log(title = "付款流水", businessType = BusinessType.DELETE)
	@DeleteMapping("/{paymentIds}")
    public AjaxResult remove(@PathVariable Long[] paymentIds)
    {
        return toAjax(fmgreFinancePaymentService.deleteFmgreFinancePaymentByPaymentIds(paymentIds));
    }
}
