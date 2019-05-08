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
		
	</head>
	<body>
		<div id="pagebody">
		  <div id="mainbody">
		      <div id="contentbar">
					<h1>检验报告</h1>
					<div class="main-blk">
							<s:form action="/business/checkreport/listCheckReport.html"  namespace="/business">
							<table border="0" cellpadding="0" cellspacing="0" class="lsttable">
					         <tr>
							        <td>样品名称：</td><td><s:textfield name="checkReport.samplename"></s:textfield></td>
							        <td>委托单位：</td><td><s:textfield name="checkReport.entrustcompany"></s:textfield></td>
							        <td>检验项：</td><td><s:textfield name="checkReport.checkitem"></s:textfield></td>
								</tr>
								<tr>
							       <td>接收日期：</td><td>
							       <s:textfield id="receivedate" name="checkReport.receivedate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly" cssClass="shorttext">
		                                 <s:param name="value"><s:date name="checkReport.receivedate" format="yyyy-MM-dd"/></s:param>
		                           </s:textfield></td>
                                   <td>完成日期：</td><td>
                                   <s:textfield id="finishdate" name="checkReport.finishdate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly" cssClass="shorttext">
		                                 <s:param name="value"><s:date name="checkReport.finishdate" format="yyyy-MM-dd"/></s:param>
		                           </s:textfield></td>
                                   <td><input type="image" name="imageField" id="imageField" onclick="clearPageNo()" src="<%=path %>/img/search.jpg" /></td>
								<s:hidden name="pageNo"></s:hidden>
								</tr>
								</table>
							</s:form>
							<div id="opbar">
									<input type="image" name="imageField2" id="imageField2" onclick="window.location.href='<s:url action="addCheckReport"/>'" src="<%=path %>/img/add.png"/>
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
					        <a href="javascript:void(0)" onclick="submit_batch(this)" data-action="<s:url action='batchremoveCheckReport' namespace='/business' />"><input name="" type="image" src="<%=path %>/img/alldelete.png" /></a>
				    </div>
			 </div>
		</div>
	</div>	
	<s:include value="/include/footer-script.jsp"></s:include>
	<script type="text/javascript">
		set_date_picker($('#receivedate'));
		set_date_picker($('#finishdate'));
		</script>
	</body>
</html>
