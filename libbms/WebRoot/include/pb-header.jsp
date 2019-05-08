<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
%>
<title>iWork</title>
<link href="<%=path %>/css/layout.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/css/status.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/css/styles.css" rel="stylesheet" type="text/css" />
  <div id="topbody" class="partytop">
    <div id="navbar">欢迎您！<s:property value="@com.zhirui.business.utils.BusinessUtils@getCurrentUser().getShowname()"/>    &nbsp;&nbsp;&nbsp;<a href="<%=path%>/logout.html"> | 退  出&nbsp;&nbsp;&nbsp;</a><a href="<%=path%>/user/changeinfo.html">修改密码&nbsp;&nbsp;&nbsp;</a></div>
  </div>