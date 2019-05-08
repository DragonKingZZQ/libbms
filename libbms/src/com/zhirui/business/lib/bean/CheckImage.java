package com.zhirui.business.lib.bean;

public class CheckImage {
	private int id;
	private int sendsampleid;
	private String imageurl;
	private String imagename;
	private String sampleno;
	
	public CheckImage() {
		
	}
	public CheckImage(int id, int sendsampleid, String imageurl,String imagename,String sampleno) {
		super();
		this.id = id;
		this.sendsampleid = sendsampleid;
		this.imageurl = imageurl;
		this.imagename = imagename;
		this.sampleno = sampleno;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSendsampleid() {
		return sendsampleid;
	}
	public void setSendsampleid(int sendsampleid) {
		this.sendsampleid = sendsampleid;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	public String getSampleno() {
		return sampleno;
	}
	public void setSampleno(String sampleno) {
		this.sampleno = sampleno;
	}
	
	
	
}
