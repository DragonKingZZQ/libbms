package com.zhirui.business.common.bean;
// default package


import java.util.Date;

/**
 * Notice entity. @author MyEclipse Persistence Tools
 */

public class Notice  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String title;
     private String content;
     private String category;
	private String url;
     private String istop;
     private Date topTime;
     private String status;
     private Integer uid;
     private Date createTime;


    // Constructors

    /** default constructor */
    public Notice() {
    }

	/** minimal constructor */
    public Notice(Integer id, String title, String istop, String status, Integer uid, Date createTime) {
        this.id = id;
        this.title = title;
        this.istop = istop;
        this.status = status;
        this.uid = uid;
        this.createTime = createTime;
    }
    
    /** full constructor */
    public Notice(Integer id, String title, String content, String url, String istop, Date topTime, String status, Integer uid, Date createTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.url = url;
        this.istop = istop;
        this.topTime = topTime;
        this.status = status;
        this.uid = uid;
        this.createTime = createTime;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public String getIstop() {
        return this.istop;
    }
    
    public void setIstop(String istop) {
        this.istop = istop;
    }


    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUid() {
        return this.uid;
    }
    
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setTopTime(Date topTime) {
		this.topTime = topTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getTopTime() {
		return topTime;
	}

	public Date getCreateTime() {
		return createTime;
	}



}