package com.zhirui.business.lib.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.kepu.utils.DateTimeUtils;
import cn.kepu.utils.PageModel;
import cn.kepu.utils.StringUtils;

import com.zhirui.business.common.Constants;
import com.zhirui.business.common.bean.User;
import com.zhirui.business.common.dao.UserDao;
import com.zhirui.business.lib.bean.CheckImage;
import com.zhirui.business.lib.bean.CheckItem;
import com.zhirui.business.lib.bean.GroupUser;
import com.zhirui.business.lib.bean.SendCheckItem;
import com.zhirui.business.lib.bean.SendSample;
import com.zhirui.business.lib.dao.CheckItemDao;
import com.zhirui.business.lib.dao.GroupUserDao;
import com.zhirui.business.lib.dao.SendCheckItemDao;
import com.zhirui.business.lib.dao.SendSampleDao;
import com.zhirui.business.lib.service.SendSampleService;
import com.zhirui.business.utils.BusinessUtils;

@Repository("sendSampleService")
public class SendSampleServiceImpl implements SendSampleService {
	
	private static final Log log = LogFactory.getLog(SendSampleServiceImpl.class);

	@Autowired
	private SendSampleDao sendSampleDao;
	@Autowired
	private CheckItemDao checkItemDao;
	@Autowired
	private SendCheckItemDao sendCheckItemDao;
	@Autowired
	private GroupUserDao groupUserDao;
	@Autowired
	private UserDao userDao;
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#getExamples(com.zhirui.business.bean.Example, int, int)
	 */
	@Override
	public PageModel<SendSample> getSendSample(SendSample sendSample, int pageNo, int pageSize) {
		String where = "where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();
		if(sendSample != null) {
			if (!StringUtils.isEmpty(sendSample.getSampleno())) {
				where += " and sampleno = ?";
				queryParamList.add(sendSample.getSampleno());
			}
			if (!StringUtils.isEmpty(sendSample.getSendid())) {
				where += " and sendid = ?";
				queryParamList.add(sendSample.getSendid());
			}
			if (!StringUtils.isEmpty(sendSample.getSendsampleno())) {
				where += " and sendsampleno like ?";
				queryParamList.add("%"+sendSample.getSendsampleno()+"%");
			}
			if (!StringUtils.isEmpty(sendSample.getSendsamplename())) {
				where += " and sendsamplename like ?";
				queryParamList.add("%"+sendSample.getSendsamplename()+"%");
			}
			if (sendSample.getReceivedate()!=null) {
				where += " and receivedate = ?";
				Calendar cal = Calendar.getInstance();
				cal.setTime(sendSample.getReceivedate());
				queryParamList.add(cal.getTime());
			} 
			if (sendSample.getFinishdate()!=null) {
				where += " and finishdate = ?";
				Calendar cal = Calendar.getInstance();
				cal.setTime(sendSample.getFinishdate());
				queryParamList.add(cal.getTime());
			} 
			//增加派样人和检验员
			if (!StringUtils.isEmpty(sendSample.getLeader())&&sendSample.getLeader()!=0) {
				where += " and leader = ?";
				queryParamList.add(sendSample.getLeader());
			}
			if (!StringUtils.isEmpty(sendSample.getExamineuser())&&sendSample.getExamineuser()!=0) {
				where += " and examineuser = ?";
				queryParamList.add(sendSample.getExamineuser());
			}
			if (!StringUtils.isEmpty(sendSample.getCheckitem())) {
				PageModel<SendCheckItem> ci = sendCheckItemDao.find(null, null, null, 1, Constants.MAX_COUNT);
                int i = 0;
				if (ci!=null&&ci.getList().size()>=1){
					//where  += " and (";
					// 得到检验项目名称
					String tmp = "";
					for(SendCheckItem sr : ci.getList()){
						PageModel<CheckItem> iteems = checkItemDao.find(" where itemname like '%"+sendSample.getCheckitem()+"%'",null,null,1,Constants.MAX_COUNT);
						if (iteems!=null&&iteems.getList().size()>=1){
							for(CheckItem citem:iteems.getList()){
								 if (Integer.parseInt(sr.getCheckitem())==citem.getId()){
									if (i==0)
									    tmp += " id="+sr.getSendsampleid();
									else
										tmp += " or id="+sr.getSendsampleid();
									i++;
								 }
							}
						}else{
							where +=" and 1=2 ";
						}
					}
					if (i>0)
					   where  += " and ( "+tmp+" ) ";
					else
						where +=" and 1=2 ";
				}
			} 
		}
		// 增加权限判断
		if (BusinessUtils.getCurrentUser().getAuthority().equals(Constants.RIGHT_MEMBER)){
			where += " and (createuser = ? or examineuser = ? ) and (status>='D') ";
			queryParamList.add(BusinessUtils.getCurrentUser().getUid());
			queryParamList.add(BusinessUtils.getCurrentUser().getUid());
		}
		/*
		if (BusinessUtils.getCurrentUser().getAuthority().equals(Constants.RIGHT_MEMBERLEADER)){
			int groupid = BusinessUtils.getCurrentUser().getGroupid();
			PageModel<GroupUser> gu = groupUserDao.find(" where groupid="+groupid, null,null,1,Constants.MAX_COUNT);
			where += " and createuser in ( ";
			for(int i=0;i<gu.getList().size();i++){
				where += gu.getList().get(i).getUserid();
				if (i+1!=gu.getList().size()){
					where +=",";
				}
			}
			where += " ) ";
		}*/
		if (BusinessUtils.getCurrentUser().getAuthority().indexOf(Constants.RIGHT_MEMBERLEADER)>=0){
			User user = BusinessUtils.getCurrentUser();
			if(user.getOffice()!=1){
			     where +=" and createuser in (";	
			     PageModel<User> pu = userDao.find(" where office="+user.getOffice(), null,null,1,Constants.MAX_COUNT);
			     String tmp = "";
			     for(int x=0;x<pu.getList().size();x++){
			    	 tmp += pu.getList().get(x).getUid();
			    	 if(x+1!=pu.getList().size()){
			    		 tmp +=",";
			    	 }
			     }
			     where += tmp+")";
			}
		}
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		if (sendSample!=null&&!StringUtils.isEmpty(sendSample.getFieldorder())){
			orderby.put(sendSample.getFieldorder(), "desc");
		}else{
		    orderby.put("id", "desc");
		}
		System.out.println(where+"值where");
		System.out.println(queryParamList.toString()+"集合");
		PageModel<SendSample> page = sendSampleDao.find(where, queryParamList.toArray(new Object[0]), orderby, pageNo, pageSize);
		return page;
	}
	
	
	
