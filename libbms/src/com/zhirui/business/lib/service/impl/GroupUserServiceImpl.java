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

import com.zhirui.business.lib.bean.GroupUser;
import com.zhirui.business.lib.dao.GroupUserDao;
import com.zhirui.business.lib.service.GroupUserService;

@Repository("groupUserService")
public class GroupUserServiceImpl implements GroupUserService {
	
	private static final Log log = LogFactory.getLog(GroupsServiceImpl.class);

	@Autowired
	private GroupUserDao groupUserDao;

	@Override
	public PageModel<GroupUser> getGroupUser(GroupUser groupUser, int pageNo, int pageSize) {
		String where = "where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();
		if(groupUser != null) {
			if (!StringUtils.isEmpty(groupUser.getGroupid())) {
				where += " and groupid = ?";
				queryParamList.add(groupUser.getGroupid());
			}
			if (!StringUtils.isEmpty(groupUser.getUserid())) {
				where += " and userid = ?";
				queryParamList.add(groupUser.getUserid());
			}
		}
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		PageModel<GroupUser> page = groupUserDao.find(where, queryParamList.toArray(new Object[0]), orderby, pageNo, pageSize);
		return page;
	}
	
	@Override
	public GroupUser getGroupUser(int id){
		  return groupUserDao.get(id);
	}
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#removeExample(int)
	 */
	@Override
	public boolean removeGroupUser(int id) {
		try {
			//删除通用表数据
			groupUserDao.delete(id);
			return true;
		} catch (Exception e) {
			log.error("删除"+id+"失败", e);
			return false;
		}
	}

	@Override
	public GroupUser addGroupUser(GroupUser groupUser) {
		try {
			int id = (Integer)groupUserDao.save(groupUser);
			return groupUser;
		} catch (Exception e) {
			log.error("添加"+e.getMessage()+"失败", e);
			return null;
		}
	}

	@Override
	public GroupUser updateGroupUser(GroupUser groupUser) {
		try {
			groupUserDao.update(groupUser);
		} catch (Exception e) {
			log.error("更新"+groupUser.getId()+"失败", e);
			return null;
		}
		return groupUser;
	}

}
