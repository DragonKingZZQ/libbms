<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<link href="<%=path %>/css/layui.css" rel="stylesheet" type="text/css" />
		<script src="<%=path %>/js/layui.js" charset="utf-8"></script>
		<link href="<%=path %>/css/jquery.datepicker.css" rel="stylesheet" type="text/css" />
	</head>

	<body onload="myfunction()">
	<!-- 曾智琴 -->
	<%-- <s:hidden id="hiddenvalue" value="%{sendSample.statusname}"></s:hidden> --%>
	<span id="hiddenvalue" style="display:none">${sendSample.returnvalue}</span>
	<a id="${sendSample.id}_1" style="display:none" href="/libbms/business/export1SendSample.html?sendSample.id=${sendSample.id}">1.TY-001通用</a>
 	<a id="${sendSample.id}_2" style="display:none" href="/libbms/business/export2SendSample.html?sendSample.id=${sendSample.id}">2.导出离子</a>
	<a id = "returnback" style="display:none" href="/libbms/business/send/listSendSample.html">返回按钮</a>
	<s:include value="/include/common.jsp"></s:include>
	<div id="pagebody">
	<div id="mainbody">
	<div id="contentbar">
		<h1>派样单结果填报</h1>
		<s:form action="checkfinishsaveSendSample" namespace="/business" name="upload" enctype="multipart/form-data" method="post">
		<table class="frmtable">
		
		<s:hidden name="sendSample.id" id="sendSampleId"></s:hidden>
		<s:hidden name="sendSample.finishflag" value="1"></s:hidden>
		<tr><td width="18%">样品登记单号：</td><td>${sendSample.sendsampleno}</td></tr>
		<tr><td>样品名称：</td><td>${sendSample.sendsamplename}</td></tr>
		<tr><td>样品数量：</td><td>${sendSample.samplecount}</td></tr>
		<tr><td>存储条件：</td><td><s:checkboxlist list="#{'1':'常温','2':'冷藏','3':'冷冻','4':'避光','5':'干燥'}" name="sendSample.storecondition" value="storeList" disabled="true"/></td></tr>
		<tr><td>检验项目：</td><td></td></tr>
		<tr><td  colspan="2">
		<s:iterator value="subno" status="sub" id="s">
		   <div style="border:1px dashed gray"><table>
		      <tr><td>样品编号 ：<br>${s}  <br></td><td>
				  <s:iterator value="sciList" status="st">
				    <s:if test='#s==subsendsampleno'>
					    <div style="border:1px dashed gray"><table><tr><td><strong>检验项目：</strong><s:property value="checkitemname"/></td></tr>
					   <tr><td><strong>检验标准：</strong> ${sendSample.checkstandardname}</td></tr>
					    <%-- <tr><td><strong>检验依据：</strong> 
					    <s:if test='type=="2"'>
					                    由本中心选定合适的标准方法
					    </s:if>
					    <s:if test='type=="3"'>
					                    同意用本中心确定的非标准方法
					    </s:if>
					    <s:if test='type=="1"'>
					                    指定检验依据
					    </s:if>
					    </td></tr>
				        <tr><td><strong>依据：</strong><s:property value="standard"/></td></tr> --%>
					    </table></div>
					    </s:if>
					</s:iterator>
					</td></tr>
			</table></div>	  
		</s:iterator>
		</td></tr>
		<tr><td>要求开始日期：</td><td><s:date name="sendSample.startdate" format='yyyy-MM-dd'/></td></tr> 
		<tr><td>要求完成日期：</td><td><s:date name="sendSample.enddate" format='yyyy-MM-dd'/></td></tr>
        <tr><td>检验人员：</td><td>${sendSample.examineuserStr}</td></tr>
		<tr><td>收样人：</td><td>${sendSample.receiveuserStr}</td></tr>
		<tr><td>收样日期：</td><td><s:date name="sendSample.receivedate" format='yyyy-MM-dd'/></td></tr>
        <tr  style="background:#DDD" height="30"><td colspan="2">请填写以下内容：</td></tr>
