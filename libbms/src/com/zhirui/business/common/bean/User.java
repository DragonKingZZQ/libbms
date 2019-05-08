package com.zhirui.business.common.bean;

import java.util.Date;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable, Cloneable {

	private static final long serialVersionUID = -2222996456190071652L;

	// Fields
	private int uid;
	private String name;
	private String password;
	private String authority;
	private String showname;
	private String email;
	private int groupid;
	private String telphone;
	private String address;
	private Date lastlogin;
	private String lastip;
	private int loginTimes;
	private String duty;
	private Integer office;
	private String officeStr;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String name, String password, String authority,
			String showname, String email, int groupid, String telphone,
			String address, Date lastlogin, String lastip, int loginTimes) {
		this.name = name;
		this.password = password;
		this.authority = authority;
		this.showname = showname;
		this.email = email;
		this.groupid = groupid;
		this.telphone = telphone;
		this.address = address;
		this.lastlogin = lastlogin;
		this.lastip = lastip;
		this.loginTimes = loginTimes;
	}

	// Property accessors

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getShowname() {
		return this.showname;
	}

	public void setShowname(String showname) {
		this.showname = showname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getLastlogin() {
		return this.lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

	public String getLastip() {
		return this.lastip;
	}

	public void setLastip(String lastip) {
		this.lastip = lastip;
	}

	public int getLoginTimes() {
		return this.loginTimes;
	}

	public void setLoginTimes(int loginTimes) {
		this.loginTimes = loginTimes;
	}

	@Override
	public Object clone() {
		User o = null;
		try {
			o = (User) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public Integer getOffice() {
		return office;
	}

	public void setOffice(Integer office) {
		this.office = office;
	}

	public String getOfficeStr() {
		return officeStr;
	}

	public void setOfficeStr(String officeStr) {
		this.officeStr = officeStr;
	}
}