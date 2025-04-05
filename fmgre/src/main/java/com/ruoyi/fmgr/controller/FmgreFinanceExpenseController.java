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
import com.ruoyi.fmgr.domain.FmgreFinanceExpense;
import com.ruoyi.fmgr.service.IFmgreFinanceExpenseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 费用类目Controller
 * 
 * @author terence
 * @date 2025-04-01
 */
@RestController
@RequestMapping("/finance/expense")
public class FmgreFinanceExpenseController extends BaseController
{
    @Autowired
    private IFmgreFinanceExpenseService fmgreFinanceExpenseService;

    /**
     * 查询费用类目列表
     */
    @PreAuthorize("@ss.hasPermi('finance:expense:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmgreFinanceExpense fmgreFinanceExpense)
    {
        startPage();
        List<FmgreFinanceExpense> list = fmgreFinanceExpenseService.selectFmgreFinanceExpenseList(fmgreFinanceExpense);
        return getDataTable(list);
    }

    /**
     * 导出费用类目列表
     */
    @PreAuthorize("@ss.hasPermi('finance:expense:export')")
    @Log(title = "费用类目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FmgreFinanceExpense fmgreFinanceExpense)
    {
        List<FmgreFinanceExpense> list = fmgreFinanceExpenseService.selectFmgreFinanceExpenseList(fmgreFinanceExpense);
        ExcelUtil<FmgreFinanceExpense> util = new ExcelUtil<FmgreFinanceExpense>(FmgreFinanceExpense.class);
        util.exportExcel(response, list, "费用类目数据");
    }

    /**
     * 获取费用类目详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:expense:query')")
    @GetMapping(value = "/{expenseId}")
    public AjaxResult getInfo(@PathVariable("expenseId") Long expenseId)
    {
        return success(fmgreFinanceExpenseService.selectFmgreFinanceExpenseByExpenseId(expenseId));
    }

    /**
     * 新增费用类目
     */
    @PreAuthorize("@ss.hasPermi('finance:expense:add')")
    @Log(title = "费用类目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmgreFinanceExpense fmgreFinanceExpense)
    {
        return toAjax(fmgreFinanceExpenseService.insertFmgreFinanceExpense(fmgreFinanceExpense));
    }

    /**
     * 修改费用类目
     */
    @PreAuthorize("@ss.hasPermi('finance:expense:edit')")
    @Log(title = "费用类目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmgreFinanceExpense fmgreFinanceExpense)
    {
        return toAjax(fmgreFinanceExpenseService.updateFmgreFinanceExpense(fmgreFinanceExpense));
    }

    /**
     * 删除费用类目
     */
    @PreAuthorize("@ss.hasPermi('finance:expense:remove')")
    @Log(title = "费用类目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{expenseIds}")
    public AjaxResult remove(@PathVariable Long[] expenseIds)
    {
        return toAjax(fmgreFinanceExpenseService.deleteFmgreFinanceExpenseByExpenseIds(expenseIds));
    }
}
