<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<s:include value="/include/pb-header.jsp"></s:include>
		<link href="<%=path %>/css/jquery.datepicker.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
	<div id="pagebody">
		<s:if test='systype=="kepu"'>
		<s:include value="/include/kepu-top-body.jsp"></s:include>
		</s:if><s:elseif test='systype=="party"'>
		<s:include value="/include/pb-header.jsp"></s:include>
		</s:elseif><s:elseif test='systype=="news1"'>
		<s:include value="/include/news-top-body.jsp"></s:include>
		</s:elseif><s:elseif test='systype=="news2"'>
		<s:include value="/include/news2-top-body.jsp"></s:include>
		</s:elseif><s:elseif test='systype=="periodical"'>
		<s:include value="/include/kepu-top-body.jsp"></s:include>
		</s:elseif>
		<div id="mainbody">
			<div id="contentbar">
				<h1>修改登录密码</h1>
				<s:form action="savepassword" namespace="/">
				<s:hidden name="systype" />
				<table>
					<tr>
						<td>
							<span class="mustfill">*</span>原登录密码：
						</td>
						<td>
							<s:password name="oldpassword" data-validation="length" data-validation-length="min6" data-validation-error-msg="原密码输入不正确"></s:password>
						</td>
					</tr>
					<tr>
						<td>
							<span class="mustfill">*</span>新密码:
						</td>
						<td>
							<s:password name="password_confirmation" id="inputPassword1" data-validation="length" data-validation-length="6-14" data-validation-error-msg="密码在6到14位之间"></s:password>
						</td>
					</tr>
					<tr>
						<td>
							<span class="mustfill">*</span>重复新密码:
						</td>
						<td>
							<s:password name="password" id="inputPassword2" data-validation="confirmation" data-validation-error-msg="密码输入不一致" ></s:password>
						</td>
					</tr>
					<tr>
						<td>
							<span class="mustfill">*</span>验证码:
						</td>
						<td>
							<s:textfield name="checkcode" /><img class="verify-code" src="<%=path %>/verifycode.jpg"/>
						</td>
					</tr>
					<tr>
						<td height="60" colspan="2" align="center" valign="middle"><s:submit value="保 存 "></s:submit></td>
					</tr>
				</table>
				</s:form>
			</div>
		</div>
	</div>
	<s:include value="/include/footer-info.jsp"></s:include>
	<s:include value="/include/footer-script.jsp"></s:include>
	<script type="text/javascript">
	$.validate({modules : 'security'});
	</script>
	</body>
</html>
