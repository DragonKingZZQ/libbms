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
import com.zhirui.business.lib.bean.RelationUser;
import com.zhirui.business.lib.dao.RelationUserDao;
import com.zhirui.business.lib.service.RelationUserService;

@Repository("relationUserService")
public class RelationUserServiceImpl implements RelationUserService {
	
	private static final Log log = LogFactory.getLog(RelationUserServiceImpl.class);

	@Autowired
	private RelationUserDao relationUserDao;
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#getExamples(com.zhirui.business.bean.Example, int, int)
	 */
	@Override
	public PageModel<RelationUser> getRelationUser(RelationUser relationUser, int pageNo, int pageSize) {
		String where = "where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();
		if(relationUser != null) {
			if (relationUser.getEntrustcompanyid()!=null) {
				where += " and entrustcompanyid = ?";
				queryParamList.add(relationUser.getEntrustcompanyid());
			}
			if (!StringUtils.isEmpty(relationUser.getAddress())) {
				where += " and address like ?";
				queryParamList.add("%"+relationUser.getAddress()+"%");
			}
			if (!StringUtils.isEmpty(relationUser.getSenduser())) {
				where += " and senduser = ?";
				queryParamList.add(relationUser.getSenduser());
			}
			if (!StringUtils.isEmpty(relationUser.getType())) {
				where += " and type = ?";
				queryParamList.add(relationUser.getType());
			}
		}
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		PageModel<RelationUser> page = relationUserDao.find(where, queryParamList.toArray(new Object[0]), orderby, pageNo, pageSize);
		return page;
	}
	
	@Override
	public RelationUser getRelationUser(int id){
		  return relationUserDao.get(id);
	}
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#removeExample(int)
	 */
	@Override
	public boolean removeRelationUser(int id) {
		try {
			//删除通用表数据
			relationUserDao.delete(id);
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
	public RelationUser addRelationUser(RelationUser relationUser) {
		try {
			int id = (Integer)relationUserDao.save(relationUser);
			relationUser = relationUserDao.get(id);
			return relationUser;
		} catch (Exception e) {
			log.error("添加"+e.getMessage()+"失败", e);
			return null;
		}
	}
    
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#updateExample(com.zhirui.business.bean.Example)
	 */
	@Override
	public RelationUser updateRelationUser(RelationUser relationUser) {
		try {
			relationUserDao.update(relationUser);
		} catch (Exception e) {
			log.error("更新"+relationUser.getId()+"失败", e);
			return null;
		}
		return relationUser;
	}
	@Override
	public boolean removeRelationUsers(int[] ids) {
		for(int id : ids) {
			removeRelationUser(id);
		}
		return true;
	}
	@Override
	public Map<Integer,String> getAll() {
		try{
		Map<Integer, String> users = new HashMap<Integer, String>();
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("id", "asc");
		orderby.get("id");
		
		PageModel<RelationUser> page = relationUserDao.find(null, null, orderby, 1, Constants.MAX_COUNT);
		
		if (page != null) {
			for(RelationUser ec : page.getList()){
				users.put(ec.getId(),ec.getSenduser());
			}
			return users;
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
