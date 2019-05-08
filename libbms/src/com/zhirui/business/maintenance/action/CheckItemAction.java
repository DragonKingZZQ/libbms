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
import com.zhirui.business.lib.bean.SubCheckReport;
import com.zhirui.business.lib.service.CheckItemService;
import com.zhirui.business.utils.BusinessUtils;

import cn.kepu.base.MessageType;
import cn.kepu.base.action.PageAction;
import cn.kepu.utils.ContextUtils;

@SuppressWarnings("serial")
public class CheckItemAction extends PageAction<CheckItem> {
	@Autowired
	private CheckItemService checkItemService;

	private CheckItem checkItem;
	public Map<Integer,String> entrustcompanyList;
	public Map<Integer,String> instrumentList;
	public Map<Integer,String> itemList;
	public Map<Integer,String> registeList;
	private String info;
	public List<SubCheckReport> checkResultList;
	private String checkname;
	private Integer cid;
	private Map<Integer,String> itemmap;  //曾智琴


	private static final Log log = LogFactory.getLog(CheckItemAction.class);
	
	/**
	 * 列表功能
	 * @return
	 */
	public String list() {
		pageModel = checkItemService.getCheckItem(checkItem, pageNo, pageSize);
		return LIST;
	}

	public String add() {
		/* itemmap = checkItemService.getAll();*/
		return ADD;
	}
	/**
	 * 删除
	 * @return
	 */
	public String remove() {
		checkItem = checkItemService.getCheckItem(checkItem.getId());
		checkItem.setValidflag(0);
		if(checkItemService.updateCheckItem(checkItem)!=null){
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
		checkItem = checkItemService.getCheckItem(checkItem.getId());
		checkItem.setValidflag(1);
		if(checkItemService.updateCheckItem(checkItem)!=null){
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据恢复成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据恢复失败");
		return SUCCESS;
	}
	public String modify() {
		checkItem = checkItemService.getCheckItem(checkItem.getId());
		return "modify";
	}
	public String save() {
		User user = BusinessUtils.getCurrentUser();
		checkItem.setCreateuser(user.getUid());
		checkItem.setCreatedate(new Date());
		checkItem.setValidflag(1);
		if((checkItem=checkItemService.addCheckItem(checkItem)) != null) {
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据添加成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据添加失败");
		return SUCCESS;
	}
	public String update() {
		User user = BusinessUtils.getCurrentUser();
		checkItem.setCreateuser(user.getUid());
		checkItem.setCreatedate(new Date());
		checkItem.setValidflag(1);
		if((checkItem=checkItemService.updateCheckItem(checkItem)) != null) {
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据更新成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据更新失败");
		return SUCCESS;
	}
	public String detail() {
		checkItem = checkItemService.getCheckItem(checkItem.getId());
		return DETAIL;
	}

	/**
     * 通过 ajax 方式，获取检验项目是否已经存在
     * @return
	 * @throws UnsupportedEncodingException 
     */
	public String isExists() throws UnsupportedEncodingException {
		String checkname1 = URLDecoder.decode(request.getParameter("checkname"),"UTF-8");
		//String checkname2=URLDecoder.decode(checkname1,"UTF-8");
		System.out.println(checkname1);
    	if (checkItemService.isExists(cid,checkname1)!=-1){
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
	public CheckItem getCheckItem() {
		return checkItem;
	}

	public void setCheckItem(CheckItem checkItem) {
		this.checkItem = checkItem;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCheckname() {
		return checkname;
	}

	public void setCheckname(String checkname) {
		this.checkname = checkname;
	}
	//曾智琴
	public Map<Integer, String> getItemmap() {
		return itemmap;
	}

	public void setItemmap(Map<Integer, String> itemmap) {
		this.itemmap = itemmap;
	}
	
	
}
