package com.ruoyi.fmgr.controller;

import java.util.List;

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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.fmgr.domain.FmgreFranchiseStore;
import com.ruoyi.fmgr.service.IFmgreFranchiseStoreService;

/**
 * 门店Controller
 * 
 * @author terence
 * @date 2025-02-24
 */
@RestController
@RequestMapping("/branch/store")
public class FmgreFranchiseStoreController extends BaseController
{
    @Autowired
    private IFmgreFranchiseStoreService fmgreFranchiseStoreService;

    /**
     * 查询门店列表
     */
    @PreAuthorize("@ss.hasPermi('branch:store:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmgreFranchiseStore fmgreFranchiseStore)
    {
        startPage();
        List<FmgreFranchiseStore> list = fmgreFranchiseStoreService.selectFmgreFranchiseStoreList(fmgreFranchiseStore);
        return getDataTable(list);
    }

    /**
     * 导出门店列表
     */
    @PreAuthorize("@ss.hasPermi('branch:store:export')")
    @Log(title = "门店", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FmgreFranchiseStore fmgreFranchiseStore)
    {
        List<FmgreFranchiseStore> list = fmgreFranchiseStoreService.selectFmgreFranchiseStoreList(fmgreFranchiseStore);
        ExcelUtil<FmgreFranchiseStore> util = new ExcelUtil<FmgreFranchiseStore>(FmgreFranchiseStore.class);
        util.exportExcel(response, list, "门店数据");
    }

    /**
     * 获取门店详细信息
     */
    @PreAuthorize("@ss.hasPermi('branch:store:query')")
    @GetMapping(value = "/{deptId}")
    @DataScope(deptAlias = "d")
    public AjaxResult getInfo(@PathVariable("deptId") Long deptId)
    {
        return success(fmgreFranchiseStoreService.selectFmgreFranchiseStoreByDeptId(deptId));
    }

    /**
     * 新增门店
     */
    @PreAuthorize("@ss.hasPermi('branch:store:add')")
    @Log(title = "门店", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmgreFranchiseStore fmgreFranchiseStore)
    {
        return toAjax(fmgreFranchiseStoreService.insertFmgreFranchiseStore(fmgreFranchiseStore));
    }

    /**
     * 修改门店
     */
    @PreAuthorize("@ss.hasPermi('branch:store:edit')")
    @Log(title = "门店", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmgreFranchiseStore fmgreFranchiseStore)
    {
        return toAjax(fmgreFranchiseStoreService.updateFmgreFranchiseStore(fmgreFranchiseStore));
    }

    /**
     * 删除门店
     */
    @PreAuthorize("@ss.hasPermi('branch:store:remove')")
    @Log(title = "门店", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deptIds}")
    public AjaxResult remove(@PathVariable Long[] deptIds)
    {
        return toAjax(fmgreFranchiseStoreService.deleteFmgreFranchiseStoreByDeptIds(deptIds));
    }
}
