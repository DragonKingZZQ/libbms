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
		<h1>查看实验过程数据</h1>
		<s:form action="listLabProcess" namespace="/business">
		<table class="frmtable">
		<s:hidden name="id"></s:hidden>
		<tr><td>实验过程标题：</td><td>${labProcess.labprocesstitle}</td></tr>
		<tr><td>实验过程内容：</td><td>${labProcess.labprocesscontent}</td></tr>

		<tr><td colspan="2" align="center"><input value="返 回" type="button" onclick="history.back();" /></td></tr>
		</table>
		</s:form>
		</div>
		</div>
		</div>
	</body>
</html>
