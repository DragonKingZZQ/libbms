package com.zhirui.business.lib.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.zhirui.business.lib.bean.CheckItem;
import com.zhirui.business.lib.bean.CheckReport;
import com.zhirui.business.lib.bean.GroupUser;
import com.zhirui.business.lib.bean.SendSample;
import com.zhirui.business.lib.bean.SubCheckReport;
import com.zhirui.business.lib.dao.CheckItemDao;
import com.zhirui.business.lib.dao.CheckReportDao;
import com.zhirui.business.lib.dao.GroupUserDao;
import com.zhirui.business.lib.dao.SubCheckReportDao;
import com.zhirui.business.lib.service.CheckReportService;
import com.zhirui.business.utils.BusinessUtils;

@Repository("checkReportService")
public class CheckReportServiceImpl implements CheckReportService {
	
	private static final Log log = LogFactory.getLog(CheckReportServiceImpl.class);

	@Autowired
	private CheckReportDao checkReportDao;
	@Autowired
	private SubCheckReportDao subCheckReportDao;
	@Autowired
	private CheckItemDao checkItemDao;
	@Autowired
	private GroupUserDao groupUserDao;

	@Override
	public PageModel<CheckReport> getCheckReport(CheckReport checkReport, int pageNo, int pageSize) {
		String where = "where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();
		if(checkReport != null) {
			if (!StringUtils.isEmpty(checkReport.getSampleno())) {
				where += " and sampleno like ?";
				queryParamList.add("%"+checkReport.getSampleno()+"%");
			}
			if (!StringUtils.isEmpty(checkReport.getSamplename())) {
				where += " and samplename like ?";
				queryParamList.add("%"+checkReport.getSamplename()+"%");
			}
			if (checkReport.getReceivedate()!=null) {
				where += " and receivedate = ?";
				Calendar cal = Calendar.getInstance();
				cal.setTime(checkReport.getReceivedate());
				queryParamList.add(cal.getTime());
			} 
			if (checkReport.getFinishdate()!=null) {
				where += " and finishdate = ?";
				Calendar cal = Calendar.getInstance();
				cal.setTime(checkReport.getFinishdate());
				queryParamList.add(cal.getTime());
			} 
			if (!StringUtils.isEmpty(checkReport.getEntrustcompany())) {
				where += " and entrustcompany like ?";
				queryParamList.add("%"+checkReport.getEntrustcompany()+"%");
			}
			if (!StringUtils.isEmpty(checkReport.getCheckitem())) {
				PageModel<SubCheckReport> ci = subCheckReportDao.find(null, null, null, 1, Constants.MAX_COUNT);
                int i = 0;
				if (ci!=null&&ci.getList().size()>=1){
					//where  += " and (";
					// 得到检验项目名称
					String tmp = "";
					for(SubCheckReport sr : ci.getList()){
						PageModel<CheckItem> iteems = checkItemDao.find(" where itemname like '%"+checkReport.getCheckitem()+"%'",null,null,1,Constants.MAX_COUNT);
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
		// 增加权限判断
		if (BusinessUtils.getCurrentUser().getAuthority().indexOf(Constants.RIGHT_MEMBER)>=0){
			where += " and createuser = ? ";
			queryParamList.add(BusinessUtils.getCurrentUser().getUid());
		}
		if (BusinessUtils.getCurrentUser().getAuthority().indexOf(Constants.RIGHT_MEMBERLEADER)>=0){
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
		}
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		PageModel<CheckReport> page = checkReportDao.find(where,queryParamList.toArray(new Object[0]), orderby, pageNo, pageSize);
		return page;
	}
	@Override
	public PageModel<CheckReport> getMyCheckReport(int pageNo, int pageSize) {
		String where = "where 1=1";
		where += " and finishdate<>null ";

		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("finishdate", "desc");
		PageModel<CheckReport> page = checkReportDao.find(where, null, orderby, pageNo, pageSize);
		return page;
	}
	@Override
	public CheckReport getCheckReport(int id){
		  return checkReportDao.get(id);
	}
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#removeExample(int)
	 */
	@Override
	public boolean removeCheckReport(int id) {
		try {
			//删除通用表数据
			checkReportDao.delete(id);
			return true;
		} catch (Exception e) {
			log.error("删除"+id+"失败", e);
			return false;
		}
	}

	@Override
	public CheckReport addCheckReport(CheckReport checkReport) {
		try {
			int id = (Integer)checkReportDao.save(checkReport);
			return checkReport;
		} catch (Exception e) {
			log.error("添加"+e.getMessage()+"失败", e);
			return null;
		}
	}
    
	@Override
	public boolean removeCheckReports(int[] ids) {
		for(int id : ids) {
			removeCheckReport(id);
		}
		return true;
	}

	@Override
	public CheckReport updateCheckReport(CheckReport checkReport) {
		try {
			checkReportDao.update(checkReport);
		} catch (Exception e) {
			log.error("更新"+checkReport.getId()+"失败", e);
			return null;
		}
		return checkReport;
	}
}
