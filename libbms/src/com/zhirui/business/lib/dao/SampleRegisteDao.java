package com.zhirui.business.lib.dao;

import com.zhirui.business.lib.bean.SampleRegiste;

import cn.kepu.base.dao.BaseDao;

public interface SampleRegisteDao extends BaseDao<SampleRegiste> {
   
	public String getSerilsNumber(String yymmdd);
	
	public boolean updateStatus(SampleRegiste sampleRegiste);
}
