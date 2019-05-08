package com.zhirui.business.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.zhirui.business.common.bean.User;
import com.zhirui.business.utils.BusinessUtils;

import cn.kepu.base.annotation.Authority;
import cn.kepu.base.checker.AuthorityChecker;
import cn.kepu.utils.ContextUtils;
import cn.kepu.utils.PropertyConfigureUtils;
import cn.kepu.utils.StringUtils;

/**
 * Action级别的系统权限检测
 * 
 * @author zhangzl
 */
@Repository("auth_checker")
public class AuthChecker extends AuthorityChecker {

	private static final Log log = LogFactory.getLog(AuthorityChecker.class);

	private static final String LOGIN = "global_login";
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@Override
	public String check(Authority auth) {
		if(auth.login()) {
			if (!BusinessUtils.isUserLogin()) {
				if (log.isDebugEnabled()) {
					log.debug("用户需要登录后，才可访问！");
				}
				return LOGIN;
			}
			User user = BusinessUtils.getCurrentUser();
			
		}
		return SUCCESS;
	}
	
	private boolean inArray(String[] array, String item) {
		if (array == null) {
			return true;
		}
		if (StringUtils.isEmpty(item)) {
			return false;
		}
		for (String aitem : array) {
			if(item.indexOf("|"+aitem+"|") >= 0) {
				return true;
			}
		}
		return false;
	}

}
