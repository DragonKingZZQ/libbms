package com.zhirui.business.lib.bean;

import java.sql.Timestamp;
import java.util.Date;


/**
 * CheckReport entity. @author MyEclipse Persistence Tools
 */

public class CheckReport  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer sampleid;
     private String sampleno;
     private String samplename;
     private String entrustcompany;
     private String productionunit;
     private String trademark;
     private Date receivedate;
     private Date finishdate;
     private String samplemodel;
     private String samplecount;
     private String samplestatus;
     private String remark;
     private Integer createuser;
     private Date createdate;
     //查询用属性
     private String checkitem;
     
     private String resultremark;  //曾智琴  结果备注
     private String analyzeremark;  //分析备注

    // Constructors

    /** default constructor */
    public CheckReport() {
    }

	/** minimal constructor */
    public CheckReport(String sampleno, String samplename) {
        this.sampleno = sampleno;
        this.samplename = samplename;
    }
    
    /** full constructor */
    public CheckReport(String sampleno, String samplename, String entrustcompany, String productionunit, String trademark, Date receivedate, Date finishdate, String samplemodel, String samplecount, String samplestatus, String remark, Integer createuser, Timestamp createdate,String resultremark,String analyzeremark) {
        this.sampleno = sampleno;
        this.samplename = samplename;
        this.entrustcompany = entrustcompany;
        this.productionunit = productionunit;
        this.trademark = trademark;
        this.receivedate = receivedate;
        this.finishdate = finishdate;
        this.samplemodel = samplemodel;
        this.samplecount = samplecount;
        this.samplestatus = samplestatus;
        this.remark = remark;
        this.createuser = createuser;
        this.createdate = createdate;
        this.analyzeremark = analyzeremark;
        this.resultremark = resultremark;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getSampleno() {
        return this.sampleno;
    }
    
    public void setSampleno(String sampleno) {
        this.sampleno = sampleno;
    }

    public String getSamplename() {
        return this.samplename;
    }
    
    public void setSamplename(String samplename) {
        this.samplename = samplename;
    }

    public String getEntrustcompany() {
        return this.entrustcompany;
    }
    
    public void setEntrustcompany(String entrustcompany) {
        this.entrustcompany = entrustcompany;
    }

    public String getProductionunit() {
        return this.productionunit;
    }
    
    public void setProductionunit(String productionunit) {
        this.productionunit = productionunit;
    }

    public String getTrademark() {
        return this.trademark;
    }
    
    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public Date getReceivedate() {
        return this.receivedate;
    }
    
    public void setReceivedate(Date receivedate) {
        this.receivedate = receivedate;
    }

    public Date getFinishdate() {
        return this.finishdate;
    }
    
    public void setFinishdate(Date finishdate) {
        this.finishdate = finishdate;
    }

    public String getSamplemodel() {
        return this.samplemodel;
    }
    
    public void setSamplemodel(String samplemodel) {
        this.samplemodel = samplemodel;
    }

    public String getSamplecount() {
        return this.samplecount;
    }
    
    public void setSamplecount(String samplecount) {
        this.samplecount = samplecount;
    }

    public String getSamplestatus() {
        return this.samplestatus;
    }
    
    public void setSamplestatus(String samplestatus) {
        this.samplestatus = samplestatus;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
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

	public Integer getSampleid() {
		return sampleid;
	}

	public void setSampleid(Integer sampleid) {
		this.sampleid = sampleid;
	}

	public String getCheckitem() {
		return checkitem;
	}

	public void setCheckitem(String checkitem) {
		this.checkitem = checkitem;
	}

	public String getResultremark() {
		return resultremark;
	}

	public void setResultremark(String resultremark) {
		this.resultremark = resultremark;
	}

	public String getAnalyzeremark() {
		return analyzeremark;
	}

	public void setAnalyzeremark(String analyzeremark) {
		this.analyzeremark = analyzeremark;
	}
	
}