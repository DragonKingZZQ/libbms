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

import cn.kepu.utils.PageModel;
import cn.kepu.utils.StringUtils;

import com.zhirui.business.common.Constants;
import com.zhirui.business.common.bean.User;
import com.zhirui.business.common.dao.UserDao;
import com.zhirui.business.lib.bean.CheckItem;
import com.zhirui.business.lib.bean.EntrustCompany;
import com.zhirui.business.lib.bean.GroupUser;
import com.zhirui.business.lib.bean.RegisteCheckItem;
import com.zhirui.business.lib.bean.SampleRegiste;
import com.zhirui.business.lib.bean.SendSample;
import com.zhirui.business.lib.bean.Statistics;
import com.zhirui.business.lib.dao.CheckItemDao;
import com.zhirui.business.lib.dao.EntrustCompanyDao;
import com.zhirui.business.lib.dao.GroupUserDao;
import com.zhirui.business.lib.dao.RegisteCheckItemDao;
import com.zhirui.business.lib.dao.SampleRegisteDao;
import com.zhirui.business.lib.dao.SendSampleDao;
import com.zhirui.business.lib.service.SampleRegisteService;
import com.zhirui.business.utils.BusinessUtils;

@Repository("sampleRegisteService")
public class SampleRegisteServiceImpl implements SampleRegisteService {
	
	private static final Log log = LogFactory.getLog(SampleRegisteServiceImpl.class);

