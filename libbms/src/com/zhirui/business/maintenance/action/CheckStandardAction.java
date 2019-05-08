package com.zhirui.business.maintenance.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhirui.business.common.bean.User;
import com.zhirui.business.lib.bean.CheckStandard;
import com.zhirui.business.lib.bean.SubCheckReport;
import com.zhirui.business.lib.service.CheckStandardService;
import com.zhirui.business.utils.BusinessUtils;

import cn.kepu.base.MessageType;
import cn.kepu.base.action.PageAction;
import cn.kepu.utils.ContextUtils;

@SuppressWarnings("serial")
public class CheckStandardAction extends PageAction<CheckStandard> {
	@Autowired
	private CheckStandardService checkStandardService;
	private CheckStandard checkStandard;
	public Map<Integer,String> entrustcompanyList;
	public Map<Integer,String> instrumentList;
	public Map<Integer,String> itemList;
	public Map<Integer,String> registeList;
	public List<SubCheckReport> checkResultList;
	private String info;
	private String standardcode;
	private Integer cid;
	private static final Log log = LogFactory.getLog(CheckStandardAction.class);
	
	/**
	 * 列表功能
	 * @return
	 */
	public String list() {
		pageModel = checkStandardService.getCheckStandard(checkStandard, pageNo, pageSize);
		return LIST;
	}

	public String add() {
		return ADD;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String remove() {
		checkStandard = checkStandardService.getCheckStandard(checkStandard.getId());
		checkStandard.setValidflag(0);
		if(checkStandardService.updateCheckStandard(checkStandard)!=null){
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据删除成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据删除失败");
		return SUCCESS;
	}
	 /**
     * 恢复被删除的数据
     * @return
     */
	public String recover() {
		checkStandard = checkStandardService.getCheckStandard(checkStandard.getId());
		checkStandard.setValidflag(1);
		if(checkStandardService.updateCheckStandard(checkStandard)!=null){
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据恢复成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据恢复失败");
		return SUCCESS;
	}
	/*跳转修改页面*/
	public String modify() {
		checkStandard = checkStandardService.getCheckStandard(checkStandard.getId());
		return "modify";
	}
	
	public String save() {
		checkStandard.setCreatedate(new Date());
		checkStandard.setValidflag(1);
		if((checkStandard=checkStandardService.addCheckStandard(checkStandard)) != null) {
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据添加成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据添加失败");
		return SUCCESS;
	}
	
	/*更新*/
	public String update() {
		checkStandard.setCreatedate(new Date());
		checkStandard.setValidflag(1);
		if((checkStandard=checkStandardService.updateCheckStandard(checkStandard)) != null) {
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据更新成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据更新失败");
		return SUCCESS;
	}
	
	
	public String detail() {
		checkStandard = checkStandardService.getCheckStandard(checkStandard.getId());
		return DETAIL;
	}
	/**
     * 通过 ajax 方式，获取检验项目是否已经存在
     * @return
     */
	public String isExists(){
    	if (checkStandardService.isExists(cid,standardcode)!=-1){
			info = "{res:true}";
			return "exists";
		}
     	info = "{res:false}";
    	return "exists";  
    }
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getStandardcode() {
		return standardcode;
	}

	public void setStandardcode(String standardcode) {
		this.standardcode = standardcode;
	}
	
	
	
	
	
	
	
	
	
	
	
	public CheckStandard getCheckStandard() {
		return checkStandard;
	}

	public void setCheckStandard(CheckStandard CheckStandard) {
		this.checkStandard = CheckStandard;
	}

}
