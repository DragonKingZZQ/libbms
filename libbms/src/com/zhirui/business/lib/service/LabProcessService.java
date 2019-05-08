package com.zhirui.business.lib.service;

import java.util.Map;

import cn.kepu.utils.PageModel;

import com.zhirui.business.lib.bean.LabProcess;

public interface LabProcessService {
	/**
	 * 分页查询的方法实现
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<LabProcess> getLabProcess(LabProcess labProcess, int pageNo, int pageSize);

	/**
	 * 获取数据的方法
	 * @param id
	 * @return
	 */
	public LabProcess getLabProcess(int id);
	/**
	 * 删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeLabProcess(int id);
	/**
	 * 批量删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeLabProcess(int[] id);
	/**
	 * 插入新数据的方法实现
	 * @param example
	 * @return
	 */
	public LabProcess addLabProcess(LabProcess labProcess);
	
	/**
	 * 更新数据的方法实现
	 * @param example
	 * @return
	 */
	public LabProcess updateLabProcess(LabProcess labProcess);
	
	public Map<Integer,String> getAll();
	
	public Map<Integer,String> getAllValid();
	//是否存在，存在返回主键值，否则返回-1
	public int isExists(Integer cid,String name);
	
	//曾智琴
	Map<Integer, String> getAllTitles();
}
