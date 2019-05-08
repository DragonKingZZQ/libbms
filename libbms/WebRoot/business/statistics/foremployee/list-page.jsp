<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
%>
<s:if test="pageModel != null && pageModel.list.size > 0">
<table  border="0" cellpadding="0" cellspacing="0" class="lsttable">
<thead>
<tr>
    <th width="30" scope="col">序号</th>
    <th width="60" scope="col">员工名称</th>
    <th width="30" scope="col">派样数量</th>
    <th width="30" scope="col">收样数量</th>
    <th width="30" scope="col">总完成数量</th>
    <th width="30" scope="col">未完成数量</th>
    <th width="30" scope="col">按时完成数量</th>
    <th width="30" scope="col">超期完成数量</th>
</tr>
</thead>
<tbody>
<s:iterator value="pageModel.list" status="st">
<tr><td><s:property value="%{(pageNo-1)*10 + #st.index+1}"  /></td>
<td>${examineuser}</td><td>${sends}</td><td>${receives}</td><td>${finished}</td>
<td>${nofinished}</td><td>${finishintime}</td><td>${finishovertime}</td>
</tr>
</s:iterator>
</tbody>
</table>
<s:include value="/include/page-bar.jsp"></s:include>	
</s:if>
<s:else>无数据</s:else>
