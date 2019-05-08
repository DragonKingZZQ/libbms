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
		<h1>修改样品登记</h1>
		<s:form action="updateSampleRegiste" namespace="/business">
		<table class="frmtable">
		<s:hidden id="sampleid" name="sampleRegiste.id"></s:hidden>
		<tr><td colspan="2" align="center" bgcolor="#d6e4ed"><b>客户信息</b></td></tr>
		<tr><td><span class="mustfill">*</span>委托单位：</td><td>
			  <s:select  list="entrustcompanyList"  id="hiddensel" style="display:none"></s:select>
			  <input type="hidden" id="enid" name="sampleRegiste.entrustcompanyid" value="${sampleRegiste.entrustcompany}"/>
			  <input type="text" id="adminAreaCode" onkeyup="inputchange()" name="sampleRegiste.entrustcompany" value="${sampleRegiste.entrustcompanyStr}" class="longtext"/><span class="textdesc"> 输入查询</span>
			    <div id="orgDataDiv" style="margin-left: 0px;position:absolute;width: 270px;border: 1px solid black;background: white;display:none;">
					<ul id="orgDataUl" style="line-height:24px;">
					</ul>
				</div>
		</td></tr>
		<tr><td>生产单位：</td><td><s:textfield name="sampleRegiste.productionunit" data-validation="length" data-validation-length="0-200" data-validation-error-msg="字数范围在(0-200)" cssClass="longtext"></s:textfield></td></tr>
		<tr id="tr_input_user"><td><span class="mustfill">*</span>送样人：</td><td>
		        <s:select  list="userList"  id="hiddenseluser"  style="display:none"></s:select>
			    <s:textfield type="hidden" id="inputuser" name="sampleRegiste.senduserid" />
			    <s:textfield id="user" onkeyup="inputusername()" name="sampleRegiste.senduser" class="longtext"/><span class="textdesc"> 输入查询</span>
			    <div id="userDiv" style="margin-left: 0px;position:absolute;width: 270px;border: 1px solid black;background: white;display:none;">
					<ul id="userUl" style="line-height:24px;">
					</ul>
				</div>
		</td></tr>
		<tr id="tr_input_address"><td><span class="mustfill">*</span>联系地址：</td><td><s:textfield name="sampleRegiste.address" id="inputaddress"  data-validation="length" data-validation-length="1-500" data-validation-error-msg="字数范围在(1-500)"  cssClass="longtext"></s:textfield></td></tr>
		<tr id="tr_input_relation"><td><span class="mustfill">*</span>联系方式：</td><td><s:textfield name="sampleRegiste.relation" id="inputrelation" data-validation="length" data-validation-length="1-50" data-validation-error-msg="字数范围在(1-50)"  cssClass="longtext"></s:textfield></td></tr>
		<tr id="tr_input_email"><td>邮件地址：</td><td><s:textfield name="sampleRegiste.email" id="inputemail"  data-validation="custom" data-validation-regexp="^(['_a-z0-9-\\+]+(\\.['_a-z0-9-\\+]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*\\.([a-z]{2}|aero|arpa|asia|biz|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|nato|net|org|pro|tel|travel|xxx)|)$"  data-validation-error-msg="邮箱格式不正确"  cssClass="longtext"></s:textfield></td></tr>
		
		<tr><td colspan="2" align="center" bgcolor="#d6e4ed"><b>样品信息</b></td></tr>
		<tr><td   width="20%"><span class="mustfill">*</span>样品编号：</td><td><s:textfield id="sampleno" name="sampleRegiste.sampleno" data-validation="length" data-validation-length="1-50" data-validation-error-msg="字数范围在(1-50)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>样品名称：</td><td><s:textfield name="sampleRegiste.samplename"  data-validation="length" data-validation-length="1-500" data-validation-error-msg="字数范围在(1-500)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td>规格型号：</td><td><s:textfield name="sampleRegiste.samplemodel"  data-validation="length" data-validation-length="0-50" data-validation-error-msg="字数范围在(0-50)" cssClass="shorttext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>样品数量：</td><td><s:textfield name="sampleRegiste.samplecount"  maxlength="2" data-validation="number" data-validation-error-msg="只能输入大于等于 1 的数字" cssClass="shorttext"></s:textfield></td></tr>
		<tr><td>存储条件：</td><td><s:checkboxlist list="#{'1':'常温','2':'冷藏','3':'冷冻','4':'避光','5':'干燥'}" name="sampleRegiste.storecondition" value="storeList" /></td></tr>
		<tr><td>样品状态：</td><td><s:textfield name="sampleRegiste.samplestatus"  data-validation="length" data-validation-length="0-50" data-validation-error-msg="字数范围在(0-50)" cssClass="shorttext"></s:textfield></td></tr>
		
		<tr><td><span class="mustfill">*</span>对接人：</td><td><s:select name="sampleRegiste.acceptpeople" id="selectExamineuser" headerKey="" headerValue="--请选择--" list="map" ></s:select></td></tr>
		<tr><td><span class="mustfill">*</span>样品来源：</td><td><s:textfield name="sampleRegiste.resource"  data-validation="length" data-validation-length="0-50" data-validation-error-msg="字数范围在(0-50)" cssClass="shorttext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>委托分类：</td><td><s:select name="sampleRegiste.samplecategoryid"  id="selectSampleCategory"  list="samplecategorymap"></s:select></td></tr>
