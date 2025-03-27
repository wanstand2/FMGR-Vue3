package com.fmger.http.site.ruoyi.commiter;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import com.fmger.http.site.ruoyi.SiteRuoyiFast;
import com.fmger.http.site.ruoyi.vo.fmgre.SupplierQuote;
import com.fmger.utils.CSVImport;
import com.fmger.utils.IConvert;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.fmgr.domain.FmgreMaterial;
import com.ruoyi.fmgr.domain.FmgreSupplier;
import com.ruoyi.fmgr.domain.FmgreSupplierBrand;

public class QuotaCommiter extends SiteRuoyiCommiter {

	public void commit(String filename) {
		List<SupplierQuote> quotas = new CSVImport().importCsv(new File(filename), SupplierQuote.class, new String[]{
				"materaiCode",
				"materaiName",
				"supplierCode",
				"supplierName",
				"quoteUnit",
				"packSize",
				"brandName",
				"quotaTime",			
				"quotaPrice",
				"salesPrice",
				"ratio",
				"quotaComment",
			}, new IConvert[]{
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				SiteRuoyiFast.dateConv,
				SiteRuoyiFast.priceConv,
				SiteRuoyiFast.priceConv,
				null,
				null
			});
		this.refreshSuppliers();
		this.refreshMaterials();
		this.refreshSupplierBrands();
		this.refreshPackDictDatas();
		for(SupplierQuote quota:quotas) {
			FmgreMaterial material = this.materials.get(quota.materaiCode);
			if(material == null) {
				System.out.println(quota);
				throw new RuntimeException("物料不存在: " + quota.materaiCode);
			}
			FmgreSupplier supplier = this.suppliers.get(quota.supplierCode);
			if(supplier == null) {
				System.out.println(quota);
				throw new RuntimeException("供应商不存在: " + quota.supplierCode);
			}
			quota.setSupplierId(supplier.getSupplierId());
			quota.setMaterailId(material.getMaterailId());
			FmgreSupplierBrand supplierBrand = null;
			if(quota.brandName != null && quota.brandName.length() > 0) {
				FmgreSupplierBrand brand = new FmgreSupplierBrand();
				brand.setBrandName(quota.brandName);
				brand.setBrandDetail("");
				supplierBrand = addOrQueryBrand(brand);
			}
			SysDictData pack = this.packDictDatas.get(quota.quoteUnit);
			if(pack == null) {
				throw new RuntimeException("包装单位不存在: " + quota.quoteUnit);
			}
			quota.setPackUnitDictid(pack.getDictValue());
			if(quota.getPackSize() == null || quota.getPackSize().compareTo(BigDecimal.ZERO) == 0) {
				System.out.println(quota);
				throw new RuntimeException("包装大小不合法: " + quota.getPackSize());
			}
		}
		for(SupplierQuote quota:quotas) {
			sf.insertSupplierQuote(quota);
		}
	}
}
