<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<body>
	<s:include value="/include/common.jsp"></s:include>
	<div id="pagebody">
	<div id="mainbody">
	<div id="contentbar">
		<h1>继续派样</h1>
		<s:form action="nextsubmitSendSample" namespace="/business">
		<table class="frmtable">
		<s:hidden name="sendSample.id"></s:hidden>
		<s:hidden name="sendSample.receiveflag" value="1"></s:hidden>
		<s:hidden id="sct" name="sendSample.samplecount"></s:hidden>
		<tr><td>样品登记单号：</td><td>${sendSample.sendsampleno}</td></tr>
		<tr><td>样品名称：</td><td>${sendSample.sendsamplename}</td></tr>
		<tr><td>样品数量：</td><td id="sct">${sendSample.samplecount}</td></tr>
		<tr><td>存储条件：</td><td><s:checkboxlist list="#{'1':'常温','2':'冷藏','3':'冷冻','4':'避光','5':'干燥'}" name="sendSample.storecondition" value="storeList" disabled="true"/></td></tr>
		<tr  style="background:#DDD" height="30"><td colspan="2">请填写下面内容：</td></tr>
		<tr><td><span class="mustfill">*</span>检验项目：</td><td></td></tr>
		<tr><td id="itemlist" colspan="2">
		<s:iterator value="subno" status="sub" id="s">
		   <div style="border:1px dashed gray" id="listdiv${sub.index}"><table>
		      <tr><td>样品编号 ：<br>${s}  <br> <a href="javascript:void(0)" onclick="removeItem(listdiv${sub.index},1)">移除</a></td><td id="listdivitem${sub.index}">
				  <s:iterator value="sciList" status="st">
				    <s:if test='#s==subsendsampleno'>
					    <div id="div${st.index}" style="border:1px dashed gray"><table>
					          <tr><td><s:hidden name="code" value="%{subsendsampleno}"/><s:hidden name="checkid" value="%{checkitem}"></s:hidden>检验项：<s:textfield  name="inputCheckItems" value="%{checkitemname}" readonly="true" cssClass="hiddenlongtext"></s:textfield> | <a href="javascript:void(0)" onclick="removeItem(div${st.index},0)">移除</a></td></tr>
					         <%-- <s:if test='type=="2"'>
					              <tr><td><s:hidden name="optvalue" id="opt%{#st.index}" value="2"></s:hidden><input type="radio" name="sampleRegiste.accordingtype${st.index}" id="opt2${st.index}" value="2" checked="checked" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype2">由本中心选定合适的标准方法</label><input type="radio" name="sampleRegiste.accordingtype${st.index}" id="opt3${st.index}"  value="3" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype3">同意用本中心确定的非标准方法</label><input type="radio" name="sampleRegiste.accordingtype${st.index}" id="opt1${st.index}"  value="1" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype1">指定检验依据</label></td></tr>
					              <tr id="tr_accordcontent${st.index}" ><td> 依据： <s:textfield  name="inputAccording" value="%{standard}" data-validation="length" data-validation-length="1-500" data-validation-error-msg="字数范围在(1-500)"  cssClass="longtext"></s:textfield></td></tr>
					         </s:if>
					         <s:if test='type=="3"'>
					              <tr><td><s:hidden name="optvalue" id="opt%{#st.index}"  value="3"></s:hidden><input type="radio" name="sampleRegiste.accordingtype${st.index}" id="opt2${st.index}" value="2" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype2">由本中心选定合适的标准方法</label><input type="radio" name="sampleRegiste.accordingtype${st.index}" id="opt3${st.index}" checked="checked" value="3" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype3">同意用本中心确定的非标准方法</label><input type="radio" name="sampleRegiste.accordingtype${st.index}" id="opt1${st.index}" value="1" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype1">指定检验依据</label></td></tr>
					              <tr id="tr_accordcontent${st.index}" ><td> 依据： <s:textfield  name="inputAccording" value="%{standard}" data-validation="length" data-validation-length="1-500" data-validation-error-msg="字数范围在(1-500)"  cssClass="longtext"></s:textfield></td></tr>
					         </s:if>
					         <s:if test='type=="1"'>
					              <tr><td><s:hidden name="optvalue" id="opt%{#st.index}"  value="1"></s:hidden><input type="radio" name="sampleRegiste.accordingtype${st.index}" id="opt2${st.index}" value="2" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype2">由本中心选定合适的标准方法</label><input type="radio" name="sampleRegiste.accordingtype${st.index}" id="opt3${st.index}" value="3" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype3">同意用本中心确定的非标准方法</label><input type="radio" name="sampleRegiste.accordingtype${st.index}" id="opt1${st.index}" checked="checked" value="1" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype1">指定检验依据</label></td></tr>
					              <tr id="tr_accordcontent${st.index}"><td> 依据： <s:textfield  name="inputAccording" value="%{standard}" data-validation="length" data-validation-length="1-500" data-validation-error-msg="字数范围在(1-500)"  cssClass="longtext"></s:textfield></td></tr>
					         </s:if>  --%>
					         
					   </table></div>
					   </s:if>
		          </s:iterator>
		          </td></tr>
			</table></div>	  
		</s:iterator>
		</td></tr>
		<tr><td><span class="mustfill">*</span>要求开始日期：</td><td>
		<s:textfield id="startdate"  name="sendSample.startdate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly" cssClass="shorttext">
		<s:param name="value"><s:date name="sendSample.startdate" format="yyyy-MM-dd"/></s:param></s:textfield>
		</td></tr> 
		<tr><td><span class="mustfill">*</span>要求完成日期：</td><td>
		<s:textfield id="enddate" name="sendSample.enddate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly" cssClass="shorttext">
		<s:param name="value"><s:date name="sendSample.enddate" format="yyyy-MM-dd"/></s:param></s:textfield>
		</td></tr>
        <tr id="tr_input_user"><td><span class="mustfill">*</span>检验人员：</td><td><s:select name="sendSample.examineuser" list="map" cssClass="shortext"></s:select></td></tr>
        <tr><td>备注：</td><td><s:textarea name="sendSample.remark" cssStyle="width:400px;height:110px;" data-validation="length" data-validation-length="0-200" data-validation-error-msg="字数范围在(0-200)" ></s:textarea></td></tr>
		<tr><td></td><td>（还可以输入<span id="textarea_length_max">200</span>字）</td><td></td></tr>
		<tr><td colspan="2" align="center"><s:submit value="保 存" ></s:submit><input value="返 回" type="button" onclick="history.back();" /></td></tr>
		</table>
		</s:form>
		<script type="text/javascript">
		   function removeItem(aObj,val) {
			   if (confirm("确定要删除本条数据吗？")){
				 $(aObj).remove();
				 if (val==1){
				     $("#sct").val(parseInt($("#sct").val())-1);
				 }
			   }
		   }
		   
		   function showContent(o){
	        /*
	        if ($('input:radio[name="sampleRegiste.accordingtype'+o+'"]:checked').val()!="1"){
	             $("#accordings").val("无");
	             $("#tr_accordcontent"+o).hide();
	        }else{
	             $("#accordings").val("");
	             $("#tr_accordcontent"+o).show();
	        }
	        */
	        $("#opt"+o).val($('input:radio[name="sampleRegiste.accordingtype'+o+'"]:checked').val());
	      }
		</script>
        </div>
		</div>
		</div>
	</body>
</html>