	@Autowired
	private SampleRegisteDao sampleRegisteDao;
	@Autowired
	private SendSampleDao sendSampleDao;
	@Autowired
	private CheckItemDao checkItemDao;
	@Autowired
	private EntrustCompanyDao entrustCompanyDao;
	@Autowired
	private RegisteCheckItemDao registeCheckItemDao;
	@Autowired
	private GroupUserDao groupUserDao;
	@Autowired
	private UserDao userDao;
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#getExamples(com.zhirui.business.bean.Example, int, int)
	 */
	@Override
	public PageModel<SampleRegiste> getSampleRegiste(SampleRegiste sampleRegiste, int pageNo, int pageSize) {
		String where = "where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();
		if(sampleRegiste != null) {
			if (!StringUtils.isEmpty(sampleRegiste.getSampleno())) {
				where += " and sampleno like ?";
				queryParamList.add("%"+sampleRegiste.getSampleno()+"%");
			}
			if (!StringUtils.isEmpty(sampleRegiste.getSamplename())) {
				where += " and samplename like ?";
				queryParamList.add("%"+sampleRegiste.getSamplename()+"%");
			}
			if (sampleRegiste.getReceivedate()!=null&&!sampleRegiste.getReceivedate().equals("")) {
				where += " and receivedate = ?";
				Calendar cal = Calendar.getInstance();
				cal.setTime(sampleRegiste.getReceivedate());
				queryParamList.add(cal.getTime());
			} 
			if (!StringUtils.isEmpty(sampleRegiste.getSenduser())) {
				where += " and senduser like ?";
				queryParamList.add("%"+sampleRegiste.getSenduser()+"%");
			}
			if(!StringUtils.isEmpty(sampleRegiste.getProductionunit())){
				where += " and productionunit like ?";
				queryParamList.add("%"+sampleRegiste.getProductionunit()+"%");
			}
			if(!StringUtils.isEmpty(sampleRegiste.getStatus())){
				where += " and status = ?";
				queryParamList.add(sampleRegiste.getStatus());
			}
			//11.16 增加付款情况查询
			if (!StringUtils.isEmpty(sampleRegiste.getPayway())&&sampleRegiste.getPayway()==1){
				where += " and balancemoney>0 ";
			}
			if (!StringUtils.isEmpty(sampleRegiste.getPayway())&&sampleRegiste.getPayway()==2){
				where += " and balancemoney=0 ";
			}
			if (!StringUtils.isEmpty(sampleRegiste.getEntrustcompany())){
				String wherecodition = "";
				List<Object> ParamList = new ArrayList<Object>();
				wherecodition =" where entrustcompany like ?";
				ParamList.add("%"+sampleRegiste.getEntrustcompany()+"%");
				PageModel<EntrustCompany> ec = entrustCompanyDao.find(wherecodition, ParamList.toArray(new Object[0]), null, 1, Constants.MAX_COUNT);
				int i =0 ;
				if (ec!=null&&ec.getList().size()>=1){
					where  += " and entrustcompany in (";
					for(EntrustCompany sr : ec.getList()){
						if (i==0)
							where += sr.getId();
						else
							where += ","+sr.getId();
						i++;
					}
					where  += " ) ";
				}else{
					where +=" and 1=2 ";
				}
			}
			if (!StringUtils.isEmpty(sampleRegiste.getCheckitemname())) {
				String wherecodition = "";
				List<Object> ParamList = new ArrayList<Object>();
				wherecodition =" where itemname like ?";
				ParamList.add("%"+sampleRegiste.getCheckitemname()+"%");
				PageModel<CheckItem> ci = checkItemDao.find(wherecodition, ParamList.toArray(new Object[0]), null, 1, Constants.MAX_COUNT);
				int i =0 ;
				wherecodition = "";
				if (ci!=null&&ci.getList().size()>=1){
					where  += " and id in (";
					for(CheckItem sr : ci.getList()){
						if (i==0)
							wherecodition += sr.getId();
						else
							wherecodition += ","+sr.getId();
						i++;
					}
					PageModel<RegisteCheckItem> pageRegisteCheckItem = registeCheckItemDao.find("where checkitem in ("+wherecodition+")",null,null, 1, Constants.MAX_COUNT);
				
					int j =0;
					for(RegisteCheckItem rci : pageRegisteCheckItem.getList()){
						 where += rci.getSampleregisteid();
						 if (j+1!=pageRegisteCheckItem.getList().size()){
							 where +=",";
						 }
						 j++;
					}
					where  += " ) ";
				}else{
					where +=" and 1=2 ";
				}
			}
		}
		// 增加权限判断
		if (BusinessUtils.getCurrentUser().getAuthority().equals(Constants.RIGHT_MEMBER)){
			where += " and createuser = ? ";
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
		} */
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
		if (sampleRegiste!=null&&!StringUtils.isEmpty(sampleRegiste.getFieldorder())){
			orderby.put(sampleRegiste.getFieldorder(), "desc");
		}else{
		    orderby.put("id", "desc");
		}
		System.out.println(where+"值where");
		System.out.println(queryParamList.toString()+"集合");
		PageModel<SampleRegiste> page = sampleRegisteDao.find(where, queryParamList.toArray(new Object[0]), orderby, pageNo, pageSize);
		return page;
	}
	/**
	 * 得到未付完款的数据
	 */
	@Override
	public PageModel<SampleRegiste> getNoPayOff(Integer companyid,int pageNo, int pageSize) {
		String where = "where 1=1";
        if (companyid!=-1){
        	where += " and entrustcompany="+companyid;
        }
        where += " and status<>'I'";
        //2015.7.19 因为付款金额没法确定，暂时去掉这个条件，只要是这个公司的，所有登记单都显示
        where += " and balancemoney>0";
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("entrustcompany", "asc");
		PageModel<SampleRegiste> page = sampleRegisteDao.find(where, null, orderby, pageNo, pageSize);
		return page;
	}
	/**
	 * 得到未完成的登记
	 */
	@Override
	public PageModel<SampleRegiste> getNoFinishTask(int pageNo, int pageSize) {
		String where = "where 1=1";
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		where += " and finishdate < '"+now.get(Calendar.YEAR)+"-"+now.get(Calendar.MONTH)+"-"+now.get(Calendar.DAY_OF_MONTH)+" 23:59:59'";
		where += " and status<>'"+Constants.STATUS_FINISHCHECK+"' ";

		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		PageModel<SampleRegiste> page = sampleRegisteDao.find(where, null, orderby, pageNo, pageSize);
		return page;
	}
	/**
	 * 得到未完成的登记
	 */
	@Override
	public PageModel<SampleRegiste> getNewNoPayTask(int pageNo, int pageSize) {
		String where = "where 1=1";
		where += " and balancemoney>0 ";
		where += " and status='"+Constants.STATUS_REGISTEVERIFY+"' ";

		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		PageModel<SampleRegiste> page = sampleRegisteDao.find(where, null, orderby, pageNo, pageSize);
		return page;
	}

	@Override
	public SampleRegiste getSampleRegiste(int id){
		  return sampleRegisteDao.get(id);
	}
	
	@Override
	public SampleRegiste getSampleRegisteByCode(String code){
		String where = "";
		List<Object> queryParamList = new ArrayList<Object>();
		where =" where sampleno like ?";
		queryParamList.add(code);
		PageModel<SampleRegiste> ap = sampleRegisteDao.find(where, queryParamList.toArray(new Object[0]), null, 1, 1);
		if (ap!=null&&ap.getList().size()>0)
		    return ap.getList().get(0);
		else
			return null;
	}
	
