package com.zhirui.business.utils;

import java.io.BufferedWriter;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.OutputStream;
import java.io.OutputStreamWriter;  
import java.io.Writer;  
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;  
import java.util.Date;
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.zhirui.business.common.Constants;
  
import freemarker.template.Configuration;  
import freemarker.template.Template;  
import freemarker.template.TemplateException;  
  
@SuppressWarnings("serial")
public class ExportDocUtils extends HttpServlet{  
    
	private Configuration configuration = new Configuration(); 
    public ExportDocUtils(){  
    	configuration.setDefaultEncoding("UTF-8"); 
    }  
      
    public static void main(String[] args) {  
    	ExportDocUtils test = new ExportDocUtils();  
        test.createWord();  
    }  
      
    public void createWord(){  
        Map<String,Object> dataMap=new HashMap<String,Object>();  
        //getData(dataMap);  
        //configuration.setClassForTemplateLoading(this.getClass(), "/com");  //FTL文件所存在的位置
        Template t=null;  
        try {  
            configuration.setDirectoryForTemplateLoading(new File("E:/zhirui"));
             t = configuration.getTemplate("test.ftl");
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        File outFile = new File("E:/zhirui/m1.doc");  
        Writer out = null;  
        try {  
            out= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
        } catch (Exception e1) {  
            e1.printStackTrace();  
        }  
           
        try {  
            t.process(dataMap, out);  
            out.close();
        } catch (TemplateException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }
    public  String exportWord(String docname,String template_name,Map<String,Object> dataMap,HttpServletRequest req, HttpServletResponse resp) throws IOException{  
    	req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/x-download");
        
        //if (dataMap.size()==0) return null;   //曾智琴
        Template t=null;  
        try {  
             configuration.setServletContextForTemplateLoading(req.getSession().getServletContext(), "/WEB-INF/template");
            // configuration.setDirectoryForTemplateLoading(new File("E:/zhirui"));
             t = configuration.getTemplate(template_name);
             String name = "";
             if (docname==null||docname.equals("")){
                 Date date1 = new Date();
  	             name = "tmp"+String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(date1))+".doc";
             }else{
            	 name = docname+".doc";
             }
//             final String userAgent = req.getHeader("USER-AGENT");
//             if(StringUtils.contains(userAgent, "MSIE")){//IE浏览器
//            	 name = URLEncoder.encode(name,"UTF8");
//             }else if(StringUtils.contains(userAgent, "Mozilla")){//google,火狐浏览器
//            	 name = new String(name.getBytes(), "ISO8859-1");
//             }else{
//            	 name = URLEncoder.encode(name,"UTF8");//其他浏览器
//             }
             name = new String(name.getBytes(), "ISO8859-1");
             ((HttpServletResponse) resp).addHeader("Content-Disposition", "attachment;filename="+ name);
             
             OutputStream outs = resp.getOutputStream();
             Writer out= new BufferedWriter(new OutputStreamWriter(outs, "UTF-8"));
             t.process(dataMap, out);  
             out.close();
             return name;
        } catch (TemplateException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return null;
    }  
    //得到检测报告的数据
    private Map<String,Object> getCheckReportData() {  
    	Map<String,Object> dataMap=new HashMap<String,Object>();  
        dataMap.put("name", "艾丝凡咖啡");  
        dataMap.put("date", "2012");  
        dataMap.put("article", "关于什么什么的问题讨论");  
        dataMap.put("des", "集体讨论学习");  
          
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();  
        for (int i = 0; i < 10; i++) {  
            Map<String,Object> map = new HashMap<String,Object>();  
            map.put("number", i);  
            map.put("content", "内容"+i);  
            list.add(map);  
        }  
        dataMap.put("list", list);  
        return dataMap;
    }  
}  

