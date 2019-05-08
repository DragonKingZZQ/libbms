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
import com.zhirui.business.lib.bean.SendCheckItem;
import com.zhirui.business.lib.dao.CheckItemDao;
import com.zhirui.business.lib.service.CheckItemService;

@Repository("checkItemService")
public class CheckItemServiceImpl implements CheckItemService {
	
	private static final Log log = LogFactory.getLog(CheckItemServiceImpl.class);

	@Autowired
	private CheckItemDao checkItemDao;
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#getExamples(com.zhirui.business.bean.Example, int, int)
	 */
	@Override
	public PageModel<CheckItem> getCheckItem(CheckItem checkItem, int pageNo, int pageSize) {
		String where = "where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();
		if(checkItem != null) {
			if (!StringUtils.isEmpty(checkItem.getItemname())) {
				where += " and itemname like ?";
				queryParamList.add("%"+checkItem.getItemname()+"%");
			}
		}
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		PageModel<CheckItem> page = checkItemDao.find(where, queryParamList.toArray(new Object[0]), orderby, pageNo, pageSize);
		return page;
	}
	
	@Override
	public CheckItem getCheckItem(int id){
		  return checkItemDao.get(id);
	}
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#removeExample(int)
	 */
	
	
	@Override
	public boolean removeCheckItem(int id) {
		try {
			//删除通用表数据
			checkItemDao.delete(id);
			return true;
		} catch (Exception e) {
			log.error("删除"+id+"失败", e);
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#addExample(com.zhirui.business.bean.Example)
	 */
	@Override
	public CheckItem addCheckItem(CheckItem checkItem) {
		try {
			int id = (Integer)checkItemDao.save(checkItem);
			return checkItem;
		} catch (Exception e) {
			log.error("添加"+e.getMessage()+"失败", e);
			return null;
		}
	}
    
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#updateExample(com.zhirui.business.bean.Example)
	 */
	@Override
	public CheckItem updateCheckItem(CheckItem checkItem) {
		try {
			checkItemDao.update(checkItem);
		} catch (Exception e) {
			log.error("更新"+checkItem.getId()+"失败", e);
			return null;
		}
		return checkItem;
	}
	@Override
	public boolean removeCheckItems(int[] ids) {
		for(int id : ids) {
			removeCheckItem(id);
		}
		return true;
	}
	@Override
	public Map<Integer,String> getAll() {
		Map<Integer, String> items = new HashMap<Integer, String>();
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("id", "asc");
		PageModel<CheckItem> page = checkItemDao.find(null, null, orderby, 1, Constants.MAX_COUNT);
		if (page != null) {
			for(CheckItem ec : page.getList()){
				items.put(ec.getId(),ec.getItemname());
			}
			return items;
		}
		return null;
	}
	@Override
	public Map<Integer,String> getAllValid() {
		Map<Integer, String> items = new HashMap<Integer, String>();
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("id", "asc");
		PageModel<CheckItem> page = checkItemDao.find(" where validflag=1 ", null, orderby, 1, Constants.MAX_COUNT);
		if (page != null) {
			for(CheckItem ec : page.getList()){
				items.put(ec.getId(),ec.getItemname());
			}
			return items;
		}
		return null;
	}
	@Override
	public int isExists(Integer cid,String name) {
		String where = "";
		if (cid==-1)
		   where = " where itemname='"+name+"'";
		else
			where = " where itemname='"+name+"' and id<>"+cid;
		PageModel<CheckItem> page = checkItemDao.find(1,Constants.MAX_COUNT,where,null);
		if (page.getTotalRecords()>=1) return page.getList().get(0).getId();
		return -1;
	}
	
}
