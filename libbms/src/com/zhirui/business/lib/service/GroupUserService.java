package com.zhirui.business.lib.service;

import cn.kepu.utils.PageModel;
import com.zhirui.business.lib.bean.GroupUser;

public interface GroupUserService {
	/**
	 * 分页查询的方法实现
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<GroupUser> getGroupUser(GroupUser groupUser, int pageNo, int pageSize);

	/**
	 * 获取数据的方法
	 * @param id
	 * @return
	 */
	public GroupUser getGroupUser(int id);
	/**
	 * 删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeGroupUser(int id);

	
	public GroupUser updateGroupUser(GroupUser groupUser);
	/**
	 * 插入新数据的方法实现
	 * @param example
	 * @return
	 */
	public GroupUser addGroupUser(GroupUser groupUser);
	
}
