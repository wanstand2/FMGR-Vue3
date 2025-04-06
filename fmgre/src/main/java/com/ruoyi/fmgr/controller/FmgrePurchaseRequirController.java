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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.fmgr.domain.FmgreMaterial;
import com.ruoyi.fmgr.domain.FmgrePurchaseItem;
import com.ruoyi.fmgr.domain.FmgrePurchaseRequir;
import com.ruoyi.fmgr.domain.FmgrePurchaseRequirSummaryBo;
import com.ruoyi.fmgr.service.IFmgreMaterialService;
import com.ruoyi.fmgr.service.IFmgrePurchaseItemService;
import com.ruoyi.fmgr.service.IFmgrePurchaseRequirService;

/**
 * 采购需求Controller
 * 
 * @author terence
 * @date 2025-02-24
 */
@RestController
@RequestMapping("/purchase/requir")
public class FmgrePurchaseRequirController extends BaseController
{
    @Autowired
    private IFmgrePurchaseRequirService fmgrePurchaseRequirService;

    @Autowired
    private IFmgrePurchaseItemService fmgrePurchaseItemService;

    @Autowired
    private IFmgreMaterialService fmgreMaterialService;

    /**
     * 查询采购需求列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:requir:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmgrePurchaseRequir fmgrePurchaseRequir)
    {
        startPage();
        List<FmgrePurchaseRequir> list = fmgrePurchaseRequirService.selectFmgrePurchaseRequirList(fmgrePurchaseRequir);
        List<Long> requirIds = list.stream().map(FmgrePurchaseRequir::getRequirId).collect(Collectors.toList());
        if(requirIds.size() > 0) {
            List<FmgrePurchaseRequirSummaryBo> summaryList = fmgrePurchaseItemService.selectFmgrePurchaseRequirSummaryByRequirIds(requirIds);
            for(FmgrePurchaseRequir requir : list) {
                requir.setSummary(summaryList.stream().filter(s -> s.getRequirId().equals(requir.getRequirId())).findFirst().orElse(null));
            }
        }
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('purchase:requir:list')")
    @GetMapping("/listids")
    public TableDataInfo listByIds(Long[] requirIds)
    {
        startPage();
        List<FmgrePurchaseRequir> list = fmgrePurchaseRequirService.selectFmgrePurchaseRequirListByRequirIds(requirIds);
        return getDataTable(list);
    }

    /**
     * 导出采购需求列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:requir:export')")
    @Log(title = "采购需求", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FmgrePurchaseRequir fmgrePurchaseRequir)
    {
        List<FmgrePurchaseRequir> list = fmgrePurchaseRequirService.selectFmgrePurchaseRequirList(fmgrePurchaseRequir);
        ExcelUtil<FmgrePurchaseRequir> util = new ExcelUtil<FmgrePurchaseRequir>(FmgrePurchaseRequir.class);
        util.exportExcel(response, list, "采购需求数据");
    }

    /**
     * 获取采购需求详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:requir:query')")
    @GetMapping(value = "/{requirId}")
    public AjaxResult getInfo(@PathVariable("requirId") Long requirId)
    {
        FmgrePurchaseRequir fmgrePurchaseRequir = fmgrePurchaseRequirService.selectFmgrePurchaseRequirByRequirId(requirId);
        FmgrePurchaseItem query = new FmgrePurchaseItem();
        query.setRequirId(fmgrePurchaseRequir.getRequirId());
        fmgrePurchaseRequir.setRequirItems(fmgrePurchaseItemService.selectFmgrePurchaseItemList(query));
        List<Long> materialIds = fmgrePurchaseRequir.getRequirItems().stream().map(FmgrePurchaseItem::getMaterailId).collect(Collectors.toList());
        if(materialIds.size() > 0) {
            List<FmgreMaterial> materials = fmgreMaterialService.selectFmgreMaterialByMaterailIds(materialIds);
            for(FmgrePurchaseItem item : fmgrePurchaseRequir.getRequirItems()) {
                item.setMaterail(materials.stream().filter(m -> m.getMaterailId().equals(item.getMaterailId())).findFirst().orElse(null));
            }
        }
        return success(fmgrePurchaseRequir);
    }

    /**
     * 新增采购需求
     */
    @PreAuthorize("@ss.hasPermi('purchase:requir:add')")
    @Log(title = "采购需求", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmgrePurchaseRequir fmgrePurchaseRequir)
    {
        if(fmgrePurchaseRequir.getRequirItems() == null || fmgrePurchaseRequir.getRequirItems().size() == 0) {
            return error("不能增加空采购需求！");
        }
        int ret = fmgrePurchaseRequirService.insertFmgrePurchaseRequir(fmgrePurchaseRequir);
        for(FmgrePurchaseItem item : fmgrePurchaseRequir.getRequirItems()) {
            item.setRequirId(fmgrePurchaseRequir.getRequirId());
            fmgrePurchaseItemService.insertFmgrePurchaseItem(item);
        }
        return toAjax(ret);
    }

    /**
     * 修改采购需求
     */
    @PreAuthorize("@ss.hasPermi('purchase:requir:edit')")
    @Log(title = "采购需求", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmgrePurchaseRequir fmgrePurchaseRequir)
    {
        if(fmgrePurchaseRequir.getRequirItems() == null || fmgrePurchaseRequir.getRequirItems().size() == 0) {
            return error("不能修改为空采购需求！");
        }
        int ret = fmgrePurchaseRequirService.updateFmgrePurchaseRequir(fmgrePurchaseRequir);
        for(FmgrePurchaseItem item : fmgrePurchaseRequir.getRequirItems()) {
            if (item.getRequirId() == null) { 
                item.setRequirId(fmgrePurchaseRequir.getRequirId());
                fmgrePurchaseItemService.insertFmgrePurchaseItem(item);
            } else if(item.getOrderId() == null) {
                fmgrePurchaseItemService.updateFmgrePurchaseItem(item);
            }
        }
        FmgrePurchaseItem query = new FmgrePurchaseItem();
        query.setRequirId(fmgrePurchaseRequir.getRequirId());
        List<FmgrePurchaseItem> items = fmgrePurchaseItemService.selectFmgrePurchaseItemList(query);
        List<Long> itemIds = items.stream().filter(item -> item.getSupplierId() == 0).map(FmgrePurchaseItem::getItemId).collect(Collectors.toList());
        itemIds.removeAll(fmgrePurchaseRequir.getRequirItems().stream().map(FmgrePurchaseItem::getItemId).collect(Collectors.toList()));
        if(itemIds.size() > 0) {
            fmgrePurchaseItemService.deleteFmgrePurchaseItemByItemIds(itemIds.toArray(new Long[0]));
        }
        return toAjax(ret);
    }

    /**
     * 删除采购需求
     */
    @PreAuthorize("@ss.hasPermi('purchase:requir:remove')")
    @Log(title = "采购需求", businessType = BusinessType.DELETE)
	@DeleteMapping("/{requirIds}")
    public AjaxResult remove(@PathVariable Long[] requirIds)
    {
        return toAjax(fmgrePurchaseRequirService.deleteFmgrePurchaseRequirByRequirIds(requirIds));
    }
}
