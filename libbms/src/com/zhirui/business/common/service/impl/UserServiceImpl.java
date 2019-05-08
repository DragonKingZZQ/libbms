package com.zhirui.business.common.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhirui.business.common.Constants;
import com.zhirui.business.common.bean.User;
import com.zhirui.business.common.dao.UserDao;
import com.zhirui.business.common.service.UserService;
import com.zhirui.business.lib.bean.CheckItem;
import com.zhirui.business.lib.bean.CheckReport;
import com.zhirui.business.lib.bean.SampleRegiste;
import com.zhirui.business.lib.bean.SubCheckReport;
import com.zhirui.business.utils.BusinessUtils;

import cn.kepu.base.MessageType;
import cn.kepu.utils.ContextUtils;
import cn.kepu.utils.PageModel;
import cn.kepu.utils.StringUtils;

/**
 * 用户相关业务处理
 * @author zhangzl
 */
@Repository("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	public UserDao userDao;
	@Override
	public List<User> getExamineUser() {
		Map<String, String> orderby = new HashMap<String, String>();
		String where = "where authority=? or authority like ?";
		Object[] queryParams = new Object[] { "C" ,"%|C|%"};
		orderby.put("id", "asc");
		PageModel<User> page = userDao.find(where, queryParams, orderby, 1, 100);
		if (page != null) {
			return page.getList();
		}
		return null;
	}
	@Override
	public User getUser(int userId) {
		return userDao.get(userId);
	}

	@Override
	public User getUserByEmail(String email) {
		String where = "where email=?";
		Object[] queryParams = new Object[] { email };
		PageModel<User> page = userDao.find(where, queryParams, null, 1, 1);
		if (!PageModel.isEmpty(page)) {
			return page.getList().get(0);
		}
		return null;
	}

	@Override
	public User getUserByName(String name) {
		String where = "where name=?";
		Object[] queryParams = new Object[] { name };
		PageModel<User> page = userDao.find(where, queryParams, null, 1, 1);
		if (!PageModel.isEmpty(page)) {
			return page.getList().get(0);
		}
		return null;
	}

	@Override
	public User checkUser(User user) {
		if(user == null) {
			ContextUtils.setOpMessage(MessageType.ERROR, "请输入用户名和密码");
			return null;
		}
		User dbuser = null;
		//if(!StringUtils.isEmpty(user.getName()) && user.getName().indexOf("@") < 0) {
			dbuser = getUserByName(user.getName());
		/*} else if(!StringUtils.isEmpty(user.getName()) && user.getName().indexOf("@") > 0) {
			dbuser = getUserByEmail(user.getName());
		} else {
			ContextUtils.setOpMessage(MessageType.ERROR, "请输入正确的用户名或电子邮箱地址");
			return null;
		}*/
		if(dbuser == null || !BusinessUtils.getEncodePassword(user.getPassword()).equals(dbuser.getPassword())) {
			ContextUtils.setOpMessage(MessageType.ERROR, "用户名或密码错误，用户登录失败");
			return null;
		}
		return dbuser;
	}

	@Override
	public User updateLoginInfo(User dbuser, String newip) {
		// Store user information
		BusinessUtils.setUserLastInfo((User) dbuser.clone());
		// Update ip address and access_token
		dbuser.setLastip(newip);
		dbuser.setLastlogin(BusinessUtils.getRequestTime());
		dbuser.setLoginTimes(dbuser.getLoginTimes() + 1);
		userDao.update(dbuser);
		return dbuser;
	}

	@Override
	public User updateSecurity(User user, String password) {
		User dbuser = getUser(user.getUid());
		if(dbuser == null || !BusinessUtils.getEncodePassword(user.getPassword()).equals(dbuser.getPassword())) {
			ContextUtils.setOpMessage(MessageType.ERROR, "用户的原密码输入错误，密码修改失败");
			return null;
		}
		dbuser.setPassword(BusinessUtils.getEncodePassword(password));
		userDao.update(dbuser);
		return dbuser;
	}

	@Override
	public User updateInfo(User user) {
		User dbuser = getUser(user.getUid());
		dbuser.setShowname(user.getShowname());
		dbuser.setTelphone(user.getTelphone());
		dbuser.setAddress(user.getAddress());
		userDao.update(dbuser);
		return dbuser;
	}
	@Override
	public User addUser(User user) {
		try {
			int id = (Integer)userDao.save(user);
			return user;
		} catch (Exception e) {
			return null;
		}
	}
	@Override
	public PageModel<User> getUser(User user, int pageNo, int pageSize) {
		String where = "where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();
		if(user != null) {
			if (!StringUtils.isEmpty(user.getShowname())) {
				where += " and showname like ?";
				queryParamList.add("%"+user.getShowname()+"%");
			}
			if (!StringUtils.isEmpty(user.getName())) {
				where += " and name like ?";
				queryParamList.add("%"+user.getName()+"%");
			}
			if (!StringUtils.isEmpty(user.getTelphone())) {
				where += " and telphone like ?";
				queryParamList.add("%"+user.getTelphone()+"%");
			}
		}
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("uid", "desc");
		PageModel<User> page = userDao.find(where,queryParamList.toArray(new Object[0]), orderby, pageNo, pageSize);
		return page;
	}
	@Override
	public boolean removeUser(int id) {
		try {
			//删除通用表数据
			userDao.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public User updateUser(User user) {
		try {
			userDao.update(user);
		} catch (Exception e) {
			return null;
		}
		return user;
	}
	
	/**
	 * 查询数据是否已经存在
	 * (按所有还是按年度季度)
	 * @param 
	 * @return
	 */
	@Override
	public boolean isExists(Integer userid,String  username){
		// 新增和修改调用不同的方法
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("uid", "desc");
		if (userid==-1){
			String where = "";
			List<Object> queryParamList = new ArrayList<Object>();
			where =" where name=?";
			queryParamList.add(username);
			PageModel<User> ap = userDao.find(where, queryParamList.toArray(new Object[0]), orderby, 1, 1);
			
			if (ap==null||ap.getList().size()==0) return false;
			
			return true;
			//return basedataDao.checkExist(academyPeople.getAuthorcorporation(), academyPeople.getName(), "pb_academypeople");
		}
		else{
			String where = "";
			List<Object> queryParamList = new ArrayList<Object>();
			where =" where name=? and uid<>?";
			queryParamList.add(username);
			queryParamList.add(userid);
			PageModel<User> ap = userDao.find(where, queryParamList.toArray(new Object[0]), orderby, 1, 1);
			
			if (ap==null||ap.getList().size()==0) return false;
			
			return true;
		}
	}
}
