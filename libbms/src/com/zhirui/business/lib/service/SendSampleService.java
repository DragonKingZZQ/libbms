package com.zhirui.business.lib.service;

import cn.kepu.utils.PageModel;
import com.zhirui.business.lib.bean.SampleRegiste;
import com.zhirui.business.lib.bean.SendSample;

public interface SendSampleService {
	/**
	 * 分页查询的方法实现
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<SendSample> getSendSample(SendSample sendSample, int pageNo, int pageSize);
	
	/**
	 * 首页提示信息的查询功能
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<SendSample> getMySendSample(int pageNo, int pageSize);
	/**
	 * 首页提示信息的查询功能
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<SendSample> getWarnSendSample(int pageNo, int pageSize);
	
	public PageModel<SendSample> getWarnSendSampleForLeader(int pageNo, int pageSize);
	/**
	 * 获取数据的方法
	 * @param id
	 * @return
	 */
	public SendSample getSendSample(int id);
	/**
	 * 删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeSendSample(int id);
	/**
	 * 批量删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeSendSamples(int[] id);
	/**
	 * 插入新数据的方法实现
	 * @param example
	 * @return
	 */
	public SendSample addSendSample(SendSample sendSample);
	
	/**
	 * 更新数据的方法实现
	 * @param example
	 * @return
	 */
	public SendSample updateSendSample(SendSample sendSample);
	/**
	 * 查询数据是否已经存在
	 * @param 
	 * @return
	 */
	public boolean isExists(SendSample sendSample);
	
	//修改状态
	public boolean setStatus(SendSample sendSample);
}
