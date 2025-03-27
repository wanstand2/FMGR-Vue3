package com.fmger.http.site.ruoyi.commiter;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fmger.http.site.ruoyi.SiteRuoyiFast;
import com.fmger.http.site.ruoyi.vo.RyTableDataInfo;
import com.fmger.http.site.ruoyi.vo.fmgre.PurchaseList;
import com.fmger.utils.CSVImport;
import com.fmger.utils.IConvert;
import com.fmger.utils.ReflectUtils;
import com.ruoyi.fmgr.domain.FmgreFinanceAccount;
import com.ruoyi.fmgr.domain.FmgreFinancePayment;
import com.ruoyi.fmgr.domain.FmgreFinancePaymentPayBo;
import com.ruoyi.fmgr.domain.FmgreMaterial;
import com.ruoyi.fmgr.domain.FmgrePurchaseItem;
import com.ruoyi.fmgr.domain.FmgrePurchaseOrder;
import com.ruoyi.fmgr.domain.FmgrePurchaseOrderSubmitBo;
import com.ruoyi.fmgr.domain.FmgrePurchaseRequir;
import com.ruoyi.fmgr.domain.FmgreSupplier;
import com.ruoyi.fmgr.domain.FmgreSupplierQuote;

public class PurchaseCommiter extends SiteRuoyiCommiter {

