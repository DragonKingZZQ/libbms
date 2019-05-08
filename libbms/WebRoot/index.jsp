<%@ page language="java" import="java.util.*" isErrorPage="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
response.setStatus(HttpServletResponse.SC_OK);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=path %>/css/layout.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/css/menu.css" rel="stylesheet" type="text/css" />
<style> 
  .black_overlay{  display: none;  position: absolute;  top: 0%;  left: 0%;  width: 100%;  height: 100%;  background-color: black;  z-index:1001;  -moz-opacity: 0.8;  opacity:.80;  filter: alpha(opacity=20);  }  
  .white_content {  display: none;  position: absolute;  top: 25%;  left: 25%;  width: 40%;  height: 45%;  padding: 16px;  border: 6px solid gray;  background-color: white;  z-index:1002;  overflow: auto;  }  
</style> 
</head>
<body>
   <div id="pagebody">
		<s:include value="/include/pb-header.jsp"></s:include>
		<div id="mainbody">
		     <s:include value="/include/left-bar.jsp"></s:include>
		     <div id="rightbar">
	              <s:form action="index" namespace="/business">
		               <iframe id="myframe" name="myframe"  src="<%=path%>/index/showinfo.html" frameborder="0" width="770" height="50%" style="border:0px solid #CCC; margin:0; padding:0;" onLoad="iFrameHeight()" ></iframe>
		          </s:form>
	         </div>
        </div>
   </div>
   <div id="light" class="white_content"></div> 
	<div id="fade" class="black_overlay"></div>
   <input  type="hidden" id="warncount" value="<s:property value='warnInfo.list.size()'/>">
   <input  type="hidden" id="warnforleadercount" value="<s:property value='warnInfoForLeader.list.size()'/>">
   <input  type="hidden" id="usertype" value="<s:property value='user.getAuthority()'/>">
   <input  type="hidden" id="finishedcheck" value="<s:property value='finishedcheckNo'/>">
	<s:include value="/include/footer-info.jsp"></s:include>
	<s:include value="/include/footer-script.jsp"></s:include>	
	<script type="text/javascript">
	    var oPath = true;
	    var oRight ;

		 function DoMenu(emid)
		{
			 var obj = document.getElementById(emid); 
			 obj.className = (obj.className.toLowerCase() == "expanded"?"collapsed":"expanded");
		}
		/*
		function leftbarclick(){
		    oRight = document.getElementById('leftbar');
		    if(oPath){
		    	//dMove(oRight, 'right', 100);
		    	//oRight.style.display="none";
		    	oRight.style.width="5px";
		    	document.getElementById('mainbody').style.left="-500px";
		    	oPath = false;
		    }else{
		    	dMove(oRight, 'right', -400);
		    	oPath = true;
		    }
		} */
	    function dMove(obj, attr, iT){
		    var iCur = 0;
		    iCur = 10;
		    var iS = (iT - iCur) ;
		    iS = iS > 0 ? Math.ceil(iS) : Math.floor(iS);
		    //obj.style[attr] = iCur+iS+'px';
		     obj.style[attr] = '-400px';
	    }
	    
	    function chgStyle(v){
	        if (v==1)
		       document.getElementsByTagName("link")["grayStyle"].href = "test.css";
		    else
		       document.getElementsByTagName("link")["grayStyle"].href = "test2.css";
		}
		//自适应高度
		function iFrameHeight() { 
			var ifm= document.getElementById("myframe"); 
			var subWeb = document.frames ? document.frames["myframe"].document : ifm.contentDocument; 
			if(ifm != null && subWeb != null) { 
				ifm.height = subWeb.body.scrollHeight+50; 
			} 
		} 
		onload = function(){
		    //  setInterval(showTask,30000); 
		};		
		
		function showTask(){
		    var flag = 0;
		    if($("#warncount").val()!=null&&$("#warncount").val()!=""&&parseInt($("#warncount").val())>0&&$("#usertype").val().indexOf("C")>=0){
		       flag = window.showModalDialog("<%=path%>/popinfo.jsp","width:200px;height:50px;center:yes;status:no;scroll:no;help:no;"); 
		    }
		    if($("#warnforleadercount").val()!=null&&$("#warnforleadercount").val()!=""&&parseInt($("#warnforleadercount").val())>0&&($("#usertype").val()=="A"||$("#usertype").val().indexOf("B")>=0)){
		       flag = window.showModalDialog("<%=path%>/popinfo.jsp","width:200px;height:50px;center:yes;status:no;scroll:no;help:no;"); 
		    }
		    // 如果是登记人员，查询是否有完成的检验，给出提示
		    if($("#finishedcheck").val()!=null&&$("#finishedcheck").val()!=""){
		        //alert("样品登记单编号为："+$("#finishedcheck").val()+" 的检验已经完成，请出检验报告！");
		        $("#light").html("<table border='0'><tr><td>样品登记单编号为："+$("#finishedcheck").val()+" 的检验已经完成，请出检验报告！</td></tr><tr><td align='center'><a href='javascript:void(0)' onclick='hidenotice()'> 关  闭 </a></td></tr></table>");
		        $("#light").show();
		        $("#fade").show();
		    }
		    if (flag==1){
		       window.location.reload();
		    }
		}
		
		function hidenotice(){
		    $("#light").hide();
		    $("#fade").hide();
		}
 	</script>
</body>
</html>