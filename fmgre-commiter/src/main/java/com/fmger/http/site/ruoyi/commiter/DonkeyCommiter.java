package com.fmger.http.site.ruoyi.commiter;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fmger.http.site.ruoyi.SiteRuoyiFast;
import com.fmger.http.site.ruoyi.vo.fmgre.DonkeyItem;
import com.fmger.http.site.ruoyi.vo.fmgre.MapItem;
import com.fmger.utils.CSVImport;
import com.fmger.utils.IConvert;
import com.fmger.utils.ReflectUtils;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.fmgr.domain.FmgreMaterial;
import com.ruoyi.fmgr.domain.FmgrePurchaseItem;
import com.ruoyi.fmgr.domain.FmgrePurchaseItemSubmitBo;
import com.ruoyi.fmgr.domain.FmgrePurchaseOrder;
import com.ruoyi.fmgr.domain.FmgrePurchaseOrderSubmitBo;
import com.ruoyi.fmgr.domain.FmgrePurchaseRequir;
import com.ruoyi.fmgr.domain.FmgreSupplier;
import com.ruoyi.fmgr.domain.FmgreSupplierBrand;
import com.ruoyi.fmgr.domain.FmgreSupplierQuote;

public class DonkeyCommiter extends SiteRuoyiCommiter {

	private String mapfile;
	public DonkeyCommiter setMap(String map) {
		this.mapfile = map;
		return this;
	}
	
