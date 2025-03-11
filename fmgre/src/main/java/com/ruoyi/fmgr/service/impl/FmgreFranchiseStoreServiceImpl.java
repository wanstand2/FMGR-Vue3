package com.ruoyi.fmgr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.fmgr.domain.FmgreFranchiseStore;
import com.ruoyi.fmgr.mapper.FmgreFranchiseStoreMapper;
import com.ruoyi.fmgr.service.IFmgreFranchiseStoreService;
import com.ruoyi.system.service.ISysDeptService;

/**
 * 门店Service业务层处理
 * 
 * @author terence
 * @date 2025-02-24
 */
@Service
public class FmgreFranchiseStoreServiceImpl implements IFmgreFranchiseStoreService 
{
    @Autowired
    private FmgreFranchiseStoreMapper fmgreFranchiseStoreMapper;
    @Autowired
    private ISysDeptService sysDeptService;

    /**
     * 查询门店
     * 
     * @param deptId 门店主键
     * @return 门店
     */
    @Override
    public FmgreFranchiseStore selectFmgreFranchiseStoreByDeptId(Long deptId)
    {
    	return fmgreFranchiseStoreMapper.selectFmgreFranchiseStoreByDeptId(deptId);
    }

    /**
     * 查询门店列表
     * 
     * @param fmgreFranchiseStore 门店
     * @return 门店
     */
    @Override
    public List<FmgreFranchiseStore> selectFmgreFranchiseStoreList(FmgreFranchiseStore fmgreFranchiseStore)
    {
    	return fmgreFranchiseStoreMapper.selectFmgreFranchiseStoreList(fmgreFranchiseStore);
    }

    /**
     * 新增门店
     * 
     * @param fmgreFranchiseStore 门店
     * @return 结果
     */
    @Override
    public int insertFmgreFranchiseStore(FmgreFranchiseStore fmgreFranchiseStore)
    {
        sysDeptService.insertDept(fmgreFranchiseStore);
        System.out.println("sysDeptService="+fmgreFranchiseStore.getDeptId());
        return fmgreFranchiseStoreMapper.insertFmgreFranchiseStore(fmgreFranchiseStore);
    }

    /**
     * 修改门店
     * 
     * @param fmgreFranchiseStore 门店
     * @return 结果
     */
    @Override
    public int updateFmgreFranchiseStore(FmgreFranchiseStore fmgreFranchiseStore)
    {
        sysDeptService.updateDept(fmgreFranchiseStore);
        return fmgreFranchiseStoreMapper.updateFmgreFranchiseStore(fmgreFranchiseStore);
    }

    /**
     * 批量删除门店
     * 
     * @param deptIds 需要删除的门店主键
     * @return 结果
     */
    @Override
    public int deleteFmgreFranchiseStoreByDeptIds(Long[] deptIds)
    {
        for(Long id:deptIds) {
            sysDeptService.deleteDeptById(id);
        }
        return fmgreFranchiseStoreMapper.deleteFmgreFranchiseStoreByDeptIds(deptIds);
    }

    /**
     * 删除门店信息
     * 
     * @param deptId 门店主键
     * @return 结果
     */
    @Override
    public int deleteFmgreFranchiseStoreByDeptId(Long deptId)
    {
        sysDeptService.deleteDeptById(deptId);
        return fmgreFranchiseStoreMapper.deleteFmgreFranchiseStoreByDeptId(deptId);
    }
}
