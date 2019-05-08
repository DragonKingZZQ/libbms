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
		<h1>修改派样单</h1>
		<s:form action="updateSendSample" namespace="/business">
		<table class="frmtable">
		<s:hidden name="sendSample.id"></s:hidden>
		<s:hidden name="sendSample.sendid"></s:hidden>
		<s:hidden name="sendSample.sampleno" id="regid"></s:hidden>
		<tr id="tr_input_user"><td><span class="mustfill">*</span>检验人员：</td><td><s:select name="sendSample.examineuser" id="checkuser"  list="map" onchange="getRegistes()" cssClass="shortext"></s:select></td></tr>
		<tr><td  width="22%"><span class="mustfill">*</span>样品登记单号：</td><td><s:select id="selregiste"  headerKey="" headerValue="请选择" list="#{}" onchange="getItems()" cssClass="longtext"></s:select></td></tr>
		<tr><td><span class="mustfill">*</span>样品编号：</td><td><s:textfield  id="no" name="sendSample.sendsampleno" readonly="true" cssClass="hiddenlongtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>样品名称：</td><td><s:textfield  id="noname" name="sendSample.sendsamplename"  readonly="true" cssClass="hiddenlongtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>样品数量：</td><td><s:textfield  id="nocount" name="sendSample.samplecount" readOnly="true" maxlength="2" data-validation="number" data-validation-error-msg="只能输入大于等于 1 的数字" cssClass="hiddenlongtext"></s:textfield></td></tr>
		<tr><td>存储条件：</td><td><s:checkboxlist list="#{'1':'常温','2':'冷藏','3':'冷冻','4':'避光','5':'干燥'}" name="sendSample.storecondition" value="storeList" disabled="true"/></td></tr>
		<tr><td><span class="mustfill">*</span>检验项目：</td><td></td></tr>
		<tr><td id="itemlist" colspan="2">
		<s:iterator value="subno" status="sub" id="s">
		   <div style="border:1px dashed gray" id="listdiv${sub.index}"><table>
		      <tr><td>样品编号 ：<br>${s}  <br> <a href="javascript:void(0)" onclick="removeItem(listdiv${sub.index},1)">移除</a></td><td id="listdivitem${sub.index}">
				  <s:iterator value="sciList" status="st">
				    <s:if test='#s==subsendsampleno'>
					    <div id="div${st.index}" style="border:1px dashed gray"><table>
					         <tr><td><s:hidden name="code" value="%{subsendsampleno}"/><s:hidden name="checkid" value="%{checkitem}"></s:hidden>检验项：<s:textfield  name="inputCheckItems" value="%{checkitemname}" readonly="true" cssClass="hiddenlongtext"></s:textfield> | <a href="javascript:void(0)" onclick="removeItem(div${st.index},0)">移除</a></td></tr>
					          <s:if test='type=="2"'>
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
					         </s:if>
					         
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
        
	    <tr><td>备注：</td><td><s:textarea name="sendSample.remark" cssStyle="width:400px;height:110px;" data-validation="length" data-validation-length="0-200" data-validation-error-msg="字数范围在(0-200)" ></s:textarea></td></tr>
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
		var type ="";
        var oldcode = "";
        var codefrom = 0;
        //初始化赋值 
        onload = function(){
           getRegistes();
        }
		function selCheckItems(f,e,t,c){
		   var content = '<div style="border:1px dashed gray" id="divitem'+selcount+'"><table><tr><td><s:hidden name="checkid"  value="'+f+'"></s:hidden>检验项：</td><td><s:textfield  name="inputCheckItems" readonly="true" value="'+e+'"  cssClass="hiddenshorttext"></s:textfield> | <a href="javascript:void(0)" onclick="removeItem(divitem'+selcount+',0)">移除</a></td></tr> ';
		   /* if(t=="2"){
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
		   } */
		   $("#itemlist").append(content);
		   $("#opt"+selcount).val(t);

		   selcount = selcount + 1;
		   
		   parent.iFrameHeight();
		}
		
		function addSampleCheckItems(f,e,t,c){
		     for(var j=0;j<parseInt(type);j++){
		           var code = "";
		           var val = parseInt(codefrom) + j;
		          
		           if (val>=10){
		              code = oldcode + "-"+val;
		           }else{
		              code = oldcode + "-0"+val;
		           }
				   var content = '<div style="border:1px dashed gray" id="divitem'+selcount+'"><s:hidden name="code" value="'+code+'"/><table><tr><td><s:hidden name="checkid"  value="'+f+'"></s:hidden>检验项：</td><td><s:textfield  name="inputCheckItems" readonly="true" value="'+e+'"  cssClass="hiddenshorttext"></s:textfield> | <a href="javascript:void(0)" onclick="removeItem(divitem'+selcount+',0)">移除</a></td></tr> ';
				   /* if(t=="2"){
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
 */
				   $("#listdivitem"+j).append(content);
				   $("#opt"+selcount).val(t);
				   selcount = selcount +1;
             }
     	     parent.iFrameHeight();
		}
		
		function addSampleList(sum,start,samcode){
		    var v = sum-start;
		    for(var i=0;i<=v;i++){
		          var code = "";
		          var val = i+parseInt(start);
		          if (val>=10){
		              code = samcode +"-"+val;
		          }else{
		              code = samcode + "-0"+val;
		          }
		          var listcontent = '<div style="border:1px dashed gray" id="listdiv'+i+'"><table>';
		          listcontent = listcontent+'<tr><td>样品编号 ：<br>'+code+'  <br> <a href="javascript:void(0)" onclick="removeItem(listdiv'+i+',1)">移除</a></td>';
		          listcontent = listcontent+'<td id="listdivitem'+i+'"></td></tr>';
		          listcontent = listcontent+'</table></div> ';
		          
		          $("#itemlist").append(listcontent);
		    }
		    
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
	        }*/
	        $("#opt"+o).val($('input:radio[name="sampleRegiste.accordingtype'+o+'"]:checked').val());
	    }
		
		function removeItem(aObj,val) {
		   if (confirm("确定要删除本条数据吗？")){
			 $(aObj).remove();
			 if(val=="1"){
                   $("#nocount").val(parseInt($("#nocount").val())-1);		           
             }
		   }
		}

	    function check(){
	          if ($("#startdate").val()>$("#endate").val()){
	                  alert("要求完成日期不能早于要求开始日期！");
	                  return false;
	          }
	    }
	    function getRegistes(){
	       if ($("#checkuser").val()=="请选择") return;
	       $.ajax({
	            async:false,
	            type:'get',
				url: 'getRegistesSendSample.html',
				data:{ examineUser: $("#checkuser").val()},
				dataType:'json',
				success: function(data) {
				    // 先删除原来增加的内容
				    $("#selregiste").empty();
				    var info = eval("("+data+")");
				    for(var o in info){ 
			            if (o!=null&&o!="unique"){   
                              $("#selregiste").append("<option value='"+info[o].id+"'>"+info[o].name+"</option>");
			            }  
				    } 
				    // 重新赋值后调用事件 
				    //给下拉框赋值
				    $("#selregiste").val($("#regid").val());
				},
				error: function(){                         
		           alert('获取样品登记单信息出错！');    
		        }
			});
	    }
	    
	    function getItems(){
	       if ($("#selregiste").val()=="") return;
	       $("#regid").val($("#selregiste").val());
	       $.ajax({
	            async:false,
	            type:'get',
				url: 'getCheckItemsSendSample.html',
				data:{ registeid: $("#selregiste").val(),modifyflag:1,checkuser:$("#checkuser").val()},
				dataType:'json',
				success: function(data) {
				    // 先删除原来增加的内容
	                $("#itemlist").empty();
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
				                type = info[o].count;
				                oldcode = info[o].id;
				                codefrom = info[o].codestart;
				                // 增加样品 列表 
				                addSampleList(type,info[o].codestart,info[o].id);
				            }else{
				               // 如果样品数量为空或者不是数字 ，直接按检验项目展示，否则按样品 
				               if (info[o].count==null||info[o].count==""||isNaN(type)){
				                    selCheckItems(info[o].id,info[o].name,info[o].accordingtype,info[o].according,i);
				               }else{
				                    addSampleCheckItems(info[o].id,info[o].name,info[o].accordingtype,info[o].according,i);
				               }				            
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