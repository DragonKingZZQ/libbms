package com.zhirui.business.lib.bean;

import java.util.Date;

/**
 * SampleRegiste entity. @author MyEclipse Persistence Tools
 */

public class Statistics  implements java.io.Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields    

     private Integer id;
     private String entrustcompany;
     private Integer entrustcompanyid;
     private String talent;
     private String checkitem;
     private Date receivedatestart;
     private Date receivedateend;
     private String itemname;
     private String instrumentname;
     private Integer createuser;
     private Date createdate;
     private Integer validflag;
     
     private int samples;
     private int finished;
     private int bills;
     private int posts;
     private float checkmoney;
     private float premoney;
     private float balmoney;
     
     private String examineuser;
     private int sends;
     private int receives;
     
     private int nofinished;
     private int finishintime;
     private int finishovertime;
	/** default constructor */
    public Statistics() {
    }

    /** full constructor */
    public Statistics(String itemname, String instrumentname) {
        this.itemname = itemname;
        this.instrumentname = instrumentname;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemname() {
        return this.itemname;
    }
    
    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getInstrumentname() {
        return this.instrumentname;
    }
    
    public Integer getEntrustcompanyid() {
		return entrustcompanyid;
	}

	public void setEntrustcompanyid(Integer entrustcompanyid) {
		this.entrustcompanyid = entrustcompanyid;
	}

	public void setInstrumentname(String instrumentname) {
        this.instrumentname = instrumentname;
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

	public Integer getValidflag() {
		return validflag;
	}

	public void setValidflag(Integer validflag) {
		this.validflag = validflag;
	}

	public String getEntrustcompany() {
		return entrustcompany;
	}

	public void setEntrustcompany(String enstrustcompany) {
		this.entrustcompany = enstrustcompany;
	}

	public String getTalent() {
		return talent;
	}

	public void setTalent(String talent) {
		this.talent = talent;
	}

	public String getCheckitem() {
		return checkitem;
	}

	public void setCheckitem(String checkitem) {
		this.checkitem = checkitem;
	}

	public Date getReceivedatestart() {
		return receivedatestart;
	}

	public void setReceivedatestart(Date receivedatestart) {
		this.receivedatestart = receivedatestart;
	}

	public Date getReceivedateend() {
		return receivedateend;
	}

	public void setReceivedateend(Date receivedateend) {
		this.receivedateend = receivedateend;
	}

	public float getCheckmoney() {
		return checkmoney;
	}

	public void setCheckmoney(float checkmoney) {
		this.checkmoney = checkmoney;
	}

	public float getPremoney() {
		return premoney;
	}

	public void setPremoney(float premoney) {
		this.premoney = premoney;
	}

	public float getBalmoney() {
		return balmoney;
	}

	public void setBalmoney(float balmoney) {
		this.balmoney = balmoney;
	}

	public int getSamples() {
		return samples;
	}

	public void setSamples(int samples) {
		this.samples = samples;
	}

	public int getFinished() {
		return finished;
	}

	public void setFinished(int finished) {
		this.finished = finished;
	}

	public int getBills() {
		return bills;
	}

	public void setBills(int bills) {
		this.bills = bills;
	}

	public int getPosts() {
		return posts;
	}

	public void setPosts(int posts) {
		this.posts = posts;
	}

	public String getExamineuser() {
		return examineuser;
	}

	public void setExamineuser(String examineuser) {
		this.examineuser = examineuser;
	}

	public int getSends() {
		return sends;
	}

	public void setSends(int sends) {
		this.sends = sends;
	}

	public int getReceives() {
		return receives;
	}

	public void setReceives(int receives) {
		this.receives = receives;
	}

	public int getNofinished() {
		return nofinished;
	}

	public void setNofinished(int nofinished) {
		this.nofinished = nofinished;
	}

	public int getFinishintime() {
		return finishintime;
	}

	public void setFinishintime(int finishintime) {
		this.finishintime = finishintime;
	}

	public int getFinishovertime() {
		return finishovertime;
	}

	public void setFinishovertime(int finishovertime) {
		this.finishovertime = finishovertime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}