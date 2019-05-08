package com.zhirui.business.common;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.zhirui.business.common.bean.Qualification;
import com.zhirui.business.common.bean.SampleCategory;
import com.zhirui.business.common.bean.User;
import com.zhirui.business.common.service.QualificationService;
import com.zhirui.business.common.service.SampleCategoryService;
import com.zhirui.business.common.service.UserService;
import com.zhirui.business.utils.ApplicationUtils;

import cn.kepu.base.annotation.LoaderFunction;
import cn.kepu.base.loader.BaseStartupLoader;
import cn.kepu.utils.PropertyConfigureUtils;

/**
 * 系统启动时，加载配置信息和相关的初始化操作
 * 
 * @author zhangzl
 */
@Repository("default_loader")
public class StartupLoader extends BaseStartupLoader {

	private static final Log log = LogFactory.getLog(StartupLoader.class);

	@LoaderFunction(1)
	public void loadConfig() {
		if(log.isInfoEnabled())
			log.info(PropertyConfigureUtils.getInstance().toString());
	}

	@LoaderFunction(2)
	public void loadExamineUser() {
		UserService service = (UserService)getBean("userService");
		List<User> users = service.getExamineUser();
		ApplicationUtils.setExamineUser(users);
	}
	
	@LoaderFunction(3)
	public void loadQualification() {
		QualificationService service = (QualificationService)getBean("qualificationService");
		List<Qualification> qualifications = service.getAll();
		ApplicationUtils.setQualification(qualifications);
	}

	

	@LoaderFunction(4)
	public void loadBaseStatus() {
		// 数据表状态
		Map<String, String> baseStatusMap = new LinkedHashMap<String, String>();
		baseStatusMap.put(Constants.STATUS_CREATE, "登记完成");
		baseStatusMap.put(Constants.STATUS_REGISTEVERIFY, "等待派样");
		baseStatusMap.put(Constants.STATUS_SENDSAMPLE, "登记完成");
		baseStatusMap.put(Constants.STATUS_SAMPLED, "已经派样");
		
		baseStatusMap.put(Constants.STATUS_RECEIVEDSAMPLE, "收到派样");   //管理员确认
		baseStatusMap.put(Constants.STATUS_FINISHCHECK, "检验完成"); //管理员退回
		baseStatusMap.put(Constants.STATUS_FINISHEDSAMPLE, "完成派样");
		baseStatusMap.put(Constants.STATUS_FINISHREPORT, "已出报告");   //管理员审核
		baseStatusMap.put(Constants.STATUS_FINISH, "任务完成");  //审核不通过

		ApplicationUtils.setBaseStatusMap(baseStatusMap);
	}
	//曾智琴
	@LoaderFunction(5)
	public void loadSampleCategory() {
		SampleCategoryService service = (SampleCategoryService)getBean("sampleCategoryService");
		List<SampleCategory> sampleCategory = service.getAll();
		ApplicationUtils.setSampleCategory(sampleCategory);
	}
	
}
