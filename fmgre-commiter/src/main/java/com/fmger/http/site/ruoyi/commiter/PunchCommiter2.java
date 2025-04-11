package com.fmger.http.site.ruoyi.commiter;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fmger.http.site.ruoyi.vo.fmgre.PunchList;
import com.fmger.http.site.ruoyi.vo.fmgre.PunchList2;
import com.fmger.utils.CSVImport;
import com.fmger.utils.IConvert;
import com.ruoyi.fmgr.domain.FmgreHrEmployee;
import com.ruoyi.fmgr.domain.FmgreHrEmployeePunch;

public class PunchCommiter2 extends SiteRuoyiCommiter {

	protected Map<String, FmgreHrEmployee> employees = null;
	
	private Integer punchYear = 2025;
	
	private String[] names = new String[] {};
	
	public PunchCommiter2 setYear(Integer year) {
		this.punchYear = year;
		return this;
	}
	
	public PunchCommiter2 setNames(String[] names) {
		this.names = names;
		return this;
	}
	
	protected void refreshEmployees() {
		this.employees = sf.listEmployees(new HashMap<String, Object>(){{
			put("pageNum", 1);
			put("pageSize", 10000);
		}}).rows.stream().collect(Collectors.toMap(FmgreHrEmployee::getEmployeeName, Function.identity()));
	}
	
	@Override
	public void commit(String file) {
		List<PunchList2> punch2es = new CSVImport().importCsv(new File(file), PunchList2.class, new String[]{
				"date","hours1","hours2","hours3","hours4"
			}, new IConvert[]{
					CSVImport.localDate2,
				null,
				null,
				null,
				null
		}, 1);

		this.refreshEmployees();
		LinkedList<FmgreHrEmployeePunch> eps = new LinkedList<>();
		for(PunchList2 punch2:punch2es) {
			punch2.date.plusYears(this.punchYear - punch2.date.getYear());
			this.addEp(eps, this.names[0], punch2.date, punch2.hours1);
			this.addEp(eps, this.names[1], punch2.date, punch2.hours2);
			this.addEp(eps, this.names[2], punch2.date, punch2.hours3);
			this.addEp(eps, this.names[3], punch2.date, punch2.hours4);
			LocalTime inT = LocalTime.of(8, 30), outT = LocalTime.of(21, 0);
			eps.add(genEp("汤沛栋", inT, outT, punch2.date));
			eps.add(genEp("文师傅", inT, outT, punch2.date));
		}
		for(FmgreHrEmployeePunch ep:eps) {
			System.out.println(ep);
			sf.insertHrEmployeePunch(ep);
			try {
				Thread.sleep(50L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private FmgreHrEmployeePunch genEp(String name, LocalTime inT, LocalTime outT, LocalDate day) {
		FmgreHrEmployeePunch ep = new FmgreHrEmployeePunch();
		ep.setPunchInTime(Date.from(LocalDateTime.of(day, inT).minusHours(8).toInstant(ZoneOffset.ofHours(0))));
		ep.setPunchOutTime(Date.from(LocalDateTime.of(day, outT).minusHours(8).toInstant(ZoneOffset.ofHours(0))));
		ep.setEmployeeId(this.employees.get(name).getEmployeeId());
		ep.setPunchTypeDictid("DA");
		Duration duration = Duration.between(ep.getPunchInTime().toInstant(), ep.getPunchOutTime().toInstant());
		BigDecimal dur = BigDecimal.valueOf(duration.toMinutes()).divide(BigDecimal.valueOf(30).setScale(0, RoundingMode.FLOOR)).setScale(1).divide(BigDecimal.valueOf(2));
		ep.setWorkDuration(dur);
		if(ep.getEmployeeId() == null || ep.getEmployeeId() == 0) {
			throw new RuntimeException("没有搜索到员工:"+name);
		}
		return ep;
	}
	
	private void addEp(LinkedList<FmgreHrEmployeePunch> eps, String name, LocalDate day, BigDecimal hours) {
		LocalTime inT, outT;
		if(hours == null || hours.compareTo(BigDecimal.ZERO) == 0) {
			return;
		} else if(hours.compareTo(new BigDecimal("6.5")) == 0) {
			inT = LocalTime.of(8, 30);
			outT = LocalTime.of(15, 0);
			eps.add(this.genEp(name, inT, outT, day));
		} else if(hours.compareTo(new BigDecimal("6")) == 0) {
			inT = LocalTime.of(10, 30);
			outT = LocalTime.of(14, 30);
			eps.add(this.genEp(name, inT, outT, day));
			inT = LocalTime.of(17, 00);
			outT = LocalTime.of(19, 00);
			eps.add(this.genEp(name, inT, outT, day));
		} else if(hours.compareTo(new BigDecimal("5")) == 0) {
			inT = LocalTime.of(16, 30);
			outT = LocalTime.of(21, 30);
			eps.add(this.genEp(name, inT, outT, day));
		} else if(hours.compareTo(new BigDecimal("2")) == 0) {
			inT = LocalTime.of(11, 30);
			outT = LocalTime.of(13, 30);
			eps.add(this.genEp(name, inT, outT, day));
		} 
	}
		

}
