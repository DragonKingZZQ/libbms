package com.zhirui.business.common.bean;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */

public class SysUser implements java.io.Serializable, Cloneable {

	// Fields
	private int id;
	private String username;
	private String password;
	private String accountno;
	private String usertype;
	// Constructors

	/** default constructor */
	public SysUser() {
	}

	/** full constructor */
	public SysUser(String username, String password, String accountno,String usertype) {
		this.username = username;
		this.password = password;
		this.accountno = accountno;
		this.usertype = usertype;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountno() {
		return this.accountno;
	}

	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}

	public String getUsertype() {
		return this.usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

}