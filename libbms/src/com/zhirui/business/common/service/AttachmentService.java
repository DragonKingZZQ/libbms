package com.zhirui.business.common.service;

import java.io.File;
import java.util.List;

import com.zhirui.business.common.bean.Attachment;


/**
 * 文件（附件）上传相关的业务处理类
 * @author zhangzl
 */
public interface AttachmentService {

	/**
	 * 上传文件
	 * @param category	文件所属数据表的类别，一般是数据表的表名
	 * @param cid		对应的数据表的主键
	 * @param file		上传的文件
	 * @param filename	上传文件的文件名
	 * @return
	 */
	public Attachment uploadFile(String category, int cid, File file, String filename);

	/**
	 * 上传文件，对应的数据表的主键cid需要在之后指定
	 * @param category	文件所属数据表的类别，一般是数据表的表名
	 * @param file		上传的文件
	 * @param filename	上传文件的文件名
	 * @return
	 */
	public Attachment uploadFile(String category, File file, String filename);
	
	/**
	 * 完成文件的上传
	 * @param id	对应的Attachment表的主键
	 * @param cid	对应的数据表的主键
	 * @return
	 */
	public boolean completeUpload(int id, int cid);
	
	/**
	 * 删除上传的文件
	 * @param id	对应的Attachment表的主键
	 * @return
	 */
	public boolean deleteAttachment(int id);

	/**
	 * 根据指定的编号查找附件
	 * @param id
	 * @return
	 */
	public Attachment getAttachment(int id);
	

	/**
	 * 根据指定的模块查找已经上传的附件
	 * @param category
	 * @param cid
	 * @return
	 */
	public List<Attachment> getAttachmentByCid(String category, int cid);
	/**
	 * 根据指定的模块和状态查找附件
	 * @param category
	 * @param cid
	 * @param status
	 * @return
	 */
	public List<Attachment> getAttachmentByCid(String category, int cid, String status);

}
