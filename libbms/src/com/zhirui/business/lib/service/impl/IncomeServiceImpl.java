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
import com.zhirui.business.lib.bean.Income;
import com.zhirui.business.lib.bean.SampleRegiste;
import com.zhirui.business.lib.dao.EntrustCompanyDao;
import com.zhirui.business.lib.dao.IncomeDao;
import com.zhirui.business.lib.dao.SampleRegisteDao;
import com.zhirui.business.lib.service.IncomeService;

@Repository("incomeService")
public class IncomeServiceImpl implements IncomeService {
	
	private static final Log log = LogFactory.getLog(IncomeServiceImpl.class);

	@Autowired
	private IncomeDao incomeDao;
	@Autowired
	private SampleRegisteDao sampleRegisteDao;
	@Autowired
	private EntrustCompanyDao entrustCompanyDao;

	@Override
	public PageModel<Income> getIncome(Income income, int pageNo, int pageSize) {
		String where = "where 1=1";
		List<Object> queryParamList = new ArrayList<Object>();
		if(income != null) {
			if (!StringUtils.isEmpty(income.getUsername())) {
				where += " and username like ?";
				queryParamList.add("%"+income.getUsername()+"%");
			}
			if (!StringUtils.isEmpty(income.getEntrustcompanyStr())) {
					String tmp = "";
					PageModel<EntrustCompany> pec = entrustCompanyDao.find(" where entrustcompany like '%"+income.getEntrustcompanyStr()+"%'", null,null,1,Constants.MAX_COUNT);
					if (pec!=null&&pec.getList().size()>0){
						for(int i=0;i<pec.getList().size();i++){
							tmp +=pec.getList().get(i).getId();
							if (i+1!=pec.getList().size()){
								tmp +=",";
							}
						}
						
						PageModel<SampleRegiste> psr = sampleRegisteDao.find(" where entrustcompany in ("+tmp+")", null,null,1,Constants.MAX_COUNT);
						if (psr!=null&&psr.getList().size()>0){
							where +=" and (";
							for(int i=0;i<psr.getList().size();i++){
                               where += " sampleregisteids like '%|"+psr.getList().get(i).getId()+"|%'";
                               if (i+1!=psr.getList().size()){
                            	   where +=" or ";
                               }
							}
							where +=")";
						}
					}else{
						where +=" and 1=2 ";
					}
			}
			
			if (income.getPaydate()!=null&&!income.getPaydate().equals("")) {
				where += " and paydate = ?";
				Calendar cal = Calendar.getInstance();
				cal.setTime(income.getPaydate());
				queryParamList.add(cal.getTime());
			} 
		}
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		PageModel<Income> page = incomeDao.find(where, queryParamList.toArray(new Object[0]), orderby, pageNo, pageSize);
		return page;
	}
	
	@Override
	public Income getIncome(int id){
		  return incomeDao.get(id);
	}
	/* (non-Javadoc)
	 * @see com.zhirui.business.service.ExampleService#removeExample(int)
	 */
	@Override
	public boolean removeIncome(int id) {
		try {
			//删除通用表数据
			incomeDao.delete(id);
			return true;
		} catch (Exception e) {
			log.error("删除"+id+"失败", e);
			return false;
		}
	}

	@Override
	public Income addIncome(Income income) {
		try {
			int id = (Integer)incomeDao.save(income);
			return income;
		} catch (Exception e) {
			log.error("添加"+e.getMessage()+"失败", e);
			return null;
		}
	}
    
	@Override
	public boolean removeIncomes(int[] ids) {
		for(int id : ids) {
			removeIncome(id);
		}
		return true;
	}

	@Override
	public Income updateIncome(Income income) {
		try {
			incomeDao.update(income);
		} catch (Exception e) {
			log.error("更新"+income.getId()+"失败", e);
			return null;
		}
		return income;
	}
	
}
