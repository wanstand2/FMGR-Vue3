package com.ruoyi.fmgr.controller;

import java.util.Collections;
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
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.fmgr.domain.FmgrePurchaseOrder;
import com.ruoyi.fmgr.domain.FmgreSupplier;
import com.ruoyi.fmgr.domain.FmgreSupplierFinanceBo;
import com.ruoyi.fmgr.service.IFmgrePurchaseOrderService;
import com.ruoyi.fmgr.service.IFmgreSupplierService;

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

    @Autowired
    private IFmgrePurchaseOrderService fmgrePurchaseOrderService;

    /**
     * 查询供应商列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmgreSupplier fmgreSupplier)
    {
        List<FmgreSupplier> list;
        Boolean f = Convert.toBool(fmgreSupplier.getParams().get("finance"));
        if(f!= null && f) {
            FmgrePurchaseOrder order = new FmgrePurchaseOrder();
            order.setParams(fmgreSupplier.getParams());
            Long payment = Convert.toLong(fmgreSupplier.getParams().get("payment"));
            if(payment != null && payment > 0) {
                order.setPaymentId(1L);
            } else {
                order.setPaymentId(0L);
            }
            Boolean nonull = Convert.toBool(fmgreSupplier.getParams().get("nonull"));
            List<FmgreSupplierFinanceBo> bos;
            if(!nonull) {
                startPage();
                list = fmgreSupplierService.selectFmgreSupplierList(fmgreSupplier);
                List<Long> supplierIds = list.stream().map(item -> item.getSupplierId()).collect(Collectors.toList());
                bos = fmgrePurchaseOrderService.selectFmgrePurchaseOrderSupplierFinanceList(supplierIds, order);
            } else {
                bos = fmgrePurchaseOrderService.selectFmgrePurchaseOrderSupplierFinanceList(null, order);
                List<Long> supplierIds = bos.stream().map(item -> item.getSupplierId()).collect(Collectors.toList());
                startPage();
                list = fmgreSupplierService.selectFmgreSupplierList(supplierIds, fmgreSupplier);
            }
            list.stream().forEach(
                item -> {
                    item.setFinanceBos(bos.stream().filter(bo -> bo.getSupplierId().equals(item.getSupplierId())).collect(Collectors.toList()));
                }
            );
        } else {
            startPage();
            list = fmgreSupplierService.selectFmgreSupplierList(fmgreSupplier);
        }
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
