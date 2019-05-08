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
    <th width="60" scope="col">合同金额</th>
    <th width="60" scope="col">实际收入</th>
    <th width="60" scope="col">逾期未付</th>
</tr>
</thead>
<tbody>
<s:iterator value="pageModel.list" status="st">
<tr><td><s:property value="%{(pageNo-1)*10 + #st.index+1}"  /></td>
<td>${checkmoney}</td><td>${premoney}</td><td>${balmoney}</td>
</tr>
</s:iterator>
</tbody>
</table>
<s:include value="/include/page-bar.jsp"></s:include>	
</s:if>
<s:else>无数据</s:else>
