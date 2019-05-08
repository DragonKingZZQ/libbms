package com.zhirui.business.lib.bean;
// default package

import java.util.Date;


/**
 * Groups entity. @author MyEclipse Persistence Tools
 */

public class Groups  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String groupname;
     private String privillage;
     private Integer createuser;
     private Date createdate;


    // Constructors

    /** default constructor */
    public Groups() {
    }

	/** minimal constructor */
    public Groups(String groupname, Integer createuser, Date createdate) {
        this.groupname = groupname;
        this.createuser = createuser;
        this.createdate = createdate;
    }
    
    /** full constructor */
    public Groups(String groupname, String privillage, Integer createuser, Date createdate) {
        this.groupname = groupname;
        this.privillage = privillage;
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

    public String getGroupname() {
        return this.groupname;
    }
    
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getPrivillage() {
        return this.privillage;
    }
    
    public void setPrivillage(String privillage) {
        this.privillage = privillage;
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
}