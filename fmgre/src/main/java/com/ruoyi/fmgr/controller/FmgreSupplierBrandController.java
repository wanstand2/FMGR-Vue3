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
import com.ruoyi.fmgr.domain.FmgreSupplierBrand;
import com.ruoyi.fmgr.service.IFmgreSupplierBrandService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 供应商品牌Controller
 * 
 * @author terence
 * @date 2025-02-24
 */
@RestController
@RequestMapping("/purchase/brand")
public class FmgreSupplierBrandController extends BaseController
{
    @Autowired
    private IFmgreSupplierBrandService fmgreSupplierBrandService;

    /**
     * 查询供应商品牌列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:brand:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmgreSupplierBrand fmgreSupplierBrand)
    {
        startPage();
        List<FmgreSupplierBrand> list = fmgreSupplierBrandService.selectFmgreSupplierBrandList(fmgreSupplierBrand);
        return getDataTable(list);
    }

    /**
     * 导出供应商品牌列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:brand:export')")
    @Log(title = "供应商品牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FmgreSupplierBrand fmgreSupplierBrand)
    {
        List<FmgreSupplierBrand> list = fmgreSupplierBrandService.selectFmgreSupplierBrandList(fmgreSupplierBrand);
        ExcelUtil<FmgreSupplierBrand> util = new ExcelUtil<FmgreSupplierBrand>(FmgreSupplierBrand.class);
        util.exportExcel(response, list, "供应商品牌数据");
    }

    /**
     * 获取供应商品牌详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:brand:query')")
    @GetMapping(value = "/{brandId}")
    public AjaxResult getInfo(@PathVariable("brandId") Long brandId)
    {
        return success(fmgreSupplierBrandService.selectFmgreSupplierBrandByBrandId(brandId));
    }

    /**
     * 新增供应商品牌
     */
    @PreAuthorize("@ss.hasPermi('purchase:brand:add')")
    @Log(title = "供应商品牌", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmgreSupplierBrand fmgreSupplierBrand)
    {
        return toAjax(fmgreSupplierBrandService.insertFmgreSupplierBrand(fmgreSupplierBrand));
    }

    /**
     * 修改供应商品牌
     */
    @PreAuthorize("@ss.hasPermi('purchase:brand:edit')")
    @Log(title = "供应商品牌", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmgreSupplierBrand fmgreSupplierBrand)
    {
        return toAjax(fmgreSupplierBrandService.updateFmgreSupplierBrand(fmgreSupplierBrand));
    }

    /**
     * 删除供应商品牌
     */
    @PreAuthorize("@ss.hasPermi('purchase:brand:remove')")
    @Log(title = "供应商品牌", businessType = BusinessType.DELETE)
	@DeleteMapping("/{brandIds}")
    public AjaxResult remove(@PathVariable Long[] brandIds)
    {
        return toAjax(fmgreSupplierBrandService.deleteFmgreSupplierBrandByBrandIds(brandIds));
    }
}
