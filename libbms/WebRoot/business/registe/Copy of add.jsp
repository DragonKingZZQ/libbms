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
		<h1>添加样品登记</h1>
		<s:form action="saveSampleRegiste" namespace="/business">
		<table class="frmtable">
		<tr><td  width="20%"><span class="mustfill">*</span>样品编号：</td><td><s:textfield id="sampleno" name="sampleRegiste.sampleno" data-validation="length" data-validation-length="1-50" data-validation-error-msg="字数范围在(1-50)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td ><span class="mustfill">*</span>样品名称：</td><td><s:textfield name="sampleRegiste.samplename"  data-validation="length" data-validation-length="1-500" data-validation-error-msg="字数范围在(1-500)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td>规格型号：</td><td><s:textfield name="sampleRegiste.samplemodel"  data-validation="length" data-validation-length="0-50" data-validation-error-msg="字数范围在(0-50)" cssClass="shorttext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>样品数量：</td><td><s:textfield name="sampleRegiste.samplecount" value="1"  maxlength="2" data-validation="number" data-validation-error-msg="只能输入大于等于 1 的数字"  cssClass="shorttext"></s:textfield></td></tr>
		<tr><td>存储条件：</td><td><s:checkboxlist list="#{'1':'常温','2':'冷藏','3':'冷冻','4':'避光','5':'干燥'}" name="sampleRegiste.storecondition" value="1" /></td></tr>
		<tr><td><span class="mustfill">*</span>委托单位：</td><td>
		      <div style="width:150px;margin:0px;padding:0px;position:relative;">  
			    <s:select onchange="selchange()" headerKey="" headerValue="请选择" list="entrustcompanyList"  name="sampleRegiste.entrustcompany" id="selsel" style="width:300px;height:24px;margin:0px;padding:0px;"></s:select>
			    <input type="text" onfocus="inputfocus()" name="addEntrustCompany" onBlur="inputblur()" id="entrustinput" onchange="inputchange()" value="输入可进行过滤" style="width:275px;height:17px;position:absolute;left:3px;top:1px;border:0px;" ></input>  
			  </div>
			  <s:select  list="entrustcompanyList"  id="hiddensel" style="display:none"></s:select>
		</td></tr>
		<tr><td>生产单位：</td><td><s:textfield name="sampleRegiste.productionunit" data-validation="length" data-validation-length="0-200" data-validation-error-msg="字数范围在(0-200)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>检验项目：</td><td><s:select  list="itemList" id="hiddenCheckItems" style="display:none"></s:select><s:select  list="map"  id="hiddenUsers" style="display:none"></s:select>
		     <a href="javascript:void(0)" onclick="addCheckItems()">新增检验项目</a> | <a href="javascript:void(0)" onclick="selCheckItems()">添加已有项目</a></td></tr>
		<tr><td></td><td id="itemlist"><div>项目：<s:textfield id="firstInputItem" name="inputCheckItems" data-validation="length" data-validation-length="1-500" data-validation-error-msg="字数范围在(1-500)"  cssClass="longtext"></s:textfield>  负责人：<s:select name="inputExamineusers" headerKey="" headerValue="--请选择--" list="map"/> | <a href="javascript:void(0)" onclick="removeItem(this)">移除</a></div></td></tr>
		<tr><td><span class="mustfill">*</span>接收日期：</td><td>
		<s:textfield id="receivedate" name="sampleRegiste.receivedate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly" cssClass="shorttext"></s:textfield>
		</td></tr>
		<tr>
           	<td></td><td class="textdesc">送样人可以选择或填写  <s:radio list="#{'1':'填写','0':'选择'}" name="sampleRegiste.addrelationuser" value="1" onclick="usercheck()" /></td>
        </tr>
		<tr id="tr_input_user"><td><span class="mustfill">*</span>送样人：</td><td><s:textfield name="sampleRegiste.senduser" id="inputuser" data-validation="length" data-validation-length="1-50" data-validation-error-msg="字数范围在(1-50)"  cssClass="longtext"></s:textfield></td></tr>
		<tr id="tr_input_address"><td><span class="mustfill">*</span>联系地址：</td><td><s:textfield name="sampleRegiste.address" id="inputaddress"  data-validation="length" data-validation-length="1-500" data-validation-error-msg="字数范围在(1-500)"  cssClass="longtext"></s:textfield></td></tr>
		<tr id="tr_input_relation"><td><span class="mustfill">*</span>联系方式：</td><td><s:textfield name="sampleRegiste.relation" id="inputrelation" data-validation="length" data-validation-length="1-50" data-validation-error-msg="字数范围在(1-50)"  cssClass="longtext"></s:textfield></td></tr>
		<tr id="tr_select_user"><td><span class="mustfill">*</span>送样人：</td><td><s:select name="selectsenduser" id="selectsenduser" list="userList" onchange="setUserInfo()" cssClass="longtext"></s:select></td></tr>
		<tr id="tr_select_address"><td><span class="mustfill">*</span>联系地址：</td><td><s:textfield name="selectaddress" id="selectaddress"  readonly="true"  cssClass="hiddenlongtext"></s:textfield></td></tr>
		<tr id="tr_select_relation"><td><span class="mustfill">*</span>联系方式：</td><td><s:textfield name="selectrelation" id="selectrelation" readonly="true" cssClass="hiddenlongtext"></s:textfield></td></tr>
				
		<tr><td>检测费用：</td><td><s:textfield name="sampleRegiste.checkmoney" value="0"  maxlength="8" data-validation="number" data-validation-allowing="float" data-validation-error-msg="只能输入数字"  cssClass="shorttext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>付款方式：</td><td><s:radio list="#{'1':'预付款扣除','2':'直接支付','3':'定期结算','0':'其他'}" name="sampleRegiste.payway" value="1" checked="checked" onclick="showContent()" /></td></tr>
        <tr id="tr_paydes"><td><span class="mustfill">*</span>付款方式说明：</td><td><s:textfield id="paydes" name="sampleRegiste.paydes"  data-validation="length" data-validation-length="1-200" data-validation-error-msg="字数范围在(1-200)" cssClass="longtext"></s:textfield></td></tr>
