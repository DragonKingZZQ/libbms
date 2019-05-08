package com.zhirui.business.lib.bean;

import java.util.Date;

/**
 * SampleRegiste entity. @author MyEclipse Persistence Tools
 */

public class RelationUser  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String senduser;
     private String tel;
     private String address;
     private String email;
     private Integer entrustcompanyid;
     private Integer type;
     private Float  balance;
     
     public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private Integer createuser;
     private Date createdate;
     
	/** default constructor */
    public RelationUser() {
    }

    /** full constructor */
    public RelationUser(String senduser, String tel, String address,String email) {
        this.senduser = senduser;
        this.tel = tel;
        this.address = address;
        this.email = email;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getSenduser() {
        return this.senduser;
    }
    
    public void setSenduser(String senduser) {
        this.senduser = senduser;
    }

    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

	public Integer getCreateuser() {
		return createuser;
	}

	public void setCreateuser(Integer createuser) {
		this.createuser = createuser;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Integer getEntrustcompanyid() {
		return entrustcompanyid;
	}

	public void setEntrustcompanyid(Integer entrustcompanyid) {
		this.entrustcompanyid = entrustcompanyid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Float getBalance() {
		return balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}
}