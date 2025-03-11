package com.ruoyi.fmgr.service;

import java.util.List;
import com.ruoyi.fmgr.domain.FmgreSupplierBrand;

/**
 * 供应商品牌Service接口
 * 
 * @author terence
 * @date 2025-02-24
 */
public interface IFmgreSupplierBrandService 
{
    /**
     * 查询供应商品牌
     * 
     * @param brandId 供应商品牌主键
     * @return 供应商品牌
     */
    public FmgreSupplierBrand selectFmgreSupplierBrandByBrandId(Long brandId);

    /**
     * 查询供应商品牌列表
     * 
     * @param fmgreSupplierBrand 供应商品牌
     * @return 供应商品牌集合
     */
    public List<FmgreSupplierBrand> selectFmgreSupplierBrandList(FmgreSupplierBrand fmgreSupplierBrand);

    /**
     * 新增供应商品牌
     * 
     * @param fmgreSupplierBrand 供应商品牌
     * @return 结果
     */
    public int insertFmgreSupplierBrand(FmgreSupplierBrand fmgreSupplierBrand);

    /**
     * 修改供应商品牌
     * 
     * @param fmgreSupplierBrand 供应商品牌
     * @return 结果
     */
    public int updateFmgreSupplierBrand(FmgreSupplierBrand fmgreSupplierBrand);

    /**
     * 批量删除供应商品牌
     * 
     * @param brandIds 需要删除的供应商品牌主键集合
     * @return 结果
     */
    public int deleteFmgreSupplierBrandByBrandIds(Long[] brandIds);

    /**
     * 删除供应商品牌信息
     * 
     * @param brandId 供应商品牌主键
     * @return 结果
     */
    public int deleteFmgreSupplierBrandByBrandId(Long brandId);
}
