package com.zhirui.business.lib.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.kepu.utils.PageModel;

import com.zhirui.business.lib.bean.SubSendSampleNoQrCode;
import com.zhirui.business.lib.dao.CheckImageDao;
import com.zhirui.business.lib.dao.SubSendSampleNoQrCodeDao;
import com.zhirui.business.lib.service.SubSendSampleNoQrCodeService;

//李沅罡
@Repository("subSendSampleNoQrCodeService")
public class SubSendSampleNoQrCodeServiceImpl implements SubSendSampleNoQrCodeService {
	private static final Log log = LogFactory.getLog(CheckItemServiceImpl.class);
	
	@Autowired
	private SubSendSampleNoQrCodeDao subSendSampleNoQrCodeDao;
	
	//李沅罡 返回一个SubSendSampleNoQrCode对象 
	@Override
	public SubSendSampleNoQrCode getSubSendSampleNoQrCode(String subSendSampleNo) {
		String where = "";
		List<Object> queryParamList = new ArrayList<Object>();
		where =" where subsendsampleno like ?";
		queryParamList.add(subSendSampleNo);
		PageModel<SubSendSampleNoQrCode> ap = subSendSampleNoQrCodeDao.find(where, queryParamList.toArray(new Object[0]), null, 1, 1);
		if (ap!=null&&ap.getList().size()>0)
		    return ap.getList().get(0);
		else
			return null;
	}

	@Override
	public SubSendSampleNoQrCode getSubSendSampleNoQrCodeBySendSampleId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubSendSampleNoQrCode addSubSendSampleNoQrCode(
			SubSendSampleNoQrCode subSendSampleNoQrCode) {
		try {
			int id = (Integer) subSendSampleNoQrCodeDao.save(subSendSampleNoQrCode);
		} catch (InternalError e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public SubSendSampleNoQrCode updateSubSendSampleNoQrCode(
			SubSendSampleNoQrCode subSendSampleNoQrCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, String> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
