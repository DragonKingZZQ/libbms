package com.zhirui.business.lib.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.kepu.base.dao.DaoSupport;
import cn.kepu.utils.PageModel;

import com.zhirui.business.common.bean.Qualification;
import com.zhirui.business.lib.bean.LabProcessContent;
import com.zhirui.business.lib.dao.LabProcessContentDao;
import com.zhirui.business.lib.dao.LabProcessDao;
@Repository("labProcessContentDao")
public class LabProcessContentDaoImpl extends DaoSupport<LabProcessContent> implements
LabProcessContentDao {
	@Override
	public List<LabProcessContent> getAll() {
		PageModel<LabProcessContent> page = find(null,null, null, 1, 100);
		return page.getList();
	}
}
