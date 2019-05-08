package com.zhirui.business.lib.dao.impl;

import org.springframework.stereotype.Repository;
import cn.kepu.base.dao.DaoSupport;

import com.zhirui.business.lib.bean.Groups;
import com.zhirui.business.lib.dao.GroupsDao;

@Repository("groupsDao")
public class GroupsDaoImpl extends DaoSupport<Groups> implements GroupsDao {
	
}
