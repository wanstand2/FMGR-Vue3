package com.ruoyi.fmgr.service.impl;

import java.util.List;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fmgr.mapper.FmgrePurchaseItemMapper;
import com.ruoyi.fmgr.domain.FmgrePurchaseItem;
import com.ruoyi.fmgr.domain.FmgrePurchaseRequirSummaryBo;
import com.ruoyi.fmgr.service.IFmgrePurchaseItemService;

/**
 * 采购记录Service业务层处理
 * 
 * @author terence
 * @date 2025-02-24
 */
@Service
public class FmgrePurchaseItemServiceImpl implements IFmgrePurchaseItemService 
{
    @Autowired
    private FmgrePurchaseItemMapper fmgrePurchaseItemMapper;

    /**
     * 查询采购记录
     * 
     * @param itemId 采购记录主键
     * @return 采购记录
     */
    @Override
    public FmgrePurchaseItem selectFmgrePurchaseItemByItemId(Long itemId)
    {
        return fmgrePurchaseItemMapper.selectFmgrePurchaseItemByItemId(itemId);
    }

    @Override
    public List<FmgrePurchaseItem> selectFmgrePurchaseItemByItemIds(Collection<Long> itemIds)
    {
        return fmgrePurchaseItemMapper.selectFmgrePurchaseItemByItemIds(itemIds);
    }

    /**
     * 查询采购记录列表
     * 
     * @param fmgrePurchaseItem 采购记录
     * @return 采购记录
     */
    @Override
    public List<FmgrePurchaseItem> selectFmgrePurchaseItemList(FmgrePurchaseItem fmgrePurchaseItem)
    {
        return fmgrePurchaseItemMapper.selectFmgrePurchaseItemList(fmgrePurchaseItem);
    }

    /**
     * 新增采购记录
     * 
     * @param fmgrePurchaseItem 采购记录
     * @return 结果
     */
    @Override
    public int insertFmgrePurchaseItem(FmgrePurchaseItem fmgrePurchaseItem)
    {
        return fmgrePurchaseItemMapper.insertFmgrePurchaseItem(fmgrePurchaseItem);
    }

    /**
     * 修改采购记录
     * 
     * @param fmgrePurchaseItem 采购记录
     * @return 结果
     */
    @Override
    public int updateFmgrePurchaseItem(FmgrePurchaseItem fmgrePurchaseItem)
    {
        return fmgrePurchaseItemMapper.updateFmgrePurchaseItem(fmgrePurchaseItem);
    }

    /**
     * 批量删除采购记录
     * 
     * @param itemIds 需要删除的采购记录主键
     * @return 结果
     */
    @Override
    public int deleteFmgrePurchaseItemByItemIds(Long[] itemIds)
    {
        return fmgrePurchaseItemMapper.deleteFmgrePurchaseItemByItemIds(itemIds);
    }

    /**
     * 删除采购记录信息
     * 
     * @param itemId 采购记录主键
     * @return 结果
     */
    @Override
    public int deleteFmgrePurchaseItemByItemId(Long itemId)
    {
        return fmgrePurchaseItemMapper.deleteFmgrePurchaseItemByItemId(itemId);
    }

    @Override
    public List<FmgrePurchaseRequirSummaryBo> selectFmgrePurchaseRequirSummaryByRequirIds(Collection<Long> requirIds)
    {
        return fmgrePurchaseItemMapper.selectFmgrePurchaseRequirSummaryByRequirIds(requirIds);
    }

    @Override
    public int updateFmgrePurchaseItems(List<FmgrePurchaseItem> fmgrePurchaseItems) {
        int rows = 0;
        for(FmgrePurchaseItem item : fmgrePurchaseItems) {
            rows += updateFmgrePurchaseItem(item);
        }
        return rows;
    }
}
