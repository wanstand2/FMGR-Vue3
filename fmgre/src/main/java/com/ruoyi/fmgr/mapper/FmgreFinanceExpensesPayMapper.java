package com.ruoyi.fmgr.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.fmgr.domain.FmgreFinanceExpensesPay;
import com.ruoyi.fmgr.domain.FmgreFinanceExpensesPayBo;
import com.ruoyi.fmgr.domain.FmgreFinanceStatisticResult;

/**
 * 费用支付Mapper接口
 * 
 * @author terence
 * @date 2025-04-01
 */
public interface FmgreFinanceExpensesPayMapper 
{
    /**
     * 查询费用支付
     * 
     * @param payId 费用支付主键
     * @return 费用支付
     */
    public FmgreFinanceExpensesPayBo selectFmgreFinanceExpensesPayByPayId(Long payId);

    /**
     * 查询费用支付列表
     * 
     * @param fmgreFinanceExpensesPay 费用支付
     * @return 费用支付集合
     */
    public List<FmgreFinanceExpensesPayBo> selectFmgreFinanceExpensesPayList(FmgreFinanceExpensesPay fmgreFinanceExpensesPay);

    /**
     * 新增费用支付
     * 
     * @param fmgreFinanceExpensesPay 费用支付
     * @return 结果
     */
    public int insertFmgreFinanceExpensesPay(FmgreFinanceExpensesPay fmgreFinanceExpensesPay);

    /**
     * 修改费用支付
     * 
     * @param fmgreFinanceExpensesPay 费用支付
     * @return 结果
     */
    public int updateFmgreFinanceExpensesPay(FmgreFinanceExpensesPay fmgreFinanceExpensesPay);

    /**
     * 删除费用支付
     * 
     * @param payId 费用支付主键
     * @return 结果
     */
    public int deleteFmgreFinanceExpensesPayByPayId(Long payId);

    /**
     * 批量删除费用支付
     * 
     * @param payIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFmgreFinanceExpensesPayByPayIds(Long[] payIds);

    public List<FmgreFinanceStatisticResult> statisticFmgreFinanceExpensesPayByMonth(@Param("deptIds") Long[] deptIds, @Param("types") String[] types, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
