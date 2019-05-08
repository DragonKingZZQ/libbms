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
	<div id="pagebody">
	<div id="mainbody">
	<div id="contentbar">
		<h1>添加派样单</h1>
		<s:form action="saveSendSample" namespace="/business">
		<table class="frmtable">
		<s:if test='registeList==null||registeList.getList().size()==0'>
		     <tr><td  width="22%"><span class="mustfill">*</span>样品登记单号：</td><td><s:select name="sendSample.sampleno" id="selregiste" headerKey="" headerValue="请选择" list="#{}" onchange="getItems()" cssClass="longtext"></s:select><span class="textdesc"> 提交后的登记单才能派样</span></td></tr>
		</s:if>
		<s:else>
		    <tr><td  width="22%"><span class="mustfill">*</span>样品登记单号：</td><td><s:select name="sendSample.sampleno" id="selregiste" headerKey="" headerValue="请选择" list="registeList" onchange="getItems()" cssClass="longtext"></s:select><span class="textdesc"> 提交后的登记单才能派样</span></td></tr>
		</s:else>
		<tr><td><span class="mustfill">*</span>样品编号：</td><td><s:textfield  id="no" name="sendSample.sendsampleno" readonly="true"  cssClass="hiddenlongtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>样品名称：</td><td><s:textfield  id="noname" name="sendSample.sendsamplename" readonly="true"  cssClass="hiddenlongtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>样品数量：</td><td><s:textfield  id="nocount" name="sendSample.samplecount" data-validation="length" data-validation-length="1-50" data-validation-error-msg="字数范围在(1-50)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td>存储条件：</td><td><s:checkboxlist id="storeid" list="#{'1':'常温','2':'冷藏','3':'冷冻','4':'避光','5':'干燥'}" name="sendSample.storecondition" disabled="true"/></td></tr>
		<tr><td><span class="mustfill">*</span>检验项目：</td><td></td></tr>
