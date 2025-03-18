package com.ruoyi.fmgr.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 供应商对象 fmgre_supplier
 * 
 * @author terence
 * @date 2025-02-24
 */
public class FmgreSupplier extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 供应商id */
    private Long supplierId;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String supplierName;

    /** 供应商编码 */
    @Excel(name = "供应商编码")
    private String supplierCode;

    /** 供应商描述 */
    @Excel(name = "供应商描述")
    private String supplierDetail;

    /** 供应商地址 */
    @Excel(name = "供应商地址")
    private String address;

    /** 联系人姓名 */
    @Excel(name = "联系人姓名")
    private String contactName;

    /** 联系人电话 */
    @Excel(name = "联系人电话")
    private String contactPhone;

    private  List<FmgreSupplierFinanceBo> financeBos;

    public void setSupplierId(Long supplierId) 
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId() 
    {
        return supplierId;
    }
    public void setSupplierName(String supplierName) 
    {
        this.supplierName = supplierName;
    }

    public String getSupplierName() 
    {
        return supplierName;
    }
    public void setSupplierCode(String supplierCode) 
    {
        this.supplierCode = supplierCode;
    }

    public String getSupplierCode() 
    {
        return supplierCode;
    }
    public void setSupplierDetail(String supplierDetail) 
    {
        this.supplierDetail = supplierDetail;
    }

    public String getSupplierDetail() 
    {
        return supplierDetail;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setContactName(String contactName) 
    {
        this.contactName = contactName;
    }

    public String getContactName() 
    {
        return contactName;
    }
    public void setContactPhone(String contactPhone) 
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() 
    {
        return contactPhone;
    }

	public List<FmgreSupplierFinanceBo> getFinanceBos() {
		return financeBos;
	}

	public void setFinanceBos(List<FmgreSupplierFinanceBo> financeBos) {
		this.financeBos = financeBos;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("supplierId", getSupplierId())
            .append("supplierName", getSupplierName())
            .append("supplierCode", getSupplierCode())
            .append("supplierDetail", getSupplierDetail())
            .append("address", getAddress())
            .append("contactName", getContactName())
            .append("contactPhone", getContactPhone())
            .append("finance", financeBos == null ? "null" : financeBos.toString())
            .toString();
    }
}
