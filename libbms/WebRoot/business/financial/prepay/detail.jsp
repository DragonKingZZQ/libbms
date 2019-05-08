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
		<h1>查看预付款</h1>
		<s:form action="listPrepayment" namespace="/business">
		<table class="frmtable">
		<s:hidden name="id"></s:hidden>
		<tr><td>单位名称：</td><td>${prepayment.entrustcompanyStr}</td></tr>
		<tr><td>联系人：</td><td>${prepayment.username}</td></tr>
		<tr><td>联系地址：</td><td>${prepayment.address}</td></tr>
		<tr><td>联系电话：</td><td>${prepayment.tel}</td></tr>
		<tr><td>预付日期：</td><td>${prepayment.prepaydate}</td></tr>
		<tr><td>本次预付金额：</td><td>${prepayment.prepaymoney}</td></tr>
		<tr><td>收款人：</td><td><s:date name="prepayment.receiveruser" format='yyyy-MM-dd'/></td></tr>
		<tr><td colspan="2" align="center"><input value="返 回" type="button" onclick="history.back();" /></td></tr>
		</table>
		</s:form>
		</div>
		</div>
		</div>
	</body>
</html>