<!--		<tr><td>预付费用：</td><td><s:textfield name="sampleRegiste.prepaymoney" value="0"  maxlength="8" data-validation="number" data-validation-allowing="float" data-validation-error-msg="只能输入数字" cssClass="shorttext"></s:textfield></td></tr>-->
<!--		<tr><td>剩余费用：</td><td><s:textfield name="sampleRegiste.balancemoney" readonly="true" cssClass="hiddenshorttext"></s:textfield></td></tr>-->
<!--		<tr><td><span class="mustfill">*</span>检验员：</td><td><s:select name="sampleRegiste.examineuser" list="map" cssClass="shortext"></s:select></td></tr>-->
		<tr><td><span class="mustfill">*</span>检验依据：</td><td> <s:radio list="#{'1':'指定检验依据','2':'由本中心选定合适的标准方法','3':'同意用本中心确定的非标准方法'}" name="sampleRegiste.accordingtype" value="1" /></td></tr>
		<tr id="tr_accordcontent"><td><span class="mustfill">*</span>依据内容：</td><td><s:textfield id="accordings" name="sampleRegiste.accordings"  data-validation="length" data-validation-length="1-200" data-validation-error-msg="字数范围在(1-200)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>是否出具报告：</td><td> <s:radio list="#{'0':'不出报告','1':'出具科研测试报告','2':'出具检测报告'}" name="sampleRegiste.reporttype" value="1" onclick="showTalent()"/></td></tr>
