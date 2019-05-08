package com.zhirui.business.lib.dao.impl;

import org.springframework.stereotype.Repository;
import cn.kepu.base.dao.DaoSupport;

import com.zhirui.business.lib.bean.GroupUser;
import com.zhirui.business.lib.dao.GroupUserDao;

@Repository("groupUserDao")
public class GroupUserDaoImpl extends DaoSupport<GroupUser> implements GroupUserDao {
	
}
