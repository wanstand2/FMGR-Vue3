package com.ruoyi.fmgr.service.impl;

import java.util.List;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fmgr.mapper.FmgreSupplierQuoteMapper;
import com.ruoyi.fmgr.domain.FmgreSupplierQuote;
import com.ruoyi.fmgr.service.IFmgreSupplierQuoteService;
import java.util.Date;

/**
 * 供应商报价Service业务层处理
 * 
 * @author terence
 * @date 2025-02-24
 */
@Service
public class FmgreSupplierQuoteServiceImpl implements IFmgreSupplierQuoteService 
{
    @Autowired
    private FmgreSupplierQuoteMapper fmgreSupplierQuoteMapper;

    /**
     * 查询供应商报价
     * 
     * @param quoteId 供应商报价主键
     * @return 供应商报价
     */
    @Override
    public FmgreSupplierQuote selectFmgreSupplierQuoteByQuoteId(Long quoteId)
    {
        return fmgreSupplierQuoteMapper.selectFmgreSupplierQuoteByQuoteId(quoteId);
    }

    /**
     * 查询供应商报价列表
     * 
     * @param fmgreSupplierQuote 供应商报价
     * @return 供应商报价
     */
    @Override
    public List<FmgreSupplierQuote> selectFmgreSupplierQuoteList(FmgreSupplierQuote fmgreSupplierQuote)
    {
        return fmgreSupplierQuoteMapper.selectFmgreSupplierQuoteList(fmgreSupplierQuote);
    }

    @Override
    public List<FmgreSupplierQuote> selectFmgreSupplierQuoteListByQuoteIds(Collection<Long> quoteIds)
    {
        return fmgreSupplierQuoteMapper.selectFmgreSupplierQuoteListByQuoteIds(quoteIds);
    }

    /**
     * 查询供应商报价列表
     * 
     * @param supplierId 供应商ID
     * @return 供应商报价集合
     */
    @Override
    public List<FmgreSupplierQuote> selectFmgreSupplierQuoteListByMaterailIdPackUnitDictidLatest(Long supplierId, Date quotaTime, Long[] materailIds)
    {
        return fmgreSupplierQuoteMapper.selectFmgreSupplierQuoteListByMaterailIdPackUnitDictidLatest(supplierId, quotaTime, materailIds);
    }

    /**
     * 新增供应商报价
     * 
     * @param fmgreSupplierQuote 供应商报价
     * @return 结果
     */
    @Override
    public int insertFmgreSupplierQuote(FmgreSupplierQuote fmgreSupplierQuote)
    {
        return fmgreSupplierQuoteMapper.insertFmgreSupplierQuote(fmgreSupplierQuote);
    }

    /**
     * 修改供应商报价
     * 
     * @param fmgreSupplierQuote 供应商报价
     * @return 结果
     */
    @Override
    public int updateFmgreSupplierQuote(FmgreSupplierQuote fmgreSupplierQuote)
    {
        return fmgreSupplierQuoteMapper.updateFmgreSupplierQuote(fmgreSupplierQuote);
    }

    /**
     * 批量删除供应商报价
     * 
     * @param quoteIds 需要删除的供应商报价主键
     * @return 结果
     */
    @Override
    public int deleteFmgreSupplierQuoteByQuoteIds(Long[] quoteIds)
    {
        return fmgreSupplierQuoteMapper.deleteFmgreSupplierQuoteByQuoteIds(quoteIds);
    }

    /**
     * 删除供应商报价信息
     * 
     * @param quoteId 供应商报价主键
     * @return 结果
     */
    @Override
    public int deleteFmgreSupplierQuoteByQuoteId(Long quoteId)
    {
        return fmgreSupplierQuoteMapper.deleteFmgreSupplierQuoteByQuoteId(quoteId);
    }
}
