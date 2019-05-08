package com.zhirui.business.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

import cn.kepu.utils.PageModel;

/**
 * excel导出类
 * @author zy
 */
public class ExportUtils extends HttpServlet {
	
    /**
     * 导出数据
     * @param title        标题
     * @param fields       标头数组
     * @param columnsname  要获取的字段名称数组（名称与属性一致）
     * @param pageModel    查询到的页数据，在一页内包含所有数据
     * @return             文件名称
     * @throws IOException 
     */
	public static String exportExcel(String title,String[] fields,String[] columnsname,PageModel pageModel,HttpServletRequest req, HttpServletResponse resp) throws IOException{

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/x-download");
		
		if (fields==null||columnsname==null||pageModel==null) return null;
		//如果表头数组和字段名称数组长度不一致
		if (fields.length!=columnsname.length) return null;
	   //第一步，创建一个webbook，对应一个Excel文件
       HSSFWorkbook wb = new HSSFWorkbook();
       
       //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
       HSSFSheet sheet = wb.createSheet(title);
   
       for(int j=0;j<fields.length;j++){
       	sheet.setColumnWidth(j, 4000);
       }
       //第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
       HSSFRow row0 = sheet.createRow(0);  
       // 设置行高   
       row0.setHeight((short) 400);   
       CellRangeAddress range = new CellRangeAddress(0, 0, 0, fields.length);
	   sheet.addMergedRegion(range);
       HSSFCell cell0 = row0.createCell(0);  
       cell0.setCellValue(new HSSFRichTextString(title));  
       
       HSSFCellStyle style2 = wb.createCellStyle();  //Execl列明样式：背景色为灰色，字体居中。
      style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
      style2.setFillForegroundColor(HSSFColor.GREY_50_PERCENT.index);
       style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
       HSSFFont font2 = wb.createFont();
       font2.setFontHeightInPoints((short) 18);
       font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
       font2.setColor(HSSFFont.COLOR_NORMAL);
       
       
       style2.setFont(font2);  
       cell0.setCellStyle(style2);
      
       //第四步，创建单元格，并设置值表头  设置表头居中
       HSSFCellStyle style1 = wb.createCellStyle();  //Execl列明样式：背景色为灰色，字体居中。
       style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
       style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
       style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
       style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
       style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
       HSSFFont font = wb.createFont();
       font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
       font.setColor(HSSFFont.COLOR_NORMAL);
       style1.setFont(font);  //黑色粗体
     
       HSSFRow row = sheet.createRow(1); 
       HSSFCell cell = row.createCell(0);
       //循环列头
       
       cell.setCellValue(new HSSFRichTextString("序号"));  cell.setCellStyle(style1);
       for(int i=0;i<fields.length;i++){
       	cell = row.createCell(i+1);
	        cell.setCellValue(new HSSFRichTextString(fields[i]));  cell.setCellStyle(style1);
       }
       	
       //第五步，写入实体数据 实际应用中这些数据从数据库得到，
       HSSFCellStyle style3 = wb.createCellStyle();  //Execl列明样式：背景色为灰色，字体居中。
       style3.setAlignment(HSSFCellStyle.ALIGN_LEFT);
       HSSFFont font3 = wb.createFont();
       font3.setColor(HSSFFont.COLOR_NORMAL);
       style3.setFont(font3);  //黑色粗体
       
       int rownum =2;
       int k=1;
       int r = 0;
       // 得到记录数（传入的参数应该是一页所有的记录）
       int recorders = pageModel.getTotalRecords();
       Object base = null;
       Method basemethod = null;    
	   Object basevalue = null;
	        
	   Object detail = null;
       Method detailmethod = null;    
	   Object detailvalue = null;
       while(recorders>0){
           row = sheet.createRow(rownum);
           cell = row.createCell(0);
	       cell.setCellValue(k);  
	       cell.setCellStyle(style1);
	       	try{
		        	base = pageModel.getList().get(r);
		        	basemethod = base.getClass().getMethod("getBase", new Class[] {});    
			        basevalue = basemethod.invoke(base, new Object[] {});
			        
			        detail = pageModel.getList().get(r);
		        	detailmethod = detail.getClass().getMethod("getDetail", new Class[] {});    
			        detailvalue = detailmethod.invoke(detail, new Object[] {});
	       	}catch(Exception e){
	       		
	       	}
           for(int i=0;i<fields.length;i++){
	        	cell = row.createCell(i+1);
	        	// 先按业务对象查询
	        	Object colvalue = getFieldValueByName(columnsname[i],detailvalue);
	        	if (colvalue!=null){
	        		//判断是否为日期
	        		if (colvalue instanceof Date){
	        			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        			cell.setCellValue(new HSSFRichTextString(sdf.format(colvalue)));
	        		}else if (colvalue instanceof Float ||colvalue instanceof Integer||colvalue instanceof Long){
	        		   cell.setCellValue(new HSSFRichTextString(String.valueOf(colvalue)));
	        		}else if (colvalue instanceof Map){
	        			cell.setCellValue(new HSSFRichTextString(getMapValue((Map)colvalue)));
	        		}
	        		else{
		        		cell.setCellValue(new HSSFRichTextString((String)colvalue));
		        	}
	        	}else{
	        		Object colvalue2 = getFieldValueByName(columnsname[i],basevalue);
	        		if (colvalue2 instanceof Date){
	        			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        			cell.setCellValue(new HSSFRichTextString(sdf.format(colvalue2)));
	        		}else if (colvalue2 instanceof Float ||colvalue2 instanceof Integer||colvalue2 instanceof Long){
	        		    cell.setCellValue(new HSSFRichTextString(String.valueOf(colvalue2)));
	        		}else if (colvalue2 instanceof Map){
	        			cell.setCellValue(new HSSFRichTextString(getMapValue((Map)colvalue2)));
	        		}else{
	        		    cell.setCellValue(new HSSFRichTextString((String)colvalue2));
	        		}
	        		colvalue2 = null;
	        	}
		        cell.setCellStyle(style3);
		        
		        colvalue = null;
	        }
           rownum++;
           k++;
           recorders--;
           r++;
       }
        base = null;
        basemethod = null;    
	    basevalue = null;
	        
	    detail = null;
      	detailmethod = null;    
	    detailvalue = null;
       //第六步，将文件存到指定位置
	    
//       String p="d:/testexport/";
	    Date date1 = new Date();
	       String name = "tmp"+String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(date1))+".xls";
	      
