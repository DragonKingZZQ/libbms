package com.zhirui.business.common.dao;

import java.util.List;

import com.zhirui.business.common.bean.Qualification;
import cn.kepu.base.dao.BaseDao;

/**
 * 资质表
 * @author zy
 */
public interface QualificationDao extends BaseDao<Qualification> {
    
	public List<Qualification> getAll();
}
