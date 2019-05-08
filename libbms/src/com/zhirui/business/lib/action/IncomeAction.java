package com.zhirui.business.lib.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhirui.business.common.Constants;
import com.zhirui.business.common.bean.User;
import com.zhirui.business.lib.bean.CheckItem;
import com.zhirui.business.lib.bean.EntrustCompany;
import com.zhirui.business.lib.bean.Income;
import com.zhirui.business.lib.bean.Prepayment;
import com.zhirui.business.lib.bean.RegisteCheckItem;
import com.zhirui.business.lib.bean.RelationUser;
import com.zhirui.business.lib.bean.SampleRegiste;
import com.zhirui.business.lib.bean.Statistics;
import com.zhirui.business.lib.service.EntrustCompanyService;
import com.zhirui.business.lib.service.IncomeService;
import com.zhirui.business.lib.service.PrepaymentService;
import com.zhirui.business.lib.service.RelationUserService;
import com.zhirui.business.lib.service.SampleRegisteService;
import com.zhirui.business.utils.BusinessUtils;
import com.zhirui.business.utils.ExportUtils;

import cn.kepu.base.MessageType;
import cn.kepu.base.action.PageAction;
import cn.kepu.utils.ContextUtils;
import cn.kepu.utils.PageModel;
import cn.kepu.utils.StringUtils;

@SuppressWarnings("serial")
public class IncomeAction extends PageAction<Income> {

	@Autowired
	private IncomeService incomeService;
	@Autowired
	private EntrustCompanyService entrustCompanyService;
	@Autowired
	private SampleRegisteService sampleRegisteService;
	@Autowired
	private PrepaymentService prepaymentService;
	@Autowired
	private RelationUserService relationUserService;
	private Income income;
	public Map<Integer,String> entrustcompanyList;
	public Map<Integer,String> regList;
	private Map<Integer,String> regvalueList;
	//取不同的登记单信息
	private int[] registeid;
	private int myregid;
	private String info;
	
	private int companyid;
	private String username;
	private SampleRegiste sampleRegiste;

	/**
	 * 初始化 检验用户 委托单位 检验项目 资质等
	 */
	private void initData(){
		entrustcompanyList = entrustCompanyService.getAll();
	}
	/**
	 * 登记统计列表功能
	 * @return
	 */
	public String list() {
		initData();
		pageModel = incomeService.getIncome(income, pageNo, pageSize);
		//处理付款单位名称
		for(Income p:pageModel.getList()){
		   EntrustCompany ec=entrustCompanyService.getEntrustCompany(p.getEntrustcompanyid());
		   p.setEntrustcompanyStr(ec.getEntrustcompany());
		}
		return LIST;
	}
	 /**
     * 通过 ajax 方式，获取页面点击不同的单位时，得到未付款的样品登记
     * @return
     */
    public String getRegisteInfo(){
    	    		
    	PageModel<SampleRegiste> sr = sampleRegisteService.getNoPayOff(companyid, 1, Constants.MAX_COUNT);
    	if (sr==null) return "ajax";
  	
    	List list = new ArrayList();
    	Map<String, String> usermap ;
    	for(SampleRegiste newsr:sr.getList()){
    		usermap = new HashMap<String, String>();
	    	usermap.put("key",String.valueOf(newsr.getId()));  
	    	usermap.put("value", newsr.getSampleno());  
	    	list.add(usermap);
    	}
    	JSONArray  jo = JSONArray.fromObject(list);
    	//JSONObject jo = JSONObject.fromObject(usermap);  
    	info = jo.toString();  
    	return "ajax";  
    }
    
    /**
     * 通过 ajax 方式，获取页面点击不同的样品登记时，得到未付款金额
     * @return
     */
    public String getBalance(){
    	    		
    	SampleRegiste sr = sampleRegisteService.getSampleRegiste(myregid);
    	if (sr==null) return "ajax";
  	
    	info = "{value:"+sr.getBalancemoney()+"}";  
    	return "ajax";  
    }
    
