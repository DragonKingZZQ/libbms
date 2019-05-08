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
		<h1>查看用户数据</h1>
		<s:form action="listUser" namespace="/business/admin">
		<table class="frmtable">
		<s:hidden name="id"></s:hidden>
		<tr><td>用户名称：</td><td>${user.showname}</td></tr>
		<tr><td>联系电话：</td><td>${user.telphone}</td></tr>
		<tr><td>所属科室：</td><td>${user.officeStr}</td></tr>
		<tr><td>职务：</td><td>${user.duty}</td></tr>
		<tr><td>系统用户名称：</td><td>${user.name}</td></tr>
		<tr><td>用户类型：</td><td>${user.authority}</td></tr>
		<tr><td colspan="2" align="center"><input value="返 回" type="button" onclick="history.back();" /></td></tr>
		</table>
		</s:form>
		</div>
		</div>
		</div>
	</body>
</html>
