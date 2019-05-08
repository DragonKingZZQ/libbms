package com.zhirui.business.lib.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import cn.kepu.utils.PageModel;

import com.zhirui.business.common.Constants;
import com.zhirui.business.lib.bean.SubCheckReport;
import com.zhirui.business.lib.dao.CheckItemDao;
import com.zhirui.business.lib.dao.SubCheckReportDao;
import com.zhirui.business.lib.service.CheckItemService;
import com.zhirui.business.lib.service.SubCheckReportService;

@Repository("subCheckReportService")
public class SubCheckReportServiceImpl implements SubCheckReportService {
	
	private static final Log log = LogFactory.getLog(SubCheckReportServiceImpl.class);

	@Autowired
	private SubCheckReportDao subCheckReportDao;
	@Autowired
	private CheckItemDao checkItemDao;

	@Override
	public PageModel<SubCheckReport> getSubCheckReport(SubCheckReport subCheckReport, int pageNo, int pageSize) {
		PageModel<SubCheckReport> page = subCheckReportDao.find(null,null, null, pageNo, pageSize);
		return page;
	}
	
	@Override
	public SubCheckReport getSubCheckReport(int id){
		  return subCheckReportDao.get(id);
	}
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#removeExample(int)
	 */
	@Override
	public boolean removeSubCheckReport(int id) {
		try {
			//删除通用表数据
			subCheckReportDao.delete(id);
			return true;
		} catch (Exception e) {
			log.error("删除"+id+"失败", e);
			return false;
		}
	}

	@Override
	public SubCheckReport addSubCheckReport(SubCheckReport subCheckReport) {
		try {
			int id = (Integer)subCheckReportDao.save(subCheckReport);
			return subCheckReport;
		} catch (Exception e) {
			log.error("添加"+e.getMessage()+"失败", e);
			return null;
		}
	}
    
	@Override
	public boolean removeSubCheckReports(int[] ids) {
		for(int id : ids) {
			removeSubCheckReport(id);
		}
		return true;
	}
	@Override
	public List<SubCheckReport> getAllCheckItemByReport(Integer reportid){
		String where = "where checkreportid='"+reportid+"'";
		List<SubCheckReport> scr = subCheckReportDao.find(where,null, null, 1, Constants.MAX_COUNT).getList();
		return scr;
	}
}
