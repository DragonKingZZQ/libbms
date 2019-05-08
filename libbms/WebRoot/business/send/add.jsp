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
		<tr id="tr_input_user"><td><span class="mustfill">*</span>检验人员：</td><td><s:select id="checkuser" name="sendSample.examineuser" list="map" onchange="getRegistes()" cssClass="shortext"></s:select></td></tr>	    <tr><td  width="22%"><span class="mustfill">*</span>样品登记单号：</td><td><s:select name="sendSample.sampleno" id="selregiste" headerKey="" headerValue="请选择" list="#{}" onchange="getItems()" cssClass="longtext"></s:select><span class="textdesc"> 提交后的并且检验项目未全部派样的登记单才能派样</span></td></tr>
		<tr><td><span class="mustfill">*</span>样品编号：</td><td><s:textfield  id="no" name="sendSample.sendsampleno" readonly="true"  cssClass="hiddenlongtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>样品名称：</td><td><s:textfield  id="noname" name="sendSample.sendsamplename" readonly="true"  cssClass="hiddenlongtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>样品数量：</td><td><s:textfield  id="nocount" name="sendSample.samplecount" value="1" readOnly="true"  maxlength="2" data-validation="number" data-validation-error-msg="只能输入大于等于 1 的数字" cssClass="hiddenlongtext"></s:textfield></td></tr>
		<tr><td>存储条件：</td><td><s:checkboxlist id="storeid" list="#{'1':'常温','2':'冷藏','3':'冷冻','4':'避光','5':'干燥'}" name="sendSample.storecondition" disabled="true"/></td></tr>
		<tr><td><span class="mustfill">*</span>检验项目：</td><td></td></tr>
		<tr><td colspan="2" id="itemlist"></td></tr>
		<tr><td><span class="mustfill">*</span>要求开始日期：</td><td>
		<s:textfield id="startdate"  name="sendSample.startdate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly" cssClass="shorttext"></s:textfield>
		</td></tr> 
		<tr><td><span class="mustfill">*</span>要求完成日期：</td><td>
		<s:textfield id="enddate" name="sendSample.enddate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly" cssClass="shorttext"></s:textfield>
		</td></tr>
        
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
        var type ="";
        var oldcode = "";
        var codefrom = 0;
        
        var ckiarr = new Array();//zeng
        //初始化赋值 
        onload = function(){
           getRegistes();
        }
		function selCheckItems(f,e,t,c){
		   var content = '<div style="border:1px dashed gray" id="divitem'+selcount+'"><table><tr><td><s:hidden name="checkid"  value="'+f+'"></s:hidden>检验项：</td><td><s:textfield  name="inputCheckItems" readonly="true" value="'+e+'"  cssClass="hiddenshorttext"></s:textfield> | <a href="javascript:void(0)" onclick="removeItem2(divitem'+selcount+',0)">移除</a></td></tr> ';
		  /*  if(t=="2"){
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
		
		function addSampleCheckItems(f,e,t,c,cks){	
			
			
		     for(var j=0;j<parseInt(type);j++){
		           var code = "";
		           // 得到小编号
		           code = $("#code"+j).val();
		           
		         //曾智琴
					cks = ckiarr;
			           var cksi = new Array();
			           cksi = cks.split(",");
			         //  var obj = $("<select id='post'><option value="+f+">"+e+"</option></select>");
			           var objstr = '<div style="border:1px dashed gray" id="divitem'+selcount+'">"'+"检验项："+"<select id='post' name='post'><option value="+f+">"+e+"</option>";
			           for(var i=0;i<cksi.length;i++){		        	   
			        	   var index = cksi[i].indexOf("=");
			        	   var valuein = cksi[i].substring(1,index);   		        	   
			        		if(i!=cksi.length-1)
			        		   var conin = cksi[i].substring(index+1,cksi[i].length);   
			        		else
			        		  var conin = cksi[i].substring(index+1,cksi[i].length-1);
			        	  // obj.append("<option value="+valuein+">"+conin+"</option>");
			        	  objstr += "<option value="+valuein+">"+conin+"</option>";
			           }
					  objstr =objstr+'</select>  | <a href="javascript:void(0)" onclick="removeItem(divitem'+selcount+',0)">移除</a></div>';
			           console.log(objstr);
		           
				   var content = '<br><div style="display:none; border:1px dashed gray" id="divitem'+selcount+'"><s:hidden name="code"  value="'+code+'"/><table><tr><td><s:hidden name="checkid"  value="'+f+'"></s:hidden>检验项：</td><td><s:textfield  name="inputCheckItems" readonly="true" value="'+e+'"  cssClass="hiddenshorttext"></s:textfield> | <a href="javascript:void(0)" onclick="removeItem(divitem'+selcount+',0)">移除</a></td></tr> '; 			
 			   
				   /*  if(t=="2"){
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
				      content = content +'<tr id="tr_accordcontent'+selcount+'"><td>依据：  </td><td><s:textfield  name="inputAccording" value="'+c+'" data-validation="length" data-validation-length="1-500" data-validation-error-msg="字数范围在(1-500)"  cssClass="shorttext"></s:textfield></td></tr></table></div>';
				   } */
				   
				 //  $("#listdivitem"+j).append(obj);
				   $("#listdivitem"+j).append(objstr);
				   $("#listdivitem"+j).append(content);
				   $("#opt"+selcount).val(t);
				   selcount = selcount +1;
             }
     	     parent.iFrameHeight();
		}
		/**
		*  sum      :   样品数量
		*  samcode  :   原编号
		**/
		function addSampleList(sum,start,samcode){
		    $("#itemlist").empty();
		    //start 是返回的断号和最新开始编号的字符串
		    //如果字符串只包含一个数字，表示第一次派样，或全新派样，否则是断号派样（派样没有派完的）
		    var arr = start.split(",");
		    var len = arr.length;
		    var v = sum-start;
		    
		    //根据这个循环变更样品的数量（直接取的样品数量不准确）
		    var sc = 0;
		    //alert(start);
		    //全新派样
		    if (len==1){
		        sc = sum;
		        //for(var i=0;i<=v;i++){
			    for(var i=0;i<sum;i++){
			          var code = "";
			          //var val = i+parseInt(start);
			          var val = parseInt(parseInt(arr[0])+i);
			          if (val>=10){
			              code = samcode +"-"+val;
			          }else{
			              code = samcode + "-0"+val;
			          }
			          var listcontent = '<div style="border:1px dashed gray" id="listdiv'+i+'"><s:hidden name="bigcode" id="code'+i+'" value="'+code+'"/><table>';
			          listcontent = listcontent+'<tr><td>样品编号 ：<br>'+code+'  <br> <a href="javascript:void(0)" onclick="removeItem(listdiv'+i+',1)">移除</a></td>';
			          listcontent = listcontent+'<td id="listdivitem'+i+'"></td></tr>';
			          listcontent = listcontent+'</table></div> ';
			          
			          $("#itemlist").append(listcontent);
			    }
		    }else{
		          //断号派样
	              if (len-2>sum)
	                 sc = sum;
	              else
	                 sc = sum-parseInt(arr[0]);
	              if (sc<=0) sc = sum;
	              
		          for(var i=0;i<sc;i++){
			          var code = "";
			          var val = 0;
			          if (i+1>=len)
			              val = parseInt(arr[len-1])+i-1;
			          else
			              val = parseInt(arr[i+1]);
			          if (val>=10){
			              code = samcode +"-"+val;
			          }else{
			              code = samcode + "-0"+val;
			          }
			         
			          var listcontent = '<div style="border:1px dashed gray" id="listdiv'+i+'"><s:hidden name="bigcode" id="code'+i+'" value="'+code+'"/><table>';
			          listcontent = listcontent+'<tr><td>样品编号 ：<br>'+code+'  <br> <a href="javascript:void(0)" onclick="removeItem(listdiv'+i+',1)">移除</a></td>';
			          listcontent = listcontent+'<td id="listdivitem'+i+'"></td></tr>';
			          listcontent = listcontent+'</table></div> ';
			         
			          $("#itemlist").append(listcontent);
			    }
		    }
		    $("#nocount").val(sc);		
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
	    
		function removeItem(aObj,val) {		   
			if (confirm("确定要删除本条数据吗？")){
					
		           $(aObj).remove();
		           if(val=="1"){
                        $("#nocount").val(parseInt($("#nocount").val())-1);		           
		           }
		         
		     }
		     parent.iFrameHeight();
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
	          if($("input[name=inputCheckItems]").length>=2){
				    alert("一个派样单只有一个检测项目");
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
				     $("#itemlist").empty();
				    var info = eval("("+data+")");
				    for(var o in info){ 
			            if (o!=null&&o!="unique"){   
                              $("#selregiste").append("<option value='"+info[o].id+"'>"+info[o].name+"</option>");
			            }  
				    } 
				    // 重新赋值后调用事件 
				    getItems();
				},
				error: function(){                         
		           alert('获取样品登记单信息出错！');    
		        }
			});
	    }
	    
	    function getItems(){
	       if ($("#selregiste").val()=="") return;
	       $.ajax({
	            async:false,
	            type:'get',
				url: 'getCheckItemsSendSample.html',
				data:{ registeid: $("#selregiste").val(),modifyflag:0,checkuser:$("#checkuser").val()},
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
				    ckiarr = info[0].checkitems;   //曾智琴
				    
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
				                    addSampleCheckItems(info[o].id,info[o].name,info[o].accordingtype,info[o].according,i,info[0].checkitems);
				               }				            
				            }
				            i = i+1;
				        }  
				    } 
				 
				},
				error: function(){                         
		         /* alert('获取检验项目信息出错！');    */
		        }
			});
	    }
		</script>
	</body>
</html>
