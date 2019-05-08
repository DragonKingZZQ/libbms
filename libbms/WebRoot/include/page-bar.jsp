<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.kepu.utils.PageModel" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
%>
<% PageModel pageModel = (PageModel)request.getAttribute("pageModel");
	if(pageModel != null && pageModel.getTotalPages() > 1) {
 %>
<div style="display:inline-block;">
<div class="float_left margin_right5" style="width: 66px; height: 28px; background-image: url(<%=path %>/img/nr_fs_right_01.jpg); line-height: 28px; color: #cccccc">
	<% if(pageModel.getPageNo() > 1) { %>
	<img src="<%=path %>/img/nr_fs_right_03_l.jpg" width="5" height="9" style="margin-right: 5px;">
	<a href="javascript:ajax_gotopage('<%=(pageModel.getPageNo()-1) %>', '.main-blk form')" class="a_666_red">上一页</a><% } else {%>
	<img src="<%=path %>/img/nr_fs_right_02_l.jpg" width="5" height="9" style="margin-right: 5px;">
	上一页<% }%>
</div>
<% int pageStart = pageModel.getPageNo()>2?pageModel.getPageNo()-2:1; 
   int pageEnd = pageModel.getBottomPageNo()-pageModel.getPageNo()>2?pageModel.getPageNo()+2:pageModel.getBottomPageNo();%>
<% if(pageStart!=1) {%>
<div class="float_left font_bold margin_right5" style="width: 30px; height: 28px; background-image: url(<%=path %>/img/nr_fs_right_04.jpg); line-height: 28px;">
	<a href="javascript:ajax_gotopage('1', '.main-blk form')" class="a_666_red">1</a>
</div>
<% if(pageStart > 2) {%>
<div class="float_left font_bold margin_right5" style="width: 30px; height: 28px; line-height: 28px;">
	…
</div>
<%} } %>
<% for (int count = pageStart; count <= pageEnd; count++) { %>
   <%if(count==pageModel.getPageNo()) {%>
<div class="float_left font_bold margin_right5" style="width: 28px; height: 28px; line-height: 28px; color: #ff6600">
	<%=count %>
</div>
   <%} else {%>
<div class="float_left font_bold margin_right5" style="width: 30px; height: 28px; background-image: url(<%=path %>/img/nr_fs_right_04.jpg); line-height: 28px;">
	<a href="javascript:ajax_gotopage('<%=count %>', '.main-blk form')" class="a_666_red"><%=count %></a>
</div>
   <%} %>
<%} %>
<% if(pageEnd!=pageModel.getBottomPageNo()) {
     if(pageEnd < pageModel.getBottomPageNo()-1) {%>
<div class="float_left font_bold margin_right5" style="width: 30px; height: 28px; line-height: 28px;">
	…
</div>
   <%} %>
<div class="float_left font_bold margin_right5" style="width: 30px; height: 28px; background-image: url(<%=path %>/img/nr_fs_right_04.jpg); line-height: 28px;">
	<a href="javascript:ajax_gotopage('<%=pageModel.getBottomPageNo() %>', '.main-blk form')" class="a_666_red"><%=pageModel.getBottomPageNo() %></a>
</div>
<%} %>
<% if(pageModel.getPageNo() < pageModel.getBottomPageNo()) { %>
<div class="float_left" style="width: 66px; height: 28px; background-image: url(<%=path %>/img/nr_fs_right_01.jpg); line-height: 28px;">
	<a href="javascript:ajax_gotopage('<%=(pageModel.getPageNo()+1) %>', '.main-blk form')" class="a_666_red">下一页</a>
	<img src="<%=path %>/img/nr_fs_right_03_r.jpg" width="5" height="9" style="margin-left: 5px;">
</div><% } else {%>
<div class="float_left" style="width: 66px; height: 28px; background-image: url(<%=path %>/img/nr_fs_right_01.jpg); line-height: 28px;">
	下一页
	<img src="<%=path %>/img/nr_fs_right_02_r.jpg" width="5" height="9" style="margin-left: 5px;">
</div><% } %>
</div>
<% } %>
