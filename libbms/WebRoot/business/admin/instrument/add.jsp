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
		<h1>添加检验仪器</h1>
		<s:form action="saveInstrument" namespace="/business/admin">
		<table class="frmtable">
		<tr><td><span class="mustfill">*</span>编码名称：</td><td><s:textfield id="codename" name="instrument.codename" data-validation="length" data-validation-length="1-200" data-validation-error-msg="字数范围在(1-200)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td>规格型号：</td><td><s:textfield  name="instrument.standard" data-validation="length" data-validation-length="0-100" data-validation-error-msg="字数范围在(0-100)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td>测量范围：</td><td><s:textfield  name="instrument.measueRange" data-validation="length" data-validation-length="0-100" data-validation-error-msg="字数范围在(0-100)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td>准确度等级：</td><td><s:textfield  name="instrument.grade" data-validation="length" data-validation-length="0-100" data-validation-error-msg="字数范围在(0-100)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td>生产单位：</td><td><s:textfield  name="instrument.product" data-validation="length" data-validation-length="0-300" data-validation-error-msg="字数范围在(0-300)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td>校准单位：</td><td><s:textfield  name="instrument.correctCompany" data-validation="length" data-validation-length="0-300" data-validation-error-msg="字数范围在(0-300)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td>校准周期：</td><td><s:textfield  name="instrument.correctCycle" data-validation="length" data-validation-length="0-50" data-validation-error-msg="字数范围在(0-50)" cssClass="longtext"></s:textfield></td></tr>
		<tr><td>最近校准日期：</td><td><s:textfield id="recentdate" name="instrument.recentCorrectDate"  readonly="readonly" cssClass="shorttext"></s:textfield></td></tr>
		<tr><td>下次校准日期：</td><td><s:textfield id="nextdate" name="instrument.nextCorrectDate"  readonly="readonly" cssClass="shorttext"></s:textfield></td></tr>
		<tr><td>备注：</td><td><s:textarea name="instrument.remark" cssStyle="width:400px;height:110px;" data-validation="length" data-validation-length="0-500" data-validation-error-msg="字数范围在(0-500)" ></s:textarea></td></tr>
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
		set_date_picker($('#recentdate'));
		set_date_picker($('#nextdate'));
		
	    var flag = false;
	    
	    function check(){
	         // 查询是否有重复编号
			 checkCode();
             if (flag){
                 alert("相同的仪器编码名称已经存在，请重新修改！");
                 return false;
              }
	    }
	    
	    function checkCode(){
	       $.ajax({
	            async:false,
	            type:'get',
				url: 'business/admin/isExistsInstrument.html',
				data:{iid:-1, codename:$("#codename").val()},
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
		           alert('检验仪器编码名称出错！');    
		        }
			});
	    }
		</script>
	</body>
</html>
