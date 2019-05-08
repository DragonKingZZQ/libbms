<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path %>/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery.addition.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery.form-validator.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/common.js"></script>
<% cn.kepu.base.form.Message msg = cn.kepu.utils.ContextUtils.getOpMessage(); 
   if (msg != null) {
%>
<div style="display:none"><input type="hidden" id="msg_showen" value="0" /></div>
<script type="text/javascript">
	$(document).ready(function() {
		showMessage('<%=msg.getType()%>', '<%=msg.getMessage()%>', '<%=msg.getTitle()==null?"":msg.getTitle()%>');
	});
</script>
<% } %>
