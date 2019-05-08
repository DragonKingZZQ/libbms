package com.zhirui.business.lib.service;

import cn.kepu.utils.PageModel;
import com.zhirui.business.lib.bean.CheckReport;
import com.zhirui.business.lib.bean.SendSample;

public interface CheckReportService {
	/**
	 * 分页查询的方法实现
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<CheckReport> getCheckReport(CheckReport checkReport, int pageNo, int pageSize);
	/**
	 * 首页提示信息的查询功能
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<CheckReport> getMyCheckReport(int pageNo, int pageSize);
	/**
	 * 获取数据的方法
	 * @param id
	 * @return
	 */
	public CheckReport getCheckReport(int id);
	/**
	 * 删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeCheckReport(int id);
	/**
	 * 批量删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeCheckReports(int[] id);
	
	public CheckReport updateCheckReport(CheckReport checkReport);
	/**
	 * 插入新数据的方法实现
	 * @param example
	 * @return
	 */
	public CheckReport addCheckReport(CheckReport checkReport);
	
}
