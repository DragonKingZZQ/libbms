package com.zhirui.business.lib.dao.impl;

import org.springframework.stereotype.Repository;
import cn.kepu.base.dao.DaoSupport;

import com.zhirui.business.lib.bean.Prepayment;
import com.zhirui.business.lib.dao.PrepaymentDao;

@Repository("prepaymentDao")
public class PrepaymentDaoImpl extends DaoSupport<Prepayment> implements PrepaymentDao {
	
}
