package com.ruoyi.fmgr.mapper;

import java.util.List;
import com.ruoyi.fmgr.domain.FmgreSupplierBrand;

/**
 * 供应商品牌Mapper接口
 * 
 * @author terence
 * @date 2025-02-24
 */
public interface FmgreSupplierBrandMapper 
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
     * 删除供应商品牌
     * 
     * @param brandId 供应商品牌主键
     * @return 结果
     */
    public int deleteFmgreSupplierBrandByBrandId(Long brandId);

    /**
     * 批量删除供应商品牌
     * 
     * @param brandIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFmgreSupplierBrandByBrandIds(Long[] brandIds);
}
