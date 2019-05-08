package com.zhirui.business.lib.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.kepu.utils.PageModel;

import com.zhirui.business.common.Constants;
import com.zhirui.business.lib.bean.SampleRegiste;
import com.zhirui.business.lib.bean.SendCheckItem;
import com.zhirui.business.lib.dao.CheckItemDao;
import com.zhirui.business.lib.dao.SendCheckItemDao;
import com.zhirui.business.lib.service.SendCheckItemService;

@Repository("sendCheckItemService")
public class SendCheckItemServiceImpl implements SendCheckItemService {
	
	private static final Log log = LogFactory.getLog(SendCheckItemServiceImpl.class);

	@Autowired
	private SendCheckItemDao sendCheckItemDao;
	@Autowired
	private CheckItemDao checkItemDao;

	@Override
	public PageModel<SendCheckItem> getSendCheckItem(int sendsampleid,int pageNo, int pageSize) {
		PageModel<SendCheckItem> page = sendCheckItemDao.find(" where sendsampleid='"+sendsampleid+"'",null, null, pageNo, pageSize);
		return page;
	}
	
	@Override
	public PageModel<SendCheckItem> getSendCheckItem(String[] sendsampleid,int pageNo, int pageSize) {
		String tmp = "";
		for(int i=0;i<sendsampleid.length;i++){
			tmp += sendsampleid[i];
			if (i+1!=sendsampleid.length){
				tmp +=",";
			}
		}
		PageModel<SendCheckItem> page = sendCheckItemDao.find(" where sendsampleid in ("+tmp+")",null, null, pageNo, pageSize);
		return page;
	}
	//曾智琴
	@Override
	public PageModel<SendCheckItem> getSendCheckItem(String sendsampleid,int pageNo, int pageSize) {
		String tmp = "";
		
		PageModel<SendCheckItem> page = sendCheckItemDao.find(" where sendsampleid='"+sendsampleid+"'",null, null, pageNo, pageSize);
		return page;
	}
	
	@Override
	public PageModel<SendCheckItem> getSendCheckItemForRegiste(String registeno,int pageNo, int pageSize) {
		PageModel<SendCheckItem> page = sendCheckItemDao.find(" where subsendsampleno like '"+registeno+"%'",null, null, pageNo, pageSize);
		return page;
	}
	@Override
	public SendCheckItem getSendCheckItem(int id){
		  return sendCheckItemDao.get(id);
	}
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#removeExample(int)
	 */
	//曾智琴
	@Override
	public SendCheckItem getSendCheckItemBySendSampleid(int sendsampleid){
		String where = "";
		List<Object> queryParamList = new ArrayList<Object>();
		where =" where sendsampleid = ?";
		queryParamList.add(sendsampleid);
		PageModel<SendCheckItem> ap = sendCheckItemDao.find(where, queryParamList.toArray(new Object[0]), null, 1, 1);
		if (ap!=null&&ap.getList().size()>0)
		    return ap.getList().get(0);
		else
			return null;
	}
	
	@Override
	public boolean removeSendCheckItem(int id) {
		try {
			//删除通用表数据
			sendCheckItemDao.delete(id);
			return true;
		} catch (Exception e) {
			log.error("删除"+id+"失败", e);
			return false;
		}
	}

	@Override
	public SendCheckItem addSendCheckItem(SendCheckItem sendCheckItem) {
		try {
			int id = (Integer)sendCheckItemDao.save(sendCheckItem);
			return sendCheckItem;
		} catch (Exception e) {
			log.error("添加"+e.getMessage()+"失败", e);
			return null;
		}
	}
    
	@Override
	public boolean removeSendCheckItems(int[] ids) {
		for(int id : ids) {
			removeSendCheckItem(id);
		}
		return true;
	}

	@Override
	public SendCheckItem updateSendCheckItem(SendCheckItem sendCheckItem) {
		try {
			sendCheckItemDao.update(sendCheckItem);
		} catch (Exception e) {
			log.error("更新"+sendCheckItem.getId()+"失败", e);
			System.out.println("checkitem更新失败");
			return null;
		}
		return sendCheckItem;
	}
	
	
	//李沅罡 根据SubSendSampleNo获得一个对象   zengzhiqin
		@Override
		public PageModel<SendCheckItem> getSendCheckItem2(String subSendSampleNo) {
			String where = "";
			List<Object> queryParamList = new ArrayList<Object>();
			where =" where subsendsampleno like ?";
			queryParamList.add(subSendSampleNo);
			PageModel<SendCheckItem> ap = sendCheckItemDao.find(where, queryParamList.toArray(new Object[0]), null, 1, Constants.MAX_COUNT);
			
				return ap;
		}
		
		//李沅罡 根据SubSendSampleNo获得一个对象   zengzhiqin
				@Override
				public SendCheckItem getSendCheckItem(String subSendSampleNo) {
					String where = "";
					List<Object> queryParamList = new ArrayList<Object>();
					where =" where subsendsampleno like ?";
					queryParamList.add(subSendSampleNo);
					PageModel<SendCheckItem> ap = sendCheckItemDao.find(where, queryParamList.toArray(new Object[0]), null, 1, Constants.MAX_COUNT);
					if (ap!=null&&ap.getList().size()>0)
					    return ap.getList().get(0);
					else
						return null;
				}
}
