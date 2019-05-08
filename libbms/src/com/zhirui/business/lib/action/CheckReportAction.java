package com.zhirui.business.lib.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import sun.misc.BASE64Encoder;

import com.zhirui.business.common.Constants;
import com.zhirui.business.common.bean.SampleCategory;
import com.zhirui.business.common.bean.User;
import com.zhirui.business.common.service.SampleCategoryService;
import com.zhirui.business.lib.bean.CheckImage;
import com.zhirui.business.lib.bean.CheckReport;
import com.zhirui.business.lib.bean.CheckStandard;
import com.zhirui.business.lib.bean.Instrument;
import com.zhirui.business.lib.bean.RegisteCheckItem;
import com.zhirui.business.lib.bean.SampleRegiste;
import com.zhirui.business.lib.bean.SendCheckItem;
import com.zhirui.business.lib.bean.SendSample;
import com.zhirui.business.lib.bean.SubCheckReport;
import com.zhirui.business.lib.service.CheckImageService;
import com.zhirui.business.lib.service.CheckItemService;
import com.zhirui.business.lib.service.CheckReportService;
import com.zhirui.business.lib.service.CheckStandardService;
import com.zhirui.business.lib.service.EntrustCompanyService;
import com.zhirui.business.lib.service.InstrumentService;
import com.zhirui.business.lib.service.RegisteCheckItemService;
import com.zhirui.business.lib.service.SampleRegisteService;
import com.zhirui.business.lib.service.SendCheckItemService;
import com.zhirui.business.lib.service.SendSampleService;
import com.zhirui.business.lib.service.SubCheckReportService;
import com.zhirui.business.utils.ExportDocUtils;
import com.zhirui.business.utils.BusinessUtils;

import cn.kepu.base.MessageType;
import cn.kepu.base.action.PageAction;
import cn.kepu.utils.ContextUtils;
import cn.kepu.utils.PageModel;
import cn.kepu.utils.StringUtils;

@SuppressWarnings("serial")
public class CheckReportAction extends PageAction<CheckReport> {

	@Autowired
	private CheckReportService checkReportService;
	@Autowired
	private RegisteCheckItemService registeCheckItemService;
	@Autowired
	private SendCheckItemService sendCheckItemService;
	@Autowired
	private CheckItemService checkItemService;
	@Autowired
	private EntrustCompanyService entrustCompanyService;
	@Autowired
	private SampleRegisteService sampleRegisteService;
	@Autowired
	private CheckStandardService checkStandardService; //zjx
	@Autowired
	private SendSampleService sendSampleService;
	@Autowired
	private InstrumentService instrumentService;
	@Autowired
	private SubCheckReportService subCheckReportService;
	@Autowired
	private CheckImageService checkImageService;   //曾智琴
	@Autowired
	private SampleCategoryService sampleCategoryService; // zengzhiqin
	private CheckReport checkReport;
	public Map<Integer,String> entrustcompanyList;
	public Map<Integer,String> instrumentList;
	public Map<Integer,String> itemList;
	private int[] ids;
	private String[]  optvalue;
	private String[]  selectCheckItems;
	public Map<Integer,String> registeList;
	public Map<Integer,String> registeList2;
	private String info;
	private Integer modifyflag;
	public List<SubCheckReport> checkResultList;
	//取不同的登记单信息
	private String registeid;
	
	private String[]  checkid;
	private String[]  checkstandard;
	private String[]  instrumentname;

	private String[]  checkresult;
	private String[] unit;
	private String[] checkscope;
	private String[] code;

	
	
	
	private static final Log log = LogFactory.getLog(CheckReportAction.class);
	
	/**
	 * 初始化 检验用户 委托单位 检验项目 资质等
	 */
	private void initData(){
		entrustcompanyList = entrustCompanyService.getAll();
		itemList = checkItemService.getAll();
		//registeList = sampleRegisteService.getFinishRegisted();
		registeList = sampleRegisteService.getAllRegisted();
	
		instrumentList = instrumentService.getAll();
	}
	/**
	 * 列表功能
	 * @return
	 */
	public String list() {
		entrustcompanyList = entrustCompanyService.getAll();
		pageModel = checkReportService.getCheckReport(checkReport, pageNo, pageSize);
		return LIST;
	}

