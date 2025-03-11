package com.ruoyi.fmgr.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.entity.SysDept;

/**
 * 门店对象 fmgre_franchise_store
 * 
 * @author terence
 * @date 2025-02-24
 */
public class FmgreFranchiseStore extends SysDept
{
    private static final long serialVersionUID = 1L;

    /** 门店地址 */
    @Excel(name = "门店地址")
    private String storeAddress;

    /** 门店描述 */
    @Excel(name = "门店描述")
    private String storeDetail;

    public void setStoreAddress(String storeAddress) 
    {
        this.storeAddress = storeAddress;
    }

    public String getStoreAddress() 
    {
        return storeAddress;
    }
    public void setStoreDetail(String storeDetail) 
    {
        this.storeDetail = storeDetail;
    }

    public String getStoreDetail() 
    {
        return storeDetail;
    }

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append(super.toString())
            .append("storeAddress", getStoreAddress())
            .append("storeDetail", getStoreDetail())
            .toString();
    }
}
