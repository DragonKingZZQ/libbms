package com.zhirui.business.common.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.zhirui.business.common.Constants;
import com.zhirui.business.common.bean.Attachment;
import com.zhirui.business.common.dao.AttachmentDao;

import cn.kepu.base.dao.DaoSupport;
import cn.kepu.utils.PageModel;

/**
 * 附件信息
 * @author zhangzl
 */
@Repository("attachmentDao")
public class AttachmentDaoImpl extends DaoSupport<Attachment> implements AttachmentDao {

	public static final Log log = LogFactory.getLog(AttachmentDaoImpl.class);

	@Override
	public List<Attachment> getAttachmentByCid(int cid, String status) {
		String where ="where cid=? and status=?";
		Object[] params = { cid, status};
			PageModel<Attachment> page = find(where, params, null, 1, Constants.MAX_ATTACHMENT_NUM);
			if(page!=null && page.getList().size()>0){
				return page.getList();
			}
		return null;
	}

	@Override
	public List<Attachment> getAttachmentByCid(String category, int cid, String status) {
		String where ="where category=? and cid=? and status=?";
		Object[] params = {category, cid, status};
			PageModel<Attachment> page = find(where, params, null, 1, Constants.MAX_ATTACHMENT_NUM);
			if(page!=null && page.getList().size()>0){
				return page.getList();
			}
		return null;
	}

	@Override
	public boolean deleteAttachment(int id) {
		Attachment dbattachment = get(id);
		if(dbattachment == null) {
			log.info("The attachment ["+id+"] is not exist");
			return true;
		}
		if(Constants.FILE_DELETED.equals(dbattachment.getStatus())) {
			log.warn("The attachment ["+id+"] is already deleted");
			return true;
		}
		dbattachment.setStatus(Constants.FILE_DELETED);
		try {
			update(dbattachment);
			return true;
		} catch (Exception e) {
			log.error("Delete upload file ["+dbattachment.getName()+"] failed", e);
		}
		return false;
	}
	@Override
	public boolean deleteAttachmentByCid(int cid) {
		List<Attachment> attachments = getAttachmentByCid(cid, Constants.FILE_UPLOADED);
		if(attachments == null) {
			log.info("The attachment [cid="+cid+"] is not exist");
			return true;
		}
		for(Attachment attachment : attachments) {
			attachment.setStatus(Constants.FILE_DELETED);
			try {
				update(attachment);
			} catch (Exception e) {
				log.error("Delete upload file ["+attachment.getName()+"] failed", e);
			}
		}
		return true;
	}
}
