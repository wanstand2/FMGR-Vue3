package com.ruoyi.fmgr.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.fmgr.domain.FmgreFinancePayment;
import com.ruoyi.fmgr.domain.FmgreFinancePaymentPayBo;
import com.ruoyi.fmgr.domain.FmgrePurchaseOrder;
import com.ruoyi.fmgr.service.IFmgreFinanceAccountBalanceService;
import com.ruoyi.fmgr.service.IFmgreFinancePaymentService;
import com.ruoyi.fmgr.service.IFmgrePurchaseOrderService;

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

    @PreAuthorize("@ss.hasPermi('finance:payment:edit')")
    @GetMapping(value = "refresh/{paymentId}")
    public AjaxResult refresh(@PathVariable("paymentId") Long paymentId)
    {
        FmgreFinancePayment payment = new FmgreFinancePayment();
        payment.setPaymentId(paymentId);
        return success(fmgreFinancePaymentService.updateFmgreFinancePayment(payment));
    }


    /**
     * 新增付款流水
     */
    @PreAuthorize("@ss.hasPermi('finance:payment:add')")
    @Log(title = "付款流水", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmgreFinancePayment fmgreFinancePayment)
    {
        return toAjax(this._add(fmgreFinancePayment));
    }

    public int _add(@RequestBody FmgreFinancePayment fmgreFinancePayment) {
        if(fmgreFinancePayment.getPaymentTime() == null) {
            fmgreFinancePayment.setPaymentTime(new Date());
        }
        int ret = fmgreFinancePaymentService.insertFmgreFinancePayment(fmgreFinancePayment);
        if(ret > 0) {
            afterEdit(fmgreFinancePayment);
        }
        return ret;
    }

    public void afterEdit(FmgreFinancePayment fmgreFinancePayment) {
        if(fmgreFinancePayment.getOrderId() != null) {
            FmgrePurchaseOrder order = new FmgrePurchaseOrder();
            order.setOrderId(fmgreFinancePayment.getOrderId());
            order.setPaymentId(fmgreFinancePayment.getPaymentId());
            fmgrePurchaseOrderService.updateFmgrePurchaseOrder(order);
        }
    }

    /**
     * 向订单付款
     */
    @PreAuthorize("@ss.hasPermi('finance:payment:add')")
    @Log(title = "付款流水", businessType = BusinessType.INSERT)
    @PostMapping(value = "/pay")
    public AjaxResult pay(@RequestBody FmgreFinancePaymentPayBo fmgreFinancePaymentPayBo) {
    	return toAjax(this._pay(fmgreFinancePaymentPayBo));
    }
    
    public int _pay(@RequestBody FmgreFinancePaymentPayBo fmgreFinancePaymentPayBo) {
        if(fmgreFinancePaymentPayBo.getOrderIds() == null || fmgreFinancePaymentPayBo.getOrderIds().size() == 0) {
            return 0;
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
                fmgreFinancePaymentPayBo.setComment("支付多个订单："+order.getOrderCode());
            }
        }
        if(fmgreFinancePaymentPayBo.getOrderIds().size() > 1) {
            fmgreFinancePaymentPayBo.setComment(fmgreFinancePaymentPayBo.getComment() + "-订单" + fmgreFinancePaymentPayBo.getOrderIds().size() + "个");
        }
        fmgreFinancePayment.setUserId(getUserId());
        fmgreFinancePayment.setPaymentComment(fmgreFinancePaymentPayBo.getComment());
        this._add(fmgreFinancePayment);
        if(fmgreFinancePaymentPayBo.getOrderIds().size() > 1) {
            fmgrePurchaseOrderService.updateFmgrePurchaseOrderPaymentId(fmgreFinancePaymentPayBo.getOrderIds(), fmgreFinancePayment.getPaymentId());
        }
        return fmgreFinancePaymentPayBo.getOrderIds().size();
    }

    /**
     * 修改付款流水
     */
    @PreAuthorize("@ss.hasPermi('finance:payment:edit')")
    @Log(title = "付款流水", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmgreFinancePayment fmgreFinancePayment)
    {
        int ret = fmgreFinancePaymentService.updateFmgreFinancePayment(fmgreFinancePayment);
        if(ret > 0) {
            afterEdit(fmgreFinancePayment);
        }
        return toAjax(ret);
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
