package com.zhirui.business.common.dao;

import java.util.List;

import com.zhirui.business.common.bean.Attachment;

import cn.kepu.base.dao.BaseDao;

/**
 * 附件信息
 * @author zhangzl
 */
public interface AttachmentDao extends BaseDao<Attachment> {

	public List<Attachment> getAttachmentByCid(int cid, String status);

	public List<Attachment> getAttachmentByCid(String category, int cid, String status);

	public boolean deleteAttachment(int id);

	public boolean deleteAttachmentByCid(int cid);

}
