package com.fmger.http.site.ruoyi.vo.fmgre;

import java.time.LocalDate;
import java.time.LocalTime;

public class PunchList {

	public LocalDate date;
	
	public String job;
	
	public String name;
	
	public LocalTime punchIn;
	
	public LocalTime punchOut;

	@Override
	public String toString() {
		return "PunchList [date=" + date + ", job=" + job + ", name=" + name + ", punchIn=" + punchIn + ", punchOut="
				+ punchOut + "]";
	}
	
	
}
