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
		<h1>添加结算</h1>
		<s:form action="cashIncome" namespace="/business">
		<table class="frmtable">
		<s:hidden id="enid"  name="sampleRegiste.entrustcompany"></s:hidden>
		<s:hidden  name="sampleRegiste.id"></s:hidden>
		<tr><td><span class="mustfill">*</span>付款单位：</td><td><s:textfield  name="sampleRegiste.entrustcompanyStr" readOnly="true" cssClass="hiddenlongtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>结算登记单号：</td><td><s:textfield  name="sampleRegiste.sampleno" readonly="true" cssClass="hiddenlongtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>登记单检测费：</td><td><s:hidden id="zfy" name="sampleRegiste.balancemoney" value="%{sampleRegiste.checkmoney}"></s:hidden><s:textfield  id="fy"  name="sampleRegiste.checkmoney"  cssClass="longtext" onkeyup="checkmoney()"></s:textfield></td></tr>
		<tr><td>已付金额：</td><td><s:textfield  id="yfje"  name="sampleRegiste.prepaymoney" readonly="true" cssClass="hiddenlongtext"></s:textfield></td></tr>
		<s:if test='sampleRegiste.payedmoney-sampleRegiste.checkmoney>0'>
		   <tr><td>单位预付款余额：</td><td><s:textfield id="ye" name="sampleRegiste.payedmoney" readonly="true"  cssClass="hiddenlongtext"></s:textfield>
		   <br><s:checkbox name="income.precal" value="1" id="pmoney" onclick="changeit()">预付款扣除</s:checkbox>
		   </br>
		</td></tr>
		</s:if><s:else>
	      <tr><td>单位预付款余额：</td><td><s:textfield  id="ye"  name="sampleRegiste.payedmoney" readonly="true"  cssClass="hiddenlongtext"></s:textfield>
	      <br><s:checkbox name="income.precal" value="0" disabled="true" id="pmoney"  onclick="changeit()">预付款扣除</s:checkbox>
	      </br>
	      </td></tr>
	    </s:else>
	    <tr><td><span class="mustfill">*</span>联系人：</td><td>
                <s:select  list="userMap"  id="hiddenseluser"  style="display:none"></s:select>
                <input type="hidden" id="userid" name="username"/>
			    <input type="text" id="user" onkeyup="inputuserchange()" name="sampleRegiste.senduser" value="" class="longtext"/><span class="textdesc"> 输入查询</span>
			    <div id="userDiv" style="margin-left: 0px;position:absolute;width: 270px;border: 1px solid black;background: white;display:none;">
					<ul id="userUl" style="line-height:24px;">
					</ul>
				</div>
		</td></tr>
		<tr><td><span class="mustfill">*</span>联系地址：</td><td><s:textfield id="address" name="income.address" data-validation="length" data-validation-length="1-200" data-validation-error-msg="字数范围在(1-200)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>联系电话：</td><td><s:textfield id="tel" name="income.tel" data-validation="length" data-validation-length="1-50" data-validation-error-msg="字数范围在(1-50)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td>该联系人预付款余额：</td><td><s:textfield id="lxrye" readOnly="true"  cssClass="hiddenshorttext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>付款日期：</td><td><s:textfield id="paydate" name="income.paydate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly" cssClass="shorttext"></s:textfield></td></tr>
         <tr id="tr_pay"><td><span class="mustfill">*</span>付款金额：</td><td><s:textfield id="fkje" name="income.paymoney"  data-validation="number" data-validation-allowing="float" data-validation-error-msg="只能输入数字" value="0" cssClass="shorttext"></s:textfield></td></tr>
	    <tr><td><span class="mustfill">*</span>收款人：</td><td><s:textfield  name="income.receiveuser" data-validation="length" data-validation-length="1-50" data-validation-error-msg="字数范围在(1-50)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td>发票类型：</td><td><s:radio id="optbill" list="#{'1':'增值税专用发票','2':'增值税普通发票','3':'未确定'}" name="income.taxtype" value="0" checked="checked" /></td></tr>
		<tr><td><span class="mustfill">*</span>发票是否开具：</td><td><s:radio id="optbill" list="#{'1':'已开','0':'未开'}" name="income.billischeck" value="0" checked="checked" onclick="billcheck()" /></td></tr>
		<tr id="tr_billno"><td>发票快递单号：</td><td><s:textfield name="income.billno" id="billno" data-validation="length" data-validation-length="0-50" data-validation-error-msg="字数范围在(0-200)"  cssClass="longtext"></s:textfield></td></tr>
		<tr>
           	<td colspan="2" class="textdesc">注意：以上<span class="mustfill">*</span>标识的为必填项</td>
        </tr>
		<tr><td colspan="2" align="center"><s:submit value="结  算" onclick="return check()" ></s:submit><input value="返 回" type="button" onclick="history.back();" /></td></tr>
		</table>
		</s:form>
        </div>
		</div>
		</div>
		<script type="text/javascript">
		$.validate({modules : 'platform'});
		set_date_picker($('#paydate'));
		onload=function(){
		   var mydate = new Date();
		   var str = "" + mydate.getFullYear() + "-";
		   var str1 = (mydate.getMonth()+1) + "-";
		   if (str1.length==2)
		       str1 = "0"+str1;
		   var str2 = mydate.getDate();
		   if (str2<10)
		       str2 = "0"+str2;
		   $("#paydate").val(str+str1+str2);
		   if (parseFloat($("#ye").val())-parseFloat($("#fy").val())>0)
		     $("#tr_pay").hide();
		   else
		      $("#tr_pay").show();
		   $("#fkje").val(parseFloat($("#zfy").val())-parseFloat($("#yfje").val()));
		   getRelationUsers($("#enid").val());
        }	
		function changeit(){
		    var obj = document.getElementById("pmoney");
		    if (obj.checked==true)
		       $("#tr_pay").hide();
		    else
		       $("#tr_pay").show();
		}
		function checkmoney(){
		    if (parseFloat($("#ye").val())>=parseFloat($("#fy").val())){
		         $("#pmoney").attr("disabled",false);
		    }else{
		         $("#pmoney").attr("checked",false);
		         $("#pmoney").attr("disabled",true);
		    }
		}
		function check(){
		   if (parseFloat($("#fy").val())<parseFloat($("#yfje").val())){
		         alert("检测费用不能小于该登记单已经付款金额，请检查！");
		         return false;
		    }
		    if (!confirm("确定结算吗？")){
		       return false;
		    }
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
       	        if(obj.options[i].text.indexOf(inputparam) != -1) {  
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
                     //是F能预付款扣除的状态
                     if (parseFloat($("#lxrye").val())>=(parseFloat($("#zfy").val())-parseFloat($("#yfje").val()))){
                         $("#pmoney").attr("disabled",false);
                     }else{
                         $("#pmoney").attr("disabled",true);
                         $("#pmoney").attr("checked",false);
                     }
                     
                     getUserInfo();
				},
				error: function(){                         
		           alert('获取联系人预付款余额信息出错！');    
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
