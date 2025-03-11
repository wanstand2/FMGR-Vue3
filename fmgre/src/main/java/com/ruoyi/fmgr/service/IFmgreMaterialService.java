package com.ruoyi.fmgr.service;

import java.util.List;
import java.util.Collection;
import com.ruoyi.fmgr.domain.FmgreMaterial;

/**
 * 原材料Service接口
 * 
 * @author terence
 * @date 2025-02-24
 */
public interface IFmgreMaterialService 
{
    /**
     * 查询原材料
     * 
     * @param materailId 原材料主键
     * @return 原材料
     */
    public FmgreMaterial selectFmgreMaterialByMaterailId(Long materailId);

    public List<FmgreMaterial> selectFmgreMaterialByMaterailIds(Collection<Long> materailIds);

    /**
     * 查询原材料列表
     * 
     * @param fmgreMaterial 原材料
     * @return 原材料集合
     */
    public List<FmgreMaterial> selectFmgreMaterialList(FmgreMaterial fmgreMaterial);

    /**
     * 新增原材料
     * 
     * @param fmgreMaterial 原材料
     * @return 结果
     */
    public int insertFmgreMaterial(FmgreMaterial fmgreMaterial);

    /**
     * 修改原材料
     * 
     * @param fmgreMaterial 原材料
     * @return 结果
     */
    public int updateFmgreMaterial(FmgreMaterial fmgreMaterial);

    /**
     * 批量删除原材料
     * 
     * @param materailIds 需要删除的原材料主键集合
     * @return 结果
     */
    public int deleteFmgreMaterialByMaterailIds(Long[] materailIds);

    /**
     * 删除原材料信息
     * 
     * @param materailId 原材料主键
     * @return 结果
     */
    public int deleteFmgreMaterialByMaterailId(Long materailId);
}
