<%@ page language="java" import="java.util.*" isErrorPage="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
response.setStatus(HttpServletResponse.SC_OK);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">td img {display: block;}</style>
<!--Fireworks 8 Dreamweaver 8 target.  Created Tue Mar 24 00:56:22 GMT+0800 2015-->
</head>
<body style="width:100%;height:100%;background-image: url(img/loginback5.jpg); background-size: 100%; background-repeat: no-repeat">


<s:form action="dologin" namespace="/"  > 
	<div style="margin:15% auto;width:480px !important;height:280px !important;">
		<div style="width:100%;height:100%;background-image: url(img/loginbackground_iwork3.jpg); background-size: 100%; background-repeat:repeat">
			<img src="img/loginback-top4.jpg" style="width:480px;height:85px;"></img>
			<div style="margin-left:55px;">
			
			用户名：<input type="text" name="user.name" id="name" placeholder="请输入用户名" style="line-height:30px !important;width:250px !important;    margin-top: 20px;"></input></br></br>	
			密&nbsp&nbsp&nbsp码：<input name="user.password" id="pass" type="password" placeholder="请输入密码" style="line-height:30px !important;width:250px !important" ></input>
		</br></br>	
		<div style="height:20px;width:20px;display:inline;">
			<input type="image" name="submit" src="img/loginok.jpg" style="margin-left:65px !important "/>
			<!-- <img src="img/loginok.jpg" style="margin-left:70px"></img> -->
			<a href="javascript:void(0)" onclick="clearinfo()"><img src="img/resetnew.jpg" style="margin-left: 43px !important" ></img></a>
		</div>
		</div>
		</div>
	</div>
<s:include value="/include/footer-script.jsp"></s:include>
<script type="text/javascript">
			function clearinfo(){
			    $("#name").val("");
			    $("#pass").val("");
			    $("#code").val("");
			}
</script>
 </s:form>
</body>
</html>