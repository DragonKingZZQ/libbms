<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<body style="background-color:#F98;">
	<s:include value="/include/common.jsp"></s:include>
         <h1>您有待检验的任务即将到期!</h1>
         <h1>请尽快完成检验！谢谢！</h1>
         <h3>具体检验任务请看“首页”列表</h3>
         <p align="center"><s:form action="gotit.html" namespace="/index">
             <input type="button" onclick="iknow()" value="我知道了" /> 
          </s:form></p>
         <script type="text/javascript">
           function iknow(){
	          $.ajax({
	            async:false,
	            type:'get',
				url: '<%=path%>/index/gotit.html',
				success: function(data) {
				    window.returnValue=1; 
				    window.close();
				},
				error: function(){                         
		           alert('修改信息出错！');    
		        }
			});
	    }
	    </script>
	</body>
</html>