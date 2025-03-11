package com.ruoyi.fmgr.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fmgr.mapper.FmgrePurchaseRequirMapper;
import com.ruoyi.fmgr.domain.FmgrePurchaseRequir;
import com.ruoyi.fmgr.service.IFmgrePurchaseRequirService;

/**
 * 采购需求Service业务层处理
 * 
 * @author terence
 * @date 2025-02-24
 */
@Service
public class FmgrePurchaseRequirServiceImpl implements IFmgrePurchaseRequirService 
{
    @Autowired
    private FmgrePurchaseRequirMapper fmgrePurchaseRequirMapper;

    /**
     * 查询采购需求
     * 
     * @param requirId 采购需求主键
     * @return 采购需求
     */
    @Override
    public FmgrePurchaseRequir selectFmgrePurchaseRequirByRequirId(Long requirId)
    {
        return fmgrePurchaseRequirMapper.selectFmgrePurchaseRequirByRequirId(requirId);
    }

    /**
     * 查询采购需求列表
     * 
     * @param fmgrePurchaseRequir 采购需求
     * @return 采购需求
     */
    @Override
    public List<FmgrePurchaseRequir> selectFmgrePurchaseRequirList(FmgrePurchaseRequir fmgrePurchaseRequir)
    {
        return fmgrePurchaseRequirMapper.selectFmgrePurchaseRequirList(fmgrePurchaseRequir);
    }

    /**
     * 新增采购需求
     * 
     * @param fmgrePurchaseRequir 采购需求
     * @return 结果
     */
    @Override
    public int insertFmgrePurchaseRequir(FmgrePurchaseRequir fmgrePurchaseRequir)
    {
        return fmgrePurchaseRequirMapper.insertFmgrePurchaseRequir(fmgrePurchaseRequir);
    }

    /**
     * 修改采购需求
     * 
     * @param fmgrePurchaseRequir 采购需求
     * @return 结果
     */
    @Override
    public int updateFmgrePurchaseRequir(FmgrePurchaseRequir fmgrePurchaseRequir)
    {
        return fmgrePurchaseRequirMapper.updateFmgrePurchaseRequir(fmgrePurchaseRequir);
    }

    /**
     * 批量删除采购需求
     * 
     * @param requirIds 需要删除的采购需求主键
     * @return 结果
     */
    @Override
    public int deleteFmgrePurchaseRequirByRequirIds(Long[] requirIds)
    {
        return fmgrePurchaseRequirMapper.deleteFmgrePurchaseRequirByRequirIds(requirIds);
    }

    /**
     * 删除采购需求信息
     * 
     * @param requirId 采购需求主键
     * @return 结果
     */
    @Override
    public int deleteFmgrePurchaseRequirByRequirId(Long requirId)
    {
        return fmgrePurchaseRequirMapper.deleteFmgrePurchaseRequirByRequirId(requirId);
    }

    @Override
    public List<FmgrePurchaseRequir> selectFmgrePurchaseRequirListByRequirIds(Long[] requirIds)
    {
        return fmgrePurchaseRequirMapper.selectFmgrePurchaseRequirListByRequirIds(requirIds);
    }
}
