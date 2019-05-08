package com.zhirui.business.lib.service;

import cn.kepu.utils.PageModel;
import com.zhirui.business.lib.bean.Income;
import com.zhirui.business.lib.bean.Prepayment;

public interface PrepaymentService {
	/**
	 * 分页查询的方法实现
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<Prepayment> getPrepayment(Prepayment prepayment, int pageNo, int pageSize);

	/**
	 * 获取数据的方法
	 * @param id
	 * @return
	 */
	public Prepayment getPrepayment(int id);
	/**
	 * 删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removePrepayment(int id);
	/**
	 * 批量删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removePrepayments(int[] id);
	
	public Prepayment updatePrepayment(Prepayment prepayment);
	/**
	 * 插入新数据的方法实现
	 * @param example
	 * @return
	 */
	public Prepayment addPrepayment(Prepayment prepayment);
	
}
