package com.zhirui.business.lib.service;

import cn.kepu.utils.PageModel;
import com.zhirui.business.lib.bean.MenuFunction;

public interface MenuFunctionService {
	/**
	 * 分页查询的方法实现
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<MenuFunction> getMenuFunction(MenuFunction menuFunction, int pageNo, int pageSize);

	/**
	 * 获取数据的方法
	 * @param id
	 * @return
	 */
	public MenuFunction getMenuFunction(int id);
	/**
	 * 删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeMenuFunction(int id);

	
	public MenuFunction updateMenuFunction(MenuFunction menuFunction);
	/**
	 * 插入新数据的方法实现
	 * @param example
	 * @return
	 */
	public MenuFunction addMenuFunction(MenuFunction menuFunction);
	
}
