package com.zhirui.business.lib.dao;

import java.util.ArrayList;
import java.util.List;

import com.zhirui.business.lib.bean.SampleRegiste;
import com.zhirui.business.lib.bean.SendSample;

import cn.kepu.base.dao.BaseDao;
import cn.kepu.utils.PageModel;

public interface SendSampleDao extends BaseDao<SendSample> {
	
	public boolean updateStatus(SendSample sendSample);
	
    //得到样品登记的派样个数
	public int getSendCount(int sampleid);
	//得到样品登记的派样的完成检验的个数
	public int getSendFinishCount(int sampleid);
}
