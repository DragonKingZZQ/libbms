<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path %>/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery.addition.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery.form-validator.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/common.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body >
    <s:form id="submit_review" action="" namespace="">
        <s:textfield name="ids" id="ids" value="%{#parameters.ids}"/>  
    	<s:textfield name="actname" id="actname" value="%{#parameters.actname}"/>
    	<table border="0" cellpadding="0" cellspacing="0"  class="frmtable">
		<tr><td>批复意见：</td></tr>
		<tr><td><s:textarea name="review" data-validation="length" data-validation-length="1-200" data-validation-error-msg="字数范围在(1-200)" cols="60" rows="20"></s:textarea></td></tr>
		<tr><td>还可以输入<span id="textarea_length_max">200</span>字</td><td></td></tr>
		<tr><td  align="center"><a href="javascript:void(0)" onclick="back()">确定</a> | <a href="javascript:void(0)" onclick="closeWin()">取消</a></td></tr>
		</table>
		</s:form>
		<script type="text/javascript">
		$.validate({modules : 'platform'});
		$('textarea').restrictLength( $('#textarea_length_max') );
		
		function back(){
		    window.parent.opener=null;
		    window.parent.close();
		   // window.top.close();
		    $("#submit_review").attr("action",$("#actname").val());
			$("#submit_review").submit();
		}
		function closeWin(){
			if (confirm("您确定取消本次操作吗？")){
			    window.opener=null;
	            window.open('','_self');
	            window.close();
            }
		}
		</script>
  </body>
</html>
