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
		<h1>添加检验报告</h1>
		<s:form action="saveCheckReport" namespace="/business">
		<table class="frmtable">
		<s:if test='registeList==null||registeList.getList().size()==0'>
		    <tr><td ><span class="mustfill">*</span>样品登记单号：</td><td><s:select id="selregiste" name="checkReport.sampleid" headerKey="" headerValue="请选择" list="#{}" onchange="getItems()" cssClass="longtext"></s:select></td></tr>
		</s:if>
		<s:else>
		    <tr><td><span class="mustfill">*</span>样品登记单号：</td><td><s:select id="selregiste" name="checkReport.sampleid" headerKey="" headerValue="请选择" list="registeList" onchange="getItems()" cssClass="hiddenlongtext"></s:select></td></tr>
		</s:else>
		<tr><td><span class="mustfill">*</span>检测报告编号：</td><td><s:textfield id="no" name="checkReport.sampleno" data-validation="length" data-validation-length="1-50" data-validation-error-msg="字数范围在(1-50)" readonly="true" cssClass="hiddenlongtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>样品名称：</td><td><s:textfield id="noname" name="checkReport.samplename"  data-validation="length" data-validation-length="1-500" data-validation-error-msg="字数范围在(1-500)" readonly="true" cssClass="hiddenlongtext"></s:textfield></td></tr>
		<tr><td>样品状态：</td><td><s:textfield id="status" name="checkReport.samplestatus"  data-validation="length" data-validation-length="0-50" data-validation-error-msg="字数范围在(0-50)" readonly="true" cssClass="hiddenshorttext"></s:textfield></td></tr>
		<tr><td>规格型号：</td><td><s:textfield id="model" name="checkReport.samplemodel"  data-validation="length" data-validation-length="0-50" data-validation-error-msg="字数范围在(0-50)"  readonly="true" cssClass="hiddenshorttext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>样品数量：</td><td><s:textfield id="count" name="checkReport.samplecount"  data-validation="length" data-validation-length="1-100" data-validation-error-msg="字数范围在(1-100)" readonly="true" cssClass="hiddenshorttext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>委托单位：</td><td><s:textfield id="entrustname" name="checkReport.entrustcompanyStr"  readonly="true" cssClass="hiddenlongtext"></s:textfield></td></tr>
		<tr><td>生产单位：</td><td><s:textfield id="productionunit" name="checkReport.productionunit" readonly="true" cssClass="hiddenlongtext"></s:textfield></td></tr>
		<tr><td>注册商标：</td><td><s:textfield name="checkReport.trademark" cssClass="longtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>接收日期：</td><td>
		<s:textfield id="receivedate" name="checkReport.receivedate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="true" cssClass="hiddenshorttext"></s:textfield>
		</td></tr>
		<tr><td><span class="mustfill">*</span>检验项目：</td><td><s:select id="hiddenIns"  list="instrumentList" style="display:none"></s:select></td></tr>
		<tr><td colspan="2" id="itemlist"></td></tr>
		<tr><td><span class="mustfill">*</span>检测完成日期：</td><td><s:textfield id="finishdate" name="checkReport.finishdate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly" cssClass="shorttext"></s:textfield></td></tr>
		<tr><td>委托单位备注：</td><td><s:textarea name="checkReport.remark" cssStyle="width:400px;height:110px;" data-validation="length" data-validation-length="0-200" data-validation-error-msg="字数范围在(0-200)" value="送检样品信息均由委托单位提供"></s:textarea></td></tr>
		<tr><td></td><td>（还可以输入<span id="textarea_length_max">200</span>字）</td><td></td></tr>
		<!-- 曾智琴 -->
		<tr><td>结果备注：</td><td><s:textarea name="checkReport.resultremark" cssStyle="width:400px;height:110px;" data-validation="length" data-validation-length="0-200" data-validation-error-msg="字数范围在(0-200)"></s:textarea></td></tr>
		<tr><td></td><td>（还可以输入<span id="textarea_length_max">200</span>字）</td><td></td></tr>
		<!-- 曾智琴 -->
		<tr><td>结果分析：</td><td><s:textarea name="checkReport.analyzeremark" cssStyle="width:400px;height:110px;" data-validation="length" data-validation-length="0-200" data-validation-error-msg="字数范围在(0-200)"></s:textarea></td></tr>
		<tr><td></td><td>（还可以输入<span id="textarea_length_max">200</span>字）</td><td></td></tr>
		
		
		<tr>
           	<td colspan="2" class="textdesc">注意：以上<span class="mustfill">*</span>标识的为必填项</td>
        </tr>
		<tr><td colspan="2" align="center"><s:submit value="保 存" onclick="return check()"></s:submit><input value="返 回" type="button" onclick="history.back();" /></td></tr>		
		</table>
		<!-- 张嘉兴 -->
		<span id="instrumentcode" style="display:none" name="instrumentcode"></span>
		</s:form>
        </div>
		</div>
		</div>
		<script type="text/javascript">
		$.validate({modules : 'platform'});
		$('textarea').restrictLength( $('#textarea_length_max') );
		
		set_date_picker($('#receivedate'));
		set_date_picker($('#finishdate'));

		var count = 0;  // 输入的检验项目个数

		function addCheckItems(f,e,a,b,c,nc,d,g,h,k,xh){
			var instrumentcode = $('#instrumentcode').val(c);
			console.log(instrumentcode);
		   var str = '<div style="border:1px dashed gray" id="listdiv'+xh+'"><table width="100%" >';
		   str = str + '<tr><td>样品编号 ：<br>'+k+'  <br> <a href="javascript:void(0)" onclick="removeItem(listdiv'+xh+')">移除</a></td>';
		   str = str + '<td id="listdivitem'+xh+'">';
		   
           str = str + '<div style="border:1px dashed gray" id="divitem'+count+'"><table><tr><td width="20%"><s:hidden name="code"  value="'+k+'"/><s:hidden name="checkid"  value="'+f+'"></s:hidden>检验项：</td><td><s:textfield  name="inputCheckItems" readonly="true" value="'+e+'" cssClass="hiddenlongtext"></s:textfield> </td></tr>';
		   /* if (a==2){
		   		str = str+ '<tr><td>检验依据：</td><td><s:hidden name="optvalue" id="opt'+count+'" value="2"></s:hidden><input type="radio" name="accordingtype'+count+'" id="opt2'+count+'" value="2" checked="checked" onclick="showContent('+count+')"/><label for="sampleRegiste_accordingtype2">由本中心选定合适的标准方法</label>';
           		str = str+ '<input type="radio" name="accordingtype'+count+'" id="opt3'+count+'" value="3" onclick="showContent('+count+')"/><label for="sampleRegiste_accordingtype3">同意用本中心确定的非标准方法</label>';
           		str = str +'<input type="radio" name="accordingtype'+count+'" id="opt1'+count+'"  value="1" onclick="showContent('+count+')"/><label for="sampleRegiste_accordingtype1">指定检验依据</label></td></tr>';		   
		   }
		   if (a==3){
		   		str = str+ '<tr><td>检验依据：</td><td><s:hidden name="optvalue" id="opt'+count+'" value="2"></s:hidden><input type="radio" name="accordingtype'+count+'" id="opt2'+count+'" value="2"  onclick="showContent('+count+')"/><label for="sampleRegiste_accordingtype2">由本中心选定合适的标准方法</label>';
           		str = str+ '<input type="radio" name="accordingtype'+count+'" id="opt3'+count+'" value="3" checked="checked" onclick="showContent('+count+')"/><label for="sampleRegiste_accordingtype3">同意用本中心确定的非标准方法</label>';
           		str = str +'<input type="radio" name="accordingtype'+count+'" id="opt1'+count+'"  value="1" onclick="showContent('+count+')"/><label for="sampleRegiste_accordingtype1">指定检验依据</label></td></tr>';		   
		   }
		   if (a==1){
		   		str = str+ '<tr><td>检验依据：</td><td><s:hidden name="optvalue" id="opt'+count+'" value="2"></s:hidden><input type="radio" name="accordingtype'+count+'" id="opt2'+count+'" value="2" onclick="showContent('+count+')"/><label for="sampleRegiste_accordingtype2">由本中心选定合适的标准方法</label>';
           		str = str+ '<input type="radio" name="accordingtype'+count+'" id="opt3'+count+'" value="3" onclick="showContent('+count+')"/><label for="sampleRegiste_accordingtype3">同意用本中心确定的非标准方法</label>';
           		str = str +'<input type="radio" name="accordingtype'+count+'" id="opt1'+count+'"  value="1" checked="checked" onclick="showContent('+count+')"/><label for="sampleRegiste_accordingtype1">指定检验依据</label></td></tr>';		   
		   } */
		   		   //////////////////////////////////////////////////////
		    var instrumentstr ='<input type="hidden" id="enid'+count+'" name="instrumentname" value="'+c+'" />';
		   instrumentstr = instrumentstr + '<input type="text" id="company'+count+'" onkeyup="" name="iname" value="'+nc+'"readonly="true" class="hiddenlongtext"/>';
		   instrumentstr = instrumentstr + '<div id="companyDiv'+count+'" style="margin-left: 0px;position:absolute;width: 270px;border: 1px solid black;background: white;display:none;">';
		   instrumentstr = instrumentstr +	'<ul id="companyUl'+count+'" style="line-height:24px;"></ul></div>';
		   ///////////////////////////////////////////////////////
		  /*  str = str + '<tr id="tr_accordcontent'+count+'"><td>依据内容 ： </td><td><s:textfield id="accordings'+count+'"  name="checkstandard" value="'+b+'" data-validation="length" data-validation-length="0-500" data-validation-error-msg="字数范围在(0-500)" cssClass="longtext"></s:textfield></td></tr>';
	 */   //str = str +'<tr><td>仪器名称： </td><td><select id="instrumentname'+count+'"  name="instrumentname" ></select></td></tr>';
		   str = str +'<tr><td>仪器名称： </td><td>'+instrumentstr+'</td></tr>';
		   str = str +'<tr><td>方法检出限：</td><td> <s:textfield  name="checkscope" value="'+d+'" readonly="true" data-validation="length" data-validation-length="0-500" data-validation-error-msg="字数范围在(0-500)" cssClass="hiddenlongtext"></s:textfield></td></tr>';
/* 		   str = str +'<tr><td>单位 ：</td><td> <s:textfield  name="unit" value="'+g+'"  readonly="true" data-validation="length" data-validation-length="0-20" data-validation-error-msg="字数范围在(0-20)" cssClass="hiddenlongtext"></s:textfield></td></tr>';
 */		   str = str +'<tr><td>检验标准：</td><td> <s:textfield  name="checkstandard" value="'+b+'" readonly="true" data-validation="length" data-validation-length="0-500" data-validation-error-msg="字数范围在(0-500)" cssClass="hiddenlongtext"></s:textfield></td></tr>';
		   str = str +'<tr><td><span class="mustfill">*</span>检验结果 ：</td><td> <s:textfield  name="checkresult" value="'+h+'" readonly="true"  data-validation="length" data-validation-length="0-200" data-validation-error-msg="字数范围在(0-200)" cssClass="hiddenlongtext"></s:textfield></td></tr></table></div>';
		   
		   str = str +'</td></tr></table></div> ';
		   $("#itemlist").append(str);
		   /*
           var obj = document.getElementById("hiddenIns");
		   var options = obj.options;
		   var newobj = document.getElementById("instrumentname"+count);
		   for (var i=0;i<options.length;i++){
		        var val = options[i].value;
		        var name = options[i].text;
		        var newoption = new Option(name,val);
		        newobj.options.add(newoption);
		   }
		   newobj.value=c;
		   */
		   count = count + 1;
		   
		   parent.iFrameHeight();
		}
		
		function removeItem(aObj) {
		     if (confirm("确定要删除本条数据吗？")){
		           $(aObj).remove();
		     }
		     parent.iFrameHeight();
		}
		
		function showContent(o){
	       /*
	        if ($('input:radio[name="accordingtype'+o+'"]:checked').val()!="1"){
	             $("#accordings"+o).val("无");
	             $("#tr_accordcontent"+o).hide();
	        }else{
	             $("#accordings"+o).val("");
	             $("#tr_accordcontent"+o).show();
	        }*/
	        $("#opt"+o).val($('input:radio[name="accordingtype'+o+'"]:checked').val());
	    }
		
	    function check(){
	          if ($("#selregiste").val()==""){
	              alert("请选择登记单 ！");
	              return false;
	          }
	          if ($("#receivedate").val()>$("#finishdate").val()){
	                  alert("检查完成日期不能早于接收样品日期！");
	                  return false;
	          }
	          
	          //判断仪器的选择是否正确
	          /*
	          var objs = document.getElementsByName("instrumentname");
	          for(var i=0;i<objs.length;i++){
                    if (objs[i].value==""){
                        var yq = document.getElementById("company"+i);
                        if (yq!=null){
                           alert("仪器名称输入不正确，没有名称为："+yq.value+" 的仪器，请检查！");
                           yq.focus();
                           return false;
                        }else{
                           alert("仪器名称输入不正确，请检查！");
                           return false;
                        }
                    }	          
	          }
	          */
	    }
	    function getItems(){
	       if ($("#selregiste").val()=="") return;
	       $.ajax({
	            async:false,
	            type:'get',
				url: 'getCheckItems_bakCheckReport.html',
				data:{ registeid: $("#selregiste").val(),modifyflag:0},
				dataType:'json',
				success: function(data) {
				    // 先删除原来增加的内容
	                for(var j=0;j<=count;j++){
	                     $("#listdiv"+j).remove();

	                }
				    var info = eval("("+data+")");
				    var i = 0;
				    count = 0;
				    for(var o in info){ 
				        if (o!=null&&o!="unique"){    
				            if(i==0){
				               $("#no").val(info[o].id);
				               $("#noname").val(info[o].name);
				               $("#entrustname").val(info[o].entrustname);
				               $("#productionunit").val(info[o].productunit);
				               $("#status").val(info[o].status);
				               $("#model").val(info[o].model);
				               $("#count").val(info[o].count);
				               $("#receivedate").val(info[o].receivedate);
				               //添加小编号 
				               
				            }else{
				               addCheckItems(info[o].id,info[o].name,info[o].type,info[o].standard,info[o].instrument,info[o].instrumentname,info[o].checkscope,info[o].unit,info[o].checkresult,info[o].subno,i);				            
				            }
				            i = i+1;
				        }  
				    }  
				},
				error: function(){           
					//曾智琴
		           alert('此样品有未提交项目，不能出报告！');    
		        }
			});
	    }
	    
	    function inputchange(xh){  
	       var inputparam = $("#company"+xh).val();
		   if(inputparam == ''){
				$("#companyDiv"+xh).hide();
				return;
		   }
	       var lis = "";
           var obj = document.getElementById("hiddenIns"); 
   	       for(var i=0; i<obj.options.length; i++) {  
       	        if(obj.options[i].text.indexOf(inputparam) != -1) {  
		        	lis += '<li style="cursor:pointer" onclick="selOrg(this,'+xh+')">'+obj.options[i].value+'|'+obj.options[i].text+'</li>';
                }  
	       }  
	       
	       if (lis!=""){
	          $("#companyUl"+xh).html(lis);
		      $("#companyDiv"+xh).show();
		   }else{
		      $("#enid"+xh).val("");
		      $("#companyDiv"+xh).hide();
		   }
        }
        
        function selOrg(liObj,xh){
			var html = $(liObj).html();
			if(html == '' || html  == '&nbsp'){
				return;
			}
			var arr = html.split("|");
			$("#company"+xh).val(arr[0]+"--"+arr[1]);
			$("#enid"+xh).val(arr[0]);
			$("#companyDiv"+xh).hide();
		}
		</script>
	</body>
</html>
