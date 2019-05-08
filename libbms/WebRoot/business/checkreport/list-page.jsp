<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
%>
<head>
	<link href="<%=path %>/css/layui.css" rel="stylesheet" type="text/css" />
		<script src="<%=path %>/js/layui.js" charset="utf-8"></script>
</head>

<s:if test="pageModel != null && pageModel.list.size > 0">
共 <s:property value="pageModel.getTotalRecords()"/> 条记录
<table  border="0" cellpadding="0" cellspacing="0" class="lsttable">
<thead>
<tr>
    <th width="10" scope="col"></th>
    <th width="130" scope="col">报告编号</th>
    <th scope="col">样品名称</th>
    <th width="80" scope="col">接收日期</th>
    <th width="80" scope="col">完成日期</th>
    <th scope="col">委托单位</th>
    <th width="80" scope="col">操作</th>
</tr>
</thead>
<tbody>
<s:iterator value="pageModel.list" status="st">
<tr><td><input type="checkbox" name="ids" value="${id}" class="data_id"/></td>
<td>${sampleno}</td><td>${samplename}</td><td><s:date name="receivedate" format='yyyy-MM-dd'/></td><td><s:date name="finishdate" format='yyyy-MM-dd'/></td><td>${entrustcompany}</td>
<td><s:a action="detailCheckReport" namespace="/business"><s:param name="checkReport.id">${id}</s:param>详细</s:a> | <s:a action="modifyCheckReport" namespace="/business"><s:param name="checkReport.id">${id}</s:param>修改</s:a> | <s:a action="removeCheckReport" namespace="/business" onclick="return confirm('确定删除这条数据吗？');"><s:param name="checkReport.id">${id}</s:param>删除</s:a> | 
<%-- <s:a action="exportCheckReport" namespace="/business" onclick="return confirm('确定导出Word文件吗？');"><s:param name="checkReport.id">${id}</s:param>导出</s:a>
 --%>
 <!-- 曾智琴 -->
 <a id="${id}" class="export"  style="color:#33666666;cursor:pointer;hover:color{red}">导出</a>
 <a id="${id}_1" style="display:none" href="/libbms/business/exportCheckReport.html?checkReport.id=${id}">1.分析报告</a>
 <a id="${id}_2" style="display:none" href="/libbms/business/export2CheckReport.html?checkReport.id=${id}">2.检验报告</a>
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
	     	,content: '<select id="myselect" style="margin-left:60px;"><option>'+ "1.检测报告"+'</option>' +'<option>'+ "2.分析报告" +'</option></select>'	    	
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
