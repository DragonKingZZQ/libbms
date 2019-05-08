package com.zhirui.business.lib.service;

import java.util.Map;

import cn.kepu.utils.PageModel;

import com.zhirui.business.lib.bean.CheckStandard;

public interface CheckStandardService {
	/**
	 * 分页查询的方法实现
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<CheckStandard> getCheckStandard(CheckStandard CheckStandard, int pageNo, int pageSize);

	/**
	 * 获取数据的方法
	 * @param id
	 * @return
	 */
	public CheckStandard getCheckStandard(int id);
	/**
	 * 删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeCheckStandard(int id);
	/**
	 * 批量删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeCheckStandards(int[] id);
	/**
	 * 插入新数据的方法实现
	 * @param example
	 * @return
	 */
	public CheckStandard addCheckStandard(CheckStandard checkStandard);
	
	/**
	 * 更新数据的方法实现
	 * @param example
	 * @return
	 */
	public CheckStandard updateCheckStandard(CheckStandard CheckStandard);
	
	public Map<Integer,String> getAll();
	
	public Map<Integer,String> getAllValid();
	//是否存在，存在返回主键值，否则返回-1
	public int isExists(Integer cid,String name);


	
}
