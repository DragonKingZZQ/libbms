package com.zhirui.business.lib.action;

import org.springframework.beans.factory.annotation.Autowired;

import cn.kepu.utils.PageModel;

import com.zhirui.business.lib.bean.CheckItem;
import com.zhirui.business.lib.bean.SendCheckItem;
import com.zhirui.business.lib.bean.SendSample;
import com.zhirui.business.lib.bean.SubSendSampleNoQrCode;
import com.zhirui.business.lib.service.CheckItemService;
import com.zhirui.business.lib.service.SendCheckItemService;
import com.zhirui.business.lib.service.SendSampleService;
import com.zhirui.business.lib.service.SubSendSampleNoQrCodeService;


@SuppressWarnings("serial")
public class SubSendSampleNoQrCodeAction {
	
	@Autowired
	private SendCheckItemService sendCheckItemService;
	@Autowired
	private SendSampleService sendSampleService;
	
	@Autowired
	private CheckItemService checkItemService;
	@Autowired
	private SubSendSampleNoQrCodeService subSendSampleNoQrCodeService;
	private String subSendSampleNo;
	private String info;
	
	public String getSubSendSampleNo() {
		return subSendSampleNo;
	}
	public void setSubSendSampleNo(String subSendSampleNo) {
		this.subSendSampleNo = subSendSampleNo;
	}

	private String sendSampleanme;
	private String itemName;

	
	public String getSendSampleanme() {
		return sendSampleanme;
	}
	public void setSendSampleanme(String sendSampleanme) {
		this.sendSampleanme = sendSampleanme;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	Integer sendsampleid;
	
	public String find(){
		
		SendCheckItem s = sendCheckItemService.getSendCheckItem(subSendSampleNo.trim());
		if(s == null)
		{
			subSendSampleNo = "";
			return "submitOk";
			
		}
		//List<SendCheckItem> s =sendCheckItemService.getSendCheckItem(subSendSampleNo);
		SendSample sp = sendSampleService.getSendSample(s.getSendsampleid()); //根据 取回的sendCheckItem 中的 Sendsampleid取SendSample对象
		
		Integer checkitem = Integer.valueOf(s.getCheckitem()).intValue();
		
		
		CheckItem ci = checkItemService.getCheckItem(checkitem);
		
		System.out.println(s.getSubsendsampleno());//四段小号
		System.out.println(sp.getSendsamplename());//样品名称
		sendSampleanme=sp.getSendsamplename();
		System.out.println(ci.getItemname());//项目名称
		itemName=ci.getItemname();
		//测试 能否取到二维码路径
//		SubSendSampleNoQrCode sssCode = subSendSampleNoQrCodeService.getSubSendSampleNoQrCode(s.getSubsendsampleno());
//		System.out.println("ces"+sssCode.getQrcodeurl());
		return "findOk";
	}
	public String submit(){
		PageModel<SendCheckItem> ss = sendCheckItemService.getSendCheckItem2(subSendSampleNo.trim());
		if(ss!=null && ss.getList().size()==1){
			//SendCheckItem s = sendCheckItemService.getSendCheckItem(subSendSampleNo.trim());
			SendSample sp = sendSampleService.getSendSample(ss.getList().get(0).getSendsampleid()); 
			sp.setFinishflag("2");
			sendSampleService.updateSendSample(sp);
		}
		if(ss!=null && ss.getList().size()>1){
			for(int i=0;i<ss.getList().size();i++){
				SendSample sp = sendSampleService.getSendSample(ss.getList().get(i).getSendsampleid()); 
				sp.setFinishflag("2");
				sendSampleService.updateSendSample(sp);
			}
		}
		
		return "submitOk";
	}
	
	/**
     * 通过 ajax 方式，获取是否已经存在
     * @return
     */
	public String isExists(){
		SendCheckItem s = sendCheckItemService.getSendCheckItem(subSendSampleNo);
		SendSample sp = sendSampleService.getSendSample(s.getSendsampleid()); //根据 取回的sendCheckItem 中的 Sendsampleid取SendSample对象
		int a=Integer.valueOf(sp.getFinishflag());
    	if (a==2){
			info = "{res:true}";
			System.out.println(info);
			return "exists";
		}
     	info = "{res:false}";
     	System.out.println(info);
    	return "exists";  
    }
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
}
