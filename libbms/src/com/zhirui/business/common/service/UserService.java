package com.zhirui.business.common.service;

import java.util.List;

import cn.kepu.utils.PageModel;

import com.zhirui.business.common.bean.User;
import com.zhirui.business.lib.bean.CheckReport;
import com.zhirui.business.lib.bean.SampleRegiste;

/**
 * 用户相关业务处理
 * @author zhangzl
 */
public interface UserService {

	public List<User> getExamineUser();
	
	public User getUser(int userId);

	public User getUserByEmail(String email);

	public User getUserByName(String name);

	public User checkUser(User user);

	public User updateLoginInfo(User dbuser, String newip);

	public User updateSecurity(User user, String password);

	public User updateInfo(User user);
	
	/**
	 * 分页查询的方法实现
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<User> getUser(User user, int pageNo, int pageSize);

	/**
	 * 删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeUser(int id);

	
	public User updateUser(User user);
	/**
	 * 插入新数据的方法实现
	 * @param example
	 * @return
	 */
	public User addUser(User user);
	
	public boolean isExists(Integer userid,String username);
}
