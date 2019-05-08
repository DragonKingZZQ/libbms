package com.zhirui.business.lib.dao;

import java.util.List;
import java.util.Map;

import cn.kepu.base.dao.BaseDao;

import com.zhirui.business.lib.bean.LabProcessContent;

public interface LabProcessContentDao extends BaseDao<LabProcessContent>{

	public List<LabProcessContent> getAll();

}
