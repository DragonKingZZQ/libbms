package com.zhirui.business.lib.bean;
// default package

import java.util.Date;


/**
 * Prepayment entity. @author MyEclipse Persistence Tools
 */

public class Prepayment  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer entrustcompanyid;
     private String entrustcompanyStr;
     private String address;
     private Integer userid;
     private String username;
     private String tel;
     private Float prepaymoney;
     private Float balancemoney;
     private Date prepaydate;
     private String receiveruser;
     private Integer createuser;
     private Date createdate;


    // Constructors

    /** default constructor */
    public Prepayment() {
    }

	/** minimal constructor */
    public Prepayment(Integer entrustcompanyid,String entrustcompanyStr, String username, String tel, Float prepaymoney, Date prepaydate, String receiveruser) {
        this.entrustcompanyid = entrustcompanyid;
        this.username = username;
        this.tel = tel;
        this.prepaymoney = prepaymoney;
        this.prepaydate = prepaydate;
        this.receiveruser = receiveruser;
        this.entrustcompanyStr = entrustcompanyStr;
    }
    
    /** full constructor */
    public Prepayment(Integer entrustcompanyid,String entrustcompanyStr, String address, String username, String tel, Float prepaymoney, Date prepaydate, String receiveruser, Integer createuser, Date createdate) {
        this.entrustcompanyid = entrustcompanyid;
        this.entrustcompanyStr = entrustcompanyStr;
        this.address = address;
        this.username = username;
        this.tel = tel;
        this.prepaymoney = prepaymoney;
        this.prepaydate = prepaydate;
        this.receiveruser = receiveruser;
        this.createuser = createuser;
        this.createdate = createdate;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEntrustcompanyid() {
        return this.entrustcompanyid;
    }
    
    public void setEntrustcompanyid(Integer entrustcompanyid) {
        this.entrustcompanyid = entrustcompanyid;
    }

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }

    public Float getPrepaymoney() {
        return this.prepaymoney;
    }
    
    public void setPrepaymoney(Float prepaymoney) {
        this.prepaymoney = prepaymoney;
    }

    public Date getPrepaydate() {
        return this.prepaydate;
    }
    
    public void setPrepaydate(Date prepaydate) {
        this.prepaydate = prepaydate;
    }

    public String getReceiveruser() {
        return this.receiveruser;
    }
    
    public void setReceiveruser(String receiveruser) {
        this.receiveruser = receiveruser;
    }

    public Integer getCreateuser() {
        return this.createuser;
    }
    
    public void setCreateuser(Integer createuser) {
        this.createuser = createuser;
    }

    public Date getCreatedate() {
        return this.createdate;
    }
    
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

	public String getEntrustcompanyStr() {
		return entrustcompanyStr;
	}

	public void setEntrustcompanyStr(String entrustcompanyStr) {
		this.entrustcompanyStr = entrustcompanyStr;
	}

	public Float getBalancemoney() {
		return balancemoney;
	}

	public void setBalancemoney(Float balancemoney) {
		this.balancemoney = balancemoney;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
}