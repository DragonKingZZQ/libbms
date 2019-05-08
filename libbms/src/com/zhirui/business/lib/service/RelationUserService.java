package com.zhirui.business.lib.service;

import java.util.List;
import java.util.Map;

import cn.kepu.utils.PageModel;

import com.zhirui.business.lib.bean.RelationUser;


public interface RelationUserService {
	/**
	 * 分页查询的方法实现
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<RelationUser> getRelationUser(RelationUser relationUser, int pageNo, int pageSize);

	/**
	 * 获取数据的方法
	 * @param id
	 * @return
	 */
	public RelationUser getRelationUser(int id);
	/**
	 * 删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeRelationUser(int id);
	/**
	 * 批量删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeRelationUsers(int[] id);
	/**
	 * 插入新数据的方法实现
	 * @param example
	 * @return
	 */
	public RelationUser addRelationUser(RelationUser relationUser);
	
	/**
	 * 更新数据的方法实现
	 * @param example
	 * @return
	 */
	public RelationUser updateRelationUser(RelationUser relationUser);
	
	public Map<Integer,String> getAll();
	
}
