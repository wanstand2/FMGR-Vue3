package com.ruoyi.fmgr.controller;

import java.util.Collection;
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
import com.ruoyi.fmgr.domain.FmgreMaterial;
import com.ruoyi.fmgr.service.IFmgreMaterialService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 原材料Controller
 * 
 * @author terence
 * @date 2025-02-24
 */
@RestController
@RequestMapping("/material/material")
public class FmgreMaterialController extends BaseController
{
    @Autowired
    private IFmgreMaterialService fmgreMaterialService;

    /**
     * 查询原材料列表
     */
    @PreAuthorize("@ss.hasPermi('material:material:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmgreMaterial fmgreMaterial)
    {
        startPage();
        List<FmgreMaterial> list = fmgreMaterialService.selectFmgreMaterialList(fmgreMaterial);
        return getDataTable(list);
    }

    /**
     * 导出原材料列表
     */
    @PreAuthorize("@ss.hasPermi('material:material:export')")
    @Log(title = "原材料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FmgreMaterial fmgreMaterial)
    {
        List<FmgreMaterial> list = fmgreMaterialService.selectFmgreMaterialList(fmgreMaterial);
        ExcelUtil<FmgreMaterial> util = new ExcelUtil<FmgreMaterial>(FmgreMaterial.class);
        util.exportExcel(response, list, "原材料数据");
    }

    /**
     * 获取原材料详细信息
     */
    @PreAuthorize("@ss.hasPermi('material:material:query')")
    @GetMapping(value = "/{materailId}")
    public AjaxResult getInfo(@PathVariable("materailId") Long materailId)
    {
        return success(fmgreMaterialService.selectFmgreMaterialByMaterailId(materailId));
    }

    /**
     * 新增原材料
     */
    @PreAuthorize("@ss.hasPermi('material:material:add')")
    @Log(title = "原材料", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmgreMaterial fmgreMaterial)
    {
        return toAjax(fmgreMaterialService.insertFmgreMaterial(fmgreMaterial));
    }

    /**
     * 修改原材料
     */
    @PreAuthorize("@ss.hasPermi('material:material:edit')")
    @Log(title = "原材料", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmgreMaterial fmgreMaterial)
    {
        return toAjax(fmgreMaterialService.updateFmgreMaterial(fmgreMaterial));
    }

    /**
     * 删除原材料
     */
    @PreAuthorize("@ss.hasPermi('material:material:remove')")
    @Log(title = "原材料", businessType = BusinessType.DELETE)
	@DeleteMapping("/{materailIds}")
    public AjaxResult remove(@PathVariable Long[] materailIds)
    {
        return toAjax(fmgreMaterialService.deleteFmgreMaterialByMaterailIds(materailIds));
    }
}