	public String add() {
		initData();
		itemList = checkItemService.getAllValid();
		instrumentList = instrumentService.getAllValid();
		return ADD;
	}
	public String remove() {
		if(checkReport == null) {
			log.warn("用户非法访问");
			ContextUtils.setOpMessage(MessageType.WARN, "用户非法访问");
			return ERROR;
		}
		int id = checkReport.getId();
		checkReport = checkReportService.getCheckReport(id);
		String sampleno = checkReport.getSampleno();
		
		//曾智琴
		checkResultList = subCheckReportService.getAllCheckItemByReport(checkReport.getId());
		int[] ids2 = new int[checkResultList.size()];		
		for(int i=0;i<checkResultList.size();i++){
			ids2[i] = checkResultList.get(i).getId();
			
		}
		subCheckReportService.removeSubCheckReports(ids2); 
		
		
		
		if(checkReportService.removeCheckReport(checkReport.getId())) {
			//修改样品登记单状态为已经出报告
			SampleRegiste sr = sampleRegisteService.getSampleRegisteByCode(sampleno);
			//sr.setStatus(Constants.STATUS_FINISHCHECK);
			//11.26改成起草状态
			sr.setStatus(Constants.STATUS_CREATE);
			sampleRegisteService.updateSampleRegiste(sr);
			
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据删除成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据删除失败");
		return ERROR;
	}
	public String batchremove() {
		if(!checkReportService.removeCheckReports(ids)) {
			    for(int i=0;i<ids.length;i++){
			    	int id = ids[i];
					checkReport = checkReportService.getCheckReport(id);
					String sampleno = checkReport.getSampleno();
					
					//修改样品登记单状态为已经出报告
					SampleRegiste sr = sampleRegisteService.getSampleRegisteByCode(sampleno);
					sr.setStatus(Constants.STATUS_FINISHCHECK);
					sampleRegisteService.updateSampleRegiste(sr);
			    }
				ContextUtils.setOpMessage(MessageType.SUCCESS, "数据批量删除成功");
				return SUCCESS;
		}
		
		ContextUtils.setOpMessage(MessageType.ERROR, "数据批量删除失败");
		return SUCCESS;
	}
	public String modify() {
		initData();
		checkReport = checkReportService.getCheckReport(checkReport.getId());
		checkResultList = subCheckReportService.getAllCheckItemByReport(checkReport.getId());
		//根据编号设置登记单的id
//		for (Integer key : registeList.keySet()) {
//			   if (registeList.get(key).equals(checkReport.getSampleno())){
//                   checkReport.setSampleid(key);				   
//				   break;
//			   }
//  	    }
        SampleRegiste sr = sampleRegisteService.getSampleRegisteByCode(checkReport.getSampleno());
        if(sr!=null){
        	checkReport.setSampleid(sr.getId());
        }
		for(SubCheckReport obj:checkResultList){
			obj.setCheckitemname(checkItemService.getCheckItem(Integer.parseInt(obj.getCheckitem())).getItemname());
			if (obj.getInstrumentcode()!=null&&!obj.getInstrumentcode().equals("")){
			   obj.setInstrumentname(instrumentService.getInstrument(Integer.parseInt(obj.getInstrumentcode())).getCodename());
			}else{
				obj.setInstrumentname("");
			}
		}
		return "modify";
	}
	public String save() {
		User user = BusinessUtils.getCurrentUser();
		checkReport.setCreateuser(user.getUid());
		checkReport.setCreatedate(new Date());
		
		
		SampleRegiste sr = sampleRegisteService.getSampleRegiste(checkReport.getSampleid());
		String str = sr.getEntrustcompany();
		str = entrustCompanyService.getEntrustCompany(Integer.parseInt(str)).getEntrustcompany();
		checkReport.setEntrustcompany(str);
		if((checkReport=checkReportService.addCheckReport(checkReport)) != null) {
			//保存检验项目结果
			if (checkid!=null){
				for(int i=0;i<checkid.length;i++){
					SubCheckReport subCheckReport = new SubCheckReport();
					subCheckReport.setCheckreportid(checkReport.getId());
					subCheckReport.setSubsendsampleno(code[i]);
					//subCheckReport.setType(optvalue[i]);
					subCheckReport.setCheckitem(checkid[i]);
					subCheckReport.setStandard(checkstandard[i]);
					subCheckReport.setInstrumentcode(instrumentname[i]);
					subCheckReport.setCheckresult(checkresult[i]);
					//subCheckReport.setUnit(unit[i]);
					subCheckReport.setCheckscope(checkscope[i]);
					subCheckReportService.addSubCheckReport(subCheckReport);
				}
				//修改样品登记单状态为已经出报告
				sr.setStatus(Constants.STATUS_FINISHREPORT);
				sampleRegisteService.updateSampleRegiste(sr);
			}
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据添加成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据添加失败");
		return SUCCESS;
	}
	public String update() {
		User user = BusinessUtils.getCurrentUser();
		checkReport.setCreateuser(user.getUid());
		checkReport.setCreatedate(new Date());
		//String str = sampleRegisteService.getSampleRegiste(checkReport.getSampleid()).getEntrustcompany();
		//str = entrustCompanyService.getEntrustCompany(Integer.parseInt(str)).getEntrustcompany();
		//checkReport.setEntrustcompany(str);
		if((checkReport=checkReportService.updateCheckReport(checkReport)) != null) {
			//保存检验项目结果
			if (checkid!=null){
				//首先删除原来的检验项目
				checkResultList = subCheckReportService.getAllCheckItemByReport(checkReport.getId());
				for(SubCheckReport scr:checkResultList){
				     subCheckReportService.removeSubCheckReport(scr.getId());
				}
				for(int i=0;i<checkid.length;i++){
					SubCheckReport subCheckReport = new SubCheckReport();
					subCheckReport.setCheckreportid(checkReport.getId());
					subCheckReport.setSubsendsampleno(code[i]);
					subCheckReport.setCheckitem(checkid[i]);
					//subCheckReport.setType(optvalue[i]);
					//subCheckReport.setStandard(checkstandard[i]);
					subCheckReport.setInstrumentcode(instrumentname[i]);
					subCheckReport.setCheckresult(checkresult[i]);
					subCheckReport.setUnit(unit[i]);
					subCheckReport.setCheckscope(checkscope[i]);
					subCheckReportService.addSubCheckReport(subCheckReport);
				}
			}
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据更新成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据更新失败");
		return SUCCESS;
	}
	public String detail() {
		if(checkReport == null) {
			log.warn("用户非法访问");
			ContextUtils.setOpMessage(MessageType.WARN, "用户非法访问");
			return ERROR;
		}
		initData();
		checkReport = checkReportService.getCheckReport(checkReport.getId());
		checkResultList = subCheckReportService.getAllCheckItemByReport(checkReport.getId());
		//根据编号设置登记单的id
//		for (Integer key : registeList.keySet()) {
//			   if (registeList.get(key).equals(checkReport.getSampleno())){
//                   checkReport.setSampleid(key);				   
//				   break;
//			   }
//  	    }
		SampleRegiste sr = sampleRegisteService.getSampleRegisteByCode(checkReport.getSampleno());
		CheckStandard check = checkStandardService.getCheckStandard(sr.getCheckstandardid());
        if(sr!=null){
        	checkReport.setSampleid(sr.getId());
        }
		for(SubCheckReport obj:checkResultList){
			
			obj.setCheckitemname(checkItemService.getCheckItem(Integer.parseInt(obj.getCheckitem())).getItemname());
			if (obj.getInstrumentcode()!=null&&!obj.getInstrumentcode().equals("")){
			    obj.setInstrumentname(instrumentService.getInstrument(Integer.parseInt(obj.getInstrumentcode())).getInsname());
			    obj.setStandard("");
			}else{
				obj.setInstrumentname("");
			}
		}
		
		return DETAIL;
	}
	
	/**
	 * 检查数据合法性
	 * 验证对象是否存在
	 * 验证所有字段是否合法
	 * @return
	 */
	private void checkData(){
		// 验证数据
		if(checkReport == null) {
			log.warn("用户非法访问");
			ContextUtils.setOpMessage(MessageType.VALIDATE_FAILED, "用户非法访问");
			return ;
		}
	}

	/**
	 * 提交审核
	 * @return
	 */
	public String submit() {
		if(checkReport == null) {
			ContextUtils.setOpMessage(MessageType.WARN, "用户非法访问");
			return SUCCESS;
		}
		checkReport = checkReportService.getCheckReport(checkReport.getId());
		ContextUtils.setOpMessage(MessageType.SUCCESS, "提交成功");
		return SUCCESS;
	}
	
	// 导出word
	public String export() throws IOException{
		//得到数据（检验报告，检验项目，检验仪器）
        Map<String,Object> dataMap=new HashMap<String,Object>();  
        //initData();
		checkReport = checkReportService.getCheckReport(checkReport.getId());
		checkResultList = subCheckReportService.getAllCheckItemByReport(checkReport.getId());
		System.out.println(checkReport.getId()+"导出id");
		//曾智琴
		String path = ServletActionContext.getServletContext().getRealPath("/");
		
	    PageModel<CheckImage> checkimages = checkImageService.getCheckImageBySampleno(checkReport.getSampleno());
	    List<Map<String,Object>> images = new ArrayList<Map<String,Object>>();  
//	    System.out.println(checkimages.toString()+"checkimage的值");
	    if(checkimages != null){
		    for(CheckImage checkimage:checkimages.getList()){
				try {
					Map<String,Object> map = new HashMap<String,Object>();
					System.out.println(path + checkimage.getImageurl()+"checkimage的值"+checkimage.getSampleno()+"imagename的值");
					map.put("imageurl",getImageBase(path + checkimage.getImageurl()));
					map.put("imagename",checkimage.getImagename().substring(0,checkimage.getImagename().lastIndexOf(".")));
					images.add(map);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	    }
	    System.out.println(images.size()+"图片size");
	    
	    
	  
		//根据编号设置登记单的id
//		for (Integer key : registeList.keySet()) {
//			   if (registeList.get(key).equals(checkReport.getSampleno())){
//                   checkReport.setSampleid(key);				   
//				   break;
//			   }
//  	    }
		int id=-1;
		SampleRegiste sr = sampleRegisteService.getSampleRegisteByCode(checkReport.getSampleno());
        checkReport.setSampleid(sr.getId());
        if("".equals(checkReport.getProductionunit())){
			dataMap.put("productunit", "—— ——");
		}else{
			dataMap.put("productunit", checkReport.getProductionunit());
		}
		for(SubCheckReport obj:checkResultList){
			obj.setCheckitemname(checkItemService.getCheckItem(Integer.parseInt(obj.getCheckitem())).getItemname());
			if (obj.getInstrumentcode()!=null&&!obj.getInstrumentcode().equals("")){
			    obj.setInstrumentname(instrumentService.getInstrument(Integer.parseInt(obj.getInstrumentcode())).getInsname());
			    obj.setMeasuerange(instrumentService.getInstrument(Integer.parseInt(obj.getInstrumentcode())).getMeasueRange());
			}else{
				obj.setInstrumentcode("");
				obj.setMeasuerange("");
			}
		}
		SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(checkReport.getReceivedate());
		dataMap.put("sampleno", checkReport.getSampleno());
		dataMap.put("samplename", checkReport.getSamplename());
		dataMap.put("entrustcompany", checkReport.getEntrustcompany());
		
		dataMap.put("trademark", checkReport.getTrademark());
		dataMap.put("receivedate", dateStr);
		dataMap.put("samplemodel", checkReport.getSamplemodel());
		dataMap.put("samplestatus", checkReport.getSamplestatus());
		dataMap.put("samplecount", checkReport.getSamplecount());
		dataMap.put("remark", checkReport.getRemark());
		if(checkReport.getResultremark()!=null){
			dataMap.put("resultremark", checkReport.getResultremark());
		}
		Calendar newcalendar = Calendar.getInstance();  
	    newcalendar.setTime(new Date());
		dataMap.put("sampledate", newcalendar.get(Calendar.YEAR)+"年"+(newcalendar.get(Calendar.MONTH)+1)+"月"+newcalendar.get(Calendar.DAY_OF_MONTH)+"日");
	    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();  
        for (SubCheckReport obj:checkResultList) {  
            Map<String,Object> map = new HashMap<String,Object>();  
            map.put("checkitemname", obj.getCheckitemname());  
            if (obj.getStandard()!=null){
               map.put("standard", obj.getStandard());
            }else{
            	map.put("standard", "");
            }
            if (obj.getCheckscope()!=null){
                map.put("measuerange", obj.getCheckscope());
            }else{
            	map.put("measuerange","");
            }
            if (obj.getInstrumentname()!=null){
               map.put("instrumentname", obj.getInstrumentname());
            }else{
            	map.put("instrumentname", "");
            }
            if (obj.getCheckresult()!=null){
                map.put("checkresult", obj.getCheckresult());
            }else{
            	map.put("checkresult", "");
            }
            list.add(map);  
        }  
        
        dataMap.put("list", list); 
        
		if(images!=null){
	        dataMap.put("images", images);
		}
		
        String docname = checkReport.getSampleno()+ checkReport.getSamplename();
		ExportDocUtils edu = new ExportDocUtils();
		edu.exportWord(docname,Constants.TEMPLATE_CHECKREPORT,dataMap,request,response);
		return null;
	}
	
	
	// 导出word
		public String export2() throws IOException{
			//得到数据（检验报告，检验项目，检验仪器）
	        Map<String,Object> dataMap=new HashMap<String,Object>();  
	        //initData();
			checkReport = checkReportService.getCheckReport(checkReport.getId());
			checkResultList = subCheckReportService.getAllCheckItemByReport(checkReport.getId());
			
			//曾智琴
			String path = ServletActionContext.getServletContext().getRealPath("/");
			
		    PageModel<CheckImage> checkimages = checkImageService.getCheckImageBySampleno(checkReport.getSampleno());
		    List<Map<String,Object>> images = new ArrayList<Map<String,Object>>();  
//		    System.out.println(checkimages.toString()+"checkimage的值");
		    if(checkimages != null){
			    for(CheckImage checkimage:checkimages.getList()){
					try {
						Map<String,Object> map = new HashMap<String,Object>();
						System.out.println(path + checkimage.getImageurl()+"checkimage的值"+checkimage.getSampleno()+"imagename的值");
						map.put("imageurl",getImageBase(path + checkimage.getImageurl()));
						map.put("imagename",checkimage.getImagename().substring(0,checkimage.getImagename().lastIndexOf(".")));
						images.add(map);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		    }		   
		    		    
		  
			//根据编号设置登记单的id
//			for (Integer key : registeList.keySet()) {
//				   if (registeList.get(key).equals(checkReport.getSampleno())){
//	                   checkReport.setSampleid(key);				   
//					   break;
//				   }
//	  	    }
		    
		    
			int id=-1;
			SampleRegiste sr = sampleRegisteService.getSampleRegisteByCode(checkReport.getSampleno());
			//曾智琴
			SampleCategory cate = sampleCategoryService.getSampleCategory(Integer.parseInt(sr.getSamplecount()));
			List<String> storeList = new ArrayList<String>();
			String[] store = sr.getStorecondition().split(",");
			for(int i=0;i<store.length;i++){
				if(Integer.parseInt(store[i].trim())==1)
					storeList.add("常温");
				else if(Integer.parseInt(store[i].trim())==2)
					storeList.add("冷藏");
				else if(Integer.parseInt(store[i].trim())==3)
					storeList.add("冷冻");
				else if(Integer.parseInt(store[i].trim())==4)
					storeList.add("避光");
				else 
					storeList.add("干燥");
			}
			
			if(Integer.parseInt(sr.getSamplecount())>1){  //样品数量大于1
		        checkReport.setSampleid(sr.getId());
				for(SubCheckReport obj:checkResultList){
					obj.setCheckitemname(checkItemService.getCheckItem(Integer.parseInt(obj.getCheckitem())).getItemname());
					if (obj.getInstrumentcode()!=null&&!obj.getInstrumentcode().equals("")){
					    obj.setInstrumentname(instrumentService.getInstrument(Integer.parseInt(obj.getInstrumentcode())).getInsname());
					    
					}else{
						obj.setInstrumentcode("");
						
					}
				}
				/*String topimageurl = ServletActionContext.getServletContext().getRealPath("/") +"exportcontent/topimage.png";
				System.out.println(topimageurl+"顶上照片");*/
				SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		        String dateStr = format.format(checkReport.getReceivedate());
		        dataMap.put("sampleno", checkReport.getSampleno());
				dataMap.put("samplename", checkReport.getSamplename());
				dataMap.put("entrustcompany", checkReport.getEntrustcompany());
				//存储条件，类别,图片
				dataMap.put("storeList", storeList);
				dataMap.put("type", cate.getCategory());
				/*try {
					dataMap.put("topimageurl", getImageBase(topimageurl));
				} catch (Exception e) {
					e.printStackTrace();
				}*/
				
				dataMap.put("receivedate", dateStr);
				dataMap.put("resultremark",checkReport.getResultremark());
				if(checkReport.getAnalyzeremark()!=null){
					dataMap.put("analyzeremark", checkReport.getAnalyzeremark());
				}
				dataMap.put("samplecount", checkReport.getSamplecount());
				dataMap.put("remark", checkReport.getRemark());
				if(checkReport.getResultremark()!=null){
					dataMap.put("resultremark", checkReport.getResultremark());
				}
				Calendar newcalendar = Calendar.getInstance();  
			    newcalendar.setTime(new Date());
				dataMap.put("sampledate", newcalendar.get(Calendar.YEAR)+"年"+(newcalendar.get(Calendar.MONTH)+1)+"月"+newcalendar.get(Calendar.DAY_OF_MONTH)+"日");
			    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();  
		        for (SubCheckReport obj:checkResultList) {  
		            Map<String,Object> map = new HashMap<String,Object>();  
		            map.put("checkitemname", obj.getCheckitemname());  
		            map.put("subsendsampleno", obj.getSubsendsampleno());
		            if (obj.getStandard()!=null){
		               map.put("standard", obj.getStandard());
		            }else{
		            	map.put("standard", "");
		            }
		            
		            if (obj.getInstrumentname()!=null){
		               map.put("instrumentname", obj.getInstrumentname());
		            }else{
		            	map.put("instrumentname", "");
		            }
		            if (obj.getCheckresult()!=null){
		                map.put("checkresult", obj.getCheckresult());
		            }else{
		            	map.put("checkresult", "");
		            }
		            list.add(map);  
		        }  
		        
		        dataMap.put("list", list); 
		        
				if(images!=null){
			        dataMap.put("images", images);
				}
				
		        String docname = checkReport.getSampleno()+ checkReport.getSamplename();
				ExportDocUtils edu = new ExportDocUtils();
				edu.exportWord(docname,Constants.TEMPLATE_CHECKREPORT3,dataMap,request,response);
				return null;
			}else{  //样品数量是一个
				 checkReport.setSampleid(sr.getId());
					for(SubCheckReport obj:checkResultList){
						obj.setCheckitemname(checkItemService.getCheckItem(Integer.parseInt(obj.getCheckitem())).getItemname());
						if (obj.getInstrumentcode()!=null&&!obj.getInstrumentcode().equals("")){
						    obj.setInstrumentname(instrumentService.getInstrument(Integer.parseInt(obj.getInstrumentcode())).getInsname());
						    
						}else{
							obj.setInstrumentcode("");
							
						}
					}
					/*String topimageurl = ServletActionContext.getServletContext().getRealPath("/") +"exportcontent/topimage.png";
					System.out.println(topimageurl+"顶上照片");*/
					SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
			        String dateStr = format.format(checkReport.getReceivedate());
					dataMap.put("sampleno", checkReport.getSampleno());
					dataMap.put("samplename", checkReport.getSamplename());
					dataMap.put("entrustcompany", checkReport.getEntrustcompany());
					//
					dataMap.put("storeList", storeList);
					dataMap.put("type", cate.getCategory());
					/*try {
						dataMap.put("topimageurl", getImageBase(topimageurl));
					} catch (Exception e) {
						e.printStackTrace();
					}*/
					
					dataMap.put("receivedate", dateStr);
					dataMap.put("samplecount", checkReport.getSamplecount());
					dataMap.put("remark", checkReport.getRemark());
					if(checkReport.getResultremark()!=null){
						dataMap.put("resultremark", checkReport.getResultremark());
					}
					if(checkReport.getAnalyzeremark()!=null){
						dataMap.put("analyzeremark", checkReport.getAnalyzeremark());
					}
					Calendar newcalendar = Calendar.getInstance();  
				    newcalendar.setTime(new Date());
					dataMap.put("sampledate", newcalendar.get(Calendar.YEAR)+"年"+(newcalendar.get(Calendar.MONTH)+1)+"月"+newcalendar.get(Calendar.DAY_OF_MONTH)+"日");
				    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();  
			        for (SubCheckReport obj:checkResultList) {  
			            Map<String,Object> map = new HashMap<String,Object>();  
			            map.put("checkitemname", obj.getCheckitemname());
			            map.put("subsendsampleno", obj.getSubsendsampleno());
			            if (obj.getStandard()!=null){
			               map.put("standard", obj.getStandard());
			            }else{
			            	map.put("standard", "");
			            }
			            
			            if (obj.getInstrumentname()!=null){
			               map.put("instrumentname", obj.getInstrumentname());
			            }else{
			            	map.put("instrumentname", "");
			            }
			            if (obj.getCheckresult()!=null){
			                map.put("checkresult", obj.getCheckresult());
			            }else{
			            	map.put("checkresult", "");
			            }
			            list.add(map);  
			        }  
			        
			        dataMap.put("list", list); 
			        
					if(images!=null){
				        dataMap.put("images", images);
					}
					
			        String docname = checkReport.getSampleno()+ checkReport.getSamplename();
					ExportDocUtils edu = new ExportDocUtils();
					edu.exportWord(docname,Constants.TEMPLATE_CHECKREPORT2,dataMap,request,response);
					return null;
			}
		}
		
	
	//曾智琴   获取图片的base64码
		public static String getImageBase(String src) throws Exception {

			if (src == null || src == "") {

			return "";

			 }

			File file = new File(src);

			 if (!file.exists()) {

			return "";

			 }

			InputStream in = null;

			 byte[] data = null;

			 try {

			in = new FileInputStream(file);

			 data = new byte[in.available()];

			 in.read(data);

			 in.close();

			} catch (IOException e) {

			 e.printStackTrace();

			 }

			BASE64Encoder encoder = new BASE64Encoder();

			return encoder.encode(data);

			}
	
	/**  用下面的方法替换
     * 通过 ajax 方式，获取页面点击不同的样品登记时，得到检验项目
     * @return
     */
    public String getCheckItems_old(){
    	SampleRegiste sampleRegiste = sampleRegisteService.getSampleRegiste(Integer.parseInt(registeid));
		// 设置检验项目
    	itemList = checkItemService.getAll();
		/*
    	String checkitem = sampleRegiste.getCheckitem();
		checkitem = checkitem.substring(1, checkitem.length()-1);
		String[] ins = checkitem.split("\\|");
		*/
    	PageModel<RegisteCheckItem> pageRegisteCheckItem = registeCheckItemService.getRegisteCheckItem(sampleRegiste.getId(), 1, Constants.MAX_COUNT);
		info = "";
		JSONArray jsonArray = new JSONArray();
		Map<String, String> sampleinfo = new HashMap<String, String>(); 
		// 如果是修改的情况下，不增加下面的
		if (modifyflag!=1){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	String code = sdf.format(sampleRegiste.getReceivedate());
	    	
			entrustcompanyList = entrustCompanyService.getAll();
			sampleinfo.put("id",sampleRegiste.getSampleno());  
			sampleinfo.put("name", sampleRegiste.getSamplename());
			sampleinfo.put("entrustname", entrustcompanyList.get(Integer.parseInt(sampleRegiste.getEntrustcompany())));
			sampleinfo.put("productunit", sampleRegiste.getProductionunit());
			sampleinfo.put("status", sampleRegiste.getSamplestatus());
			sampleinfo.put("model", sampleRegiste.getSamplemodel());
			sampleinfo.put("count", sampleRegiste.getSamplecount());
			sampleinfo.put("receivedate", code);
			JSONObject jos = JSONObject.fromObject(sampleinfo);  
		    jsonArray.add(jos);   
		}
		for(int i=0;i<pageRegisteCheckItem.getList().size();i++){
			if (pageRegisteCheckItem.getList().get(i)!=null){
			   Map<String, String> itemmap = new HashMap<String, String>(); 
			   itemmap.put("id", String.valueOf(pageRegisteCheckItem.getList().get(i).getCheckitem()));  
		       itemmap.put("name", itemList.get(pageRegisteCheckItem.getList().get(i).getCheckitem()));  
		       JSONObject jo = JSONObject.fromObject(itemmap);  
		       jsonArray.add(jo);
			}
		}
    	info = jsonArray.toString();
    	return "ajax";  
    }
    /**
     * 通过 ajax 方式，获取页面点击不同的样品登记时，得到检验项目
     * @return
     */
    public String getCheckItems_bak(){
    	SampleRegiste sampleRegiste = sampleRegisteService.getSampleRegiste(Integer.parseInt(registeid));
		// 设置检验项目
    	itemList = checkItemService.getAll();
		/*
    	String checkitem = sampleRegiste.getCheckitem();
		checkitem = checkitem.substring(1, checkitem.length()-1);
		String[] ins = checkitem.split("\\|");
		*/
    	//从登记取检验项目改为从派样单中读取
    	//PageModel<RegisteCheckItem> pageRegisteCheckItem = registeCheckItemService.getRegisteCheckItem(sampleRegiste.getId(), 1, Constants.MAX_COUNT);
    	SendSample ss = new SendSample();
    	ss.setSampleno(registeid);
    	PageModel<SendSample> pss = sendSampleService.getSendSample(ss, 1, Constants.MAX_COUNT);
    	
    	if (pss==null||pss.getList().size()==0) return null;
    	
    	String tmp = "";
    	Vector vt = new Vector();
    	Vector vtid = new Vector();
    	
    	for(int i=0;i<pss.getList().size();i++){
//    		tmp += pss.getList().get(i).getId();
//    		
//    		if (i+1!=pss.getList().size()){
//    			tmp +=",";
//    		}
    		// 去掉再次派样的后的原派样（只保留没有再次派样的和最后收到的派样,中间的派样默认不做实验结果的录入）
    		if(pss.getList().get(i).getSendid()!=null){
    		      vt.add(pss.getList().get(i).getSendid());
    		}
    		vtid.add(pss.getList().get(i).getId());
    	}
    	/*
    	for(int i=0;i<vt.size();i++){
    		vtid.remove(vt.get(i));
    	}
    	*/
    	for(int i=0;i<vtid.size();i++){
    		tmp += vtid.get(i);
    		if (i+1!=vtid.size()){
    			tmp +=",";
    		}
    	}
    	String[] ids = tmp.split(",");
    	PageModel<SendCheckItem> pageSendCheckItem = sendCheckItemService.getSendCheckItem(ids, 1, Constants.MAX_COUNT);
    	
    	info = "";
		JSONArray jsonArray = new JSONArray();
		Map<String, String> sampleinfo = new HashMap<String, String>(); 
		// 如果是修改的情况下，不增加下面的
		if (modifyflag!=1){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	String code = sdf.format(sampleRegiste.getReceivedate());
	    	
			entrustcompanyList = entrustCompanyService.getAll();
			sampleinfo.put("id",sampleRegiste.getSampleno());  
			sampleinfo.put("name", sampleRegiste.getSamplename());
			sampleinfo.put("entrustname", entrustcompanyList.get(Integer.parseInt(sampleRegiste.getEntrustcompany())));
			sampleinfo.put("productunit", sampleRegiste.getProductionunit());
			sampleinfo.put("status", sampleRegiste.getSamplestatus());
			sampleinfo.put("model", sampleRegiste.getSamplemodel());
			sampleinfo.put("count", sampleRegiste.getSamplecount());
			sampleinfo.put("receivedate", code);
			JSONObject jos = JSONObject.fromObject(sampleinfo);  
		    jsonArray.add(jos);   
		}
		for(int i=0;i<pageSendCheckItem.getList().size();i++){
			CheckStandard check = checkStandardService.getCheckStandard(sampleRegiste.getCheckstandardid());
			if (pageSendCheckItem.getList().get(i)!=null&&pageSendCheckItem.getList().get(i).getInstrumentcode()!=null&&!pageSendCheckItem.getList().get(i).equals("")){
			   Map<String, String> itemmap = new HashMap<String, String>(); 
		       itemmap.put("subno", pageSendCheckItem.getList().get(i).getSubsendsampleno());
			   itemmap.put("id", String.valueOf(pageSendCheckItem.getList().get(i).getCheckitem()));  
		       itemmap.put("name", itemList.get(Integer.parseInt(pageSendCheckItem.getList().get(i).getCheckitem())));  
		       itemmap.put("type", pageSendCheckItem.getList().get(i).getType());
		       itemmap.put("standard", "");
		       //itemmap.put("instrument", pageSendCheckItem.getList().get(i).getInstrumentcode());
		       if(pageSendCheckItem.getList().get(i).getInstrumentcode()!=null){
		          Instrument ins = instrumentService.getInstrument(Integer.parseInt(pageSendCheckItem.getList().get(i).getInstrumentcode()));
		          itemmap.put("instrument", String.valueOf(ins.getId()));
		          itemmap.put("instrumentname", ins.getInsname());
		       }else{
		    	  itemmap.put("instrument", "");
			      itemmap.put("instrumentname", "");
		       }
		       if (pageSendCheckItem.getList().get(i).getCheckscope()==null)
		    	   itemmap.put("checkscope", "");
		       else
		           itemmap.put("checkscope", pageSendCheckItem.getList().get(i).getCheckscope());
		       if (pageSendCheckItem.getList().get(i).getUnit()==null)
		    	   itemmap.put("unit", "");
		       else
		           itemmap.put("unit", pageSendCheckItem.getList().get(i).getUnit());
		       if(pageSendCheckItem.getList().get(i).getCheckresult()==null)
		    	   itemmap.put("checkresult", "");
		       else
		           itemmap.put("checkresult", pageSendCheckItem.getList().get(i).getCheckresult());
		       JSONObject jo = JSONObject.fromObject(itemmap);  
		       jsonArray.add(jo);
			}
		}
    	info = jsonArray.toString();
    	return "ajax";  
    }
    /** 2015. 11 .18 修改从登记单中取，派样功能去掉了
     * 通过 ajax 方式，获取页面点击不同的样品登记时，得到检验项目
     * @return
     */
    public String getCheckItems(){
    	SampleRegiste sampleRegiste = sampleRegisteService.getSampleRegiste(Integer.parseInt(registeid));
		// 设置检验项目
    	itemList = checkItemService.getAll();
    	//从登记取检验项目
    	PageModel<RegisteCheckItem> pci = registeCheckItemService.getRegisteCheckItem(sampleRegiste.getId(), 1, Constants.MAX_COUNT);
    	
    	//zjx
    	SendSample ss = new SendSample();
    	ss.setSampleno(registeid);
    	PageModel<SendSample> pss = sendSampleService.getSendSample(ss, 1, Constants.MAX_COUNT);
    	
    	if (pss==null||pss.getList().size()==0) return null;
    	
    	String tmp = "";
    	Vector vt = new Vector();
    	Vector vtid = new Vector();
    	
    	for(int i=0;i<pss.getList().size();i++){

    		if(pss.getList().get(i).getSendid()!=null){
    		      vt.add(pss.getList().get(i).getSendid());
    		}
    		vtid.add(pss.getList().get(i).getId());
    	}

    	for(int i=0;i<vtid.size();i++){
    		tmp += vtid.get(i);
    		if (i+1!=vtid.size()){
    			tmp +=",";
    		}
    	}
    	String[] ids = tmp.split(",");
    	PageModel<SendCheckItem> pageSendCheckItem = sendCheckItemService.getSendCheckItem(ids, 1, Constants.MAX_COUNT);
    	//
    	
    	
    	if (pci==null||pci.getList().size()==0) return null;
    	
    	info = "";
		JSONArray jsonArray = new JSONArray();
		Map<String, String> sampleinfo = new HashMap<String, String>(); 
		// 如果是修改的情况下，不增加下面的
		if (modifyflag!=1){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	String code = sdf.format(sampleRegiste.getReceivedate());
	    	
	    	
			entrustcompanyList = entrustCompanyService.getAll();
			sampleinfo.put("id",sampleRegiste.getSampleno());  
			sampleinfo.put("name", sampleRegiste.getSamplename());
			sampleinfo.put("entrustname", entrustcompanyList.get(Integer.parseInt(sampleRegiste.getEntrustcompany())));
			sampleinfo.put("productunit", sampleRegiste.getProductionunit());
			sampleinfo.put("status", sampleRegiste.getSamplestatus());
			sampleinfo.put("model", sampleRegiste.getSamplemodel());
			sampleinfo.put("count", sampleRegiste.getSamplecount());
			sampleinfo.put("receivedate", code);
			JSONObject jos = JSONObject.fromObject(sampleinfo);  
		    jsonArray.add(jos);   
		}
		
		for(int i=0;i<pci.getList().size();i++){
			if (pci.getList().get(i)!=null&&!pci.getList().get(i).equals("")){
				Map<String, String> itemmap = new HashMap<String, String>(); 
				CheckStandard check = checkStandardService.getCheckStandard(sampleRegiste.getCheckstandardid());
			       itemmap.put("subno", "");
				   itemmap.put("id", String.valueOf(pci.getList().get(i).getCheckitem()));  
			       itemmap.put("name", itemList.get(pci.getList().get(i).getCheckitem()));  
			       itemmap.put("type", "");
			       itemmap.put("standard",check.getStandardcode());
			       if(pageSendCheckItem.getList().get(i).getInstrumentcode()!=null)
			       {	
			    	    Instrument yiqi = instrumentService.getInstrument(Integer.parseInt(pageSendCheckItem.getList().get(i).getInstrumentcode()));
			          	itemmap.put("instrument", String.valueOf(yiqi.getId()));
			          	itemmap.put("instrumentname", yiqi.getInsname());
			       }
			       else
			       {
			    	   itemmap.put("instrumentname","");
			    	   itemmap.put("instrument", "");
			       }
			    	   
			       if(pageSendCheckItem.getList().get(i).getCheckscope()!=null)
			    	   itemmap.put("checkscope",pageSendCheckItem.getList().get(i).getCheckscope());
			       else
			    	   itemmap.put("checkscope","");
			       if(pageSendCheckItem.getList().get(i).getUnit()!=null)
			    	   itemmap.put("unit",pageSendCheckItem.getList().get(i).getUnit());
			       else
			    	   itemmap.put("unit","");
			       if(pageSendCheckItem.getList().get(i).getCheckresult()!=null)
			    	   itemmap.put("checkresult",pageSendCheckItem.getList().get(i).getCheckresult());
			       else
			    	   itemmap.put("checkresult","");
		    	   
			       
			       JSONObject jo = JSONObject.fromObject(itemmap);  
			       jsonArray.add(jo);
			}
		}
    	info = jsonArray.toString();
    	return "ajax";  
    }
	public int[] getIds() {
		return ids;
	}
	public void setIds(int[] ids) {
		this.ids = ids;
	}
	// Validate Methods
	public void validateSave() {
		checkData();
	}
	public void validateUpdate() {
		checkData();
	}
	// getters and setters
	public void setCheckReport(CheckReport peo) {
		this.checkReport = peo;
	}
	public CheckReport getCheckReport() {
		return checkReport;
	}
	public String[] getSelectCheckItems() {
		return selectCheckItems;
	}
	public void setSelectCheckItems(String[] selectCheckItems) {
		this.selectCheckItems = selectCheckItems;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Integer getModifyflag() {
		return modifyflag;
	}
	public void setModifyflag(Integer modifyflag) {
		this.modifyflag = modifyflag;
	}
	public String getRegisteid() {
		return registeid;
	}
	public void setRegisteid(String registeid) {
		this.registeid = registeid;
	}
	public String[] getCheckid() {
		return checkid;
	}
	public void setCheckid(String[] checkid) {
		this.checkid = checkid;
	}
	public String[] getCheckstandard() {
		return checkstandard;
	}
	public void setCheckstandard(String[] checkstandard) {
		this.checkstandard = checkstandard;
	}
	public String[] getInstrumentname() {
		return instrumentname;
	}
	public void setInstrumentname(String[] instrumentname) {
		this.instrumentname = instrumentname;
	}
	public String[] getCheckresult() {
		return checkresult;
	}
	public void setCheckresult(String[] checkresult) {
		this.checkresult = checkresult;
	}
	public String[] getOptvalue() {
		return optvalue;
	}
	public void setOptvalue(String[] optvalue) {
		this.optvalue = optvalue;
	}
	public String[] getUnit() {
		return unit;
	}
	public void setUnit(String[] unit) {
		this.unit = unit;
	}
	public String[] getCheckscope() {
		return checkscope;
	}
	public void setCheckscope(String[] checkscope) {
		this.checkscope = checkscope;
	}
	public String[] getCode() {
		return code;
	}
	public void setCode(String[] code) {
		this.code = code;
	}

}
