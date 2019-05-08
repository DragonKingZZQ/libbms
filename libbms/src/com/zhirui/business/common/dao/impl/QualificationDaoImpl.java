package com.zhirui.business.common.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.zhirui.business.common.bean.Qualification;
import com.zhirui.business.common.dao.QualificationDao;
import cn.kepu.base.dao.DaoSupport;
import cn.kepu.utils.PageModel;

/**
 * 
 * @author zy
 */
@Repository("qualificationDao")
public class QualificationDaoImpl extends DaoSupport<Qualification> implements QualificationDao {

	@Override
	public List<Qualification> getAll() {
		PageModel<Qualification> page = find(null,null, null, 1, 100);
		return page.getList();
	}

}
