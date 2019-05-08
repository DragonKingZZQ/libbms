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

import com.zhirui.business.lib.bean.Groups;
import com.zhirui.business.lib.dao.GroupsDao;
import com.zhirui.business.lib.service.GroupsService;

@Repository("groupsService")
public class GroupsServiceImpl implements GroupsService {
	
	private static final Log log = LogFactory.getLog(GroupsServiceImpl.class);

	@Autowired
	private GroupsDao groupsDao;

	@Override
	public PageModel<Groups> getGroups(Groups groups, int pageNo, int pageSize) {
		String where = "where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();
		if(groups != null) {
			if (!StringUtils.isEmpty(groups.getGroupname())) {
				where += " and groupname like ?";
				queryParamList.add("%"+groups.getGroupname()+"%");
			}
			if (!StringUtils.isEmpty(groups.getPrivillage())) {
				where += " and privillage like ?";
				queryParamList.add("%"+groups.getPrivillage()+"%");
			}
		}
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		PageModel<Groups> page = groupsDao.find(where, queryParamList.toArray(new Object[0]), orderby, pageNo, pageSize);
		return page;
	}
	
	@Override
	public Groups getGroups(int id){
		  return groupsDao.get(id);
	}
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#removeExample(int)
	 */
	@Override
	public boolean removeGroups(int id) {
		try {
			//删除通用表数据
			groupsDao.delete(id);
			return true;
		} catch (Exception e) {
			log.error("删除"+id+"失败", e);
			return false;
		}
	}

	@Override
	public Groups addGroups(Groups groups) {
		try {
			int id = (Integer)groupsDao.save(groups);
			return groups;
		} catch (Exception e) {
			log.error("添加"+e.getMessage()+"失败", e);
			return null;
		}
	}

	@Override
	public Groups updateGroups(Groups groups) {
		try {
			groupsDao.update(groups);
		} catch (Exception e) {
			log.error("更新"+groups.getId()+"失败", e);
			return null;
		}
		return groups;
	}

}
