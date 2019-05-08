<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
%>
<s:if test="pageModel != null && pageModel.list.size > 0">
共 <s:property value="pageModel.getTotalRecords()"/> 条记录
<table  border="0" cellpadding="0" cellspacing="0" class="lsttable">
<thead>
<tr>
    <th width="30" scope="col">序号</th>
    <th scope="col">检验项目名称</th>
    <th width="80" scope="col">创建日期</th>
    <th width="30" scope="col">状态</th>
    <th width="80" scope="col">操作</th>
</tr>
</thead>
<tbody>
<s:iterator value="pageModel.list" status="st">
<s:if test='validflag==0'>
<tr style="background: #DDD"><td><s:property value="%{(pageNo-1)*10 + #st.index+1}"  /></td>
<td>${itemname}</td><td><s:date name="createdate" format='yyyy-MM-dd'/></td><td>被删除</td>
<td><s:a action="detailCheckItem" namespace="/business/admin"><s:param name="checkItem.id">${id}</s:param>详细</s:a> | <s:a action="recoverCheckItem" namespace="/business/admin" onclick="return confirm('确定恢复这条数据吗？');"><s:param name="checkItem.id">${id}</s:param>恢复</s:a></td></tr>
</s:if>
<s:else>
<tr><td><s:property value="%{(pageNo-1)*10 + #st.index+1}"  /></td>
<td>${itemname}</td><td><s:date name="createdate" format='yyyy-MM-dd'/></td><td>正常</td>
<td><s:a action="modifyCheckItem" namespace="/business/admin"><s:param name="checkItem.id">${id}</s:param>修改</s:a> | <s:a action="removeCheckItem" namespace="/business/admin" onclick="return confirm('确定删除这条数据吗？');"><s:param name="checkItem.id">${id}</s:param>删除</s:a>
</td></tr>
</s:else>
</s:iterator>
</tbody>
</table>
<s:include value="/include/page-bar.jsp"></s:include>	
</s:if>
<s:else>无列表信息</s:else>
