package com.fmger;

import java.text.ParseException;

import com.fmger.http.site.ruoyi.SiteRuoyi;
import com.fmger.http.site.ruoyi.commiter.DonkeyCommiter;
import com.fmger.http.site.ruoyi.commiter.PunchCommiter;
import com.fmger.http.site.ruoyi.commiter.RefreshPaymentCommiter;
import com.fmger.http.site.ruoyi.commiter.TestCommiter;
import com.fmger.http.site.ruoyi.commiter.UpdatePaymentCommentCommiter;
public class MainCommiter {

    public static void main(String[] args) throws ParseException {
    	SiteRuoyi.profile = SiteRuoyi.LOCAL;
		if(false) {
	    	DonkeyCommiter dc = new DonkeyCommiter();
			dc.setMap("/Users/wusee/Library/Mobile Documents/com~apple~CloudDocs/Documents/Docs/板凳面/IT/快驴对照表.csv");
			dc.commit("/Users/wusee/Library/Mobile Documents/com~apple~CloudDocs/Documents/Docs/板凳面/IT/快驴订单明细列表20250201.csv");
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
//		rc.commitPurchase("/Users/wusee/Library/Mobile Documents/com~apple~CloudDocs/Documents/Docs/板凳面/IT/采购记录.csv");
//		rc.commitQuote("/Users/wusee/Library/Mobile Documents/com~apple~CloudDocs/Documents/Docs/板凳面/IT/供应商报价.csv");
    	/*
    	SiteRuoyiFast sf = new SiteRuoyiFast();
    	FmgrePurchaseItem query = new FmgrePurchaseItem();
    	query.setRequirId(1L);
    	RyTableDataInfo<FmgrePurchaseItem> table = sf.listPurchaseItems(ReflectUtils.getFields(query));
    	System.out.println(ReflectUtils.getFields(query));
    	System.out.println(table.rows);
    	*/
	}
}
