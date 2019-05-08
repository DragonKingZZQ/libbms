package com.zhirui.business.lib.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.zhirui.business.lib.bean.CheckStandard;
import com.zhirui.business.lib.bean.EntrustCompany;
import com.zhirui.business.lib.dao.CheckStandardDao;
import com.zhirui.business.lib.service.CheckStandardService;

@Repository("CheckStandardService")
public class CheckStandardServiceImpl implements CheckStandardService {
	
	private static final Log log = LogFactory.getLog(CheckStandardServiceImpl.class);
	@Autowired
	private CheckStandardDao checkStandardDao;

	@Override
	public PageModel<CheckStandard> getCheckStandard(CheckStandard checkStandard, int pageNo, int pageSize) {
		String where = "where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();
		if(checkStandard != null){
			if (!StringUtils.isEmpty(checkStandard.getStandardcode())){
				where += " and standardcode like ?";
				queryParamList.add("%"+checkStandard.getStandardcode()+"%");
			}
		}
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		PageModel<CheckStandard> page = checkStandardDao.find(where, queryParamList.toArray(new Object[0]), orderby, pageNo, pageSize);
		return page;
	}

	@Override
	public CheckStandard getCheckStandard(int id) {
		return checkStandardDao.get(id);
	}

	@Override
	public boolean removeCheckStandard(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeCheckStandards(int[] id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CheckStandard addCheckStandard(CheckStandard checkStandard) {
		try {
			int id = (Integer)checkStandardDao.save(checkStandard);
			return checkStandard;
		} catch (Exception e) {
			log.error("添加"+e.getMessage()+"失败", e);
			return null;
		}
	}

	@Override
	public CheckStandard updateCheckStandard(CheckStandard checkStandard) {
		try {
			checkStandardDao.update(checkStandard);
		} catch (Exception e) {
			log.error("更新"+checkStandard.getId()+"失败", e);
			return null;
		}
		return checkStandard;
	}

	@Override
	public Map<Integer, String> getAll() {
		Map<Integer, String> checkstandard = new HashMap<Integer, String>();
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("id", "asc");
		PageModel<CheckStandard> page = checkStandardDao.find(null, null, orderby, 1, Constants.MAX_COUNT);
		if (page != null) {
			for(CheckStandard ec : page.getList()){
				checkstandard.put(ec.getId(),ec.getStandardcode());
			}
			return checkstandard;
		}
		return null;
	}

	@Override
	public Map<Integer, String> getAllValid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int isExists(Integer cid, String standardcode) {
		String where = "";
		if (cid==-1)
		   where = " where standardcode='"+standardcode+"'";
		else
			where = " where standardcode='"+standardcode+"' and id<>"+cid;
		PageModel<CheckStandard> page = checkStandardDao.find(1,Constants.MAX_COUNT,where,null);
		if (page.getTotalRecords()>=1) return page.getList().get(0).getId();
		return -1;
	}

	
}