	@Override
	public PageModel<SendSample> getMySendSample(int pageNo, int pageSize) {
		String where = "where 1=1";
		where += " and examineuser = "+BusinessUtils.getCurrentUser().getUid();
		where += " and receiveflag='0' and receivewarnflag='0' and (status='D' or status='E') ";

		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		PageModel<SendSample> page = sendSampleDao.find(where, null, orderby, pageNo, pageSize);
		return page;
	}
	@Override
	public PageModel<SendSample> getWarnSendSample(int pageNo, int pageSize) {
		String where = "where 1=1";
		where += " and examineuser = "+BusinessUtils.getCurrentUser().getUid();
		where += " and (finishflag='0' or finishflag is null)  and receivewarnflag='0' ";
		
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		now.add(Calendar.DATE,Constants.WARN_DAYS);
		
		where += " and enddate<'"+now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+now.get(Calendar.DAY_OF_MONTH)+" 23:59:59'";
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("enddate", "asc");
		PageModel<SendSample> page = sendSampleDao.find(where, null, orderby, pageNo, pageSize);
		return page;
	}
	@Override
	public PageModel<SendSample> getWarnSendSampleForLeader(int pageNo, int pageSize) {
		String where = "where 1=1";
		where += " and finishflag='0'  and receivewarnflag='0' ";
		
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		now.add(Calendar.DATE,Constants.WARNLEADER_DAYS);
		
		where += " and enddate<'"+now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+now.get(Calendar.DAY_OF_MONTH)+" 23:59:59'";
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("enddate", "asc");
		PageModel<SendSample> page = sendSampleDao.find(where, null, orderby, pageNo, pageSize);
		return page;
	}
	@Override
	public SendSample getSendSample(int id){
		  return sendSampleDao.get(id);
	}
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#removeExample(int)
	 */
	@Override
	public boolean removeSendSample(int id) {
		try {
			//删除通用表数据
			sendSampleDao.delete(id);
			return true;
		} catch (Exception e) {
			log.error("删除"+id+"失败", e);
			return false;
		}
	}
	/**
	 * 查询数据是否已经存在
	 * (按所有还是按年度季度)
	 * @param 
	 * @return
	 */
	@Override
	public boolean isExists(SendSample sendSample){
		//查询样品编号是否有重复
		// 新增和修改调用不同的方法
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		if (sendSample.getId()==null){
			String where = "";
			List<Object> queryParamList = new ArrayList<Object>();
			where =" where sendsampleno=?";
			queryParamList.add(sendSample.getSendsampleno());
			PageModel<SendSample> ap = sendSampleDao.find(where, queryParamList.toArray(new Object[0]), orderby, 1, 1);
			
			if (ap==null||ap.getList().size()==0) return false;
			
			return true;
			//return basedataDao.checkExist(academyPeople.getAuthorcorporation(), academyPeople.getName(), "pb_academypeople");
		}
		else{
			String where = "";
			List<Object> queryParamList = new ArrayList<Object>();
			where =" where sendsampleno=? and id<>?";
			queryParamList.add(sendSample.getSendsampleno());
			queryParamList.add(sendSample.getId());
			PageModel<SendSample> ap = sendSampleDao.find(where, queryParamList.toArray(new Object[0]), orderby, 1, 1);
			
			if (ap==null||ap.getList().size()==0) return false;
			
			return true;
			 //return basedataDao.checkExist(academyPeople.getPeopleid(),academyPeople.getAuthorcorporation(), academyPeople.getName(), "pb_academypeople");
		}
	}
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#addExample(com.zhirui.business.bean.Example)
	 */
	@Override
	public SendSample addSendSample(SendSample sendSample) {
		try {
			int id = (Integer)sendSampleDao.save(sendSample);
			sendSample = sendSampleDao.get(id);
			return sendSample;
		} catch (Exception e) {
			log.error("添加"+e.getMessage()+"失败", e);
			return null;
		}
	}
    
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#updateExample(com.zhirui.business.bean.Example)
	 */
	@Override
	public SendSample updateSendSample(SendSample sendSample) {
		try {
			sendSampleDao.update(sendSample);
		} catch (Exception e) {
			log.error("更新"+sendSample.getId()+"失败", e);
			return null;
		}
		return sendSample;
	}
	@Override
	public boolean removeSendSamples(int[] ids) {
		for(int id : ids) {
			removeSendSample(id);
		}
		return true;
	}

	@Override
	public boolean setStatus(SendSample sendSample) {
		return sendSampleDao.updateStatus(sendSample);
	}
}
