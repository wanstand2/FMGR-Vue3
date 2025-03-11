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
import com.ruoyi.fmgr.domain.FmgreSupplier;
import com.ruoyi.fmgr.service.IFmgreSupplierService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 供应商Controller
 * 
 * @author terence
 * @date 2025-02-24
 */
@RestController
@RequestMapping("/purchase/supplier")
public class FmgreSupplierController extends BaseController
{
    @Autowired
    private IFmgreSupplierService fmgreSupplierService;

    /**
     * 查询供应商列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmgreSupplier fmgreSupplier)
    {
        startPage();
        List<FmgreSupplier> list = fmgreSupplierService.selectFmgreSupplierList(fmgreSupplier);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('purchase:supplier:list')")
    @GetMapping("/listids")
    public TableDataInfo listByIds(Long[] supplierIds)
    {
        startPage();
        List<FmgreSupplier> list = fmgreSupplierService.selectFmgreSupplierListBySupplierIds(supplierIds);
        return getDataTable(list);
    }

    /**
     * 导出供应商列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:export')")
    @Log(title = "供应商", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FmgreSupplier fmgreSupplier)
    {
        List<FmgreSupplier> list = fmgreSupplierService.selectFmgreSupplierList(fmgreSupplier);
        ExcelUtil<FmgreSupplier> util = new ExcelUtil<FmgreSupplier>(FmgreSupplier.class);
        util.exportExcel(response, list, "供应商数据");
    }

    /**
     * 获取供应商详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:query')")
    @GetMapping(value = "/{supplierId}")
    public AjaxResult getInfo(@PathVariable("supplierId") Long supplierId)
    {
        return success(fmgreSupplierService.selectFmgreSupplierBySupplierId(supplierId));
    }

    /**
     * 新增供应商
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:add')")
    @Log(title = "供应商", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmgreSupplier fmgreSupplier)
    {
        return toAjax(fmgreSupplierService.insertFmgreSupplier(fmgreSupplier));
    }

    /**
     * 修改供应商
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:edit')")
    @Log(title = "供应商", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmgreSupplier fmgreSupplier)
    {
        return toAjax(fmgreSupplierService.updateFmgreSupplier(fmgreSupplier));
    }

    /**
     * 删除供应商
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:remove')")
    @Log(title = "供应商", businessType = BusinessType.DELETE)
	@DeleteMapping("/{supplierIds}")
    public AjaxResult remove(@PathVariable Long[] supplierIds)
    {
        return toAjax(fmgreSupplierService.deleteFmgreSupplierBySupplierIds(supplierIds));
    }
}
