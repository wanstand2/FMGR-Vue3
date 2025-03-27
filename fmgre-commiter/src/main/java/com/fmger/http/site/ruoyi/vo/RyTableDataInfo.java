package com.fmger.http.site.ruoyi.vo;

import java.util.List;

public class RyTableDataInfo<T> {

    public long total;

    /** 列表数据 */
    public List<T> rows;

    /** 消息状态码 */
    public int code;

    /** 消息内容 */
    public String msg;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
    
    
}
