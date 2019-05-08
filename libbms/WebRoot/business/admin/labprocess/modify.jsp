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
		<h1>修改实验过程数据</h1>
		<s:form action="updateLabProcess" namespace="/business/admin">
		<table class="frmtable">
		<s:hidden id="labProcessid" name="labProcess.id"></s:hidden>
		 <tr><td style="float:right"><span class="mustfill">*</span>实验过程标题：</td><td><s:textfield id="labprocesstitle" name="labProcess.labprocesstitle" data-validation="length" data-validation-length="1-50" data-validation-error-msg="字数范围在(1-50)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td style="float:right"><span class="mustfill">*</span>实验过程内容：</td><td><s:textarea rows="7" cols="40" id="labprocesscontent" style="font-size:22px;overflow-y:scroll;" name="labProcess.labprocesscontent" data-validation="length" data-validation-length="1-50" data-validation-error-msg="字数范围在(1-50)" cssClass="longtext"></s:textarea></td></tr> 
		
		<tr>
		
		
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
	    var flag = false;
	    
	    function check(){
	           // 查询是否有重复编号
			 checkCode();
             if (flag){
                 alert("实验过程标题名称已经存在，请重新修改！");
                 return false;
              }
	    }
	    
	    function checkCode(){
	       $.ajax({
	            async:false,
	            type:'get',
				url: 'business/admin/isExistsLabProcess.html',
				data:{cid:$("#labProcessid").val(),labprocesstitle:$("#labprocesstitle").val()},
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
		           alert('实验过程标题出错！');    
		        }
			});
	    }
		</script>
	</body>
</html>
