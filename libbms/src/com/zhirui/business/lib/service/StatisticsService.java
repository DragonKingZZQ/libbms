package com.zhirui.business.lib.service;

import cn.kepu.utils.PageModel;
import com.zhirui.business.lib.bean.Statistics;

public interface StatisticsService {
	/**
	 * 分页查询的方法实现
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<Statistics> getRegisteStatistics(Statistics statistics, int pageNo, int pageSize);
	public PageModel<Statistics> getEntrustCompanyStatistics(Statistics statistics, int pageNo, int pageSize);
	public PageModel<Statistics> getEmployeeStatistics(Statistics statistics, int pageNo, int pageSize);
}
