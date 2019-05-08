<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
%>
<s:if test="psr != null && psr.size > 0">
<table  border="0" cellpadding="0" cellspacing="0" class="lsttable">
<thead>
<tr>
    <th width="30" scope="col">序号</th>
    <th scope="col">委托单位</th>
    <th width="100" scope="col">预付款总额</th>
     <th width="100" scope="col">预付款余额</th>
    <th width="60" scope="col">合同金额</th>
    <th width="60" scope="col">实际收入</th>
    <th width="60" scope="col">未付款</th>
</tr>
</thead>
<tbody>
<s:iterator value="psr" status="st">
<tr><td><s:property value="%{(pageNo-1)*10 + #st.index+1}"  /></td>
<td>${entrustcompanyStr}</td><td>${payedmoney}</td><td>${notusedmoney}</td><td>${checkmoney}</td><td>${prepaymoney}</td><td>${balancemoney}</td>
</tr>
</s:iterator>
</tbody>
</table>
<s:include value="/include/page-bar.jsp"></s:include>	
</s:if>
<s:else>无数据</s:else>
