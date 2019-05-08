package com.zhirui.business.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhirui.business.common.bean.SampleCategory;

import com.zhirui.business.common.dao.SampleCategoryDao;

import cn.kepu.base.dao.DaoSupport;
import cn.kepu.utils.PageModel;

/**
 * 
 * @author 曾智琴
 */
@Repository("sampleCategoryDao")
public class SampleCategoryDaoImpl extends DaoSupport<SampleCategory> implements SampleCategoryDao {

	@Override
	public List<SampleCategory> getAll() {
		PageModel<SampleCategory> page = find(null,null, null, 1, 100);
		return page.getList();
	}



}
