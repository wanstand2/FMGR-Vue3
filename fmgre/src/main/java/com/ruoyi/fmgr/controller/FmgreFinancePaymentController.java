package com.ruoyi.fmgr.controller;

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
import com.ruoyi.fmgr.domain.FmgreFinancePayment;
import com.ruoyi.fmgr.service.IFmgreFinancePaymentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

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
        return toAjax(fmgreFinancePaymentService.insertFmgreFinancePayment(fmgreFinancePayment));
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
