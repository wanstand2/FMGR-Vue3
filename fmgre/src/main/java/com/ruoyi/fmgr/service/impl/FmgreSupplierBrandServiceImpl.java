package com.ruoyi.fmgr.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fmgr.mapper.FmgreSupplierBrandMapper;
import com.ruoyi.fmgr.domain.FmgreSupplierBrand;
import com.ruoyi.fmgr.service.IFmgreSupplierBrandService;

/**
 * 供应商品牌Service业务层处理
 * 
 * @author terence
 * @date 2025-02-24
 */
@Service
public class FmgreSupplierBrandServiceImpl implements IFmgreSupplierBrandService 
{
    @Autowired
    private FmgreSupplierBrandMapper fmgreSupplierBrandMapper;

    /**
     * 查询供应商品牌
     * 
     * @param brandId 供应商品牌主键
     * @return 供应商品牌
     */
    @Override
    public FmgreSupplierBrand selectFmgreSupplierBrandByBrandId(Long brandId)
    {
        return fmgreSupplierBrandMapper.selectFmgreSupplierBrandByBrandId(brandId);
    }

    /**
     * 查询供应商品牌列表
     * 
     * @param fmgreSupplierBrand 供应商品牌
     * @return 供应商品牌
     */
    @Override
    public List<FmgreSupplierBrand> selectFmgreSupplierBrandList(FmgreSupplierBrand fmgreSupplierBrand)
    {
        return fmgreSupplierBrandMapper.selectFmgreSupplierBrandList(fmgreSupplierBrand);
    }

    /**
     * 新增供应商品牌
     * 
     * @param fmgreSupplierBrand 供应商品牌
     * @return 结果
     */
    @Override
    public int insertFmgreSupplierBrand(FmgreSupplierBrand fmgreSupplierBrand)
    {
        return fmgreSupplierBrandMapper.insertFmgreSupplierBrand(fmgreSupplierBrand);
    }

    /**
     * 修改供应商品牌
     * 
     * @param fmgreSupplierBrand 供应商品牌
     * @return 结果
     */
    @Override
    public int updateFmgreSupplierBrand(FmgreSupplierBrand fmgreSupplierBrand)
    {
        return fmgreSupplierBrandMapper.updateFmgreSupplierBrand(fmgreSupplierBrand);
    }

    /**
     * 批量删除供应商品牌
     * 
     * @param brandIds 需要删除的供应商品牌主键
     * @return 结果
     */
    @Override
    public int deleteFmgreSupplierBrandByBrandIds(Long[] brandIds)
    {
        return fmgreSupplierBrandMapper.deleteFmgreSupplierBrandByBrandIds(brandIds);
    }

    /**
     * 删除供应商品牌信息
     * 
     * @param brandId 供应商品牌主键
     * @return 结果
     */
    @Override
    public int deleteFmgreSupplierBrandByBrandId(Long brandId)
    {
        return fmgreSupplierBrandMapper.deleteFmgreSupplierBrandByBrandId(brandId);
    }
}
