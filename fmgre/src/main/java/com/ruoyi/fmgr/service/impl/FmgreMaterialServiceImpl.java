package com.ruoyi.fmgr.service.impl;

import java.util.List;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fmgr.mapper.FmgreMaterialMapper;
import com.ruoyi.fmgr.domain.FmgreMaterial;
import com.ruoyi.fmgr.service.IFmgreMaterialService;
import java.util.ArrayList;
/**
 * 原材料Service业务层处理
 * 
 * @author terence
 * @date 2025-02-24
 */
@Service
public class FmgreMaterialServiceImpl implements IFmgreMaterialService 
{
    @Autowired
    private FmgreMaterialMapper fmgreMaterialMapper;

    /**
     * 查询原材料
     * 
     * @param materailId 原材料主键
     * @return 原材料
     */
    @Override
    public FmgreMaterial selectFmgreMaterialByMaterailId(Long materailId)
    {
        return fmgreMaterialMapper.selectFmgreMaterialByMaterailId(materailId);
    }

    @Override
    public List<FmgreMaterial> selectFmgreMaterialByMaterailIds(Collection<Long> materailIds)
    {
        if (materailIds == null || materailIds.isEmpty()) {
            return new ArrayList<>();
        }
        return fmgreMaterialMapper.selectFmgreMaterialByMaterailIds(materailIds);
    }

    /**
     * 查询原材料列表
     * 
     * @param fmgreMaterial 原材料
     * @return 原材料
     */
    @Override
    public List<FmgreMaterial> selectFmgreMaterialList(FmgreMaterial fmgreMaterial)
    {
        return fmgreMaterialMapper.selectFmgreMaterialList(fmgreMaterial);
    }

    /**
     * 新增原材料
     * 
     * @param fmgreMaterial 原材料
     * @return 结果
     */
    @Override
    public int insertFmgreMaterial(FmgreMaterial fmgreMaterial)
    {
        return fmgreMaterialMapper.insertFmgreMaterial(fmgreMaterial);
    }

    /**
     * 修改原材料
     * 
     * @param fmgreMaterial 原材料
     * @return 结果
     */
    @Override
    public int updateFmgreMaterial(FmgreMaterial fmgreMaterial)
    {
        return fmgreMaterialMapper.updateFmgreMaterial(fmgreMaterial);
    }

    /**
     * 批量删除原材料
     * 
     * @param materailIds 需要删除的原材料主键
     * @return 结果
     */
    @Override
    public int deleteFmgreMaterialByMaterailIds(Long[] materailIds)
    {
        return fmgreMaterialMapper.deleteFmgreMaterialByMaterailIds(materailIds);
    }

    /**
     * 删除原材料信息
     * 
     * @param materailId 原材料主键
     * @return 结果
     */
    @Override
    public int deleteFmgreMaterialByMaterailId(Long materailId)
    {
        return fmgreMaterialMapper.deleteFmgreMaterialByMaterailId(materailId);
    }
}
