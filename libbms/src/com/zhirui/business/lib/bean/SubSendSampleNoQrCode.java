package com.zhirui.business.lib.bean;

public class SubSendSampleNoQrCode {
	private Integer id;
    private String subsendsampleno;
    private String qrcodeurl;
    
    
	public SubSendSampleNoQrCode() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SubSendSampleNoQrCode(Integer id, String subsendsampleno,
			String qrcodeurl) {
		super();
		this.id = id;
		this.subsendsampleno = subsendsampleno;
		this.qrcodeurl = qrcodeurl;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSubsendsampleno() {
		return subsendsampleno;
	}
	public void setSubsendsampleno(String subsendsampleno) {
		this.subsendsampleno = subsendsampleno;
	}
	public String getQrcodeurl() {
		return qrcodeurl;
	}
	public void setQrcodeurl(String qrcodeurl) {
		this.qrcodeurl = qrcodeurl;
	}
    
    
    
}
