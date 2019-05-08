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
import com.zhirui.business.lib.dao.InstrumentDao;
import com.zhirui.business.lib.service.InstrumentService;

@Repository("instrumentService")
public class InstrumentServiceImpl implements InstrumentService {
	
	private static final Log log = LogFactory.getLog(InstrumentServiceImpl.class);

	@Autowired
	private InstrumentDao instrumentDao;

	@Override
	public PageModel<Instrument> getInstrument(Instrument instrument, int pageNo, int pageSize) {
		String where = "where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();
		if(instrument != null) {
			if (!StringUtils.isEmpty(instrument.getInsname())) {
				where += " and name like ?";
				queryParamList.add("%"+instrument.getInsname()+"%");
			}
			if (!StringUtils.isEmpty(instrument.getProduct())) {
				where += " and product like ?";
				queryParamList.add("%"+instrument.getProduct()+"%");
			}
		}
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		PageModel<Instrument> page = instrumentDao.find(where, queryParamList.toArray(new Object[0]), orderby, pageNo, pageSize);
		return page;
	}
	
	@Override
	public Instrument getInstrument(int id){
		  return instrumentDao.get(id);
	}
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#removeExample(int)
	 */
	@Override
	public boolean removeInstrument(int id) {
		try {
			//删除通用表数据
			instrumentDao.delete(id);
			return true;
		} catch (Exception e) {
			log.error("删除"+id+"失败", e);
			return false;
		}
	}

	@Override
	public Instrument addInstrument(Instrument instrument) {
		try {
			int id = (Integer)instrumentDao.save(instrument);
			return instrument;
		} catch (Exception e) {
			log.error("添加"+e.getMessage()+"失败", e);
			return null;
		}
	}
    
	@Override
	public boolean removeInstruments(int[] ids) {
		for(int id : ids) {
			removeInstrument(id);
		}
		return true;
	}

	@Override
	public Instrument updateInstrument(Instrument instrument) {
		try {
			instrumentDao.update(instrument);
		} catch (Exception e) {
			log.error("更新"+instrument.getId()+"失败", e);
			return null;
		}
		return instrument;
	}
	
	@Override
	public Map<Integer,String> getAll() {
		String where = "where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();
		Map<Integer, String> ins = new HashMap<Integer, String>();
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("id", " asc ");
		PageModel<Instrument> page = instrumentDao.find(where, queryParamList.toArray(new Object[0]), orderby, 1, Constants.MAX_COUNT);
		if (page != null) {
			for(Instrument ec : page.getList()){
				ins.put(ec.getId(),ec.getCodename());
			}
			return ins;
		}
		return null;
	}
	@Override
	public Map<Integer,String> getAllValid() {
		Map<Integer, String> ins = new HashMap<Integer, String>();
		List<Object> queryParamList = new ArrayList<Object>();
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("id", "asc");
		PageModel<Instrument> page = instrumentDao.find(" where validflag=1 ", queryParamList.toArray(new Object[0]), orderby, 1, Constants.MAX_COUNT);
		if (page != null) {
			for(Instrument ec : page.getList()){
				ins.put(ec.getId(),ec.getCodename());
			}
			return ins;
		}
		return null;
	}
	//是否存在，存在返回true
	public boolean isExists(Integer iid,String name){
		String where = "";
		if (iid==-1)
			where = " where name='"+name+"'";
		else
			where = " where name='"+name+"' and id<>"+iid;
		PageModel<Instrument> page = instrumentDao.find(1,Constants.MAX_COUNT,where,null);
		if (page.getTotalRecords()>=1) return true;
		return false;
	}
}
