package com.fmger.http.site.ruoyi.commiter;

import java.util.HashMap;
import java.util.List;

import com.ruoyi.fmgr.domain.FmgreFinancePayment;
import com.ruoyi.fmgr.domain.FmgrePurchaseOrder;

public class UpdatePaymentCommentCommiter extends SiteRuoyiCommiter {

	@Override
	public void commit(String file) {
		List<FmgreFinancePayment> payments = sf.listPayments(new HashMap<String, Object>(){{
			put("pageNum", 1);
			put("pageSize", 10000);
		}}).rows;
		for(FmgreFinancePayment payment : payments) {
			if(!"订单付款".equals(payment.getPaymentComment())) {
				continue;
			}
			List<FmgrePurchaseOrder> orders = sf.listPurchaseOrders(new HashMap<String, Object>() {{
				put("paymentId", payment.getPaymentId());
			}}).rows;
			if(orders.size() < 1) {
				throw new RuntimeException("不匹配的支付：PaymentId=" + payment.getPaymentId());
			} else if(orders.size() < 2) {
				payment.setPaymentComment("支付订单：" + orders.get(0).getOrderCode());
			} else {
				payment.setPaymentComment("支付多个订单（" + orders.size() + "）：" + orders.get(0).getOrderCode());
			}
			sf.updatePaymentComment(payment);
		}
		
	}

}
