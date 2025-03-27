package com.fmger.http.site.ruoyi.vo.fmgre;

import java.math.BigDecimal;
import java.util.Date;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.fmgr.domain.FmgreMaterial;
import com.ruoyi.fmgr.domain.FmgreSupplierBrand;
import com.ruoyi.fmgr.domain.FmgreSupplierQuote;

public class DonkeyItem {

    public String store;
    public String storeOrder;
    public Date requirTime;
    public Date payTime;
    public String code1;
    public String code2;
    public String sheetType;
    public String orderType;
    public String dunk;
    public String classify;
    public String brandName;
    public String itemName;
    public String specString;
    public BigDecimal itemPrice;
    public BigDecimal Num;
    public BigDecimal itemDiscountPrice;
    public BigDecimal itemTotalPrice;
    public BigDecimal itemBanlancePrice;
    public BigDecimal itemFinalTotalPrice;

    public Long itemId;

    public FmgreSupplierBrand brand;

    public FmgreSupplierQuote quote;

    public FmgreMaterial material;

    public Spec spec;

    public String getCode1() {
        return this.code1;
    }
    
    public String toString() {
        return "DonkeyItem [store=" + store + ", storeOrder=" + storeOrder + ", requirTime=" + requirTime + ", payTime="
                + payTime + ", code1=" + code1 + ", code2=" + code2 + ", sheetType=" + sheetType + ", orderType=" + orderType + ", dunk=" + dunk
                + ", classify=" + classify + ", brandName=" + brandName + ", itemName=" + itemName + ", spec=" + spec
                + ", itemPrice=" + itemPrice + ", Num=" + Num + ", itemDiscountPrice=" + itemDiscountPrice
                + ", itemTotalPrice=" + itemTotalPrice + ", itemBanlancePrice=" + itemBanlancePrice
                + ", itemFinalTotalPrice=" + itemFinalTotalPrice + "]";
    }
    
    public static class Spec {
    	
    	public String comment;
    	
        /** 包装单位（字典) */
    	public String packUnitDictid;

        /** 包装数量(子包装数) */
    	public BigDecimal packNum;

        /** 包装大小(包装总和) */
        @Excel(name = "包装大小(包装总和)")
        public BigDecimal packSize;

        /** 子包装单位（字典) */
        @Excel(name = "子包装单位", readConverterExp = "子包装单位（字典)")
        public String subPackUnitDictid;

        /** 子包装数量(子包装个数) */
        @Excel(name = "子包装数量(子包装个数)")
        public Long subPackNum;
        
        /** 子包装大小(子包装总和) */
        @Excel(name = "子包装大小(子包装总和)")
        public BigDecimal subPackSize;
        
        public FmgreMaterial material;

		@Override
		public String toString() {
			if(subPackUnitDictid == null) {
				return "Spec [" +  packSize + material.getUnitDictid() + "/" + packUnitDictid + "::" + comment +"]";
			} else {
				return "Spec [" +  packNum + "*(" + subPackSize + material.getUnitDictid() + "/" + subPackUnitDictid + ")=" + packSize + "/" + packUnitDictid + "::" + comment + "]";
			}
		}
        
        
    }
}
