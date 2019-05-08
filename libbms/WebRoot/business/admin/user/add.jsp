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
		<h1>添加员工</h1>
		<s:form action="saveUser" namespace="/business/admin">
		<table class="frmtable">
		<tr><td><span class="mustfill">*</span>用户名称：</td><td><s:textfield id="username" name="user.showname" data-validation="length" data-validation-length="1-20" data-validation-error-msg="字数范围在(1-20)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td>联系电话：</td><td><s:textfield  name="user.telphone" data-validation="length" data-validation-length="0-15" data-validation-error-msg="字数范围在(0-15)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td>所属科室：</td><td><s:select  name="user.office" list="map" cssClass="longtext"></s:select></td></tr>
		<tr><td>职务：</td><td><s:textfield  name="user.duty" data-validation="length" data-validation-length="0-15" data-validation-error-msg="字数范围在(0-15)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>系统用户名称：</td><td><s:textfield id="name" name="user.name" data-validation="length" data-validation-length="1-15" data-validation-error-msg="字数范围在(1-15)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td><span class="mustfill">*</span>用户密码：</td><td><s:password  name="user.password" data-validation="length" data-validation-length="1-8" data-validation-error-msg="字数范围在(1-8)" cssClass="longtext"></s:password></td></tr>
		<tr><td>用户类型：</td><td><s:radio list="#{'A':'领导用户','D':'室主任','B':'接样员','C':'实验员'}" name="user.authority" value="D" checked="checked" /></td></tr>
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
	         // 查询是否有重复系统用户名称
			 checkName();
             if (flag){
                 alert("相同的系统用户名称已经存在，请重新修改！");
                 return false;
              }
	    }
	    
	    function checkName(){
	       $.ajax({
	            async:false,
	            type:'get',
				url: 'business/admin/isExistsUser.html',
				data:{uid:-1, name:$("#name").val()},
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
		           alert('校验系统用户名称出错！');    
		        }
			});
	    }
		</script>
	</body>
</html>
