package com.zhirui.business.lib.service;


import java.util.Map;





import cn.kepu.utils.PageModel;

import com.zhirui.business.lib.bean.CheckImage;


//曾智琴
public interface CheckImageService {
	

	/**
	 * 获取数据的方法
	 * @param id
	 * @return
	 */
	public CheckImage getCheckImage(int id);
	/**
	 * 根据派样单id获取数据的方法
	 * @param id
	 * @return
	 */
	public PageModel<CheckImage> getCheckImageBySendSampleId(int id);
	
	/**
	 * 插入新数据的方法实现
	 * @param example
	 * @return
	 */
	public CheckImage addCheckImage(CheckImage checkimage);
	
	/**
	 * 更新数据的方法实现
	 * @param example
	 * @return
	 */
	public CheckImage updateCheckImage(CheckImage checkimage);
	
	public Map<Integer,String> getAll();
	PageModel<CheckImage> getCheckImageBySampleno(String sampleno);
	
	
	
}
