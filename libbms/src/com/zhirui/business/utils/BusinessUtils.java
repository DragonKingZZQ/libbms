package com.zhirui.business.utils;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.kepu.base.BaseConstant;
import cn.kepu.base.memory.ContextStorage;
import cn.kepu.utils.PropertyConfigureUtils;
import cn.kepu.utils.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.zhirui.business.common.Constants;
import com.zhirui.business.common.bean.User;

/**
 * 与业务相关的系统工具类
 * 
 * @author zhangzl
 */
public class BusinessUtils {

	private static final Log log = LogFactory.getLog(BusinessUtils.class);

	/**
	 * 取得当前的登录用户
	 * @return
	 * @author zhangzl
	 */
	public static User getCurrentUser() {
		ActionContext context = ActionContext.getContext();
		if(context == null) return null;
		Map<String, Object> session = context.getSession();
		if(session == null) return null;

		Object user = session.get(BaseConstant.LOGIN_USER_COOKIE_KEY);
		if(user != null && user instanceof User/* && ((User)user).getUid() != 0*/) {
			return (User)user;
		}
		return null;
	}

	/**
	 * 判断用户是否登录
	 * @return
	 * @author zhangzl
	 */
	public static boolean isUserLogin() {
		if(getCurrentUser() != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获取当前请求的处理时间点
	 * @return
	 * @author zhangzl
	 */
	public static Date getRequestTime() {
		Date date=(Date)ContextStorage.getContextValue("now");
		if(date==null) 
			return new Date();
		else
			return date;
	}
	
	public static void setSessionCookie(Object user) {
		setSessionCookie(user,  null);
	}
	public static void setSessionCookie(Object user, String cookieCode) {
		ActionContext context = ActionContext.getContext();
		if(context == null) return;
		Map<String, Object> session = context.getSession();
		if(session == null) return;

		// Add to Session and Cookie
		session.put(BaseConstant.LOGIN_USER_COOKIE_KEY, user);

		if (!StringUtils.isEmpty(cookieCode)) {
			// Do not need cookie any more
		}
	}

	/**
	 * Remove all information of user stored in session and cookie
	 * @author zhangzl
	 */
	public static void removeSessionCookie() {
		ActionContext context = ActionContext.getContext();
		if(context == null) return;
		Map<String, Object> session = context.getSession();
		if(session == null) return;

		session.put(Constants.LAST_USER_SESS_KEY, null);
		session.remove(Constants.LAST_USER_SESS_KEY);

		session.put(BaseConstant.LOGIN_USER_COOKIE_KEY, null);
		session.remove(BaseConstant.LOGIN_USER_COOKIE_KEY);
	}
	
	/**
	 * @param password
	 * @author zhangzl
	 */
	public static String getEncodePassword(String password) {
		if(StringUtils.isEmpty(password)) return "";
		return DigestUtils.md5Hex(DigestUtils.md5Hex(password) + Constants.PASSWORD_SALT);
	}

	public static User getUserLastInfo() {
		ActionContext context = ActionContext.getContext();
		if(context == null) return getCurrentUser();
		Map<String, Object> session = context.getSession();
		if(session == null) return getCurrentUser();

		User user = (User)session.get(Constants.LAST_USER_SESS_KEY);
		return user==null?getCurrentUser():user;
	}

	public static boolean setUserLastInfo(User lastinfo) {
		ActionContext context = ActionContext.getContext();
		if(context == null) return false;
		Map<String, Object> session = context.getSession();
		if(session == null) return false;

		session.put(Constants.LAST_USER_SESS_KEY, lastinfo);
		return true;
	}

	// 文件上传相关
	public static boolean isAllowedUpload(String suffix) {
		String allowedSuffix = PropertyConfigureUtils.getString("system.upload.allowed.suffix");
		suffix = "|"+suffix.toLowerCase()+"|";
		if(allowedSuffix.indexOf(suffix) >= 0) {
			return true;
		}
		return false;
	}
	public static String createUploadFolder(String basePath, boolean withDate) {
		String uploadFolderName= basePath;
		if (withDate) {
			String subFolder = new SimpleDateFormat("yyyyMMdd").format(getRequestTime());
			uploadFolderName = StringUtils.combineUnixPath(uploadFolderName, subFolder);
		}
		if (!uploadFolderName.endsWith("/")) {
			uploadFolderName += "/";
		}
		if(File.separatorChar == '\\') {
			uploadFolderName = uploadFolderName.replace('/', '\\');
		}
		File uploadFolder = new File(PropertyConfigureUtils.getString("real_path")+uploadFolderName);
		if (!uploadFolder.exists() || !uploadFolder.isDirectory()) {
			if (!uploadFolder.mkdirs()) {
				return basePath;
			}
		}
		return uploadFolderName;
	}
	public static String uploadfile(File file, String filename) {
		String filesuffix = StringUtils.getFilePostfix(filename);
		if(!isAllowedUpload(filesuffix)) {
			if(log.isWarnEnabled()) {
				log.warn("File surfix [" + filesuffix + "] is not allowed to upload");
			}
			return null;
		}
		String systemUploadDir = PropertyConfigureUtils.getString(BaseConstant.CONFIG_UPLOAD_DIR_KEY);
		String basePath = createUploadFolder(systemUploadDir, true);

		String tempName = filename;
		String newFileName = basePath+StringUtils.randomFileNameShort(tempName);
		File f = new File(PropertyConfigureUtils.getString("real_path")+newFileName);
		try{
			FileUtils.copyFile(file, f);
			log.info("save file: "+basePath+tempName+" success!");
		}catch(IOException e){
			log.info("save file: "+basePath+tempName+" failure!", e);
			return null;
		}
		return newFileName.replace('\\', '/');
	}

	public static String formatTexttoHtml(String text)
	{
		return text.replaceAll("\n","<br />");
	}
}
