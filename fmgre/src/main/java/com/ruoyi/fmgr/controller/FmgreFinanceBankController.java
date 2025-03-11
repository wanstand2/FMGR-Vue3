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
import com.ruoyi.fmgr.domain.FmgreFinanceBank;
import com.ruoyi.fmgr.service.IFmgreFinanceBankService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 银行Controller
 * 
 * @author terence
 * @date 2025-02-24
 */
@RestController
@RequestMapping("/finance/bank")
public class FmgreFinanceBankController extends BaseController
{
    @Autowired
    private IFmgreFinanceBankService fmgreFinanceBankService;

    /**
     * 查询银行列表
     */
    @PreAuthorize("@ss.hasPermi('finance:bank:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmgreFinanceBank fmgreFinanceBank)
    {
        startPage();
        List<FmgreFinanceBank> list = fmgreFinanceBankService.selectFmgreFinanceBankList(fmgreFinanceBank);
        return getDataTable(list);
    }

    /**
     * 导出银行列表
     */
    @PreAuthorize("@ss.hasPermi('finance:bank:export')")
    @Log(title = "银行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FmgreFinanceBank fmgreFinanceBank)
    {
        List<FmgreFinanceBank> list = fmgreFinanceBankService.selectFmgreFinanceBankList(fmgreFinanceBank);
        ExcelUtil<FmgreFinanceBank> util = new ExcelUtil<FmgreFinanceBank>(FmgreFinanceBank.class);
        util.exportExcel(response, list, "银行数据");
    }

    /**
     * 获取银行详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:bank:query')")
    @GetMapping(value = "/{bankId}")
    public AjaxResult getInfo(@PathVariable("bankId") Long bankId)
    {
        return success(fmgreFinanceBankService.selectFmgreFinanceBankBybankId(bankId));
    }

    /**
     * 新增银行
     */
    @PreAuthorize("@ss.hasPermi('finance:bank:add')")
    @Log(title = "银行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmgreFinanceBank fmgreFinanceBank)
    {
        return toAjax(fmgreFinanceBankService.insertFmgreFinanceBank(fmgreFinanceBank));
    }

    /**
     * 修改银行
     */
    @PreAuthorize("@ss.hasPermi('finance:bank:edit')")
    @Log(title = "银行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmgreFinanceBank fmgreFinanceBank)
    {
        return toAjax(fmgreFinanceBankService.updateFmgreFinanceBank(fmgreFinanceBank));
    }

    /**
     * 删除银行
     */
    @PreAuthorize("@ss.hasPermi('finance:bank:remove')")
    @Log(title = "银行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bankIds}")
    public AjaxResult remove(@PathVariable Long[] bankIds)
    {
        return toAjax(fmgreFinanceBankService.deleteFmgreFinanceBankBybankIds(bankIds));
    }
}
