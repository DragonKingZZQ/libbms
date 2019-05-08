package com.zhirui.business.common.action;

import cn.kepu.base.MessageType;
import cn.kepu.base.action.BaseAction;
import cn.kepu.utils.ContextUtils;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GlobalAction extends BaseAction {
  private static final long serialVersionUID = 8764523764123123L;
  private String code;
  
  public String error()
  {
    System.out.println("----------------------------------------------");
    if ("403".equals(this.code)) {
      ContextUtils.setOpMessage(MessageType.WARN, "没有权限访问(403 Acess Denied)");
    }
    if ("404".equals(this.code)) {
      ContextUtils.setOpMessage(MessageType.WARN, "请求的页面不存在(404 NOT FOUND)");
    }
    if ("500".equals(this.code)) {
      ContextUtils.setOpMessage(MessageType.ERROR, "用户请求处理异常，请稍后重新提交请求");
    }
    return "showmessage";
  }
  
  public String info()
  {
    System.out.println("-**************************************************");
    StringBuffer message = new StringBuffer();
    Collection<String> actionErrors = getActionErrors();
    if ((actionErrors != null) && (actionErrors.size() > 0))
    {
      message.append("-----------------------------------<br/>");
      for (String error : actionErrors) {
        message.append(error).append("<br/>");
      }
    }
    Collection<String> actionMessages = getActionMessages();
    if ((actionMessages != null) && (actionMessages.size() > 0))
    {
      message.append("-----------------------------------<br/>");
      for (String msg : actionMessages) {
        message.append(msg).append("<br/>");
      }
    }
    Object fieldErrors = getFieldErrors();
    if ((fieldErrors != null) && (((Map)fieldErrors).size() > 0))
    {
      message.append("-----------------------------------<br/>");
      Iterator localIterator4;
      for (Iterator localIterator3 = ((Map)fieldErrors).keySet().iterator(); localIterator3.hasNext(); localIterator4.hasNext())
      {
        String errorKey = (String)localIterator3.next();
        message.append("**").append(errorKey).append(":<br/>");
        localIterator4 = ((List)((Map)fieldErrors).get(errorKey)).iterator(); continue;
     //   String msg = (String)localIterator4.next();
     //   message.append(msg).append("<br/>");
      }
    }
    if (message.length() > 0)
    {
      message.append("-----------------------------------<br/>");
      ContextUtils.setOpMessage(MessageType.ERROR, message.toString());
    }
    return "showmessage";
  }
  
  public void setCode(String code)
  {
    this.code = code;
  }
}
