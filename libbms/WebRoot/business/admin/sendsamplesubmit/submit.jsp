<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		
	</head>
	<body >
	<div style="border:1px soild black;height:100px;"></div>
	<div >
		
		<s:include value="/include/common.jsp"></s:include>
	<div id="pagebody">
		 <div id="mainbody" style="border:0;">
		      <div id="contentbar" style="text-align:center;">
					<h1 style="letter-spacing:8px;font-size:30px;padding-bottom:30px;">扫码枪提交</h1>
					<div class="main-blk">
							<s:form action="findSubSendSampleNoQrCode"  namespace="/business/admin">
							<table border="0" cellpadding="0" cellspacing="0" class="lsttable">
							<tr>
                                   <td>样品编号：</td><td><s:textfield id="subSendSampleNo1" name="subSendSampleNo"></s:textfield></td>
                                   <td>
                                   <s:submit value="查询"  onclick="return check()"></s:submit>
                                   <!--  <input type="image" name="imageField" id="imageField" onclick="clearPageNo()" src="<%=path %>/img/search.jpg" />
                                   -->
                                   </td>
								
							</tr>
							</table>
							</s:form>
							<div>
						<div class="cls"></div>
						
						<s:form action="submitSubSendSampleNoQrCode" namespace="/business/admin">
			
				<table  border="0" cellpadding="0" cellspacing="0" class="lsttable">
						<thead>
						<tr>
    					<th  scope="col">样品编号</th>
    						<th scope="col">样品名称</th>
    					<th  scope="col">项目名称</th>
    					<th  scope="col">操作</th>
						</tr>
					    </thead>
					    <tbody>
					    <tr>
					    <td>${subSendSampleNo}
					    </td>
					     <td style="display:none;"><s:textfield name="subSendSampleNo" ></s:textfield>
					    </td>
					    <td>${sendSampleanme}
					</td>
					 <td style="display:none;"><s:textfield name="sendSampleanme" ></s:textfield>
					    </td>
					    <td>${itemName}
					</td>
					 <td style="display:none;"><s:textfield name="itemName" ></s:textfield>
					    </td>
					<td colspan="2" align="center"><s:submit id="submit3" value="提交" onclick="return check1()"></s:submit>
					</td>
					    </tr>
					    </tbody>
		</s:form>
		
						

	</table>
					    </div>
					</div>
			 </div>
		</div>
	</div>	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	</div>
	<script type="text/javascript">
		set_date_picker($('#receivedate'));
		
		$.validate({modules : 'platform'});
	    var flag = false;
	    
	    function check1(){
	    	var submit3 = $("#submit3");
	    	submit3.css("color", "#C0C0C0");
	    	
	    	
           alert("提交成功");
               
	    }
	    function check(){
	    	var s = $("#subSendSampleNo1").val();
	    	if(s==""){
	    	
	    		alert("样品编号不能为空");
	    		return false;
	    	}
	    	checkCode();
            if (flag){
                alert("该项目已提交！");
                return false;
             }
         }
            function checkCode(){
            	$.ajax({
     	            async:false,
     	            type:'get',
     				url: 'business/admin/isExistsSubSendSampleNoQrCode.html',
     				data:{subSendSampleNo:$("#subSendSampleNo1").val()},
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
     		           //alert('扫描出错！！');    
     		        }
     			});
	    }
	   
		</script>
	</body>
</html>
