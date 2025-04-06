package com.fmger.http.site.ruoyi.commiter;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fmger.http.site.ruoyi.vo.fmgre.PunchList;
import com.fmger.utils.CSVImport;
import com.fmger.utils.IConvert;
import com.ruoyi.fmgr.domain.FmgreHrEmployee;
import com.ruoyi.fmgr.domain.FmgreHrEmployeePunch;

public class PunchCommiter extends SiteRuoyiCommiter {

	protected Map<String, FmgreHrEmployee> employees = null;
	
	protected void refreshEmployees() {
		this.employees = sf.listEmployees(new HashMap<String, Object>(){{
			put("pageNum", 1);
			put("pageSize", 10000);
		}}).rows.stream().collect(Collectors.toMap(FmgreHrEmployee::getEmployeeName, Function.identity()));
	}
	
	@Override
	public void commit(String file) {
		List<PunchList> punches = new CSVImport().importCsv(new File(file), PunchList.class, new String[]{
				"date","job","name","punchIn","punchOut"
			}, new IConvert[]{
					CSVImport.localDate1,
				null,
				null,
				CSVImport.localTime1,
				CSVImport.localTime1
		});

		this.refreshEmployees();
		LinkedList<FmgreHrEmployeePunch> eps = new LinkedList<>();
		for(PunchList punch:punches) {
			FmgreHrEmployeePunch ep = new FmgreHrEmployeePunch();
			ep.setPunchInTime(Date.from(LocalDateTime.of(punch.date, punch.punchIn.minusHours(8)).toInstant(ZoneOffset.ofHours(0))));
			ep.setPunchOutTime(Date.from(LocalDateTime.of(punch.date, punch.punchOut.minusHours(8)).toInstant(ZoneOffset.ofHours(0))));
			ep.setEmployeeId(this.employees.get(punch.name).getEmployeeId());
			ep.setPunchTypeDictid("DA");
			Duration duration = Duration.between(punch.punchIn, punch.punchOut);
			BigDecimal dur = BigDecimal.valueOf(duration.toMinutes()).divide(BigDecimal.valueOf(30).setScale(0, RoundingMode.FLOOR)).setScale(1).divide(BigDecimal.valueOf(2));
			System.out.println(dur);
			ep.setWorkDuration(dur);
			System.out.println(ep);
			System.out.println(ep.getWorkDuration());
			eps.addLast(ep);
		}
		for(FmgreHrEmployeePunch ep:eps) {
			sf.insertHrEmployeePunch(ep);
			try {
				Thread.sleep(10L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		

}
