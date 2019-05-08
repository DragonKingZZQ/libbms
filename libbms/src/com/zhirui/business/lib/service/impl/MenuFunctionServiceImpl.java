package com.zhirui.business.lib.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import cn.kepu.utils.PageModel;
import cn.kepu.utils.StringUtils;

import com.zhirui.business.lib.bean.MenuFunction;
import com.zhirui.business.lib.dao.MenuFunctionDao;
import com.zhirui.business.lib.service.MenuFunctionService;

@Repository("menuFunctionService")
public class MenuFunctionServiceImpl implements MenuFunctionService {
	
	private static final Log log = LogFactory.getLog(MenuFunctionServiceImpl.class);

	@Autowired
	private MenuFunctionDao menuFunctionDao;

	@Override
	public PageModel<MenuFunction> getMenuFunction(MenuFunction menuFunction, int pageNo, int pageSize) {
		String where = "where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();
		if(menuFunction != null) {
			if (!StringUtils.isEmpty(menuFunction.getCode())) {
				where += " and code like ?";
				queryParamList.add("%"+menuFunction.getCode()+"%");
			}
			if (!StringUtils.isEmpty(menuFunction.getMenuname())) {
				where += " and menuname like ?";
				queryParamList.add("%"+menuFunction.getMenuname()+"%");
			}
			if (!StringUtils.isEmpty(menuFunction.getUrl())) {
				where += " and url like ?";
				queryParamList.add("%"+menuFunction.getUrl()+"%");
			}
			if (!StringUtils.isEmpty(menuFunction.getIcon())) {
				where += " and icon like ?";
				queryParamList.add("%"+menuFunction.getIcon()+"%");
			}
			if (!StringUtils.isEmpty(menuFunction.getOrder())) {
				where += " and order = ?";
				queryParamList.add(menuFunction.getOrder());
			}
			if (!StringUtils.isEmpty(menuFunction.getRemark())) {
				where += " and remark like ?";
				queryParamList.add("%"+menuFunction.getRemark()+"%");
			}
		}
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("order", "asc");
		PageModel<MenuFunction> page = menuFunctionDao.find(where, queryParamList.toArray(new Object[0]), orderby, pageNo, pageSize);
		return page;
	}
	
	@Override
	public MenuFunction getMenuFunction(int id){
		  return menuFunctionDao.get(id);
	}
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#removeExample(int)
	 */
	@Override
	public boolean removeMenuFunction(int id) {
		try {
			//删除通用表数据
			menuFunctionDao.delete(id);
			return true;
		} catch (Exception e) {
			log.error("删除"+id+"失败", e);
			return false;
		}
	}

	@Override
	public MenuFunction addMenuFunction(MenuFunction menuFunction) {
		try {
			int id = (Integer)menuFunctionDao.save(menuFunction);
			return menuFunction;
		} catch (Exception e) {
			log.error("添加"+e.getMessage()+"失败", e);
			return null;
		}
	}

	@Override
	public MenuFunction updateMenuFunction(MenuFunction menuFunction) {
		try {
			menuFunctionDao.update(menuFunction);
		} catch (Exception e) {
			log.error("更新"+menuFunction.getId()+"失败", e);
			return null;
		}
		return menuFunction;
	}

}
