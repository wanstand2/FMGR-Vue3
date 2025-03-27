package com.fmger.http.site.ruoyi.vo.fmgre;

import java.math.BigDecimal;
import java.util.Date;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.fmgr.domain.FmgreSupplierQuote;
import com.ruoyi.fmgr.domain.FmgreFinancePayment;
import com.ruoyi.fmgr.domain.FmgrePurchaseRequir;
import com.ruoyi.fmgr.domain.FmgrePurchaseOrderSubmitBo;
import com.ruoyi.fmgr.domain.FmgrePurchaseItemSubmitBo;
import com.ruoyi.fmgr.domain.FmgreFinancePaymentPayBo;
public class PurchaseList extends FmgrePurchaseItemSubmitBo {

    public transient FmgrePurchaseRequir requir;

    public transient FmgrePurchaseOrderSubmitBo order;
    /*
     * 当场结算的payment
     */
    public transient FmgreFinancePayment payment;
    /**
     * 报销的payment
     */
    public transient FmgreFinancePayment paymentReimbursement;
    /**
     * 合并支付
     */
    public transient FmgreFinancePaymentPayBo paymentMergePay;

    public transient FmgreSupplierQuote quota;


    public String purchaseCode;
    public Date happenTime;
    public String materaiCode;
    public String materaiName;
    public String supplierCode;
    public String supplierName;
    public BigDecimal happenUnit;
    public BigDecimal happenNum;
    public BigDecimal happenSize;
    public BigDecimal happenTotalSize;
    
    public String payAccountString;
    /** 采购总价 */
    @Excel(name = "采购总价")
    public BigDecimal purchaseTotalPrice;
    public String comment;
    public String paymentCode;
    
	@Override
	public String toString() {
		return "PurchaseList purchaseCode=" + purchaseCode + ", happenTime="
				+ happenTime + ", materaiCode=" + materaiCode + ", materaiName=" + materaiName + ", supplierCode="
				+ supplierCode + ", supplierName=" + supplierName + ", happenUnit=" + happenUnit + ", happenNum="
				+ happenNum + ", happenSize=" + happenSize + ", happenTotalSize=" + happenTotalSize
				+ ", itemTotalPrice=" + getItemTotalPrice() + ", purchaseTotalPrice=" + purchaseTotalPrice + ", comment="
				+ comment + ", paymentCode=" + paymentCode + "|" +
                ", materailId=" + getMaterailId() +
                ", supplierId=" + getSupplierId() +
                ", quota=" + quota +
                ", payment=" + payment +
                ", paymentReimbursement=" + paymentReimbursement +
                ", requir=" + requir +
                ", order=" + order +
                "]";
	}
    
    
}
