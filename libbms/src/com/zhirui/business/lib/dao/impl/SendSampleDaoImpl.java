package com.zhirui.business.lib.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.kepu.base.dao.DaoSupport;
import cn.kepu.utils.PageModel;

import com.zhirui.business.common.Constants;
import com.zhirui.business.lib.bean.SampleRegiste;
import com.zhirui.business.lib.bean.SendSample;
import com.zhirui.business.lib.dao.SendSampleDao;


@Repository("sendSampleDao")
public class SendSampleDaoImpl extends DaoSupport<SendSample> implements SendSampleDao {

	@Override
	public boolean updateStatus(SendSample sendSample) {
		if (sendSample==null) return false;
		sendSample.setStatuschangedate(new Date());
		try{
		    super.update(sendSample);
		    return true;
		}catch(Exception e){
			return false;
		}
	}
	@Override
	public int getSendCount(int sampleid) {
		StringBuffer where = new StringBuffer("where sampleno = ?");
		List<Object> queryParamList = new ArrayList<Object>();
		queryParamList.add(String.valueOf(sampleid));
		PageModel<SendSample> page = find(where.toString(), queryParamList.toArray(new Object[0]), null, 1, 1);
		if (page==null||page.getList().size()==0){
        	return 0;
        }

		return page.getTotalRecords();
	}
	
	@Override
	public int getSendFinishCount(int sampleid) {
		StringBuffer where = new StringBuffer("where sampleno = ? and status>=?");
		List<Object> queryParamList = new ArrayList<Object>();
		queryParamList.add(String.valueOf(sampleid));
		queryParamList.add(Constants.STATUS_FINISHCHECK);
		PageModel<SendSample> page = find(where.toString(), queryParamList.toArray(new Object[0]), null, 1, 1);
		if (page==null||page.getList().size()==0){
        	return 0;
        }

		return page.getTotalRecords();
	}
}
