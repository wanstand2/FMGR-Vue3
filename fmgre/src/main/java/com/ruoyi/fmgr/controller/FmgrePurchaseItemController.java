package com.ruoyi.fmgr.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Collection;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.fmgr.domain.FmgrePurchaseItem;
import com.ruoyi.fmgr.service.IFmgrePurchaseItemService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 采购记录Controller
 * 
 * @author terence
 * @date 2025-02-24
 */
@RestController
@RequestMapping("/purchase/item")
public class FmgrePurchaseItemController extends BaseController
{
    @Autowired
    private IFmgrePurchaseItemService fmgrePurchaseItemService;

    /**
     * 查询采购记录列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:item:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmgrePurchaseItem fmgrePurchaseItem)
    {
        startPage();
        List<FmgrePurchaseItem> list = fmgrePurchaseItemService.selectFmgrePurchaseItemList(fmgrePurchaseItem);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('purchase:item:list')")
    @GetMapping("/mls")
    public TableDataInfo materialLastSupplier(@RequestParam("materialId") Long[] materialId, @RequestParam("deptId") Long deptId, @RequestParam("orderTime") Date orderTime)
    {
        List<FmgrePurchaseItem> list = fmgrePurchaseItemService.selectFmgrePurchaseMaterialLastSupplier(Arrays.asList(materialId), deptId, orderTime);
        return getDataTable(list);
    }
    /**
     * 导出采购记录列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:item:export')")
    @Log(title = "采购记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FmgrePurchaseItem fmgrePurchaseItem)
    {
        List<FmgrePurchaseItem> list = fmgrePurchaseItemService.selectFmgrePurchaseItemList(fmgrePurchaseItem);
        ExcelUtil<FmgrePurchaseItem> util = new ExcelUtil<FmgrePurchaseItem>(FmgrePurchaseItem.class);
        util.exportExcel(response, list, "采购记录数据");
    }

    /**
     * 获取采购记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:item:query')")
    @GetMapping(value = "/{itemId}")
    public AjaxResult getInfo(@PathVariable("itemId") Long itemId)
    {
        return success(fmgrePurchaseItemService.selectFmgrePurchaseItemByItemId(itemId));
    }

    /**
     * 新增采购记录
     */
    @PreAuthorize("@ss.hasPermi('purchase:item:add')")
    @Log(title = "采购记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmgrePurchaseItem fmgrePurchaseItem)
    {
        return toAjax(fmgrePurchaseItemService.insertFmgrePurchaseItem(fmgrePurchaseItem));
    }

    /**
     * 修改采购记录
     */
    @PreAuthorize("@ss.hasPermi('purchase:item:edit')")
    @Log(title = "采购记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmgrePurchaseItem fmgrePurchaseItem)
    {
        return toAjax(fmgrePurchaseItemService.updateFmgrePurchaseItem(fmgrePurchaseItem));
    }

    /**
     * 批量修改采购记录
     */
    @PreAuthorize("@ss.hasPermi('purchase:item:edit')")
    @Log(title = "采购记录", businessType = BusinessType.UPDATE)
    @PutMapping("/edits")
    public AjaxResult edits(@RequestBody List<FmgrePurchaseItem> fmgrePurchaseItems)
    {
        return toAjax(fmgrePurchaseItemService.updateFmgrePurchaseItems(fmgrePurchaseItems));
    }

    /**
     * 删除采购记录
     */
    @PreAuthorize("@ss.hasPermi('purchase:item:remove')")
    @Log(title = "采购记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{itemIds}")
    public AjaxResult remove(@PathVariable Long[] itemIds)
    {
        return toAjax(fmgrePurchaseItemService.deleteFmgrePurchaseItemByItemIds(itemIds));
    }
}
