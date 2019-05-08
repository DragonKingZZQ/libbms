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
		<h1>添加预付款</h1>
		<s:form action="savePrepayment" namespace="/business">
		<table class="frmtable">
<!-- 		<tr><td>单位选择：</td><td><s:radio list="#{'1':'选择已有单位','2':'录入新单位'}" value="1" name="cmp" onclick="showCompany()"/></td></tr> -->
		<tr id="tr_inputcompany"><td><span class="mustfill">*</span>付款单位：</td><td>
<!-- 		        <s:textfield id="inputcompany" name="prepayment.entrustcompanyStr" data-validation="length" data-validation-length="1-200" data-validation-error-msg="字数范围在(1-200)" cssClass="longtext"></s:textfield> -->
		        <s:select  list="entrustcompanyList"  id="hiddensel" style="display:none"></s:select>
			    <input type="hidden" id="enid" name="prepayment.entrustcompanyid" />
			    <input type="text" id="company" onkeyup="inputchange()" name="prepayment.entrustcompanyStr" value="" class="longtext"/><span class="textdesc"> 输入查询</span>
			    <div id="companyDiv" style="margin-left: 0px;position:absolute;width: 270px;border: 1px solid black;background: white;display:none;">
					<ul id="companyUl" style="line-height:24px;">
					</ul>
				</div>
		</td></tr>
		<tr><td><span class="mustfill">*</span>单位总预付款余额：</td><td><s:textfield id="ye" name="prepayment.balancemoney"  readOnly="true"  cssClass="hiddenshorttext"></s:textfield></td></tr>
<!-- 		<tr id="tr_selectcompany"><td><span class="mustfill">*</span>付款单位：</td><td><s:select  name="prepayment.entrustcompanyid" list="entrustcompanyList"></s:select></td></tr> -->
		<tr><td><span class="mustfill">*</span>联系人：</td><td>