<%-- 		<tr><td><span class="mustfill">*</span>检验标准：</td><td><s:select name="sampleRegiste.checkstandardid"  id="selectCheckStandardid" headerKey="" headerValue="--请选择--" list="samplecategorymap"/></td></tr>
 --%>		
 		<%-- <tr><td><span class="mustfill">*</span>检验标准：</td><td>
			  <s:select  list="checkstandardmap"  id="hiddensel2" style="display:none"></s:select>	
			  <input type="hidden" id="enid2" name="sampleRegiste.checkstandardid" value="${sampleRegiste.checkstandardid}" />		  
			  <input type="text" id="adminAreaCode2" onkeyup="inputchange2()"  value="${sampleRegiste.checkstandardname}" class="longtext"/><span class="textdesc"> 输入查询</span>
			    <div id="orgDataDiv2" style="margin-left: 0px;position:absolute;width: 270px;border: 1px solid black;background: white;display:none;">
					<ul id="orgDataUl2" style="line-height:24px;">
					</ul>
				</div>
		</td></tr>  --%>
 		
 		
 		
 		<tr><td><span class="mustfill">*</span>样品处置：</td><td> <s:radio list="#{'1':'处置','0':'退回'}" name="sampleRegiste.samplehandle" value="1" /></td></tr>
		<tr><td><span class="mustfill">*</span>检验项目：</td><td><s:select  list="itemList"  id="hiddenCheckItems" style="display:none"></s:select><s:select  list="map" id="hiddenUsers" style="display:none"></s:select>
		     <a href="javascript:void(0)" onclick="addCheckItems()">新增检验项目</a> | <a href="javascript:void(0)" onclick="selCheckItems()">添加已有项目</a></td></tr>
		<tr><td></td><td><s:hidden id="itemcount" value="%{pageRegisteCheckItem.list.size}"/>
		<s:iterator value="pageRegisteCheckItem.list" status="status">    
		         <div><input type="checkbox" id="oldcheckbox${status.index}"/>  项目：<s:select list="itemList"  name="selectCheckItems"  value="checkitem"> </s:select>负责人：<s:select headerKey="" headerValue="--请选择--" list="map" name="selectExamineusers" id="oldselectexamineusers%{#status.index }" value="examineuser"></s:select> | <a href="javascript:void(0)" onclick="removeItem(this)">移除</a></div><br/>
		</s:iterator>
		</td></tr>
		<tr><td></td><td id="itemlist"><div></div></td></tr>
		<tr><td></td><td><input type="checkbox" name="selectcontrol" id="selectall" onclick="allselected(this)">全选</input><input type="checkbox" name="selectcontrol" id="selectpart" onclick="partselected()">反选</input>&nbsp;&nbsp;&nbsp;&nbsp;  负责人：<s:select name="selectExamineuser" id="selectExamineuser" headerKey="" headerValue="--请选择--" list="map" onchange="updatecheck()"/><span class="mustfill">* 选择此负责人后，上面选中项自动变更</span></td></tr>
		<tr><td><span class="mustfill">*</span>接收日期：</td><td>
		<s:textfield id="receivedate" name="sampleRegiste.receivedate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly" cssClass="shorttext">
		   <s:param name="value"><s:date name="sampleRegiste.receivedate" format="yyyy-MM-dd"/></s:param>
		</s:textfield>
		</td></tr>
		<tr><td colspan="2" align="center" bgcolor="#d6e4ed"><b>结算信息</b></td></tr>						
		<tr><td>检测费用：</td><td><s:textfield id="chemoney" name="sampleRegiste.checkmoney"   maxlength="8" data-validation="number" data-validation-allowing="float" data-validation-error-msg="只能输入数字"  cssClass="shorttext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>付款方式：</td><td><s:radio list="#{'2':'直接支付','0':'待定'}" name="sampleRegiste.payway"/></td></tr>
        <tr id="tr_paydes"><td><span class="mustfill">*</span>付款方式说明：</td><td><s:textfield id="paydes" name="sampleRegiste.paydes"  data-validation="length" data-validation-length="1-200" data-validation-error-msg="字数范围在(1-200)" cssClass="longtext"></s:textfield></td></tr>
 		<s:hidden id="premoney" name="sampleRegiste.prepaymoney"  maxlength="8" data-validation="number" data-validation-allowing="float" data-validation-error-msg="只能输入数字" cssClass="shorttext"/>
 		<s:hidden id="balmoney" name="sampleRegiste.balancemoney" readonly="true" cssClass="hiddenshorttext"/>
 <!--    	<tr><td>检验依据：</td><td> <s:radio list="#{'1':'指定检验依据','2':'由本中心选定合适的标准方法','3':'同意用本中心确定的非标准方法'}" name="sampleRegiste.accordingtype"  /></td></tr> -->
