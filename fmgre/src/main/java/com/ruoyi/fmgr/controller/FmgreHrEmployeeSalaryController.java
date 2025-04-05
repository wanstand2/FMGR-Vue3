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
import com.ruoyi.fmgr.domain.FmgreHrEmployeeSalary;
import com.ruoyi.fmgr.service.IFmgreHrEmployeeSalaryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 员工薪酬Controller
 * 
 * @author terence
 * @date 2025-04-01
 */
@RestController
@RequestMapping("/hr/salary")
public class FmgreHrEmployeeSalaryController extends BaseController
{
    @Autowired
    private IFmgreHrEmployeeSalaryService fmgreHrEmployeeSalaryService;

    /**
     * 查询员工薪酬列表
     */
    @PreAuthorize("@ss.hasPermi('hr:salary:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmgreHrEmployeeSalary fmgreHrEmployeeSalary)
    {
        startPage();
        List<FmgreHrEmployeeSalary> list = fmgreHrEmployeeSalaryService.selectFmgreHrEmployeeSalaryList(fmgreHrEmployeeSalary);
        return getDataTable(list);
    }

    /**
     * 导出员工薪酬列表
     */
    @PreAuthorize("@ss.hasPermi('hr:salary:export')")
    @Log(title = "员工薪酬", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FmgreHrEmployeeSalary fmgreHrEmployeeSalary)
    {
        List<FmgreHrEmployeeSalary> list = fmgreHrEmployeeSalaryService.selectFmgreHrEmployeeSalaryList(fmgreHrEmployeeSalary);
        ExcelUtil<FmgreHrEmployeeSalary> util = new ExcelUtil<FmgreHrEmployeeSalary>(FmgreHrEmployeeSalary.class);
        util.exportExcel(response, list, "员工薪酬数据");
    }

    /**
     * 获取员工薪酬详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:salary:query')")
    @GetMapping(value = "/{employeeId}")
    public AjaxResult getInfo(@PathVariable("employeeId") Long employeeId)
    {
        return success(fmgreHrEmployeeSalaryService.selectFmgreHrEmployeeSalaryByEmployeeId(employeeId));
    }

    /**
     * 新增员工薪酬
     */
    @PreAuthorize("@ss.hasPermi('hr:salary:add')")
    @Log(title = "员工薪酬", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmgreHrEmployeeSalary fmgreHrEmployeeSalary)
    {
        return toAjax(fmgreHrEmployeeSalaryService.insertFmgreHrEmployeeSalary(fmgreHrEmployeeSalary));
    }

    /**
     * 修改员工薪酬
     */
    @PreAuthorize("@ss.hasPermi('hr:salary:edit')")
    @Log(title = "员工薪酬", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmgreHrEmployeeSalary fmgreHrEmployeeSalary)
    {
        return toAjax(fmgreHrEmployeeSalaryService.updateFmgreHrEmployeeSalary(fmgreHrEmployeeSalary));
    }

    /**
     * 删除员工薪酬
     */
    @PreAuthorize("@ss.hasPermi('hr:salary:remove')")
    @Log(title = "员工薪酬", businessType = BusinessType.DELETE)
	@DeleteMapping("/{employeeIds}")
    public AjaxResult remove(@PathVariable Long[] employeeIds)
    {
        return toAjax(fmgreHrEmployeeSalaryService.deleteFmgreHrEmployeeSalaryByEmployeeIds(employeeIds));
    }
}
