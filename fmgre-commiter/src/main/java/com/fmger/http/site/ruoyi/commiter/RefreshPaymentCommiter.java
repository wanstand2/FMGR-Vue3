package com.fmger.http.site.ruoyi.commiter;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.fmgr.domain.FmgreFinancePayment;

public class RefreshPaymentCommiter extends SiteRuoyiCommiter {

	@Override
	public void commit(String file) {
		List<FmgreFinancePayment> payments = sf.listPayments(new HashMap<String, Object>(){{
			put("pageNum", 1);
			put("pageSize", 10000);
			put("orderByColumn","paymentTime-paymentId");
			put("isAsc","descending-descending");
			put("accountId","102");
		}}).rows;
		List<Long> ids = payments.stream().map(FmgreFinancePayment::getPaymentId).collect(Collectors.toList());
		System.out.println(ids);
		for(int i=ids.size();i>0;i--) {
			System.out.println(ids.get(i-1));
			this.sf.refreshPayment(ids.get(i-1));
			try {
				Thread.sleep(100L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
