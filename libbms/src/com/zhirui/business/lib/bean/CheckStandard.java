package com.zhirui.business.lib.bean;

import java.util.Date;

public class CheckStandard {
	// Fields    

    private Integer id;
    private String standardcode;
    private String standardcontent;
    private Date createdate;
    private Integer validflag;
    
    
    
    
    
    
	public CheckStandard(Integer id, String standardcode,
			String standardcontent,Date createdate,
			Integer validflag) {
		super();
		this.id = id;
		this.standardcode = standardcode;
		this.standardcontent = standardcontent;
		this.createdate = createdate;
		this.validflag = validflag;
	}
	public CheckStandard() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStandardcode() {
		return standardcode;
	}
	public void setStandardcode(String standardcode) {
		this.standardcode = standardcode;
	}
	public String getStandardcontent() {
		return standardcontent;
	}
	public void setStandardcontent(String standardcontent) {
		this.standardcontent = standardcontent;
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
