package com.zhirui.business.lib.bean;

import java.util.Date;


/**
 * Instrument entity. @author MyEclipse Persistence Tools
 */

public class Instrument  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String codename;
     private String inscode;
     private String insname;
     private String standard;
     private String measueRange;
     private String grade;
     private String product;
     private String correctCompany;
     private String correctCycle;
     private Date recentCorrectDate;
     private Date nextCorrectDate;
     private String remark;
     private Integer validflag;

    // Constructors

    /** default constructor */
    public Instrument() {
    }

	/** minimal constructor */
    public Instrument(String codename) {
        this.codename = codename;
    }
    
    /** full constructor */
    public Instrument(String codename,String inscode,String insname, String standard, String measueRange, String grade, String product, String correctCompany, String correctCycle, Date recentCorrectDate, Date nextCorrectDate, String remark) {
        this.codename = codename;
        this.standard = standard;
        this.measueRange = measueRange;
        this.grade = grade;
        this.product = product;
        this.correctCompany = correctCompany;
        this.correctCycle = correctCycle;
        this.recentCorrectDate = recentCorrectDate;
        this.nextCorrectDate = nextCorrectDate;
        this.remark = remark;
        this.inscode = inscode;
        this.insname = insname;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodename() {
        return this.codename;
    }
    
    public void setCodename(String codename) {
        this.codename = codename;
    }

    public String getStandard() {
        return this.standard;
    }
    
    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getMeasueRange() {
        return this.measueRange;
    }
    
    public void setMeasueRange(String measueRange) {
        this.measueRange = measueRange;
    }

    public String getGrade() {
        return this.grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getProduct() {
        return this.product;
    }
    
    public void setProduct(String product) {
        this.product = product;
    }

    public String getCorrectCompany() {
        return this.correctCompany;
    }
    
    public void setCorrectCompany(String correctCompany) {
        this.correctCompany = correctCompany;
    }

    public String getCorrectCycle() {
        return this.correctCycle;
    }
    
    public void setCorrectCycle(String correctCycle) {
        this.correctCycle = correctCycle;
    }

    public Date getRecentCorrectDate() {
        return this.recentCorrectDate;
    }
    
    public void setRecentCorrectDate(Date recentCorrectDate) {
        this.recentCorrectDate = recentCorrectDate;
    }

    public Date getNextCorrectDate() {
        return this.nextCorrectDate;
    }
    
    public void setNextCorrectDate(Date nextCorrectDate) {
        this.nextCorrectDate = nextCorrectDate;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

	public Integer getValidflag() {
		return validflag;
	}

	public void setValidflag(Integer validflag) {
		this.validflag = validflag;
	}

	public String getInscode() {
		return inscode;
	}

	public void setInscode(String inscode) {
		this.inscode = inscode;
	}

	public String getInsname() {
		return insname;
	}

	public void setInsname(String insname) {
		this.insname = insname;
	}
}