package com.zhirui.business.lib.service;

import cn.kepu.utils.PageModel;

import com.zhirui.business.lib.bean.SendCheckItem;
import com.zhirui.business.lib.bean.SendSample;

public interface SendCheckItemService {
	/**
	 * 分页查询的方法实现
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<SendCheckItem> getSendCheckItem(int sendsampleid,int pageNo, int pageSize);
    //根据派样单获得
	public PageModel<SendCheckItem> getSendCheckItem(String[] sendsampleid,int pageNo, int pageSize);

	public PageModel<SendCheckItem> getSendCheckItemForRegiste(String registeno,int pageNo, int pageSize);
	/**
	 * 获取数据的方法
	 * @param id
	 * @return
	 */
	public SendCheckItem getSendCheckItem(int id);
	/**
	 * 更新数据的方法实现
	 * @param example
	 * @return
	 */
	public SendCheckItem updateSendCheckItem(SendCheckItem sendCheckItem);
	/**
	 * 删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeSendCheckItem(int id);
	/**
	 * 批量删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeSendCheckItems(int[] id);
	/**
	 * 插入新数据的方法实现
	 * @param example
	 * @return
	 */
	public SendCheckItem addSendCheckItem(SendCheckItem sendCheckItem);
	
	//曾智琴   根据样品编号得到此条信息
	
	SendCheckItem getSendCheckItemBySendSampleid(int sendsampleid);
	SendCheckItem getSendCheckItem(String subSendSampleNo);
	PageModel<SendCheckItem> getSendCheckItem2(String subSendSampleNo);
	//曾智琴
	PageModel<SendCheckItem> getSendCheckItem(String sendsampleid, int pageNo,
			int pageSize);
	
}
