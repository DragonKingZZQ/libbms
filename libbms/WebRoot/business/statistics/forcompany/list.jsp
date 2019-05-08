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
					<h1>委托单位送样统计</h1>
					<div class="main-blk">
							<s:form id="searchform" action="/business/statistics/forcompany/listcompanyStatistics.html"  namespace="/business">
							<table border="0" cellpadding="0" cellspacing="0" class="lsttable">
					         <tr>
							        <td>委托单位：</td><td><s:textfield name="statistics.entrustcompany"></s:textfield></td>
							        <td>检验项目：</td><td><s:textfield name="statistics.checkitem"></s:textfield></td>
							        <td>资质：</td><td><s:select name="statistics.talent" headerKey="" headerValue="" list="qualificationmap" ></s:select></td>
								</tr>
								<tr>
							       <td>接收日期起：</td><td>
							       <s:textfield id="receivedatestart" name="statistics.receivedatestart" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly" cssClass="shorttext">
		                                 <s:param name="value"><s:date name="statistics.receivedatestart" format="yyyy-MM-dd"/></s:param>
		                           </s:textfield></td>
                                   <td>接收日期止：</td><td>
                                   <s:textfield id="receivedateend" name="statistics.receivedateend" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly" cssClass="shorttext">
		                                 <s:param name="value"><s:date name="statistics.receivedateend" format="yyyy-MM-dd"/></s:param>
		                           </s:textfield></td>
                                   <td><input type="image" name="imageField" id="imageField" onclick="clearPageNo()" src="<%=path %>/img/search.jpg" /></td>
                                   <td><a href="javascript:void(0)" onclick="exportexcel(this)" data-action="<s:url action='exportCompanyStatistics' namespace='/business' />">
					                <img style="border:0px" src="<%=path %>/img/export.jpg" /></a></td>
								<s:hidden name="pageNo"></s:hidden>
								</tr>
								</table>
							</s:form>
					</div>
					<form action="" id="batch_form" method="post">
					<div>
						<div class="cls"></div>
						<s:include value="list-page.jsp"></s:include>
					</div>
					</form>
			 </div>
		</div>
	</div>	
	<script type="text/javascript">
		set_date_picker($('#receivedatestart'));
		set_date_picker($('#receivedateend'));
		
		function exportexcel(btnObj) {
			$("#searchform").attr("action",$(btnObj).data('action'));
			$("#searchform").submit();
			$("#searchform").attr("action","");
		}
		</script>
	</body>
</html>
