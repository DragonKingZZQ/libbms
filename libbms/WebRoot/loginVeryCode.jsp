<%@ page language="java" import="java.util.*" isErrorPage="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
response.setStatus(HttpServletResponse.SC_OK);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="<%=path %>/css/styles.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div style="align:center;">
<table border="0" cellpadding="0" cellspacing="0" style="margin:0px auto; width:80%;">
 <tr>
   <td><img src="img/spacer.gif" width="330" height="1" border="0" alt="" /></td>
   <td><img src="img/spacer.gif" width="362" height="1" border="0" alt="" /></td>
   <td><img src="img/spacer.gif" width="332" height="1" border="0" alt="" /></td>
   <td><img src="img/spacer.gif" width="1" height="1" border="0" alt="" /></td>
  </tr>

  <tr>
   <td colspan="3"><img name="n2_r1_c1" src="img/r1_c1.jpg" width="1024" height="195" border="0" id="n2_r1_c1" alt="" /></td>
   <td><img src="img/spacer.gif" width="1" height="195" border="0" alt="" /></td>
  </tr>
  <tr>
   <td rowspan="2"><img name="n2_r2_c1" src="img/r2_c1.jpg" width="330" height="405" border="0" id="n2_r2_c1" alt="" /></td>
   <td bgcolor="#FF8202">
        <div style="width:350px; float:right;margin-top:0px;">
        <s:form action="dologin" namespace="/">
          <s:hidden name="systype"></s:hidden>
          <table border="0" cellpadding="0" cellspacing="0" style="color:#666666">
            <tbody>
              <tr>
                <td nowrap="nowrap">用户名&nbsp; </td>
                <td><div style="width:250px;border:1px solid #cccccc; margin-top:0px;">
                    <div class="float_left">
                      <input name="user.name" id="name" type="text" value="" placeholder="请输入用户名" style="padding-left:5px;padding-right:5px;width:250px;height:32px;border:0;line-height:32px;font-size:14px;" />
                    </div>
                    <div class="cls"></div>
                  </div></td>
              </tr>
              <tr>
              	<td colspan="2" height="10"></td>
              </tr>
              <tr>
                <td nowrap="nowrap">密&nbsp;&nbsp;码&nbsp; </td>
                <td>
                <div style="width:250px;border:1px solid #cccccc; margin-top:0px;">
                    <div class="float_left">
                      <input name="user.password" id="pass" type="password" placeholder="请输入用户密码" value="" style="padding-left:5px;padding-right:5px;width:250px;height:32px;border:0;line-height:32px;font-size:14px;"/>
                    </div>
                    <div class="cls"></div>
                  </div></td>
              </tr>
              <tr>
                <td>验证码</td>
                <td>
                <div style="margin-top:5px">
                <div class="float_left">
                <input name="checkcode" id="code" type="text"  placeholder="请输入右侧验证码" style="padding-left:0px;padding-right:5px;width:110px;height:32px;border:1;line-height:32px;font-size:14px;" />
                </div>
                <div class="float_right>"><img src="<%=path %>/verifycode.jpg" align="absmiddle" class="verify-code"/></div>
                <div class="cls"></div></div></td>
              </tr>
              <tr>
                <td height="50"></td>
                <td align="center" >
                	  <table>
                	  	<tr>
                	  		<td><input type="image" name="submit" src="img/login.jpg" style="cursor:pointer;border:0;" height="20" /></td>
                	      <td><a href="javascript:void(0)" onclick="clearinfo()"><img id="loginreset" src="img/reset.jpg" style="cursor:pointer;border:0;" height="20"/></a></td>
                	    </tr>
                	  </table>
                </td>
              </tr>
            </tbody>
          </table>
          </s:form>
      </div>
   </td>
   <td rowspan="2"><img name="n2_r2_c3" src="img/r2_c3.jpg" width="332" height="405" border="0" id="n2_r2_c3" alt="" /></td>
   <td><img src="img/spacer.gif" width="1" height="210" border="0" alt="" /></td>
  </tr>
  <tr>
   <td><img name="n2_r3_c2" src="img/r3_c2.jpg" width="362" height="195" border="0" id="n2_r3_c2" alt="" /></td>
   <td><img src="img/spacer.gif" width="1" height="195" border="0" alt="" /></td>
  </tr>
</table>
<s:include value="/include/footer-script.jsp"></s:include>
<script type="text/javascript">
			$(function(){
				$('.verify-code').click(function() {
					$(this).hide().attr('src', '<%=path %>/verifycode.jpg?' + Math.floor(Math.random() * 100)).fadeIn();
				});
			});
			
			function clearinfo(){
			    $("#name").val("");
			    $("#pass").val("");
			    $("#code").val("");
			}
</script>
</div>
</body>
</html>
