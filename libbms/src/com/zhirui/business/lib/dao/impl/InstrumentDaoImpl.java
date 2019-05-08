package com.zhirui.business.lib.dao.impl;

import org.springframework.stereotype.Repository;
import cn.kepu.base.dao.DaoSupport;

import com.zhirui.business.lib.bean.Instrument;
import com.zhirui.business.lib.dao.InstrumentDao;

@Repository("instrumentDao")
public class InstrumentDaoImpl extends DaoSupport<Instrument> implements InstrumentDao {
	
}
