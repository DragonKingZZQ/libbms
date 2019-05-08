package com.zhirui.business.lib.bean;
// default package

import java.util.Date;


/**
 * SampleRegiste entity. @author MyEclipse Persistence Tools
 */

public class EntrustCompany  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String entrustcompany;
     private String username;
     private Integer createuser;
     private Date createdate;
     private float prepaymoney;
    // Constructors


	/** default constructor */
    public EntrustCompany() {
    }

	/** minimal constructor */
    public EntrustCompany(String entrustcompany) {
        this.entrustcompany = entrustcompany;
    }
    
   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getEntrustcompany() {
        return this.entrustcompany;
    }
    
    public void setEntrustcompany(String entrustcompany) {
        this.entrustcompany = entrustcompany;
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

	public float getPrepaymoney() {
		return prepaymoney;
	}

	public void setPrepaymoney(float prepaymoney) {
		this.prepaymoney = prepaymoney;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}