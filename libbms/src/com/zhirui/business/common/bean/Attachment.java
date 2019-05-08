package com.zhirui.business.common.bean;

import java.util.Date;

/**
 * TAttachment entity. @author MyEclipse Persistence Tools
 */

public class Attachment implements java.io.Serializable {

	private static final long serialVersionUID = -1189378047620878208L;

	// Fields

	private int id;
	private String category;
	private int cid;
	private Date uploadTime;
	private String name;
	private String url;
	private int size;
	private String describe;
	private String status;

	// Constructors

	/** default constructor */
	public Attachment() {
	}

	/** full constructor */
	public Attachment(String category, Integer cid, Date uploadTime, String name,
			String url, Integer size, String describe, String status) {
		this.category = category;
		this.cid = cid;
		this.uploadTime = uploadTime;
		this.name = name;
		this.url = url;
		this.size = size;
		this.describe = describe;
		this.status = status;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getCid() {
		return this.cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public Date getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}