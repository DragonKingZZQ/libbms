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
		<h1>查看检验报告数据</h1>
		<s:form action="listCheckReport" namespace="/business">
		<table class="frmtable">
		<s:hidden name="id"></s:hidden>
		<tr><td width="18%">检测报告编号：</td><td>${checkReport.sampleno}</td></tr>
		<tr><td>样品名称：</td><td>${checkReport.samplename}</td></tr>
		<tr><td>样品状态：</td><td>${checkReport.samplestatus}</td></tr>
		<tr><td>规格型号：</td><td>${checkReport.samplemodel}</td></tr>
		<tr><td>样品数量：</td><td>${checkReport.samplecount}</td></tr>
		<tr><td>委托单位：</td><td>${checkReport.entrustcompany}</td></tr>
		<tr><td>生产单位：</td><td>${checkReport.productionunit}</td></tr>
		<tr><td>注册商标：</td><td>${checkReport.trademark}</td></tr>
		<tr><td>接收日期：</td><td><s:date name="checkReport.receivedate" format='yyyy-MM-dd'/></td></tr>
		<tr><td>检验项目：</td><td></td></tr>
		<tr><td colspan="2" id="itemlist">
		<s:iterator value="checkResultList" >
		<div style="border:1px dashed gray" id="listdiv'${st.index}"><table>
		<tr><td>样品编号 ：<br>${subsendsampleno}  <br> </td>
		   <td id="listdivitem${st.index}">
		    <div style="border:1px dashed gray"><table><tr><td><strong>项目：</strong><s:property value="checkitemname"/></td></tr>
		    <%-- <tr><td><strong>检验依据：</strong> 
		    <s:if test='type=="2"'>
		                    由本中心选定合适的标准方法
		    </s:if>
		    <s:if test='type=="3"'>
		                    同意用本中心确定的非标准方法
		    </s:if>
		    <s:if test='type=="1"'>
		                    指定检验依据
		    </s:if>
		    </td></tr>
		     --%>
	        <tr><td><strong>检验标准：</strong><s:property value="standard"/></td></tr>
		    <tr><td><strong>检验仪器：&nbsp;&nbsp;&nbsp; &nbsp;</strong><s:property  value="instrumentname"/></td></tr>
	        <tr><td><strong>方法检出限： </strong><s:property  value="checkscope" /></td></tr>
<%-- 	        <tr><td><strong>单位： &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong><s:property  value="unit" /></td></tr>
 --%>	       
<%--  		   <tr><td><strong>单位： &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong><s:property  value="standard" /></td></tr>
 --%> 		   <tr><td><strong>检验结果：&nbsp;&nbsp;&nbsp;&nbsp;</strong><s:property  value="checkresult" /></td></tr>
	       </td></tr></table></div>
	        </table></div>
		</s:iterator>
		</td></tr>
		<tr><td>检验完成日期：</td><td><s:date name="checkReport.receivedate" format='yyyy-MM-dd'/></td></tr>
		<tr><td>委托单位备注：</td><td>${checkReport.remark}</td></tr>
		<tr><td>结果备注：</td><td>${checkReport.resultremark}</td></tr>
		<tr><td>结果分析：</td><td>${checkReport.analyzeremark}</td></tr>
		<tr><td colspan="2" align="center"><input value="返 回" type="button" onclick="history.back();" /></td></tr>
		</table>
		</s:form>
		</div>
		</div>
		</div>
	</body>
</html>
