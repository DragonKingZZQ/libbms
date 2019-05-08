package com.zhirui.business.common.bean;

public class SampleCategory implements java.io.Serializable{
	private Integer id;
	private String category;
	private String remain;
	
	
	
	public SampleCategory() {
		
	}
	public SampleCategory(Integer id, String category, String remain) {
		super();
		this.id = id;
		this.category = category;
		this.remain = remain;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getRemain() {
		return remain;
	}
	public void setRemain(String remain) {
		this.remain = remain;
	}
	
	
}
