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
		<h1>查看结算</h1>
		<s:form action="listIncome" namespace="/business">
		<table class="frmtable">
		<s:hidden name="id"></s:hidden>
		<tr><td  width="20%">单位名称：</td><td>${income.entrustcompanyStr}</td></tr>
		<tr><td>联系人：</td><td>${income.username}</td></tr>
		<tr><td>联系地址：</td><td>${income.address}</td></tr>
		<tr><td>联系电话：</td><td>${income.tel}</td></tr>
		<tr><td>付款日期：</td><td><s:date name="income.paydate" format='yyyy-MM-dd'/></td></tr>
		<s:if test='income.paytype==1'>
		   <tr><td>结算方式：</td><td>预付款结算</td></tr>
		</s:if>
		<s:elseif test='income.paytype==3'>
		    <tr><td>结算方式：</td><td>系统自动结算 </td></tr>
		</s:elseif>
		<s:else>
		   <tr><td>结算方式：</td><td>现金结算</td></tr>
		</s:else>
		<s:iterator value="regvalueList" status="index">    
		     <tr><td></td><td id="calculate"><div id="modidiv<s:property value="#index.index"/>">样品登记：<s:property value="value"/></div></td></tr>
		</s:iterator>
		<tr><td>合计检测费：</td><td>${income.totalcheckmoney}</td></tr>
		<tr><td>付款金额：</td><td>${income.paymoney}</td></tr>
		<tr><td>收款人：</td><td>${income.receiveuser }</td></tr>
		<tr><td>发票类型：</td><td><s:if test='income.taxtype==1'>增值税专用发票</s:if><s:if test='income.taxtype==2'>增值税普通发票</s:if></td></tr>
		<s:if test='income.billischeck=="1"'>
			<tr><td>发票是否开具：</td><td>已开</td></tr>
			<tr><td>发票快递单号：</td><td>${income.billno}</td></tr>
		</s:if>
		<s:else>
		    <tr><td>发票是否开具：</td><td>未开</td></tr>
		</s:else>
		<tr><td colspan="2" align="center"><input value="返 回" type="button" onclick="history.back();" /></td></tr>
		</table>
		</s:form>
		</div>
		</div>
		</div>
	</body>
</html>
