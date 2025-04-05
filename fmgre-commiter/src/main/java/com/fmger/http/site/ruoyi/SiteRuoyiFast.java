package com.fmger.http.site.ruoyi;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.htmlunit.HttpMethod;

import com.fmger.http.WebRequestVo;
import com.fmger.http.site.ruoyi.vo.RyAjaxResult;
import com.fmger.http.site.ruoyi.vo.RyTableDataInfo;
import com.fmger.utils.IConvert;
import com.google.gson.reflect.TypeToken;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.fmgr.domain.FmgreFinanceAccount;
import com.ruoyi.fmgr.domain.FmgreFinancePayment;
import com.ruoyi.fmgr.domain.FmgreFinancePaymentPayBo;
import com.ruoyi.fmgr.domain.FmgreHrEmployee;
import com.ruoyi.fmgr.domain.FmgreHrEmployeePunch;
import com.ruoyi.fmgr.domain.FmgreMaterial;
import com.ruoyi.fmgr.domain.FmgrePurchaseItem;
import com.ruoyi.fmgr.domain.FmgrePurchaseOrder;
import com.ruoyi.fmgr.domain.FmgrePurchaseOrderSubmitBo;
import com.ruoyi.fmgr.domain.FmgrePurchaseRequir;
import com.ruoyi.fmgr.domain.FmgreSupplier;
import com.ruoyi.fmgr.domain.FmgreSupplierBrand;
import com.ruoyi.fmgr.domain.FmgreSupplierQuote;
public class 	SiteRuoyiFast {

	private static SiteRuoyi _site = null;
	private static SiteRuoyi _get() {
		if(_site == null) {
			_site = new SiteRuoyi();
		}
		return _site;
	}
	private static <T> T commit(WebRequestVo rvo, Type type) {
		rvo.setUrl("/" + _get().getApi() + rvo.url);
		if(rvo.method.equals(HttpMethod.GET) && rvo.querys.get("pageSize") == null) {
			rvo.querys.put("pageSize", 10000);
		}
		//Ruoyi 的日期格式是 yyyy-MM-dd
		for(Map.Entry<String, Object> entry:rvo.querys.entrySet()) {
			if(entry.getValue() instanceof Date) {
				rvo.setQuery(entry.getKey(), new SimpleDateFormat("yyyy-MM-dd").format(entry.getValue()));
			}
			if(entry.getValue() instanceof Collection) {
				rvo.setQuery(entry.getKey(),null);
			}
		}
		return _get().doRequest(rvo, type);
	}
	
	public String getInfo() {
		WebRequestVo ro = new WebRequestVo("/getInfo", HttpMethod.GET).asJsonRequest();
		RyAjaxResult<String> r = commit(ro, new TypeToken<RyAjaxResult<String>>() {}.getType());
		return r.getData();	
	}
	
	public RyTableDataInfo<FmgreSupplier> listSuppliers(Map<String, Object> querys) {
		WebRequestVo ro = 
				new WebRequestVo(
						"/purchase/supplier/list", HttpMethod.GET)
				.asJsonRequest()
				.setQuerys(querys);
		return commit(ro, new TypeToken<RyTableDataInfo<FmgreSupplier>>() {}.getType());
	}
	
	public RyTableDataInfo<FmgreMaterial> listMaterials(Map<String, Object> querys) {
		WebRequestVo ro = 
				new WebRequestVo(
						"/material/material/list", HttpMethod.GET)
				.asJsonRequest()
				.setQuerys(querys);
		return commit(ro, new TypeToken<RyTableDataInfo<FmgreMaterial>>() {}.getType());
	}
	
	public List<SysDictData> listDictDatas(String dictType) {
		WebRequestVo ro = 
				new WebRequestVo(
						"/system/dict/data/type/" + dictType, HttpMethod.GET)
				.asJsonRequest();
		RyAjaxResult<List<SysDictData>> r =  commit(ro, new TypeToken<RyAjaxResult<List<SysDictData>>>() {}.getType());
		return r.data;
	}

	public RyTableDataInfo<FmgreSupplierBrand> listSupplierBrands(Map<String, Object> querys) {
		WebRequestVo ro = 
				new WebRequestVo(
						"/purchase/brand/list", HttpMethod.GET)
				.asJsonRequest()
				.setQuerys(querys);
		return commit(ro, new TypeToken<RyTableDataInfo<FmgreSupplierBrand>>() {}.getType());
	}
	
