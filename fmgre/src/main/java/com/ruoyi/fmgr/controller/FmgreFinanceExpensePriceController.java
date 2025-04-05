package com.ruoyi.fmgr.controller;

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

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.fmgr.domain.FmgreFinanceExpensePrice;
import com.ruoyi.fmgr.service.IFmgreFinanceExpensePriceService;

/**
 * 费用类目价格Controller
 * 
 * @author terence
 * @date 2025-04-01
 */
@RestController
@RequestMapping("/finance/expenseprice")
public class FmgreFinanceExpensePriceController extends BaseController
{
    @Autowired
    private IFmgreFinanceExpensePriceService fmgreFinanceExpensePriceService;

    /**
     * 查询费用类目价格列表
     */
    @PreAuthorize("@ss.hasPermi('finance:expenseprice:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d")
    public TableDataInfo list(FmgreFinanceExpensePrice fmgreFinanceExpensePrice)
    {
        startPage();
        List<FmgreFinanceExpensePrice> list = fmgreFinanceExpensePriceService.selectFmgreFinanceExpensePriceList(fmgreFinanceExpensePrice);
        return getDataTable(list);
    }

    /**
     * 导出费用类目价格列表
     */
    @PreAuthorize("@ss.hasPermi('finance:expenseprice:export')")
    @Log(title = "费用类目价格", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @DataScope(deptAlias = "d")
    public void export(HttpServletResponse response, FmgreFinanceExpensePrice fmgreFinanceExpensePrice)
    {
        List<FmgreFinanceExpensePrice> list = fmgreFinanceExpensePriceService.selectFmgreFinanceExpensePriceList(fmgreFinanceExpensePrice);
        ExcelUtil<FmgreFinanceExpensePrice> util = new ExcelUtil<FmgreFinanceExpensePrice>(FmgreFinanceExpensePrice.class);
        util.exportExcel(response, list, "费用类目价格数据");
    }

    /**
     * 获取费用类目价格详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:expenseprice:query')")
    @GetMapping(value = "/{priceId}")
    @DataScope(deptAlias = "d")
    public AjaxResult getInfo(@PathVariable("priceId") Long priceId)
    {
        return success(fmgreFinanceExpensePriceService.selectFmgreFinanceExpensePriceByPriceId(priceId));
    }

    /**
     * 新增费用类目价格
     */
    @PreAuthorize("@ss.hasPermi('finance:expenseprice:add')")
    @Log(title = "费用类目价格", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmgreFinanceExpensePrice fmgreFinanceExpensePrice)
    {
        return toAjax(fmgreFinanceExpensePriceService.insertFmgreFinanceExpensePrice(fmgreFinanceExpensePrice));
    }

    /**
     * 修改费用类目价格
     */
    @PreAuthorize("@ss.hasPermi('finance:expenseprice:edit')")
    @Log(title = "费用类目价格", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmgreFinanceExpensePrice fmgreFinanceExpensePrice)
    {
        return toAjax(fmgreFinanceExpensePriceService.updateFmgreFinanceExpensePrice(fmgreFinanceExpensePrice));
    }

    /**
     * 删除费用类目价格
     */
    @PreAuthorize("@ss.hasPermi('finance:expenseprice:remove')")
    @Log(title = "费用类目价格", businessType = BusinessType.DELETE)
	@DeleteMapping("/{priceIds}")
    public AjaxResult remove(@PathVariable Long[] priceIds)
    {
        return toAjax(fmgreFinanceExpensePriceService.deleteFmgreFinanceExpensePriceByPriceIds(priceIds));
    }
}