	public void commit(String filename) {
		List<MapItem> mapItems = new CSVImport().importCsv(new File(mapfile), MapItem.class, new String[] {
					"key",
					"value"
			}, new IConvert[]{
					null,
					null},1 );
		Map<String, String> materialMapping = new HashMap<>();
		for(MapItem item:mapItems) {
			materialMapping.put(item.key, item.value);
		}
		List<DonkeyItem> purchases = new CSVImport().importCsv(new File(filename), DonkeyItem.class, new String[]{
			"store",
			"storeOrder",
			"requirTime",
			"payTime",
			"code1",
			"code2",
			"sheetType",
			"orderType",
			"dunk",
			"classify",
			"brandName",
			"itemName",
			"specString",
			"itemPrice",
			"Num",
			"itemDiscountPrice",
			"itemTotalPrice",
			"itemBanlancePrice",
			"itemFinalTotalPrice",
		},
		new IConvert[]{
			null,
			null,
			SiteRuoyiFast.dateConv3,
			SiteRuoyiFast.dateConv3,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
		}, 1);
		
		this.refreshSupplierBrands();
		this.refreshUnitDictDatas();
		this.refreshPackDictDatas();
		this.refreshAccounts();
		
		FmgreSupplier supplier = sf.listSuppliers(new HashMap<String, Object>(){{
			put("supplierCode", "GY001");
		}}).rows.stream().findFirst().orElse(null);
		
		List<FmgreSupplierQuote> quotes = sf.listSupplierQuotas(new HashMap(){{
			put("supplierId", supplier.getSupplierId());
		}}).rows;
		
		for(DonkeyItem item:purchases) {
			if(brandMapping.containsKey(item.brandName)) {
				item.brandName = brandMapping.get(item.brandName);
			}
			item.brand = supplierBrands.get(item.brandName);
			if(item.brand == null && !"无".equals(item.brandName)) {
				FmgreSupplierBrand brand = new FmgreSupplierBrand();
				brand.setBrandName(item.brandName);
				item.brand = this.addOrQueryBrand(brand);
				supplierBrands.put(item.brandName, item.brand);
			}
			String searchName = item.itemName; 
			if(materialMapping.containsKey(item.itemName)) {
				searchName = materialMapping.get(searchName);
			}
			item.material = this.searchMaterail(searchName);
			if(item.material == null) {
				throw new RuntimeException("搜索不出material对象！KEY="+searchName);
			}

			item.spec = new  DonkeyItem.Spec();
			item.spec.material = item.material;
			this.parseSpec(item.spec, item.specString);
			
			FmgreSupplierQuote quote = quotes.stream().filter(q -> 
				q.getPackUnitDictid().equals(item.spec.packUnitDictid) 
				&& q.getPackSize().equals(item.spec.packSize) 
				&& (item.spec.packNum == null ? true : true && ((Long)item.spec.packNum.longValue()).equals(q.getSubPackNum()))
				&& (item.spec.subPackUnitDictid == null ? true : true && item.spec.subPackUnitDictid.equals(q.getSubPackUnitDictid()))
				).findFirst().orElse(null);
			if(quote == null) {
				quote = new FmgreSupplierQuote();
				quote.setMaterailId(item.material.getMaterailId());
				quote.setSupplierId(supplier.getSupplierId());
				quote.setPackUnitDictid(item.spec.packUnitDictid);
				quote.setPackSize(item.spec.packSize);
				quote.setSubPackNum(item.spec.packNum.longValue());
				quote.setSubPackSize(item.spec.subPackSize);
				quote.setQuotaTime(item.requirTime);
				quote.setQuotaPrice(item.itemFinalTotalPrice.divide(item.Num));
				sf.insertSupplierQuote(quote);
				quote = sf.listSupplierQuotas(ReflectUtils.getFields(quote)).rows.stream().findFirst().orElse(null);
			}
			if(quote == null) {
				throw new RuntimeException("quote bug! " + quote);
			}
			item.quote = quote;
			System.out.println(quote);
		}
		
		if(false)
		throw new RuntimeException("暂停！");
		
		
		Map<String, List<DonkeyItem>> orderMap = purchases.stream().collect(Collectors.groupingBy(DonkeyItem::getCode1));
		List<String> ordersSort = orderMap.keySet().stream().sorted().collect(Collectors.toList());
		for(String orderKey:ordersSort) {
			List<DonkeyItem> donkeyItems = orderMap.get(orderKey);
			FmgrePurchaseRequir existRequir = (FmgrePurchaseRequir) sf.listPurchaseRequirs(new HashMap() {{
				put("requirComment", orderKey);
			}}).rows.stream().findFirst().orElse(null);
			if(existRequir == null) {
				FmgrePurchaseRequir requir = new FmgrePurchaseRequir();
				requir.setDeptId(201L);
				requir.setRequirTime(donkeyItems.get(0).requirTime);
				requir.setRequirComment(orderKey);
				requir.setRequirUserId(100L);
				List<FmgrePurchaseItem> _items = new ArrayList<>();
				for(DonkeyItem item:donkeyItems) {
					FmgrePurchaseItem _item = new FmgrePurchaseItem();
					_item.setMaterailId(item.material.getMaterailId());
					_item.setSupplierId(supplier.getSupplierId());
					_item.setRequirNum(item.Num);
					_item.setRequirUnitDictid(item.spec.packUnitDictid);
					if(!item.material.getMaterailName().equals(item.itemName)) {
						_item.setItemComment(item.itemName);
					}
					_items.add(_item);
				}
				requir.setRequirItems(_items);
				sf.insertPurchaseRequir(requir);
				existRequir = (FmgrePurchaseRequir) sf.listPurchaseRequirs(new HashMap() {{
					put("requirComment", orderKey);
				}}).rows.stream().findFirst().orElse(null);
			}
			Long requirId = existRequir.getRequirId();
			existRequir.setRequirItems(sf.listPurchaseItems(new HashMap(){{
				put("requirId", requirId);
			}}).rows);

			for(DonkeyItem item:donkeyItems) {
				FmgrePurchaseItem _item = existRequir.getRequirItems().stream().filter(i -> 
				item.material.getMaterailId().equals(i.getMaterailId())
				&& item.spec.packUnitDictid.equals(i.getRequirUnitDictid())
				&& item.Num.compareTo(i.getRequirNum()) == 0).findFirst().orElse(null);
				if(_item == null) {
					throw new RuntimeException("采购需求项目不存在！");
				}
				System.out.println(_item);
				item.itemId = _item.getItemId();
				existRequir.getRequirItems().remove(_item);
			}
			
			FmgrePurchaseOrder existOrder = (FmgrePurchaseOrder) sf.listPurchaseOrders(new HashMap() {{
				put("orderComment", orderKey);
			}}).rows.stream().findFirst().orElse(null);
			if(existOrder == null) {
				FmgrePurchaseOrderSubmitBo order = new FmgrePurchaseOrderSubmitBo();
				order.setOrderComment(orderKey);
				order.setOrderTime(donkeyItems.get(0).requirTime);
				order.setSupplierId(supplier.getSupplierId());
				order.setAccountId(this.accounts.get("汤").getAccountId());
				order.setDeliveryDictid("RI");
				order.setItems(new ArrayList<>());
				for(DonkeyItem item:donkeyItems) {
					FmgrePurchaseItemSubmitBo bo = new FmgrePurchaseItemSubmitBo();
					bo.setItemId(item.itemId);
					bo.setSupplierId(supplier.getSupplierId());
					bo.setMaterailId(item.material.getMaterailId());
					bo.setQuotaId(item.quote.getQuoteId());
					bo.setQuotePrice(item.quote.getQuotaPrice());
					bo.setOrderNum(item.Num);
					order.getItems().add(bo);
				}
				sf.submitPurchaseOrder(order);
			}
			try {
				Thread.sleep(50L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private String[] numUnit(String s) {
		if(s == null || s.length() == 0) return null;
		String r[] = new String[] { new String(), new String()};
		s = s.trim();
		for(int i=0;i<s.length();i++) {
			char c = s.charAt(i);
			if(c == '.' || (c >= '0' && c<= '9')) {
				r[0] = r[0]+c;
				continue;
			}
			r[1] += s.substring(i);
			break;
		}
		return r;
	}
	
	private static class Unit {
		BigDecimal amount;
		String unit;
		public Unit() {};
		public Unit(BigDecimal amount, String unit) {
			this.unit = unit;
			this.amount = amount;
		}
	}
	private DonkeyItem.Spec parseSpec(DonkeyItem.Spec spec, String specString) {
		 String[] ss1 = specString.split("/");
		 String[] ss2 = null;
		 if(ss1.length > 1 && ss1[1].contains("(")) {
			 ss2 = ss1[1].split("\\(");
		 }
		 String[] mainSpec = numUnit(ss1[0]);
		 String mainUnit = ss2 == null ?  (ss1.length > 1 ? ss1[1].trim() : null) : ss2[0];
		 String[] subSpec = numUnit(ss2 != null && ss2.length > 1 ? ss2[1].replace(")", "") : null);
		 
		 System.out.println(mainSpec[0] + ',' + mainSpec[1] + "|" + mainUnit + " " + (subSpec == null ? "" : subSpec[0] + ',' + subSpec[1]));
		 Unit unit = this.findUnit(mainSpec[1], spec.material);
		 spec.packSize = new BigDecimal(mainSpec[0]).multiply(unit.amount);
		 if(mainUnit == null) {
			 spec.packUnitDictid = unit.unit;
		 } else {
			 spec.packUnitDictid = this.packDictDatas.get(mainUnit).getDictValue();
		 }
		 if(!spec.material.getUnitDictid().equals(unit.unit)) {
			 Unit conv = this.unitConv.get(unit.unit + "-" + spec.material.getUnitDictid());
			 if(conv == null) {
				if(unit.unit.equals("g") &&  (spec.packUnitDictid.equals("SHUANG") || spec.packUnitDictid.equals("BA"))) {
					//手套等克重
					spec.comment = spec.packSize.toString() + unit.unit;
					spec.packSize = BigDecimal.ONE;
				} else {
					throw new RuntimeException("不一致的基本单位："+spec.material.getMaterailName() + " " + unit.unit + " vs " + spec.material.getUnitDictid());
				}
			 } else {
				 spec.packSize = spec.packSize.multiply(conv.amount);
			 }
		 }
		 if(subSpec == null) {
			 spec.packNum = BigDecimal.ONE;
		 } else {
			 spec.packNum = new BigDecimal(subSpec[0]);
			 spec.subPackSize = spec.packSize.divide(spec.packNum, RoundingMode.HALF_DOWN);
			 String subpack = this.packMapping.get(subSpec[1]);
			 if(subpack == null) {
				 subpack = subSpec[1];
			 }
			 spec.subPackUnitDictid = this.packDictDatas.get(subpack).getDictValue();
		 }
		 System.out.println(spec);
		 return spec;
	}
	
	private Unit findUnit(String unitString, FmgreMaterial material) {
		SysDictData dict = this.unitDictDatas.get(unitString);
		if(dict != null) {
			return new Unit(BigDecimal.ONE, dict.getDictValue());
		}
		Unit unit = unitMapping.get(unitString);
		if(unit == null) {
			if(material.getMaterailCode().equals("80006") && "包".equals(unitString)) {//一次性筷子
				return new Unit(new BigDecimal("1"), "SHUANG");
			}
			throw new RuntimeException("无法转换的单位：" + unitString);
		}
		return unit;
	}
	
	private Map<String, String> brandMapping = new HashMap<String, String>() {{
		put("红九九", "红99");
	}};
	private Map<String, Unit> unitMapping = new HashMap<String, Unit>() {{
		put("斤", new Unit(new BigDecimal("500"), "g"));
		put("公斤", new Unit(new BigDecimal("1"), "kg"));
//		put("包", new Unit(new BigDecimal("1"), "SHUANG"));
		put("片", new Unit(new BigDecimal("1"), "GE"));
		put("ml", new Unit(new BigDecimal("1"), "mL"));
		put("支", new Unit(new BigDecimal("1"), "JUAN"));
		put("板", new Unit(new BigDecimal("1"), "ZHI"));
	}};
	private Map<String, String> packMapping = new HashMap<String, String>() {{
		put("枚", "个");
	}};
	private Map<String, Unit> unitConv = new HashMap<String, Unit>() {{
		put("kg-g", new Unit(new BigDecimal("1000"), "g"));
		put("kg-mL", new Unit(new BigDecimal("1000"), "mL"));
		put("L-mL", new Unit(new BigDecimal("1000"), "mL"));
		put("L-g", new Unit(new BigDecimal("1000"), "g"));
		put("GE-ZHI", new Unit(BigDecimal.ONE, "ZHI"));
		put("TAO-ZHI", new Unit(BigDecimal.ONE, "ZHI"));
		put("mL-g", new Unit(BigDecimal.ONE, "g"));
		put("ZHI-SHUANG", new Unit(new BigDecimal("0.5"), "SHUANG"));
		put("GE-SHUANG", new Unit(new BigDecimal("0.5"), "SHUANG"));
		put("ZHI-GEN", new Unit(BigDecimal.ONE, "GEN"));
		put("BA-SHUANG", new Unit(new BigDecimal("1"), "SHUANG"));
	}};
}
