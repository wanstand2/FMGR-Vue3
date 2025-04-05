package com.ruoyi.fmgr.controller;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.fmgr.domain.FmgreFinancePayment;
import com.ruoyi.fmgr.domain.FmgreHrEmployeeSalary;
import com.ruoyi.fmgr.domain.FmgreHrEmployeeSalaryPay;
import com.ruoyi.fmgr.domain.FmgreHrEmployeeSalaryPayBo;
import com.ruoyi.fmgr.domain.FmgreHrEmployeeSalaryPayCalcBo;
import com.ruoyi.fmgr.service.IFmgreHrEmployeePunchService;
import com.ruoyi.fmgr.service.IFmgreHrEmployeeSalaryPayService;
import com.ruoyi.fmgr.service.IFmgreHrEmployeeSalaryService;

/**
 * 员工薪酬发放Controller
 * 
 * @author terence
 * @date 2025-04-02
 */
@RestController
@RequestMapping("/hr/pay")
public class FmgreHrEmployeeSalaryPayController extends BaseController
{
    @Autowired
    private IFmgreHrEmployeeSalaryPayService fmgreHrEmployeeSalaryPayService;

    @Autowired
    private IFmgreHrEmployeePunchService fmgreHrEmployeePunchService;

    @Autowired
    private IFmgreHrEmployeeSalaryService fmgreHrEmployeeSalaryService;
    
    @Autowired
    private FmgreFinancePaymentController fmgreFinancePaymentController;

