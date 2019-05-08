package com.zhirui.business.lib.service;

import cn.kepu.utils.PageModel;
import com.zhirui.business.lib.bean.Groups;

public interface GroupsService {
	/**
	 * 分页查询的方法实现
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<Groups> getGroups(Groups groups, int pageNo, int pageSize);

	/**
	 * 获取数据的方法
	 * @param id
	 * @return
	 */
	public Groups getGroups(int id);
	/**
	 * 删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeGroups(int id);

	
	public Groups updateGroups(Groups groups);
	/**
	 * 插入新数据的方法实现
	 * @param example
	 * @return
	 */
	public Groups addGroups(Groups groups);
	
}
