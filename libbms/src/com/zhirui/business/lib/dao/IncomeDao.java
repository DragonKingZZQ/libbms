package com.zhirui.business.lib.dao;

import com.zhirui.business.lib.bean.Income;

import cn.kepu.base.dao.BaseDao;

public interface IncomeDao extends BaseDao<Income> {

	//得到样品登记的已开发票的个数
	public int getBillCount(int entrustid);
	//得到样品登记的已发快递的个数
	public int getPostCount(int entrustid);
}
