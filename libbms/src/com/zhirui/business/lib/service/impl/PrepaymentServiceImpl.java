package com.zhirui.business.lib.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import cn.kepu.utils.PageModel;
import cn.kepu.utils.StringUtils;

import com.zhirui.business.common.Constants;
import com.zhirui.business.lib.bean.EntrustCompany;
import com.zhirui.business.lib.bean.Prepayment;
import com.zhirui.business.lib.dao.EntrustCompanyDao;
import com.zhirui.business.lib.dao.PrepaymentDao;
import com.zhirui.business.lib.dao.SampleRegisteDao;
import com.zhirui.business.lib.service.PrepaymentService;

@Repository("prepaymentService")
public class PrepaymentServiceImpl implements PrepaymentService {
	
	private static final Log log = LogFactory.getLog(PrepaymentServiceImpl.class);

	@Autowired
	private PrepaymentDao prepaymentDao;
	@Autowired
	private SampleRegisteDao sampleRegisteDao;
	@Autowired
	private EntrustCompanyDao entrustCompanyDao;

	@Override
	public PageModel<Prepayment> getPrepayment(Prepayment prepayment, int pageNo, int pageSize) {
		String where = "where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();
		if(prepayment != null) {
			if (prepayment.getId()!=null) {
				where += " and entrustcompanyid=?";
				queryParamList.add(prepayment.getId());
			}
			if (!StringUtils.isEmpty(prepayment.getUsername())) {
				where += " and username like ?";
				queryParamList.add("%"+prepayment.getUsername()+"%");
			}
			if (!StringUtils.isEmpty(prepayment.getEntrustcompanyStr())) {
				String tmp = "";
				PageModel<EntrustCompany> pec = entrustCompanyDao.find(" where entrustcompany like '%"+prepayment.getEntrustcompanyStr()+"%'", null,null,1,Constants.MAX_COUNT);
				if (pec!=null&&pec.getList().size()>0){
					for(int i=0;i<pec.getList().size();i++){
						tmp +=pec.getList().get(i).getId();
						if (i+1!=pec.getList().size()){
							tmp +=",";
						}
					}
                    where +=" and entrustcompanyid in ("+tmp+")";
				}else{
					where +=" and 1=2";
				}
		      }
		    	  
		
				if (prepayment.getPrepaydate()!=null&&!prepayment.getPrepaydate().equals("")) {
					where += " and prepaydate = ?";
					Calendar cal = Calendar.getInstance();
					cal.setTime(prepayment.getPrepaydate());
					queryParamList.add(cal.getTime());
				} 
		}
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		PageModel<Prepayment> page = prepaymentDao.find(where, queryParamList.toArray(new Object[0]), orderby, pageNo, pageSize);
		return page;
	}
	
	@Override
	public Prepayment getPrepayment(int id){
		  return prepaymentDao.get(id);
	}
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#removeExample(int)
	 */
	@Override
	public boolean removePrepayment(int id) {
		try {
			//删除通用表数据
			prepaymentDao.delete(id);
			return true;
		} catch (Exception e) {
			log.error("删除"+id+"失败", e);
			return false;
		}
	}

	@Override
	public Prepayment addPrepayment(Prepayment prepayment) {
		try {
			int id = (Integer)prepaymentDao.save(prepayment);
			return prepayment;
		} catch (Exception e) {
			log.error("添加"+e.getMessage()+"失败", e);
			return null;
		}
	}
    
	@Override
	public boolean removePrepayments(int[] ids) {
		for(int id : ids) {
			removePrepayment(id);
		}
		return true;
	}

	@Override
	public Prepayment updatePrepayment(Prepayment prepayment) {
		try {
			prepaymentDao.update(prepayment);
		} catch (Exception e) {
			log.error("更新"+prepayment.getId()+"失败", e);
			return null;
		}
		return prepayment;
	}
	
}
