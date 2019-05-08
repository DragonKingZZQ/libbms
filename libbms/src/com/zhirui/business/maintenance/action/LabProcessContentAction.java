package com.zhirui.business.maintenance.action;

import java.util.List;
import java.util.Map;









import org.springframework.beans.factory.annotation.Autowired;

import cn.kepu.base.action.PageAction;

import com.zhirui.business.lib.bean.LabProcess;
import com.zhirui.business.lib.bean.LabProcessContent;
import com.zhirui.business.lib.service.LabProcessContentService;
import com.zhirui.business.lib.service.LabProcessService;

@SuppressWarnings("serial")
public class LabProcessContentAction extends PageAction<LabProcessContent>{
	@Autowired
	private LabProcessContentService labProcessContentService;
	public List<String> labProcessContentlist;
	
	private LabProcess labProcess;
	@Autowired
	private LabProcessService labProcessService;
	public String addfindContent(){
		labProcessContentlist = labProcessContentService.getAll();
		
		return "addfindOk";
	}
	
	public String modifyfindContent(){
		labProcess = labProcessService.getLabProcess(labProcess.getId());
		labProcessContentlist = labProcessContentService.getAll();
		
		return "modifyfindOk";
	}
	
	public LabProcess getLabProcess() {
		return labProcess;
	}

	public void setLabProcess(LabProcess labProcess) {
		this.labProcess = labProcess;
	}
}