<!--		<tr><td></td><td id="itemlist"><div id="divdefault" style="border:1px dashed gray"><table><tr><td><s:hidden id="firsthiddencheck" name="checkid"  value=""></s:hidden>检验项：</td><td><s:textfield id="firstInputItem" name="inputCheckItems" readonly="true"  cssClass="hiddenlongtext"></s:textfield></td></tr><tr><td> 依    据： </td><td><s:textfield id="InputItemAccording" name="inputAccording" data-validation="length" data-validation-length="1-500" data-validation-error-msg="字数范围在(1-500)"  cssClass="longtext"></s:textfield> | <a href="javascript:void(0)" onclick="removeItem(divdefault)">移除</a></td></tr></table></div></td></tr>-->
		<tr><td colspan=2 id="itemlist"></td></tr>
		<tr><td><span class="mustfill">*</span>要求开始日期：</td><td>
		<s:textfield id="startdate"  name="sendSample.startdate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly" cssClass="shorttext"></s:textfield>
		</td></tr> 
		<tr><td><span class="mustfill">*</span>要求完成日期：</td><td>
		<s:textfield id="enddate" name="sendSample.enddate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly" cssClass="shorttext"></s:textfield>
		</td></tr>
        <tr id="tr_input_user"><td><span class="mustfill">*</span>检验人员：</td><td><s:select name="sendSample.examineuser" list="map" cssClass="shortext"></s:select></td></tr>
	    <tr><td>备注：</td><td><s:textarea name="sendSample.remark" cssStyle="width:400px;height:110px;" data-validation="length" data-validation-length="0-200" data-validation-error-msg="字数范围在(0-200)"></s:textarea></td></tr>
		<tr><td></td><td>（还可以输入<span id="textarea_length_max">200</span>字）</td><td></td></tr>
	    <tr>
           	<td colspan="2" class="textdesc">注意：以上<span class="mustfill">*</span>标识的为必填项</td>
        </tr>
		<tr><td colspan="2" align="center"><s:submit value="保 存" onclick="return check()"></s:submit><input value="返 回" type="button" onclick="history.back();" /></td></tr>
		</table>
		</s:form>
        </div>
		</div>
		</div>
		<script type="text/javascript">
		$.validate({modules : 'platform'});
		set_date_picker($('#startdate'));
		set_date_picker($('#enddate'));
		set_date_picker($('#receivedate'));
		set_date_picker($('#finishdate'));
        var selcount = 0;

		function selCheckItems(f,e,t,c){
		   var content = '<div style="border:1px dashed gray" id="divitem'+selcount+'"><table><tr><td><s:hidden name="checkid"  value="'+f+'"></s:hidden>检验项：</td><td><s:textfield  name="inputCheckItems" readonly="true" value="'+e+'"  cssClass="hiddenshorttext"></s:textfield> | <a href="javascript:void(0)" onclick="removeItem(divitem'+selcount+')">移除</a></td></tr> ';
		   if(t=="2"){
		       content = content+ '<tr><td></td><td><s:hidden name="optvalue" id="opt'+selcount+'"></s:hidden><input type="radio" name="sampleRegiste.accordingtype'+selcount+'" id="opt2'+selcount+'" value="2" checked="checked" onclick="showContent('+selcount+')"/><label for="sampleRegiste_accordingtype2">由本中心选定合适的标准方法</label>';
           }else{
               content = content+ '<tr><td></td><td><s:hidden name="optvalue" id="opt'+selcount+'"></s:hidden><input type="radio" name="sampleRegiste.accordingtype'+selcount+'" id="opt2'+selcount+'" value="2" onclick="showContent('+selcount+')"/><label for="sampleRegiste_accordingtype2">由本中心选定合适的标准方法</label>';
           }
           if(t=="3"){
               content = content+ '<input type="radio" name="sampleRegiste.accordingtype'+selcount+'" id="opt3'+selcount+'" checked="checked" value="3" onclick="showContent('+selcount+')"/><label for="sampleRegiste_accordingtype3">同意用本中心确定的非标准方法</label>';
		   }else{
		       content = content+ '<input type="radio" name="sampleRegiste.accordingtype'+selcount+'" id="opt3'+selcount+'" value="3" onclick="showContent('+selcount+')"/><label for="sampleRegiste_accordingtype3">同意用本中心确定的非标准方法</label>';
		   }
		   if(t=="1"){
		      content = content +'<input type="radio" name="sampleRegiste.accordingtype'+selcount+'" id="opt1'+selcount+'" checked="checked" value="1" onclick="showContent('+selcount+')"/><label for="sampleRegiste_accordingtype1">指定检验依据</label></td></tr>';
		      content = content +'<tr id="tr_accordcontent'+selcount+'"><td>依据：  </td><td><s:textfield  name="inputAccording" value="'+c+'" data-validation="length" data-validation-length="1-500" data-validation-error-msg="字数范围在(1-500)"  cssClass="shorttext"></s:textfield></td></tr></table></div>';
		   }else{
		      content = content +'<input type="radio" name="sampleRegiste.accordingtype'+selcount+'" id="opt1'+selcount+'" checked="checked" value="1" onclick="showContent('+selcount+')"/><label for="sampleRegiste_accordingtype1">指定检验依据</label></td></tr>';
		      content = content +'<tr id="tr_accordcontent'+selcount+'"><td>依据：  </td><td><s:textfield  name="inputAccording" data-validation="length" data-validation-length="1-500" data-validation-error-msg="字数范围在(1-500)"  cssClass="shorttext"></s:textfield></td></tr></table></div>';
		   }
		   $("#itemlist").append(content);
		   $("#opt"+selcount).val(t);

		   selcount = selcount + 1;
		   
		   parent.iFrameHeight();
		}
		
		function showContent(o){
	       /*
	        if ($('input:radio[name="sampleRegiste.accordingtype'+o+'"]:checked').val()!="1"){
	             $("#accordings").val("无");
	             $("#tr_accordcontent"+o).hide();
	        }else{
	             $("#accordings").val("");
	             $("#tr_accordcontent"+o).show();
	        } */
	        $("#opt"+o).val($('input:radio[name="sampleRegiste.accordingtype'+o+'"]:checked').val());
	    }
	    
		function removeItem(aObj) {
		     var obj = document.getElementById("divdefault");
		     if (confirm("确定要删除本条数据吗？")){
		           $(aObj).remove();
		     }
		}

	    function check(){
	          if ($("#selregiste").val()==""){
	              alert("请选择登记单 ！");
	              return false;
	          }
	          if ($("#startdate").val()>$("#endate").val()){
	                  alert("要求完成日期不能早于要求开始日期！");
	                  return false;
	          }
	    }
	    
	    function getItems(){
	       if ($("#selregiste").val()=="") return;
	       $.ajax({
	            async:false,
	            type:'get',
				url: 'getCheckItemsSendSample.html',
				data:{ registeid: $("#selregiste").val(),modifyflag:0},
				dataType:'json',
				success: function(data) {
				    // 先删除原来增加的内容
	                for(var j=0;j<selcount;j++){
	                     var obj = document.getElementById("divitem"+j);
	                     if (obj!=null && obj !=""){
	                         $("#divitem"+j).remove();
	                     }
	                }
				    var info = eval("("+data+")");
				    var i = 0;
				    for(var o in info){ 
				        if (o!=null&&o!="unique"){   
				        
				            if(i==0){
				               $("#no").val(info[o].id);
				               $("#noname").val(info[o].name);
				               $("#nocount").val(info[o].count);
				               $("input[name='sendSample.storecondition']").each(function () { 
				                    if (info[o].store.indexOf($(this).val())>=0){
				                        $(this).attr("checked", true); 
				                    }
				                });
				            }else if (i==1){
				               var obj = document.getElementById("divdefault");
				               if (obj!=null&&obj!=""){
				                    $("#firsthiddencheck").val(info[o].id);
				                    $("#firstInputItem").val(info[o].name);
				               }else{
				                  selCheckItems(info[o].id,info[o].name,info[o].accordingtype,info[o].according);	
				               }
				            }else{
				               selCheckItems(info[o].id,info[o].name,info[o].accordingtype,info[o].according);				            
				            }
				            i = i+1;
				        }  
				    }  
				},
				error: function(){                         
		           alert('获取检验项目信息出错！');    
		        }
			});
	    }
		</script>
	</body>
</html>
