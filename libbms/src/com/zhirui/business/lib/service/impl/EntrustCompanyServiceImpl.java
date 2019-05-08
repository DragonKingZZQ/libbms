package com.zhirui.business.lib.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.kepu.utils.PageModel;

import com.zhirui.business.common.Constants;
import com.zhirui.business.lib.bean.EntrustCompany;
import com.zhirui.business.lib.bean.SampleRegiste;
import com.zhirui.business.lib.dao.EntrustCompanyDao;
import com.zhirui.business.lib.dao.SampleRegisteDao;
import com.zhirui.business.lib.service.EntrustCompanyService;

@Repository("entrustCompanyService")
public class EntrustCompanyServiceImpl implements EntrustCompanyService {
	
	private static final Log log = LogFactory.getLog(EntrustCompanyServiceImpl.class);

	@Autowired
	private EntrustCompanyDao entrustCompanyDao;
	@Autowired
	private SampleRegisteDao sampleRegisteDao;
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#getExamples(com.zhirui.business.bean.Example, int, int)
	 */
	@Override
	public PageModel<EntrustCompany> getEntrustCompany(EntrustCompany entrustCompany, int pageNo, int pageSize) {
		String where = "where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		PageModel<EntrustCompany> page = entrustCompanyDao.find(where, queryParamList.toArray(new Object[0]), orderby, pageNo, pageSize);
		return page;
	}
	
	@Override
	public EntrustCompany getEntrustCompany(int id){
		  return entrustCompanyDao.get(id);
	}
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#removeExample(int)
	 */
	@Override
	public boolean removeEntrustCompany(int id) {
		try {
			//删除通用表数据
			entrustCompanyDao.delete(id);
			return true;
		} catch (Exception e) {
			log.error("删除"+id+"失败", e);
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#addExample(com.zhirui.business.bean.Example)
	 */
	@Override
	public EntrustCompany addEntrustCompany(EntrustCompany entrustCompany) {
		try {
			int id = (Integer)entrustCompanyDao.save(entrustCompany);
			entrustCompany = entrustCompanyDao.get(id);
			return entrustCompany;
		} catch (Exception e) {
			log.error("添加"+e.getMessage()+"失败", e);
			return null;
		}
	}
    
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#updateExample(com.zhirui.business.bean.Example)
	 */
	@Override
	public EntrustCompany updateEntrustCompany(EntrustCompany entrustCompany) {
		try {
			entrustCompanyDao.update(entrustCompany);
		} catch (Exception e) {
			log.error("更新"+entrustCompany.getId()+"失败", e);
			return null;
		}
		return entrustCompany;
	}
	@Override
	public boolean removeEntrustCompanys(int[] ids) {
		for(int id : ids) {
			removeEntrustCompany(id);
		}
		return true;
	}

	@Override
	public Map<Integer,String> getAll() {
		Map<Integer, String> companys = new HashMap<Integer, String>();
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("id", "asc");
		PageModel<EntrustCompany> page = entrustCompanyDao.find(null, null, orderby, 1, Constants.MAX_COUNT);
		if (page != null) {
			for(EntrustCompany ec : page.getList()){
				companys.put(ec.getId(),ec.getEntrustcompany());
			}
			return companys;
		}
		return null;
	}
	
	@Override
	public EntrustCompany getEntrustCompany(String name) {
		String where = "where entrustcompany='"+name+"'";
		PageModel<EntrustCompany> page = entrustCompanyDao.find(where, null, null, 1, Constants.MAX_COUNT);
		if (page != null&&page.getList().size()>=1) {
			return page.getList().get(0);
		}
		return null;
	}

	@Override
	public Map<Integer, String> getNOPayOff() {
		String where = "";
		Map<Integer, String> companys = new HashMap<Integer, String>();
		PageModel<SampleRegiste> sr = sampleRegisteDao.find(" where balancemoney>0 ",null,null,1,Constants.MAX_COUNT);
		String tmp = "";
		for(int i=0;i<sr.getList().size();i++){
			tmp +=sr.getList().get(i).getEntrustcompany();
			if (i+1!=sr.getList().size()){
				tmp +=",";
			}
		}
		if (!tmp.equals("")){
			where = " where id in ("+tmp+")";
		}
		PageModel<EntrustCompany> page = entrustCompanyDao.find(where, null, null, 1, Constants.MAX_COUNT);
		if (page != null) {
			for(EntrustCompany ec : page.getList()){
				companys.put(ec.getId(),ec.getEntrustcompany());
			}
			return companys;
		}
		return null;
	}
}
