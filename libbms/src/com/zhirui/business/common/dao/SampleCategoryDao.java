package com.zhirui.business.common.dao;

import java.util.List;

import com.zhirui.business.common.bean.Qualification;
import com.zhirui.business.common.bean.SampleCategory;

import cn.kepu.base.dao.BaseDao;

/**
 * 资质表
 * @author zy
 */
public interface SampleCategoryDao extends BaseDao<SampleCategory> {
    
	public List<SampleCategory> getAll();
}
