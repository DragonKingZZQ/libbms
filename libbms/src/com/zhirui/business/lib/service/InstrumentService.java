package com.zhirui.business.lib.service;

import java.util.Map;

import cn.kepu.utils.PageModel;
import com.zhirui.business.lib.bean.Instrument;

public interface InstrumentService {
	/**
	 * 分页查询的方法实现
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @return 分页对象
	 */
	public PageModel<Instrument> getInstrument(Instrument instrument, int pageNo, int pageSize);

	/**
	 * 获取数据的方法
	 * @param id
	 * @return
	 */
	public Instrument getInstrument(int id);
	/**
	 * 删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeInstrument(int id);
	/**
	 * 批量删除数据的方法实现
	 * @param id
	 * @return
	 */
	public boolean removeInstruments(int[] id);
	
	public Instrument updateInstrument(Instrument instrument);
	/**
	 * 插入新数据的方法实现
	 * @param example
	 * @return
	 */
	public Instrument addInstrument(Instrument instrument);
	
	public Map<Integer,String> getAll();
	
	public Map<Integer,String> getAllValid();
	//是否存在，存在返回true
	public boolean isExists(Integer iid,String name);
}
