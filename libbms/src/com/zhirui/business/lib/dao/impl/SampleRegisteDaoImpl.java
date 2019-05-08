package com.zhirui.business.lib.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import cn.kepu.base.dao.DaoSupport;
import cn.kepu.utils.PageModel;

import com.zhirui.business.lib.bean.SampleRegiste;
import com.zhirui.business.lib.dao.SampleRegisteDao;


@Repository("sampleRegisteDao")
public class SampleRegisteDaoImpl extends DaoSupport<SampleRegiste> implements
		SampleRegisteDao {

	@Override
	public String getSerilsNumber(String yymmdd) {
		StringBuffer where = new StringBuffer("where sampleno like ?");
		List<Object> queryParamList = new ArrayList<Object>();
		queryParamList.add(yymmdd+"%");
		PageModel<SampleRegiste> page = find(where.toString(), queryParamList.toArray(new Object[0]), null, 1, 1000);
		if (page==null||page.getList().size()==0){
        	return yymmdd+"-YFC-001";
        }
		List<SampleRegiste> sList = page.getList();
        String code = "",tmpcode="";
        for(SampleRegiste sr : sList){
        	if (code.equals("")){
        		code = sr.getSampleno();
        		code = code.substring(code.lastIndexOf("-")+1);
        		tmpcode = code;
        	}
        	tmpcode = sr.getSampleno();
        	tmpcode = tmpcode.substring(tmpcode.lastIndexOf("-")+1);
        	if (Integer.parseInt(code)<Integer.parseInt(tmpcode))
        		code = tmpcode;
        }
        //不足3位补足
        int newcode = Integer.parseInt(code)+1;
        code = String.valueOf(newcode);
        while(code.length()<3)
        	code = "0"+code;
        code = yymmdd+"-YFC-"+code;
		return code;
	}

	@Override
	public boolean updateStatus(SampleRegiste sampleRegiste) {
		if (sampleRegiste==null) return false;
		sampleRegiste.setStatuschangedate(new Date());
		try{
		    super.update(sampleRegiste);
		    return true;
		}catch(Exception e){
			return false;
		}
	}

}
