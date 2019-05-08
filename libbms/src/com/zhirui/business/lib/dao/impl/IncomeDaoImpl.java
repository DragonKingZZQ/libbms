package com.zhirui.business.lib.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.kepu.base.dao.DaoSupport;
import cn.kepu.utils.PageModel;

import com.zhirui.business.common.Constants;
import com.zhirui.business.lib.bean.Income;
import com.zhirui.business.lib.bean.SendSample;
import com.zhirui.business.lib.dao.IncomeDao;

@Repository("incomeDao")
public class IncomeDaoImpl extends DaoSupport<Income> implements IncomeDao {
	@Override
	public int getBillCount(int entrustid) {
		StringBuffer where = new StringBuffer("where entrustcompanyid = ? and billischeck='1' and status='1'");
		List<Object> queryParamList = new ArrayList<Object>();
		queryParamList.add(entrustid);
		PageModel<Income> page = find(where.toString(), queryParamList.toArray(new Object[0]), null, 1, 1);
		if (page==null||page.getList().size()==0){
        	return 0;
        }

		return page.getTotalRecords();
	}
	@Override
	public int getPostCount(int entrustid) {
		StringBuffer where = new StringBuffer("where entrustcompanyid = ? and  billno<>'' AND billno is not null  and status='1'");
		List<Object> queryParamList = new ArrayList<Object>();
		queryParamList.add(entrustid);
		PageModel<Income> page = find(where.toString(), queryParamList.toArray(new Object[0]), null, 1, 1);
		if (page==null||page.getList().size()==0){
        	return 0;
        }

		return page.getTotalRecords();
	}
}
