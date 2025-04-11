package com.fmger.http.site.ruoyi.commiter;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fmger.http.site.ruoyi.vo.fmgre.NoodleList;
import com.fmger.utils.CSVImport;
import com.fmger.utils.IConvert;
import com.ruoyi.fmgr.domain.FmgreHrEmployee;
import com.ruoyi.fmgr.domain.FmgreMaterial;
import com.ruoyi.fmgr.domain.FmgrePurchaseItem;
import com.ruoyi.fmgr.domain.FmgrePurchaseItemSubmitBo;
import com.ruoyi.fmgr.domain.FmgrePurchaseOrderSubmitBo;
import com.ruoyi.fmgr.domain.FmgrePurchaseRequir;
import com.ruoyi.fmgr.domain.FmgreSupplier;
import com.ruoyi.fmgr.domain.FmgreSupplierQuote;

public class NoodleCommiter extends SiteRuoyiCommiter {

	protected Map<String, FmgreHrEmployee> employees = null;
	
	private Integer punchYear = 2025;
	
	public NoodleCommiter setYear(Integer year) {
		this.punchYear = year;
		return this;
	}
	
	
	@Override
	public void commit(String file) {
		List<NoodleList> noodleList = new CSVImport().importCsv(new File(file), NoodleList.class, new String[]{
				"date","num"
			}, new IConvert[]{
					CSVImport.localDate2,
				null
		}, 1);
		this.refreshSuppliers();
		this.refreshMaterials();
		this.refreshSupplierQuotas();
		FmgreSupplier supplier = this.suppliers.values().stream().filter(sup -> sup.getSupplierCode().equals("GY024")).findFirst().orElse(null);
		FmgreMaterial material = this.materials.values().stream().filter(mat -> mat.getMaterailCode().equals("40001")).findFirst().orElse(null);
		FmgreSupplierQuote quote = this.supplierQuotas.values().stream()
				.filter(quo -> quo.get(0).getMaterailId().equals(material.getMaterailId()) && quo.get(0).getSupplierId().equals(supplier.getSupplierId())).findFirst().orElse(null).get(0);
		
		System.out.println(supplier);
		System.out.println(material);
		System.out.println(quote);
				
		LinkedList<FmgrePurchaseRequir> requirs = new LinkedList<>();
		for(NoodleList noodle:noodleList) {
			noodle.date.plusYears(this.punchYear - noodle.date.getYear());
			Date dt = Date.from(noodle.date.atTime(0, 0, 0).minusHours(8).toInstant(ZoneOffset.ofHours(0)));
			String comment = noodle.date.format(DateTimeFormatter.ofPattern("yyyy年M月d日")) + "面";
			if(this.queryRequir(comment) != null) {
				continue;
			}
			FmgrePurchaseRequir requir = new FmgrePurchaseRequir();
			requir.setDeptId(201L);
			requir.setRequirUserId(100L);
			requir.setRequirTime(dt);
			requir.setRequirItems(new ArrayList<FmgrePurchaseItem>());
			FmgrePurchaseItem item = new FmgrePurchaseItem();
			item.setMaterailId(material.getMaterailId());
			item.setRequirNum(noodle.num);
			item.setRequirUnitDictid("JIN");
			item.setOrderUnitDictid("JIN");
			item.setOrderNum(noodle.num);
			item.setOrderAmount(noodle.num.multiply(quote.getPackSize()));
			item.setQuotaId(quote.getQuoteId());
			item.setSupplierId(supplier.getSupplierId());
			requir.getRequirItems().add(item);
			requir.setRequirComment(comment);
			requir = this.addRequir(requir);
			
			item = this.getItems(requir.getRequirId()).get(0);
			List<FmgrePurchaseItemSubmitBo> itemBos =  new ArrayList<FmgrePurchaseItemSubmitBo>();
			FmgrePurchaseItemSubmitBo itemBo = new FmgrePurchaseItemSubmitBo();
			itemBo.setMaterailId(material.getMaterailId());
			itemBo.setSupplierId(supplier.getSupplierId());
			itemBo.setItemId(item.getItemId());
			itemBo.setOrderUnitDictid("JIN");
			itemBo.setOrderNum(noodle.num);
			itemBo.setOrderAmount(noodle.num.multiply(quote.getPackSize()));
			itemBo.setQuotaId(quote.getQuoteId());
			itemBo.setQuotePrice(quote.getQuotaPrice());
			itemBos.add(itemBo);
			FmgrePurchaseOrderSubmitBo order = new FmgrePurchaseOrderSubmitBo();
			order.setUserId(100L);
			order.setSupplierId(supplier.getSupplierId());
			order.setDeliveryDictid("RI");
			order.setOrderTime(dt);
			order.setOrderComment(noodle.date.format(DateTimeFormatter.ofPattern("yyyy年M月d日")) + "面");
			order.setItems(itemBos);
			sf.submitPurchaseOrder(order);
			try {
				Thread.sleep(50L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected List<FmgrePurchaseItem> getItems(Long requirId) {
		return sf.listPurchaseItems(new HashMap<String, Object>(){{
			put("requirId", requirId);
		}}).rows;
	}
	
	protected FmgrePurchaseRequir queryRequir(String comment) {
		List<FmgrePurchaseRequir> exists = sf.listPurchaseRequirs(new HashMap<String, Object>(){{
			put("requirComment", comment);
		}}).rows;
		if(exists.size()  == 0) {
			return null;
		}
		return exists.get(0);
	}
	
	protected FmgrePurchaseRequir addRequir(FmgrePurchaseRequir requir) {
		sf.insertPurchaseRequir(requir);
		return this.queryRequir(requir.getRequirComment());
	}
		

}
