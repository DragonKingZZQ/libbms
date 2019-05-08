package com.zhirui.business.lib.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhirui.business.lib.bean.EntrustCompany;
import com.zhirui.business.lib.bean.Prepayment;
import com.zhirui.business.lib.bean.RelationUser;
import com.zhirui.business.lib.service.EntrustCompanyService;
import com.zhirui.business.lib.service.PrepaymentService;
import com.zhirui.business.lib.service.RelationUserService;
import com.zhirui.business.utils.BusinessUtils;

import cn.kepu.base.MessageType;
import cn.kepu.base.action.PageAction;
import cn.kepu.utils.ContextUtils;
import cn.kepu.utils.PageModel;

@SuppressWarnings("serial")
public class PrepaymentAction extends PageAction<Prepayment> {

	@Autowired
	private PrepaymentService prepaymentService;
	@Autowired
	private EntrustCompanyService entrustCompanyService;
	@Autowired
	private RelationUserService relationUserService;
	private Prepayment prepayment;
	public Map<Integer,String> entrustcompanyList;
	//取不同的登记单信息
	private String registeid;
	
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
		pageModel = prepaymentService.getPrepayment(prepayment, pageNo, pageSize);
		//处理付款单位名称
		for(Prepayment p:pageModel.getList()){
		   EntrustCompany ec=entrustCompanyService.getEntrustCompany(p.getEntrustcompanyid());
		   p.setEntrustcompanyStr(ec.getEntrustcompany());
		   p.setBalancemoney(ec.getPrepaymoney());
		}
		return LIST;
	}
	public String add() {
		initData();
		return ADD;
	}
	public String remove() {
		Prepayment p = prepaymentService.getPrepayment(prepayment.getId());
		if(prepaymentService.removePrepayment(prepayment.getId())) {
			
			//7.31 修改预付款金额
			EntrustCompany entrustCompany = new EntrustCompany();
			entrustCompany = entrustCompanyService.getEntrustCompany(p.getEntrustcompanyid());
			entrustCompany.setPrepaymoney(entrustCompany.getPrepaymoney()-p.getPrepaymoney());
			entrustCompanyService.updateEntrustCompany(entrustCompany);
			
			//11.26 修改对应人的预付款金额
			RelationUser u = relationUserService.getRelationUser(p.getUserid());
			if (u!=null){
			    u.setBalance(u.getBalance()-p.getPrepaymoney());
				relationUserService.updateRelationUser(u);
			}
			
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据删除成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据删除失败");
		return ERROR;
	}
	public String modify() {
		initData();
		prepayment = prepaymentService.getPrepayment(prepayment.getId());
		EntrustCompany ec = entrustCompanyService.getEntrustCompany(prepayment.getEntrustcompanyid());
		prepayment.setEntrustcompanyStr(ec.getEntrustcompany());
		return "modify";
	}
	public String save() {
		int cmpid = -1;
		if (prepayment.getEntrustcompanyid()==null||prepayment.getEntrustcompanyid()==-1){
			//新增单位
			EntrustCompany entrustCompany = new EntrustCompany();
			entrustCompany.setEntrustcompany(prepayment.getEntrustcompanyStr());
			entrustCompany.setUsername(prepayment.getUsername());
			entrustCompany.setPrepaymoney(prepayment.getPrepaymoney());
			entrustCompany.setCreateuser(BusinessUtils.getCurrentUser().getUid());
			entrustCompany.setCreatedate(new Date());
			entrustCompany = entrustCompanyService.addEntrustCompany(entrustCompany);
			
			cmpid= entrustCompany.getId();
			
			//11.26 新增单位预付款人
			RelationUser ru = new RelationUser();
			ru.setEntrustcompanyid(cmpid);
			ru.setSenduser(prepayment.getUsername());
			ru.setAddress(prepayment.getAddress());
			ru.setTel(prepayment.getTel());
			ru.setCreateuser(BusinessUtils.getCurrentUser().getUid());
			ru.setCreatedate(new Date());
			ru.setType(2);
			ru.setBalance(prepayment.getPrepaymoney());
			ru = relationUserService.addRelationUser(ru);
			prepayment.setCreateuser(BusinessUtils.getCurrentUser().getUid());
			
			prepayment.setEntrustcompanyid(cmpid);
			prepayment.setUserid(ru.getId());
			prepayment.setCreateuser(BusinessUtils.getCurrentUser().getUid());
			prepayment.setCreatedate(new Date());
			prepaymentService.addPrepayment(prepayment);
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据添加成功");
			return SUCCESS;
		}
		if (cmpid!=-1)
		     prepayment.setEntrustcompanyid(cmpid);
		else
			cmpid = prepayment.getEntrustcompanyid();
		prepayment.setCreateuser(BusinessUtils.getCurrentUser().getUid());
		prepayment.setCreatedate(new Date());
		if((prepayment=prepaymentService.addPrepayment(prepayment)) != null) {
			//7.31 修改预付款金额
			EntrustCompany entrustCompany = new EntrustCompany();
			entrustCompany = entrustCompanyService.getEntrustCompany(cmpid);
			entrustCompany.setPrepaymoney(entrustCompany.getPrepaymoney()+prepayment.getPrepaymoney());
			entrustCompanyService.updateEntrustCompany(entrustCompany);
			//11.26 修改对应人的预付款金额
			RelationUser u = new RelationUser();
			u.setEntrustcompanyid(cmpid);
			u.setSenduser(prepayment.getUsername());
			//u.setType(2);
			PageModel<RelationUser> pru = relationUserService.getRelationUser(u, 1, 1);
			if (pru!=null&&pru.getList().size()>0){
			    RelationUser ru = pru.getList().get(0);
			    ru.setBalance(ru.getBalance()+prepayment.getPrepaymoney());
			    ru.setTel(prepayment.getTel());
			    ru.setAddress(prepayment.getAddress());
				relationUserService.updateRelationUser(ru);
			}else{
				RelationUser ru = new RelationUser();
				ru.setEntrustcompanyid(cmpid);
				ru.setSenduser(prepayment.getUsername());
				ru.setAddress(prepayment.getAddress());
				ru.setTel(prepayment.getTel());
				ru.setCreateuser(BusinessUtils.getCurrentUser().getUid());
				ru.setCreatedate(new Date());
				ru.setType(2);
				ru.setBalance(prepayment.getPrepaymoney());
				ru = relationUserService.addRelationUser(ru);
				
				prepayment.setUserid(ru.getId());
				prepaymentService.updatePrepayment(prepayment);
			}
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据添加成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据添加失败");
		return SUCCESS;
	}
	public String update() {
		Prepayment p = prepaymentService.getPrepayment(prepayment.getId());
		float firstmoney = p.getPrepaymoney();
		prepayment.setUserid(p.getUserid());
		if((prepayment=prepaymentService.updatePrepayment(prepayment)) != null) {
			//7.31 修改预付款金额
			EntrustCompany entrustCompany = new EntrustCompany();
			entrustCompany = entrustCompanyService.getEntrustCompany(prepayment.getEntrustcompanyid());
			entrustCompany.setPrepaymoney(entrustCompany.getPrepaymoney()-firstmoney+prepayment.getPrepaymoney());
			entrustCompanyService.updateEntrustCompany(entrustCompany);
			
			//11.26 修改对应人的预付款金额
			RelationUser u =  relationUserService.getRelationUser(p.getUserid());
			if (u!=null){
			    u.setBalance(u.getBalance()-firstmoney+prepayment.getPrepaymoney());
		        u.setTel(prepayment.getTel());
			    u.setAddress(prepayment.getAddress());
				relationUserService.updateRelationUser(u);
			}else{
				RelationUser ru = new RelationUser();
				ru.setEntrustcompanyid(prepayment.getEntrustcompanyid());
				ru.setSenduser(prepayment.getUsername());
				ru.setAddress(prepayment.getAddress());
				ru.setTel(prepayment.getTel());
				ru.setCreateuser(BusinessUtils.getCurrentUser().getUid());
				ru.setCreatedate(new Date());
				ru.setType(2);
				ru.setBalance(prepayment.getPrepaymoney());
                ru = relationUserService.addRelationUser(ru);
				
				prepayment.setUserid(ru.getId());
				prepaymentService.updatePrepayment(prepayment);
			}
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据更新成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据更新失败");
		return SUCCESS;
	}
	public String detail() {
		initData();
		prepayment = prepaymentService.getPrepayment(prepayment.getId());
		
		return DETAIL;
	}
	/**
	 * 提交审核
	 * @return
	 */
	public String submit() {
		prepayment = prepaymentService.getPrepayment(prepayment.getId());
		
		prepayment = prepaymentService.updatePrepayment(prepayment);
		ContextUtils.setOpMessage(MessageType.SUCCESS, "提交成功");
		return SUCCESS;
	}
    
	public String getRegisteid() {
		return registeid;
	}
	public void setRegisteid(String registeid) {
		this.registeid = registeid;
	}
	public Prepayment getPrepayment() {
		return prepayment;
	}
	public void setPrepayment(Prepayment prepayment) {
		this.prepayment = prepayment;
	}
}
