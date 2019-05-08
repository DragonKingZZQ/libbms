package com.zhirui.business.lib.bean;
// default package

import java.util.Date;


/**
 * Income entity. @author MyEclipse Persistence Tools
 */

public class Income  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer entrustcompanyid;
     private String entrustcompanyStr;
     private Integer userid;
     private String username;
     private String address;
     private String tel;
     private Float paymoney;
     private Date paydate;
     private String paytype;
     private String sampleregisteids;
     private String receiveuser;
     private Integer createuser;
     private Date createdate;
     private String caltype;
     private String precal;
     private String status;
     
     private int taxtype;
     private String taxtypename;

	 private String billischeck;
     private String billno;
     
     private Float totalcheckmoney;
    // Constructors

    public int getTaxtype() {
		return taxtype;
	}


	public void setTaxtype(int taxtype) {
		this.taxtype = taxtype;
	}


	public String getBillischeck() {
		return billischeck;
	}


	public void setBillischeck(String billischeck) {
		this.billischeck = billischeck;
	}


	public String getBillno() {
		return billno;
	}


	public void setBillno(String billno) {
		this.billno = billno;
	}


	/** default constructor */
    public Income() {
    }

    
    /** full constructor */
    public Income(Integer entrustcompanyid, String username, Float paymoney, Date paydate, String sampleregisteids, String receiveuser, Integer createuser, Date createdate,int taxtype,String billischeck,String billno) {
        this.entrustcompanyid = entrustcompanyid;
        this.username = username;
        this.paymoney = paymoney;
        this.paydate = paydate;
        this.sampleregisteids = sampleregisteids;
        this.receiveuser = receiveuser;
        this.createuser = createuser;
        this.createdate = createdate;
        this.taxtype = taxtype;
        this.billischeck = billischeck;
        this.billno = billno;
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

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public Float getPaymoney() {
        return this.paymoney;
    }
    
    public void setPaymoney(Float paymoney) {
        this.paymoney = paymoney;
    }

    public Date getPaydate() {
        return this.paydate;
    }
    
    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public String getSampleregisteids() {
        return this.sampleregisteids;
    }
    
    public void setSampleregisteids(String sampleregisteids) {
        this.sampleregisteids = sampleregisteids;
    }

    public String getReceiveuser() {
        return this.receiveuser;
    }
    
    public void setReceiveuser(String receiveuser) {
        this.receiveuser = receiveuser;
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


	public String getCaltype() {
		return caltype;
	}


	public void setCaltype(String caltype) {
		this.caltype = caltype;
	}


	public String getPaytype() {
		return paytype;
	}


	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}


	public String getPrecal() {
		return precal;
	}


	public void setPrecal(String precal) {
		this.precal = precal;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	public String getTaxtypename() {
		return taxtypename;
	}

	public void setTaxtypename(String taxtypename) {
		this.taxtypename = taxtypename;
	}


	public Float getTotalcheckmoney() {
		return totalcheckmoney;
	}


	public void setTotalcheckmoney(Float totalcheckmoney) {
		this.totalcheckmoney = totalcheckmoney;
	}


	public Integer getUserid() {
		return userid;
	}


	public void setUserid(Integer userid) {
		this.userid = userid;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}
}