	//曾智琴
	@Override
	public SampleRegiste getSampleRegisteBySampleno(String sampleno){
		String where = "";
		List<Object> queryParamList = new ArrayList<Object>();
		where =" where sampleno = ?";
		queryParamList.add(sampleno);
		PageModel<SampleRegiste> ap = sampleRegisteDao.find(where, queryParamList.toArray(new Object[0]), null, 1, 1);
		if (ap!=null&&ap.getList().size()>0)
		    return ap.getList().get(0);
		else
			return null;
	}
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#removeExample(int)
	 */
	@Override
	public boolean removeSampleRegiste(int id) {
		try {
			//删除通用表数据
			sampleRegisteDao.delete(id);
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
	public boolean isExists(SampleRegiste sampleRegiste){
		//查询样品编号是否有重复
		// 新增和修改调用不同的方法
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		if (sampleRegiste.getId()==null){
			String where = "";
			List<Object> queryParamList = new ArrayList<Object>();
			where =" where sampleno=?";
			queryParamList.add(sampleRegiste.getSampleno());
			PageModel<SampleRegiste> ap = sampleRegisteDao.find(where, queryParamList.toArray(new Object[0]), orderby, 1, 1);
			
			if (ap==null||ap.getList().size()==0) return false;
			
			return true;
			//return basedataDao.checkExist(academyPeople.getAuthorcorporation(), academyPeople.getName(), "pb_academypeople");
		}
		else{
			String where = "";
			List<Object> queryParamList = new ArrayList<Object>();
			where =" where sampleno=? and id<>?";
			queryParamList.add(sampleRegiste.getSampleno());
			queryParamList.add(sampleRegiste.getId());
			PageModel<SampleRegiste> ap = sampleRegisteDao.find(where, queryParamList.toArray(new Object[0]), orderby, 1, 1);
			
			if (ap==null||ap.getList().size()==0) return false;
			
			return true;
			 //return basedataDao.checkExist(academyPeople.getPeopleid(),academyPeople.getAuthorcorporation(), academyPeople.getName(), "pb_academypeople");
		}
	}
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#addExample(com.zhirui.business.bean.Example)
	 */
	@Override
	public SampleRegiste addSampleRegiste(SampleRegiste sampleRegiste) {
		try {
			int id = (Integer)sampleRegisteDao.save(sampleRegiste);
			return sampleRegiste;
		} catch (Exception e) {
			log.error("添加"+e.getMessage()+"失败", e);
			return null;
		}
	}
    
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#updateExample(com.zhirui.business.bean.Example)
	 */
	@Override
	public SampleRegiste updateSampleRegiste(SampleRegiste sampleRegiste) {
		try {
			sampleRegisteDao.update(sampleRegiste);
		} catch (Exception e) {
			log.error("更新"+sampleRegiste.getId()+"失败", e);
			return null;
		}
		return sampleRegiste;
	}
	@Override
	public boolean removeSampleRegistes(int[] ids) {
		for(int id : ids) {
			removeSampleRegiste(id);
		}
		return true;
	}
	@Override
	public String getSerilsNo(String yymmdd) {
		return sampleRegisteDao.getSerilsNumber(yymmdd);
	}

	@Override
	public boolean setStatus(SampleRegiste sampleRegiste) {
		return sampleRegisteDao.updateStatus(sampleRegiste);
	}

	@Override
	public Map<Integer, String> getSubmitRegisted() {
		String where = "where 1=1 and status>='B'";
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		PageModel<SampleRegiste> page = sampleRegisteDao.find(where, null, orderby, 1, Constants.MAX_COUNT);
        if (page==null||page.getList().size()==0) return null;
		
        Map<Integer, String> registes = new LinkedHashMap<Integer, String>();
        for(SampleRegiste sr : page.getList()){
        	
        	//查询是否派样完成的，完成的不能再进行派样
        	/*
        	PageModel<SendSample> pss = sendSampleDao.find(" where sampleno="+sr.getId(),null,null,1,Constants.MAX_COUNT);
        	int count = 0;
        	for(SendSample ss:pss.getList()){
        		if (ss.getSamplecount()!=null&&!ss.getSamplecount().trim().equals(""))
        		    count += Integer.parseInt(ss.getSamplecount().trim());
        	}
        	*/
        	//一个样品有多次派样的情况，放开不能派样的限制 2015.06.10
        	//if (count<Integer.parseInt(sr.getSamplecount().trim()))
        	    registes.put(sr.getId(), sr.getSampleno());
        }
        return registes;
	}
	
	@Override
	public Map<Integer, String> getFinishRegisted() {
		String where = "where status='G'";
		// 增加权限判断
		where += " and createuser = "+BusinessUtils.getCurrentUser().getUid();

		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		PageModel<SampleRegiste> page = sampleRegisteDao.find(where, null, orderby, 1, Constants.MAX_COUNT);
        if (page==null||page.getList().size()==0) return null;
		
        Map<Integer, String> registes = new LinkedHashMap<Integer, String>();
        for(SampleRegiste sr : page.getList()){
        	registes.put(sr.getId(), sr.getSampleno());
        }
        return registes;
	}
	@Override
	public Map<Integer, String> getAllRegisted() {
		// 增加权限判断
		String where = " where createuser = "+BusinessUtils.getCurrentUser().getUid();
        //11.26 增加去掉已经出了报告的
		where +=" and status<>'H'";
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		PageModel<SampleRegiste> page = sampleRegisteDao.find(where, null, orderby, 1, Constants.MAX_COUNT);
        if (page==null||page.getList().size()==0) return null;
		
        Map<Integer, String> registes = new LinkedHashMap<Integer, String>();
        
        //曾智琴  where
       
        for(SampleRegiste sr : page.getList()){
        	
        	registes.put(sr.getId(), sr.getSampleno());
        	List<Object> queryParamList = new ArrayList<Object>();
        	String where2 = "where 1=1";
        	 where2 += "and sendsampleno = ?";
        	 queryParamList.add(sr.getSampleno());
        	PageModel<SendSample> page2 = sendSampleDao.find(where2, queryParamList.toArray(new Object[0]),null,1,Constants.MAX_COUNT);
        	for(SendSample ss : page2.getList()){
        		//if(ss.getFinishflag()=="" ||(ss.getFinishflag()) != "2"){ //有一个不为2则不通过
        		if(!("2".equals(ss.getFinishflag()))  || "".equals(ss.getFinishflag())){
        			registes.remove(sr.getId());
        			break;
        		}
        	}
        	
        }
        return registes;
	}
	@Override
	public PageModel<SampleRegiste> getNoPayOff(Statistics statistics,
			int pageNo, int pageSize) {
		String where = "where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();
        if (statistics!=null){
        	
        	if (statistics.getReceivedatestart()!=null){
        	    where += " and receivedate>=?";
        	    Calendar cal = Calendar.getInstance();
				cal.setTime(statistics.getReceivedatestart());
				queryParamList.add(cal.getTime());
        	}
        	if (statistics.getReceivedateend()!=null){
        	    where += " and receivedate<=?";
        	    Calendar cal = Calendar.getInstance();
				cal.setTime(statistics.getReceivedateend());
				queryParamList.add(cal.getTime());
        	}
        	if (statistics.getEntrustcompanyid()!=null){
        	    where += " and entrustcompany=?";
				queryParamList.add(String.valueOf(statistics.getEntrustcompanyid()));
        	}
        	if (!StringUtils.isEmpty(statistics.getEntrustcompany())){
        		PageModel<EntrustCompany> pec = entrustCompanyDao.find(" where entrustcompany like '%"+statistics.getEntrustcompany()+"%'", null,null,1,Constants.MAX_COUNT);
        	    if (pec!=null&&pec.getList().size()>0){
        	    	where += " and entrustcompany in (";
        	    	String temp = "";
	        	    for(int i=0;i<pec.getList().size();i++){
	        	    	temp += pec.getList().get(i).getId();
	        	    	if (i+1!=pec.getList().size()){
	        	    		temp +=",";
	        	    	}
	        	    }
	        	    where +=temp+")";
        	    }else{
        	    	where += " and 1 =2 ";
        	    }
        	    
        	}
        }
        //增加默认按科室计算的功能
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
        //where += " and balancemoney>0";
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("entrustcompany", "asc");
		PageModel<SampleRegiste> page = sampleRegisteDao.find(where, queryParamList.toArray(new Object[0]), orderby, pageNo, pageSize);
		return page;
	}
	
	@Override
	public PageModel<SampleRegiste> getRegisteForExamine(int userid,
			int pageNo, int pageSize) {
		String where = "where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();
        	
		PageModel<RegisteCheckItem> pec = registeCheckItemDao.find(" where examineuser is NULL or examineuser ="+userid, null,null,1,Constants.MAX_COUNT);
	    //派样完成的状态不取
		where += " and status<>'"+Constants.STATUS_FINISHEDSAMPLE+"'";
		if (pec!=null&&pec.getList().size()>0){
	    	where += " and id in (";
	    	String temp = "";
    	    for(int i=0;i<pec.getList().size();i++){
    	    	temp += pec.getList().get(i).getSampleregisteid();
    	    	if (i+1!=pec.getList().size()){
    	    		temp +=",";
    	    	}
    	    }
    	    where +=temp+") and status<>'"+Constants.STATUS_FINISH+"'";
	    }else{
	    	where += " and 1 =2 ";
	    }
    	    

        //where += " and balancemoney>0";
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("sampleno", "asc");
		PageModel<SampleRegiste> page = sampleRegisteDao.find(where, queryParamList.toArray(new Object[0]), orderby, pageNo, pageSize);
		return page;
	}
}
