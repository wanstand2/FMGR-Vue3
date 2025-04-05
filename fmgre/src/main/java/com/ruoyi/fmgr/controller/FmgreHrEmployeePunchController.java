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
import com.ruoyi.fmgr.domain.FmgreHrEmployeePunch;
import com.ruoyi.fmgr.service.IFmgreHrEmployeePunchService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 员工打卡Controller
 * 
 * @author terence
 * @date 2025-04-01
 */
@RestController
@RequestMapping("/hr/punch")
public class FmgreHrEmployeePunchController extends BaseController
{
    @Autowired
    private IFmgreHrEmployeePunchService fmgreHrEmployeePunchService;

    /**
     * 查询员工打卡列表
     */
    @PreAuthorize("@ss.hasPermi('hr:punch:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmgreHrEmployeePunch fmgreHrEmployeePunch)
    {
        startPage();
        List<FmgreHrEmployeePunch> list = fmgreHrEmployeePunchService.selectFmgreHrEmployeePunchList(fmgreHrEmployeePunch);
        return getDataTable(list);
    }

    /**
     * 导出员工打卡列表
     */
    @PreAuthorize("@ss.hasPermi('hr:punch:export')")
    @Log(title = "员工打卡", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FmgreHrEmployeePunch fmgreHrEmployeePunch)
    {
        List<FmgreHrEmployeePunch> list = fmgreHrEmployeePunchService.selectFmgreHrEmployeePunchList(fmgreHrEmployeePunch);
        ExcelUtil<FmgreHrEmployeePunch> util = new ExcelUtil<FmgreHrEmployeePunch>(FmgreHrEmployeePunch.class);
        util.exportExcel(response, list, "员工打卡数据");
    }

    /**
     * 获取员工打卡详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:punch:query')")
    @GetMapping(value = "/{punchId}")
    public AjaxResult getInfo(@PathVariable("punchId") Long punchId)
    {
        return success(fmgreHrEmployeePunchService.selectFmgreHrEmployeePunchByPunchId(punchId));
    }

    /**
     * 新增员工打卡
     */
    @PreAuthorize("@ss.hasPermi('hr:punch:add')")
    @Log(title = "员工打卡", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmgreHrEmployeePunch fmgreHrEmployeePunch)
    {
        return toAjax(fmgreHrEmployeePunchService.insertFmgreHrEmployeePunch(fmgreHrEmployeePunch));
    }

    /**
     * 修改员工打卡
     */
    @PreAuthorize("@ss.hasPermi('hr:punch:edit')")
    @Log(title = "员工打卡", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmgreHrEmployeePunch fmgreHrEmployeePunch)
    {
        return toAjax(fmgreHrEmployeePunchService.updateFmgreHrEmployeePunch(fmgreHrEmployeePunch));
    }

    /**
     * 删除员工打卡
     */
    @PreAuthorize("@ss.hasPermi('hr:punch:remove')")
    @Log(title = "员工打卡", businessType = BusinessType.DELETE)
	@DeleteMapping("/{punchIds}")
    public AjaxResult remove(@PathVariable Long[] punchIds)
    {
        return toAjax(fmgreHrEmployeePunchService.deleteFmgreHrEmployeePunchByPunchIds(punchIds));
    }
}
