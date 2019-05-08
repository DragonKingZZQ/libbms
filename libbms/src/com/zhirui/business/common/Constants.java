package com.zhirui.business.common;

/**
 * 系统常类
 * 
 * @author zy
 */
public class Constants {
	
	public static final String PASSWORD_SALT = "ANf6q24";
	public static final String LAST_USER_SESS_KEY = "_last_user";
	// 上传文件的状态
	public static final String FILE_UPLOADING = "U";	// 文件正在上传
	public static final String FILE_UPLOADED = "N";		// 文件已经上传完成
	public static final String FILE_DELETED = "D";		// 文件删除
	public static final String FILE_REJECT = "R";		// 文件被拒绝，审核不通过
	
	public static final int MAX_ATTACHMENT_NUM = 50;
	
	public static final int MAX_COUNT = 9999;  // 获取下拉框中的 列表的最大数量
	
	public static final String STATUS_CREATE="A";          //登记完成
	public static final String STATUS_REGISTEVERIFY="B";   //确认可以送样
	public static final String STATUS_SENDSAMPLE="C";      //送样登记送样
	public static final String STATUS_SAMPLED="D";         //已经送样
	public static final String STATUS_FINISHEDSAMPLE="E";  //完成派样
	public static final String STATUS_RECEIVEDSAMPLE="F";  //接到送样
	public static final String STATUS_FINISHCHECK="G";     //检验完成
	public static final String STATUS_FINISHREPORT="H";    //已出报告
	public static final String STATUS_FINISH="I";          //全部完成
	//public static final String STATUS_CASHED="J";          //已经结算
	
	//导出模板的常量
	public static final String TEMPLATE_SAMPLE ="sample.ftl" ;           //样品登记
	public static final String TEMPLATE_SENDSAMPLE = "sendsample.ftl";         //派样单   曾智琴
	public static final String TEMPLATE_YF003 = "YF_003.ftl";
	public static final String TEMPLATE_CHECKREPORT ="checkreport.ftl";       //检验报告  检测报告
	public static final String TEMPLATE_CHECKREPORT2 ="checkreport2.ftl";     //分析报告  一个样品
	public static final String TEMPLATE_CHECKREPORT3 ="checkreport3.ftl";     //分析报告 多个样品
	// 超级用户，组长，组员
	public static final String RIGHT_ADMIN="A";
	public static final String RIGHT_MEMBERLEADER="B";
	public static final String RIGHT_MEMBER="C";
	
	//警告提示前的天数
	public static final int WARN_DAYS=2;
	public static final int WARNLEADER_DAYS=1;
}
