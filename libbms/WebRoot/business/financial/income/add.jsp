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
		<h1>添加结算发报告</h1>
		<s:form action="saveIncome" namespace="/business">
		<table class="frmtable">
		<tr><td><span class="mustfill">*</span>付款单位：</td><td>
		      <s:select  list="entrustcompanyList"  id="hiddensel" style="display:none"></s:select>
			  <input type="hidden" id="enid" name="income.entrustcompanyid" />
			  <input type="text" id="company" onkeyup="inputchange()" name="income.entrustcompany" value="" class="longtext"/><span class="textdesc"> 输入查询</span>
			    <div id="companyDiv" style="margin-left: 0px;position:absolute;width: 270px;border: 1px solid black;background: white;display:none;">
					<ul id="companyUl" style="line-height:24px;">
					</ul>
				</div>
		</td></tr>
		<tr><td>单位总预付款余额：</td><td><s:textfield id="ye" name="income.balancemoney"  readOnly="true"  cssClass="hiddenshorttext"></s:textfield></td></tr>
    	<tr><td><span class="mustfill">*</span>联系人：</td><td>
                <s:select  list="#{}"  id="hiddenseluser"  style="display:none"></s:select>
                <input type="hidden" id="userid" name="income.userid"/>
			    <input type="text" id="user" onkeyup="inputuserchange()" name="income.username" value="" class="longtext"/><span class="textdesc"> 输入查询</span>
			    <div id="userDiv" style="margin-left: 0px;position:absolute;width: 270px;border: 1px solid black;background: white;display:none;">
					<ul id="userUl" style="line-height:24px;">
					</ul>
				</div>
		</td></tr>
		<tr><td>联系人预付款余额：</td><td><s:textfield id="lxrye"  readonly="true"  cssClass="hiddenlongtext"></s:textfield>
		<tr><td><span class="mustfill">*</span>联系地址：</td><td><s:textfield id="address" name="income.address" data-validation="length" data-validation-length="1-200" data-validation-error-msg="字数范围在(1-200)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>联系电话：</td><td><s:textfield id="tel" name="income.tel" data-validation="length" data-validation-length="1-50" data-validation-error-msg="字数范围在(1-50)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>付款日期：</td><td><s:textfield id="paydate" name="income.paydate" data-validation="date" data-validation-format="yyyy-mm-dd" data-validation-error-msg="不能为空" readonly="readonly" cssClass="shorttext"></s:textfield></td></tr>
		<tr><td>结算方式：</td><td><div><input type="checkbox" id="precal" name="income.precal" onclick="clearSyscal(this)"/>预付款结算 | <a href="javascript:void(0)" onclick="addRegiste()">添加需结算登记单</a></div></td></tr>
		<tr><td></td><td id="calculate"></td></tr>
		<tr><td>合计检测费：</td><td><s:textfield id="jcje" readonly="true" name="income.totalcheckmoney" cssClass="hiddenlongtext"></s:textfield>
		<tr id="tr_pay"><td><span class="mustfill">*</span>付款金额：</td><td><s:textfield id="nowcash"  name="income.paymoney" data-validation="number" data-validation-allowing="float" data-validation-error-msg="只能输入数字" value="0" cssClass="shorttext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>收款人：</td><td><s:textfield  name="income.receiveuser" data-validation="length" data-validation-length="1-50" data-validation-error-msg="字数范围在(1-50)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td>发票类型：</td><td><s:radio id="optbill" list="#{'1':'增值税专用发票','2':'增值税普通发票','3':'未确定'}" name="income.taxtype" value="0" checked="checked" /></td></tr>
		<tr><td><span class="mustfill">*</span>发票是否开具：</td><td><s:radio id="optbill" list="#{'1':'已开','0':'未开'}" name="income.billischeck" value="0" checked="checked" onclick="billcheck()" /></td></tr>
		<tr id="tr_billno"><td>发票发放方式：</td><td><s:textfield name="income.billno" id="billno" data-validation="length" data-validation-length="0-50" data-validation-error-msg="字数范围在(0-200)"  cssClass="longtext"></s:textfield></td></tr>
		<tr>
           	<td colspan="2" class="textdesc">注意：以上<span class="mustfill">*</span>标识的为必填项</td>
        </tr>
		<tr><td colspan="2" align="center"><s:submit value="保 存" onclick="return check()" ></s:submit><input value="返 回" type="button" onclick="history.back();" /></td></tr>
		</table>
		</s:form>
        </div>
		</div>
		</div>
		<script type="text/javascript">
		$.validate({modules : 'platform'});
		set_date_picker($('#paydate'));
		var count = 0;
		
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
		function changeunit(obj){
		   for(var i=0;i<count;i++){
	            var selobj = document.getElementById('div'+i);
	            if (selobj!=null){
	                $(selobj).remove();
	            }
		   }
		   count = 0;
		}
		function removeRegiste(obj){
		   if (obj.checked){
		       for(var i=0;i<count;i++){
		            var selobj = document.getElementById('div'+i);
		            if (selobj!=null){
		                $(selobj).remove();
		            }
		       }
		       $("#precal").attr("checked",false);
		       $("#tr_pay").val("0");
		       $("#tr_pay").show();
		       count = 0;
		   }
		}
		function clearSyscal(obj){
		   if (obj.checked){
		       $("#caltype").attr("checked",false);
		       $("#tr_pay").hide();
		   }else{
		       $("#tr_pay").val("0");
		       $("#tr_pay").show();
		   }
		}
		function removeItem(obj){
		   $(obj).remove();
		   
		   calneedmoney();
		}
		function addRegiste(){
		   if ($("input:checkbox[name='income.caltype']:checked").val()=="on"){
		      alert("选择系统结算就不必选择对应的样品登记单，系统将自动按登记单时间顺序结算登记单！ ");
		      return;
		   }
		   $("#calculate").append('<div id="div'+count+'">样品登记： <select id="regid'+count+'" name="registeid" onchange="getBalance(regid'+count+',b'+count+')" />未付费用： <s:textfield id="b'+count+'" cssClass="hiddenshorttext" readonly="true"/><a href="javascript:void(0)" onclick="removeItem(div'+count+')">移除</a></div>');
          
           count = count +1;
           getRegisteList($("#enid").val());
           
           
		}
		function getRegisteList(obj){
		     $.ajax({
	            type:'get',
				url: 'getRegisteInfoIncome.html',
				data:{companyid: obj},
				dataType:'json',
				success: function(data) {
				     if (data==null||data=="") return;
				     var info = eval(data);
				     var newobj = document.getElementById("regid"+(count-1));
				     for(var i = 0;i < info.length;i++){
				        var id = info[i].key;
				        var name = info[i].value;
				        var newoption = new Option(name,id);
					    newobj.options.add(newoption);
				     }
				     var b = document.getElementById("b"+(count-1));
				     getBalance(newobj,b);
				},
				error: function(){                         
		           alert('获取样品登记 信息出错！');    
		        }
			});
		}
		//得到 检验项目的未付金额 
		function getBalance(selobj,txtobj){
		     $.ajax({
	            type:'get',
				url: 'getBalanceIncome.html',
				data:{myregid: $(selobj).val()},
				dataType:'json',
				success: function(data) {
				     if (data==null||data=="") return;
				     var info = eval(data);
                     $(txtobj).val(info);
                     
                     calneedmoney();
				},
				error: function(){                         
		           alert('获取样品登记未付检验费用出错！');    
		        }
			});
		}
		//计算金额
		function calneedmoney(){
		  var money = 0;
		  for(var i=0;i<count;i++){
             var obj = document.getElementById("b"+i);
             if(obj!=null&&obj!="undefined"){
                money = parseFloat(money)+parseFloat(obj.value);
             }
          }
          $("#jcje").val(money);
		}
		//得到需要付款金额
		function needMoney(){
		      var money = 0;
		      if ($("input:checkbox[name='income.precal']:checked").val()=="on"){
		          for(var i=0;i<count;i++){
		             var obj = document.getElementById("b"+i);
		             if(obj!=null&&obj!="undefined"){
		                money = parseFloat(money)+parseFloat(obj.value);
		             }
		          }
		          $("#nowcash").val(money);
		          
		          // 如果预付款金额小于需要付款的总金额
		         // if (parseFloat($("#nowcash").val())>parseFloat($("#ye").val())){
		          //         alert("预付款余额不足，请重新选择登记单或者选择其他结算方式！");
		          //         return false;
		          //}
		          if (parseFloat($("#lxrye").val())<parseFloat($("#jcje").val())){
			          alert("该联系人预付款余额小于应付金额，不能预付款结算 ！ ");
			          return false;
			      }
		      }else{
		           if (parseFloat($("#nowcash").val())==0){
		                alert("付款金额必须大于0，请重新输入！");
		                return false;
		           }
		           
		           if (parseFloat($("#nowcash").val())<parseFloat($("#jcje").val())){
			          alert("该联系人付款金额小于应付金额，不能结算 ！ ");
			          return false;
			      }
		      }
		      return true;
		}
		function check(){
		
		      if ($("#enid").val()==null||$("#enid").val()==""){
		          alert("请选择付款单位 ！ ");
		          return false;
		      }
		      if ($("input:checkbox[name='income.caltype']:checked").val()!="on"&&count==0){
		          alert("请增加样品登记单 ！ ");
		          return false;
		      }
		      if(!needMoney()) return false;
			  // 选择的检验项目中，是否有重复的检验项目
	          if($("select[name=registeid]").length>=2){
				    var arrText = new Array();
				    var idx = 0;
				    for(var i=0;i<count;i++){
                        var obj = document.getElementById("regid"+i);
                        if (obj!=null){
	                        arrText[idx] = obj.value;
	                        idx = idx +1;
                        }
                    }
                    var len1 = arrText.length;
                    var len2= arrText.unique().length;
                    if (len1!=len2){
	                    alert("在增加的样品登记单中有重复数据，请重新选择！");
					    return false;
				    }
			  }
			  return true;
	    }
	    
	    function inputchange(){  
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
