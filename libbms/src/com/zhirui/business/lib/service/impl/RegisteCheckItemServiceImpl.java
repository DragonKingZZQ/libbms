package com.zhirui.business.lib.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import cn.kepu.utils.PageModel;

import com.zhirui.business.lib.bean.RegisteCheckItem;
import com.zhirui.business.lib.dao.CheckItemDao;
import com.zhirui.business.lib.dao.RegisteCheckItemDao;
import com.zhirui.business.lib.service.RegisteCheckItemService;

@Repository("registeCheckItemService")
public class RegisteCheckItemServiceImpl implements RegisteCheckItemService {
	
	private static final Log log = LogFactory.getLog(RegisteCheckItemServiceImpl.class);

	@Autowired
	private RegisteCheckItemDao registeCheckItemDao;
	@Autowired
	private CheckItemDao checkItemDao;
	
	//曾智琴
	@Override
	public PageModel<RegisteCheckItem> getRegisteCheckItem(int sampleregisteid,int checkuser,int pageNo, int pageSize) {
		PageModel<RegisteCheckItem> page = registeCheckItemDao.find(" where sampleregisteid='"+sampleregisteid+"'"+"and examineuser='"+checkuser+"'",null, null, pageNo, pageSize);
		return page;
	}
	
	@Override
	public PageModel<RegisteCheckItem> getRegisteCheckItem(int sampleregisteid,int pageNo, int pageSize) {
		PageModel<RegisteCheckItem> page = registeCheckItemDao.find(" where sampleregisteid='"+sampleregisteid+"'",null, null, pageNo, pageSize);
		return page;
	}
	
	
	@Override
	public RegisteCheckItem getRegisteCheckItem(int id){
		  return registeCheckItemDao.get(id);
	}
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#removeExample(int)
	 */
	@Override
	public boolean removeRegisteCheckItem(int id) {
		try {
			//删除通用表数据
			registeCheckItemDao.delete(id);
			return true;
		} catch (Exception e) {
			log.error("删除"+id+"失败", e);
			return false;
		}
	}

	@Override
	public RegisteCheckItem addRegisteCheckItem(RegisteCheckItem registeCheckItem) {
		try {
			int id = (Integer)registeCheckItemDao.save(registeCheckItem);
			return registeCheckItem;
		} catch (Exception e) {
			log.error("添加"+e.getMessage()+"失败", e);
			return null;
		}
	}
	@Override
	public RegisteCheckItem updateRegisteCheckItem(RegisteCheckItem registeCheckItem) {
		try {
			registeCheckItemDao.update(registeCheckItem);
			return registeCheckItem;
		} catch (Exception e) {
			log.error("添加"+e.getMessage()+"失败", e);
			return null;
		}
	}
	@Override
	public boolean removeRegisteCheckItems(int[] ids) {
		for(int id : ids) {
			removeRegisteCheckItem(id);
		}
		return true;
	}
}
