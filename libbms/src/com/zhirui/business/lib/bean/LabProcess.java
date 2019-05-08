package com.zhirui.business.lib.bean;

import java.util.Date;

public class LabProcess implements java.io.Serializable{
	private Integer id;
	private String labprocesstitle;
	private String labprocesscontent;
	private Date createdate;
	private Integer validflag;
	
	
	
	
	@Override
	public String toString() {
		return "LabProcess [id=" + id + ", labprocesstitle=" + labprocesstitle
				+ ", labprocesscontent=" + labprocesscontent + ", createdate="
				+ createdate + ", validflag=" + validflag + "]";
	}
	public LabProcess(Integer id, String labprocesstitle,
			String labprocesscontent, Date createdate, Integer validflag) {
		super();
		this.id = id;
		this.labprocesstitle = labprocesstitle;
		this.labprocesscontent = labprocesscontent;
		this.createdate = createdate;
		this.validflag = validflag;
	}
	public LabProcess() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLabprocesstitle() {
		return labprocesstitle;
	}
	public void setLabprocesstitle(String labprocesstitle) {
		this.labprocesstitle = labprocesstitle;
	}
	public String getLabprocesscontent() {
		return labprocesscontent;
	}
	public void setLabprocesscontent(String labprocesscontent) {
		this.labprocesscontent = labprocesscontent;
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
