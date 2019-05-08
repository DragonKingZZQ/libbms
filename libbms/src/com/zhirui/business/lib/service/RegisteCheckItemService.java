package com.zhirui.business.lib.service;

import cn.kepu.utils.PageModel;

import com.zhirui.business.lib.bean.RegisteCheckItem;
import com.zhirui.business.lib.bean.SendCheckItem;

public interface RegisteCheckItemService {
	/**
	 * 分页查询的方法实现
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<RegisteCheckItem> getRegisteCheckItem(int sampleregisteid,int pageNo, int pageSize);

	/**
	 * 获取数据的方法
	 * @param id
	 * @return
	 */
	public RegisteCheckItem getRegisteCheckItem(int id);
	/**
	 * 删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeRegisteCheckItem(int id);
	/**
	 * 批量删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeRegisteCheckItems(int[] ids);
	/**
	 * 插入新数据的方法实现
	 * @param example
	 * @return
	 */
	public RegisteCheckItem addRegisteCheckItem(RegisteCheckItem registeCheckItem);
	
	public RegisteCheckItem updateRegisteCheckItem(RegisteCheckItem registeCheckItem);

	PageModel<RegisteCheckItem> getRegisteCheckItem(int sampleregisteid,
			int checkuser, int pageNo, int pageSize);
}
