package com.zhirui.business.lib.service;

import java.util.Map;

import cn.kepu.utils.PageModel;

import com.zhirui.business.lib.bean.SampleRegiste;
import com.zhirui.business.lib.bean.Statistics;

public interface SampleRegisteService {
	/**
	 * 分页查询的方法实现
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<SampleRegiste> getSampleRegiste(SampleRegiste sampleRegiste, int pageNo, int pageSize);
	/**
	 * 分页查询的方法实现
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<SampleRegiste> getNoFinishTask(int pageNo, int pageSize);
	public PageModel<SampleRegiste> getNewNoPayTask(int pageNo, int pageSize);

	public PageModel<SampleRegiste> getNoPayOff(Integer companyid,int pageNo, int pageSize);

	public PageModel<SampleRegiste> getNoPayOff(Statistics statistics,int pageNo, int pageSize);

	/**
	 * 获取数据的方法
	 * @param id
	 * @return
	 */
	public SampleRegiste getSampleRegiste(int id);
	/**
	 * 获取数据的方法
	 * @param 编号
	 * @return
	 */
	public SampleRegiste getSampleRegisteByCode(String code);
	/**
	 * 删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeSampleRegiste(int id);
	/**
	 * 批量删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeSampleRegistes(int[] id);
	/**
	 * 插入新数据的方法实现
	 * @param example
	 * @return
	 */
	public SampleRegiste addSampleRegiste(SampleRegiste sampleRegiste);
	
	/**
	 * 更新数据的方法实现
	 * @param example
	 * @return
	 */
	public SampleRegiste updateSampleRegiste(SampleRegiste sampleRegiste);
	/**
	 * 查询数据是否已经存在
	 * @param 
	 * @return
	 */
	public boolean isExists(SampleRegiste sampleRegiste);
	
	public String getSerilsNo(String yymmdd);
	
	//修改状态
	public boolean setStatus(SampleRegiste sampleRegiste);
	
	public Map<Integer,String> getSubmitRegisted();
	
	public Map<Integer,String> getFinishRegisted();
	
	public PageModel<SampleRegiste> getRegisteForExamine(int userid,
			int pageNo, int pageSize);
	
	public Map<Integer,String> getAllRegisted();
	
	SampleRegiste getSampleRegisteBySampleno(String sampleno);
}
