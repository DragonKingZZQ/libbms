package com.zhirui.business.lib.service;

import java.util.List;
import java.util.Map;

import cn.kepu.utils.PageModel;
import com.zhirui.business.lib.bean.EntrustCompany;


public interface EntrustCompanyService {
	/**
	 * 分页查询的方法实现
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<EntrustCompany> getEntrustCompany(EntrustCompany entrustCompany, int pageNo, int pageSize);

	/**
	 * 获取数据的方法
	 * @param id
	 * @return
	 */
	public EntrustCompany getEntrustCompany(int id);
	/**
	 * 删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeEntrustCompany(int id);
	/**
	 * 批量删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeEntrustCompanys(int[] id);
	/**
	 * 插入新数据的方法实现
	 * @param example
	 * @return
	 */
	public EntrustCompany addEntrustCompany(EntrustCompany entrustCompany);
	
	/**
	 * 更新数据的方法实现
	 * @param example
	 * @return
	 */
	public EntrustCompany updateEntrustCompany(EntrustCompany entrustCompany);
	
	public Map<Integer,String> getAll();
	
	public EntrustCompany getEntrustCompany(String name);
	
	public Map<Integer,String> getNOPayOff();
}
