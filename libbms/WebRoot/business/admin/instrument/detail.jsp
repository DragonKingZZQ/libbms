<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		
		<link href="<%=path %>/css/jquery.datepicker.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
	<s:include value="/include/common.jsp"></s:include>
	<div id="pagebody">
	<div id="mainbody">
	<div id="contentbar">
		<h1>查看仪器数据</h1>
		<s:form action="listInstrument" namespace="/business/admin">
		<table class="frmtable">
		<s:hidden name="id"></s:hidden>
		<tr><td>仪器名称：</td><td>${instrument.codename}</td></tr>
		<tr><td>规格型号：</td><td>${instrument.standard}</td></tr>
		<tr><td>测量范围：</td><td>${instrument.measueRange}</td></tr>
		<tr><td>准确度等级：</td><td>${instrument.grade}</td></tr>
		<tr><td>生产单位：</td><td>${instrument.product}</td></tr>
		<tr><td>校准单位：</td><td>${instrument.correctCompany}</td></tr>
		<tr><td>校准周期：</td><td>${instrument.correctCycle}</td></tr>
		<tr><td>最近校准日期：</td><td><s:date name="instrument.recentCorrectDate" format='yyyy-MM-dd'/></td></tr>
		<tr><td>下次校准日期：</td><td><s:date name="instrument.nextCorrectDate" format='yyyy-MM-dd'/></td></tr>
		<tr><td>备注：</td><td>${instrument.remark}</td></tr>
		<tr><td colspan="2" align="center"><input value="返 回" type="button" onclick="history.back();" /></td></tr>
		</table>
		</s:form>
		</div>
		</div>
		</div>
	</body>
</html>
