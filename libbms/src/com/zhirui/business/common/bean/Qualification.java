package com.zhirui.business.common.bean;

/**
 * TAttachment entity. @author MyEclipse Persistence Tools
 */

public class Qualification implements java.io.Serializable {
	// Fields

	private int id;
	private int code;
	private String name;

	/** default constructor */
	public Qualification() {
	}

	/** full constructor */
	public Qualification(Integer code,  String name) {
		this.code = code;
		this.name = name;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}