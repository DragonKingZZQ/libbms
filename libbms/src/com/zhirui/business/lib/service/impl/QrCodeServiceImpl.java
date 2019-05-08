package com.zhirui.business.lib.service.impl;

import java.io.File;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.kepu.utils.PageModel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.zhirui.business.common.Constants;
import com.zhirui.business.lib.bean.LabProcessContent;
import com.zhirui.business.lib.bean.SampleRegiste;
import com.zhirui.business.lib.bean.SendCheckItem;
import com.zhirui.business.lib.bean.SubSendSampleNoQrCode;
import com.zhirui.business.lib.dao.SampleRegisteDao;
import com.zhirui.business.lib.dao.SendCheckItemDao;
import com.zhirui.business.lib.dao.SubSendSampleNoQrCodeDao;
import com.zhirui.business.lib.service.QrCodeService;
import com.zhirui.business.lib.service.SubSendSampleNoQrCodeService;
@Repository("QrCodeService")
public class QrCodeServiceImpl implements QrCodeService{
//	@Autowired
//	SampleRegisteDao sampleRegisteDao;
	@Autowired
	SendCheckItemDao sendCheckItemDao;
	@Autowired
	SubSendSampleNoQrCodeDao subSendSampleNoQrCodeDao;
	@Autowired
	SubSendSampleNoQrCodeService subSendSampleNoQrCodeService;
	@Override
	public List<String> getAll() {
		Map<Integer, String> contents = new HashMap<Integer, String>();
		Map<String, String> orderby = new HashMap<String, String>();
		List<String> contentliList = new ArrayList<String>();
		orderby.put("id", "asc");
//		PageModel<> page = sampleRegisteDao.find(null, null, orderby, 1, Constants.MAX_COUNT);
		PageModel<SendCheckItem> page = sendCheckItemDao.find(null, null, orderby, 1, Constants.MAX_COUNT);
		
		if (page != null) {
//			for(SampleRegiste ec : page.getList()){
//				contents.put(ec.getId(),ec.getSampleno());
//				contentliList.add(ec.getSampleno());
//			}
			for(SendCheckItem ec : page.getList()){
				contents.put(ec.getId(),ec.getSubsendsampleno());
				contentliList.add(ec.getSubsendsampleno());
			}
			return  contentliList;
		}
		return null;
	}
	@Override
	public void creatQrCode(String sampleno) {
		
		
		System.out.println("service "+sampleno);
		  //设置二维码像素  
        int width = 300;  
        int height = 300;  
        //要生成什么格式的二维码  
        String format = "png";  
     	//二维码当中要存储什么信息  时间戳+subsendsampleno new Date() +"."+;
//        DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
//	     
//	     String formatDate = format1.format(new Date());
        Date date = new Date();
        String dateStr = new SimpleDateFormat("-yyyyMMdd-HHmm").format(date);
        String content = sampleno;  
        HashMap hints = new HashMap();  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        //设置纠错率，分为L、M、H三个等级，等级越高，纠错率越高，但存储的信息越少  
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);  
        //设置一下边距，默认是5  
        hints.put(EncodeHintType.MARGIN, 2);  
        try {  
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);  
            
            
            String path = ServletActionContext.getServletContext().getRealPath("/");
            Path file = new File(path+"/SubSendSampleNoQrCode/"+content + dateStr +".png").toPath();
            SubSendSampleNoQrCode s = new SubSendSampleNoQrCode();
            s.setSubsendsampleno(sampleno);
            s.setQrcodeurl("/SubSendSampleNoQrCode/"+content+dateStr+".png");
            subSendSampleNoQrCodeService.addSubSendSampleNoQrCode(s);
            
           // Path file = new File("F:/code/"+content+".png").toPath();//前提是E盘下有code这个目录  
            
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}

}
