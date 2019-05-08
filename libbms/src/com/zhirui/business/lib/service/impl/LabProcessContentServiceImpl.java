package com.zhirui.business.lib.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.kepu.utils.PageModel;

import com.zhirui.business.common.Constants;
import com.zhirui.business.lib.bean.EntrustCompany;
import com.zhirui.business.lib.bean.LabProcessContent;
import com.zhirui.business.lib.dao.LabProcessContentDao;
import com.zhirui.business.lib.service.LabProcessContentService;
@Repository("labProcessContentService")
public class LabProcessContentServiceImpl implements LabProcessContentService {
	@Autowired
	private LabProcessContentDao labProcessContentDao;
	@Override
	public List<String> getAll() {
		Map<Integer, String> contents = new HashMap<Integer, String>();
		Map<String, String> orderby = new HashMap<String, String>();
		List<String> contentliList = new ArrayList<String>();
		orderby.put("id", "asc");
		PageModel<LabProcessContent> page = labProcessContentDao.find(null, null, orderby, 1, Constants.MAX_COUNT);
		if (page != null) {
			for(LabProcessContent ec : page.getList()){
				contents.put(ec.getId(),ec.getLabprocesscontent());
				contentliList.add(ec.getLabprocesscontent());
			}
			return  contentliList;
		}
		return null;
	}
}
