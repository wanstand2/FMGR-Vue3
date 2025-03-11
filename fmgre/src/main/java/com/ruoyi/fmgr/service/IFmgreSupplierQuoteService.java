package com.ruoyi.fmgr.service;

import java.util.List;
import com.ruoyi.fmgr.domain.FmgreSupplierQuote;

/**
 * 供应商报价Service接口
 * 
 * @author terence
 * @date 2025-02-24
 */
public interface IFmgreSupplierQuoteService 
{
    /**
     * 查询供应商报价
     * 
     * @param quoteId 供应商报价主键
     * @return 供应商报价
     */
    public FmgreSupplierQuote selectFmgreSupplierQuoteByQuoteId(Long quoteId);

    /**
     * 查询供应商报价列表
     * 
     * @param fmgreSupplierQuote 供应商报价
     * @return 供应商报价集合
     */
    public List<FmgreSupplierQuote> selectFmgreSupplierQuoteList(FmgreSupplierQuote fmgreSupplierQuote);

    /**
     * 查询供应商报价列表
     * 
     * @param supplierId 供应商ID
     * @return 供应商报价集合
     */
    public List<FmgreSupplierQuote> selectFmgreSupplierQuoteListByMaterailIdPackUnitDictidLatest(Long supplierId);

    /**
     * 新增供应商报价
     * 
     * @param fmgreSupplierQuote 供应商报价
     * @return 结果
     */
    public int insertFmgreSupplierQuote(FmgreSupplierQuote fmgreSupplierQuote);

    /**
     * 修改供应商报价
     * 
     * @param fmgreSupplierQuote 供应商报价
     * @return 结果
     */
    public int updateFmgreSupplierQuote(FmgreSupplierQuote fmgreSupplierQuote);

    /**
     * 批量删除供应商报价
     * 
     * @param quoteIds 需要删除的供应商报价主键集合
     * @return 结果
     */
    public int deleteFmgreSupplierQuoteByQuoteIds(Long[] quoteIds);

    /**
     * 删除供应商报价信息
     * 
     * @param quoteId 供应商报价主键
     * @return 结果
     */
    public int deleteFmgreSupplierQuoteByQuoteId(Long quoteId);
}
