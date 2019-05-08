package com.zhirui.business.common.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhirui.business.common.bean.User;
import com.zhirui.business.common.dao.UserDao;

import cn.kepu.base.dao.DaoSupport;

/**
 * 用户信息表
 * @author zhangzl
 */
@Repository("userDao")
public class UserDaoImpl extends DaoSupport<User> implements UserDao {

}
