package com.zhirui.business.lib.service;

import java.util.List;

import cn.kepu.utils.PageModel;
import com.zhirui.business.lib.bean.SubCheckReport;

public interface SubCheckReportService {
	/**
	 * 分页查询的方法实现
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<SubCheckReport> getSubCheckReport(SubCheckReport subCheckReport, int pageNo, int pageSize);

	/**
	 * 获取数据的方法
	 * @param id
	 * @return
	 */
	public SubCheckReport getSubCheckReport(int id);
	/**
	 * 删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeSubCheckReport(int id);
	/**
	 * 批量删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeSubCheckReports(int[] id);
	/**
	 * 插入新数据的方法实现
	 * @param example
	 * @return
	 */
	public SubCheckReport addSubCheckReport(SubCheckReport subCheckReport);
	
	public List<SubCheckReport> getAllCheckItemByReport(Integer reportid);
	
}
