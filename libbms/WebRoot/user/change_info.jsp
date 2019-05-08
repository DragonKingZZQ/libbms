<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<s:include value="/include/header.jsp"></s:include>
		<link href="<%=path %>/css/jquery.datepicker.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
	<div id="pagebody">
		<s:include value="/include/pb-header.jsp"></s:include>
		<div id="mainbody">
			<div id="contentbar">
				<h1>修改用户信息</h1>
				<s:form action="saveinfo" namespace="/">
				<s:hidden name="systype" />
				<table border="0" cellpadding="0" cellspacing="0" class="frmtable">
					<tr>
						<td width="15%">用户登录名：</td>
						<td width="85%">${user.name}</td>
					</tr>
					<tr>
						<td>所属单位：</td>
						<td><s:property value="user.instituteStr" /></td>
					</tr>
					<tr>
						<td>
							<span class="mustfill">*</span>填报人姓名:
						</td>
						<td>
							<s:textfield name="user.showname" data-validation="length" data-validation-length="2-8" data-validation-error-msg="用户显示名在2到8字之间"></s:textfield>
						</td>
					</tr>
					<tr>
						<td>
							<span class="mustfill">*</span>通信邮箱:
						</td>
						<td>
							<s:textfield name="user.email" data-validation="email length" data-validation-length="max20" data-validation-error-msg="通信邮箱输入有误"></s:textfield>
						</td>
					</tr>
					<tr>
						<td>
							<span class="mustfill">*</span>联系电话:
						</td>
						<td>
							<s:textfield name="user.telphone" data-validation="length" data-validation-length="8-15" data-validation-error-msg="联系电话在8到15位之间"></s:textfield>
						</td>
					</tr>
					<tr>
						<td>
							<span class="mustfill">*</span>联系地址:
						</td>
						<td>
							<s:textfield name="user.address" data-validation="length" data-validation-length="5-50" data-validation-error-msg="联系地址在5到50字之间"></s:textfield>
						</td>
					</tr>
					<tr>
						<td height="60" colspan="2" valign="middle"><s:submit value="保 存 "></s:submit>　<input type="button" onclick="window.location.href='<s:url action="changepassword" namespace="/"><s:param name="systype">${systype}</s:param></s:url>'" value="修改密码" /></td>
					</tr>
				</table>
				</s:form>
			</div>
		</div>
	</div>
	<s:include value="/include/footer-info.jsp"></s:include>
	<s:include value="/include/footer-script.jsp"></s:include>
	<script type="text/javascript">
	$.validate();
	</script>
	</body>
</html>
