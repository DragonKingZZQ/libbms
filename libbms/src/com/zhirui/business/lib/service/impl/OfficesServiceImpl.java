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
import com.zhirui.business.lib.bean.Instrument;
import com.zhirui.business.lib.bean.Offices;
import com.zhirui.business.lib.dao.CheckItemDao;
import com.zhirui.business.lib.dao.InstrumentDao;
import com.zhirui.business.lib.dao.OfficesDao;
import com.zhirui.business.lib.service.InstrumentService;
import com.zhirui.business.lib.service.OfficesService;

@Repository("officesService")
public class OfficesServiceImpl implements OfficesService {
	
	private static final Log log = LogFactory.getLog(OfficesServiceImpl.class);

	@Autowired
	private OfficesDao officesDao;
	@Autowired
	private CheckItemDao checkItemDao;

	@Override
	public PageModel<Offices> getOffices(Offices offices, int pageNo, int pageSize) {
		String where = "where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();
		if(offices != null) {
			if (!StringUtils.isEmpty(offices.getName())) {
				where += " and name like ?";
				queryParamList.add("%"+offices.getName()+"%");
			}
		}
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		PageModel<Offices> page = officesDao.find(where, queryParamList.toArray(new Object[0]), orderby, pageNo, pageSize);
		return page;
	}
	
	@Override
	public Offices getOffices(int id){
		  return officesDao.get(id);
	}
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#removeExample(int)
	 */
	@Override
	public boolean removeOffices(int id) {
		try {
			//删除通用表数据
			officesDao.delete(id);
			return true;
		} catch (Exception e) {
			log.error("删除"+id+"失败", e);
			return false;
		}
	}

	@Override
	public Offices addOffices(Offices offices) {
		try {
			int id = (Integer)officesDao.save(offices);
			return offices;
		} catch (Exception e) {
			log.error("添加"+e.getMessage()+"失败", e);
			return null;
		}
	}
    
	@Override
	public boolean removeOfficess(int[] ids) {
		for(int id : ids) {
			removeOffices(id);
		}
		return true;
	}

	@Override
	public Offices updateOffices(Offices offices) {
		try {
			officesDao.update(offices);
		} catch (Exception e) {
			log.error("更新"+offices.getId()+"失败", e);
			return null;
		}
		return offices;
	}
}
