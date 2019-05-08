package com.zhirui.business.utils;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.zhirui.business.common.bean.Qualification;
import com.zhirui.business.common.bean.SampleCategory;
import com.zhirui.business.common.bean.User;



import cn.kepu.base.memory.StaticStorage;


/**
 * 应用全局存储信息工具类
 * @author zhangzl
 */
public class ApplicationUtils {
	public static void setBaseStatusMap(Map<String, String> baseStatusMap) {
		StaticStorage.setStaticValue("BASESTATUS_MAP", baseStatusMap);
	}
	@SuppressWarnings("unchecked")
	public static Map<String, String> getBaseStatusMap() {
		return (Map<String, String>)StaticStorage.getStaticValue("BASESTATUS_MAP");
	}
	//检验员设置
	public static void setExamineUser(List<User> users) {
		Map<String, String> userList = new LinkedHashMap<String, String>();
		for(User user:users){
			userList.put(String.valueOf(user.getUid()), user.getShowname());
		}
		StaticStorage.setStaticValue("EXAMINEUSER_MAP", userList);
	}
	@SuppressWarnings("unchecked")
	public static Map<String,String> getExamineUser() {
		return (Map<String,String>)StaticStorage.getStaticValue("EXAMINEUSER_MAP");
	}
	//资质设置
	public static void setQualification(List<Qualification> qualifications) {
		Map<String, String> qualificationList = new LinkedHashMap<String, String>();
		for(Qualification q:qualifications){
			qualificationList.put(String.valueOf(q.getId()), q.getName());
		}
		StaticStorage.setStaticValue("QUALIFICATION_MAP", qualificationList);
	}
	@SuppressWarnings("unchecked")
	public static Map<String,String> getQualification() {			
		return (Map<String,String>)StaticStorage.getStaticValue("QUALIFICATION_MAP");
	}
	//样品分类设置  曾智琴
	public static void setSampleCategory(List<SampleCategory> sampleCategory) {
		Map<String, String> sampleCategoryList = new LinkedHashMap<String, String>();
		for(SampleCategory q:sampleCategory){
			sampleCategoryList.put(String.valueOf(q.getId()), q.getCategory());
		}
		StaticStorage.setStaticValue("SAMPLECATEGORY_MAP", sampleCategoryList);
	}
	@SuppressWarnings("unchecked")
	public static Map<String,String> getSampleCategory() {
		System.out.println(StaticStorage.getStaticValue("SAMPLECATEGORY_MAP"));
		return (Map<String,String>)StaticStorage.getStaticValue("SAMPLECATEGORY_MAP");
	}
}
