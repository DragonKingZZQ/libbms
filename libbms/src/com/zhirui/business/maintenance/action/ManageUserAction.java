package com.zhirui.business.maintenance.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.zhirui.business.common.bean.User;
import com.zhirui.business.common.service.UserService;
import com.zhirui.business.lib.bean.Offices;
import com.zhirui.business.lib.service.OfficesService;
import com.zhirui.business.utils.BusinessUtils;
import cn.kepu.base.MessageType;
import cn.kepu.base.action.PageAction;
import cn.kepu.utils.ContextUtils;
import cn.kepu.utils.PageModel;

@SuppressWarnings("serial")
public class ManageUserAction extends PageAction<User> {
	@Autowired
	private UserService userService;
	@Autowired
	private OfficesService officesService;
	private User user;
	private String info;
	private String name;
	private Integer uid;
	public Map<String,String> map;
    private Offices offices;
	private static final Log log = LogFactory.getLog(ManageUserAction.class);
	
	/**
	 * 列表功能
	 * @return
	 */
	public String list() {
		pageModel = userService.getUser(user, pageNo, pageSize);
		//得到科室名称
		for(User user:pageModel.getList()){
			offices = officesService.getOffices(user.getOffice());
			user.setOfficeStr(offices.getName());
		}
		return LIST;
	}

	public String add() {
		map = new HashMap();
		PageModel<Offices> po = officesService.getOffices(null, pageNo, pageSize);
		for(Offices o:po.getList()){
			map.put(String.valueOf(o.getId()),o.getName());
		}
		return ADD;
	}
	/**
	 * 删除
	 * @return
	 */
	public String remove() {
		user = userService.getUser(user.getUid());
	//	user.setValidflag(0);
		//曾智琴
	//	if(userService.updateUser(user)!=null){
		if(userService.removeUser((user.getUid()))){
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
		user = userService.getUser(user.getUid());
	//	user.setValidflag(1);
		if(userService.updateUser(user)!=null){
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据恢复成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据恢复失败");
		return SUCCESS;
	}
	public String modify() {
		map = new HashMap();
		PageModel<Offices> po = officesService.getOffices(null, pageNo, pageSize);
		for(Offices o:po.getList()){
			map.put(String.valueOf(o.getId()),o.getName());
		}
		user = userService.getUser(user.getUid());
		return "modify";
	}
	public String save() {
	//	user.setValidflag(1);
		String ps = BusinessUtils.getEncodePassword(user.getPassword());
		user.setPassword(ps);
		if((user=userService.addUser(user)) != null) {
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据添加成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据添加失败");
		return SUCCESS;
	}
	public String update() {
		//user.setValidflag(1);
		//如果修改的时候密码为空的话不修改密码
		String ps = BusinessUtils.getEncodePassword(user.getPassword());
		user.setPassword(ps);
		if((user=userService.updateUser(user)) != null) {
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据更新成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据更新失败");
		return SUCCESS;
	}
	public String detail() {
		user =userService.getUser(user.getUid());
		offices = officesService.getOffices(user.getOffice());
		user.setOfficeStr(offices.getName());
		return DETAIL;
	}

	/**
     * 通过 ajax 方式，获取检验项目是否已经存在
     * @return
     */
	public String isExists(){

    	if (userService.isExists(uid,name)){
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
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
}
