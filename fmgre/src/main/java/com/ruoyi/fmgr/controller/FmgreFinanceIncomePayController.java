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
import com.ruoyi.fmgr.domain.FmgreFinanceIncomePay;
import com.ruoyi.fmgr.domain.FmgreFinanceIncomePayBo;
import com.ruoyi.fmgr.domain.FmgreFinancePayment;
import com.ruoyi.fmgr.service.IFmgreFinanceIncomePayService;

/**
 * 收入Controller
 * 
 * @author terence
 * @date 2025-04-09
 */
@RestController
@RequestMapping("/finance/income")
public class FmgreFinanceIncomePayController extends BaseController
{
    @Autowired
    private IFmgreFinanceIncomePayService fmgreFinanceIncomePayService;

    @Autowired
    private FmgreFinancePaymentController fmgreFinancePaymentController;
    
    /**
     * 查询收入列表
     */
    @PreAuthorize("@ss.hasPermi('finance:income:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmgreFinanceIncomePay fmgreFinanceIncomePay)
    {
        startPage();
        List<FmgreFinanceIncomePay> list = fmgreFinanceIncomePayService.selectFmgreFinanceIncomePayList(fmgreFinanceIncomePay);
        return getDataTable(list);
    }

    /**
     * 导出收入列表
     */
    @PreAuthorize("@ss.hasPermi('finance:income:export')")
    @Log(title = "收入", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FmgreFinanceIncomePay fmgreFinanceIncomePay)
    {
        List<FmgreFinanceIncomePay> list = fmgreFinanceIncomePayService.selectFmgreFinanceIncomePayList(fmgreFinanceIncomePay);
        ExcelUtil<FmgreFinanceIncomePay> util = new ExcelUtil<FmgreFinanceIncomePay>(FmgreFinanceIncomePay.class);
        util.exportExcel(response, list, "收入数据");
    }

    /**
     * 获取收入详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:income:query')")
    @GetMapping(value = "/{incomeId}")
    public AjaxResult getInfo(@PathVariable("incomeId") Long incomeId)
    {
        return success(fmgreFinanceIncomePayService.selectFmgreFinanceIncomePayByIncomeId(incomeId));
    }

    /**
     * 新增收入
     */
    @PreAuthorize("@ss.hasPermi('finance:income:add')")
    @Log(title = "收入", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmgreFinanceIncomePayBo fmgreFinanceIncomePay)
    {
    	int ret = fmgreFinanceIncomePayService.insertFmgreFinanceIncomePay(fmgreFinanceIncomePay);
    	this._pay(fmgreFinanceIncomePay);
        return toAjax(ret);
    }

    /**
     * 修改收入
     */
    @PreAuthorize("@ss.hasPermi('finance:income:edit')")
    @Log(title = "收入", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmgreFinanceIncomePayBo fmgreFinanceIncomePay)
    {
    	int ret = fmgreFinanceIncomePayService.updateFmgreFinanceIncomePay(fmgreFinanceIncomePay);
    	this._pay(fmgreFinanceIncomePay);
        return toAjax(ret);
    }
    
    private void _pay(FmgreFinanceIncomePayBo fmgreFinanceIncomePay) {
    	if(fmgreFinanceIncomePay.getPaymentId() != null && fmgreFinanceIncomePay.getPaymentId() > 0) {
    		//付过了
    		return;
    	}
    	if(fmgreFinanceIncomePay.getAccountId() == null || fmgreFinanceIncomePay.getAccountId() == 0) {
    		//不需要支付
    		return;
    	}
    	FmgreFinancePayment payment = new FmgreFinancePayment();
    	payment.setInAccId(fmgreFinanceIncomePay.getAccountId());
    	if(getUserId().equals(1L) && fmgreFinanceIncomePay.getUserId() != null && fmgreFinanceIncomePay.getUserId() != 0) {
    		//管理员可以保留设置
    		payment.setUserId(fmgreFinanceIncomePay.getUserId());
        } else {
        	payment.setUserId(getUserId());
        }
    	payment.setPaymentComment("收入:"+fmgreFinanceIncomePay.getIncomeName());
    	payment.setPaymentAmount(fmgreFinanceIncomePay.getIncomeSubtotal());
    	if(fmgreFinanceIncomePay.getPaymentTime() != null) {
    		payment.setPaymentTime(fmgreFinanceIncomePay.getPaymentTime());
    	} else {
    		payment.setPaymentTime(new Date());
    	}
    	fmgreFinancePaymentController._add(payment);
    	FmgreFinanceIncomePay payUpdate = new FmgreFinanceIncomePay();
        payUpdate.setIncomeId(fmgreFinanceIncomePay.getIncomeId());
        payUpdate.setPaymentId(payment.getPaymentId());
        fmgreFinanceIncomePayService.updateFmgreFinanceIncomePay(payUpdate);
    }


    /**
     * 删除收入
     */
    @PreAuthorize("@ss.hasPermi('finance:income:remove')")
    @Log(title = "收入", businessType = BusinessType.DELETE)
	@DeleteMapping("/{incomeIds}")
    public AjaxResult remove(@PathVariable Long[] incomeIds)
    {
        return toAjax(fmgreFinanceIncomePayService.deleteFmgreFinanceIncomePayByIncomeIds(incomeIds));
    }
}
