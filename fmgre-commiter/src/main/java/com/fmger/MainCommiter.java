package com.fmger;

import java.text.ParseException;

import com.fmger.http.site.ruoyi.SiteRuoyi;
import com.fmger.http.site.ruoyi.commiter.DonkeyCommiter;
import com.fmger.http.site.ruoyi.commiter.NoodleCommiter;
import com.fmger.http.site.ruoyi.commiter.PunchCommiter;
import com.fmger.http.site.ruoyi.commiter.PunchCommiter2;
import com.fmger.http.site.ruoyi.commiter.RefreshPaymentCommiter;
import com.fmger.http.site.ruoyi.commiter.TestCommiter;
import com.fmger.http.site.ruoyi.commiter.UpdatePaymentCommentCommiter;
public class MainCommiter {

    public static void main(String[] args) throws ParseException {
    	SiteRuoyi.profile = SiteRuoyi.ZZSELECT;
		if(true) {
	    	DonkeyCommiter dc = new DonkeyCommiter();
			dc.setMap("/Users/wusee/Library/Mobile Documents/com~apple~CloudDocs/Documents/Docs/板凳面/IT/快驴对照表.csv");
			dc.commit("/Users/wusee/Library/Mobile Documents/com~apple~CloudDocs/Documents/Docs/板凳面/IT/快驴订单明细列表20250407.csv");
		}
		if(false) {
			UpdatePaymentCommentCommiter upcc = new UpdatePaymentCommentCommiter();
			upcc.commit("");
		}
		if(false) {
			TestCommiter tc = new TestCommiter();
			tc.commit("");
		}
		if(false) {
			RefreshPaymentCommiter rpc = new RefreshPaymentCommiter();
			rpc.commit("");
		}
		if(false) {
			PunchCommiter pc = new PunchCommiter();
			pc.commit("/Users/wusee/Library/Mobile Documents/com~apple~CloudDocs/Documents/Docs/板凳面/IT/员工打卡.csv");
		}
		if(true) {
			PunchCommiter2 pc = new PunchCommiter2().setYear(2025).setNames(new String[] {"何爱如","何有如","赵阿姨1","武阿姨"});
			pc.commit("/Users/wusee/Library/Mobile Documents/com~apple~CloudDocs/Documents/Docs/板凳面/IT/考勤2025.csv");
		}
		if(true) {
			NoodleCommiter nc = new NoodleCommiter();
			nc.commit("/Users/wusee/Library/Mobile Documents/com~apple~CloudDocs/Documents/Docs/板凳面/IT/面2025.csv");
		}
	}
}
