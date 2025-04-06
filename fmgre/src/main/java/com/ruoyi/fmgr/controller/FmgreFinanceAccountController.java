package com.ruoyi.fmgr.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.fmgr.domain.FmgreFinanceAccount;
import com.ruoyi.fmgr.domain.FmgreFinanceBank;
import com.ruoyi.fmgr.service.IFmgreFinanceAccountService;
import com.ruoyi.fmgr.service.IFmgreFinanceBankService;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
/**
 * 银行账户Controller
 * 
 * @author terence
 * @date 2025-02-24
 */
@RestController
@RequestMapping("/finance/account")
public class FmgreFinanceAccountController extends BaseController
{
    @Autowired
    private IFmgreFinanceAccountService fmgreFinanceAccountService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private IFmgreFinanceBankService fmgreFinanceBankService;

    /**
     * 查询银行账户列表
     */
    @PreAuthorize("@ss.hasPermi('finance:account:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmgreFinanceAccount fmgreFinanceAccount)
    {
        startPage();
        List<FmgreFinanceAccount> list = fmgreFinanceAccountService.selectFmgreFinanceAccountList(fmgreFinanceAccount);
        List<Long> userIds = list.stream().map(FmgreFinanceAccount::getUserId).collect(Collectors.toList());
        List<Long> deptIds = list.stream().map(FmgreFinanceAccount::getDeptId).collect(Collectors.toList());
        List<Long> bankIds = list.stream().map(FmgreFinanceAccount::getBankId).collect(Collectors.toList());
        List<SysUser> users = userService.selectUserByIds(userIds);
        List<SysDept> depts = deptService.selectDeptByIds(deptIds);
        List<FmgreFinanceBank> banks = fmgreFinanceBankService.selectFmgreFinanceBankListByIds(bankIds);
        list.forEach(item -> {
            if(item.getAccountDictid().equals("Personal")) {
              users.stream().filter(user -> user.getUserId().equals(item.getUserId())).findFirst().ifPresent(user -> item.setAccountDisplay(user.getNickName()));
            } else {
              depts.stream().filter(dept -> dept.getDeptId().equals(item.getDeptId())).findFirst().ifPresent(dept -> item.setAccountDisplay(dept.getDeptName()));
            }
        });
        list.forEach(item -> {
            banks.stream().filter(bank -> bank.getbankId().equals(item.getBankId())).findFirst().ifPresent(bank -> item.setAccountDisplay(
                item.getAccountDisplay() + "@" + bank.getBankName() + "(" + item.getAccountAlias() + ")"));
        });
        return getDataTable(list);
    }

    /**
     * 导出银行账户列表
     */
    @PreAuthorize("@ss.hasPermi('finance:account:export')")
    @Log(title = "银行账户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FmgreFinanceAccount fmgreFinanceAccount)
    {
        List<FmgreFinanceAccount> list = fmgreFinanceAccountService.selectFmgreFinanceAccountList(fmgreFinanceAccount);
        ExcelUtil<FmgreFinanceAccount> util = new ExcelUtil<FmgreFinanceAccount>(FmgreFinanceAccount.class);
        util.exportExcel(response, list, "银行账户数据");
    }

    /**
     * 获取银行账户详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:account:query')")
    @GetMapping(value = "/{accountId}")
    public AjaxResult getInfo(@PathVariable("accountId") Long accountId)
    {
        return success(fmgreFinanceAccountService.selectFmgreFinanceAccountByAccountId(accountId));
    }

    /**
     * 新增银行账户
     */
    @PreAuthorize("@ss.hasPermi('finance:account:add')")
    @Log(title = "银行账户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmgreFinanceAccount fmgreFinanceAccount)
    {
        return toAjax(fmgreFinanceAccountService.insertFmgreFinanceAccount(fmgreFinanceAccount));
    }

    /**
     * 修改银行账户
     */
    @PreAuthorize("@ss.hasPermi('finance:account:edit')")
    @Log(title = "银行账户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmgreFinanceAccount fmgreFinanceAccount)
    {
        return toAjax(fmgreFinanceAccountService.updateFmgreFinanceAccount(fmgreFinanceAccount));
    }

    /**
     * 删除银行账户
     */
    @PreAuthorize("@ss.hasPermi('finance:account:remove')")
    @Log(title = "银行账户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{accountIds}")
    public AjaxResult remove(@PathVariable Long[] accountIds)
    {
        return toAjax(fmgreFinanceAccountService.deleteFmgreFinanceAccountByAccountIds(accountIds));
    }
}
