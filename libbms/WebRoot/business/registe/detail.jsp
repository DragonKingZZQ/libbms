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
		<h1>查看样品登记数据</h1>
		<s:form action="listSampleRegiste" namespace="/business">
		<table class="frmtable">
		<s:hidden name="id"></s:hidden>
		<tr><td colspan="2" align="center" bgcolor="#d6e4ed"><b>客户信息</b></td></tr>
		<tr><td>委托单位：</td><td>${sampleRegiste.entrustcompanyStr}</td></tr>
		<tr><td>生产单位：</td><td>${sampleRegiste.productionunit}</td></tr>
		<tr><td>送样人：</td><td>${sampleRegiste.senduser}</td></tr>
		<tr><td>联系地址：</td><td>${sampleRegiste.address}</td></tr>
		<tr><td>联系方式：</td><td>${sampleRegiste.relation}</td></tr>
		<tr><td>邮件地址：</td><td>${sampleRegiste.email}</td></tr>
		<tr><td colspan="2" align="center" bgcolor="#d6e4ed"><b>样品信息</b></td></tr>
		<tr><td width="20%">样品编号：</td><td>${sampleRegiste.sampleno}</td></tr>
		<tr><td>样品名称：</td><td>${sampleRegiste.samplename}</td></tr>
		<tr><td>规格型号：</td><td>${sampleRegiste.samplemodel}</td></tr>
		<tr><td>样品数量：</td><td>${sampleRegiste.samplecount}</td></tr>
		<tr><td>存储条件：</td><td><s:if test='sampleRegiste.storecondition.indexOf("1")>=0'>常温</s:if>  <s:if test='sampleRegiste.storecondition.indexOf("2")>=0'>冷藏</s:if> <s:if test='sampleRegiste.storecondition.indexOf("3")>=0'>冷冻</s:if>  <s:if test='sampleRegiste.storecondition.indexOf("4")>=0'>避光</s:if>  <s:if test='sampleRegiste.storecondition.indexOf("5")>=0'>干燥</s:if></td></tr>
		<tr><td>样品状态：</td><td>${sampleRegiste.samplestatus}</td></tr>
		
		<!-- zjx -->
		<tr><td>对接人：</td><td>${acceptpeople3}</td></tr>
		<tr><td>样品来源：</td><td>${sampleRegiste.resource}</td></tr>
		<tr><td>委托分类：</td><td>${categoryString}</td></tr>
		<tr><td>检验标准：</td><td>${checkstandard3}</td></tr>
		
		<tr><td>样品处置：</td><td><s:if test='sampleRegiste.samplehandle==0'>退回</s:if><s:else>处置</s:else></td></tr>
		<tr><td>检验项目：</td><td></td></tr>
		<s:iterator value="pageRegisteCheckItem.list" status="status">    
		         <tr><td></td><td><strong>项目：</strong><s:property value="checkitemname"/><strong> 负责人：</strong><s:property value="examineusername"/></td></tr>
		</s:iterator>
		<tr><td>接收日期：</td><td><s:date name="sampleRegiste.receivedate" format='yyyy-MM-dd'/></td></tr>
	<s:if test='@com.zhirui.business.utils.BusinessUtils@getCurrentUser().authority!="D"'>	
		
	</s:if>
	    <tr><td colspan="2" align="center" bgcolor="#d6e4ed"><b>结算信息</b></td></tr>					
		<tr><td>检测费用：</td><td>${sampleRegiste.checkmoney}</td></tr>
		<tr><td>付款方式：</td><td><s:if test='sampleRegiste.payway==1'>预付款扣除</s:if><s:elseif test='sampleRegiste.payway==2'>直接支付</s:elseif><s:elseif test='sampleRegiste.payway==3'>定期结算</s:elseif><s:else>待定 : ${sampleRegiste.paydes}</s:else></td></tr>
<!--		<tr><td>预付费用：</td><td>${sampleRegiste.prepaymoney}</td></tr>-->
<!--		<tr><td>检验员：</td><td>${sampleRegiste.examineuserStr}</td></tr>-->
		<tr><td>检验依据：</td><td><s:if test='sampleRegiste.accordingtype==1'>指定检验依据</s:if><s:if test='sampleRegiste.accordingtype==2'>由本中心选定合适的标准方法</s:if><s:if test='sampleRegiste.accordingtype==3'>同意用本中心确定的非标准方法</s:if></td></tr>
<!--		<s:if test='sampleRegiste.accordingtype==1'>-->
		      <tr><td>依据内容：</td><td>${sampleRegiste.accordings}</td></tr>
<!--		</s:if>-->
		<tr><td>是否出具检验报告：</td><td><s:if test='sampleRegiste.reporttype==0'>指定检验依据</s:if><s:if test='sampleRegiste.reporttype==1'>出具科研测试报告</s:if><s:if test='sampleRegiste.reporttype==2'>出具检测报告</s:if></td></tr>
		<s:if test='sampleRegiste.reporttype!=0'>
		      <tr><td>资质：</td><td>${sampleRegiste.talentStr}</td></tr>
		</s:if>
		<tr><td>计划检测完成日期：</td><td><s:date name="sampleRegiste.finishdate" format='yyyy-MM-dd'/></td></tr>
				<tr><td>发票类型：</td><td><s:if test='sampleRegiste.taxtype==1'>增值税专用发票</s:if><s:if test='sampleRegiste.taxtype==2'>增值税普通发票</s:if></td></tr>
		<s:if test='sampleRegiste.billischeck=="1"'>
			<tr><td>发票是否开具：</td><td>已开</td></tr>
			<tr><td>发票快递单号：</td><td>${sampleRegiste.billno}</td></tr>
		</s:if>
		<s:else>
		    <tr><td>发票是否开具：</td><td>未开</td></tr>
		</s:else>
<!-- 		<tr><td>报告快递单号：</td><td>${sampleRegiste.reportno}</td></tr> -->
		<tr><td>备注：</td><td>${sampleRegiste.remark}</td></tr>
		<tr><td align="left"><s:a action="modifySampleRegiste" namespace="/business"><s:param name="sampleRegiste.id">${sampleRegiste.id}</s:param>修改</s:a></td><td><input value="返 回" type="button" onclick="history.back();" /></td></tr>
		</table>
		</s:form>
		</div>
		</div>
		</div>
	</body>
</html>