	public Integer insertSupplierBrand(FmgreSupplierBrand brand) {
		WebRequestVo ro = 
				new WebRequestVo(
						"/purchase/brand", HttpMethod.POST)
				.asJsonRequest()
				.setPayload(brand);
		RyAjaxResult<Integer> r = commit(ro, new TypeToken<RyAjaxResult<Integer>>() {}.getType());
		return r.getData();	
	}

	public  RyTableDataInfo<FmgrePurchaseRequir> listPurchaseRequirs(Map<String, Object> querys) {
		WebRequestVo ro = 
				new WebRequestVo(
						"/purchase/requir/list", HttpMethod.GET)
				.asJsonRequest()
				.setQuerys(querys);
		return commit(ro, new TypeToken<RyTableDataInfo<FmgrePurchaseRequir>>() {}.getType());
	}
	
	public RyTableDataInfo<FmgrePurchaseOrder> listPurchaseOrders(Map<String, Object> querys) {
		WebRequestVo ro = 
				new WebRequestVo(
						"/purchase/order/list", HttpMethod.GET)
				.asJsonRequest()
				.setQuerys(querys);
		return commit(ro, new TypeToken<RyTableDataInfo<FmgrePurchaseOrder>>() {}.getType());
	}
	
	public RyTableDataInfo<FmgrePurchaseItem> listPurchaseItems(Map<String, Object> querys) {
		WebRequestVo ro = 
				new WebRequestVo(
						"/purchase/item/list", HttpMethod.GET)
				.asJsonRequest()
				.setQuerys(querys);
		return commit(ro, new TypeToken<RyTableDataInfo<FmgrePurchaseItem>>() {}.getType());
	}

	public RyTableDataInfo<FmgreFinancePayment> listFinancePayments(Map<String, Object> querys) {
		WebRequestVo ro = 
				new WebRequestVo(
						"/finance/payment/list", HttpMethod.GET)
				.asJsonRequest()
				.setQuerys(querys);
		return commit(ro, new TypeToken<RyTableDataInfo<FmgreFinancePayment>>() {}.getType());
	}

