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
		<h1>修改检验报告</h1>
		<s:form action="updateCheckReport" namespace="/business">
		<table class="frmtable">
		<s:hidden name="checkReport.id"></s:hidden>
		<tr><td><span class="mustfill">*</span>检测报告编号：</td><td><s:textfield id="no" name="checkReport.sampleno" data-validation="length" data-validation-length="1-50" data-validation-error-msg="字数范围在(1-50)" readonly="true" cssClass="hiddenlongtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>样品名称：</td><td><s:textfield id="noname" name="checkReport.samplename"  data-validation="length" data-validation-length="1-500" data-validation-error-msg="字数范围在(1-500)" readonly="true" cssClass="hiddenlongtext"></s:textfield></td></tr>
		<tr><td>样品状态：</td><td><s:textfield id="status" name="checkReport.samplestatus"  data-validation="length" data-validation-length="0-50" data-validation-error-msg="字数范围在(0-50)" readonly="true" cssClass="hiddenshorttext"></s:textfield></td></tr>
		<tr><td>规格型号：</td><td><s:textfield id="model" name="checkReport.samplemodel"  data-validation="length" data-validation-length="0-50" data-validation-error-msg="字数范围在(0-50)" readonly="true" cssClass="hiddenshorttext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>样品数量：</td><td><s:textfield id="count" name="checkReport.samplecount"  data-validation="length" data-validation-length="1-100" data-validation-error-msg="字数范围在(1-100)" readonly="true" cssClass="hiddenshorttext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>委托单位：</td><td><s:textfield id="entrustname" name="checkReport.entrustcompany"  readonly="true" cssClass="hiddenlongtext"></s:textfield></td></tr>
		<tr><td>生产单位：</td><td><s:textfield id="productionunit" name="checkReport.productionunit" readonly="true" cssClass="hiddenlongtext"></s:textfield></td></tr>
		<tr><td>注册商标：</td><td><s:textfield name="checkReport.trademark" cssClass="longtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>接收日期：</td><td>
		<s:textfield id="receivedate" name="checkReport.receivedate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="true" cssClass="hiddenshorttext">
		<s:param name="value"><s:date name="checkReport.receivedate" format="yyyy-MM-dd"/></s:param></s:textfield>
		</td></tr>
		<tr><td><span class="mustfill">*</span>检验项目：</td><td><s:select id="hiddenIns"  list="instrumentList" style="display:none"></s:select></td></tr>
		<tr><td colspan="2" id="itemlist"><s:select id="hiddensel" list="instrumentList"  style="display:none"></s:select>
		<s:iterator value="checkResultList" status="st">
		<div style="border:1px dashed gray" id="listdiv'${st.index}"><table>
		<tr><td>样品编号 ：<br>${subsendsampleno}  <br> <a href="javascript:void(0)" onclick="removeItem(listdiv${st.index})">移除</a></td>
		   <td id="listdivitem${st.index}">
		    <div style="border:1px dashed gray" ><table>
		           <tr><td width="20%"><s:hidden name="code"  value="%{subsendsampleno}"/><s:hidden name="checkid"  value="%{checkitem}"></s:hidden>检验项：<s:textfield  name="inputCheckItems" readonly="true" value="%{checkitemname}" cssClass="hiddenlongtext"></s:textfield></td></tr>
		           <%-- <s:if test='type=="2"'>
		              <tr><td><s:hidden name="optvalue" id="opt%{#st.index}" value="2"></s:hidden><input type="radio" name="accordingtype${st.index}" id="opt2${st.index}" value="2" checked="checked" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype2">由本中心选定合适的标准方法</label><input type="radio" name="accordingtype${st.index}" id="opt3${st.index}"  value="3" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype3">同意用本中心确定的非标准方法</label><input type="radio" name="accordingtype${st.index}" id="opt1${st.index}"  value="1" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype1">指定检验依据</label></td></tr>
		              <tr id="tr_accordcontent${st.index}" ><td> 依据： <s:textfield id="according%{st.index}"  name="checkstandard" value="%{standard}" data-validation="length" data-validation-length="1-500" data-validation-error-msg="字数范围在(1-500)"  cssClass="longtext"></s:textfield></td></tr>
		           </s:if>
		           <s:if test='type=="3"'>
		              <tr><td><s:hidden name="optvalue" id="opt%{#st.index}"  value="3"></s:hidden><input type="radio" name="accordingtype${st.index}" id="opt3${st.index}" value="2" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype2">由本中心选定合适的标准方法</label><input type="radio" name="accordingtype${st.index}" id="opt3${st.index}" checked="checked" value="3" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype3">同意用本中心确定的非标准方法</label><input type="radio" name="accordingtype${st.index}" id="opt1${st.index}" value="1" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype1">指定检验依据</label></td></tr>
		              <tr id="tr_accordcontent${st.index}" ><td> 依据： <s:textfield id="according%{st.index}"  name="checkstandard" value="%{standard}" data-validation="length" data-validation-length="1-500" data-validation-error-msg="字数范围在(1-500)"  cssClass="longtext"></s:textfield></td></tr>
		           </s:if>
		           <s:if test='type=="1"'>
		              <tr><td><s:hidden name="optvalue" id="opt%{#st.index}"  value="1"></s:hidden><input type="radio" name="accordingtype${st.index}" id="opt1${st.index}" value="2" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype2">由本中心选定合适的标准方法</label><input type="radio" name="accordingtype${st.index}" id="opt3${st.index}" value="3" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype3">同意用本中心确定的非标准方法</label><input type="radio" name="accordingtype${st.index}" id="opt1${st.index}" checked="checked" value="1" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype1">指定检验依据</label></td></tr>
		              <tr id="tr_accordcontent${st.index}"><td> 依据： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield id="according%{st.index}"  name="checkstandard" value="%{standard}" data-validation="length" data-validation-length="1-500" data-validation-error-msg="字数范围在(1-500)"  cssClass="longtext"></s:textfield></td></tr>
		           </s:if> --%>
