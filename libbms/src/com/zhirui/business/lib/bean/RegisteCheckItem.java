package com.zhirui.business.lib.bean;

/**
 * SendCheckItem entity. @author MyEclipse Persistence Tools
 */

public class RegisteCheckItem  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
     private Integer sampleregisteid;
     private Integer checkitem;
     private Integer examineuser;

     private String checkitemname;
     private String examineusername;

    // Constructors

    /** default constructor */
    public RegisteCheckItem() {
    }

	/** minimal constructor */
    public RegisteCheckItem(Integer sampleregisteid) {
        this.sampleregisteid = sampleregisteid;
    }
    
    /** full constructor */
    public RegisteCheckItem(Integer sampleregisteid, Integer checkitem, Integer examineuser) {
        this.sampleregisteid = sampleregisteid;
        this.checkitem = checkitem;
        this.examineuser = examineuser;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSampleregisteid() {
        return this.sampleregisteid;
    }
    
    public void setSampleregisteid(Integer sampleregisteid) {
        this.sampleregisteid = sampleregisteid;
    }

    public Integer getCheckitem() {
        return this.checkitem;
    }
    
    public void setCheckitem(Integer checkitem) {
        this.checkitem = checkitem;
    }

    public Integer getExamineuser() {
        return this.examineuser;
    }
    
    public void setExamineuser(Integer examineuser) {
        this.examineuser = examineuser;
    }

	public String getCheckitemname() {
		return checkitemname;
	}

	public void setCheckitemname(String checkitemname) {
		this.checkitemname = checkitemname;
	}

	public String getExamineusername() {
		return examineusername;
	}

	public void setExamineusername(String examineusername) {
		this.examineusername = examineusername;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}