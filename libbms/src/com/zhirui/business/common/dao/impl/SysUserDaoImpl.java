package com.zhirui.business.common.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhirui.business.common.bean.SysUser;
import com.zhirui.business.common.dao.SysUserDao;

import cn.kepu.base.dao.DaoSupport;

/**
 * 用户信息表
 * @author zy
 */
@Repository("sysuserDao")
public class SysUserDaoImpl extends DaoSupport<SysUser> implements SysUserDao {

}
