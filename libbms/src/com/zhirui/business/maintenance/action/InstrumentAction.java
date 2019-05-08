package com.zhirui.business.maintenance.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.zhirui.business.common.bean.User;
import com.zhirui.business.lib.bean.Instrument;
import com.zhirui.business.lib.service.InstrumentService;
import com.zhirui.business.utils.BusinessUtils;
import cn.kepu.base.MessageType;
import cn.kepu.base.action.PageAction;
import cn.kepu.utils.ContextUtils;

@SuppressWarnings("serial")
public class InstrumentAction extends PageAction<Instrument> {
	@Autowired
	private InstrumentService instrumentService;

	private Instrument instrument;
	private String info;
	private String codename;
	private Integer iid;


	private static final Log log = LogFactory.getLog(InstrumentAction.class);
	
	/**
	 * 列表功能
	 * @return
	 */
	public String list() {
		pageModel = instrumentService.getInstrument(instrument, pageNo, pageSize);
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
		instrument = instrumentService.getInstrument(instrument.getId());
		instrument.setValidflag(0);
		if(instrumentService.updateInstrument(instrument)!=null){
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
		instrument = instrumentService.getInstrument(instrument.getId());
		instrument.setValidflag(1);
		if(instrumentService.updateInstrument(instrument)!=null){
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据恢复成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据恢复失败");
		return SUCCESS;
	}
	public String modify() {
		instrument = instrumentService.getInstrument(instrument.getId());
		return "modify";
	}
	public String save() {
		User user = BusinessUtils.getCurrentUser();
		instrument.setValidflag(1);
		if((instrument=instrumentService.addInstrument(instrument)) != null) {
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据添加成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据添加失败");
		return SUCCESS;
	}
	public String update() {
		User user = BusinessUtils.getCurrentUser();
		instrument.setValidflag(1);
		if((instrument=instrumentService.updateInstrument(instrument)) != null) {
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据更新成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据更新失败");
		return SUCCESS;
	}
	public String detail() {
		instrument =instrumentService.getInstrument(instrument.getId());
		return DETAIL;
	}

	/**
     * 通过 ajax 方式，获取检验项目是否已经存在
     * @return
     */
	public String isExists(){

    	if (instrumentService.isExists(iid,codename)){
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
	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

	public Integer getIid() {
		return iid;
	}

	public void setIid(Integer iid) {
		this.iid = iid;
	}

	public String getCodename() {
		return codename;
	}

	public void setCodename(String codename) {
		this.codename = codename;
	}
}
