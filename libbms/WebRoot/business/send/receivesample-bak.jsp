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
		<h1>派样单收样</h1>
		<s:form action="updateSendSample" namespace="/business">
		<table class="frmtable">
		<s:hidden name="sendSample.id"></s:hidden>
		<tr><td  width="22%"><span class="mustfill">*</span>样品登记单号：</td><td><s:select name="sendSample.sampleno" id="selregiste" headerKey="" headerValue="请选择" list="registeList"  cssClass="longtext" readonly="true"></s:select></td></tr>
		<tr><td><span class="mustfill">*</span>样品编号：</td><td><s:textfield  id="no" name="sendSample.sendsampleno" readonly="true" cssClass="hiddenlongtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>样品名称：</td><td><s:textfield  id="noname" name="sendSample.sendsamplename"  readonly="true" cssClass="hiddenlongtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>样品数量：</td><td><s:textfield name="sendSample.samplecount"  readonly="true" cssClass="hiddenlongtext"></s:textfield></td></tr>
		<tr><td>存储条件：</td><td><s:checkboxlist list="#{'1':'常温','2':'冷藏','3':'冷冻','4':'避光','5':'干燥'}" name="sendSample.storecondition"  disabled="true"/></td></tr>
		<tr><td><span class="mustfill">*</span>检验项目：</td><td></td></tr>
		<tr><td></td><td id="itemlist">
		<s:iterator value="subno" status="sub" id="s">
		   <div style="border:1px dashed gray" id="listdiv${sub.index}"><table>
		      <tr><td>样品编号 ：<br>${s}  <br></td><td id="listdivitem${sub.index}">
				  
					<s:iterator value="sciList" >
					  <s:if test='#s==subsendsampleno'>
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
				        <tr><td><strong>依据：</strong><s:property value="standard"/></td></tr>  --%>
					    </table></div>
					    </s:if>
					</s:iterator>
					</td></tr>
			</table></div>	  
		</s:iterator>
		</td></tr>
		<tr><td><span class="mustfill">*</span>要求开始日期：</td><td>
		<s:textfield id="startdate"  name="sendSample.startdate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly" cssClass="hiddenshorttext">
		<s:param name="value"><s:date name="sendSample.startdate" format="yyyy-MM-dd"/></s:param></s:textfield>
		</td></tr> 
		<tr><td><span class="mustfill">*</span>要求完成日期：</td><td>
		<s:textfield id="enddate" name="sendSample.enddate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly" cssClass="hiddenshorttext">
		<s:param name="value"><s:date name="sendSample.enddate" format="yyyy-MM-dd"/></s:param></s:textfield>
		</td></tr>
        <tr id="tr_input_user"><td><span class="mustfill">*</span>检验人员：</td><td><s:select name="sendSample.examineuser" list="map"  readonly="true" cssClass="shortext"></s:select></td></tr>
        <tr  style="background:#DDD" height="30"><td colspan="2">请填写以下内容：</td></tr>
        <tr id="tr_input_user"><td><span class="mustfill">*</span>是否收样：</td><td><s:radio list="#{'1':'已收'}" name="sendSample.receiveflag" value="1" /></td></tr>
		<tr id="tr_input_address"><td><span class="mustfill">*</span>收样人：</td><td><s:select name="sendSample.receiveuser" list="map" cssClass="shortext"></s:select></td></tr>
		<tr id="tr_input_relation"><td><span class="mustfill">*</span>收样日期：</td><td><s:textfield id="receivedate" name="sendSample.receivedate"  data-validation="length" data-validation-length="1-50" data-validation-error-msg="字数范围在(1-50)"  cssClass="shorttext">
		<s:param name="value"><s:date name="sendSample.receivedate" format="yyyy-MM-dd"/></s:param></s:textfield></td></tr>
<!--        <tr id="tr_input_user"><td>是否检验完成：</td><td><s:radio list="#{'1':'完成','0':'未完成'}" name="sendSample.finishflag" value="0" /></td></tr>-->
<!--		<tr id="tr_input_address"><td>实际检验人：</td><td><s:select name="sendSample.finishexaminuser" list="map" cssClass="shortext"></s:select></td></tr>-->
<!--		<tr id="tr_input_relation"><td>检验完成日期：</td><td><s:textfield id="finishdate" name="sendSample.finishdate"   cssClass="shorttext">-->
<!--		<s:param name="value"><s:date name="sendSample.finishdate" format="yyyy-MM-dd"/></s:param></s:textfield></td></tr>-->
<!--	    <tr><td>派样人：</td><td><s:textfield name="sendSample.leader" readonly="true" cssClass="shorttext"></s:textfield></td></tr>-->
	    <tr><td>备注：</td><td><s:textarea name="sendSample.remark" cssStyle="width:400px;height:110px;" data-validation="length" data-validation-length="0-200" data-validation-error-msg="字数范围在(0-200)" ></s:textarea></td></tr>
		<tr><td></td><td>（还可以输入<span id="textarea_length_max">200</span>字）</td><td></td></tr>
	    <tr>
           	<td colspan="2" class="textdesc">注意：以上<span class="mustfill">*</span>标识的为必填项</td>
        </tr>
		<tr><td colspan="2" align="center"><s:submit value="保 存" ></s:submit><input value="返 回" type="button" onclick="history.back();" /></td></tr>
		</table>
		</s:form>
        </div>
		</div>
		</div>
		<script type="text/javascript">
		$.validate({modules : 'platform'});
		set_date_picker($('#receivedate'));
	    </script>
	</body>
</html>