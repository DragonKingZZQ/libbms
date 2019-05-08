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
    <th scope="col">付款单位</th>
    <th width="60" scope="col">联系人</th>
    <th width="60" scope="col">付款金额</th>
    <th width="80" scope="col">付款日期</th>
    <th width="60" scope="col">收款人</th>
    <th width="80" scope="col">操作</th>
</tr>
</thead>
<tbody>
<s:iterator value="pageModel.list" status="st">
<tr><td><s:property value="%{(pageNo-1)*10 + #st.index+1}"  /></td>
<td>${entrustcompanyStr}</td><td>${username}</td><td>${prepaymoney}</td><td><s:date name="prepaydate" format='yyyy-MM-dd'/></td><td>${receiveruser}</td>
<td><s:a action="modifyPrepayment" namespace="/business"><s:param name="prepayment.id">${id}</s:param>修改</s:a> | <s:a action="removePrepayment" namespace="/business" onclick="return confirm('确定删除这条数据吗？');"><s:param name="prepayment.id">${id}</s:param>删除</s:a></td></tr>
</s:iterator>
</tbody>
</table>
<s:include value="/include/page-bar.jsp"></s:include>	
</s:if>
<s:else>无列表信息</s:else>
