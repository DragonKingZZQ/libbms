package com.zhirui.business.common.service;

import java.util.List;

import com.zhirui.business.common.bean.Notice;

import cn.kepu.utils.PageModel;

/**
 * 通知公告业务处理
 * @author hhb
 */
public interface NoticeService {
	public Notice getNotice(int id);
	public PageModel<Notice> getNotices(String category, int pageSize, int pageNo);
	public List<Notice> getTopNotices(String category,int size);
	
}
