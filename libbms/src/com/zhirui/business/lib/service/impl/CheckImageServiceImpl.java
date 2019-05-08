package com.zhirui.business.lib.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.kepu.utils.PageModel;

import com.zhirui.business.common.Constants;
import com.zhirui.business.lib.bean.CheckImage;
import com.zhirui.business.lib.bean.CheckItem;
import com.zhirui.business.lib.bean.SubSendSampleNoQrCode;
import com.zhirui.business.lib.dao.CheckImageDao;
import com.zhirui.business.lib.service.CheckImageService;
//曾智琴
@Repository("checkImageService")
public class CheckImageServiceImpl implements CheckImageService{

	private static final Log log = LogFactory.getLog(CheckItemServiceImpl.class);
	
	@Autowired
	private CheckImageDao checkImageDao;
	
	@Override
	public CheckImage getCheckImage(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageModel<CheckImage> getCheckImageBySendSampleId(int sendSampleId) {
		String where = "";
		List<Object> queryParamList = new ArrayList<Object>();
		where =" where sendsampleid = ?";
		queryParamList.add(sendSampleId);
		PageModel<CheckImage> ap = checkImageDao.find(where, queryParamList.toArray(new Object[0]), null, 1, Constants.MAX_COUNT);
		if (ap!=null&&ap.getList().size()>0)
		    return ap;
		else
			return null;
		
	}
	//曾智琴
	@Override
	public PageModel<CheckImage> getCheckImageBySampleno(String sampleno) {
		String where = "";
		List<Object> queryParamList = new ArrayList<Object>();
		where =" where sampleno = ?";
		queryParamList.add(sampleno);
		PageModel<CheckImage> ap = checkImageDao.find(where, queryParamList.toArray(new Object[0]), null, 1, Constants.MAX_COUNT);
		if (ap!=null&&ap.getList().size()>0)
		    return ap;
		else
			return null;
		
	}

	
	@Override
	public CheckImage addCheckImage(CheckImage checkimage) {
		try {
			int id = (Integer)checkImageDao.save(checkimage);
			return checkimage;
		} catch (Exception e) {
			log.error("添加"+e.getMessage()+"失败", e);
			return  null;
		}
	}

	@Override
	public CheckImage updateCheckImage(CheckImage checkimage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, String> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
