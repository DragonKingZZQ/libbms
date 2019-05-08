package com.zhirui.business.common.dao.impl;
import org.springframework.stereotype.Repository;

import com.zhirui.business.common.bean.Notice;
import com.zhirui.business.common.dao.NoticeDao;

import cn.kepu.base.dao.DaoSupport;

@Repository("NoticeDao")
public class NoticeDaoImpl extends DaoSupport<Notice> implements NoticeDao {

}