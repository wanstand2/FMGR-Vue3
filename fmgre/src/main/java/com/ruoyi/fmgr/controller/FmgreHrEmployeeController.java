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

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.fmgr.domain.FmgreHrEmployee;
import com.ruoyi.fmgr.domain.FmgreHrEmployeeSalary;
import com.ruoyi.fmgr.service.IFmgreHrEmployeeSalaryService;
import com.ruoyi.fmgr.service.IFmgreHrEmployeeService;

/**
 * 员工Controller
 * 
 * @author terence
 * @date 2025-04-01
 */
@RestController
@RequestMapping("/hr/employee")
public class FmgreHrEmployeeController extends BaseController
{
    @Autowired
    private IFmgreHrEmployeeService fmgreHrEmployeeService;

    @Autowired
    private IFmgreHrEmployeeSalaryService fmgreHrEmployeeSalaryService;

    /**
     * 查询员工列表
     */
    @PreAuthorize("@ss.hasPermi('hr:employee:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d")
    public TableDataInfo list(FmgreHrEmployee fmgreHrEmployee)
    {
        startPage();
        List<FmgreHrEmployee> list = fmgreHrEmployeeService.selectFmgreHrEmployeeList(fmgreHrEmployee);
        if(SecurityUtils.hasPermi("hr:salary:query")) {
            List<FmgreHrEmployeeSalary> salaries = fmgreHrEmployeeSalaryService.selectFmgreHrEmployeeSalaryListByIds(list.stream().map(FmgreHrEmployee::getEmployeeId).collect(Collectors.toList()));
            list.stream().forEach(item -> {
                salaries.stream().filter(salary -> salary.getEmployeeId().equals(item.getEmployeeId())).findFirst().ifPresent(salary -> item.setSalary(salary));
            });
        }
        return getDataTable(list);
    }

    /**
     * 导出员工列表
     */
    @PreAuthorize("@ss.hasPermi('hr:employee:export')")
    @Log(title = "员工", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @DataScope(deptAlias = "d")
    public void export(HttpServletResponse response, FmgreHrEmployee fmgreHrEmployee)
    {
        List<FmgreHrEmployee> list = fmgreHrEmployeeService.selectFmgreHrEmployeeList(fmgreHrEmployee);
        ExcelUtil<FmgreHrEmployee> util = new ExcelUtil<FmgreHrEmployee>(FmgreHrEmployee.class);
        util.exportExcel(response, list, "员工数据");
    }

    /**
     * 获取员工详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:employee:query')")
    @GetMapping(value = "/{employeeId}")
    @DataScope(deptAlias = "d")
    public AjaxResult getInfo(@PathVariable("employeeId") Long employeeId)
    {
        FmgreHrEmployee employee = fmgreHrEmployeeService.selectFmgreHrEmployeeByEmployeeId(employeeId);
        if(SecurityUtils.hasPermi("hr:salary:query")) {
            FmgreHrEmployeeSalary salary = fmgreHrEmployeeSalaryService.selectFmgreHrEmployeeSalaryByEmployeeId(employeeId);
            employee.setSalary(salary);
        }
        return success(employee);
    }

    /**
     * 新增员工
     */
    @PreAuthorize("@ss.hasPermi('hr:employee:add')")
    @Log(title = "员工", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmgreHrEmployee fmgreHrEmployee)
    {
        int ret = fmgreHrEmployeeService.insertFmgreHrEmployee(fmgreHrEmployee);
        System.out.println("employee_id="+fmgreHrEmployee.getEmployeeId());
        fmgreHrEmployee.getSalary().setEmployeeId(fmgreHrEmployee.getEmployeeId());
        fmgreHrEmployeeSalaryService.insertFmgreHrEmployeeSalary(fmgreHrEmployee.getSalary());
        return toAjax(ret);
    }

    /**
     * 修改员工
     */
    @PreAuthorize("@ss.hasPermi('hr:employee:edit')")
    @Log(title = "员工", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmgreHrEmployee fmgreHrEmployee)
    {
        int ret = fmgreHrEmployeeService.updateFmgreHrEmployee(fmgreHrEmployee);
        if(SecurityUtils.hasPermi("hr:salary:edit") && fmgreHrEmployee.getSalary() != null) {
            fmgreHrEmployee.getSalary().setEmployeeId(fmgreHrEmployee.getEmployeeId());
            fmgreHrEmployeeSalaryService.updateFmgreHrEmployeeSalary(fmgreHrEmployee.getSalary());
        }
        return toAjax(ret);
    }

    /**
     * 删除员工
     */
    @PreAuthorize("@ss.hasPermi('hr:employee:remove')")
    @Log(title = "员工", businessType = BusinessType.DELETE)
	@DeleteMapping("/{employeeIds}")
    public AjaxResult remove(@PathVariable Long[] employeeIds)
    {
        return toAjax(fmgreHrEmployeeService.deleteFmgreHrEmployeeByEmployeeIds(employeeIds));
    }
}
