package com.zhirui.business.lib.service;

import cn.kepu.utils.PageModel;
import com.zhirui.business.lib.bean.Offices;

public interface OfficesService {
	/**
	 * 分页查询的方法实现
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<Offices> getOffices(Offices offices, int pageNo, int pageSize);

	/**
	 * 获取数据的方法
	 * @param id
	 * @return
	 */
	public Offices getOffices(int id);
	/**
	 * 删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeOffices(int id);
	/**
	 * 批量删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeOfficess(int[] id);
	
	public Offices updateOffices(Offices offices);
	/**
	 * 插入新数据的方法实现
	 * @param example
	 * @return
	 */
	public Offices addOffices(Offices offices);
	
}
