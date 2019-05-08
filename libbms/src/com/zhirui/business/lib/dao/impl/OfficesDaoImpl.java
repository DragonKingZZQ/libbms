package com.zhirui.business.lib.dao.impl;

import org.springframework.stereotype.Repository;
import cn.kepu.base.dao.DaoSupport;

import com.zhirui.business.lib.bean.Offices;
import com.zhirui.business.lib.dao.OfficesDao;

@Repository("officesDao")
public class OfficesDaoImpl extends DaoSupport<Offices> implements OfficesDao {
	
}