	public static IConvert<Date> dateConv = new IConvert<Date>() {
		@Override
		public Date conv(String s) {
			DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
			try {
				return df.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	};

	public static IConvert<Date> dateConv2 = new IConvert<Date>() {
		@Override
		public Date conv(String s) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return df.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	};

	public static IConvert<Date> dateConv3 = new IConvert<Date>() {
		@Override
		public Date conv(String s) {
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			try {
				return df.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	};
	
	public static IConvert<BigDecimal> priceConv = new IConvert<BigDecimal>() {
		@Override
		public BigDecimal conv(String s) {
			if(s == null) return null;
			s = s.trim();
			if(s.startsWith("(") && s.endsWith(")")) {
				//负数的情况
				s = '-' + s.substring(1, s.length()-1);
			}
			return new BigDecimal(s.replace("¥", "").replaceAll(",", "").replaceAll("\"", "").trim());
		}
	};

	public Integer insertSupplierQuote(FmgreSupplierQuote quote) {
		WebRequestVo ro = 
				new WebRequestVo(
						"/purchase/quote", HttpMethod.POST)
				.asJsonRequest()
				.setPayload(quote);
		RyAjaxResult<Integer> r = commit(ro, new TypeToken<RyAjaxResult<Integer>>() {}.getType());
		return r.getData();	
	}

	public Integer insertPurchaseRequir(FmgrePurchaseRequir requir) {
		WebRequestVo ro = 
				new WebRequestVo(
						"/purchase/requir", HttpMethod.POST)
				.asJsonRequest()
				.setPayload(requir);
		RyAjaxResult<Integer> r = commit(ro, new TypeToken<RyAjaxResult<Integer>>() {}.getType());
		return r.getData();	
	}

	public Integer insertFinancePayment(FmgreFinancePayment payment) {
		WebRequestVo ro = 
				new WebRequestVo(
						"/finance/payment", HttpMethod.POST)
				.asJsonRequest()
				.setPayload(payment);
		RyAjaxResult<Integer> r = commit(ro, new TypeToken<RyAjaxResult<Integer>>() {}.getType());
		return r.getData();	
	}	
	
	public Integer insertHrEmployeePunch(FmgreHrEmployeePunch ep) {
		WebRequestVo ro = 
				new WebRequestVo(
						"/hr/punch", HttpMethod.POST)
				.asJsonRequest()
				.setPayload(ep);
		RyAjaxResult<Integer> r = commit(ro, new TypeToken<RyAjaxResult<Integer>>() {}.getType());
		return r.getData();	
	}

	public Integer submitPurchaseOrder(FmgrePurchaseOrderSubmitBo order) {
		WebRequestVo ro = 
				new WebRequestVo(
						"/purchase/order/submit", HttpMethod.POST)
				.asJsonRequest()
				.setPayload(order);
		RyAjaxResult<Integer> r = commit(ro, new TypeToken<RyAjaxResult<Integer>>() {}.getType());
		return r.getData();	
	}

	public Integer payFinancePayment(FmgreFinancePaymentPayBo payment) {
		WebRequestVo ro = 
				new WebRequestVo(
						"/finance/payment/pay", HttpMethod.POST)
				.asJsonRequest()
				.setPayload(payment);
		RyAjaxResult<Integer> r = commit(ro, new TypeToken<RyAjaxResult<Integer>>() {}.getType());
		return r.getData();	
	}

	public Integer insertPurchaseItem(FmgrePurchaseItem item) {
		WebRequestVo ro = 
				new WebRequestVo(
						"/purchase/item", HttpMethod.POST)
				.asJsonRequest()
				.setPayload(item);
		RyAjaxResult<Integer> r = commit(ro, new TypeToken<RyAjaxResult<Integer>>() {}.getType());
		return r.getData();	
	}

	public RyTableDataInfo<FmgreSupplierQuote> listSupplierQuotas(Map<String, Object> querys) {
		WebRequestVo ro = 
				new WebRequestVo(
						"/purchase/quote/list", HttpMethod.GET)
				.asJsonRequest()
				.setQuerys(querys);
		return commit(ro, new TypeToken<RyTableDataInfo<FmgreSupplierQuote>>() {}.getType());
	}

	public List<FmgreSupplierQuote> listSupplierQuotas(List<FmgreSupplierQuote> quotes) {
		WebRequestVo ro = 
				new WebRequestVo(
						"/purchase/quote/listByMSB", HttpMethod.POST)
				.asJsonRequest()
				.setPayload(quotes);
		RyAjaxResult<List<FmgreSupplierQuote>> r = commit(ro, new TypeToken<RyAjaxResult<List<FmgreSupplierQuote>>>() {}.getType());
		return r.getData();
	}

	public RyTableDataInfo<FmgreFinanceAccount> listAccounts(Map<String, Object> querys) {
		WebRequestVo ro = 
				new WebRequestVo(
						"/finance/account/list", HttpMethod.GET)
				.asJsonRequest()
				.setQuerys(querys);
		return commit(ro, new TypeToken<RyTableDataInfo<FmgreFinanceAccount>>() {}.getType());
	}
	
	public RyTableDataInfo<FmgreFinancePayment> listPayments(Map<String, Object> querys) {
		WebRequestVo ro = 
				new WebRequestVo(
						"/finance/payment/list", HttpMethod.GET)
				.asJsonRequest()
				.setQuerys(querys);
		return commit(ro, new TypeToken<RyTableDataInfo<FmgreFinancePayment>>() {}.getType());
	}

	public Integer updatePaymentComment(FmgreFinancePayment payment) {
		WebRequestVo ro = 
				new WebRequestVo(
						"/finance/payment", HttpMethod.PUT)
				.asJsonRequest()
				.setPayload(payment);
		RyAjaxResult<Integer> r = commit(ro, new TypeToken<RyAjaxResult<Integer>>() {}.getType());
		return r.getData();
	}
	
	public Integer refreshPayment(Long paymentId) {
		WebRequestVo ro = 
				new WebRequestVo(
						"/finance/payment/refresh/" + paymentId, HttpMethod.GET)
				.asJsonRequest();
		RyAjaxResult<Integer> r = commit(ro, new TypeToken<RyAjaxResult<Integer>>() {}.getType());
		return r.getData();
	}

	public RyTableDataInfo<FmgreHrEmployee> listEmployees(Map<String, Object> querys) {
		WebRequestVo ro = 
				new WebRequestVo(
						"/hr/employee/list", HttpMethod.GET)
				.asJsonRequest()
				.setQuerys(querys);
		return commit(ro, new TypeToken<RyTableDataInfo<FmgreHrEmployee>>() {}.getType());
	}
	
}
