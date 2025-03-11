package com.ruoyi.fmgr.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.fmgr.domain.FmgrePurchaseRequirSummaryBo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 采购需求对象 fmgre_purchase_requir
 * 
 * @author terence
 * @date 2025-02-24
 */
public class FmgrePurchaseRequir extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 采购需求id */
    private Long requirId;

    /** 送达门店id */
    @Excel(name = "送达门店id")
    private Long deptId;

    /** 需求者id */
    @Excel(name = "需求者id")
    private Long requirUserId;

    /** 需求时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "需求时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date requirTime;

    /** 需求说明 */
    @Excel(name = "需求说明")
    private String requirComment;

    public void setRequirId(Long requirId) 
    {
        this.requirId = requirId;
    }

    public Long getRequirId() 
    {
        return requirId;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setRequirUserId(Long requirUserId) 
    {
        this.requirUserId = requirUserId;
    }

    public Long getRequirUserId() 
    {
        return requirUserId;
    }
    public void setRequirTime(Date requirTime) 
    {
        this.requirTime = requirTime;
    }

    public Date getRequirTime() 
    {
        return requirTime;
    }
    public void setRequirComment(String requirComment) 
    {
        this.requirComment = requirComment;
    }

    public String getRequirComment() 
    {
        return requirComment;
    }

    private List<FmgrePurchaseItem> requirItems;

    public List<FmgrePurchaseItem> getRequirItems() {
        return requirItems;
    }

    public void setRequirItems(List<FmgrePurchaseItem> requirItems) {
        this.requirItems = requirItems;
    }

    private FmgrePurchaseRequirSummaryBo summary;

    public FmgrePurchaseRequirSummaryBo getSummary() {
        return summary;
    }

    public void setSummary(FmgrePurchaseRequirSummaryBo summary) {
        this.summary = summary;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("requirId", getRequirId())
            .append("deptId", getDeptId())
            .append("requirUserId", getRequirUserId())
            .append("requirTime", getRequirTime())
            .append("requirComment", getRequirComment())
            .append("requirItems", getRequirItems())
            .toString();
    }
}
