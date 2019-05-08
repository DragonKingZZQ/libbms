package com.zhirui.business.lib.service;

import cn.kepu.utils.PageModel;
import com.zhirui.business.lib.bean.Income;

public interface IncomeService {
	/**
	 * 分页查询的方法实现
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<Income> getIncome(Income income, int pageNo, int pageSize);

	/**
	 * 获取数据的方法
	 * @param id
	 * @return
	 */
	public Income getIncome(int id);
	/**
	 * 删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeIncome(int id);
	/**
	 * 批量删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeIncomes(int[] id);
	
	public Income updateIncome(Income income);
	/**
	 * 插入新数据的方法实现
	 * @param example
	 * @return
	 */
	public Income addIncome(Income income);
	
}
