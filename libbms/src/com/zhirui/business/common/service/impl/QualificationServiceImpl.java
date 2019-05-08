package com.zhirui.business.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhirui.business.common.bean.Qualification;
import com.zhirui.business.common.dao.QualificationDao;
import com.zhirui.business.common.dao.SysUserDao;
import com.zhirui.business.common.service.QualificationService;
import com.zhirui.business.common.service.SysUserService;

/**
 * 用户相关业务处理
 * @author zy
 */
@Repository("qualificationService")
public class QualificationServiceImpl implements QualificationService {

	@Autowired
	public QualificationDao qualificationDao;

	@Override
	public List<Qualification> getAll() {
		return qualificationDao.getAll();
	}
}
