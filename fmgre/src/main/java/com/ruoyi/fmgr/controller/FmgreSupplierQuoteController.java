package com.ruoyi.fmgr.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.fmgr.domain.FmgreSupplierQuote;
import com.ruoyi.fmgr.service.IFmgreSupplierQuoteService;

/**
 * 供应商报价Controller
 * 
 * @author terence
 * @date 2025-02-24
 */
@RestController
@RequestMapping("/purchase/quote")
public class FmgreSupplierQuoteController extends BaseController
{
    @Autowired
    private IFmgreSupplierQuoteService fmgreSupplierQuoteService;

    /**
     * 查询供应商报价列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:quote:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmgreSupplierQuote fmgreSupplierQuote)
    {
        startPage();
        List<FmgreSupplierQuote> list = fmgreSupplierQuoteService.selectFmgreSupplierQuoteList(fmgreSupplierQuote);
        return getDataTable(list);
    }

    /**
     * 查询供应商报价列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:quote:list')")
    @PostMapping("/listByMSB")
    public AjaxResult listByMSB(@RequestBody List<FmgreSupplierQuote> quotes)
    {
        List<FmgreSupplierQuote> list = new ArrayList<>();
        if(quotes == null || quotes.size() == 0) {
            return success(list);
        }
        Map<Long, List<FmgreSupplierQuote>> map = new HashMap<>();
        for(FmgreSupplierQuote quote : quotes) {
            if(map.containsKey(quote.getMaterailId())) {
                map.get(quote.getMaterailId()).add(quote);
            } else {
                map.put(quote.getMaterailId(), new ArrayList<>(Arrays.asList(quote)));
            }
        }
        for(Map.Entry<Long, List<FmgreSupplierQuote>> entry : map.entrySet()) {
            Long supplierId = entry.getValue().get(0).getSupplierId();
            Date quoteTime = entry.getValue().get(0).getQuotaTime();
            List<Long> materialIds = entry.getValue().stream().map(FmgreSupplierQuote::getMaterailId).collect(Collectors.toList());
            list.addAll(fmgreSupplierQuoteService.selectFmgreSupplierQuoteListByMaterailIdPackUnitDictidLatest(
                supplierId,
                quoteTime,
                materialIds.toArray(new Long[materialIds.size()]))
            );
        }
        return success(list);
    }
    /**
     * 导出供应商报价列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:quote:export')")
    @Log(title = "供应商报价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FmgreSupplierQuote fmgreSupplierQuote)
    {
        List<FmgreSupplierQuote> list = fmgreSupplierQuoteService.selectFmgreSupplierQuoteList(fmgreSupplierQuote);
        ExcelUtil<FmgreSupplierQuote> util = new ExcelUtil<FmgreSupplierQuote>(FmgreSupplierQuote.class);
        util.exportExcel(response, list, "供应商报价数据");
    }

    /**
     * 获取供应商报价详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:quote:query')")
    @GetMapping(value = "/{quoteId}")
    public AjaxResult getInfo(@PathVariable("quoteId") Long quoteId)
    {
        return success(fmgreSupplierQuoteService.selectFmgreSupplierQuoteByQuoteId(quoteId));
    }

    /**
     * 新增供应商报价
     */
    @PreAuthorize("@ss.hasPermi('purchase:quote:add')")
    @Log(title = "供应商报价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmgreSupplierQuote fmgreSupplierQuote)
    {
        return toAjax(fmgreSupplierQuoteService.insertFmgreSupplierQuote(fmgreSupplierQuote));
    }

    /**
     * 修改供应商报价
     */
    @PreAuthorize("@ss.hasPermi('purchase:quote:edit')")
    @Log(title = "供应商报价", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmgreSupplierQuote fmgreSupplierQuote)
    {
        return toAjax(fmgreSupplierQuoteService.updateFmgreSupplierQuote(fmgreSupplierQuote));
    }

    /**
     * 删除供应商报价
     */
    @PreAuthorize("@ss.hasPermi('purchase:quote:remove')")
    @Log(title = "供应商报价", businessType = BusinessType.DELETE)
	@DeleteMapping("/{quoteIds}")
    public AjaxResult remove(@PathVariable Long[] quoteIds)
    {
        return toAjax(fmgreSupplierQuoteService.deleteFmgreSupplierQuoteByQuoteIds(quoteIds));
    }
}
