package com.zhirui.business.lib.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.kepu.utils.PageModel;
import cn.kepu.utils.StringUtils;

import com.zhirui.business.common.Constants;
import com.zhirui.business.common.bean.User;
import com.zhirui.business.common.dao.UserDao;
import com.zhirui.business.lib.bean.CheckItem;
import com.zhirui.business.lib.bean.EntrustCompany;
import com.zhirui.business.lib.bean.SampleRegiste;
import com.zhirui.business.lib.bean.SendCheckItem;
import com.zhirui.business.lib.bean.SendSample;
import com.zhirui.business.lib.bean.Statistics;
import com.zhirui.business.lib.bean.SubCheckReport;
import com.zhirui.business.lib.dao.CheckItemDao;
import com.zhirui.business.lib.dao.EntrustCompanyDao;
import com.zhirui.business.lib.dao.IncomeDao;
import com.zhirui.business.lib.dao.SampleRegisteDao;
import com.zhirui.business.lib.dao.SendCheckItemDao;
import com.zhirui.business.lib.dao.SendSampleDao;
import com.zhirui.business.lib.dao.SubCheckReportDao;
import com.zhirui.business.lib.service.StatisticsService;
import com.zhirui.business.utils.BusinessUtils;

@Repository("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {
	
	@Autowired
	private SampleRegisteDao sampleRegisteDao;
	@Autowired
	private SendSampleDao sendSampleDao;
	@Autowired
	private SubCheckReportDao subCheckReportDao;
	@Autowired
	private SendCheckItemDao sendCheckItemDao;
	@Autowired
	private CheckItemDao checkItemDao;
	@Autowired
	private EntrustCompanyDao entrustCompanyDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private IncomeDao incomeDao;

	@Override
	public PageModel<Statistics> getRegisteStatistics(Statistics statistics, int pageNo, int pageSize) {
		String where = "where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();
		if(statistics != null) {
			if (statistics.getReceivedatestart()!=null&&!statistics.getReceivedatestart().equals("")) {
				where += " and receivedate >= ?";
				Calendar cal = Calendar.getInstance();
				cal.setTime(statistics.getReceivedatestart());
				queryParamList.add(cal.getTime());
			} 
			if (statistics.getReceivedateend()!=null&&!statistics.getReceivedateend().equals("")) {
				where += " and receivedate < ?";
				Calendar cal = Calendar.getInstance();
				cal.setTime(statistics.getReceivedateend());
				queryParamList.add(cal.getTime());
			}
			if (!StringUtils.isEmpty(statistics.getEntrustcompany())) {
				where += " and entrustcompany like ?";
				queryParamList.add("%"+statistics.getEntrustcompany()+"%");
			}
			if (!StringUtils.isEmpty(statistics.getCheckitem())) {
				PageModel<SubCheckReport> ci = subCheckReportDao.find(null, null, null, 1, Constants.MAX_COUNT);
                int i = 0;
				if (ci!=null&&ci.getList().size()>=1){
					//where  += " and (";
					// 得到检验项目名称
					String tmp = "";
					for(SubCheckReport sr : ci.getList()){
						PageModel<CheckItem> iteems = checkItemDao.find(" where itemname like '%"+statistics.getCheckitem()+"%'",null,null,1,Constants.MAX_COUNT);
						if (iteems!=null&&iteems.getList().size()>=1&&Integer.parseInt(sr.getCheckitem())==iteems.getList().get(0).getId()){
							if (i==0)
							    tmp += " id="+sr.getCheckreportid();
							else
								tmp += " or id="+sr.getCheckreportid();
							i++;
						}
						
					}
					if (i>0)
					   where  += " and ( "+tmp+" ) ";
				}else{
					where +=" and 1=2 ";
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
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		PageModel<SampleRegiste> page = sampleRegisteDao.find(where,queryParamList.toArray(new Object[0]), orderby, 1, Constants.MAX_COUNT);
		PageModel<Statistics> newpage = new PageModel<Statistics>();
		List<Statistics> list = new ArrayList<Statistics>();
		Statistics s = new Statistics();
		float checkmoney = 0,premoney=0,balancemoney=0;
		for(int i=0;i<page.getList().size();i++){
			checkmoney = checkmoney+page.getList().get(i).getCheckmoney();
			premoney = premoney+page.getList().get(i).getPrepaymoney();
			balancemoney = balancemoney+page.getList().get(i).getBalancemoney();
		}
		s.setCheckmoney(checkmoney);
		s.setPremoney(premoney);
		s.setBalmoney(balancemoney);
		list.add(s);
		
		newpage.setPageNo(1);
		newpage.setPageSize(Constants.MAX_COUNT);
		newpage.setTotalRecords(page.getList().size());
		newpage.setList(list);
		return newpage;
	}
	@Override
	public PageModel<Statistics> getEntrustCompanyStatistics(Statistics statistics, int pageNo, int pageSize) {
		String where = " where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();
		if(statistics != null) {
			if (statistics.getReceivedatestart()!=null&&!statistics.getReceivedatestart().equals("")) {
				where += " and receivedate >= ?";
				Calendar cal = Calendar.getInstance();
				cal.setTime(statistics.getReceivedatestart());
				queryParamList.add(cal.getTime());
			} 
			if (statistics.getReceivedateend()!=null&&!statistics.getReceivedateend().equals("")) {
				where += " and receivedate < ?";
				Calendar cal = Calendar.getInstance();
				cal.setTime(statistics.getReceivedateend());
				queryParamList.add(cal.getTime());
			}
			if (!StringUtils.isEmpty(statistics.getEntrustcompany())) {
				where += " and entrustcompany in (";
				PageModel<EntrustCompany> ec = entrustCompanyDao.find(" where entrustcompany like '%"+statistics.getEntrustcompany()+"%'",null,null,1,Constants.MAX_COUNT);
                for(int i=0;i<ec.getList().size();i++){
                	where +=ec.getList().get(i).getId();
                	if(i+1!=ec.getList().size()){
                		where +=",";
                	}
                }
				where +=")";
			}
			if (!StringUtils.isEmpty(statistics.getTalent())) {
				where += " and talent = ?";
				queryParamList.add(statistics.getTalent());
			}
			if (!StringUtils.isEmpty(statistics.getCheckitem())) {
				PageModel<SendCheckItem> ci = sendCheckItemDao.find(null, null, null, 1, Constants.MAX_COUNT);
                int i = 0;
				if (ci!=null&&ci.getList().size()>=1){
					//where  += " and (";
					// 得到检验项目名称
					String tmp = "";
					for(SendCheckItem sr : ci.getList()){
						PageModel<CheckItem> iteems = checkItemDao.find(" where itemname like '%"+statistics.getCheckitem()+"%'",null,null,1,Constants.MAX_COUNT);
						if (iteems!=null&&iteems.getList().size()>=1&&Integer.parseInt(sr.getCheckitem())==iteems.getList().get(0).getId()){
							if (i==0)
							    tmp += " id="+sr.getSendsampleid();
							else
								tmp += " or id="+sr.getSendsampleid();
							i++;
						}
					}
					if (i>0)
					   where  += " and ( "+tmp+" ) ";
				}else{
					where +=" and 1=2 ";
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
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("entrustcompany", "asc");
		PageModel<SampleRegiste> page = sampleRegisteDao.find(where,queryParamList.toArray(new Object[0]), orderby, 1, Constants.MAX_COUNT);
		PageModel<Statistics> newpage = new PageModel<Statistics>();
		List<Statistics> list = new ArrayList<Statistics>();
		
		float checkmoney = 0,premoney=0,balancemoney=0;
		String cname = "";
		//送样数量
		int samples = 0;
		//完成数量
		int finished = 0;
		//发票开了数量
		int bills = 0;
		//快递送的数量
		int posts = 0;
		
		int count = 0;
		Statistics s= new Statistics();
		int len = page.getList().size();
		for(int i=0;i<len;i++){
			//如果是同一个单位
			if (i==0||cname.equals(page.getList().get(i).getEntrustcompany())){
				checkmoney = checkmoney+page.getList().get(i).getCheckmoney();
				premoney = premoney+page.getList().get(i).getPrepaymoney();
				balancemoney = balancemoney+page.getList().get(i).getBalancemoney();
				
				s.setEntrustcompany(page.getList().get(i).getEntrustcompany());
				//samples += sendSampleDao.getSendCount(page.getList().get(i).getId());

				finished += sendSampleDao.getSendFinishCount(page.getList().get(i).getId());
				
				cname = page.getList().get(i).getEntrustcompany();
				samples++;
				
				continue;
			}else{
				
				 bills = incomeDao.getBillCount(Integer.parseInt(cname));
				 posts = incomeDao.getPostCount(Integer.parseInt(cname));
				 EntrustCompany ec = entrustCompanyDao.get(Integer.parseInt(cname));
				 s.setEntrustcompany(ec.getEntrustcompany());
				 
				 s.setSamples(samples);
				 s.setCheckmoney(checkmoney);
				 s.setPremoney(premoney);
				 s.setBalmoney(balancemoney);
				 s.setFinished(finished);
				 s.setBills(bills);
				 s.setPosts(posts);
				 list.add(s);
				 s = null;
				 s= new Statistics();
				 samples = 0;
				 finished = 0;
				 bills =0;
				 posts =0;
				 checkmoney = page.getList().get(i).getCheckmoney();
				 premoney =page.getList().get(i).getPrepaymoney();
				 balancemoney = page.getList().get(i).getBalancemoney();
				// 获取派样次数
				 //samples = sendSampleDao.getSendCount(page.getList().get(i).getId());
				 samples =1;
				 finished = sendSampleDao.getSendFinishCount(page.getList().get(i).getId());
				 count++;
			}
			cname = page.getList().get(i).getEntrustcompany();
		}
		//增加最后一个单位
		if (len>=1){
			 EntrustCompany ec = entrustCompanyDao.get(Integer.parseInt(cname));
			 s.setEntrustcompany(ec.getEntrustcompany());
			 s.setSamples(samples);
			 s.setCheckmoney(checkmoney);
			 s.setPremoney(premoney);
			 s.setBalmoney(balancemoney);
			 s.setFinished(finished);
			 s.setBills(bills);
			 s.setPosts(posts);
			list.add(s);
		}
		newpage.setPageNo(1);
		newpage.setPageSize(Constants.MAX_COUNT);
		newpage.setTotalRecords(count);
		newpage.setList(list);
		return newpage;
	}
	@Override
	public PageModel<Statistics> getEmployeeStatistics(Statistics statistics, int pageNo, int pageSize) {
		String where = "where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();
		if(statistics != null) {
			if (statistics.getReceivedatestart()!=null&&!statistics.getReceivedatestart().equals("")) {
				where += " and receivedate >= ?";
				Calendar cal = Calendar.getInstance();
				cal.setTime(statistics.getReceivedatestart());
				queryParamList.add(cal.getTime());
			} 
			if (statistics.getReceivedateend()!=null&&!statistics.getReceivedateend().equals("")) {
				where += " and receivedate < ?";
				Calendar cal = Calendar.getInstance();
				cal.setTime(statistics.getReceivedateend());
				queryParamList.add(cal.getTime());
			}
			if (statistics.getExamineuser()!=null&&!statistics.getExamineuser().equals("0")) {
				where += " and examineuser = ?";
				queryParamList.add(Integer.parseInt(statistics.getExamineuser()));
			}
			if (!StringUtils.isEmpty(statistics.getEntrustcompany())) {
				where += " and sampleno in (";
				PageModel<EntrustCompany> ec = entrustCompanyDao.find(" where entrustcompany like '%"+statistics.getEntrustcompany()+"%'",null,null,1,Constants.MAX_COUNT);
                String tmp = "";
				for(int i=0;i<ec.getList().size();i++){
                	tmp +=ec.getList().get(i).getId();
                	if(i+1!=ec.getList().size()){
                		tmp +=",";
                	}
                }
				
				//获取登记单主键
				PageModel<SampleRegiste> sr = sampleRegisteDao.find(" where entrustcompany in ("+tmp+")",null,null,1,Constants.MAX_COUNT);
				for(int i=0;i<sr.getList().size();i++){
                	where +=sr.getList().get(i).getId();
                	if(i+1!=sr.getList().size()){
                		where +=",";
                	}
                }
				
				where +=")";
			}
			if (!StringUtils.isEmpty(statistics.getCheckitem())) {
				PageModel<SendCheckItem> ci = sendCheckItemDao.find(null, null, null, 1, Constants.MAX_COUNT);
                int i = 0;
				if (ci!=null&&ci.getList().size()>=1){
					//where  += " and (";
					// 得到检验项目名称
					String tmp = "";
					for(SendCheckItem sr : ci.getList()){
						PageModel<CheckItem> iteems = checkItemDao.find(" where itemname like '%"+statistics.getCheckitem()+"%'",null,null,1,Constants.MAX_COUNT);
						if (iteems!=null&&iteems.getList().size()>=1&&Integer.parseInt(sr.getCheckitem())==iteems.getList().get(0).getId()){
							if (i==0)
							    tmp += " id="+sr.getSendsampleid();
							else
								tmp += " or id="+sr.getSendsampleid();
							i++;
						}
					}
					if (i>0)
					   where  += " and ( "+tmp+" ) ";
				}else{
					where +=" and 1=2 ";
				}
			} 
		}
		//增加默认按科室计算的功能
		User quser = BusinessUtils.getCurrentUser();
		if(quser.getOffice()!=1){
		     where +=" and createuser in (";	
		     PageModel<User> pu = userDao.find(" where office="+quser.getOffice(), null,null,1,Constants.MAX_COUNT);
		     String tmp = "";
		     for(int x=0;x<pu.getList().size();x++){
		    	 tmp += pu.getList().get(x).getUid();
		    	 if(x+1!=pu.getList().size()){
		    		 tmp +=",";
		    	 }
		     }
		     where += tmp+")";
		}
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("examineuser", "asc");
		PageModel<SendSample> page = sendSampleDao.find(where,queryParamList.toArray(new Object[0]), orderby, 1, Constants.MAX_COUNT);
		PageModel<Statistics> newpage = new PageModel<Statistics>();
		List<Statistics> list = new ArrayList<Statistics>();
		Statistics s = new Statistics();
		int username = -1;
		//已经派样数量
		int sends = 0;
		//已经收样数量
		int receives = 0;
		//检验完成数量
		int finished = 0;
		//未完成数量
		int notfinished = 0;
		//按时完成数量
		int finishintime = 0;
		//超期完成数量
		int finishovertime = 0;
		int count = 0;
		int len = page.getList().size();
		for(int i=0;i<len;i++){
			if (page.getList().get(i).getExamineuser()==null) continue;
			//如果是同一个员工
			if (i==0||username==page.getList().get(i).getExamineuser()){
								
				//s.setExamineuser(page.getList().get(i).getExamineuser());
				sends ++;
				if (page.getList().get(i).getStatus().compareTo(Constants.STATUS_FINISHCHECK)>=0){
					finished ++;
					//是否按时完成
					if (page.getList().get(i).getFinishdate()!=null&&page.getList().get(i).getEnddate().getTime()>=page.getList().get(i).getFinishdate().getTime()){
						finishintime++;
					}else{
						finishovertime++;
					}
				}else{
					notfinished ++;
				}
				if (page.getList().get(i).getReceiveflag().equals("1")){
					receives ++;
				}
			}else{
				 User user = userDao.get(username);
				 s.setExamineuser(user.getShowname());
				 s.setSends(sends);
				 s.setFinished(finished);
				 s.setReceives(receives);
				 s.setNofinished(notfinished);
				 s.setFinishintime(finishintime);
				 s.setFinishovertime(finishovertime);
				 list.add(s);
				 s = null;
				 s= new Statistics();
				 finished =0;
				 finishintime=0;
				 finishovertime=0;
				 notfinished=0;
				 receives=0;
				 sends =1;
				if (page.getList().get(i).getStatus().compareTo(Constants.STATUS_FINISHCHECK)>=0){
					finished =1;
					//是否按时完成
					if (page.getList().get(i).getFinishdate()!=null&&page.getList().get(i).getEnddate().getTime()>=page.getList().get(i).getFinishdate().getTime()){
						finishintime=1;
					}else{
						finishovertime=1;
					}
				}else{
					notfinished =1;
				}
				if (page.getList().get(i).getReceiveflag().equals("1")){
					receives =1;
				}
				 count ++;
			}
			username = page.getList().get(i).getExamineuser();
		}
		if(len>=1){
			 User user = userDao.get(username);
			 s.setExamineuser(user.getShowname());
			 s.setSends(sends);
			 s.setFinished(finished);
			 s.setReceives(receives);
			 s.setNofinished(notfinished);
			 s.setFinishintime(finishintime);
			 s.setFinishovertime(finishovertime);
			list.add(s);
		}
		newpage.setPageNo(1);
		newpage.setPageSize(Constants.MAX_COUNT);
		newpage.setTotalRecords(count);
		newpage.setList(list);
		return newpage;
	}
}