<%--         <tr id="tr_input_address"><td><span class="mustfill">*</span>实际检验人：</td><td><s:select name="sendSample.finishexaminuser" list="map" cssClass="shortext"></s:select></td></tr>
 --%>		
 		<tr id="tr_input_relation"><td><span class="mustfill">*</span>检验日期：</td><td><s:textfield id="finishdate" name="sendSample.finishdate"  value="%{sendSample.finishdate}" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly"  cssClass="shorttext">
		<s:param name="value"><s:date name="sendSample.finishdate" format="yyyy-MM-dd"/></s:param></s:textfield></td></tr>
        <tr><td>检验结果：</td><td></td></tr>
        <tr><td id="itemlist" colspan="2">
		<s:iterator value="mysubno" status="sub" id="s2">
		   <div style="border:1px dashed gray" id="listdiv${sub.index}"><table>
		      <tr><td>样品编号 ：<br>${s2}  <br></td><td id="listdivitem${sub.index}">
				  <s:iterator value="sciList" status="st">
				    <s:if test='#s2==subsendsampleno'>
					    <div id="div${st.index}" style="border:1px dashed gray"><table>
					         <tr><td colspan=2><s:hidden name="code" value="%{subsendsampleno}"/><s:hidden name="checkid" value="%{id}"></s:hidden>检验项目：<s:textfield  name="inputCheckItems" value="%{checkitemname}" readonly="true" cssClass="hiddenlongtext"></s:textfield></td></tr>
					        
					        
					         <s:if test='type=="2"'>
					              <tr ><td colspan=2><s:hidden name="optvalue" id="opt%{#st.index}" value="2"></s:hidden><input type="radio" name="sampleRegiste.accordingtype${st.index}" id="opt2${st.index}" value="2" checked="checked" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype2">由本中心选定合适的标准方法</label><input type="radio" name="sampleRegiste.accordingtype${st.index}" id="opt3${st.index}"  value="3" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype3">同意用本中心确定的非标准方法</label><input type="radio" name="sampleRegiste.accordingtype${st.index}" id="opt1${st.index}"  value="1" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype1">指定检验依据</label></td></tr>
					              <tr id="tr_accordcontent${st.index}" style="display:none;"><td> 依据：</td><td> <s:textfield  name="inputAccording" value="%{standard}" data-validation="length" data-validation-length="1-500" data-validation-error-msg="字数范围在(1-500)"  cssClass="longtext"></s:textfield></td></tr>
					         </s:if>
					         <s:if test='type=="3"'>
					              <tr><td colspan=2><s:hidden name="optvalue" id="opt%{#st.index}"  value="3"></s:hidden><input type="radio" name="sampleRegiste.accordingtype${st.index}" id="opt2${st.index}" value="2" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype2">由本中心选定合适的标准方法</label><input type="radio" name="sampleRegiste.accordingtype${st.index}" id="opt3${st.index}" checked="checked" value="3" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype3">同意用本中心确定的非标准方法</label><input type="radio" name="sampleRegiste.accordingtype${st.index}" id="opt1${st.index}" value="1" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype1">指定检验依据</label></td></tr>
					              <tr id="tr_accordcontent${st.index}" style="display:none;"><td> 依据：</td><td> <s:textfield  name="inputAccording" value="%{standard}" data-validation="length" data-validation-length="1-500" data-validation-error-msg="字数范围在(1-500)"  cssClass="longtext"></s:textfield></td></tr>
					         </s:if>
					         <s:if test='type=="1"'>
					              <tr><td colspan=2><s:hidden name="optvalue" id="opt%{#st.index}"  value="1"></s:hidden><input type="radio" name="sampleRegiste.accordingtype${st.index}" id="opt2${st.index}" value="2" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype2">由本中心选定合适的标准方法</label><input type="radio" name="sampleRegiste.accordingtype${st.index}" id="opt3${st.index}" value="3" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype3">同意用本中心确定的非标准方法</label><input type="radio" name="sampleRegiste.accordingtype${st.index}" id="opt1${st.index}" checked="checked" value="1" onclick="showContent(${st.index})"/><label for="sampleRegiste_accordingtype1">指定检验依据</label></td></tr>
					              <tr id="tr_accordcontent${st.index}"><td> 依据： </td><td><s:textfield  name="inputAccording" value="%{standard}" data-validation="length" data-validation-length="1-500" data-validation-error-msg="字数范围在(1-500)"  cssClass="longtext"></s:textfield></td></tr>
					         </s:if>
					         <s:select id="hiddensel" list="instrumentList"  style="display:none"></s:select>											        
					         <tr><td> <span class="mustfill">*</span>检验仪器：</td><td>
					                <s:textfield type="hidden" id="enid%{#st.index}" name="instrumentname" value="%{instrumentcode}" />
									<s:textfield type="text" id="company%{#st.index}" onkeyup="inputchange('%{#st.index}')" name="iname" value="%{instrumentname}" class="longtext"/><span class="textdesc"> 输入查询</span>
									 <div id="companyDiv${st.index}" style="margin-left: 0px;position:absolute;width: 270px;border: 1px solid black;background: white;display:none;">
											<ul id="companyUl${st.index}" style="line-height:24px;">
											</ul>
									 </div>
					         </td></tr>
					          <!-- 曾智琴 -->
							<s:select  id="hiddensel2"  list="labprocesstitlesmap" style="display:none" ></s:select>							
					      	<tr><td> <span class="mustfill">*</span>实验过程：</td><td>
					              <s:textfield type="hidden" id="enidlab%{#st.index}" name="labprocesstitle" value="%{labprocesstitle}" />
								  <s:textfield type="text" id="companylab%{#st.index}" onkeyup="inputchangelab('%{#st.index}')" name="labprocess" value="%{labprocess}" class="longtext"/><span class="textdesc"> 输入查询</span>
								  <div id="companyDivlab${st.index}" style="margin-left: 0px;position:absolute;width: 270px;border: 1px solid black;background: white;display:none;">
										<ul id="companyUllab${st.index}" style="line-height:24px;">
										</ul>
								 </div>
					         </td></tr>
					         
					          <!-- 曾智琴 -->
							<s:select  id="hiddensel3"  list="instrumentList" style="display:none" ></s:select>							
					      	<tr><td> <span class="mustfill">*</span>天平：</td><td>
					              <s:textfield type="hidden" id="enidbalance%{#st.index}" name="balancetitle" value="%{balancetitle}" />
								  <s:textfield type="text" id="companybalance%{#st.index}" onkeyup="inputchangebalance('%{#st.index}')" name="balance" value="%{balance}" class="longtext"/><span class="textdesc"> 输入查询</span>
								  <div id="companyDivbalance${st.index}" style="margin-left: 0px;position:absolute;width: 270px;border: 1px solid black;background: white;display:none;">
										<ul id="companyUlbalance${st.index}" style="line-height:24px;">
										</ul>
								 </div>
					         </td></tr>
					         <tr><td> 天平精度： </td><td><s:textfield  name="balanceaccuracy" data-validation="length" data-validation-length="0-20" data-validation-error-msg="字数范围在(0-20)"  cssClass="longtext"></s:textfield></td></tr>
					         <tr><td> <span class="mustfill">*</span>实验室环境温度： </td><td><s:textfield  id="temperature" name="temperature"  data-validation="length" data-validation-length="1-50" data-validation-error-msg="字数范围在(1-50)"  cssClass="longtext"></s:textfield></td></tr>
					         <tr><td> <span class="mustfill">*</span>实验室环境湿度： </td><td><s:textfield  id="humidity" name="humidity"  data-validation="length" data-validation-length="1-50" data-validation-error-msg="字数范围在(1-50)"  cssClass="longtext"></s:textfield></td></tr>					         
					         <tr><td> <span class="mustfill">*</span>方法检出限： </td><td><s:textfield  name="checkscope"  data-validation="length" data-validation-length="1-50" data-validation-error-msg="字数范围在(1-50)"  cssClass="longtext"></s:textfield></td></tr>
					        <%--  <tr><td> 单位： </td><td><s:textfield  name="unit" data-validation="length" data-validation-length="0-20" data-validation-error-msg="字数范围在(0-20)"  cssClass="longtext"></s:textfield></td></tr> --%>
					        <%-- <tr><td> <span class="mustfill">*</span>检验结果：</td><td><s:textarea  name="result" data-validation="length" data-validation-length="1-200" data-validation-error-msg="字数范围在(1-200)"   cssStyle="width:380px;height:40px;"></s:textarea></td></tr>  --%>
					   		<tr><td>检验结果：</td><td><s:textarea  name="result"  value="%{checkresult}"  cssStyle="width:380px;height:40px;"></s:textarea></td></tr>
					   </table></div>
					   </s:if>
					</s:iterator>
					</td></tr>
			</table></div>	  
		</s:iterator>
		</td></tr>
	   <%--  <tr><td>备注：</td><td><s:textarea name="sendSample.remark" cssStyle="width:400px;height:110px;" data-validation="length" data-validation-length="0-200" data-validation-error-msg="字数范围在(0-200)" ></s:textarea></td></tr>
		<tr><td></td><td>（还可以输入<span id="textarea_length_max">200</span>字）</td><td></td></tr> --%>
		<!-- 曾智琴 -->
	  	 <tr><td>备注：</td><td><s:textarea name="sendSample.remark" cssStyle="width:400px;height:110px;"></s:textarea></td></tr>
	    
	    <!-- 曾智琴 -->
	   	<tr >
	   		<td>检测图片:</td>
	   			
	   		
	   			<td id="more" >
					<s:file name="file" value="选择图片："></s:file>
					<input type="button" value="全部清空" onclick="deleteAll()" style="margin-bottom: 10px;margin-left: -4.5px" >
				</td>
				
	   	</tr> <!-- 李沅罡 -->
	    <tr>
	    	<td colspan="3">
				<input type="button" value="更多" onclick="addMore()" style="margin-left: 140px;" id="moreAddPicture0">
			</td>
	   	</tr>	
	    
	    <tr>
           	<td colspan="2" class="textdesc">注意：以上<span class="mustfill">*</span>标识的为必填项</td>
        </tr>
      	  <!-- 曾智琴 -->
        <s:if test='sendSample.finishflag >="1"'>
        	<tr><td colspan="2" align="center"><input value="返 回" type="button" onclick="returnback();"/> <input type="button" onclick="exportsomething(this)" style="margin-left:6px;" id="${sendSample.id}" class="export" value="导出"/></td></tr>
		</s:if>
		<s:else>
			<tr><td colspan="4" align="center"><s:submit value="保 存" onclick="return check()"></s:submit><input value="返 回" type="button" onclick="returnback();" /><button onclick="commit()" style="margin-left:6px;">提交</button><input style="margin-left:6px;" type="button" onclick="exportsomething(this)" id="${sendSample.id}" class="export" value="导出"/></td></tr>		
		</s:else>
		</table>
		</s:form>
		
        </div>
		</div>
		</div>
		
		<script type="text/javascript">
		var i=0; //李沅罡
		$.validate({modules : 'platform'});
		set_date_picker($('#finishdate'));
		
		function myfunction(){
			var a = $('#hiddenvalue').text();
			if(a != "")
				alert(a);
			else{
				
			}
		}
		
		function showContent(o){
	        if ($('input:radio[name="sampleRegiste.accordingtype'+o+'"]:checked').val()!="1"){
	             $("#accordings").val("无");
	             $("#tr_accordcontent"+o).hide();
	        }else{
	             $("#accordings").val("");
	             $("#tr_accordcontent"+o).show();
	        }
	        $("#opt"+o).val($('input:radio[name="sampleRegiste.accordingtype'+o+'"]:checked').val());
	    }
	    
	    function check(){
	    	  /* if(!confirm('是否确认输入的结果，保存后不能再修改！')) return false; */   //曾智琴
	          //判断仪器的选择是否正确
	          var objs = document.getElementsByName("instrumentname");
	          for(var i=0;i<objs.length;i++){
	        	  	var yq = document.getElementById("company"+i);
                    if (objs[i].value==""){
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
	          var objg = document.getElementsByName("labprocesstitle");
	       
	          for(var i=0;i<objg.length;i++){
	        	  	var yg = document.getElementById("companylab"+i);
                  if (objg[i].value==""){
                      if (yg!=null){
                         alert("实验过程输入不正确，没有名称为："+yg.value+" 的过程，请检查！");
                         yg.focus();
                         return false;
                      }else{
                         alert("实验过程输入不正确，请检查！");
                         return false;
                      }
                  }	          
	          }
	          var objb = document.getElementsByName("instrumentname");
		       
	          for(var i=0;i<objb.length;i++){
	        	  	var yb = document.getElementById("companybalance"+i);
                  if (objb[i].value==""){
                      if (yb!=null){
                         alert("天平输入不正确，没有名称为："+yb.value+" 的仪器，请检查！");
                         yb.focus();
                         return false;
                      }else{
                         alert("天平输入不正确，请检查！");
                         return false;
                      }
                  }	          
	          }
	          
	          if(document.getElementById("companybalance"+0).value==""){
	        	  alert("天平名称不能为空！");
	        	  return false;
	          }
	          if(document.getElementById("companylab"+0).value==""){
	        	  alert("实验过程不能为空！");
	        	  return false;
	          }
	          
	          if($("#temperature").val()==""){
	        	  alert("实验室环境温度不能为空！");
	        	  return false;
	          }
	          if($("#humidity").val()==""){
	        	  alert("实验室环境湿度不能为空！");
	        	  return false;
	          }
	          
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
			$("#company"+obj).val(arr[1]);
			$("#enid"+obj).val(arr[0]);
			$("#companyDiv"+obj).hide();
		}
        
        /*曾智琴  */
        function inputchangelab(xh){  
 	       var inputparam = $("#companylab"+xh).val();
 		   if(inputparam == ''){
 				$("#companyDivlab"+xh).hide();
 				return;
 		   }
 	       var lis = "";
            var obj = document.getElementById("hiddensel2"); 
    	       for(var i=0; i<obj.options.length; i++) {  
        	        if(obj.options[i].text.indexOf(inputparam) != -1) {  
 		        	lis += '<li style="cursor:pointer" onclick="selOrglab(this,'+xh+')">'+obj.options[i].value+'|'+obj.options[i].text+'</li>';
                 }  
 	       }  
 	       
 	       if (lis!=""){
 	          $("#companyUllab"+xh).html(lis);
 		      $("#companyDivlab"+xh).show();
 		   }else{
 		      $("#companyDivlab"+xh).hide();
 		      $("#enidlab"+xh).val("");
 		   }
         }
        
        function selOrglab(liObj,obj){
			var html = $(liObj).html();
			if(html == '' || html  == '&nbsp'){
				return;
			}
			var arr = html.split("|");
			console.log(arr[1]);  //曾智琴
			console.log(obj);
			$("#companylab"+obj).val(arr[1]);
			$("#enidlab"+obj).val(arr[0]);
			$("#companyDivlab"+obj).hide();
		}
        //曾智琴
        function addMore()
         {
             var td = document.getElementById("more");
             
             var br = document.createElement("br");
             var input = document.createElement("input");
             var button = document.createElement("input");
             
             input.type = "file";
             input.name = "file";
             
             button.type = "button";
             button.value = "删除";
             /* 李沅罡*/
             i++;
             input.id="moreAddPicture"+i;
             button.id="deleteButton"+i;
             br.id="addMorebr"+i;
             input.style="margin-bottom: 10px;";
             /* 李沅罡 */
             button.onclick = function()
             {
                 td.removeChild(br);
                 td.removeChild(input);
                 td.removeChild(button);
            }
             td.appendChild(br);
             td.appendChild(input);
             td.appendChild(button);
         }
      //李沅罡
        function deleteAll()
        {
        	var td = document.getElementById("more");
        	while(i>=1){
        		var input = document.getElementById("moreAddPicture"+i);
        		var button = document.getElementById("deleteButton"+i);
        		var br = document.getElementById("addMorebr"+i);
        		td.removeChild(br);
                td.removeChild(input);
               	td.removeChild(button);
                i--;
        	}
        	
            
        }
      
        /*曾智琴  */
        function inputchangebalance(xh){  
 	       var inputparam = $("#companybalance"+xh).val();
 		   if(inputparam == ''){
 				$("#companyDivbalance"+xh).hide();
 				return;
 		   }
 	       var lis = "";
            var obj = document.getElementById("hiddensel3"); 
    	       for(var i=0; i<obj.options.length; i++) {  
        	        if(obj.options[i].text.indexOf(inputparam) != -1) {  
 		        	lis += '<li style="cursor:pointer" onclick="selOrgbalance(this,'+xh+')">'+obj.options[i].value+'|'+obj.options[i].text+'</li>';
                 }  
 	       }  
 	       
 	       if (lis!=""){
 	          $("#companyUlbalance"+xh).html(lis);
 		      $("#companyDivbalance"+xh).show();
 		   }else{
 		      $("#companyDivbalance"+xh).hide();
 		      $("#enidlab"+xh).val("");
 		   }
         }
        
        function selOrgbalance(liObj,obj){
			var html = $(liObj).html();
			if(html == '' || html  == '&nbsp'){
				return;
			}
			var arr = html.split("|");
			console.log(arr[1]);  //曾智琴
			console.log(obj);
			$("#companybalance"+obj).val(arr[1]);
			$("#enidbalance"+obj).val(arr[0]);
			$("#companyDivbalance"+obj).hide();
		}
        
        //曾智琴
        function commit(){        	
        	$.ajax({
	            
	            type:'get',
				url: 'checkcommitSendSample.html',
				data:{ sendsampleid: $("#sendSampleId").val()},
				success: function(data) {
				    /*  alert("提交成功！"); */
				},
				error: function(){                         
		           alert('提交失败了！');    
		        }
			});
        }
	    </script>
	  
<script type="text/javascript">
layui.use('layer', function(){ //独立版的layer无需执行这一句
	  var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
});


function returnback(){
	var mya = document.getElementById('returnback');
	mya.click();
	
}

function exportsomething(){
	var myid = $("#sendSampleId").val();
	console.log(myid+"id的值");
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
}
</script>
	</body>
</html>