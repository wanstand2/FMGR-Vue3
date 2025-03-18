package com.ruoyi.fmgr.service.impl;

import java.util.List;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fmgr.mapper.FmgreSupplierMapper;
import com.ruoyi.fmgr.domain.FmgreSupplier;
import com.ruoyi.fmgr.service.IFmgreSupplierService;

/**
 * 供应商Service业务层处理
 * 
 * @author terence
 * @date 2025-02-24
 */
@Service
public class FmgreSupplierServiceImpl implements IFmgreSupplierService 
{
    @Autowired
    private FmgreSupplierMapper fmgreSupplierMapper;


    /**
     * 查询供应商
     * 
     * @param supplierId 供应商主键
     * @return 供应商
     */
    @Override
    public FmgreSupplier selectFmgreSupplierBySupplierId(Long supplierId)
    {
        return fmgreSupplierMapper.selectFmgreSupplierBySupplierId(supplierId);
    }

    /**
     * 查询供应商列表
     * 
     * @param fmgreSupplier 供应商
     * @return 供应商
     */
    @Override
    public List<FmgreSupplier> selectFmgreSupplierList(FmgreSupplier fmgreSupplier)
    {
        return fmgreSupplierMapper.selectFmgreSupplierList(fmgreSupplier);
    }

    public List<FmgreSupplier> selectFmgreSupplierList(Collection<Long> supplierIds, FmgreSupplier fmgreSupplier) {
        return fmgreSupplierMapper.selectFmgreSupplierListSupplierIds(supplierIds, fmgreSupplier);
    }

    /**
     * 新增供应商
     * 
     * @param fmgreSupplier 供应商
     * @return 结果
     */
    @Override
    public int insertFmgreSupplier(FmgreSupplier fmgreSupplier)
    {
        return fmgreSupplierMapper.insertFmgreSupplier(fmgreSupplier);
    }

    /**
     * 修改供应商
     * 
     * @param fmgreSupplier 供应商
     * @return 结果
     */
    @Override
    public int updateFmgreSupplier(FmgreSupplier fmgreSupplier)
    {
        return fmgreSupplierMapper.updateFmgreSupplier(fmgreSupplier);
    }

    /**
     * 批量删除供应商
     * 
     * @param supplierIds 需要删除的供应商主键
     * @return 结果
     */
    @Override
    public int deleteFmgreSupplierBySupplierIds(Long[] supplierIds)
    {
        return fmgreSupplierMapper.deleteFmgreSupplierBySupplierIds(supplierIds);
    }

    /**
     * 删除供应商信息
     * 
     * @param supplierId 供应商主键
     * @return 结果
     */
    @Override
    public int deleteFmgreSupplierBySupplierId(Long supplierId)
    {
        return fmgreSupplierMapper.deleteFmgreSupplierBySupplierId(supplierId);
    }

    @Override
    public List<FmgreSupplier> selectFmgreSupplierListBySupplierIds(Long[] supplierIds)
    {
        return fmgreSupplierMapper.selectFmgreSupplierListBySupplierIds(supplierIds);
    }
}
