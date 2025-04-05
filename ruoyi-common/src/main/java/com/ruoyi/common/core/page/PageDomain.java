package com.ruoyi.common.core.page;

import com.ruoyi.common.utils.StringUtils;

/**
 * 分页数据
 * 
 * @author ruoyi
 */
public class PageDomain
{
    /** 当前记录起始索引 */
    private Integer pageNum;

    /** 每页显示记录数 */
    private Integer pageSize;

    /** 排序列 */
    private String[] orderByColumn;

    /** 排序的方向desc或者asc */
    private String[] isAsc = {"asc"};

    /** 分页参数合理化 */
    private Boolean reasonable = true;

    public String getOrderBy()
    {
        if (orderByColumn == null || orderByColumn.length == 0)
        {
            return "";
        }
        String ret = "";
        for(int i=0;i<orderByColumn.length && i<isAsc.length;i++) {
            if(i>0) ret+= ", ";
            ret += (StringUtils.toUnderScoreCase(orderByColumn[i]) + " " + isAsc[i]);
        }
        return ret;
    }

    public Integer getPageNum()
    {
        return pageNum;
    }

    public void setPageNum(Integer pageNum)
    {
        this.pageNum = pageNum;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public String[] getOrderByColumn()
    {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn)
    {
        if(StringUtils.isNotEmpty(orderByColumn)) {
            this.orderByColumn = orderByColumn.split("-");
        }
    }

    public String[] getIsAsc()
    {
        return isAsc;
    }

    public void setIsAsc(String isAsc)
    {
        if (StringUtils.isNotEmpty(isAsc))
        {
            String[] isAscs = isAsc.split("-");
            this.isAsc = new String[isAsc.length()];
            for(int i=0;i<isAscs.length;i++) {
                // 兼容前端排序类型
                if ("ascending".equals(isAscs[i]))
                {
                    this.isAsc[i] = "asc";
                }
                else if ("descending".equals(isAscs[i]))
                {
                    this.isAsc[i] = "desc";
                }
            }
        }
    }

    public Boolean getReasonable()
    {
        if (StringUtils.isNull(reasonable))
        {
            return Boolean.TRUE;
        }
        return reasonable;
    }

    public void setReasonable(Boolean reasonable)
    {
        this.reasonable = reasonable;
    }
}
