package com.zhirui.business.common.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhirui.business.common.bean.Notice;
import com.zhirui.business.common.dao.NoticeDao;
import com.zhirui.business.common.service.NoticeService;
import com.zhirui.business.utils.BusinessUtils;

import cn.kepu.base.MessageType;
import cn.kepu.utils.ContextUtils;
import cn.kepu.utils.PageModel;
import cn.kepu.utils.StringUtils;

/**
 * 用户相关业务处理
 * @author zhangzl
 */
@Repository("NoticeService")
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	public NoticeDao noticeDao;

	@Override
	public Notice getNotice(int id) {
		return noticeDao.get(id);
	}
	
	@Override
	public PageModel<Notice> getNotices(String category, int pageSize, int pageNo){return null;};
	
	@Override
	public List<Notice> getTopNotices(String category,int size)
	{
		String where = "where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();

		if (!StringUtils.isEmpty(category)) {
			where += " and category = ?";
			queryParamList.add(category);
		}

		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("istop", "desc");
		orderby.put("top_time", "desc");
		orderby.put("create_time", "desc");
		
		PageModel<Notice> page = noticeDao.find(where, queryParamList.toArray(new Object[0]), orderby, 1, 5);
		if(page!=null)
			return page.getList();
		else
			return null;
		
	}

	
}
