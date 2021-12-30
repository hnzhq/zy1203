package com.st.model;

import com.st.utils.DateRange;

/**
 * 表单查询的bean实例
 * 
 * @author shenming
 *
 */
public class GoodsSearchBean extends Goods {
	private DateRange timeRange;// 时间范围

	public DateRange getTimeRange() {
		return timeRange;
	}

	public void setTimeRange(DateRange timeRange) {
		this.timeRange = timeRange;
	}

}
