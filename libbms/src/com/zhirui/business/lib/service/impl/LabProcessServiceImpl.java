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
import com.zhirui.business.lib.bean.Instrument;
import com.zhirui.business.lib.bean.LabProcess;
import com.zhirui.business.lib.dao.LabProcessDao;
import com.zhirui.business.lib.service.LabProcessService;


@Repository("labProcessServiceImpl")
public class LabProcessServiceImpl implements LabProcessService {

	private static final Log log = LogFactory.getLog(CheckItemServiceImpl.class);

	@Autowired
	private LabProcessDao labProcessDao;
	
	
	@Override
	public PageModel<LabProcess> getLabProcess(LabProcess labProcess,int pageNo, int pageSize) {
		String where = "where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();
		if(labProcess != null) {
			if (!StringUtils.isEmpty(labProcess.getLabprocesstitle())) {
				where += " and itemname like ?";
				queryParamList.add("%"+labProcess.getLabprocesstitle()+"%");
			}
		}
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		PageModel<LabProcess> page = labProcessDao.find(where, queryParamList.toArray(new Object[0]), orderby, pageNo, pageSize);
		return page;
	}
	

	
	
	@Override
	public Map<Integer,String> getAllTitles() {
		String where = "where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();
		Map<Integer, String> ins = new HashMap<Integer, String>();
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("id", " asc ");
		PageModel<LabProcess> page = labProcessDao.find(where, queryParamList.toArray(new Object[0]), orderby, 1, Constants.MAX_COUNT);
		if (page != null) {
			for(LabProcess ec : page.getList()){
				ins.put(ec.getId(),ec.getLabprocesstitle());
			}
			return ins;
		}
		return null;
	}
	
	@Override
	public LabProcess getLabProcess(int id) {
		 return labProcessDao.get(id);
	}

	@Override
	public boolean removeLabProcess(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeLabProcess(int[] id) {
		try {
			//删除通用表数据
			labProcessDao.delete(id);
			return true;
		} catch (Exception e) {
			log.error("删除"+id+"失败", e);
			return false;
		}
	}

	@Override
	public LabProcess addLabProcess(LabProcess labProcess) {
		try {
			int id = (Integer)labProcessDao.save(labProcess);
			return labProcess;
		} catch (Exception e) {
			log.error("添加"+e.getMessage()+"失败", e);
			return null;
		}
	}

	@Override
	public LabProcess updateLabProcess(LabProcess labProcess) {
		try {
			labProcessDao.update(labProcess);
		} catch (Exception e) {
			log.error("更新"+labProcess.getId()+"失败", e);
			return null;
		}
		return labProcess;
	}

	@Override
	public Map<Integer, String> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, String> getAllValid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int isExists(Integer cid, String labprocesstitle) {
		String where = "";
		if (cid==-1)
		   where = " where labprocesstitle='"+labprocesstitle+"'";
		else
			where = " where labprocesstitle='"+labprocesstitle+"' and id<>"+cid;
		PageModel<LabProcess> page = labProcessDao.find(1,Constants.MAX_COUNT,where,null);
		if (page.getTotalRecords()>=1) return page.getList().get(0).getId();
		return -1;
	}

}
