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
					<h1>实验过程列表</h1>
					<div class="main-blk">
							<s:form action="/business/admin/labprocess/listLabProcess.html"  namespace="/business/admin">
							<table border="0" cellpadding="0" cellspacing="0" class="lsttable">
							<tr>
                                   <td>实验过程标题：</td><td><s:textfield name="labProcess.labprocesstitle"></s:textfield></td>
                                   <td><input type="image" name="imageField" id="imageField" onclick="clearPageNo()" src="<%=path %>/img/search.jpg" /></td>
								<s:hidden name="pageNo"></s:hidden>
								</tr>
								</table>
							</s:form>
							<div id="opbar">
									<input type="image" name="imageField2" id="imageField2" onclick="window.location.href='<s:url action="addLabProcess"/>'" src="<%=path %>/img/add.png"/>
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
		set_date_picker($('#receivedate'));
		</script>
	</body>
</html>