	public String add() {
		initData();
		return ADD;
	}
	public String remove() {
		if(incomeService.removeIncome(income.getId())) {
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据删除成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据删除失败");
		return ERROR;
	}
	public String modify() {
		initData();
		income = incomeService.getIncome(income.getId());
		PageModel<SampleRegiste> sr = sampleRegisteService.getNoPayOff(income.getEntrustcompanyid(), 1, Constants.MAX_COUNT);
		regList = new HashMap<Integer,String>();
		for(SampleRegiste s:sr.getList()){
			regList.put(s.getId(),s.getSampleno());
		}
		if (!StringUtils.isEmpty(income.getSampleregisteids())){
			String tmp = income.getSampleregisteids();
			tmp = tmp.substring(1,tmp.length()-1);
			String[] array = tmp.split("\\|");
			regvalueList = new HashMap<Integer,String>();
			for(int i=0;i<array.length;i++){
				for(SampleRegiste s:sr.getList()){
					if (s.getId()==Integer.parseInt(array[i])){
						regvalueList.put(s.getId(), String.valueOf(s.getBalancemoney()));
					}
				}
			}
		}
		EntrustCompany ec = entrustCompanyService.getEntrustCompany(income.getEntrustcompanyid());

		income.setEntrustcompanyStr(ec.getEntrustcompany());
		return "modify";
	}
	public String save() {
		income.setCreateuser(BusinessUtils.getCurrentUser().getUid());
		income.setCreatedate(new Date());
		income.setStatus("0");
		//结算类型
		if(income.getCaltype()!=null&&income.getCaltype().equals("on")){
			income.setPaytype("2");
		}else if (income.getPrecal()!=null&&income.getPrecal().equals("on")){
			income.setPaytype("1");
		}else{
			income.setPaytype("3");
		}
		//设置登记单信息
		if (registeid!=null&&registeid.length>0){
			String tmp = "";
			for(int i=0;i<registeid.length;i++){
				tmp +="|"+registeid[i];
				if (i+1==registeid.length){
					tmp +="|";
				}
			}
			income.setSampleregisteids(tmp);
		}
		//如果新增付款人
		if (StringUtils.isEmpty(income.getUserid())){
			RelationUser ru = new RelationUser();
			ru.setEntrustcompanyid(income.getEntrustcompanyid());
			ru.setSenduser(income.getUsername());
			ru.setAddress(income.getAddress());
			ru.setTel(income.getTel());
			ru.setBalance(0f);
			ru.setType(2);
			ru.setCreateuser(BusinessUtils.getCurrentUser().getUid());
			ru.setCreatedate(new Date());
			
			ru = relationUserService.addRelationUser(ru);
			income.setUserid(ru.getId()); 
		}
		if((income=incomeService.addIncome(income)) != null) {
			
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据添加成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据添加失败");
		return SUCCESS;
	}
	public String update() {
		income.setCreateuser(BusinessUtils.getCurrentUser().getUid());
		income.setCreatedate(new Date());
		income.setStatus("0");
		//结算类型
		if(income.getCaltype()!=null&&income.getCaltype().equals("on")){
			income.setPaytype("2");
		}else if (income.getPrecal()!=null&&income.getPrecal().equals("on")){
			income.setPaytype("1");
		}else{
			income.setPaytype("3");
		}
		//设置登记单信息
		if (registeid!=null&&registeid.length>0){
			String tmp = "";
			for(int i=0;i<registeid.length;i++){
				tmp +="|"+registeid[i];
				if (i+1==registeid.length){
					tmp +="|";
				}
			}
			income.setSampleregisteids(tmp);
		}
		//更新联系人信息
		RelationUser ru = relationUserService.getRelationUser(income.getUserid());
		if (ru!=null){
			if (!ru.getAddress().equals(income.getAddress())||!ru.getTel().equals(income.getTel())){
				ru.setAddress(income.getAddress());
				ru.setTel(income.getTel());
				relationUserService.updateRelationUser(ru);
			}
		}
		if((income=incomeService.updateIncome(income)) != null) {
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据更新成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据更新失败");
		return SUCCESS;
	}
	public String detail() {
		income = incomeService.getIncome(income.getId());
		String name = entrustCompanyService.getEntrustCompany(income.getEntrustcompanyid()).getEntrustcompany();
		income.setEntrustcompanyStr(name);
		float money = 0f;
		if (!StringUtils.isEmpty(income.getSampleregisteids())){
			String tmp = income.getSampleregisteids();
			tmp = tmp.substring(1,tmp.length()-1);
			String[] array = tmp.split("\\|");
			
			regvalueList = new HashMap<Integer,String>();
			for(int i=0;i<array.length;i++){
				SampleRegiste sr = sampleRegisteService.getSampleRegiste(Integer.parseInt(array[i]));
				regvalueList.put(sr.getId(),sr.getSampleno()+" 检测费用："+sr.getCheckmoney());
				money += sr.getCheckmoney();
			}
		}
        income.setTotalcheckmoney(money);
		return DETAIL;
	}
	/**
	 * 提交审核
	 * @return
	 */
	public String submit() {
		income = incomeService.getIncome(income.getId());
        income.setStatus("1");
		income = incomeService.updateIncome(income);
		////////////////////////////////////////////////////
		
		boolean flag = false;
		float money = income.getPaymoney();
		//如果是预付款结算
		if (!StringUtils.isEmpty(income.getSampleregisteids())&&income.getPaytype().equals("1")){
			String tmp = income.getSampleregisteids();
			tmp = tmp.substring(1,tmp.length()-1);
			String[] array = tmp.split("\\|");
			for(int i=0;i<array.length;i++){
				SampleRegiste sr = sampleRegisteService.getSampleRegiste(Integer.parseInt(array[i]));
				sr.setPrepaymoney(sr.getCheckmoney());
	    		sr.setBalancemoney(0f);
	    		sampleRegisteService.updateSampleRegiste(sr);
		    }
			//7.31 修改预付款金额
			EntrustCompany entrustCompany = new EntrustCompany();
			entrustCompany = entrustCompanyService.getEntrustCompany(income.getEntrustcompanyid());
			entrustCompany.setPrepaymoney(entrustCompany.getPrepaymoney()-income.getPaymoney());
			entrustCompanyService.updateEntrustCompany(entrustCompany);
			//更新对应的预付款人
			RelationUser ru = relationUserService.getRelationUser(income.getUserid());
		    if (ru!=null){
		    	ru.setBalance(ru.getBalance()-income.getPaymoney());
		    	ru.setAddress(income.getAddress());
		    	ru.setTel(income.getTel());
		    	relationUserService.updateRelationUser(ru);
		    }
			ContextUtils.setOpMessage(MessageType.SUCCESS, "结算成功");
			return SUCCESS;
		}
		//如果是现金结算
		if (!StringUtils.isEmpty(income.getSampleregisteids())&&!income.getPaytype().equals("1")){
			String tmp = income.getSampleregisteids();
			tmp = tmp.substring(1,tmp.length()-1);
			String[] array = tmp.split("\\|");
			for(int i=0;i<array.length;i++){
				SampleRegiste sr = sampleRegisteService.getSampleRegiste(Integer.parseInt(array[i]));
				if (money-sr.getBalancemoney()>=0){
					  money -= sr.getBalancemoney();
		    		  sr.setPrepaymoney(sr.getCheckmoney());
		    		  sr.setBalancemoney(0f);
		    		  sampleRegisteService.updateSampleRegiste(sr);
		    		  continue;
		    	  }
		    	  sr.setPrepaymoney(sr.getPrepaymoney()+money);
	    		  sr.setBalancemoney(sr.getBalancemoney()-money);
	    		  sampleRegisteService.updateSampleRegiste(sr);
	    		  flag = true;
	    		  break;
		    }
			//如果付款有剩余，则增加委托单位的预付款金额
			if (!flag&&money>0){
				EntrustCompany ec = entrustCompanyService.getEntrustCompany(income.getEntrustcompanyid());
				ec.setPrepaymoney(ec.getPrepaymoney()+money);
				entrustCompanyService.updateEntrustCompany(ec);
				
				//更新对应的预付款人
				RelationUser ru = relationUserService.getRelationUser(income.getUserid());
			    if (ru!=null){
			    	ru.setBalance(ru.getBalance()+money);
			    	ru.setAddress(income.getAddress());
			    	ru.setTel(income.getTel());
			    	relationUserService.updateRelationUser(ru);
			    }
			}
			ContextUtils.setOpMessage(MessageType.SUCCESS, "结算成功");
			return SUCCESS;
		}
		//如果是系统结算
		if (StringUtils.isEmpty(income.getSampleregisteids())){
			
			PageModel<SampleRegiste> psr = sampleRegisteService.getNoPayOff(income.getEntrustcompanyid(), 1, Constants.MAX_COUNT);
			if (psr==null||psr.getList().size()==0) return SUCCESS;
			for(SampleRegiste sr:psr.getList()){
				  if (money-sr.getBalancemoney()>=0){
					  money -= sr.getBalancemoney();
		    		  sr.setPrepaymoney(sr.getCheckmoney());
		    		  sr.setBalancemoney(0f);
		    		  sampleRegisteService.updateSampleRegiste(sr);
		    		  continue;
		    	  }
		    	  sr.setPrepaymoney(sr.getPrepaymoney()+money);
	    		  sr.setBalancemoney(sr.getBalancemoney()-money);
	    		  sampleRegisteService.updateSampleRegiste(sr);
	    		  flag = true;
	    		  break;
			}
		}
		//如果付款有剩余，则增加委托单位的预付款金额
		if (!flag&&money>0){
			EntrustCompany ec = entrustCompanyService.getEntrustCompany(income.getEntrustcompanyid());
			ec.setPrepaymoney(ec.getPrepaymoney()+money);
			
			entrustCompanyService.updateEntrustCompany(ec);
		}
		///////////////////////////////////////////////////
		ContextUtils.setOpMessage(MessageType.SUCCESS, "结算成功");
		return SUCCESS;
	}
	
	/**
	 * 取消已经结算过的结算
	 * @return
	 */
	public String cancel() {
		income = incomeService.getIncome(income.getId());
        
		////////////////////////////////////////////////////
		float money = income.getPaymoney();

		if (!StringUtils.isEmpty(income.getSampleregisteids())){
			String tmp = income.getSampleregisteids();
			tmp = tmp.substring(1,tmp.length()-1);
			String[] array = tmp.split("\\|");
			for(int i=0;i<array.length;i++){
				 SampleRegiste sr = sampleRegisteService.getSampleRegiste(Integer.parseInt(array[i]));
			     
				 if (money-sr.getCheckmoney()>=0){
		    		  sr.setPrepaymoney(0f);
		    		  sr.setBalancemoney(sr.getCheckmoney());
		    		  sampleRegisteService.updateSampleRegiste(sr);
		    		  
		    		  money -= sr.getCheckmoney();
		    		  continue;
		    	  }
		    	  sr.setPrepaymoney(sr.getPrepaymoney()-money);
	    		  sr.setBalancemoney(sr.getBalancemoney()+money);
	    		  sampleRegisteService.updateSampleRegiste(sr);

	    		  break;
		    }
			
		}
		//7.31 修改预付款金额
		EntrustCompany entrustCompany = new EntrustCompany();
		entrustCompany = entrustCompanyService.getEntrustCompany(income.getEntrustcompanyid());
		entrustCompany.setPrepaymoney(entrustCompany.getPrepaymoney()+income.getPaymoney());
		entrustCompanyService.updateEntrustCompany(entrustCompany);

		//11.26 修改对应联系人的预付款金额
		RelationUser ru = relationUserService.getRelationUser(income.getUserid());
		ru.setBalance(ru.getBalance()+income.getPaymoney());
		relationUserService.updateRelationUser(ru);
		
		income.setStatus("0");
		income = incomeService.updateIncome(income);
		ContextUtils.setOpMessage(MessageType.SUCCESS, "提交成功");
		return SUCCESS;
	}
	/**
	 * 从登记单直接过来的结算
	 * @return
	 */
	public String cash() {
		income.setCreateuser(BusinessUtils.getCurrentUser().getUid());
		income.setCreatedate(new Date());
		income.setStatus("0");
		//结算类型
		income.setPaytype("3");
        
		//设置登记单信息
		if (sampleRegiste!=null){
			SampleRegiste sr = sampleRegisteService.getSampleRegiste(sampleRegiste.getId());
			//11.16 判断检验费用是否变更
			float chk = sampleRegiste.getCheckmoney();
			float bal = sampleRegiste.getCheckmoney()-sampleRegiste.getPrepaymoney();
		    sr.setCheckmoney(chk);
		    sr.setBalancemoney(bal);

			//查询已经付款的金额是不是大于等于检测费用
//			if (sr.getPrepaymoney()-sampleRegiste.getCheckmoney()>=0){
//				这种情况直接改登记单吧
//			}
			income.setEntrustcompanyid(Integer.parseInt(sampleRegiste.getEntrustcompany()));
			income.setSampleregisteids("|"+sampleRegiste.getId()+"|");
			income.setUsername(sampleRegiste.getSenduser());
			//查询结算类型
			if (income.getPrecal()!=null&&income.getPrecal().equals("true")){
			    income.setPaytype("1");
			    sr.setPrepaymoney(chk);
			    sr.setBalancemoney(0F);
			     //更新对应的公司人员的预付款金额
			    EntrustCompany ec = entrustCompanyService.getEntrustCompany(Integer.parseInt(sampleRegiste.getEntrustcompany()));
			    ec.setPrepaymoney(ec.getPrepaymoney()-bal);
			    entrustCompanyService.updateEntrustCompany(ec);
			    
			    //判断是否有此预付款人
				if (Integer.parseInt(username)>0){
                    RelationUser ru = relationUserService.getRelationUser(Integer.parseInt(username));
				    if (ru!=null){
				    	ru.setBalance(ru.getBalance()-bal);
				    	ru.setAddress(income.getAddress());
				    	ru.setTel(income.getTel());
				    	relationUserService.updateRelationUser(ru);
    	
				    	income.setUserid(Integer.parseInt(username));
				    }
				    
				}else{
					RelationUser ru = new RelationUser();
					ru.setEntrustcompanyid(Integer.parseInt(sampleRegiste.getEntrustcompany()));
					ru.setSenduser(sampleRegiste.getSenduser());
					ru.setBalance(0f);
					ru.setType(2);
					ru.setCreateuser(BusinessUtils.getCurrentUser().getUid());
					ru.setCreatedate(new Date());
					
					ru = relationUserService.addRelationUser(ru);
					income.setUserid(ru.getId());
				}
			    
			}else{
				income.setPaytype("2");
				//查询付款信息是否大于未付款金额
				if (income.getPaymoney()-bal>=0){
					sr.setPrepaymoney(chk);
					sr.setBalancemoney(0F);
					//更新对应的公司人员的预付款金额
					EntrustCompany ec = entrustCompanyService.getEntrustCompany(Integer.parseInt(sampleRegiste.getEntrustcompany()));
				    ec.setPrepaymoney(ec.getPrepaymoney()+income.getPaymoney()-bal);
				    entrustCompanyService.updateEntrustCompany(ec);
					//判断是否有此预付款人
					if (Integer.parseInt(username)>0){
                        RelationUser ru = relationUserService.getRelationUser(Integer.parseInt(username));
					    if (ru!=null){
					    	ru.setBalance(ru.getBalance()+income.getPaymoney()-bal);
					    	ru.setAddress(income.getAddress());
					    	ru.setTel(income.getTel());
					    	relationUserService.updateRelationUser(ru);
					    	income.setUserid(ru.getId());
					    	income.setAddress(ru.getAddress());
						    income.setTel(ru.getTel());
					    }
					}else{
						RelationUser ru = new RelationUser();
						ru.setEntrustcompanyid(Integer.parseInt(sampleRegiste.getEntrustcompany()));
						ru.setSenduser(sampleRegiste.getSenduser());
				        ru.setBalance(0f);
		     			ru.setType(2);
						ru.setAddress(income.getAddress());
						ru.setTel(income.getTel());
						ru.setCreateuser(BusinessUtils.getCurrentUser().getUid());
						ru.setCreatedate(new Date());
						
						ru = relationUserService.addRelationUser(ru);
						income.setUserid(ru.getId());
					}
				}else{
				   sr.setPrepaymoney(sr.getPrepaymoney()+income.getPaymoney());
				   sr.setBalancemoney(sr.getBalancemoney()-income.getPaymoney());
				   
				   RelationUser ru = relationUserService.getRelationUser(Integer.parseInt(username));
				   if (ru!=null){
				    	ru.setAddress(income.getAddress());
				    	ru.setTel(income.getTel());
				    	ru=relationUserService.updateRelationUser(ru);
				    	income.setUserid(ru.getId());
				   }else{
					    RelationUser u = new RelationUser();
						u.setEntrustcompanyid(Integer.parseInt(sampleRegiste.getEntrustcompany()));
						u.setSenduser(sampleRegiste.getSenduser());
						if (income.getPaymoney()-bal>0)
						     u.setBalance(income.getPaymoney()-bal);
						else
							 u.setBalance(0f);
						u.setType(2);
						u.setAddress(income.getAddress());
						u.setTel(income.getTel());
						u.setCreateuser(BusinessUtils.getCurrentUser().getUid());
						u.setCreatedate(new Date());
						
						u = relationUserService.addRelationUser(u);
						income.setUserid(u.getId());
				   }
				   
				}
			}
			//更新登记单的未付款余额信息
			sampleRegisteService.updateSampleRegiste(sr);
		}
		//直接结算
		income.setStatus("1");
		if((income=incomeService.addIncome(income)) != null) {
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据添加成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据添加失败");
		return SUCCESS;
	}
	//7.31 得到预付款的余额
	public String getPayedMoney(){
        EntrustCompany entrustCompany = entrustCompanyService.getEntrustCompany(companyid);
		float payedmoney = entrustCompany.getPrepaymoney();
        if (payedmoney==0){
			info = "{value:0}";  
		}else{
			info = "{value:"+payedmoney+"}";  
		}
        
		return "ajax";
	}
	//11.26 得到预付款联系人
	public String getUsersForPay(){
        RelationUser ru = new RelationUser();
        ru.setEntrustcompanyid(companyid);
       // ru.setType(2);
        PageModel<RelationUser> pru = relationUserService.getRelationUser(ru,1, 9999);
        //得到选择的预付款人
        if (pru!=null&&pru.getList().size()>0){
	        JSONArray jsonArray = new JSONArray();
	    	Map<String, String> usermap = new HashMap<String, String>(); 
	    	for(RelationUser u:pru.getList()){
	    		usermap.put("userid", u.getId().toString());  
		    	usermap.put("user", u.getSenduser());
		    	usermap.put("address", u.getAddress());  
		    	usermap.put("tel", u.getTel());  
		    	JSONObject jo = JSONObject.fromObject(usermap);
		    	jsonArray.add(jo);   
	    	}
	    	info = jsonArray.toString();
        }
		return "ajax";
	}
	//11.26 得到预付款联系人
	public String getUserInfo(){
        RelationUser ru = relationUserService.getRelationUser(Integer.parseInt(username));
        //得到选择的预付款人
        if (ru!=null){
	    	Map<String, String> usermap = new HashMap<String, String>(); 
    		usermap.put("userid", ru.getId().toString());  
	    	usermap.put("user", ru.getSenduser());
	    	usermap.put("address", ru.getAddress());  
	    	usermap.put("tel", ru.getTel());  
	    	JSONObject jo = JSONObject.fromObject(usermap);

	    	info = jo.toString();
        }
		return "ajax";
	}
	//11.25 得到联系人预付款的余额
	public String getUserPayedMoney(){
        RelationUser user =  relationUserService.getRelationUser(Integer.parseInt(username));
        if (user!=null){
			info = "{value:"+user.getBalance()+"}";  
        }
		return "ajax";
	}
	// 导出excel
	public String excelExport() throws IOException{
		ExportUtils edu = new ExportUtils();
        String[] fieldname = {"付款单位","联系人","付款金额","付款日期","收款人","样品登记单号","发票是否开具","发票类型","发票快递单号"};
		String[] columnname = {"entrustcompanyStr","username","paymoney","paydate","receiveuser","sampleregisteids","billischeck","taxtypename","billno"};
		PageModel<Income> pageModel=incomeService.getIncome(income, 1, Constants.MAX_COUNT);
		for(Income ic:pageModel.getList()){
			if (ic.getBillischeck().equals("1")){
				ic.setBillischeck("已开");
				if (ic.getTaxtype()==1)
					ic.setTaxtypename("增值税专用发票");
				if (ic.getTaxtype()==1)
					ic.setTaxtypename("增值税普通发票");
				if (ic.getTaxtype()==1)
					ic.setTaxtypename("未确定");
			}else{
				ic.setBillischeck("未开");
				ic.setTaxtypename("未确定");
			}
			EntrustCompany ec = entrustCompanyService.getEntrustCompany(ic.getEntrustcompanyid());
			ic.setEntrustcompanyStr(ec.getEntrustcompany());
			
			if (ic.getSampleregisteids()==null) continue;
			String items = "";
			items = ic.getSampleregisteids().substring(1,ic.getSampleregisteids().length()-1);
			String[] srs = items.split("\\|");
			String tmp = "";
			for(int i=0;i<srs.length;i++){
				SampleRegiste sr = sampleRegisteService.getSampleRegiste(Integer.parseInt(srs[i]));
				tmp += sr.getSampleno()+" | "; 
			}
			ic.setSampleregisteids(tmp);
		}
        edu.exportExcel2("委托单位结算单",fieldname,columnname,pageModel,request,response);
		return null;
	}
	public int[] getRegisteid() {
		return registeid;
	}
	public void setRegisteid(int[] registeid) {
		this.registeid = registeid;
	}
	public Income getIncome() {
		return income;
	}
	public void setIncome(Income income) {
		this.income = income;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getCompanyid() {
		return companyid;
	}
	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}
	public int getMyregid() {
		return myregid;
	}
	public void setMyregid(int myregid) {
		this.myregid = myregid;
	}
	public Map<Integer,String> getRegvalueList() {
		return regvalueList;
	}
	public void setRegvalueList(Map<Integer,String> regvalueList) {
		this.regvalueList = regvalueList;
	}
	public SampleRegiste getSampleRegiste() {
		return sampleRegiste;
	}
	public void setSampleRegiste(SampleRegiste sampleRegiste) {
		this.sampleRegiste = sampleRegiste;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
