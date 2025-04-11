package com.ruoyi.fmgr.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.fmgr.domain.FmgreFinanceIncomePay;
import com.ruoyi.fmgr.domain.FmgreFinanceStatisticResult;

/**
 * 收入Mapper接口
 * 
 * @author terence
 * @date 2025-04-09
 */
public interface FmgreFinanceIncomePayMapper 
{
    /**
     * 查询收入
     * 
     * @param incomeId 收入主键
     * @return 收入
     */
    public FmgreFinanceIncomePay selectFmgreFinanceIncomePayByIncomeId(Long incomeId);

    /**
     * 查询收入列表
     * 
     * @param fmgreFinanceIncomePay 收入
     * @return 收入集合
     */
    public List<FmgreFinanceIncomePay> selectFmgreFinanceIncomePayList(FmgreFinanceIncomePay fmgreFinanceIncomePay);

    /**
     * 新增收入
     * 
     * @param fmgreFinanceIncomePay 收入
     * @return 结果
     */
    public int insertFmgreFinanceIncomePay(FmgreFinanceIncomePay fmgreFinanceIncomePay);

    /**
     * 修改收入
     * 
     * @param fmgreFinanceIncomePay 收入
     * @return 结果
     */
    public int updateFmgreFinanceIncomePay(FmgreFinanceIncomePay fmgreFinanceIncomePay);

    /**
     * 删除收入
     * 
     * @param incomeId 收入主键
     * @return 结果
     */
    public int deleteFmgreFinanceIncomePayByIncomeId(Long incomeId);

    /**
     * 批量删除收入
     * 
     * @param incomeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFmgreFinanceIncomePayByIncomeIds(Long[] incomeIds);

	public List<FmgreFinanceStatisticResult> statisticFmgreFinanceIncomePayByMonth(@Param("deptIds") Long[] deptIds, @Param("types") String[] types, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
