<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<body>
	<s:include value="/include/common.jsp"></s:include>
	<div id="pagebody">
	<div id="mainbody">
	<div id="contentbar">
		<h1>派样单收样</h1>
		<s:form action="receiveSendSample" namespace="/business">
		<table class="frmtable">
		<s:hidden name="sendSample.id"></s:hidden>
		<s:hidden name="sendSample.receiveflag" value="1"></s:hidden>
		<tr><td>派样人：</td><td>${sendSample.leaderStr}</td></tr>
		<tr><td>样品登记单号：</td><td>${sendSample.sendsampleno}</td></tr>
		<tr><td>样品名称：</td><td>${sendSample.sendsamplename}</td></tr>
		<tr><td>样品数量：</td><td>${sendSample.samplecount}</td></tr>
		<tr><td>存储条件：</td><td><s:checkboxlist list="#{'1':'常温','2':'冷藏','3':'冷冻','4':'避光','5':'干燥'}" name="sendSample.storecondition"  disabled="true"/></td></tr>
		<tr><td>检验项目：</td><td></td></tr>
		<tr><td id="itemlist"  colspan="2">
		<s:iterator value="subno" status="sub" id="s">
		   <div style="border:1px dashed gray" id="listdiv${sub.index}"><table>
		      <tr><td>样品编号 ：<br>${s}  <br></td><td id="listdivitem${sub.index}">
				  
					<s:iterator value="sciList" >
					  <s:if test='#s==subsendsampleno'>
					    <div style="border:1px dashed gray"><table><tr><td><strong>项目：</strong><s:property value="checkitemname"/></td></tr>
					    <tr><td><strong>检验标准：</strong> ${sendSample.checkstandardname}</td></tr>
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
				        <tr><td><strong>依据：</strong><s:property value="standard"/></td></tr>  --%>
					    </table></div>
					    </s:if>
					</s:iterator>
					</td></tr>
			</table></div>	  
		</s:iterator>
		</td></tr>
		<tr><td>要求开始日期：</td><td><s:date name="sendSample.startdate" format='yyyy-MM-dd'/></td></tr> 
		<tr><td>要求完成日期：</td><td><s:date name="sendSample.enddate" format='yyyy-MM-dd'/></td></tr>
        <tr id="tr_input_user"><td>检验人员：</td><td>${sendSample.examineuserStr}</td></tr>
        <tr  style="background:#DDD" height="30"><td colspan="2">如需备注请填写：</td></tr>
        <tr><td>备注：</td><td><s:textarea name="sendSample.remark" cssStyle="width:400px;height:110px;" data-validation="length" data-validation-length="0-200" data-validation-error-msg="字数范围在(0-200)" ></s:textarea></td></tr>
		<tr><td></td><td>（还可以输入<span id="textarea_length_max">200</span>字）</td><td></td></tr>
		<tr><td colspan="2" align="center"><s:submit value="保 存" ></s:submit><input value="返 回" type="button" onclick="history.back();" /></td></tr>
		</table>
		</s:form>
        </div>
		</div>
		</div>
	</body>
</html>