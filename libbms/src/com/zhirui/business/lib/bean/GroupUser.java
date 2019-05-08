package com.zhirui.business.lib.bean;
// default package



/**
 * GroupUser entity. @author MyEclipse Persistence Tools
 */

public class GroupUser  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer groupid;
     private Integer userid;


    // Constructors

    /** default constructor */
    public GroupUser() {
    }

    
    /** full constructor */
    public GroupUser(Integer groupid, Integer userid) {
        this.groupid = groupid;
        this.userid = userid;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupid() {
        return this.groupid;
    }
    
    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public Integer getUserid() {
        return this.userid;
    }
    
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}