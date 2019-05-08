package com.zhirui.business.common.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhirui.business.common.bean.Notice;
import com.zhirui.business.common.bean.User;
import com.zhirui.business.common.service.NoticeService;
import com.zhirui.business.common.service.UserService;
import com.zhirui.business.utils.BusinessUtils;

import cn.kepu.base.MessageType;
import cn.kepu.base.action.BaseAction;
import cn.kepu.utils.ContextUtils;
import cn.kepu.utils.StringUtils;

/**
 * 用户登录相关流程处理，包括用户登录和登出
 * @author zy
 */
public class UserAction extends BaseAction {

	private static final long serialVersionUID = -4148776874506302404L;

	private static final Log log = LogFactory.getLog(UserAction.class);

	@Autowired
	private UserService userService;
	@Autowired
	private NoticeService noticeService;	

	private User user;
	private Notice notice;
	public Notice getNotice() {
		return notice;
	}
	private List<Notice> lstNotice1;
	private List<Notice> lstNotice2;
	
	private String checkcode;

	/**
	 * 系统首页面跳转
	 */
	public String index() {
		return "to_index";
	}
	
	/**
	 * 通知公告页面
	 */
	public String notice() {
		int id;
		try
		{
			id=Integer.parseInt((String)request.getParameter("id"));
		}catch(Exception ex){id=-1;}
		notice=noticeService.getNotice(id);
		
		return "notice";
	}
	
   //party admin 
	public String adminpartyindex() {
		return "adminparty_index";
	}
	/**
	 * 用户登录页面跳转
	 */
	public String login() {
		if (BusinessUtils.isUserLogin()) {
			if (log.isInfoEnabled()) {
				log.info("用户["+BusinessUtils.getCurrentUser().getName()+"]已经登录");
			}
			return index();
		}
		return "party_login";
	}
	/**
	 * 登录表单处理
	 */
	public String dologin() {
//		if (log.isDebugEnabled()) log.debug("[Debug] user login...");
//		String verifyCode = (String) session.get(com.jwstudio.verifycode.Constants.JWVERIFYCODE_SESSION_KEY);
//		if (!StringUtils.isEmpty(checkcode) && checkcode.equalsIgnoreCase(verifyCode)) {
			User dbuser = userService.checkUser(user);
			if(dbuser == null) {
				return login();
			}
			userService.updateLoginInfo(dbuser, request.getRemoteAddr());
			user = dbuser;
			BusinessUtils.setSessionCookie(user);
			return index();
//		} else {
//			ContextUtils.setOpMessage(MessageType.ERROR, "验证码输入错误");
//		}
		
//		return login();
	}
	/**
	 * 用户登出
	 */
	public String logout() {
		if (log.isDebugEnabled()) log.debug("[Debug] user logout");
		BusinessUtils.removeSessionCookie();
		return "login";
	}

	public String changepassword() {
		return "change_password";
	}
	public String savepassword() {
		String verifyCode = (String) session.get(com.jwstudio.verifycode.Constants.JWVERIFYCODE_SESSION_KEY);
		if (StringUtils.isEmpty(checkcode) || !checkcode.equalsIgnoreCase(verifyCode)) {
			ContextUtils.setOpMessage(MessageType.ERROR, "验证码输入有误，请重新提交请求");
			return "to_change_password";
		}
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("password");
		if(StringUtils.isEmpty(oldpassword) || StringUtils.isEmpty(newpassword)) {
			ContextUtils.setOpMessage(MessageType.ERROR, "密码输入有误，请重新提交请求");
			return "to_change_password";
		}
		User user = new User();
		user.setUid(BusinessUtils.getCurrentUser().getUid());
		user.setPassword(oldpassword);
		if(userService.updateSecurity(user, newpassword) != null) {
			ContextUtils.setOpMessage(MessageType.SUCCESS, "密码修改成功");
		} else {
			ContextUtils.setOpMessage(MessageType.ERROR, "密码输入有误，请检查后重新提交请求");
		}
		return "to_change_password";
	}
	public String changeinfo() {
		user = userService.getUser(BusinessUtils.getCurrentUser().getUid());
		return "change_info";
	}
	public String saveinfo() {
		if(user == null) {
			ContextUtils.setOpMessage(MessageType.WARN, "用户非法访问");
			return ERROR;
		}
		user.setUid(BusinessUtils.getCurrentUser().getUid());
		if(userService.updateInfo(user) != null) {
			ContextUtils.setOpMessage(MessageType.SUCCESS, "用户信息修改成功");
		} else {
			ContextUtils.setOpMessage(MessageType.ERROR, "用户信息修改失败，请检查后重新提交请求");
		}
		return "to_change_info";
	}

	// getters and setters
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String getCheckcode() {
		return checkcode;
	}
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	public List<Notice> getLstNotice1() {
		return lstNotice1;
	}
	public List<Notice> getLstNotice2() {
		return lstNotice2;
	}	
}
