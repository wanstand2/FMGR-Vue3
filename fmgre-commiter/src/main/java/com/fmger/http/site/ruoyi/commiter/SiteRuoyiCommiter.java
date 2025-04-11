package com.fmger.http.site.ruoyi.commiter;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fmger.http.site.ruoyi.SiteRuoyiFast;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.fmgr.domain.FmgreFinanceAccount;
import com.ruoyi.fmgr.domain.FmgreMaterial;
import com.ruoyi.fmgr.domain.FmgrePurchaseRequir;
import com.ruoyi.fmgr.domain.FmgreSupplier;
import com.ruoyi.fmgr.domain.FmgreSupplierBrand;
import com.ruoyi.fmgr.domain.FmgreSupplierQuote;

public abstract class SiteRuoyiCommiter {

	
	protected SiteRuoyiFast sf = new SiteRuoyiFast();
	
	public abstract void commit(String file);
	
	protected Map<String, FmgreSupplier> suppliers = null;
	protected Map<String, FmgreMaterial> materials = null;
	protected Map<String, FmgreSupplierBrand> supplierBrands = null;
	protected Map<String, SysDictData> packDictDatas = null;
	protected Map<String, SysDictData> unitDictDatas = null;
	protected Map<String, FmgreFinanceAccount> accounts = null;
	protected Map<Long, List<FmgreSupplierQuote>> supplierQuotas = null;
	
	protected void refreshSuppliers() {
		this.suppliers = sf.listSuppliers(new HashMap<String, Object>(){{
			put("pageNum", 1);
			put("pageSize", 10000);
		}}).rows.stream().collect(Collectors.toMap(FmgreSupplier::getSupplierCode, Function.identity()));
	}
	
	protected void refreshMaterials() {
		this.materials = sf.listMaterials(new HashMap<String, Object>(){{
			put("pageNum", 1);
			put("pageSize", 10000);
		}}).rows.stream().collect(Collectors.toMap(FmgreMaterial::getMaterailCode, Function.identity()));
	}

	protected void refreshSupplierBrands() {
		this.supplierBrands = sf.listSupplierBrands(new HashMap<String, Object>(){{
			put("pageNum", 1);
			put("pageSize", 10000);
		}}).rows.stream().collect(Collectors.toMap(FmgreSupplierBrand::getBrandName, Function.identity()));
	}

	protected void refreshPackDictDatas() {
		this.packDictDatas = sf.listDictDatas("fmgre_pack").stream().collect(
			Collectors.toMap(SysDictData::getDictLabel, Function.identity()));
	}

	protected void refreshUnitDictDatas() {
		this.unitDictDatas = sf.listDictDatas("fmgre_unit").stream().collect(
			Collectors.toMap(SysDictData::getDictLabel, Function.identity()));
	}

	protected void refreshSupplierQuotas() {
		this.supplierQuotas = sf.listSupplierQuotas(new HashMap<String, Object>(){{
			put("pageNum", 1);
			put("pageSize", 100000);
		}}).rows.stream().collect(Collectors.groupingBy(
			q -> q.getSupplierId()*10000+q.getMaterailId()
		));
		for(Map.Entry<Long, List<FmgreSupplierQuote>> entry:this.supplierQuotas.entrySet()) {
			Collections.sort(entry.getValue(), (a, b) -> b.getQuotaTime().compareTo(a.getQuotaTime()));
		}
	}

	protected void refreshAccounts() {
		this.accounts = sf.listAccounts(new HashMap<String, Object>(){{
			put("pageNum", 1);
			put("pageSize", 10000);
		}}).rows.stream().collect(Collectors.toMap(FmgreFinanceAccount::getAccountAlias, Function.identity()));
	}

	protected FmgreSupplierBrand addOrQueryBrand(FmgreSupplierBrand brand) {
		FmgreSupplierBrand exist = supplierBrands.get(brand.getBrandName());
		if(exist != null) return exist;
		sf.insertSupplierBrand(brand);
		this.refreshSupplierBrands();
		return supplierBrands.get(brand.getBrandName());
	}
	
	protected FmgreSupplierBrand searchBrand(String name) {
		return sf.listSupplierBrands(new HashMap<String, Object>() {{
			put("brandName", name);
		}}).rows.stream().findFirst().orElse(null);
	}
	
	protected FmgreMaterial searchMaterail(String name) {
		List<FmgreMaterial> ms = sf.listMaterials(new HashMap<String, Object>() {{
			put("materailName", name);
		}}).rows;
		for(FmgreMaterial m:ms) {
			if(name.equals(m.getMaterailName())) {
				return m;
			}
		}
		if(ms.size() > 1) {
			System.out.println("NAME=" + name);
			for(FmgreMaterial m:ms) {
				System.out.println(m);
			}
			throw new RuntimeException("搜索出超过一个对象！");
		}
		return ms.stream().findFirst().orElse(null);
	}
}