<!--  		<tr id="tr_accordcontent"><td>依据内容：</td><td><s:textfield id="accordings" name="sampleRegiste.accordings"  data-validation="length" data-validation-length="0-200" data-validation-error-msg="字数范围在(0-200)" cssClass="longtext"></s:textfield></td></tr>-->
		<tr><td><span class="mustfill">*</span>是否出具报告：</td><td> <s:radio list="#{'0':'不出报告','1':'出具科研测试报告','2':'出具检测报告'}" name="sampleRegiste.reporttype" onclick="showTalent()" /></td></tr>
<!--        <tr id="tr_talent"><td><span class="mustfill">*</span>资质：</td><td><s:select name="sampleRegiste.talent"  list="qualificationmap" cssClass="shortext"></s:select></td></tr>-->
		<tr id="tr_talent"><td><span class="mustfill">*</span>资质：</td><td><s:checkboxlist list="#{'1':'CMA','2':'CNAS','3':'CMAF','4':'其他'}" name="sampleRegiste.talent" value="talentList" /></td></tr>
		<tr><td><span class="mustfill">*</span>计划检测完成日期：</td><td><s:textfield id="finishdate" name="sampleRegiste.finishdate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly" cssClass="shorttext">
		  <s:param name="value"><s:date name="sampleRegiste.finishdate" format="yyyy-MM-dd"/></s:param>
		</s:textfield></td></tr>
		<!-- 		<tr><td>报告快递单号：</td><td><s:textfield name="sampleRegiste.reportno"  data-validation="length" data-validation-length="0-200" data-validation-error-msg="字数范围在(0-200)" cssClass="longtext"></s:textfield></td></tr> -->
		<tr><td>备注：</td><td><s:textarea name="sampleRegiste.remark" cssStyle="width:400px;height:110px;" data-validation="length" data-validation-length="0-200" data-validation-error-msg="字数范围在(0-200)" ></s:textarea></td></tr>
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
		onload = function(){  
             /*
             if ($("input[name='sampleRegiste.accordingtype']:checked").val()=="1")
                  $("#tr_accordcontent").show();
             else
                  $("#tr_accordcontent").hide();
                  */
             if ($("input[name='sampleRegiste.reporttype']:checked").val()=="0")
		         $("#tr_talent").hide();
		     else
                 $("#tr_talent").show();
		     //初始化选择的联系人地址和方式，否则第一次显示的时候为空值
		     setUserInfo();
		}
		Array.prototype.unique = function()
				{
				  var a = {}; for(var i=0; i<this.length; i++)
				  {
				    if(typeof a[this[i]] == "undefined")
				      a[this[i]] = 1;
				  }
				  this.length = 0;
				  for(var i in a)
				    this[this.length] = i;
				  return this;
		}
		var flag = false;
		var count = 0;  // 输入的检验项目个数
		var selcount = 0; // 选择的检验项目个数  
	    function billcheck(){
	       if ($("input[name='sampleRegiste.billischeck']:checked").val()=="1"){
	           $("#tr_billno").show();
	       }else{
	           $("#tr_billno").hide();
	           $("#billno").val("无 ");
	       }
	    }
	    
	    function usercheck(){
	       if ($("input[name='sampleRegiste.addrelationuser']:checked").val()=="1"){
	         $("#tr_input_user").show();
		     $("#tr_input_address").show();
		     $("#tr_input_relation").show();
		     $("#tr_input_email").show();
		     $("#inputuser").val("");
		     $("#inputaddress").val("");
		     $("#inputrelation").val("");
	       }else{
		     $("#tr_input_user").hide();
		     $("#tr_input_address").hide();
		     $("#tr_input_relation").hide();
		     $("#tr_input_email").hide();
		     
		     $("#tr_input_user").val("无");
		     $("#tr_input_address").val("无");
		     $("#tr_input_relation").val("无");
	       }
	    }
        function showContent(){
	        if ($('input:radio[name="sampleRegiste.payway"]:checked').val()=="0"){
	             $("#paydes").val("");
	             $("#tr_paydes").show();
	        }else{
	             $("#paydes").val("无");
	             $("#tr_paydes").hide();
	        }
	    }
	    function showTalent(){
	        if ($('input:radio[name="sampleRegiste.reporttype"]:checked').val()=="0"){
	             $("#tr_talent").hide();
	        }else{
	             $("#tr_talent").show();
	        }
	    }
	    function addCheckItems(){
		   if (count==0&&selcount==0){
		     // $("#itemlist").append("<br>");
		   }
		   $("#itemlist").append('<div  style="height:30px"><input type="checkbox" id="addcheckbox'+count+'"/>  项目：<s:textfield id="inputitem'+count+'" name="inputCheckItems" data-validation="length" data-validation-length="1-500" data-validation-error-msg="字数范围在(1-500)"  cssClass="longtext"/> 负责人：<select id="examieuser'+count+'" name="inputExamineusers"/> | <a href="javascript:void(0)" onclick="removeItem(this)">移除</a></div>');
		   var obj = document.getElementById("hiddenUsers");
		   var options = obj.options;
		   var newobj = document.getElementById("examieuser"+count);
		   newobj.options.add(new Option("--请选择--",""));
		   for (var i=0;i<options.length;i++){
		        var val = options[i].value;
		        var name = options[i].text;
		        var newoption = new Option(name,val);
		        newobj.options.add(newoption);
		   }
		   count = count +1;
		   parent.iFrameHeight();
		}
		
		function selCheckItems(){
		   if (count==0&&selcount==0){
		    //  $("#itemlist").append("<br>");
		   }
		   $("#itemlist").append('<div  style="height:30px"><input type="checkbox" id="addselcheckbox'+selcount+'"/>  项目：<select id="selitem'+selcount+'" name="selectCheckItems" cssClass="longtext"/> 负责人：<select id="selexamieuser'+selcount+'"  name="selectExamineusers"/> | <a href="javascript:void(0)" onclick="removeItem(this)">移除</a></div>');
		   var obj = document.getElementById("hiddenCheckItems");
		   var options = obj.options;
		   var newobj = document.getElementById("selitem"+selcount);
		   for (var i=0;i<options.length;i++){
		        var val = options[i].value;
		        var name = options[i].text;
		        var newoption = new Option(name,val);
		        newobj.options.add(newoption);
		   }
		   
		   var userobj = document.getElementById("hiddenUsers");
		   var useroptions = userobj.options;
		   var newuserobj = document.getElementById("selexamieuser"+selcount);
		   newuserobj.options.add(new Option("--请选择--",""));
		   for (var i=0;i<useroptions.length;i++){
		        var val = useroptions[i].value;
		        var name = useroptions[i].text;
		        var newoption = new Option(name,val);
		        newuserobj.options.add(newoption);
		   }
		   selcount = selcount +1;
		   parent.iFrameHeight();
		}
		function removeItem(aObj) {
			 $(aObj).parent().remove();
			 parent.iFrameHeight();
		}
		 function allselected(o){
		    
            if (o.checked==true){ 
                var obj = document.getElementById("itemcount");
			    if (obj!=null){
			         var oldcount = obj.value;
			         for(var i=0;i<oldcount;i++){
		                obj = document.getElementById("oldcheckbox"+i);
		                if (obj!=null){
		                     obj.checked=true;
		                }
		             }
			    } 
	            for(var i=0;i<count;i++){
	                var obj = document.getElementById("addcheckbox"+i);
	                if (obj!=null){
	                     obj.checked=true;
	                }
	            }
	            for(var i=0;i<selcount;i++){
	                var obj = document.getElementById("addselcheckbox"+i);
	                if (obj!=null){
	                     obj.checked=true;
	                }
	            }
            }else{
                var obj = document.getElementById("itemcount");
			    if (obj!=null){
			         var oldcount = obj.value;
			         for(var i=0;i<oldcount;i++){
		                obj = document.getElementById("oldcheckbox"+i);
		                if (obj!=null){
		                     obj.checked=false;
		                }
		             }
			    } 
	            for(var i=0;i<count;i++){
	                var obj = document.getElementById("addcheckbox"+i);
	                if (obj!=null){
	                     obj.checked=false;
	                }
	            }
	            for(var i=0;i<selcount;i++){
	                var obj = document.getElementById("addselcheckbox"+i);
	                if (obj!=null){
	                     obj.checked=false;
	                }
	            }
            }
            var partobj = document.getElementById("selectpart");
            if (partobj!=null){
               partobj.checked=false;
            }
        }
        
        function partselected(){
            var obj = document.getElementById("itemcount");
		    if (obj!=null){
		         var oldcount = obj.value;
		         for(var i=0;i<oldcount;i++){
	                obj = document.getElementById("oldcheckbox"+i);
	                if (obj!=null){
	                     obj.checked=!obj.checked;
	                }
	             }
		    } 
            for(var i=0;i<count;i++){
                var obj = document.getElementById("addcheckbox"+i);
                if (obj!=null){
                     obj.checked=!obj.checked;
                }
            }
            for(var i=0;i<selcount;i++){
                var obj = document.getElementById("addselcheckbox"+i);
                if (obj!=null){
                     obj.checked=!obj.checked;
                }
            }
            var allobj = document.getElementById("selectall");
            if (allobj!=null){
               allobj.checked=false;
            }
        }
        
        function updatecheck(){
            //得到设置的负责人选择值
            var obj = document.getElementById("selectExamineuser");
            if (obj==null) return;
            
            var index = obj.selectedIndex; 
            var obj = document.getElementById("itemcount");
            if (obj!=null){
		        var oldcount = obj.value;
	            for(var i=0;i<oldcount;i++){
	                var obj = document.getElementById("oldcheckbox"+i);
	                if (obj!=null&&obj.checked){
	  	                obj = document.getElementById("oldselectexamineusers"+i);
		                if (obj!=null){
		                     obj.options[index].selected=true;
		                }
	                }
	            }
            }
            for(var i=0;i<count;i++){
                var obj = document.getElementById("addcheckbox"+i);
                if (obj!=null&&obj.checked){
	                obj = document.getElementById("examieuser"+i);
	                if (obj!=null){
	                     obj.options[index].selected=true;
	                }
                }
            }
            for(var i=0;i<selcount;i++){
                var obj = document.getElementById("addselcheckbox"+i);
                if (obj!=null&&obj.checked){
	                obj = document.getElementById("selexamieuser"+i);
	                if (obj!=null){
	                    obj.options[index].selected=true;
	                }
                }
            }
        }
	     function inputchange(){  
	       //情况隐藏域的id 
	       $("#enid").val("");
	       var inputparam = $("#adminAreaCode").val();
		   if(inputparam == ''){
				$("#orgDataDiv").hide();
				return;
		   }
	       var lis = "";
           var obj = document.getElementById("hiddensel"); 
   	       for(var i=0; i<obj.options.length; i++) {  
       	        if(obj.options[i].text==inputparam) {
       	            $("#enid").val(obj.options[i].value);
		            $("#orgDataDiv").hide();  
		            selOrg2(obj.options[i].value+"|"+obj.options[i].text);
		        	return;
                }else if(obj.options[i].text.indexOf(inputparam) != -1) {  
		        	lis += '<li style="cursor:pointer" onclick="selOrg(this)">'+obj.options[i].value+'|'+obj.options[i].text+'</li>';
                }  
	       }  
	       if (lis!=""){
	          $("#orgDataUl").html(lis);
		      $("#orgDataDiv").show();
		   }else{
		      $("#orgDataDiv").hide();
		      $("#enid").val("");
		   }
        }
		function selchange(){  
	       var obj = document.getElementById("selsel");
	       var val = obj.options[obj.selectedIndex].text;
 			   obj = document.getElementById("entrustinput");
              obj.value = val;
	    }
	    function check(){
              if ($("#adminAreaCode").val()==""){
	                  alert("委托单位不能为空！");
	                  return false;
	          }
	          if ($("#user").val()==""){
	                  alert("送样人不能为空！");
	                  return false;
	          }
	          //zjx
	          if($("#selectExamineuser").val()==""){
	        	  alert("对接人不能为空！");
	        	  return false;
	          }
	          if($("#selectResource").val()==""){
	        	  alert("样品来源不能为空！");
	        	  return false;
	          }
	          
	          if($("#selectSampleCategory").val()==""){
	        	  alert("委托分类不能为空！");
	        	  return false;
	          }
	          
	          if($("#selectCheckStandardid").val()==""){
	        	  alert("检验标准不能为空！");
	        	  return false;
	          }
	          if ($("#receivedate").val()>$("#finishdate").val()){
	                  alert("检查完成日期不能早于接收样品日期！");
	                  return;
	          }
	          if ($("input[name=inputCheckItems]").length==0&&$("select[name=selectCheckItems]").length==0){
	               alert("至少输入或者选择一个检验项目！");
				   return false;
	          }
	          // 增加检验项目中，是否有重复的检验项目
	          if($("input[name=inputCheckItems]").length>=2){
				    var arrText = new Array();
				    arrText[0] = $("#firstInputItem").val();
				    var idx = 1;
				    for(var i=0;i<count;i++){
                        var obj = document.getElementById("inputitem"+i);
                        if (obj!=null){
	                        arrText[idx] = obj.value;
	                        idx = idx +1;
                        }
                    }
                    var len1 = arrText.length;
                    var len2= arrText.unique().length;
                    if (len1!=len2){;
                        alert("输入的检验项目中有重复数据，请重新输入！");
				        return false;
				    }
			  }
			  // 选择的检验项目中，是否有重复的检验项目
	          if($("select[name=selectCheckItems]").length>=2){
				    var arrText = new Array();
				    var idx = 0;
				    for(var i=0;i<selcount;i++){
                        var obj = document.getElementById("selitem"+i);
                        if (obj!=null){
	                        arrText[idx] = obj.value;
	                        idx = idx +1;
                        }
                    }
                    var len1 = arrText.length;
                    var len2= arrText.unique().length;
                    if (len1!=len2){;
                       alert("在增加的选择检验项目中有重复数据，请重新选择！");
				       return false;
				    }
			  }
			  var obj = document.getElementById("sampleno");
			  var v = obj.value;
			  var code = v.substr(v.lastIndexOf("-")+1);
			  if (isNaN(code)){
			     alert("样品编号后三位必须为数字 ，请重新修改！");
                 return false;
			  }
			  //查询检测费用是否少于已经付款的金额
			  if (parseFloat($("#chemoney").val())<parseFloat($("#premoney").val())){
			     alert("检测费用不能少于已经付款的金额!已付款金额（"+$("#premoney").val()+"）");
			     return false;
			  }else{
			     $("#balmoney").val(parseFloat($("#chemoney").val())-parseFloat($("#premoney").val()));
			  }
			  // 查询是否有重复编号
			  checkCode();
             if (flag){
                 alert("样品编号已经存在，请重新修改！");
                 return false;
              }
	    }
	    
	    function setUserInfo(){
	       $.ajax({
	            type:'get',
				url: 'getUserInfoSampleRegiste.html',
				data:{ userid: $("#inputuser").val()},
				dataType:'json',
				success: function(data) {
				     if (data==null||data=="") return;
				     var info = eval("("+data+")");
				     $("#inputrelation").val(info.tel);
				     $("#inputaddress").val(info.address);
				     $("#inputemail").val(info.email);
				},
				error: function(){                         
		           alert('获取联系人信息出错！');    
		        }
			});
	    }
	    function checkCode(){
	       $.ajax({
	            async:false,
	            type:'get',
				url: 'isExistsSampleRegiste.html',
				data:{ regid: $("#sampleid").val(),regcode:$("#sampleno").val()},
				dataType:'json',
				success: function(data) {
				     var info = eval("("+data+")");
				     if (info.res){
				         flag = true;
				     }else{
				         flag = false;
				     }
				},
				error: function(){                         
		           alert('编号检查出错！');    
		        }
			});
	    }
	    
	    function selOrg(liObj){
			var html = $(liObj).html();
			if(html == '' || html  == '&nbsp'){
				return;
			}
			var arr = html.split("|");
			$("#adminAreaCode").val(arr[1]);
			$("#enid").val(arr[0]);
			$("#orgDataDiv").hide();
			
			// 根据单位得到联系人
			$.ajax({
	            async:false,
	            type:'get',
				url: 'getusersSampleRegiste.html',
				data:{ cid: $("#enid").val()},
				dataType:'json',
				success: function(data) {
				       var info = eval("("+data+")");
				       $("#hiddenseluser").empty();
				       for(var o in info){ 
				           if (o!=null&&o!="unique"){
						         $("#inputuser").val(info[o].userid);
						         $("#user").val(info[o].user);
						         $("#inputaddress").val(info[o].address);
						         $("#inputrelation").val(info[o].tel);
						         $("#inputemail").val(info[o].email);
						         
						         $("#hiddenseluser").append("<option value='"+info[o].userid+"'>"+info[o].user+"</option>");
					        }
				       }  
				},
				error: function(){                         
		           alert('查询联系人出错，请重试！');    
		        }
			});
		}
		function selOrg2(str){
			var arr = str.split("|");
			$("#adminAreaCode").val(arr[1]);
			$("#enid").val(arr[0]);
			$("#orgDataDiv").hide();
			
			// 根据单位得到联系人
			$.ajax({
	            async:false,
	            type:'get',
				url: 'getusersSampleRegiste.html',
				data:{ cid: $("#enid").val()},
				dataType:'json',
				success: function(data) {
				       var info = eval("("+data+")");
				       $("#hiddenseluser").empty();
				       for(var o in info){ 
				           if (o!=null&&o!="unique"){
						         $("#inputuser").val(info[o].userid);
						         $("#user").val(info[o].user);
						         $("#inputaddress").val(info[o].address);
						         $("#inputrelation").val(info[o].tel);
						         $("#inputemail").val(info[o].email);
						         
						         $("#hiddenseluser").append("<option value='"+info[o].userid+"'>"+info[o].user+"</option>");
					        }
				       }  
				},
				error: function(){                         
		           alert('查询联系人出错，请重试！');    
		        }
			});
		}
		// 送样人变化
        function inputusername(){  
           //清空隐藏的原来选择的人员
           $("#inputuser").val("");
	       var inputparam = $("#user").val();
		   if(inputparam == ''){
				$("#userDiv").hide();
				return;
		   }
	       var lis = "";
           var obj = document.getElementById("hiddenseluser"); 
   	       for(var i=0; i<obj.options.length; i++) {  
       	        if(obj.options[i].text==inputparam) {  
		        	$("#inputuser").val(obj.options[i].value);
		        	$("#userDiv").hide();
		        	selUser2(obj.options[i].value+"|"+obj.options[i].text);
		        	return;
                }else if(obj.options[i].text.indexOf(inputparam) != -1) {  
		        	lis += '<li style="cursor:pointer" onclick="selUser(this)">'+obj.options[i].value+'|'+obj.options[i].text+'</li>';
                }  
	       }  
	       
	       if (lis!=""){
	         // $("#inputrelation").attr("disabled",true);
		     // $("#inputaddress").attr("disabled",true);
		     // $("#inputemail").attr("disabled",true);
	          $("#userUl").html(lis);
		      $("#userDiv").show();
		   }else{
		     // $("#inputrelation").attr("disabled",false);
		     // $("#inputaddress").attr("disabled",false);
		     // $("#inputemail").attr("disabled",false);
		      $("#userDiv").hide();
		   }
        }
        
        function selUser(liObj){
			var html = $(liObj).html();
			if(html == '' || html  == '&nbsp'){
				return;
			}
			var arr = html.split("|");
			$("#user").val(arr[1]);
			$("#inputuser").val(arr[0]);
			$("#userDiv").hide();
			setUserInfo();
		}
		function selUser2(str){
			var arr = str.split("|");
			$("#user").val(arr[1]);
			$("#inputuser").val(arr[0]);
			$("#userDiv").hide();
			setUserInfo();
		}
		
		
		//百度搜索样式  检测标准  曾智琴
	     function inputchange2(){  
		       $("#enid2").val("");
		       var inputparam = $("#adminAreaCode2").val();
			   if(inputparam == ''){
					$("#orgDataDiv2").hide();
					return;
			   }
		       var lis = "";
	           var obj = document.getElementById("hiddensel2"); 
	   	       for(var i=0; i<obj.options.length; i++) {  
	       	        if(obj.options[i].text==inputparam) {
	       	            $("#enid2").val(obj.options[i].value);
			            $("#orgDataDiv2").hide();  
			         <!--   selOrg2(obj.options[i].value+"|"+obj.options[i].text);  -->    //????????????
			        	return;
	                }else if(obj.options[i].text.indexOf(inputparam) != -1) {  
			        	lis += '<li style="cursor:pointer" onclick="selOrg3(this)">'+obj.options[i].value+'|'+obj.options[i].text+'</li>';
	                }  
		       }  
		       
		       if (lis!=""){
		          $("#orgDataUl2").html(lis);
			      $("#orgDataDiv2").show();
			   }else{
			      $("#orgDataDiv2").hide();
			      $("#enid2").val("");
			   }
	        }
		
		
	   //选择检验标准  曾智琴
			function selOrg3(liobj2){
				var html = $(liobj2).html();
				if(html == '' || html  == '&nbsp'){
					return;
				}
				var arr = html.split("|");
				$("#adminAreaCode2").val(arr[1]);
				$("#enid2").val(arr[0]);
				$("#orgDataDiv2").hide();
			}
		</script>
	</body>
</html>