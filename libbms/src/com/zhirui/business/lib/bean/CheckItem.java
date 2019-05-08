package com.zhirui.business.lib.bean;

import java.util.Date;

/**
 * SampleRegiste entity. @author MyEclipse Persistence Tools
 */

public class CheckItem  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String itemname;
     private String instrumentname;
     private Integer createuser;
     private Date createdate;
     private Integer validflag;
	/** default constructor */
    public CheckItem() {
    }

    /** full constructor */
    public CheckItem(String itemname, String instrumentname) {
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
}