package com.ruoyi.fmgr.mapper;

import java.util.List;
import java.util.Collection;

import com.ruoyi.fmgr.domain.FmgrePurchaseItem;
import com.ruoyi.fmgr.domain.FmgrePurchaseRequirSummaryBo;

/**
 * 采购记录Mapper接口
 * 
 * @author terence
 * @date 2025-02-24
 */
public interface FmgrePurchaseItemMapper 
{
    /**
     * 查询采购记录
     * 
     * @param itemId 采购记录主键
     * @return 采购记录
     */
    public FmgrePurchaseItem selectFmgrePurchaseItemByItemId(Long itemId);

    public List<FmgrePurchaseItem> selectFmgrePurchaseItemByItemIds(Collection<Long> itemIds);

    public List<FmgrePurchaseRequirSummaryBo> selectFmgrePurchaseRequirSummaryByRequirIds(Collection<Long> requirIds);

    /**
     * 查询采购记录列表
     * 
     * @param fmgrePurchaseItem 采购记录
     * @return 采购记录集合
     */
    public List<FmgrePurchaseItem> selectFmgrePurchaseItemList(FmgrePurchaseItem fmgrePurchaseItem);

    /**
     * 新增采购记录
     * 
     * @param fmgrePurchaseItem 采购记录
     * @return 结果
     */
    public int insertFmgrePurchaseItem(FmgrePurchaseItem fmgrePurchaseItem);

    /**
     * 修改采购记录
     * 
     * @param fmgrePurchaseItem 采购记录
     * @return 结果
     */
    public int updateFmgrePurchaseItem(FmgrePurchaseItem fmgrePurchaseItem);

    /**
     * 删除采购记录
     * 
     * @param itemId 采购记录主键
     * @return 结果
     */
    public int deleteFmgrePurchaseItemByItemId(Long itemId);

    /**
     * 批量删除采购记录
     * 
     * @param itemIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFmgrePurchaseItemByItemIds(Long[] itemIds);
}