	public void commit(String filename) {
		List<PurchaseList> purchases = new CSVImport().importCsv(new File(filename), PurchaseList.class, new String[]{
				"purchaseCode",
				"happenTime",
				"materaiCode",
				"materaiName",
				"supplierCode",
				"supplierName",
				"happenUnit",
				"happenNum",
				"happenSize",
				"happenTotalSize",
				"itemTotalPrice",
				"payAccountString",
				"purchaseTotalPrice",
				"comment",
				"paymentCode"
			}, new IConvert[]{
				null,
				SiteRuoyiFast.dateConv,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				SiteRuoyiFast.priceConv,
				null,
				SiteRuoyiFast.priceConv,
				null,
				null
			});
		this.refreshSuppliers();
		this.refreshMaterials();
		this.refreshSupplierBrands();
		this.refreshPackDictDatas();
		this.refreshAccounts();
		this.refreshSupplierQuotas();
		Map<String, FmgrePurchaseOrderSubmitBo> orders = new HashMap<>();
		Map<String, FmgrePurchaseRequir> requirs = new HashMap<>();
		Map<String, FmgreFinancePayment> payments = new HashMap<>();
		Map<String, FmgreFinancePaymentPayBo> paymentsPayBo = new HashMap<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		for(PurchaseList purchase:purchases) {
			FmgreMaterial material = this.materials.get(purchase.materaiCode);
			if(material == null) {
				throw new RuntimeException("物料不存在: " + purchase.materaiCode);
			}
			purchase.setMaterailId(material.getMaterailId());
			FmgreSupplier supplier = this.suppliers.get(purchase.supplierCode);
			if(supplier == null) {
				throw new RuntimeException("供应商不存在: " + purchase.supplierCode);
			}
			purchase.setSupplierId(supplier.getSupplierId());
			//查找报价
			List<FmgreSupplierQuote> quotas = this.supplierQuotas.get(supplier.getSupplierId()*10000+material.getMaterailId());
			if(quotas == null || quotas.size() == 0) {
				throw new RuntimeException("物料不存在: " + purchase.materaiCode);
			}
			for(FmgreSupplierQuote quota:quotas) {
				if(quota.getQuoteId().equals(499L)) {
//					System.out.println(purchase);
				}
				if(quota.getQuotaTime().after(purchase.happenTime)) continue;
				if(purchase.happenSize.compareTo(quota.getPackSize()) != 0) {
					continue;
				}
				if(purchase.happenNum.compareTo(BigDecimal.ZERO) != 0 && 
						quota.getQuotaPrice().compareTo(purchase.getItemTotalPrice().divide(purchase.happenNum, 2, BigDecimal.ROUND_HALF_UP)) != 0) {
					System.out.println("报价不一致: " + quota);
					System.out.println("报价不一致: " + purchase);
					throw new RuntimeException("报价不一致: " + purchase.materaiCode + " " + purchase.supplierCode + " " + quota.getQuotaTime() + " " + quota.getQuotaPrice() + " " + purchase.getItemTotalPrice() + "/" + purchase.happenNum);
				}
				purchase.quota = quota;
				purchase.setQuotaId(quota.getQuoteId());
				purchase.setRequirUnitDictid(quota.getPackUnitDictid());
				purchase.setOrderUnitDictid(quota.getPackUnitDictid());
				purchase.setRequirNum(purchase.happenNum);
				purchase.setOrderNum(purchase.happenNum);
				//原表格没算包装大小，这里补上
				purchase.setOrderAmount(purchase.happenNum.multiply(quota.getPackSize()));
				//submitbo部分
//				purchase.setQuoteUnitDictid(quota.getPackUnitDictid());
				purchase.setQuotePrice(quota.getQuotaPrice());
//				purchase.setBrandId(quota.getSupplierBrandId());
				break;
			}
			if(purchase.quota == null) {
				System.out.println("报价不存在: " + purchase);
				System.out.println(quotas);
				throw new RuntimeException("报价不存在: " + purchase.materaiCode + " " + purchase.supplierCode);
			}
			FmgrePurchaseRequir requir = requirs.get(sdf.format(purchase.happenTime));
			if(requir == null) {
				requir = new FmgrePurchaseRequir();
				requir.setRequirTime(purchase.happenTime);
				requir.setRequirUserId(100L); //汤
				requir.setDeptId(201L);
				requir.setRequirComment("导入云文档数据");
				requirs.put(sdf.format(purchase.happenTime), requir);
				requir.setRequirItems(new ArrayList<>());
				purchase.requir = requir;
			}
			requir.getRequirItems().add(purchase);
			FmgrePurchaseOrderSubmitBo order = orders.get(purchase.purchaseCode);
			if("2412197".equals(purchase.purchaseCode)) {
				System.out.println(purchase);
			}
			if(order == null) {
				order = new FmgrePurchaseOrderSubmitBo();
				order.setUserId(101L); //汪
				order.setSupplierId(purchase.getSupplierId());
				order.setOrderCode(purchase.purchaseCode);
				order.setOrderTime(purchase.happenTime);
				//修正物流方式
				if(purchase.supplierName.equals("快驴")) {
					order.setDeliveryDictid("RI");
					order.setUserId(100L);
				} else if(purchase.supplierName.contains("淘宝")) {
					order.setDeliveryDictid("KUAI");
				} else {
					order.setDeliveryDictid("ZITI");
				}
				order.setOrderTotalPrice(BigDecimal.ZERO);
				order.setOrderComment("导入云文档数据");
				order.setItems(new ArrayList<>());
				orders.put(purchase.purchaseCode, order);
				FmgreFinanceAccount account = this.accounts.get(purchase.payAccountString);
				if(account != null) {
					order.setAccountId(account.getAccountId());
				}
			}
			order.getItems().add(purchase);
			purchase.order = order;
			purchase.order.setOrderTotalPrice(purchase.order.getOrderTotalPrice().add(purchase.getItemTotalPrice()));

			/*
			//当场结算的payment
			FmgreFinancePayment payment = payments.get("O-" + purchase.purchaseCode);
			if(payment == null && purchase.payAccountString.equals("卡")) {
				payment = new FmgreFinancePayment();
				payment.setUserId(1L);
				payment.setPaymentComment("导入云文档数据:" + purchase.purchaseCode);
				payment.setPaymentAmount(BigDecimal.ZERO);
				payment.setPaymentTime(purchase.happenTime);
				FmgreFinanceAccount account = this.accounts.get(purchase.payAccountString);
				if("4".equals(purchase.purchaseCode)) {
					account = this.accounts.get("卡"); //1121特殊处理
					payment.setPaymentTime(sdf.parse("20241121"));
					payment.setPaymentComment("导入云文档数据:4:洞泾芷妍后付");
				}
				payment.setOutAccId(account.getAccountId());
				payments.put("O-" + purchase.purchaseCode, payment);
			}
			purchase.payment = payment;
			if(purchase.payment != null) {
				purchase.payment.setPaymentAmount(purchase.payment.getPaymentAmount().add(purchase.itemTotalPrice));
			} 
			*/


			//合计项目
			if(purchase.paymentCode != null && purchase.paymentCode.length() > 0) {
				FmgreFinanceAccount account = this.accounts.get("卡");
				FmgreFinanceAccount inAccount = this.accounts.get(purchase.payAccountString);
				if(account == null) {
					throw new RuntimeException("账户不存在: " + purchase.payAccountString);
				}
				if(inAccount == null) {
					FmgreFinancePaymentPayBo paymentMergePay = paymentsPayBo.get(purchase.paymentCode);
					if(paymentMergePay == null) {
						paymentMergePay = new FmgreFinancePaymentPayBo();
						paymentsPayBo.put(purchase.paymentCode, paymentMergePay);
						paymentMergePay.setAccountId(account.getAccountId());
						paymentMergePay.setOrderIds(new ArrayList<>());
						paymentMergePay.setComment("合并付款: " + purchase.paymentCode);
						paymentMergePay.setPaymentTime(purchase.happenTime);
					}
					if(paymentMergePay.getPaymentTime().compareTo(purchase.happenTime) < 0) {
						paymentMergePay.setPaymentTime(purchase.happenTime);
					}
					purchase.paymentMergePay = paymentMergePay;
				} else {
					FmgreFinancePayment paymentReimbursement = payments.get(purchase.paymentCode);
					if(paymentReimbursement == null) {
						paymentReimbursement = new FmgreFinancePayment();
						paymentReimbursement.setOutAccId(account.getAccountId());
						if(inAccount != null) {
							paymentReimbursement.setInAccId(inAccount.getAccountId());
						}
						paymentReimbursement.setUserId(100L);
						paymentReimbursement.setPaymentComment(purchase.paymentCode);
						paymentReimbursement.setPaymentAmount(BigDecimal.ZERO);
						paymentReimbursement.setPaymentComment("报销: " + purchase.paymentCode);
						paymentReimbursement.setPaymentTime(purchase.happenTime);
						payments.put(purchase.paymentCode, paymentReimbursement);
					}
					if(paymentReimbursement.getPaymentTime().compareTo(purchase.happenTime) < 0) {
						paymentReimbursement.setPaymentTime(purchase.happenTime);
					}
					paymentReimbursement.setPaymentAmount(paymentReimbursement.getPaymentAmount().add(purchase.purchaseTotalPrice));
					purchase.paymentReimbursement = paymentReimbursement;
				}
			}
		}
		//修正汤未记账的支付
		FmgreFinancePayment _payment = payments.get("B2501281");
		_payment.setPaymentAmount(_payment.getPaymentAmount().add(new BigDecimal("359.33").add(new BigDecimal("30"))));
		//修正物流方式
		for(PurchaseList purchase:purchases) {
			if(purchase.materaiName.equals("物流费")) {
				for(PurchaseList p:purchases) {
					if(p.happenTime.equals(purchase.happenTime)
						&& p.order.getDeliveryDictid().equals("ZITI")) {
						p.order.setDeliveryDictid("PAO");
						break;
					}
				}
			}
		}
		/*
		List<String> paymentCodes = new ArrayList<>();
		for(Map.Entry<String, FmgreFinancePayment> entry:payments.entrySet()) {
			if(entry.getKey().startsWith("B")) {
				paymentCodes.add(entry.getKey());
			}
		}
		Collections.sort(paymentCodes, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				int ret = o1.charAt(0) == o2.charAt(0) ? 0 : (o1.charAt(0) > o2.charAt(0) ? 1 : -1);
				if(ret == 0) {
					Integer i1 = Integer.parseInt(o1.replace("B", "").replace("O", "").replace("-", ""));
					Integer i2 = Integer.parseInt(o2.replace("B", "").replace("O", "").replace("-", ""));
					return i1.compareTo(i2);
				}
				return ret;
			}
		});
		for(String paymentCode:paymentCodes) {
			System.out.println(paymentCode + " " + payments.get(paymentCode));
		}
		*/
		System.out.println("requirs=" + requirs.size());
		System.out.println("orders=" + orders.size());
		System.out.println("payments=" + payments.size());
		
		
		//Step 1 提交采购需求
		for(Map.Entry<String, FmgrePurchaseRequir> entry:requirs.entrySet()) {
			RyTableDataInfo<FmgrePurchaseRequir> table = sf.listPurchaseRequirs(ReflectUtils.getFields(entry.getValue()));
			FmgrePurchaseRequir existRequir = table.rows.stream().findFirst().orElse(null);
			if(existRequir == null) {
				sf.insertPurchaseRequir(entry.getValue());
				table = sf.listPurchaseRequirs(ReflectUtils.getFields(entry.getValue()));
				existRequir = table.rows.stream().findFirst().orElse(null);
			}
			entry.getValue().setRequirId(existRequir.getRequirId());
			FmgrePurchaseItem queryItem = new FmgrePurchaseItem();
			queryItem.setRequirId(existRequir.getRequirId());
			List<FmgrePurchaseItem> items = sf.listPurchaseItems(ReflectUtils.getFields(queryItem)).rows;
			/*
			for(FmgrePurchaseItem item:entry.getValue().getRequirItems()) {
				FmgrePurchaseItem _item = items.stream().filter(i -> 
					( i.getMaterailId().equals(item.getMaterailId()) && i.getQuotaId().equals(item.getQuotaId()) )
					).findFirst().orElse(null);
				if(_item == null) {
					throw new RuntimeException("采购需求项目不存在: " + item);
				}
				item.setItemId(_item.getItemId());
				item.setRequirId(existRequir.getRequirId());
			}*/

			for(FmgrePurchaseItem item:items) {
				FmgrePurchaseItem _item = entry.getValue().getRequirItems().stream().filter(i -> 
					( i.getMaterailId().equals(item.getMaterailId()) && i.getQuotaId().equals(item.getQuotaId())
							&& i.getItemTotalPrice().compareTo(item.getItemTotalPrice()) == 0
							&& i.getItemId() == null)
					).findFirst().orElse(null);
				if(_item == null) {
					throw new RuntimeException("采购需求项目不存在: " + item);
				}
				_item.setItemId(item.getItemId());
				_item.setRequirId(existRequir.getRequirId());
			}
			try {
				Thread.sleep(10L);
			} catch (InterruptedException e) { }
		}
		
		//Step 2 提交采购订单
		for(Map.Entry<String, FmgrePurchaseOrderSubmitBo> entry:orders.entrySet()) {
			RyTableDataInfo<FmgrePurchaseOrder> table = sf.listPurchaseOrders(ReflectUtils.getFields(entry.getValue()));
			FmgrePurchaseOrder existOrder = table.rows.stream().findFirst().orElse(null);
//			System.out.println(entry.getValue());
			System.out.println("order:" + entry.getValue().getOrderCode());
			if("10".equals(entry.getValue().getOrderCode())) {
				System.out.println("order:" + entry.getValue().getOrderCode());
			}
			if(existOrder == null) {
				sf.submitPurchaseOrder(entry.getValue());
				table = sf.listPurchaseOrders(ReflectUtils.getFields(entry.getValue()));
				existOrder = table.rows.stream().findFirst().orElse(null);
			}
			entry.getValue().setOrderId(existOrder.getOrderId());
			FmgrePurchaseItem queryItem = new FmgrePurchaseItem();
			queryItem.setOrderId(existOrder.getOrderId());
			List<FmgrePurchaseItem> items = sf.listPurchaseItems(ReflectUtils.getFields(queryItem)).rows;
			for(FmgrePurchaseItem item:entry.getValue().getItems()) {
				FmgrePurchaseItem _item = items.stream().filter(i2 -> 
					( i2.getMaterailId().equals(item.getMaterailId()) && i2.getQuotaId().equals(item.getQuotaId()) )
					).findFirst().orElse(null);
				if(_item == null) {
					throw new RuntimeException("采购订单项目不存在");
				}
				item.setItemId(_item.getItemId());
				item.setOrderId(existOrder.getOrderId());
			}
			try {
				Thread.sleep(10L);
			} catch (InterruptedException e) { }
		} 
		
		//Step 3 提交报销
		for(Map.Entry<String, FmgreFinancePayment> entry:payments.entrySet()) {
			FmgreFinancePayment payment = sf.listFinancePayments(new HashMap<String, Object>(){{
				put("paymentComment", entry.getValue().getPaymentComment());
			}}).rows.stream().findFirst().orElse(null);
			if(payment == null) {
				System.out.println("+payment:" + entry.getValue());
				sf.insertFinancePayment(entry.getValue());
			} else {
				entry.getValue().setPaymentId(payment.getPaymentId());
			}
		}
		for(PurchaseList purchase:purchases) {
			if(purchase.paymentMergePay != null) {
				purchase.paymentMergePay.getOrderIds().add(purchase.order.getOrderId());
			}
		}
		for(Map.Entry<String, FmgreFinancePaymentPayBo> entry:paymentsPayBo.entrySet()) {
			FmgreFinancePayment payment = sf.listFinancePayments(new HashMap<String, Object>(){{
				put("paymentComment", entry.getValue().getComment());
			}}).rows.stream().findFirst().orElse(null);
			if(payment == null) {
				System.out.println("+paymentBo:" + entry.getValue());
				sf.payFinancePayment(entry.getValue());
			}
		}
	}

}
