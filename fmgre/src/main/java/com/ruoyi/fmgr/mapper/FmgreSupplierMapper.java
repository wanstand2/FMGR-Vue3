package com.ruoyi.fmgr.mapper;

import java.util.List;
import com.ruoyi.fmgr.domain.FmgreSupplier;

/**
 * 供应商Mapper接口
 * 
 * @author terence
 * @date 2025-02-24
 */
public interface FmgreSupplierMapper 
{
    /**
     * 查询供应商
     * 
     * @param supplierId 供应商主键
     * @return 供应商
     */
    public FmgreSupplier selectFmgreSupplierBySupplierId(Long supplierId);

    /**
     * 查询供应商列表
     * 
     * @param fmgreSupplier 供应商
     * @return 供应商集合
     */
    public List<FmgreSupplier> selectFmgreSupplierList(FmgreSupplier fmgreSupplier);

    /**
     * 新增供应商
     * 
     * @param fmgreSupplier 供应商
     * @return 结果
     */
    public int insertFmgreSupplier(FmgreSupplier fmgreSupplier);

    /**
     * 修改供应商
     * 
     * @param fmgreSupplier 供应商
     * @return 结果
     */
    public int updateFmgreSupplier(FmgreSupplier fmgreSupplier);

    /**
     * 删除供应商
     * 
     * @param supplierId 供应商主键
     * @return 结果
     */
    public int deleteFmgreSupplierBySupplierId(Long supplierId);

    /**
     * 批量删除供应商
     * 
     * @param supplierIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFmgreSupplierBySupplierIds(Long[] supplierIds);

    /**
     * 根据ID列表查询供应商列表
     * 
     * @param supplierIds 供应商主键集合
     * @return 供应商集合
     */
    public List<FmgreSupplier> selectFmgreSupplierListBySupplierIds(Long[] supplierIds);
}
