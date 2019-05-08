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
    <th width="60" scope="col">结算金额</th>
    <th width="60" scope="col">结算方式</th>
    <th width="80" scope="col">结算日期</th>
    <th width="60" scope="col">收款人</th>
    <th width="120" scope="col">操作</th>
</tr>
</thead>
<tbody>
<s:iterator value="pageModel.list" status="st">
<tr><td><s:property value="%{(pageNo-1)*10 + #st.index+1}"  /></td>
<td>${entrustcompanyStr}</td><td>${username}</td><td>${paymoney}</td><s:if test='paytype==1'><td>预付款</td></s:if><s:elseif test='paytype==2'><td>现金</td></s:elseif><s:else><td>系统</td></s:else><td><s:date name="paydate" format='yyyy-MM-dd'/></td><td>${receiveuser}</td>
<s:if test='status=="0"'>
    <td><s:a action="modifyIncome" namespace="/business"><s:param name="income.id">${id}</s:param>修改</s:a> | <s:a action="submitIncome" namespace="/business" onclick="return confirm('确定结算这条数据吗？');"><s:param name="income.id">${id}</s:param>结算</s:a> | <s:a action="removeIncome" namespace="/business" onclick="return confirm('确定删除这条数据吗？');"><s:param name="income.id">${id}</s:param>删除</s:a></td></tr>
</s:if>
<s:else>
    <td><s:a action="detailIncome" namespace="/business"><s:param name="income.id">${id}</s:param>详细</s:a></td></tr>
</s:else>
</s:iterator>
</tbody>
</table>
<s:include value="/include/page-bar.jsp"></s:include>	
</s:if>
<s:else>无列表信息</s:else>