<%-- 		           <tr id="tr_accordcontent${st.index}"><td> 检验标准： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield id="according%{st.index}"  name="checkstandard" value="%{standard}" data-validation="length" data-validation-length="1-500" data-validation-error-msg="字数范围在(1-500)"  cssClass="longtext"></s:textfield></td></tr>
 --%>		          
		           <tr><td>仪器名称：
		                  <s:textfield type="hidden" id="enid%{#st.index}" name="instrumentname" value="%{instrumentcode}" />
						  <s:textfield type="text" id="company%{#st.index}" onkeyup="inputchange('%{#st.index}')" name="iname" value="%{instrumentname}" class="longtext"/><span class="textdesc"> 输入查询</span>
						    <div id="companyDiv${st.index}" style="margin-left: 0px;position:absolute;width: 270px;border: 1px solid black;background: white;display:none;">
								<ul id="companyUl${st.index}" style="line-height:24px;">
								</ul>
							</div>
		           </td></tr>
		           <tr><td> <span class="mustfill">*</span>方法检出限： <s:textfield  name="checkscope"  data-validation="length" data-validation-length="1-50" data-validation-error-msg="字数范围在(1-50)"  cssClass="longtext"></s:textfield></td></tr>
		           <tr><td> 单位： &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield  name="unit" data-validation="length" data-validation-length="0-20" data-validation-error-msg="字数范围在(0-20)"  cssClass="longtext"></s:textfield></td></tr>
		           <tr><td> <span class="mustfill">*</span>检验结果：&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea  name="checkresult" data-validation="length" data-validation-length="1-200" data-validation-error-msg="字数范围在(1-200)"   cssStyle="width:400px;height:40px;"></s:textarea></td></tr>
		     </td></tr></table></div>
		   </table></div>
		</s:iterator>
		</td></tr>
		<tr><td><span class="mustfill">*</span>检测完成日期：</td><td><s:textfield id="finishdate" name="checkReport.finishdate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly" cssClass="shorttext">
		<s:param name="value"><s:date name="checkReport.finishdate" format="yyyy-MM-dd"/></s:param></s:textfield></td></tr>
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

		function addCheckItems(f,e,a,b,c,d,g,h,k,xh){
		   var str = '<div style="border:1px dashed gray" id="listdiv'+xh+'"><table>';
		   str = str + '<tr><td>样品编号 ：<br>'+k+'  <br> <a href="javascript:void(0)" onclick="removeItem(listdiv'+xh+')">移除</a></td>';
		   str = str + '<td id="listdivitem'+xh+'">';

		   str = str + '<div style="border:1px dashed gray" id="divitem'+count+'"><table><tr><td><s:hidden name="checkid"  value="'+f+'"></s:hidden>检验项：</td><td><s:textfield  name="inputCheckItems" readonly="true" value="'+e+'" cssClass="hiddenlongtext"></s:textfield> </td></tr>';
		   
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
		   
		   //str = str+ '<tr><td>检验依据：</td><td><s:hidden name="optvalue" id="opt'+count+'" value="1"></s:hidden><input type="radio" name="accordingtype'+count+'" id="opt2'+count+'" value="2"  onclick="showContent('+count+')"/><label for="sampleRegiste_accordingtype2">由本中心选定合适的标准方法</label>';
           str = str+ '<input type="radio" name="accordingtype'+count+'" id="opt3'+count+'" value="3" onclick="showContent('+count+')"/><label for="sampleRegiste_accordingtype3">同意用本中心确定的非标准方法</label>';
           str = str +'<input type="radio" name="accordingtype'+count+'" id="opt1'+count+'" checked="checked" value="1" onclick="showContent('+count+')"/><label for="sampleRegiste_accordingtype1">指定检验依据</label></td></tr>';		   
		   str = str + '<tr id="tr_accordcontent'+count+'"><td>依据内容 ： </td><td><s:textfield id="accordings'+count+'"  name="checkstandard" data-validation="length" data-validation-length="0-500" data-validation-error-msg="字数范围在(0-500)" cssClass="longtext"></s:textfield></td></tr>';
		   str = str +'<tr><td>仪器名称： </td><td><select id="instrumentname'+count+'"  name="instrumentname" ></select></td></tr>';
		   str = str +'<tr><td>方法检出限：</td><td> <s:textfield  name="checkscope" data-validation="length" data-validation-length="0-500" data-validation-error-msg="字数范围在(0-500)" cssClass="shorttext"></s:textfield></td></tr>';
		   str = str +'<tr><td>单位 ：</td><td> <s:textfield  name="unit" data-validation="length" data-validation-length="0-20" data-validation-error-msg="字数范围在(0-20)" cssClass="shorttext"></s:textfield></td></tr>';
		   str = str +'<tr><td><span class="mustfill">*</span>检验结果 ：</td><td> <s:textfield  name="checkresult" data-validation="length" data-validation-length="0-200" data-validation-error-msg="字数范围在(0-200)" cssClass="longtext"></s:textfield></td></tr></table></div>';
		   
		   str = str +'</td></tr></table></div> ';
		   $("#itemlist").append(str);
           var obj = document.getElementById("hiddenIns");
		   var options = obj.options;
		   var newobj = document.getElementById("instrumentname"+count);
		   for (var i=0;i<options.length;i++){
		        var val = options[i].value;
		        var name = options[i].text;
		        var newoption = new Option(name,val);
		        newobj.options.add(newoption);
		   }
		   count = count + 1;
		   parent.iFrameHeight();
		}
		
		function showContent(o){
		    if ($('input:radio[name="accordingtype'+o+'"]:checked').val()!="1"){
	             $("#accordings"+o).val("无");
	             $("#tr_accordcontent"+o).hide();
	        }else{
	             $("#accordings"+o).val("");
	             $("#tr_accordcontent"+o).show();
	        }
	        $("#opt"+o).val($('input:radio[name="accordingtype'+o+'"]:checked').val());
	    }

	    function check(){
// 	          if ($("#selregiste").val()==""){
// 	              alert("请选择登记单 ！");
// 	              return false;
// 	          }
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
	       //清空原来的内容
	       $("#itemlist").empty();
	       $.ajax({
	            async:false,
	            type:'get',
				url: 'getCheckItemsCheckReport.html',
				data:{ registeid: $("#selregiste").val(),modifyflag:0},
				dataType:'json',
				success: function(data) {
				    // 先删除原来增加的内容
	                for(var j=0;j<count;j++){
	                     $("#divitem"+j).remove();
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
				            }else{
				               //addCheckItems(info[o].id,info[o].name);
				               addCheckItems(info[o].id,info[o].name,info[o].type,info[o].standard,info[o].instrument,info[o].checkscope,info[o].unit,info[o].checkresult,info[o].subno,i);
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
	    
	    function inputchange(xh){  
	       var inputparam = $("#company"+xh).val();
		   if(inputparam == ''){
				$("#companyDiv"+xh).hide();
				return;
		   }
	       var lis = "";
           var obj = document.getElementById("hiddensel"); 
   	       for(var i=0; i<obj.options.length; i++) {  
       	        if(obj.options[i].text.indexOf(inputparam) != -1) {  
		        	lis += '<li style="cursor:pointer" onclick="selOrg(this,'+xh+')">'+obj.options[i].value+'|'+obj.options[i].text+'</li>';
                }  
	       }  
	       
	       if (lis!=""){
	          $("#companyUl"+xh).html(lis);
		      $("#companyDiv"+xh).show();
		   }else{
		      $("#companyDiv"+xh).hide();
		      $("#enid"+xh).val("");
		   }
        }
        
        function selOrg(liObj,obj){
			var html = $(liObj).html();
			if(html == '' || html  == '&nbsp'){
				return;
			}
			var arr = html.split("|");
			$("#company"+obj).val(arr[0]+"--"+arr[1]);
			$("#enid"+obj).val(arr[0]);
			$("#companyDiv"+obj).hide();
		}
		</script>
	</body>
</html>