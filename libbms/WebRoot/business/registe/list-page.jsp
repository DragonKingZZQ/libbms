<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
%>

<s:if test="pageModel != null && pageModel.list.size > 0">
共 <s:property value="pageModel.getTotalRecords()"/> 条记录  每页显示<s:textfield name="pagerecorders" id="newsize"  maxlength="3" data-validation="number" data-validation-error-msg="只能输入大于等于 1 的数字" style="width:30px"></s:textfield>条  
<input type="submit" name="d" onclick="return setPageSize()" value="确定" />
<table  border="0" cellpadding="0" cellspacing="0" class="lsttable">
<thead>
<tr>
    <th width="10" scope="col"></th>
    <th width="100" scope="col">样品编号</th>
    <th scope="col">样品名称</th>
    <th width="50" scope="col">联系人</th>
    <th width="60" scope="col">联系方式</th>
    <th scope="col">委托单位</th>
    <th width="120" scope="col">操作</th>
</tr>
</thead>
<tbody>
<div id="div1" style="display:none" />
<s:iterator value="pageModel.list" status="st">
<s:if test='overtimeflag==1&&balancemoney>0'>
<tr style="background:#F3899E"   onMouseMove="GetDIV('block','${checkitems}')" onmouseout="GetDIV('none','')" ><td>
   <s:if test='status=="A"'><input type="checkbox" name="ids" value="${id}" class="data_id"/></s:if>
   <s:else><input type="checkbox" name="r" disabled="disabled"/></s:else>
</td><td>${sampleno}</td><td>${samplename}</td><td>${senduser}</td><td>${relation}</td><td>${entrustcompanyStr}</td>
<td><s:a action="detailSampleRegiste" namespace="/business"><s:param name="sampleRegiste.id">${id}</s:param>详细</s:a> | <s:a action="removeSampleRegiste" namespace="/business" onclick="return confirm('确定删除这条数据吗？');"><s:param name="sampleRegiste.id">${id}</s:param>删除</s:a> | <s:a action="cashSampleRegiste" namespace="/business"><s:param name="sampleRegiste.id">${id}</s:param>结算</s:a>
</td></tr>
</s:if>
<s:elseif test='overtimeflag==1&&balancemoney==0'>
<tr style="background:#989DE4"  onMouseMove="GetDIV('block','${checkitems}')" onmouseout="GetDIV('none','')" ><td>
   <s:if test='status=="A"'><input type="checkbox" name="ids" value="${id}" class="data_id"/></s:if>
   <s:else><input type="checkbox" name="r" disabled="disabled"/></s:else>
</td><td>${sampleno}</td><td>${samplename}</td><td>${senduser}</td><td>${relation}</td><td>${entrustcompanyStr}</td>
<td><s:a action="detailSampleRegiste" namespace="/business"><s:param name="sampleRegiste.id">${id}</s:param>详细</s:a> | <s:a action="removeSampleRegiste" namespace="/business" onclick="return confirm('确定删除这条数据吗？');"><s:param name="sampleRegiste.id">${id}</s:param>删除</s:a>
</td></tr>
</s:elseif>
<s:elseif test='overtimeflag==0&&balancemoney>0'>
<tr style="background:#E3E099"  onMouseMove="GetDIV('block','${checkitems}')" onmouseout="GetDIV('none','')"><td>
   <s:if test='status=="A"'><input type="checkbox" name="ids" value="${id}" class="data_id"/></s:if>
   <s:else><input type="checkbox" name="r" disabled="disabled"/></s:else>
</td><td>${sampleno}</td><td>${samplename}</td><td>${senduser}</td><td>${relation}</td><td>${entrustcompanyStr}</td>
<td><s:a action="detailSampleRegiste" namespace="/business"><s:param name="sampleRegiste.id">${id}</s:param>详细</s:a> | <s:a action="removeSampleRegiste" namespace="/business" onclick="return confirm('确定删除这条数据吗？');"><s:param name="sampleRegiste.id">${id}</s:param>删除</s:a> | <s:a action="cashSampleRegiste" namespace="/business"><s:param name="sampleRegiste.id">${id}</s:param>结算</s:a>
</td></tr>
</s:elseif>
<s:else>
<tr onMouseMove="GetDIV('block','${checkitems}')" onmouseout="GetDIV('none','')"><td>
   <s:if test='status=="A"'><input type="checkbox" name="ids" value="${id}" class="data_id"/></s:if>
   <s:else><input type="checkbox" name="r" disabled="disabled"/></s:else>
</td><td>${sampleno}</td><td>${samplename}</td><td>${senduser}</td><td>${relation}</td><td>${entrustcompanyStr}</td>
<td><s:a action="detailSampleRegiste" namespace="/business"><s:param name="sampleRegiste.id">${id}</s:param>详细</s:a> | <s:a action="removeSampleRegiste" namespace="/business" onclick="return confirm('确定删除这条数据吗？');"><s:param name="sampleRegiste.id">${id}</s:param>删除</s:a>
</td></tr>
</s:else>
</s:iterator>
</tbody>
</table>
<span class="textdesc">(说明： <span style="color:#F3899E">■</span>未付款+延期未完成 <span style="color:#E3E099">■</span>未付款 <span style="color:#989DE4">■</span>延期未完成)</span><br>
<s:include value="/include/page-bar.jsp"></s:include>	
</s:if>
<s:else>无列表信息</s:else>
<script type="text/javascript">
	/* function GetDIV(obj,content){
	    var d = document.getElementById("div1"); 
	    d.innerHTML =content;
		d.style.display=obj;
		d.style.top=event.clientY+10;
		d.style.left=event.clientX+5;
		d.style.position="absolute"; 
	} */
</script>