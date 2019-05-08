package com.zhirui.business.lib.service;

import java.util.Map;

import com.zhirui.business.lib.bean.SubSendSampleNoQrCode;

public interface SubSendSampleNoQrCodeService {
	
	
	/**
	 * 获取数据的方法
	 * @param id
	 * @return
	 */
	public SubSendSampleNoQrCode getSubSendSampleNoQrCode(String subsendsampleno);
	/**
	 * 根据派样单id获取数据的方法
	 * @param id
	 * @return
	 */
	public SubSendSampleNoQrCode getSubSendSampleNoQrCodeBySendSampleId(int id);
	
	/**
	 * 插入新数据的方法实现
	 * @param example
	 * @return
	 */
	public SubSendSampleNoQrCode addSubSendSampleNoQrCode(SubSendSampleNoQrCode subSendSampleNoQrCode);
	
	/**
	 * 更新数据的方法实现
	 * @param example
	 * @return
	 */
	public SubSendSampleNoQrCode updateSubSendSampleNoQrCode(SubSendSampleNoQrCode subSendSampleNoQrCode);
	
	public Map<Integer,String> getAll();
	
	
}
