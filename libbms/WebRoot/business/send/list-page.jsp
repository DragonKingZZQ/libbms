<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	response.setStatus(HttpServletResponse.SC_OK);
%>
<head>
	<link href="<%=path %>/css/layui.css" rel="stylesheet" type="text/css" />
		<script src="<%=path %>/js/layui.js" charset="utf-8"></script>
</head>
		
<s:if test="pageModel != null && pageModel.list.size > 0">
共 <s:property value="pageModel.getTotalRecords()"/> 条记录  每页显示<s:textfield name="pagerecorders" id="newsize"  maxlength="3" data-validation="number" data-validation-error-msg="只能输入大于等于 1 的数字" style="width:30px"></s:textfield>条  
<input type="submit" name="d" onclick="return setPageSize()" value="确定" />
<table  border="0" cellpadding="0" cellspacing="0" class="lsttable">
<thead>
<tr>
    <th width="10" scope="col"></th>
    <th width="130" scope="col">样品编号</th>
    <th scope="col">样品名称</th>
    <th width="80" scope="col">接收日期</th>
    <th width="60" scope="col">检验员</th>
    <th width="60" scope="col">检验状态</th>
    <th width="80" scope="col">操作</th>
</tr>
</thead>
<tbody>
<s:iterator value="pageModel.list" status="st">
   <tr><td>
   <s:if test='status=="C"'><input type="checkbox" name="ids" value="${id}" class="data_id"/></s:if>
   <s:else><input type="checkbox" name="r" disabled="disabled"/></s:else>
   </td><td>${sendsampleno}</td><td>${sendsamplename}</td><td><s:date name="receivedate" format='yyyy-MM-dd'/></td><td>${examineuserStr}</td>
   	
   	
   	<s:if test='finishflag >="1"'>
   		<s:if test='finishflag=="1"'>
   			<td>检验完成</td>
   		</s:if>
   		
   		<s:if test='finishflag=="2"'>
   			<td>已提交</td>
   		</s:if>
   	</s:if>
   	<s:else><td>未完成</td></s:else>
   	
   	
   	
   
      <td><s:if test='status=="C"'>
	       <s:if test='leader!=examineuser && leader==@com.zhirui.business.utils.BusinessUtils@getCurrentUser().getUid()'>
	            <s:a action="modifySendSample" namespace="/business"><s:param name="sendSample.id">${id}</s:param>修改</s:a> | <s:a action="submitSendSample" namespace="/business" onclick="return confirm('确定派样这条数据吗？');"><s:param name="sendSample.id">${id}</s:param>派样</s:a> | <s:a action="removeSendSample" namespace="/business" onclick="return confirm('确定删除这条数据吗？');"><s:param name="sendSample.id">${id}</s:param>删除</s:a>
	       </s:if>
       </s:if>
       <s:elseif test='status=="D"'>
	       <s:if test='examineuser==@com.zhirui.business.utils.BusinessUtils@getCurrentUser().getUid()'>
	            <s:a action="receiveSampleSendSample" namespace="/business"><s:param name="sendSample.id">${id}</s:param>收样</s:a>
	       </s:if>
	       <s:else>
	            <s:a action="detailSendSample" namespace="/business"><s:param name="sendSample.id">${id}</s:param>详细</s:a> | <s:a action="cancelsubmitSendSample" namespace="/business" onclick="return confirm('取消将删除本派样单单据，确定取消这条派样数据吗？');"><s:param name="sendSample.id">${id}</s:param>取消派样</s:a>
	       </s:else>
       </s:elseif>
       <s:elseif test='status=="E"||status=="F"'>
	       <s:a action="detailSendSample" namespace="/business"><s:param name="sendSample.id">${id}</s:param>详细</s:a> | <s:a action="continuesubmitSendSample" namespace="/business" onclick="return confirm('确定继续派样这条数据吗？');"><s:param name="sendSample.id">${id}</s:param>继续派样</s:a> | <s:a action="checkfinishSendSample" namespace="/business"><s:param name="sendSample.id">${id}</s:param>结果填报</s:a>
      		
       </s:elseif>
		<%-- <!--        曾智琴 -->
       <s:elseif test='status=="G" && @com.zhirui.business.utils.BusinessUtils@getCurrentUser().authority=="C"'>
			
 			<s:a action="detailSendSample" namespace="/business"><s:param name="sendSample.id">${id}</s:param>详细</s:a> | <s:a action=" exportSendSample " namespace="/business" onclick="o()"><s:param name="sendSample.id">${id}</s:param>导出</s:a> | <s:a action="export2SendSample" namespace="/business" onclick="return confirm('确定导出word吗？');"><s:param name="sendSample.id">${id}</s:param>导出离子</s:a>  
 				
 			<s:a action="detailSendSample" namespace="/business"><s:param name="sendSample.id">${id}</s:param>详细</s:a> | <a id="${id}" class="export"  style="color:#33666666;cursor:pointer;hover:color{red}">导出</a> 
 			
 			<a id="${id}_1" style="display:none" href="/libbms/business/export1SendSample.html?sendSample.id=${id}">1.TY-001通用</a>
 			<a id="${id}_2" style="display:none" href="/libbms/business/export2SendSample.html?sendSample.id=${id}">2.导出离子</a>
 			
 				<s:a id="${id2}" style="display:none" action="export1SendSample" namespace="/business" ><s:param name="sendSample.id">${id}</s:param>导出通用</s:a>  
 	   		<s:a id="${id}_2" style="display:none" action="export2SendSample" namespace="/business" ><s:param name="sendSample.id">${id}</s:param>导出离子</s:a>  	   		
 	   		<span id="myid" style="display:none">${id}</span>
 	   		
 	   </s:elseif> --%>
      <s:else>
       	   <s:a action="detailSendSample" namespace="/business"><s:param name="sendSample.id">${id}</s:param>详细</s:a> | <s:a action="checkfinishSendSample" namespace="/business"><s:param name="sendSample.id">${id}</s:param>结果填报</s:a>
       </s:else> 
</td></tr>
</s:iterator>
</tbody>
</table>
<s:include value="/include/page-bar.jsp"></s:include>	
</s:if>
<s:else>无列表信息</s:else>
<script>
layui.use('layer', function(){ //独立版的layer无需执行这一句
	  var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
});

$(function () {
    $(".export").click(function () {
	
		var myid = $(this).attr("id");
		console.log(myid);
		layer.open({
	     	title:"请选择模板"	       
	     	,content: '<select id="myselect"><option>'+ "1.TY-001通用"+'</option>' +'<option>'+ "2.YF-003 离子色谱-液相-液质联用" +'</option></select>'	    	
	     	,btn: ['确定','关闭']
	        ,btnAlign: 'c' //按钮居中
	        ,shade: 0 //不显示遮罩
	        ,yes: function(){
	        	var obj = document.getElementById("myselect");
	        	var index = obj.selectedIndex;
	        	var indexx = index +1 ;
	        	var mya = document.getElementById(myid+'_'+indexx+'');
	        	mya.click();
	          
	        }
	        ,btn1:function(){
	        	layer.closeAll();
	        }
	      });
	})
})
</script>
<style>
.export:hover{
   color:red !important;
}
</style>