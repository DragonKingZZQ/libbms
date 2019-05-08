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
		<h1>修改预付款</h1>
		<s:form action="updatePrepayment" namespace="/business">
		<table class="frmtable">
		<s:hidden id="prepayid" name="prepayment.id"></s:hidden>
		<tr id="tr_inputcompany"><td><span class="mustfill">*</span>付款单位：</td><td>
			    <s:textfield type="hidden" id="enid" name="prepayment.entrustcompanyid" />
			    <s:textfield  id="company" name="prepayment.entrustcompanyStr" readOnly="true" cssClass="hiddenlongtext"/><span class="textdesc"></span>
		</td></tr>
		<tr><td><span class="mustfill">*</span>单位总预付款余额：</td><td><s:textfield id="ye" name="prepayment.balancemoney"  readOnly="true"  cssClass="hiddenshorttext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>联系人：</td><td>
                <s:select  list="#{}"  id="hiddenseluser"  style="display:none"></s:select>
                <input type="hidden" id="userid" name="prepayment.userid" value="${prepayment.userid}"/>
			    <s:textfield id="user" onkeyup="inputuserchange()" name="prepayment.username" class="longtext"/><span class="textdesc"> 输入查询</span>
			    <div id="userDiv" style="margin-left: 0px;position:absolute;width: 270px;border: 1px solid black;background: white;display:none;">
					<ul id="userUl" style="line-height:24px;">
					</ul>
				</div>
		</td></tr>
		<tr><td><span class="mustfill">*</span>该联系人预付款余额：</td><td><s:textfield id="lxrye" readOnly="true"  cssClass="hiddenshorttext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>联系地址：</td><td><s:textfield id="address" name="prepayment.address" data-validation="length" data-validation-length="1-200" data-validation-error-msg="字数范围在(1-200)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>联系电话：</td><td><s:textfield id="tel"  name="prepayment.tel" data-validation="length" data-validation-length="1-50" data-validation-error-msg="字数范围在(1-50)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>预付日期：</td><td>
		<s:textfield id="paydate" name="prepayment.prepaydate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="true" cssClass="hiddenshorttext">
		<s:param name="value"><s:date name="prepayment.prepaydate" format="yyyy-MM-dd"/></s:param></s:textfield>
		</td></tr>
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
	
		onload=function(){
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
                     initUser();
				},
				error: function(){                         
		           alert('获取预付款余额信息出错！');    
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
				        }
				      } 
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
		      $("#lxrye").val("0");
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