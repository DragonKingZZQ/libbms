package com.zhirui.business.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhirui.business.common.bean.Qualification;
import com.zhirui.business.common.bean.SampleCategory;
import com.zhirui.business.common.dao.QualificationDao;
import com.zhirui.business.common.dao.SampleCategoryDao;
import com.zhirui.business.common.dao.SysUserDao;
import com.zhirui.business.common.service.QualificationService;
import com.zhirui.business.common.service.SampleCategoryService;
import com.zhirui.business.common.service.SysUserService;
import com.zhirui.business.lib.bean.SampleRegiste;

/**
 * 用户相关业务处理
 * @author zy
 */
@Repository("sampleCategoryService")
public class SampleCategoryServiceImpl implements SampleCategoryService {

	@Autowired
	public SampleCategoryDao sampleCategoryDao;

	@Override
	public List<SampleCategory> getAll() {
		return sampleCategoryDao.getAll();
	}
	
	@Override
	public SampleCategory getSampleCategory(int id){
		  return sampleCategoryDao.get(id);
	}
}
