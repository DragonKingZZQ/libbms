package com.zhirui.business.lib.service;

import java.util.List;
import java.util.Map;

import cn.kepu.utils.PageModel;

import com.zhirui.business.lib.bean.CheckItem;
import com.zhirui.business.lib.bean.RelationUser;


public interface CheckItemService {
	/**
	 * 分页查询的方法实现
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<CheckItem> getCheckItem(CheckItem checkItem, int pageNo, int pageSize);

	/**
	 * 获取数据的方法
	 * @param id
	 * @return
	 */
	public CheckItem getCheckItem(int id);
	/**
	 * 删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeCheckItem(int id);
	/**
	 * 批量删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeCheckItems(int[] id);
	/**
	 * 插入新数据的方法实现
	 * @param example
	 * @return
	 */
	public CheckItem addCheckItem(CheckItem checkItem);
	
	/**
	 * 更新数据的方法实现
	 * @param example
	 * @return
	 */
	public CheckItem updateCheckItem(CheckItem checkItem);
	
	public Map<Integer,String> getAll();
	
	public Map<Integer,String> getAllValid();
	//是否存在，存在返回主键值，否则返回-1
	public int isExists(Integer cid,String name);
}
