package com.zhirui.business.maintenance.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhirui.business.common.bean.User;
import com.zhirui.business.lib.bean.CheckItem;
import com.zhirui.business.lib.bean.LabProcess;
import com.zhirui.business.lib.bean.SubCheckReport;
import com.zhirui.business.lib.service.CheckItemService;
import com.zhirui.business.lib.service.LabProcessService;
import com.zhirui.business.utils.BusinessUtils;

import cn.kepu.base.MessageType;
import cn.kepu.base.action.PageAction;
import cn.kepu.utils.ContextUtils;

@SuppressWarnings("serial")
public class LabProcessAction extends PageAction<LabProcess> {
	@Autowired
	private LabProcessService labProcessService;
	
	private LabProcess labProcess;
	public Map<Integer,String> entrustcompanyList;
	public Map<Integer,String> instrumentList;
	public Map<Integer,String> itemList;
	public Map<Integer,String> registeList;
	public List<SubCheckReport> checkResultList;
	private String info;
	private String labprocesstitle;
	private Integer cid;

	
	private static final Log log = LogFactory.getLog(LabProcessAction.class);
	
	
	
	/**
	 * 列表功能
	 * @return
	 */
	public String list() {
		pageModel = labProcessService.getLabProcess(labProcess, pageNo, pageSize);
		return LIST;
	}
	/**
	 * 存储功能
	 * @return
	 */
	public String save() {
		User user = BusinessUtils.getCurrentUser();
		labProcess.setCreatedate(new Date());
		labProcess.setValidflag(1);
		if((labProcess=labProcessService.addLabProcess(labProcess)) != null) {
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据添加成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据添加失败");
		return SUCCESS;
	}
	
	/**
	 * 跳转增加页面
	 * @return
	 */
	public String add() {
		return ADD;
	}
	
	/**
	 * 跳转数据显示页面
	 * @return
	 */
	public String detail() {
		labProcess = labProcessService.getLabProcess(labProcess.getId());
		return DETAIL;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String remove() {
		labProcess = labProcessService.getLabProcess(labProcess.getId());
		labProcess.setValidflag(0);
		if(labProcessService.updateLabProcess(labProcess)!=null){
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
		labProcess = labProcessService.getLabProcess(labProcess.getId());
		labProcess.setValidflag(1);
		if(labProcessService.updateLabProcess(labProcess)!=null){
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据恢复成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据恢复失败");
		return SUCCESS;
	}
	/**
	 * 修改
	 * @return
	 */
	public String modify() {
		labProcess = labProcessService.getLabProcess(labProcess.getId());
		return "modify";
	}
	
	/**
	 * 更新数据
	 * @return
	 */
	public String update() {
	
		labProcess.setCreatedate(new Date());
		labProcess.setValidflag(1);
		if((labProcess=labProcessService.updateLabProcess(labProcess)) != null) {
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据更新成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据更新失败");
		return SUCCESS;
	}
	
	
	/**
     * 通过 ajax 方式，获取检验项目是否已经存在
     * @return
	 * @throws UnsupportedEncodingException 
     */
	public String isExists() throws UnsupportedEncodingException{
		String labprocesstitle1 = URLDecoder.decode(request.getParameter("labprocesstitle"),"UTF-8");
		String labprocesstitle2=URLDecoder.decode(labprocesstitle1,"UTF-8");
		
    	if (labProcessService.isExists(cid,labprocesstitle2)!=-1){   		
			info = "{res:true}";    //有
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
	
	
	public String getLabprocesstitle() {
		return labprocesstitle;
	}

	public void setLabprocesstitle(String labprocesstitle) {
		this.labprocesstitle = labprocesstitle;
	}
	
	
	
	
	public LabProcess getLabProcess() {
		return labProcess;
	}

	public void setLabProcess(LabProcess labProcess) {
		this.labProcess = labProcess;
	}

	
	
}
