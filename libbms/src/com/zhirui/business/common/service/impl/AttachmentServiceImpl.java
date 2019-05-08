package com.zhirui.business.common.service.impl;

import java.io.File;
import java.security.InvalidParameterException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhirui.business.common.Constants;
import com.zhirui.business.common.bean.Attachment;
import com.zhirui.business.common.dao.AttachmentDao;
import com.zhirui.business.common.service.AttachmentService;
import com.zhirui.business.utils.BusinessUtils;

import cn.kepu.utils.StringUtils;

/**
 * 文件（附件）上传相关的业务处理类
 * @author zhangzl
 */
@Repository("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {

	private static final Log log = LogFactory.getLog(AttachmentServiceImpl.class);

	@Autowired
	private AttachmentDao attachmentDao;

	@Override
	public Attachment uploadFile(String category, int cid, File file, String filename) {
		if(StringUtils.isEmpty(category) || cid == 0 || file == null || StringUtils.isEmpty(filename)) {
			throw new InvalidParameterException("Can't upload file, not enough parameters");
		}
		String newFileName = BusinessUtils.uploadfile(file, filename);
		if (!StringUtils.isEmpty(newFileName)) {
			Attachment attachment = new Attachment();
			attachment.setCategory(category);
			attachment.setCid(cid);
			attachment.setName(filename);
			attachment.setSize((int)file.length());
			attachment.setUrl(newFileName);
			attachment.setUploadTime(BusinessUtils.getRequestTime());
			attachment.setStatus(Constants.FILE_UPLOADED);
			attachment.setDescribe("");
			try {
				int id = (Integer)attachmentDao.save(attachment);
				attachment.setId(id);
				return attachment;
			} catch (Exception e) {
				log.error("Upload file ["+filename+"] stored into DB failed", e);
			}
		}
		return null;
	}

	@Override
	public Attachment uploadFile(String category, File file, String filename) {
		if(StringUtils.isEmpty(category) || file == null || StringUtils.isEmpty(filename)) {
			throw new InvalidParameterException("Can't upload file, not enough parameters");
		}
		String newFileName = BusinessUtils.uploadfile(file, filename);
		if (!StringUtils.isEmpty(newFileName)) {
			Attachment attachment = new Attachment();
			attachment.setCategory(category);
			attachment.setCid(0);
			attachment.setName(filename);
			attachment.setSize((int)file.length());
			attachment.setUrl(newFileName);
			attachment.setUploadTime(BusinessUtils.getRequestTime());
			attachment.setStatus(Constants.FILE_UPLOADING);
			attachment.setDescribe("");
			try {
				int id = (Integer)attachmentDao.save(attachment);
				attachment.setId(id);
				return attachment;
			} catch (Exception e) {
				log.error("Upload file ["+filename+"] stored into DB failed", e);
			}
		}
		return null;
	}

	@Override
	public boolean completeUpload(int id, int cid) {
		if(cid == 0) {
			throw new InvalidParameterException("Can't complete uploading file, category data parameter is not set");
		}
		Attachment dbattachment = attachmentDao.get(id);
		if(dbattachment == null) {
			log.warn("The attachment ["+id+"] is not exist");
			return false;
		}
		dbattachment.setCid(cid);
		dbattachment.setStatus(Constants.FILE_UPLOADED);
		try {
			attachmentDao.update(dbattachment);
			return true;
		} catch (Exception e) {
			log.error("Complete upload file ["+dbattachment.getName()+"] failed", e);
		}
		return false;
	}

	@Override
	public boolean deleteAttachment(int id) {
		return attachmentDao.deleteAttachment(id);
	}

	@Override
	public Attachment getAttachment(int id) {
		return attachmentDao.get(id);
	}

	@Override
	public List<Attachment> getAttachmentByCid(String category, int cid) {
		return getAttachmentByCid(category, cid, Constants.FILE_UPLOADED);
	}

	@Override
	public List<Attachment> getAttachmentByCid(String category, int cid, String status) {
		return attachmentDao.getAttachmentByCid(category, cid, status);
	}
}
