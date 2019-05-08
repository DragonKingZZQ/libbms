<%@ page language="java" import="java.util.*" isErrorPage="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
%>
<div id="leftbar" class="toggle" >
	<ul id="leftmenu">
       <s:iterator value="topmenu" status="st" id="t">
            <s:if test='code.indexOf("-")<0'>
                 <li><a href='<%=path%>${url}' target="myframe"><img src="<%=path %>${icon}" width="20px" height="20px" border="0" alt="${menuname}" /> ${menuname}</a></li>
            </s:if>
            <s:else>
                  <li><a href="javascript:void(0)" onclick="DoMenu('ChildMenu<s:property value="#st.index"/>')"><img src="<%=path %>${icon}" width="20px" height="20px" border="0" alt="${menuname}" /> ${menuname}</a>
			             <ul id='ChildMenu<s:property value="#st.index"/>' class="expanded">
			                 <s:iterator value="submenu" status="sst" id="s">
			                          <s:if test='#t.code.substring(0,#t.code.indexOf("-"))==#s.code.substring(0,#s.code.indexOf("-"))'>
							                 <li style="text-align:left;"><a href='<%=path%>${s.url}' target="myframe">★  ${s.menuname}</a></li>
							          </s:if>
                             </s:iterator>
						 </ul> 
				   </li>
            </s:else>
       </s:iterator>
	</ul>
</div>
