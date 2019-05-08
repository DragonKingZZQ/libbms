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
		<h1>查看派样单数据</h1>
		<s:form action="listSendSample" namespace="/business">
		<table class="frmtable">
		<s:hidden name="id"></s:hidden>
<!--		<tr><td>样品登记编号：</td><td>${sampleRegiste.sampleno}</td></tr>-->
		<tr><td>样品编号：</td><td>${sendSample.sendsampleno}</td></tr>
		<tr><td  width="20%">样品名称：</td><td>${sendSample.sendsamplename}</td></tr>
		<tr><td  width="20%">样品数量：</td><td>${sendSample.samplecount}</td></tr>
		<tr><td>存储条件：</td><td><s:if test = 'sendSample.storecondition.indexOf("1")>=0'>常温 </s:if>
		   <s:if test='sendSample.storecondition.indexOf("2")>=0'>冷藏 </s:if>
		   <s:if test='sendSample.storecondition.indexOf("3")>=0'>冷冻 </s:if>
		   <s:if test='sendSample.storecondition.indexOf("4")>=0'>避光 </s:if>
		   <s:if test='sendSample.storecondition.indexOf("5")>=0'>干燥 </s:if>
		   </td>
		</tr>
		<tr><td>检验项目：</td><td></td></tr>
		<tr><td colspan="2" id="itemlist">
		<s:iterator value="subno" status="sub" id="s">
		   <div style="border:1px dashed gray"><table>
		      <tr><td>样品编号 ：<br>${s}  <br></td><td>
				  <s:iterator value="sciList" status="st">
				    <s:if test='#s==subsendsampleno'>
					    <div style="border:1px dashed gray"><table><tr><td><strong>项目：</strong></td><td><s:property value="checkitemname"/></td></tr>
					   <%--  <tr><td><strong>检验依据：</strong></td><td> 
					    <s:if test='type=="2"'>
					                    由本中心选定合适的标准方法
					    </s:if>
					    <s:if test='type=="3"'>
					                    同意用本中心确定的非标准方法
					    </s:if>
					    <s:if test='type=="1"'>
					                    指定检验依据
					    </s:if> --%>
					    </td></tr>
				       <%--  <tr><td><strong>依据：</strong></td><td><s:property value="standard"/></td></tr> --%>
					    <tr><td><strong>检验仪器：</strong></td><td><s:property  value="instrumentname"/></td></tr>
				        <tr><td><strong>方法检出限： </strong></td><td><s:property  value="checkscope" /></td></tr>
				        <tr><td><strong>单位： </strong></td><td><s:property  value="unit" /></td></tr>
				        <tr><td><strong>检验标准： </strong></td><td><s:property  value="standard" /></td></tr>
				        <tr><td><strong>检验结果：</strong></td><td><s:property  value="checkresult" /></td></tr>
				        </table></div>
				        </s:if>
					</s:iterator>
					</td></tr>
			</table></div>	  
		</s:iterator>
		</td></tr>
		<tr><td>要求开始日期：</td><td><s:date name="sendSample.startdate" format='yyyy-MM-dd'/></td></tr>
		<tr><td>要求完成日期：</td><td><s:date name="sendSample.enddate" format='yyyy-MM-dd'/></td></tr>
		<tr><td>检验人员：</td><td>${sendSample.examineuserStr}</td></tr>
		
		<tr><td>是否收样：</td><td><s:if test = 'sendSample.receiveflag=="1"'>已收</s:if><s:else>未收</s:else></td></tr>
						
		<tr><td>收样人：</td><td>${sendSample.receiveuserStr}</td></tr>
		<tr><td>收样日期：</td><td><s:date name="sendSample.receivedate" format='yyyy-MM-dd'/></td></tr>
		<tr><td>是否检验完成：</td><td><s:if test = 'sendSample.finishflag=="1"'>完成</s:if><s:else>未完成</s:else></td></tr>
		<tr><td>实际检验人：</td><td>${sendSample.finishexaminuserStr}</td></tr>
		<tr><td>检测完成日期：</td><td><s:date name="sendSample.finishdate" format='yyyy-MM-dd'/></td></tr>
		<tr><td>备注：</td><td>${sendSample.remark}</td></tr>
		<tr><td colspan="2" align="center"><input value="返 回" type="button" onclick="history.back();" /></td></tr>
		</table>
		</s:form>
		</div>
		</div>
		</div>
	</body>
</html>
