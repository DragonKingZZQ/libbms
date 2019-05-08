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
    <th scope="col">委托单位</th>
    <th width="30" scope="col">送样次数</th>
    <th width="60" scope="col">合同金额</th>
    <th width="60" scope="col">已付金额</th>
    <th width="60" scope="col">未付金额</th>
    <th width="30" scope="col">完成检验数量</th>
    <th width="30" scope="col">已开发票数量</th>
    <th width="30" scope="col">已发快递数量</th>
</tr>
</thead>
<tbody>
<s:iterator value="pageModel.list" status="st">
<tr><td><s:property value="%{(pageNo-1)*10 + #st.index+1}"  /></td>
<td>${entrustcompany}</td><td>${samples}</td><td>${checkmoney}</td><td>${premoney}</td>
<td>${balmoney}</td><td>${finished}</td><td>${bills}</td><td>${posts}</td>
</tr>
</s:iterator>
</tbody>
</table>
<s:include value="/include/page-bar.jsp"></s:include>	
</s:if>
<s:else>无数据</s:else>