        File file = new File(name);
		FileOutputStream fout = new FileOutputStream(file);
 
   
       ((HttpServletResponse) resp).addHeader("Content-Disposition", "attachment;filename="+ name);
       
       OutputStream out = resp.getOutputStream();
       wb.write(out);
       out.close();

		return name;
	}
	/**
     * 导出数据
     * @param title        标题
     * @param fields       标头数组
     * @param columnsname  要获取的字段名称数组（名称与属性一致）
     * @param pageModel    查询到的页数据，在一页内包含所有数据
     * @return             文件名称
     * @throws IOException 
     */
	public static String exportExcel2(String title,String[] fields,String[] columnsname,PageModel pageModel,HttpServletRequest req, HttpServletResponse resp) throws IOException{

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/x-download");
		
		if (fields==null||columnsname==null||pageModel==null) return null;
		//如果表头数组和字段名称数组长度不一致
		if (fields.length!=columnsname.length) return null;
	   //第一步，创建一个webbook，对应一个Excel文件
       HSSFWorkbook wb = new HSSFWorkbook();
       
       //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
       HSSFSheet sheet = wb.createSheet(title);
   
       for(int j=0;j<fields.length;j++){
       	sheet.setColumnWidth(j, 4000);
       }
       //第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
       HSSFRow row0 = sheet.createRow(0);  
       // 设置行高   
       row0.setHeight((short) 400);   
       CellRangeAddress range = new CellRangeAddress(0, 0, 0, fields.length);
	   sheet.addMergedRegion(range);
       HSSFCell cell0 = row0.createCell(0);  
       cell0.setCellValue(new HSSFRichTextString(title));  
       
       HSSFCellStyle style2 = wb.createCellStyle();  //Execl列明样式：背景色为灰色，字体居中。
       style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
       style2.setFillForegroundColor(HSSFColor.GREY_50_PERCENT.index);
       style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
       HSSFFont font2 = wb.createFont();
       font2.setFontHeightInPoints((short) 18);
       font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
       font2.setColor(HSSFFont.COLOR_NORMAL);
       
       
       style2.setFont(font2);  
       cell0.setCellStyle(style2);
      
       //第四步，创建单元格，并设置值表头  设置表头居中
       HSSFCellStyle style1 = wb.createCellStyle();  //Execl列明样式：背景色为灰色，字体居中。
       style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
       style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
       style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
       style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
       style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
       HSSFFont font = wb.createFont();
       font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
       font.setColor(HSSFFont.COLOR_NORMAL);
       style1.setFont(font);  //黑色粗体
     
       HSSFRow row = sheet.createRow(1); 
       HSSFCell cell = row.createCell(0);
       //循环列头
       
       cell.setCellValue(new HSSFRichTextString("序号"));  cell.setCellStyle(style1);
       for(int i=0;i<fields.length;i++){
       	cell = row.createCell(i+1);
	        cell.setCellValue(new HSSFRichTextString(fields[i]));  cell.setCellStyle(style1);
       }
       	
       //第五步，写入实体数据 实际应用中这些数据从数据库得到，
       HSSFCellStyle style3 = wb.createCellStyle();  //Execl列明样式：背景色为灰色，字体居中。
       style3.setAlignment(HSSFCellStyle.ALIGN_LEFT);
       style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
       style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
       style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
       style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
       HSSFFont font3 = wb.createFont();
       font3.setColor(HSSFFont.COLOR_NORMAL);
       style3.setFont(font3);  //黑色粗体
       
       int rownum =2;
       int k=1;
       int r = 0;
       // 得到记录数（传入的参数应该是一页所有的记录）
       int recorders = pageModel.getTotalRecords();
 
	   Object basevalue = null;
	        
	   Object detailvalue = null;
       while(recorders>0){
           row = sheet.createRow(rownum);
           cell = row.createCell(0);
	       cell.setCellValue(k);  
	       cell.setCellStyle(style1);

	       for(int i=0;i<fields.length;i++){
	        	cell = row.createCell(i+1);
	        	// 先按业务对象查询
	        	if (columnsname[i].equals("")) continue;
	        	Object colvalue = getFieldValueByName(columnsname[i],pageModel.getList().get(r));
	        	if (colvalue!=null){
	        		//判断是否为日期
	        		if (colvalue instanceof Date){
	        			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        			cell.setCellValue(new HSSFRichTextString(sdf.format(colvalue)));
	        		}else if (colvalue instanceof Float ||colvalue instanceof Integer||colvalue instanceof Long){
	        		   cell.setCellValue(new HSSFRichTextString(String.valueOf(colvalue)));
	        		}else if (colvalue instanceof Map){
	        			cell.setCellValue(new HSSFRichTextString(getMapValue((Map)colvalue)));
	        		}
	        		else{
		        		cell.setCellValue(new HSSFRichTextString((String)colvalue));
		        	}
	        	}else{
	        		Object colvalue2 = getFieldValueByName(columnsname[i],basevalue);
	        		if (colvalue2 instanceof Date){
	        			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        			cell.setCellValue(new HSSFRichTextString(sdf.format(colvalue2)));
	        		}else if (colvalue2 instanceof Float ||colvalue2 instanceof Integer||colvalue2 instanceof Long){
	        		    cell.setCellValue(new HSSFRichTextString(String.valueOf(colvalue2)));
	        		}else if (colvalue2 instanceof Map){
	        			cell.setCellValue(new HSSFRichTextString(getMapValue((Map)colvalue2)));
	        		}else{
	        		    cell.setCellValue(new HSSFRichTextString((String)colvalue2));
	        		}
	        		colvalue2 = null;
	        	}
		        cell.setCellStyle(style3);
		        
		        colvalue = null;
	       }
           rownum++;
           k++;
           recorders--;
           r++;
       }
 
	    basevalue = null;

	    detailvalue = null;
       //第六步，将文件存到指定位置
	    
//       String p="d:/testexport/";
	    Date date1 = new Date();
	       String name = "tmp"+String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(date1))+".xls";
	      
        File file = new File(name);
		FileOutputStream fout = new FileOutputStream(file);
 
   
       ((HttpServletResponse) resp).addHeader("Content-Disposition", "attachment;filename="+ name);
       
       OutputStream out = resp.getOutputStream();
       wb.write(out);
       out.close();

		return name;
	}
	/**
	 * 获取map对象的字符串
	 * @param map
	 * @return
	 */
	private static String getMapValue(Map map){
		 if (map==null) return "";
		 String rtn = "";
		 Collection con = map.values();
		 Iterator<String> iterator = con.iterator();
		 while(iterator.hasNext()){
			 rtn += iterator.next()+",";
		 }
		 if (!rtn.equals(""))
			 rtn = rtn.substring(0,rtn.length()-1);
		 return rtn;
	}
	/** 
	 * 根据属性名获取属性值 
	 * */  
	   private static Object getFieldValueByName(String fieldName, Object o) {  
	       try {    
	           String firstLetter = fieldName.substring(0, 1).toUpperCase();    
	           String getter = "get" + firstLetter + fieldName.substring(1);    
	           Method method = o.getClass().getMethod(getter, new Class[] {});    
	           Object value = method.invoke(o, new Object[] {});    
	           return value;    
	       } catch (Exception e) {    
	           return null;    
	       }    
	   }   
	     
	   /** 
	    * 获取属性名数组 
	    * */  
	   private String[] getFiledName(Object o){  
	        Field[] fields=o.getClass().getDeclaredFields();  
	        String[] fieldNames=new String[fields.length];  
		    for(int i=0;i<fields.length;i++){  
		        System.out.println(fields[i].getType());  
		        fieldNames[i]=fields[i].getName();  
		    }  
		    return fieldNames;  
	   }  
	     
	   /** 
	    * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list 
	    * */  
	   private List getFiledsInfo(Object o){  
	    Field[] fields=o.getClass().getDeclaredFields();  
	        String[] fieldNames=new String[fields.length];  
	        List list = new ArrayList();  
	        Map infoMap=null;  
	    for(int i=0;i<fields.length;i++){  
	        infoMap = new HashMap();  
	        infoMap.put("type", fields[i].getType().toString());  
	        infoMap.put("name", fields[i].getName());  
	        infoMap.put("value", getFieldValueByName(fields[i].getName(), o));  
	        list.add(infoMap);  
	    }  
	    return list;  
	   }  
	     
	   /** 
	    * 获取对象的所有属性值，返回一个对象数组 
	    * */  
	   public Object[] getFiledValues(Object o){  
	    String[] fieldNames=this.getFiledName(o);  
	    Object[] value=new Object[fieldNames.length];  
	    for(int i=0;i<fieldNames.length;i++){  
	        value[i]=this.getFieldValueByName(fieldNames[i], o);  
	    }  
	    return value;  
	   } 
}
