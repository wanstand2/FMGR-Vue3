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
import com.ruoyi.fmgr.domain.FmgreFinanceAccountBalance;
import com.ruoyi.fmgr.service.IFmgreFinanceAccountBalanceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 银行账户余额Controller
 * 
 * @author terence
 * @date 2025-02-24
 */
@RestController
@RequestMapping("/finance/balance")
public class FmgreFinanceAccountBalanceController extends BaseController
{
    @Autowired
    private IFmgreFinanceAccountBalanceService fmgreFinanceAccountBalanceService;

    /**
     * 查询银行账户余额列表
     */
    @PreAuthorize("@ss.hasPermi('finance:balance:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmgreFinanceAccountBalance fmgreFinanceAccountBalance)
    {
        startPage();
        List<FmgreFinanceAccountBalance> list = fmgreFinanceAccountBalanceService.selectFmgreFinanceAccountBalanceList(fmgreFinanceAccountBalance);
        return getDataTable(list);
    }

    /**
     * 导出银行账户余额列表
     */
    @PreAuthorize("@ss.hasPermi('finance:balance:export')")
    @Log(title = "银行账户余额", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FmgreFinanceAccountBalance fmgreFinanceAccountBalance)
    {
        List<FmgreFinanceAccountBalance> list = fmgreFinanceAccountBalanceService.selectFmgreFinanceAccountBalanceList(fmgreFinanceAccountBalance);
        ExcelUtil<FmgreFinanceAccountBalance> util = new ExcelUtil<FmgreFinanceAccountBalance>(FmgreFinanceAccountBalance.class);
        util.exportExcel(response, list, "银行账户余额数据");
    }

    /**
     * 获取银行账户余额详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:balance:query')")
    @GetMapping(value = "/{accountId}")
    public AjaxResult getInfo(@PathVariable("accountId") Long accountId)
    {
        return success(fmgreFinanceAccountBalanceService.selectFmgreFinanceAccountBalanceByAccountId(accountId));
    }

    /**
     * 新增银行账户余额
     */
    @PreAuthorize("@ss.hasPermi('finance:balance:add')")
    @Log(title = "银行账户余额", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmgreFinanceAccountBalance fmgreFinanceAccountBalance)
    {
        return toAjax(fmgreFinanceAccountBalanceService.insertFmgreFinanceAccountBalance(fmgreFinanceAccountBalance));
    }

    /**
     * 修改银行账户余额
     */
    @PreAuthorize("@ss.hasPermi('finance:balance:edit')")
    @Log(title = "银行账户余额", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmgreFinanceAccountBalance fmgreFinanceAccountBalance)
    {
        return toAjax(fmgreFinanceAccountBalanceService.updateFmgreFinanceAccountBalance(fmgreFinanceAccountBalance));
    }

    /**
     * 删除银行账户余额
     */
    @PreAuthorize("@ss.hasPermi('finance:balance:remove')")
    @Log(title = "银行账户余额", businessType = BusinessType.DELETE)
	@DeleteMapping("/{accountIds}")
    public AjaxResult remove(@PathVariable Long[] accountIds)
    {
        return toAjax(fmgreFinanceAccountBalanceService.deleteFmgreFinanceAccountBalanceByAccountIds(accountIds));
    }
}
