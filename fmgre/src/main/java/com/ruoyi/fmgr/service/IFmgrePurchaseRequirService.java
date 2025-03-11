package com.ruoyi.fmgr.service;

import java.util.List;
import com.ruoyi.fmgr.domain.FmgrePurchaseRequir;

/**
 * 采购需求Service接口
 * 
 * @author terence
 * @date 2025-02-24
 */
public interface IFmgrePurchaseRequirService 
{
    /**
     * 查询采购需求
     * 
     * @param requirId 采购需求主键
     * @return 采购需求
     */
    public FmgrePurchaseRequir selectFmgrePurchaseRequirByRequirId(Long requirId);

    /**
     * 查询采购需求列表
     * 
     * @param fmgrePurchaseRequir 采购需求
     * @return 采购需求集合
     */
    public List<FmgrePurchaseRequir> selectFmgrePurchaseRequirList(FmgrePurchaseRequir fmgrePurchaseRequir);

    /**
     * 新增采购需求
     * 
     * @param fmgrePurchaseRequir 采购需求
     * @return 结果
     */
    public int insertFmgrePurchaseRequir(FmgrePurchaseRequir fmgrePurchaseRequir);

    /**
     * 修改采购需求
     * 
     * @param fmgrePurchaseRequir 采购需求
     * @return 结果
     */
    public int updateFmgrePurchaseRequir(FmgrePurchaseRequir fmgrePurchaseRequir);

    /**
     * 批量删除采购需求
     * 
     * @param requirIds 需要删除的采购需求主键集合
     * @return 结果
     */
    public int deleteFmgrePurchaseRequirByRequirIds(Long[] requirIds);

    /**
     * 删除采购需求信息
     * 
     * @param requirId 采购需求主键
     * @return 结果
     */
    public int deleteFmgrePurchaseRequirByRequirId(Long requirId);

    /**
     * 根据ID列表查询采购需求列表
     * 
     * @param requirIds 采购需求主键集合
     * @return 采购需求集合
     */
    public List<FmgrePurchaseRequir> selectFmgrePurchaseRequirListByRequirIds(Long[] requirIds);
}
