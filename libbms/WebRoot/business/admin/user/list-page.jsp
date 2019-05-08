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
    <th width="60" scope="col">员工名称</th>
    <th scope="col">所属科室</th>
    <th width="60" scope="col">职务</th>
    <th scope="col">系统用户名称</th>
    <th width="30" scope="col">状态</th>
    <th width="80" scope="col">操作</th>
</tr>
</thead>
<tbody>
<s:iterator value="pageModel.list" status="st">
<s:if test='validflag==0'>
<tr style="background: #DDD"><td><s:property value="%{(pageNo-1)*10 + #st.index+1}"  /></td>
<td>${showname}</td><td>${officeStr}</td><td>${duty}</td><td>${name}</td><td>被删除</td>
<td><s:a action="detailUser" namespace="/business/admin"><s:param name="user.uid">${uid}</s:param>详细</s:a> | <s:a action="recoverUser" namespace="/business/admin" onclick="return confirm('确定恢复这条数据吗？');"><s:param name="user.uid">${uid}</s:param>恢复</s:a></td></tr>
</s:if>
<s:else>
<tr><td><s:property value="%{(pageNo-1)*10 + #st.index+1}"  /></td>
<td>${showname}</td><td>${officeStr}</td><td>${duty}</td><td>${name}</td><td>正常</td>
<td><s:a action="modifyUser" namespace="/business/admin"><s:param name="user.uid">${uid}</s:param>修改</s:a> | <s:a action="removeUser" namespace="/business/admin" onclick="return confirm('确定删除这条数据吗？');"><s:param name="user.uid">${uid}</s:param>删除</s:a>
</td></tr>
</s:else>
</s:iterator>
</tbody>
</table>
<!--<span class="textdesc">(<span style="color:red">说明：</span>用户类型 <span style="color:green">A</span>为超级管理员 .<span style="color:green">B</span>为组长.<span style="color:green">C</span>为组员)</span><br>-->
<s:include value="/include/page-bar.jsp"></s:include>	
</s:if>
<s:else>无列表信息</s:else>