<!-- 		          <s:textfield  name="prepayment.username" data-validation="length" data-validation-length="1-50" data-validation-error-msg="字数范围在(1-50)" cssClass="longtext"></s:textfield> -->
                <s:select  list="#{}"  id="hiddenseluser"  style="display:none"></s:select>
                <input type="hidden" id="userid" name="prepayment.userid"/>
			    <input type="text" id="user" onkeyup="inputuserchange()" name="prepayment.username" value="" class="longtext"/><span class="textdesc"> 输入查询</span>
			    <div id="userDiv" style="margin-left: 0px;position:absolute;width: 270px;border: 1px solid black;background: white;display:none;">
					<ul id="userUl" style="line-height:24px;">
					</ul>
				</div>
		</td></tr>
		<tr><td><span class="mustfill">*</span>该联系人预付款余额：</td><td><s:textfield id="lxrye" readOnly="true"  cssClass="hiddenshorttext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>联系地址：</td><td><s:textfield id="address" name="prepayment.address" data-validation="length" data-validation-length="1-200" data-validation-error-msg="字数范围在(1-200)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>联系电话：</td><td><s:textfield id="tel" name="prepayment.tel" data-validation="length" data-validation-length="1-50" data-validation-error-msg="字数范围在(1-50)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>预付日期：</td><td><s:textfield id="paydate" name="prepayment.prepaydate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly" cssClass="shorttext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>本次预付金额：</td><td><s:textfield  name="prepayment.prepaymoney" data-validation="number" data-validation-allowing="float" data-validation-error-msg="只能输入数字"  cssClass="shorttext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>收款人：</td><td><s:textfield  name="prepayment.receiveruser" data-validation="length" data-validation-length="1-50" data-validation-error-msg="字数范围在(1-50)" cssClass="longtext"></s:textfield></td></tr>
		<tr>
           	<td colspan="2" class="textdesc">注意：以上<span class="mustfill">*</span>标识的为必填项</td>
        </tr>
		<tr><td colspan="2" align="center"><s:submit value="保 存" ></s:submit><input value="返 回" type="button" onclick="history.back();" /></td></tr>
		</table>
		</s:form>
        </div>
		</div>
		</div>
		<script type="text/javascript">
		$.validate({modules : 'platform'});
		set_date_picker($('#paydate'));
		function inputchange(){  
		   $("#enid").val("-1");
		   $("#ye").val("0");
		   $("#lxrye").val("0");
		   $("#userid").val("-1");
		   $("#user").val("");
		   $("#address").val("");
		   $("#tel").val("");
	       var inputparam = $("#company").val();
		   if(inputparam == ''){
				$("#companyDiv").hide();
				return;
		   }
	       var lis = "";
           var obj = document.getElementById("hiddensel"); 
   	       for(var i=0; i<obj.options.length; i++) {  
       	        if(obj.options[i].text==inputparam) {
       	            $("#enid").val(obj.options[i].value);
		            $("#companyDiv").hide();  
		            selOrg2(obj.options[i].value+"|"+obj.options[i].text);
		        	return;
                }else if(obj.options[i].text.indexOf(inputparam) != -1) {  
		        	lis += '<li style="cursor:pointer" onclick="selOrg(this)">'+obj.options[i].value+'|'+obj.options[i].text+'</li>';
                }  
	       }  
	       
	       if (lis!=""){
	          $("#companyUl").html(lis);
		      $("#companyDiv").show();
		   }else{
		      $("#enid").val("-1");
		      $("#companyDiv").hide();
		   }
        }
        
        function selOrg(liObj){
			var html = $(liObj).html();
			if(html == '' || html  == '&nbsp'){
				return;
			}
			var arr = html.split("|");
			$("#company").val(arr[1]);
			$("#enid").val(arr[0]);
			$("#companyDiv").hide();
			$.ajax({
	            type:'get',
				url: 'getPayedMoneyIncome.html',
				data:{companyid: $("#enid").val()},
				dataType:'json',
				success: function(data) {
				     var info = eval(data);
                     $("#ye").val(info);
                     //调用当前单位的联系人
                     getRelationUsers($("#enid").val());
				},
				error: function(){                         
		           alert('获取预付款余额信息出错！');    
		        }
			});
		}
		function selOrg2(str){
			var arr = str.split("|");
			$("#company").val(arr[1]);
			$("#enid").val(arr[0]);
			$("#companyDiv").hide();
			$.ajax({
	            type:'get',
				url: 'getPayedMoneyIncome.html',
				data:{companyid: $("#enid").val()},
				dataType:'json',
				success: function(data) {
				     var info = eval(data);
                     $("#ye").val(info);
                     //调用当前单位的联系人
                     getRelationUsers($("#enid").val());
				},
				error: function(){                         
		           alert('获取预付款余额信息出错！');    
		        }
			});
		}
	    function getRelationUsers(val){
			if(val == ''){
				return;
			}
			$.ajax({
	            type:'get',
				url: 'getUsersForPayIncome.html',
				data:{companyid: val},
				dataType:'json',
				success: function(data) {
				     if (data==null||data=="") return;
				     var info = eval(data);
				     var userobj = document.getElementById("hiddenseluser");
				     //先清除数据
				     for(var i=userobj.options.length-1;i>=0;i--) {
                        userobj.options.remove(i); 
                     }

                     for(var o in info){ 
				        if (o!=null&&o!="unique"){
					        var val = info[o].userid;
						    var name = info[o].user;
						    var newoption = new Option(name,val);
						    userobj.options.add(newoption);
						    //赋值
						    $("#userid").val(info[o].userid);
						    $("#user").val(info[o].user);
					        $("#address").val(info[o].address);
					        $("#tel").val(info[o].tel);
				        }
				      } 
				      initUser();
				},
				error: function(){                         
		           alert('获取预付款联系人信息出错！');    
		        }
			});
		}	
		function inputuserchange(){  
		   $("#userid").val("-1");
		   $("#lxrye").val("0");
		   $("#address").val("");
		   $("#tel").val("");
	       var inputparam = $("#user").val();
		   if(inputparam == ''){
				$("#userDiv").hide();
				return;
		   }
	       var lis = "";
           var obj = document.getElementById("hiddenseluser"); 
   	       for(var i=0; i<obj.options.length; i++) {  
   	            if(obj.options[i].text==inputparam) {  
		        	$("#userid").val(obj.options[i].value);
		        	$("#userDiv").hide();
		        	selUserOrg2(obj.options[i].value+"|"+obj.options[i].text);
		        	return;
                }else if(obj.options[i].text.indexOf(inputparam) != -1) {  
		        	lis += '<li style="cursor:pointer" onclick="selUserOrg(this)">'+obj.options[i].value+'|'+obj.options[i].text+'</li>';
                }  
	       }  
	       
	       if (lis!=""){
	          $("#userUl").html(lis);
		      $("#userDiv").show();
		   }else{
		      $("#userid").val("-1");
		      $("#userDiv").hide();
		   }
        }
        
        function selUserOrg(liObj){
			var html = $(liObj).html();
			if(html == '' || html  == '&nbsp'){
				return;
			}
			var arr = html.split("|");
			$("#user").val(arr[1]);
			$("#userid").val(arr[0]);
			$("#userDiv").hide();
			$.ajax({
	            type:'get',
				url: 'getUserPayedMoneyIncome.html',
				data:{companyid: $("#enid").val(),username:$("#userid").val()},
				dataType:'json',
				success: function(data) {
				     var info = eval(data);
                     $("#lxrye").val(info);
                     getUserInfo();
				},
				error: function(){                         
		           alert('获取联系人预付款余额信息出错！');    
		        }
			});
		}
		function selUserOrg2(str){
			var arr = str.split("|");
			$("#user").val(arr[1]);
			$("#userid").val(arr[0]);
			$("#userDiv").hide();
			$.ajax({
	            type:'get',
				url: 'getUserPayedMoneyIncome.html',
				data:{companyid: $("#enid").val(),username:$("#userid").val()},
				dataType:'json',
				success: function(data) {
				     var info = eval(data);
                     $("#lxrye").val(info);
                     getUserInfo();
				},
				error: function(){                         
		           alert('获取联系人预付款余额信息出错！');    
		        }
			});
		}
		function initUser(){
		    $.ajax({
	            type:'get',
				url: 'getUserPayedMoneyIncome.html',
				data:{companyid: $("#enid").val(),username:$("#userid").val()},
				dataType:'json',
				success: function(data) {
				     var info = eval(data);
                     $("#lxrye").val(info);
				},
				error: function(){                         
		           alert('获取联系人预付款余额信息出错！');    
		        }
			});
		}
		function getUserInfo(){
		    $.ajax({
	            type:'get',
				url: 'getUserInfoIncome.html',
				data:{username:$("#userid").val()},
				dataType:'json',
				success: function(data) {
				    var info = eval("("+data+")");
				    //赋值
				    $("#userid").val(info.userid);
				    $("#user").val(info.user);
			        $("#address").val(info.address);
			        $("#tel").val(info.tel);

				},
				error: function(){                         
		           alert('获取联系人信息出错！');    
		        }
			});
		}
	   	</script>
	</body>
</html>
