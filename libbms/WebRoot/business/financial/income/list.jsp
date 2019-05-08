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
					<h1>结算列表</h1>
					<div class="main-blk">
							<s:form action="/business/financial/income/listIncome.html" id="searchform" namespace="/business/admin">
							<table border="0" cellpadding="0" cellspacing="0" class="lsttable">
							<tr>
                                   <td>单位名称：</td><td><s:textfield name="income.entrustcompanyStr"></s:textfield></td>
                                   <td>联系人：</td><td><s:textfield name="income.username"></s:textfield></td>
                            </tr>
                            <tr>       
                                   <td>付款日期：</td><td>
                                   <s:textfield id="paydate" name="income.paydate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly" cssClass="shorttext">
		                                 <s:param name="value"><s:date name="income.paydate" format="yyyy-MM-dd"/></s:param>
		                           </s:textfield></td>
                                   <td><input type="image" name="imageField" id="imageField" onclick="clearPageNo()" src="<%=path %>/img/search.jpg" /></td>
                                   <td><a href="javascript:void(0)" onclick="exportexcel(this)" data-action="<s:url action='excelExportIncome' namespace='/business' />">
					                <img style="border:0px" src="<%=path %>/img/export.jpg" /></a></td>
								<s:hidden name="pageNo"></s:hidden>
								</tr>
								</table>
							</s:form>
							<div id="opbar">
									<input type="image" name="imageField2" id="imageField2" onclick="window.location.href='<s:url action="addIncome"/>'" src="<%=path %>/img/add.png"/>
							</div>
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
	<s:include value="/include/footer-script.jsp"></s:include>
	<script type="text/javascript">
		set_date_picker($('#paydate'));
		function exportexcel(btnObj) {
			$("#searchform").attr("action",$(btnObj).data('action'));
			$("#searchform").submit();
			$("#searchform").attr("action","");
		}
	</script>
	</body>
</html>
