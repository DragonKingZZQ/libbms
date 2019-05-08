package com.zhirui.business.lib.bean;
/**
 * SubCheckReport entity. @author MyEclipse Persistence Tools
 */

public class SubCheckReport  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer checkreportid;
     private String checkitem;
     private String checkitemname;
     private String standard;
     private String instrumentcode;
     private String instrumentname;
     private String checkresult;
     private String measuerange;
     private String unit;
     private String checkscope;
     private String type;
     private String subsendsampleno;

    // Constructors

    public String getMeasuerange() {
		return measuerange;
	}

	public void setMeasuerange(String measuerange) {
		this.measuerange = measuerange;
	}

	/** default constructor */
    public SubCheckReport() {
    }

	/** minimal constructor */
    public SubCheckReport(Integer checkreportid) {
        this.checkreportid = checkreportid;
    }
    
    /** full constructor */
    public SubCheckReport(Integer checkreportid, String checkitem, String standard, String instrumentcode, String checkresult) {
        this.checkreportid = checkreportid;
        this.checkitem = checkitem;
        this.standard = standard;
        this.instrumentcode = instrumentcode;
        this.checkresult = checkresult;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCheckreportid() {
        return this.checkreportid;
    }
    
    public void setCheckreportid(Integer checkreportid) {
        this.checkreportid = checkreportid;
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

    public String getInstrumentcode() {
        return this.instrumentcode;
    }
    
    public void setInstrumentcode(String instrumentcode) {
        this.instrumentcode = instrumentcode;
    }

    public String getCheckresult() {
        return this.checkresult;
    }
    
    public void setCheckresult(String checkresult) {
        this.checkresult = checkresult;
    }

	public String getCheckitemname() {
		return checkitemname;
	}

	public void setCheckitemname(String checkitemname) {
		this.checkitemname = checkitemname;
	}

	public String getInstrumentname() {
		return instrumentname;
	}

	public void setInstrumentname(String instrumentname) {
		this.instrumentname = instrumentname;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubsendsampleno() {
		return subsendsampleno;
	}

	public void setSubsendsampleno(String subsendsampleno) {
		this.subsendsampleno = subsendsampleno;
	}
}