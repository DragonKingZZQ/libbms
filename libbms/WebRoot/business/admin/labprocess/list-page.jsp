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
    <th scope="col">实验过程标题</th>
    <th scope="col">实验过程内容</th>
    <th width="80" scope="col">创建日期</th>
    <th width="30" scope="col">状态</th>
    <th width="80" scope="col">操作</th>
</tr>
</thead>
<tbody>
<s:iterator value="pageModel.list" status="st">
<s:if test='validflag==0'>
	<tr style="background: #DDD">
	<td><s:property value="%{(pageNo-1)*10 + #st.index+1}"  /></td>
	<td>${labprocesstitle}</td>
	<td><div class="wrap">${labprocesscontent}</div></td>
	<td><s:date name="createdate" format='yyyy-MM-dd'/></td>
	<td>被删除</td>
	<td><s:a action="detailLabProcess" namespace="/business/admin"><s:param name="labProcess.id">${id}</s:param>详细</s:a> | <s:a action="recoverLabProcess" namespace="/business/admin" onclick="return confirm('确定恢复这条数据吗？');"><s:param name="labProcess.id">${id}</s:param>恢复</s:a></td></tr>
</s:if>
<s:else>
	<tr>
	<td><s:property value="%{(pageNo-1)*10 + #st.index+1}"  /></td>
	<td>${labprocesstitle}</td>
	<td><div class="wrap">${labprocesscontent}</div></td>
	<td><s:date name="createdate" format='yyyy-MM-dd'/></td>
	<td>正常</td>
	<td><s:a action="modifyLabProcess" namespace="/business/admin"><s:param name="labProcess.id">${id}</s:param>修改</s:a> | <s:a action="removeLabProcess" namespace="/business/admin" onclick="return confirm('确定删除这条数据吗？');"><s:param name="labProcess.id">${id}</s:param>删除</s:a>
	</td></tr>
</s:else>
</s:iterator>
</tbody>
</table>
<s:include value="/include/page-bar.jsp"></s:include>	
</s:if>
<s:else>无列表信息</s:else>
<style>
.wrap{
	   overflow: hidden;-o-text-overflow: ellipsis;text-overflow: ellipsis;width:240px; white-space:nowrap;display:block;
}
</style>
