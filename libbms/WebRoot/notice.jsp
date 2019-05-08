<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<link href="<%=path %>/css/jquery.datepicker.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
	<s:include value="/include/common.jsp"></s:include>
		      <div id="contentbar">
		           <s:if test='user.getAuthority()!="C"'>
		            <h4>以下检验任务已经超期没有完成</h4>
					<div>
						<div class="cls"></div>
					    <s:if test="sendTask != null && sendTask.list.size>0">
							<table  border="0" cellpadding="0" cellspacing="0" class="lsttable">
							<thead>
							<tr>
							    <th scope="col">序号</th>
							    <th scope="col">样品编号</th>
							    <th scope="col">样品名称</th>
<!-- 							    <th scope="col">接收日期</th> -->
							    <th scope="col">委托单位</th>
							    <th scope="col">联系人</th>
							    <th scope="col">联系方式</th>
<!-- 							    <th scope="col">状态</th> -->
							    <th scope="col">操作</th>
							</tr>
							</thead>
							<tbody>
							<s:iterator value="sendTask.list" status="st">
							    <tr style="background:#0FF"><td>${st.index+1}</td><td>${sampleno}</td><td>${samplename}</td><td>${entrustcompanyStr}</td>
								<td>${senduser}</td><td>${relation}</td><td><s:a action="detailSampleRegiste" namespace="/business"><s:param name="sampleRegiste.id">${id}</s:param>详细</s:a>
								</td></tr>
							</s:iterator>
							</tbody>
							</table>
							<s:include value="/include/page-bar.jsp"></s:include>	
						</s:if>
						<s:else>无超期未完成检验任务</s:else>
					</div>
					
<!-- 					<h4>有新的样品登记信息，请注意跟踪收款！</h4> -->
<!-- 					<div> -->
<!-- 						<div class="cls"></div> -->
<!-- 					    <s:if test="nopayTask != null && nopayTask.list.size>0"> -->
<!-- 							<table  border="0" cellpadding="0" cellspacing="0" class="lsttable"> -->
<!-- 							<thead> -->
<!-- 							<tr> -->
<!-- 							    <th scope="col">序号</th> -->
<!-- 							    <th scope="col">样品名称</th> -->
<!-- 							    <th scope="col">接收日期</th> -->
<!-- 							    <th scope="col">委托单位</th> -->
<!-- 							    <th scope="col">检测费用</th> -->
<!-- 							    <th scope="col">操作</th> -->
<!-- 							</tr> -->
<!-- 							</thead> -->
<!-- 							<tbody> -->
<!-- 							<s:iterator value="sendTask.list" status="st"> -->
<!-- 							    <tr style="background:#0FF"><td>${st.index+1}</td><td>${samplename}</td><td><s:date name="receivedate" format='yyyy-MM-dd'/></td><td>${entrustcompanyStr}</td><td>${checkmoney}</td> -->
<!-- 								<td><s:a action="detailSampleRegiste" namespace="/business"><s:param name="sampleRegiste.id">${id}</s:param>详细</s:a> -->
<!-- 								</td></tr> -->
<!-- 							</s:iterator> -->
<!-- 							</tbody> -->
<!-- 							</table> -->
<!-- 							<s:include value="/include/page-bar.jsp"></s:include>	 -->
<!-- 						</s:if> -->
<!-- 						<s:else>无超期未完成检验任务</s:else> -->
<!-- 					  </div> -->
					</s:if>
					<s:if test='user.getAuthority()=="C"'>
					<h4>以下检验请尽快完成！！！</h4>
					<div>
						<div class="cls"></div>
					    <s:if test="warnInfo != null && warnInfo.list.size>0">
							<table  border="0" cellpadding="0" cellspacing="0" class="lsttable">
							<thead>
							<tr>
							    <th scope="col">序号</th>
							    <th scope="col">样品编号</th>
							    <th scope="col">样品名称</th>
							    <th scope="col">接收日期</th>
							    <th scope="col">检验状态</th>
							    <th scope="col">状态</th>
							    <th scope="col">操作</th>
							</tr>
							</thead>
							<tbody>
							<s:iterator value="warnInfo.list" status="st">
							<tr style="background:#F30"><td>${st.index+1}</td><td>${sendsampleno}</td><td>${sendsamplename}</td><td><s:date name="receivedate" format='yyyy-MM-dd'/></td><s:if test='finishflag=="1"'><td>检验完成</td></s:if><s:else><td>未完成</td></s:else><td><span class="status${status}">${statusname}</span></td>
							<td><s:a action="detailSendSample" namespace="/business"><s:param name="sendSample.id">${id}</s:param>详细</s:a></td></tr>
							</s:iterator>
							</tbody>
							</table>
							<s:include value="/include/page-bar.jsp"></s:include>	
						</s:if>
						<s:else>无待完成检验</s:else>
					</div>
                    <h4>待收样的派样单</h4>
					<div>
						<div class="cls"></div>
					    <s:if test="waitTask != null && waitTask.list.size>0">
							<table  border="0" cellpadding="0" cellspacing="0" class="lsttable">
							<thead>
							<tr>
							    <th scope="col">序号</th>
							    <th scope="col">样品编号</th>
							    <th scope="col">样品名称</th>
							    <th scope="col">接收日期</th>
							    <th scope="col">检验状态</th>
							    <th scope="col">状态</th>
							    <th scope="col">操作</th>
							</tr>
							</thead>
							<tbody>
							<s:iterator value="waitTask.list" status="st">
							<tr><td>${st.index+1}</td><td>${sendsampleno}</td><td>${sendsamplename}</td><td><s:date name="receivedate" format='yyyy-MM-dd'/></td><s:if test='finishflag=="1"'><td>检验完成</td></s:if><s:else><td>未完成</td></s:else><td><span class="status${status}">${statusname}</span></td>
							<td><s:a action="receiveSampleSendSample" namespace="/business"><s:param name="sendSample.id">${id}</s:param>收样</s:a></td></tr>
							</s:iterator>
							</tbody>
							</table>
							<s:include value="/include/page-bar.jsp"></s:include>	
						</s:if>
						<s:else>无待确认取样信息</s:else>
					</div>
                   </s:if>
		</div>

	</body>
</html>