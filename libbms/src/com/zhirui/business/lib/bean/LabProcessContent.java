package com.zhirui.business.lib.bean;

public class LabProcessContent implements java.io.Serializable {
	private Integer id;
	private String labprocesscontent;
	
	
	
	@Override
	public String toString() {
		return "LabProcessContent [id=" + id + ", labprocesscontent="
				+ labprocesscontent + "]";
	}
	public LabProcessContent(Integer id, String labprocesscontent) {
		super();
		this.id = id;
		this.labprocesscontent = labprocesscontent;
	}
	public LabProcessContent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLabprocesscontent() {
		return labprocesscontent;
	}
	public void setLabprocesscontent(String labprocesscontent) {
		this.labprocesscontent = labprocesscontent;
	}
	
}
