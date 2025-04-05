package com.ruoyi.fmgr.controller;

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
import com.ruoyi.fmgr.domain.FmgreFinanceExpensesPay;
import com.ruoyi.fmgr.domain.FmgreFinanceExpensesPayBo;
import com.ruoyi.fmgr.domain.FmgreFinancePayment;
import com.ruoyi.fmgr.service.IFmgreFinanceExpensesPayService;

/**
 * 费用支付Controller
 * 
 * @author terence
 * @date 2025-04-01
 */
@RestController
@RequestMapping("/finance/expensepay")
public class FmgreFinanceExpensesPayController extends BaseController
{
    @Autowired
    private IFmgreFinanceExpensesPayService fmgreFinanceExpensesPayService;
    
    @Autowired
    private FmgreFinancePaymentController fmgreFinancePaymentController;

    /**
     * 查询费用支付列表
     */
    @PreAuthorize("@ss.hasPermi('finance:expensepay:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmgreFinanceExpensesPay fmgreFinanceExpensesPay)
    {
        startPage();
        List<FmgreFinanceExpensesPayBo> list = fmgreFinanceExpensesPayService.selectFmgreFinanceExpensesPayList(fmgreFinanceExpensesPay);
        return getDataTable(list);
    }

    /**
     * 导出费用支付列表
     */
    @PreAuthorize("@ss.hasPermi('finance:expensepay:export')")
    @Log(title = "费用支付", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FmgreFinanceExpensesPay fmgreFinanceExpensesPay)
    {
        List list = fmgreFinanceExpensesPayService.selectFmgreFinanceExpensesPayList(fmgreFinanceExpensesPay);
        ExcelUtil<FmgreFinanceExpensesPay> util = new ExcelUtil<FmgreFinanceExpensesPay>(FmgreFinanceExpensesPay.class);
        util.exportExcel(response, list, "费用支付数据");
    }

    /**
     * 获取费用支付详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:expensepay:query')")
    @GetMapping(value = "/{payId}")
    public AjaxResult getInfo(@PathVariable("payId") Long payId)
    {
        return success(fmgreFinanceExpensesPayService.selectFmgreFinanceExpensesPayByPayId(payId));
    }

    /**
     * 新增费用支付
     */
    @PreAuthorize("@ss.hasPermi('finance:expensepay:add')")
    @Log(title = "费用支付", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmgreFinanceExpensesPayBo fmgreFinanceExpensesPay)
    {
    	int ret = fmgreFinanceExpensesPayService.insertFmgreFinanceExpensesPay(fmgreFinanceExpensesPay);
    	this._pay(fmgreFinanceExpensesPay);
        return toAjax(ret);
    }

    /**
     * 修改费用支付
     */
    @PreAuthorize("@ss.hasPermi('finance:expensepay:edit')")
    @Log(title = "费用支付", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmgreFinanceExpensesPayBo fmgreFinanceExpensesPay)
    {
    	int ret = fmgreFinanceExpensesPayService.updateFmgreFinanceExpensesPay(fmgreFinanceExpensesPay);
    	this._pay(fmgreFinanceExpensesPay);
        return toAjax(ret);
    }
    
    private void _pay(FmgreFinanceExpensesPayBo fmgreFinanceExpensesPay) {
    	if(fmgreFinanceExpensesPay.getPaymentId() != null && fmgreFinanceExpensesPay.getPaymentId() > 0) {
    		//付过了
    		return;
    	}
    	if(fmgreFinanceExpensesPay.getAccountId() == null || fmgreFinanceExpensesPay.getAccountId() == 0) {
    		//不需要支付
    		return;
    	}
    	FmgreFinancePayment payment = new FmgreFinancePayment();
    	payment.setOutAccId(fmgreFinanceExpensesPay.getAccountId());
    	if(getUserId().equals(1L) && fmgreFinanceExpensesPay.getUserId() != null && fmgreFinanceExpensesPay.getUserId() != 0) {
    		//管理员可以保留设置
    		payment.setUserId(fmgreFinanceExpensesPay.getUserId());
        } else {
        	payment.setUserId(getUserId());
        }
    	payment.setPaymentComment("支付费用:"+fmgreFinanceExpensesPay.getPayName());
    	payment.setPaymentAmount(fmgreFinanceExpensesPay.getPaySubtotal());
    	if(fmgreFinanceExpensesPay.getPayTime() != null) {
    		payment.setPaymentTime(fmgreFinanceExpensesPay.getPayTime());
    	} else {
    		payment.setPaymentTime(new Date());
    	}
    	fmgreFinancePaymentController._add(payment);
    	FmgreFinanceExpensesPay payUpdate = new FmgreFinanceExpensesPay();
        payUpdate.setPayId(fmgreFinanceExpensesPay.getPayId());
        payUpdate.setPaymentId(payment.getPaymentId());
        fmgreFinanceExpensesPayService.updateFmgreFinanceExpensesPay(payUpdate);
    }

    /**
     * 删除费用支付
     */
    @PreAuthorize("@ss.hasPermi('finance:expensepay:remove')")
    @Log(title = "费用支付", businessType = BusinessType.DELETE)
	@DeleteMapping("/{payIds}")
    public AjaxResult remove(@PathVariable Long[] payIds)
    {
        return toAjax(fmgreFinanceExpensesPayService.deleteFmgreFinanceExpensesPayByPayIds(payIds));
    }
}
