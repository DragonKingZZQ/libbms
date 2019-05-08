package com.zhirui.business.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhirui.business.common.bean.SysUser;
import com.zhirui.business.common.dao.SysUserDao;
import com.zhirui.business.common.service.SysUserService;

/**
 * 用户相关业务处理
 * @author zy
 */
@Repository("sysuserService")
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	public SysUserDao sysuserDao;

	@Override
	public SysUser getSysUser(int userId) {
		return sysuserDao.get(userId);
	}
}