    /**
     * 查询员工薪酬发放列表
     */
    @PreAuthorize("@ss.hasPermi('hr:pay:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmgreHrEmployeeSalaryPay fmgreHrEmployeeSalaryPay)
    {
        startPage();
        List<FmgreHrEmployeeSalaryPay> list = fmgreHrEmployeeSalaryPayService.selectFmgreHrEmployeeSalaryPayList(fmgreHrEmployeeSalaryPay);
        return getDataTable(list);
    }

    /**
     * 导出员工薪酬发放列表
     */
    @PreAuthorize("@ss.hasPermi('hr:pay:export')")
    @Log(title = "员工薪酬发放", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FmgreHrEmployeeSalaryPay fmgreHrEmployeeSalaryPay)
    {
        List<FmgreHrEmployeeSalaryPay> list = fmgreHrEmployeeSalaryPayService.selectFmgreHrEmployeeSalaryPayList(fmgreHrEmployeeSalaryPay);
        ExcelUtil<FmgreHrEmployeeSalaryPay> util = new ExcelUtil<FmgreHrEmployeeSalaryPay>(FmgreHrEmployeeSalaryPay.class);
        util.exportExcel(response, list, "员工薪酬发放数据");
    }

    @PreAuthorize("@ss.hasPermi('hr:pay:list')")
    @GetMapping("/listEmployeeDates")
    public AjaxResult listByEmployees(Long[] employeeIds, String[] dates) {
        Date[] ds = new Date[dates.length];
        for(int i = 0; i<dates.length; i++) {
        	try {
				ds[i] = new SimpleDateFormat("yyyy-MM-dd").parse(dates[i]);
			} catch (ParseException e) {
				e.printStackTrace();
			}
        }
        System.out.println(ds[0]);
        List<FmgreHrEmployeeSalaryPay> list = fmgreHrEmployeeSalaryPayService.selectFmgreHrEmployeeSalaryPayByEDS(employeeIds, ds);
        return success(list);
    }
    /**
     * 获取员工薪酬发放详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:pay:query')")
    @GetMapping(value = "/{payId}")
    public AjaxResult getInfo(@PathVariable("payId") Long payId)
    {
        return success(fmgreHrEmployeeSalaryPayService.selectFmgreHrEmployeeSalaryPayByPayId(payId));
    }

    /**
     * 新增员工薪酬发放
     */
    @PreAuthorize("@ss.hasPermi('hr:pay:add')")
    @Log(title = "员工薪酬发放", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmgreHrEmployeeSalaryPay fmgreHrEmployeeSalaryPay)
    {
        return toAjax(fmgreHrEmployeeSalaryPayService.insertFmgreHrEmployeeSalaryPay(fmgreHrEmployeeSalaryPay));
    }
    
    @PreAuthorize("@ss.hasPermi('hr:act:pay')")
    @Log(title = "支付工资", businessType = BusinessType.INSERT)
    @PostMapping(value = "/pay")
    public AjaxResult pay(@RequestBody FmgreHrEmployeeSalaryPayBo payBo) {
    	FmgreHrEmployeeSalaryPay paySalary =  fmgreHrEmployeeSalaryPayService.selectFmgreHrEmployeeSalaryPayByPayId(payBo.getPayId());
    	FmgreFinancePayment payment = new FmgreFinancePayment();
    	payment.setOutAccId(payBo.getAccountId());
    	if(getUserId().equals(1L) && payBo.getUserId() != null && payBo.getUserId() != 0) {
    		//管理员可以保留设置
        	payment.setUserId(payBo.getUserId());
        } else {
        	payment.setUserId(getUserId());
        }
    	payment.setPaymentComment("支付工资:"+paySalary.getPayName());
    	payment.setPaymentAmount(paySalary.getAdjustSalary() == null ? paySalary.getTimeSalary() : paySalary.getTimeSalary().add(paySalary.getAdjustSalary()));
    	if(payBo.getPayTime() != null) {
    		payment.setPaymentTime(payBo.getPayTime());
    	} else {
    		payment.setPaymentTime(new Date());
    	}
    	int ret = fmgreFinancePaymentController._add(payment);
        FmgreHrEmployeeSalaryPay payUpdate = new FmgreHrEmployeeSalaryPay();
        payUpdate.setPayId(paySalary.getPayId());
        payUpdate.setPayTime(payment.getPaymentTime());
        payUpdate.setPaymentId(payment.getPaymentId());
        fmgreHrEmployeeSalaryPayService.updateFmgreHrEmployeeSalaryPay(payUpdate);
        return toAjax(ret);
    }

    /*
     * 输入员工号、时间给出计算结果
     */
    @PreAuthorize("@ss.hasPermi('hr:pay:query')")
    @GetMapping(value = "/calc")
    public AjaxResult calculateEmployeeMonthPay(FmgreHrEmployeeSalaryPay fmgreHrEmployeeSalaryPay) {
        if(fmgreHrEmployeeSalaryPay.getEmployeeId() == null || fmgreHrEmployeeSalaryPay.getEmployeeId() == 0
            || fmgreHrEmployeeSalaryPay.getStartTime() == null) {
            return error("参数错误");
        }
        FmgreHrEmployeeSalary salary = fmgreHrEmployeeSalaryService.selectFmgreHrEmployeeSalaryByEmployeeId(fmgreHrEmployeeSalaryPay.getEmployeeId());
        FmgreHrEmployeeSalaryPayCalcBo ret = new FmgreHrEmployeeSalaryPayCalcBo();
        if(fmgreHrEmployeeSalaryPay.getPayId() != null && fmgreHrEmployeeSalaryPay.getPayId() != 0) {
        	FmgreHrEmployeeSalaryPay pay =  fmgreHrEmployeeSalaryPayService.selectFmgreHrEmployeeSalaryPayByPayId(fmgreHrEmployeeSalaryPay.getPayId());
        	ret.setPayId(pay.getPayId());
        	ret.setPaymentId(pay.getPaymentId());
        	ret.setEmployeeId(pay.getEmployeeId());
        	ret.setPayTime(pay.getPayTime());
        	ret.setStartTime(pay.getStartTime());
        	ret.setEndTime(pay.getEndTime());
        	ret.setPieriodWorkDay(pay.getPieriodWorkDay());
        	ret.setEmployeeWorkTime(pay.getEmployeeWorkTime());
        	ret.setTimeSalary(pay.getTimeSalary());
        	ret.setAdjustSalary(pay.getAdjustSalary());
        	ret.setPayName(pay.getPayName());
        	ret.setPayComment(pay.getPayComment());
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fmgreHrEmployeeSalaryPay.getStartTime());
	        ret.setEmployeeId(fmgreHrEmployeeSalaryPay.getEmployeeId());
	        calendar.set(Calendar.DAY_OF_MONTH, 1);
	        calendar.set(Calendar.HOUR_OF_DAY, 0);
	        calendar.set(Calendar.MINUTE, 0);
	        calendar.set(Calendar.SECOND, 0);
	        calendar.set(Calendar.MILLISECOND, 0);
	        ret.setStartTime(calendar.getTime());
	        calendar.roll(Calendar.DATE, -1);
	        ret.setPieriodWorkDay(BigDecimal.valueOf(calendar.get(Calendar.DATE)));
	        calendar.set(Calendar.DAY_OF_MONTH, 1);
	        calendar.add(Calendar.MONTH, 1);
	        ret.setEndTime(calendar.getTime());
	     }
        //处理 workday
        FmgreHrEmployeeSalaryPay query = new FmgreHrEmployeeSalaryPay();
        query.setStartTime(ret.getStartTime());
        List<FmgreHrEmployeeSalaryPay> list = fmgreHrEmployeeSalaryPayService.selectFmgreHrEmployeeSalaryPayList(query);
        if(list != null && list.size() > 0) {
            ret.setPieriodWorkDay(list.get(0).getPieriodWorkDay());
            ret.setLockMonth(true);
        }
        List<FmgreHrEmployeeSalaryPayCalcBo.PunchSummay> summays = fmgreHrEmployeePunchService.selectFmgreHrEmployeePunchSummaryList(
            ret.getEmployeeId(), ret.getStartTime(), ret.getEndTime()
        );
        //可策略化的工资计算，现在用硬编码
        BigDecimal total = BigDecimal.ZERO;
        if("YUE".equals(salary.getSalaryTypeDictid())) {
            for(FmgreHrEmployeeSalaryPayCalcBo.PunchSummay summay:summays) {
                if(summay.getSumTime().compareTo(BigDecimal.valueOf(3)) < 0) {
                    summay.setSalaryCount(BigDecimal.ZERO);
                } else if(summay.getSumTime().compareTo(BigDecimal.valueOf(8)) < 0) {
                    summay.setSalaryCount(new BigDecimal("0.5"));
                } else {
                    summay.setSalaryCount(BigDecimal.valueOf(1));
                }
                total = total.add(summay.getSalaryCount());
            }
        } else if("SHI".equals(salary.getSalaryTypeDictid())) {
            for(FmgreHrEmployeeSalaryPayCalcBo.PunchSummay summary:summays) {
            	summary.setSalaryCount(summary.getSumTime()); 
            	total = total.add(summary.getSumTime());
            }
        } else {
            return error("没有方法支持的计薪方式："+salary.getSalaryTypeDictid());
        }
        ret.setEmployeeWorkTime(total);
        ret.setTimeSalary(total.multiply(salary.getSalaryAmount()));
        if("YUE".equals(salary.getSalaryTypeDictid())) {
            ret.setTimeSalary(ret.getTimeSalary().divide(ret.getPieriodWorkDay(),MathContext.DECIMAL32).setScale(2, RoundingMode.CEILING));
        }
        ret.setPunchSummays(summays);
        return success(ret);
    }
    /**
     * 修改员工薪酬发放
     */
    @PreAuthorize("@ss.hasPermi('hr:pay:edit')")
    @Log(title = "员工薪酬发放", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmgreHrEmployeeSalaryPay fmgreHrEmployeeSalaryPay)
    {
        return toAjax(fmgreHrEmployeeSalaryPayService.updateFmgreHrEmployeeSalaryPay(fmgreHrEmployeeSalaryPay));
    }

    /**
     * 删除员工薪酬发放
     */
    @PreAuthorize("@ss.hasPermi('hr:pay:remove')")
    @Log(title = "员工薪酬发放", businessType = BusinessType.DELETE)
	@DeleteMapping("/{payIds}")
    public AjaxResult remove(@PathVariable Long[] payIds)
    {
        return toAjax(fmgreHrEmployeeSalaryPayService.deleteFmgreHrEmployeeSalaryPayByPayIds(payIds));
    }
}
