package com.zhirui.business.lib.bean;

/**
 * SendCheckItem entity. @author MyEclipse Persistence Tools
 */

public class SendCheckItem  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer sendsampleid;
     private String subsendsampleno;
     private String checkitem;
     private String type;
     private String standard;
     private String checkitemname;
     private String instrumentname;
     private String instrumentcode;
     private String unit;
     private String checkscope;
     private String checkresult;
     
   //曾智琴  增加五个字段
     private String labprocess;
     private int labprocesstitle;
     private String balance;
     private String balanceaccuracy;
     private String temperature;
     private String humidity;
    // Constructors

    /** default constructor */
    public SendCheckItem() {
    }

	/** minimal constructor */
    public SendCheckItem(Integer sendsampleid) {
        this.sendsampleid = sendsampleid;
    }
    
    /** full constructor */
    public SendCheckItem(Integer sendsampleid, String checkitem, String standard) {
        this.sendsampleid = sendsampleid;
        this.checkitem = checkitem;
        this.standard = standard;
       
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSendsampleid() {
        return this.sendsampleid;
    }
    
    public void setSendsampleid(Integer sendsampleid) {
        this.sendsampleid = sendsampleid;
    }

    public String getCheckitem() {
        return this.checkitem;
    }
    
    public void setCheckitem(String checkitem) {
        this.checkitem = checkitem;
    }

    public String getStandard() {
        return this.standard;
    }
    
    public void setStandard(String standard) {
        this.standard = standard;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCheckitemname() {
		return checkitemname;
	}

	public void setCheckitemname(String checkitemname) {
		this.checkitemname = checkitemname;
	}

	public String getInstrumentcode() {
		return instrumentcode;
	}

	public void setInstrumentcode(String instrumentcode) {
		this.instrumentcode = instrumentcode;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCheckscope() {
		return checkscope;
	}

	public void setCheckscope(String checkscope) {
		this.checkscope = checkscope;
	}

	public String getCheckresult() {
		return checkresult;
	}

	public void setCheckresult(String checkresult) {
		this.checkresult = checkresult;
	}

	public String getInstrumentname() {
		return instrumentname;
	}

	public void setInstrumentname(String instrumentname) {
		this.instrumentname = instrumentname;
	}

	public String getSubsendsampleno() {
		return subsendsampleno;
	}

	public void setSubsendsampleno(String subsendsampleno) {
		this.subsendsampleno = subsendsampleno;
	}

	public String getLabprocess() {
		return labprocess;
	}

	public void setLabprocess(String labprocess) {
		this.labprocess = labprocess;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getBalanceaccuracy() {
		return balanceaccuracy;
	}

	public void setBalanceaccuracy(String balanceaccuracy) {
		this.balanceaccuracy = balanceaccuracy;
	}

	public int getLabprocesstitle() {
		return labprocesstitle;
	}

	public void setLabprocesstitle(int labprocesstitle) {
		this.labprocesstitle = labprocesstitle;
	}

	
	
	
	
	
}