<!--		<tr id="tr_talent"><td><span class="mustfill">*</span>资质：</td><td><s:select name="sampleRegiste.talent"  list="qualificationmap" cssClass="shortext"></s:select></td></tr>-->
		<tr id="tr_talent"><td><span class="mustfill">*</span>资质：</td><td><s:checkboxlist list="#{'1':'CMA','2':'CNAS','3':'CMAF','4':'其他'}" name="sampleRegiste.talent" value="1" /></td></tr>
		<tr><td><span class="mustfill">*</span>计划检测完成日期：</td><td><s:textfield id="finishdate" name="sampleRegiste.finishdate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly" cssClass="shorttext"></s:textfield></td></tr>
		<tr><td>样品状态：</td><td><s:textfield name="sampleRegiste.samplestatus"  data-validation="length" data-validation-length="0-50" data-validation-error-msg="字数范围在(0-50)" cssClass="shorttext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>样品处置：</td><td> <s:radio list="#{'1':'处置','0':'退回'}" name="sampleRegiste.samplehandle" value="1" /></td></tr>
		<tr><td>发票类型：</td><td><s:radio id="optbill" list="#{'1':'增值税专用发票','2':'增值税普通发票','3':'未确定'}" name="sampleRegiste.taxtype" value="0" checked="checked" /></td></tr>
		<tr><td><span class="mustfill">*</span>发票是否开具：</td><td><s:radio id="optbill" list="#{'1':'已开','0':'未开'}" name="sampleRegiste.billischeck" value="0" checked="checked" onclick="billcheck()" /></td></tr>
		<tr id="tr_billno"><td><span class="mustfill">*</span>发票快递单号：</td><td><s:textfield name="sampleRegiste.billno" id="billno" data-validation="length" data-validation-length="0-200" data-validation-error-msg="字数范围在(0-200)"  cssClass="longtext"></s:textfield></td></tr>
		<tr><td>报告快递单号：</td><td><s:textfield name="sampleRegiste.reportno"  data-validation="length" data-validation-length="0-200" data-validation-error-msg="字数范围在(0-200)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td>备注：</td><td><s:textarea name="sampleRegiste.remark" cssStyle="width:400px;height:110px;" data-validation="length" data-validation-length="0-200" data-validation-error-msg="字数范围在(0-200)" value="送检样品信息均由委托单位提供"></s:textarea></td></tr>
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
		     $("#tr_billno").hide();

		     $("#tr_select_user").hide();
		     $("#tr_select_address").hide();
		     $("#tr_select_relation").hide();
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
		     $("#tr_select_user").hide();
		     $("#tr_select_address").hide();
		     $("#tr_select_relation").hide();
	       }else{
	         $("#tr_select_user").show();
		     $("#tr_select_address").show();
		     $("#tr_select_relation").show();
		     $("#tr_input_user").hide();
		     $("#tr_input_address").hide();
		     $("#tr_input_relation").hide();
		     
		     $("#inputuser").val("无");
		     $("#inputaddress").val("无");
		     $("#inputrelation").val("无");
	       }
	       return true;
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
		      $("#itemlist").append("<br>");
		   }
		   $("#itemlist").append('<div  style="height:30px">项目：<s:textfield id="inputitem'+count+'" name="inputCheckItems" data-validation="length" data-validation-length="1-500" data-validation-error-msg="字数范围在(1-500)"  cssClass="longtext"/> 负责人：<select id="examieuser'+count+'" name="inputExamineusers"/> | <a href="javascript:void(0)" onclick="removeItem(this)">移除</a></div>');
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
		      $("#itemlist").append("<br>");
		   }
		   $("#itemlist").append('<div  style="height:30px">项目：<select id="selitem'+selcount+'" name="selectCheckItems" cssClass="longtext"/> 负责人：<select id="selexamieuser'+selcount+'" name="selectExamineusers"/> | <a href="javascript:void(0)" onclick="removeItem(this)">移除</a></div>');
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
		
		function  inputfocus(){  
		    if ($("#entrustinput").val()=="输入可进行过滤")
                  $("#entrustinput").val("");   
           /* var obj = document.getElementById("hiddensel");  
             var selobj = document.getElementById("selsel");
       	         for(var i=0; i<obj.options.length; i++) {  
       	        		var newoption = new Option(obj.options[i].value,obj.options[i].text);
		        	selobj.options.add(newoption);
	          }  */
	     }
	     function  inputblur(){  
		    if ($("#entrustinput").val()=="")
                  $("#entrustinput").val("输入可进行过滤");   
	     }
	     function inputchange(){  
           var obj = document.getElementById("hiddensel"); 
           var selobj = document.getElementById("selsel");
           var inputobj = document.getElementById("entrustinput");
           selobj.options.length=0;
     	       for(var i=0; i<obj.options.length; i++) {  
       	        if(obj.options[i].text.indexOf(inputobj.value) != -1) {  
                    var newoption = new Option(obj.options[i].text,obj.options[i].value);
		        	selobj.options.add(newoption);
                }  
	       }  
        }
		function selchange(){  
	       var obj = document.getElementById("selsel");
	       var val = obj.options[obj.selectedIndex].text;
 		   var obj2 = document.getElementById("entrustinput");
           obj2.value = val;
	    }
	    function check(){
	          if ($("#entrustinput").val()=="输入可进行过滤"){
                    alert("请输入或选择委托单位！");
	                return false;
              }
	          if ($("#receivedate").val()>$("#finishdate").val()){
	                  alert("检查完成日期不能早于接收样品日期！");
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
                    if (len1!=len2){
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
			  // 查询是否有重复编号
			  checkCode();
              if (flag){
                 alert("样品编号已经存在，请重新修改！");
                 return false;
              }
	    }
	    
	    function setUserInfo(){
	       $.ajax({
	            async:false,
	            type:'get',
				url: 'getUserInfoSampleRegiste.html',
				data:{ userid: $("#selectsenduser").val()},
				dataType:'json',
				success: function(data) {
				     if (data==null||data=="") return;
				     var info = eval("("+data+")");
				     
				     $("#selectrelation").val(info.tel);
				     $("#selectaddress").val(info.address);
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
				data:{ regid: "",regcode:$("#sampleno").val()},
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
		           alert('编号检查出错！或者后三位不是流水号！');    
		        }
			});
	    }
		</script>
		
	</body>
</html>
