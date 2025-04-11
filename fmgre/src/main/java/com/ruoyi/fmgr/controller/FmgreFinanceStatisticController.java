package com.ruoyi.fmgr.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.fmgr.domain.FmgreFinanceStatisticQuery;
import com.ruoyi.fmgr.domain.FmgreFinanceStatisticResult;
import com.ruoyi.fmgr.mapper.FmgreFinanceExpensesPayMapper;
import com.ruoyi.fmgr.mapper.FmgreFinanceIncomePayMapper;
import com.ruoyi.fmgr.mapper.FmgreHrEmployeeSalaryPayMapper;
import com.ruoyi.fmgr.mapper.FmgrePurchaseItemMapper;

@RestController
@RequestMapping("/finance/statistic")
public class FmgreFinanceStatisticController extends BaseController {

    @Autowired
	private FmgreFinanceExpensesPayMapper fmgreFinanceExpensesPayMapper;

    @Autowired
	private FmgreFinanceIncomePayMapper fmgreFinanceIncomePayMapper;
	
    @Autowired
    private FmgreHrEmployeeSalaryPayMapper fmgreHrEmployeeSalaryPayMapper;

    @Autowired
    private FmgrePurchaseItemMapper fmgrePurchaseItemMapper;

    /**
     * 查询费用支付列表
     */
    @PreAuthorize("@ss.hasPermi('finance:statistic:list')")
    @GetMapping("/list")
    public AjaxResult incomMonthlyList(FmgreFinanceStatisticQuery query) {
    	String type = query.getType();
    	String[] subTypes = null;
    	List<FmgreFinanceStatisticResult> results = null;
    	if(type.contains(".")) {
    		subTypes = type.substring(type.indexOf('.')+1).split(":");
    		type = type.substring(0, type.indexOf('.'));
    	}
	    switch(type) {
	    	case "income":
	    		results = fmgreFinanceIncomePayMapper.statisticFmgreFinanceIncomePayByMonth(query.getDeptIds(), subTypes, query.getStartTime(), query.getEndTime());
	    		break;
	    	case "expense":
	    		results = fmgreFinanceExpensesPayMapper.statisticFmgreFinanceExpensesPayByMonth(query.getDeptIds(), subTypes, query.getStartTime(), query.getEndTime());
	    		break;
	    	case "salary":
                results = fmgreHrEmployeeSalaryPayMapper.statisticFmgreHrEmployeeSalaryPayByMonth(query.getDeptIds(), subTypes, query.getStartTime(), query.getEndTime());
	    		break;
	    	case "purchase":
                results = fmgrePurchaseItemMapper.statisticFmgrePurchaseItemByMonth(query.getDeptIds(), subTypes, query.getStartTime(), query.getEndTime());
	    		break;
	    }
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    ZoneId zoneId = ZoneId.systemDefault();
	    if(results != null) {
	    	results.stream().forEach(r -> {
	    		if(r.getMonth() != null) {
	    			LocalDate ldt = LocalDate.parse(r.getMonth()+"-01", dtf);
	    			r.setTime(Date.from(ldt.atStartOfDay(zoneId).toInstant()));
	    	}});
	    }
	    if(results != null) {
	    	return success(results);
	    }
	    return error("没有找到对应的类型:" + type);
    }
}
