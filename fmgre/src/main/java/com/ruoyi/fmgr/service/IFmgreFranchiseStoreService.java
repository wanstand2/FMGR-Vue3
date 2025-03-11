package com.ruoyi.fmgr.service;

import java.util.List;
import com.ruoyi.fmgr.domain.FmgreFranchiseStore;

/**
 * 门店Service接口
 * 
 * @author terence
 * @date 2025-02-24
 */
public interface IFmgreFranchiseStoreService 
{
    /**
     * 查询门店
     * 
     * @param deptId 门店主键
     * @return 门店
     */
    public FmgreFranchiseStore selectFmgreFranchiseStoreByDeptId(Long deptId);

    /**
     * 查询门店列表
     * 
     * @param fmgreFranchiseStore 门店
     * @return 门店集合
     */
    public List<FmgreFranchiseStore> selectFmgreFranchiseStoreList(FmgreFranchiseStore fmgreFranchiseStore);

    /**
     * 新增门店
     * 
     * @param fmgreFranchiseStore 门店
     * @return 结果
     */
    public int insertFmgreFranchiseStore(FmgreFranchiseStore fmgreFranchiseStore);

    /**
     * 修改门店
     * 
     * @param fmgreFranchiseStore 门店
     * @return 结果
     */
    public int updateFmgreFranchiseStore(FmgreFranchiseStore fmgreFranchiseStore);

    /**
     * 批量删除门店
     * 
     * @param deptIds 需要删除的门店主键集合
     * @return 结果
     */
    public int deleteFmgreFranchiseStoreByDeptIds(Long[] deptIds);

    /**
     * 删除门店信息
     * 
     * @param deptId 门店主键
     * @return 结果
     */
    public int deleteFmgreFranchiseStoreByDeptId(Long deptId);
}
