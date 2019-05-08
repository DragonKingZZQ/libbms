<%@ page language="java" import="java.util.*" isErrorPage="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
%>
<div id="leftbar" class="toggle">
	<ul id="leftmenu">
       <li><a href="<s:url action="business/registe/listSampleRegiste"/>" target="myframe"><img src="<%=path %>/img/Save.png" width="20px" height="20px" border=0 alt="样品登记" /> 样品登记</a></li>
	   <li><a href="<s:url action="business/send/listSendSample"/>" target="myframe"><img src="<%=path %>/img/Send.png" width="20px" height="20px" border=0 alt="派样单" /> 派样单&nbsp;&nbsp;</a></li>
	   <li><a href="<s:url action="business/checkreport/listCheckReport"/>" target="myframe"><img src="<%=path %>/img/Scenario.png" width="20px" height="20px" border=0 alt="报告编制" /> 报告编制</a></li>
	   <li><a href="javascript:void(0)" onclick="DoMenu('ChildMenu3')"><img src="<%=path %>/img/Time.png" width="20px" height="20px" border=0 alt="统计分析" /> 统计分析</a>
	         <ul id="ChildMenu3" class="expanded">
				 <li><a href="<s:url action="business/statistics/forregiste/listregisteStatistics"/>" target="myframe">登记情况统计</a></li>
				 <li><a href="<s:url action="business/statistics/forcompany/listcompanyStatistics"/>" target="myframe">委托单位统计</a></li>
				 <li><a href="<s:url action="business/statistics/foremployee/listemployeeStatistics"/>" target="myframe">员工工作量统计</a></li>
			 </ul> 
	   </li>
       <li><a href="javascript:void(0)" onclick="DoMenu('ChildMenu4')" ><img src="<%=path %>/img/Touch.png" width="20px" height="20px" border=0 alt="系统管理" /> 系统管理</a> 
	        <ul id="ChildMenu4" class="expanded">
				 <li><a href="<s:url action="business/admin/checkitem/listCheckItem"/>" target="myframe">检验项目管理</a></li>
				 <li><a href="<s:url action="business/admin/instrument/listInstrument"/>" target="myframe">仪器管理</a></li>
				 <li><a href="<s:url action="business/admin/user/listUser"/>" target="myframe">员工管理</a></li>
			 </ul>
       </li>
	</ul>
</div>
