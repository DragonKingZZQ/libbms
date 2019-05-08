<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<link href="<%=path %>/css/jquery.datepicker.css" rel="stylesheet" type="text/css" />
		<link href="<%=path %>/css/layout2.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path %>/js/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="<%=path %>/js/jquery.addition.min.js"></script>
		<script type="text/javascript" src="<%=path %>/js/jquery.form-validator.min.js"></script>
		<script type="text/javascript" src="<%=path %>/js/common.js"></script>
		<style type="text/css"> <!-- #div1{  display:none;  background-color :#ABC8A6; color:#333333; font-size:12px; line-height:18px; border:1px solid #e1e3e2; width:250; height:50; padding: 5px; } -->  </style> 
	</head>
	<body>
		
		<div id="pagebody">
		
		  <div id="mainbody">
		      <div id="contentbar">
					<h1>样品登记</h1>
					<div class="main-blk">
							<s:form action="/business/registe/listSampleRegiste.html" id="searchform" namespace="/business">
							<table border="0" cellpadding="0" cellspacing="0" class="lsttable">
					         <tr>
							        <td>样品编号：</td><td><s:textfield name="sampleRegiste.sampleno"></s:textfield></td>
							        <td>样品名称：</td><td><s:textfield name="sampleRegiste.samplename"></s:textfield></td>
							        <td>送样人：</td><td><s:textfield  name="sampleRegiste.senduser"></s:textfield></td>
								</tr>
								<tr>
							       <td>接收日期：</td><td>
							       <s:textfield id="receivedate" name="sampleRegiste.receivedate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly" cssClass="shorttext">
		                                 <s:param name="value"><s:date name="sampleRegiste.receivedate" format="yyyy-MM-dd"/></s:param>
		                           </s:textfield></td>
                                   <td>检验项目：</td><td><s:textfield name="sampleRegiste.checkitemname"></s:textfield></td>
								   <td>生产者：</td><td><s:textfield name="sampleRegiste.productionunit"></s:textfield></td>
								</tr>
								<tr>
                                   <td>委托单位：</td><td><s:textfield name="sampleRegiste.entrustcompany" cssClass="longtext"></s:textfield></td>
                                   <td>排序：</td><td><s:select  name="sampleRegiste.fieldorder" list="#{'id':'录入时间','sampleno':'编号','samplename':'名称','receivedate':'接收日期'}" cssClass="longtext"></s:select>（倒序）</td>
                                   <td>付款情况</td>
                                   <td><s:radio list="#{'0':'所有'}" name="sampleRegiste.payway"  value="0" checked="checked"/><br/>
                                   <s:radio list="#{'1':'未付清'}" name="sampleRegiste.payway"/><s:radio list="#{'2':'已付清'}" name="sampleRegiste.payway"/>
                                   </td>
								</tr>
								<tr>
                                   <td></td><td></td>
                                   <td></td><td></td>
                                   <td><input type="image" name="imageField" id="imageField" onclick="clearPageNo()" src="<%=path %>/img/search.jpg" /></td>
                                   <td><a href="javascript:void(0)" onclick="exportexcel(this)" data-action="<s:url action='excelExportSampleRegiste' namespace='/business' />">
					                <img style="border:0px" src="<%=path %>/img/export.jpg" /></a></td>
								   <s:hidden name="pageNo"></s:hidden>
								   <s:hidden name="pagerecorders" id="querysize"></s:hidden>
								</tr>
								</table>
							</s:form>
							<div id="opbar">
									<input type="image" name="imageField2" id="imageField2" onclick="window.location.href='<s:url action="addSampleRegiste"/>'" src="<%=path %>/img/add.png"/>
							</div>
					</div>
					<form action="" id="batch_form" method="post">
					<div>
						<div class="cls"></div>
						<s:include value="list-page.jsp"></s:include>
					</div>
					</form>
					<div id="opbar2">
					        <input  name="" type="image" onclick="javascript:checkAll()" src="<%=path %>/img/all.png" />
					        &nbsp;
					        <a href="javascript:void(0)" onclick="submit_batch(this)" data-action="<s:url action='batchremoveSampleRegiste' namespace='/business' />"><input name="" type="image" src="<%=path %>/img/alldelete.png" /></a>
					        &nbsp;
					        <a href="javascript:void(0)" onclick="submit_batch(this)" data-action="<s:url action='batchsubmitSampleRegiste' namespace='/business' />"><input name="" type="image" src="<%=path %>/img/allsubmit.png" /></a>
				    </div>
			 </div>
		</div>
	</div>	
	<s:include value="/include/footer-script.jsp"></s:include>
	<script type="text/javascript">
		set_date_picker($('#receivedate'));
		parent.iFrameHeight();
		
		 function setPageSize(){
		    $("#querysize").val($("#newsize").val());
		    $("#newsize").val($("#querysize").val());
		    parent.iFrameHeight();
		} 
		
		function exportexcel(btnObj) {
			$("#searchform").attr("action",$(btnObj).data('action'));
			$("#searchform").submit();
			$("#searchform").attr("action","");
		}
	</script>
	</body>
</html>
