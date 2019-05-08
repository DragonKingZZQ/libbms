package com.zhirui.business.lib.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhirui.business.lib.service.QrCodeService;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import sun.misc.BASE64Encoder;

import com.zhirui.business.common.Constants;
import com.zhirui.business.common.bean.User;
import com.zhirui.business.common.service.UserService;
import com.zhirui.business.lib.bean.CheckImage;
import com.zhirui.business.lib.bean.CheckItem;
import com.zhirui.business.lib.bean.CheckStandard;
import com.zhirui.business.lib.bean.Instrument;
import com.zhirui.business.lib.bean.LabProcess;
import com.zhirui.business.lib.bean.LabProcessContent;
import com.zhirui.business.lib.bean.RegisteCheckItem;
import com.zhirui.business.lib.bean.SampleRegiste;
import com.zhirui.business.lib.bean.SendCheckItem;
import com.zhirui.business.lib.bean.SendSample;
import com.zhirui.business.lib.bean.SubSendSampleNoQrCode;
import com.zhirui.business.lib.dao.LabProcessDao;
import com.zhirui.business.lib.service.CheckImageService;
import com.zhirui.business.lib.service.CheckItemService;
import com.zhirui.business.lib.service.CheckStandardService;
import com.zhirui.business.lib.service.InstrumentService;
import com.zhirui.business.lib.service.LabProcessService;
import com.zhirui.business.lib.service.RegisteCheckItemService;
import com.zhirui.business.lib.service.RelationUserService;
import com.zhirui.business.lib.service.SampleRegisteService;
import com.zhirui.business.lib.service.SendCheckItemService;
import com.zhirui.business.lib.service.SendSampleService;
import com.zhirui.business.lib.service.SubSendSampleNoQrCodeService;
import com.zhirui.business.utils.ApplicationUtils;
import com.zhirui.business.utils.ExportDocUtils;
import com.zhirui.business.utils.BusinessUtils;

import cn.kepu.base.MessageType;
import cn.kepu.base.action.PageAction;
import cn.kepu.utils.ContextUtils;
import cn.kepu.utils.PageModel;

@SuppressWarnings("serial")
public class SendSampleAction extends PageAction<SendSample> {
	
	@Autowired
	private QrCodeService qrCodeService;
	@Autowired
	private SendSampleService sendSampleService;
	@Autowired
	private RelationUserService relationUserService;
	@Autowired
	private CheckItemService checkItemService;
	@Autowired
	private InstrumentService instrumentService;
	@Autowired
	private RegisteCheckItemService registeCheckItemService;
	@Autowired
	private SampleRegisteService sampleRegisteService;
	@Autowired
	private SendCheckItemService sendCheckItemService;
	@Autowired
	private UserService userService;
	@Autowired
	private LabProcessService labProcessService;   //曾智琴
	@Autowired
	private CheckImageService checkImageService;   //曾智琴
	@Autowired
	private SubSendSampleNoQrCodeService subSendSampleNoQrCodeService; //曾智琴
	@Autowired
	private CheckStandardService checkStandardService; //曾智琴
	
	
	private SendSample sendSample;
	
	private SendCheckItem sendCheckItem;   //曾智琴	
	private SampleRegiste sampleRegiste; //曾智琴
	private CheckStandard checkStandard; //曾智琴
	private CheckItem checkItem;  //曾智琴
	private Instrument instrument; //曾智琴
	private LabProcess labprocess; //曾智琴
	private String erimage;  //曾智琴
	private List<String> images; //曾智琴
	
	public Map<String,String> map;
	public Map<Integer,String> userList;
	public Map<Integer,String> itemList;
	public Map<Integer,String> registeList;
	public Map<String,String> sendCheckList;
	public Map<Integer,String> labprocesstitlesmap;  //曾智琴
	private List<SendCheckItem> sciList;
	private List<SendCheckItem> mysciList;
	public Map<Integer,String> instrumentList;
	private int[] ids;
	private String[]  checkid;
	private String[]  optvalue;
	private String[] inputAccording;
	private String userid;
	private int sendsampleid;//曾智琴 提交按钮用到
	private String info;
	private Integer modifyflag;
	private List<Integer> sendcheck = new ArrayList<Integer>();
	private List storeList = new ArrayList();
	private List subno = new ArrayList();
	private List mysubno = new ArrayList();
	
	//取不同的登记单信息
	private String registeid;
	private String[] instrumentname;
	private String[] checkscope;
	private String[] unit;
	private String[] result;
	private String[] code;
	private String[] labprocesstitle;  //曾智琴
	private String[] balance;  //曾智琴
	private String[] balanceaccuracy; //曾智琴
	private String[] temperature;  //曾智琴
	private String[] humidity;   //曾智琴
	
	
	//曾智琴
	public String[] getLabprocesstitle() {
		return labprocesstitle;
	}
	public void setLabprocesstitle(String[] labprocesstitle) {
		this.labprocesstitle = labprocesstitle;
	}
	public String[] getBalance() {
		return balance;
	}
	public void setBalance(String[] balance) {
		this.balance = balance;
	}
	public String[] getTemperature() {
		return temperature;
	}
	public void setTemperature(String[] temperature) {
		this.temperature = temperature;
	}
	public String[] getHumidity() {
		return humidity;
	}
	public void setHumidity(String[] humidity) {
		this.humidity = humidity;
	}
	public String[] getBalanceaccuracy() {
		return balanceaccuracy;
	}
	public void setBalanceaccuracy(String[] balanceaccuracy) {
		this.balanceaccuracy = balanceaccuracy;
	}

	public int getSendsampleid() {
		return sendsampleid;
	}
	public void setSendsampleid(int sendsampleid) {
		this.sendsampleid = sendsampleid;
	}



	private List<File> file; //曾智琴 上传图片
	private List<String> fileFileName;  //上传图片名称
	private List<String> fileContentType;
	private List<String> dataUrl;
	private List<String> dataName;
	
	
	public List<File> getFile() {
		return file;
	}
	public void setFile(List<File> file) {
		this.file = file;
	}
	public List<String> getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}
	public List<String> getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}
	public List<String> getDataUrl() {
		return dataUrl;
	}
	public void setDataUrl(List<String> dataUrl) {
		this.dataUrl = dataUrl;
	}
	
	
	
	private int examineUser;
    private int checkuser;
    private int pagerecorders;
  //lyg
  		private String bigcode;
  		
  		
  		public String getBigcode() {
  			return bigcode;
  		}
  		public void setBigcode(String bigcode) {
  			this.bigcode = bigcode;
  		}

	public int getPagerecorders() {
		return pagerecorders;
	}
	public void setPagerecorders(int pagerecorders) {
		this.pagerecorders = pagerecorders;
	}
	private static final Log log = LogFactory.getLog(SendSampleAction.class);
	
	/**
	 * 初始化 检验用户 委托单位 检验项目 资质等
	 */
	private void initData(){
		Map<String,String> startmap = ApplicationUtils.getExamineUser();
		// 只查询出本部门的人员
		
		map = new LinkedHashMap<String, String>();
		int corp = BusinessUtils.getCurrentUser().getOffice();
		if (corp!=1){
			for (Object o : startmap.keySet()) {
				   User user = userService.getUser(Integer.parseInt((String)o));
				   //7.31 增加 检验负责人之间可互派
				   if(corp==user.getOffice()||user.getAuthority().indexOf("|B|C|")>=0){
					   String s = startmap.get(o);
					   map.put((String)o, s);
				   }
			}
		}else{
			map = startmap;
		}
		userList = relationUserService.getAll();
		itemList = checkItemService.getAll();
		registeList = sampleRegisteService.getSubmitRegisted();
		
				
	}
	/**
	 * 列表功能
	 * @return
	 */
	public String list() {
		map = ApplicationUtils.getExamineUser();
		if (pagerecorders<=0){
			pagerecorders=10;
		}
		pageModel = sendSampleService.getSendSample(sendSample, pageNo, pagerecorders);
		System.out.println(pageModel.getList().toString()+"pageModel的值");	
		/*System.out.println(pageModel.getList().get(0).getSendsamplename()+"第一个值");
		System.out.println(pageModel.getList().get(1).getSendsamplename()+"第二个值");
		//检测pagemodel内容
		for(SendSample sr : pageModel.getList()){		   
		    System.out.println(sr.getSendsamplename()+"送样名称"); 
		    System.out.println(pageModel.getList().toString()+"pageModel的值");			
		}*/
		return LIST;
	}

	public String add() {
		initData();

		return ADD;
	}
	public String remove() {
		if(sendSample == null) {
			log.warn("用户非法访问");
			ContextUtils.setOpMessage(MessageType.WARN, "用户非法访问");
			return ERROR;
		}
		if(sendSampleService.removeSendSample(sendSample.getId())) {
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据删除成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据删除失败");
		return ERROR;
	}
	public String batchremove() {
		if(sendSampleService.removeSendSamples(ids)) {
				ContextUtils.setOpMessage(MessageType.SUCCESS, "数据批量删除成功");
				return SUCCESS;
		}
		
		ContextUtils.setOpMessage(MessageType.ERROR, "数据批量删除失败");
		return SUCCESS;
	}
	public String modify() {
		initData();
		sendSample = sendSampleService.getSendSample(sendSample.getId());
		this.getSendCheckItems();
		//this.registeid = sendSample.getSampleno();
		String[] store = sendSample.getStorecondition().split(",");
		for(int i=0;i<store.length;i++){
			storeList.add(store[i].trim());
		}
		return "modify";
	}
	/**
	 * 点击收样调用收样页面
	 * @return
	 */
	public String receiveSample() {
		initData();
		sendSample = sendSampleService.getSendSample(sendSample.getId());
		//曾智琴
		sampleRegiste = sampleRegisteService.getSampleRegiste(Integer.parseInt(sendSample.getSampleno()));
		checkStandard = checkStandardService.getCheckStandard(sampleRegiste.getCheckstandardid());
		sendSample.setCheckstandardname("");
		
		
		User user = userService.getUser(sendSample.getExamineuser());
		sendSample.setExamineuserStr(user.getShowname());
		this.getSendCheckItems();
		return "receivesample";
	}
	/**
	 * 确认收样
	 * @return
	 */
	public String receive(){
		SendSample newsendSample = sendSampleService.getSendSample(sendSample.getId());
		newsendSample.setRemark(sendSample.getRemark());
		newsendSample.setReceiveflag("1");
		newsendSample.setReceivedate(new Date());
		newsendSample.setReceiveuser(BusinessUtils.getCurrentUser().getUid());
		newsendSample.setStatus(Constants.STATUS_RECEIVEDSAMPLE);
		newsendSample.setReceivewarnflag("1");
		sendSampleService.updateSendSample(newsendSample);
		ContextUtils.setOpMessage(MessageType.SUCCESS, "收样成功");
		//修改登记单状态
		SampleRegiste sampleRegiste = sampleRegisteService.getSampleRegiste(Integer.parseInt(newsendSample.getSampleno()));
		if (sampleRegiste!=null){
			sampleRegiste.setStatus(Constants.STATUS_RECEIVEDSAMPLE);
			sampleRegiste.setStatuschangedate(new Date());
			sampleRegisteService.updateSampleRegiste(sampleRegiste);
		}
		return SUCCESS;
	}
	//点击 完成检测调用
	public String checkfinish() {
		initData();
		
		//得到实验过程标题   曾智琴
		labprocesstitlesmap  = labProcessService.getAllTitles();	
		instrumentList = instrumentService.getAll();
		
		sendSample = sendSampleService.getSendSample(sendSample.getId());
		String[] store = sendSample.getStorecondition().split(",");
		for(int i=0;i<store.length;i++){
			storeList.add(store[i].trim());
		}
		
		
		//曾智琴
		sampleRegiste = sampleRegisteService.getSampleRegiste(Integer.parseInt(sendSample.getSampleno()));
		checkStandard = checkStandardService.getCheckStandard(sampleRegiste.getCheckstandardid());
		//sendSample.setCheckstandardname(checkStandard.getStandardcode());
		
		this.getSendCheckItems();
		return "checkfinish";
	}
	
	//点击 完成检测调用
		public String checkfinish(String returnvalue) {
			initData();
			
			//得到实验过程标题   曾智琴
			labprocesstitlesmap  = labProcessService.getAllTitles();	
			instrumentList = instrumentService.getAll();
			
			sendSample = sendSampleService.getSendSample(sendSample.getId());
			sendSample.setReturnvalue("保存成功");
			String[] store = sendSample.getStorecondition().split(",");
			for(int i=0;i<store.length;i++){
				storeList.add(store[i].trim());
			}
			
			
			//曾智琴
			sampleRegiste = sampleRegisteService.getSampleRegiste(Integer.parseInt(sendSample.getSampleno()));
			checkStandard = checkStandardService.getCheckStandard(sampleRegiste.getCheckstandardid());
			//sendSample.setCheckstandardname(checkStandard.getStandardcode());
			
			this.getSendCheckItems();
			return "checkfinish";
		}
	
	//点击 检验完成单的保存按钮调用
	public String checkfinishsave() throws IOException {
		uploadImage();
		SendSample ss = sendSampleService.getSendSample(sendSample.getId());
        
		
		String returnvalue = "1";
		//ss.setFinishflag("1");  //曾智琴
        //ss.setFinishexaminuser(sendSample.getFinishexaminuser());
		//曾智琴
		//ss.setFinishexaminuser(BusinessUtils.getCurrentUser().getUid());
        //ss.setFinishdate(sendSample.getFinishdate());
        ss.setRemark(sendSample.getRemark());
        ss.setFinishdate(sendSample.getFinishdate());
        
        //ss.setStatus(Constants.STATUS_FINISHCHECK);  //曾智琴
        //ss.setStatuschangedate(new Date());   //曾智琴
        
        if (sendSampleService.updateSendSample(ss)==null){
        	ContextUtils.setOpMessage(MessageType.ERROR, "数据保存失败");
        	return SUCCESS;
        }
       
        //曾智琴  将图片路径和派样单ID保存到图片表
        CheckImage checkImage = new CheckImage();   //曾智琴
        if(dataUrl != null){
        	for(int j=0;j<dataUrl.size();j++){
        		System.out.println(dataUrl.get(j));
        		checkImage.setImageurl(dataUrl.get(j));
        		checkImage.setSendsampleid(sendSample.getId());
        		checkImage.setSampleno(ss.getSendsampleno());
        		checkImage.setImagename(dataName.get(j));
        		System.out.println("保存图片时候的"+dataName.get(j)+ss.getSendsampleno());
        		checkImageService.addCheckImage(checkImage);
        	}
        }
        
        
        //更新检验项目数据
        if (checkid!=null){
			for(int i=0;i<checkid.length;i++){
				SendCheckItem sendCheckItem = sendCheckItemService.getSendCheckItem(Integer.parseInt(checkid[i]));
				//sendCheckItem.setType(optvalue[i]);
				//sendCheckItem.setStandard(inputAccording[i]);
				sendCheckItem.setInstrumentcode(instrumentname[i]);
				sendCheckItem.setCheckscope(checkscope[i]);
				//sendCheckItem.setUnit();
				sendCheckItem.setLabprocess(labprocesstitle[i]);  //曾智琴
				sendCheckItem.setBalance(balance[i]);  //曾智琴
				sendCheckItem.setBalanceaccuracy(balanceaccuracy[i]);   //曾智琴
				sendCheckItem.setTemperature(temperature[i]);  //曾智琴
				sendCheckItem.setHumidity(humidity[i]);   //曾智琴
				if(result[i]!=null){
					sendCheckItem.setCheckresult(result[i]); 
				}
				sendCheckItem.setSubsendsampleno(code[i]);				
				
				sendCheckItemService.updateSendCheckItem(sendCheckItem);
				
			}
		}
        /*//更改登记表状态
        //7.28 当登记单的所有检验项目都完成的时候，更新状态
        SampleRegiste sampleRegiste = sampleRegisteService.getSampleRegiste(Integer.parseInt(ss.getSampleno()));

		boolean finishFlag = true;
		PageModel<RegisteCheckItem> prci = registeCheckItemService.getRegisteCheckItem(sampleRegiste.getId(), 1,Constants.MAX_COUNT);
		for(RegisteCheckItem rci : prci.getList()){
			boolean flag = false;
			PageModel<SendCheckItem> psci = sendCheckItemService.getSendCheckItemForRegiste(sampleRegiste.getSampleno(), 1,Constants.MAX_COUNT);
			for(SendCheckItem sci:psci.getList()){
				if (rci.getCheckitem()==Integer.parseInt(sci.getCheckitem()) &&sci.getInstrumentcode()!=null){
					flag = true;
					break;
				}
			}
			if (!flag){
				finishFlag = false;
				break;
			}
		}
		if (finishFlag){
			//修改登记单状态
			sampleRegiste.setStatus(Constants.STATUS_FINISHCHECK);
			sampleRegiste.setStatuschangedate(new Date());
			sampleRegisteService.updateSampleRegiste(sampleRegiste);
		}*/
        ContextUtils.setOpMessage(MessageType.SUCCESS, "数据保存成功");
        
		return checkfinish(returnvalue);
	}
	
	//曾智琴 完成检测提交按钮，改变状态
	public void  checkcommit(){		
		SendSample ss = sendSampleService.getSendSample(sendsampleid);
		ss.setFinishflag("1");
		ss.setFinishexaminuser(BusinessUtils.getCurrentUser().getUid());               
		ss.setStatus(Constants.STATUS_FINISHCHECK);
	    ss.setStatuschangedate(new Date());
	    if (sendSampleService.updateSendSample(ss)==null){
        	ContextUtils.setOpMessage(MessageType.ERROR, "提交数据失败！");
        	
        }
	    
	    
	    //更改登记表状态
        //7.28 当登记单的所有检验项目都完成的时候，更新状态
        SampleRegiste sampleRegiste = sampleRegisteService.getSampleRegiste(Integer.parseInt(ss.getSampleno()));

		boolean finishFlag = true;
		PageModel<RegisteCheckItem> prci = registeCheckItemService.getRegisteCheckItem(sampleRegiste.getId(), 1,Constants.MAX_COUNT);
		for(RegisteCheckItem rci : prci.getList()){
			boolean flag = false;
			PageModel<SendCheckItem> psci = sendCheckItemService.getSendCheckItemForRegiste(sampleRegiste.getSampleno(), 1,Constants.MAX_COUNT);
			for(SendCheckItem sci:psci.getList()){
				if (rci.getCheckitem()==Integer.parseInt(sci.getCheckitem()) &&sci.getInstrumentcode()!=null){
					flag = true;
					break;
				}
			}
			if (!flag){
				finishFlag = false;
				break;
			}
		}
		if (finishFlag){
			//修改登记单状态
			sampleRegiste.setStatus(Constants.STATUS_FINISHCHECK);
			sampleRegiste.setStatuschangedate(new Date());
			sampleRegisteService.updateSampleRegiste(sampleRegiste);
		}
        ContextUtils.setOpMessage(MessageType.SUCCESS, "提交数据成功！");
       
	}
	
	//上传图片     曾智琴
	public void uploadImage() throws IOException{
		dataUrl = new ArrayList<String>();
		dataName = new ArrayList<String>();
		
		 String imgpath = "upload/";
		 if(file != null){
		 for (int i = 0; i < file.size(); ++i) {

		     InputStream is = new FileInputStream(file.get(i));

		     String path = ServletActionContext.getServletContext().getRealPath("/");

		     System.out.println(path);
		     
		     DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		     
		     String formatDate = format.format(new Date());
		     
		     dataUrl.add(imgpath+formatDate+this.getFileFileName().get(i));		
		     
		     dataName.add(this.getFileFileName().get(i));
		     
		     File destFile = new File(path+imgpath, formatDate+this.getFileFileName().get(i));

		     OutputStream os = new FileOutputStream(destFile);

		      byte[] buffer = new byte[400];

		     int length = 0;

		     while ((length = is.read(buffer)) > 0) {

		         os.write(buffer, 0, length);

		     }

		     is.close();

		     os.close();

		 }
		 }
	}
	
	public String save() {
		User user = BusinessUtils.getCurrentUser();
		SampleRegiste sampleRegiste = sampleRegisteService.getSampleRegiste(Integer.parseInt(sendSample.getSampleno()));
		//添加默认值
		sendSample.setLeader(user.getUid());
		sendSample.setCreateuser(user.getUid());
		sendSample.setCreatedate(new Date());
		sendSample.setStatus(Constants.STATUS_SENDSAMPLE);
		sendSample.setReceiveflag("0");
		sendSample.setReceivewarnflag("0");
		sendSample.setStorecondition(sampleRegiste.getStorecondition());
		//曾智琴  codes是checkitem 的id
		String codes = request.getParameter("post");
		if(!(codes.equals(checkid[0]))){
			PageModel<RegisteCheckItem> rci = registeCheckItemService.getRegisteCheckItem(sampleRegiste.getId(), 1, Constants.MAX_COUNT);
			for(int i=0;i<rci.getList().size();i++){
				if((rci.getList().get(i).getCheckitem().toString()).equals((checkid[0]))){
					rci.getList().get(i).setCheckitem(Integer.parseInt(codes));
					registeCheckItemService.updateRegisteCheckItem(rci.getList().get(i));
					System.out.println("更新成功");
				}
			}
		}
		System.out.println("codes值"+codes);
		//李沅罡
		qrCodeService.creatQrCode(bigcode);
		if((sendSample=sendSampleService.addSendSample(sendSample)) != null) {
			//保存送样单的检验项目
			if (checkid!=null){
				for(int i=0;i<checkid.length;i++){
					SendCheckItem sendCheckItem = new SendCheckItem();
					sendCheckItem.setSendsampleid(sendSample.getId());
					sendCheckItem.setSubsendsampleno(code[i]);
					//sendCheckItem.setCheckitem(checkid[i]);
					sendCheckItem.setCheckitem(codes);
/*					sendCheckItem.setType(optvalue[i]);*/
					/*sendCheckItem.setStandard(inputAccording[i]);*/
					sendCheckItemService.addSendCheckItem(sendCheckItem);
				}
			}
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据添加成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据添加失败");
		return SUCCESS;
	}
	public String update() {
		//添加默认值
		User user = BusinessUtils.getCurrentUser();
		String aa = sendSample.getSampleno();
		SampleRegiste sampleRegiste = sampleRegisteService.getSampleRegiste(Integer.parseInt(sendSample.getSampleno()));
		sendSample.setLeader(user.getUid());
		sendSample.setCreateuser(user.getUid());
		sendSample.setCreatedate(new Date());
		sendSample.setStorecondition(sampleRegiste.getStorecondition());
		if (sendSample.getReceiveflag()==null||sendSample.getReceiveflag().equals("0")){
		    sendSample.setReceiveflag("0");
			sendSample.setStatus(Constants.STATUS_SENDSAMPLE);
		}
		else{
			sendSample.setStatus(Constants.STATUS_RECEIVEDSAMPLE);
			sendSample.setReceiveflag("1");
		}
		if (sendSample.getFinishflag()!=null&&sendSample.getFinishflag().equals("1")){
			sendSample.setStatus(Constants.STATUS_FINISHCHECK);
			sendSample.setFinishflag("1");
		}else
			sendSample.setFinishflag("0");
		if (sendSample.getReceivewarnflag()==null)
		      sendSample.setReceivewarnflag("0");
		// 如果是继续派样的，修改后就更改状态为已经派样
		if (sendSample.getSendid()!=null)
			 sendSample.setStatus(Constants.STATUS_SAMPLED);
		if(sendSampleService.updateSendSample(sendSample) != null) {
			//删除 检验项目并且重新新增
			this.getSendCheckItems();
			for(Integer c:sendcheck){
			     sendCheckItemService.removeSendCheckItem(c);
			}
			//更新检查项目
			//保存送样单的检验项目
			if (checkid!=null){
				for(int i=0;i<checkid.length;i++){
					SendCheckItem sendCheckItem = new SendCheckItem();
					sendCheckItem.setSendsampleid(sendSample.getId());
					sendCheckItem.setCheckitem(checkid[i]);
					sendCheckItem.setSubsendsampleno(code[i]);
					/*sendCheckItem.setType(optvalue[i]);
					sendCheckItem.setStandard(inputAccording[i]);*/
					sendCheckItemService.addSendCheckItem(sendCheckItem);
				}
			}
			
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据更新成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据更新失败");
		return ERROR;
	}
	public String detail() {
		if(sendSample == null) {
			log.warn("用户非法访问");
			ContextUtils.setOpMessage(MessageType.WARN, "用户非法访问");
			return ERROR;
		}
		sendSample = sendSampleService.getSendSample(sendSample.getId());
		
		//设置检验人员和最终检验人员
		User user;
		if (sendSample.getExamineuser()!=null){
		    user = userService.getUser(sendSample.getExamineuser());
		    sendSample.setExamineuserStr(user.getShowname());
		}
		if (sendSample.getFinishexaminuser()!=null){
		    user = userService.getUser(sendSample.getFinishexaminuser());
		    sendSample.setFinishexaminuserStr(user.getShowname());
		}
		getSendCheckItems();
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
		if(sendSample == null) {
			log.warn("用户非法访问");
			ContextUtils.setOpMessage(MessageType.VALIDATE_FAILED, "用户非法访问");
			return ;
		}
	}

	/**
	 * 派样
	 * @return
	 */
	public String submit() {
		if(sendSample == null) {
			ContextUtils.setOpMessage(MessageType.WARN, "用户非法访问");
			return SUCCESS;
		}
		sendSample = sendSampleService.getSendSample(sendSample.getId());
		sendSample.setStatus(Constants.STATUS_SAMPLED);
		if (!sendSampleService.setStatus(sendSample)){
			ContextUtils.setOpMessage(MessageType.ERROR, "提交失败");
			return SUCCESS;
		}
		//更新登记单状态
		SampleRegiste sampleRegiste = sampleRegisteService.getSampleRegiste(Integer.parseInt(sendSample.getSampleno()));
		sampleRegiste.setStatus(Constants.STATUS_SAMPLED);
		sampleRegiste.setStatuschangedate(new Date());
		sampleRegisteService.updateSampleRegiste(sampleRegiste);
		//7.28 根据当前派样单，如果登记单没有选择负责人，则修改此负责人
		PageModel<SendCheckItem> psci = sendCheckItemService.getSendCheckItem(sendSample.getId(), 1, Constants.MAX_COUNT);
		PageModel<RegisteCheckItem> prci = registeCheckItemService.getRegisteCheckItem(Integer.parseInt(sendSample.getSampleno()), 1, Constants.MAX_COUNT);
		for(SendCheckItem sci:psci.getList()){
			int cid = Integer.parseInt(sci.getCheckitem());
			for(RegisteCheckItem rci:prci.getList()){
				if(rci.getCheckitem()==cid&&rci.getExamineuser()==null){
					rci.setExamineuser(sendSample.getExamineuser());
					registeCheckItemService.updateRegisteCheckItem(rci);
				}
			}
		}
		ContextUtils.setOpMessage(MessageType.SUCCESS, "提交成功");
		return SUCCESS;
	}
	/**
	 * 继续派样页面点击保存
	 * @return
	 */
	public String nextsubmit() {
		if(sendSample == null) {
			ContextUtils.setOpMessage(MessageType.WARN, "用户非法访问");
			return SUCCESS;
		}
		// 首先得到页面传来的信息（检验人，检验时间，备注，检验项目不用，否则取老数据会覆盖新数据）
		SendSample tmpsendsample = sendSampleService.getSendSample(sendSample.getId());
		//////////////保存检验项目及检验人等信息////////////////////
		tmpsendsample.setExamineuser(sendSample.getExamineuser());
		tmpsendsample.setStartdate(sendSample.getStartdate());
		tmpsendsample.setEnddate(sendSample.getEnddate());
		tmpsendsample.setSendid(sendSample.getId());
		tmpsendsample.setLeader(BusinessUtils.getCurrentUser().getUid());
		tmpsendsample.setReceiveflag("0");
		tmpsendsample.setReceiveuser(null);
		tmpsendsample.setReceivedate(null);
		tmpsendsample.setFinishflag("0");
		tmpsendsample.setFinishexaminuser(null);
		tmpsendsample.setFinishdate(null);
		tmpsendsample.setCreatedate(new Date());
		tmpsendsample.setCreateuser(BusinessUtils.getCurrentUser().getUid());
		tmpsendsample.setStatus(Constants.STATUS_SAMPLED);
		tmpsendsample.setStatuschangedate(null);
		tmpsendsample.setReceivewarnflag("0");
		//7.28 增加继续派样的样品数量信息
		tmpsendsample.setSamplecount(sendSample.getSamplecount());
		//7.27 查询检验项目是否都派出，都派出时 本条派样状态修改为 派样完成，否则能继续派样或做检验
		PageModel<SendCheckItem> psci = sendCheckItemService.getSendCheckItem(sendSample.getId(), 1, Constants.MAX_COUNT);
		if (psci.getList().size()==checkid.length){
			tmpsendsample.setStatus(Constants.STATUS_FINISHEDSAMPLE);
		}
//		if(sendSampleService.updateSendSample(sendSample) != null) {
		if((sendSample=sendSampleService.addSendSample(tmpsendsample)) != null) {
			//删除 检验项目并且重新新增
//			this.getSendCheckItems();
//			for(Integer c:sendcheck){
//			     sendCheckItemService.removeSendCheckItem(c);
//			}
			//更新检查项目
			//保存送样单的检验项目
			if (checkid!=null){
				for(int i=0;i<checkid.length;i++){
					SendCheckItem sendCheckItem = new SendCheckItem();
					sendCheckItem.setSendsampleid(sendSample.getId());
					sendCheckItem.setCheckitem(checkid[i]);
					sendCheckItem.setSubsendsampleno(code[i]);
					/*sendCheckItem.setType(optvalue[i]);
					sendCheckItem.setStandard(inputAccording[i]);*/
					sendCheckItemService.addSendCheckItem(sendCheckItem);
				}
			}
		}
		//////////////////////////////////////////////////////////
		ContextUtils.setOpMessage(MessageType.SUCCESS, "提交成功");
		return SUCCESS;
	}
	/**
	 * 继续派样
	 * 自动创建派样单
	 * @return
	 */
	public String continuesubmit() {
		if(sendSample == null) {
			ContextUtils.setOpMessage(MessageType.WARN, "用户非法访问");
			return SUCCESS;
		}
		sendSample = sendSampleService.getSendSample(sendSample.getId());
		/*增加派样单及相关表*/
		int id = sendSample.getId();
		sendSample.setSendid(sendSample.getId());
		sendSample.setLeader(BusinessUtils.getCurrentUser().getUid());
		sendSample.setReceiveflag("0");
		sendSample.setReceiveuser(null);
		sendSample.setReceivedate(null);
		sendSample.setExamineuser(null);
		sendSample.setFinishflag("0");
		sendSample.setFinishexaminuser(null);
		sendSample.setFinishdate(null);
		sendSample.setCreatedate(new Date());
		sendSample.setCreateuser(BusinessUtils.getCurrentUser().getUid());
		sendSample.setStatus(Constants.STATUS_SENDSAMPLE);
		sendSample.setStatuschangedate(null);
		sendSample.setReceivewarnflag("0");
		
		String[] store = sendSample.getStorecondition().split(",");
		for(int i=0;i<store.length;i++){
			storeList.add(store[i].trim());
		}
		/* 确认派样时在保存  3-19 
		sendSample = sendSampleService.addSendSample(sendSample);
		
		PageModel<SendCheckItem> sci = sendCheckItemService.getSendCheckItem(id, 1, Constants.MAX_COUNT);
        for(SendCheckItem ss : sci.getList()){
        	SendCheckItem item = new SendCheckItem();
        	item.setCheckitem(ss.getCheckitem());
        	item.setSendsampleid(sendSample.getId());
        	item.setStandard(ss.getStandard());
        	sendCheckItemService.addSendCheckItem(item);
        }
        */
        // 得到页面的 派样单列表和检验项目列表
        if (registeList==null)
        	 registeList = new HashMap<Integer,String>();
        registeList.put(Integer.parseInt(sendSample.getSampleno()), sendSample.getSendsampleno());
		this.getSendCheckItems();
		//map = ApplicationUtils.getExamineUser();
		Map<String,String> startmap = ApplicationUtils.getExamineUser();
		map = new LinkedHashMap<String, String>();
		int corp = BusinessUtils.getCurrentUser().getOffice();
		for (Object o : startmap.keySet()) {
			   User user = userService.getUser(Integer.parseInt((String)o));
			   if(corp==user.getOffice()){
				   String s = startmap.get(o);
				   map.put((String)o, s);
			   }
		}
        return "nextsend";
	}
	/**
	 * 取消派样
	 * @return
	 */
	public String cancelsubmit() {
		if(sendSample == null) {
			ContextUtils.setOpMessage(MessageType.WARN, "用户非法访问");
			return SUCCESS;
		}
		/* 7.31 改成修改状态，不删除数据
		//删除派样单检验项目
		PageModel<SendCheckItem> psci = sendCheckItemService.getSendCheckItem(sendSample.getId(), 1, Constants.MAX_COUNT);
		for(SendCheckItem sci:psci.getList()){
			sendCheckItemService.removeSendCheckItem(sci.getId());
		}
		//删除派样单
		sendSampleService.removeSendSample(sendSample.getId());
		*/
		sendSample = sendSampleService.getSendSample(sendSample.getId());
		sendSample.setStatus("C");
		sendSampleService.updateSendSample(sendSample);
		
		ContextUtils.setOpMessage(MessageType.SUCCESS, "取消派样成功");
        return SUCCESS;
	}
	public String batchsubmit() {
        for(int id:ids){
        	sendSample = sendSampleService.getSendSample(id);
        	sendSample.setStatus(Constants.STATUS_SAMPLED);
        	if (!sendSampleService.setStatus(sendSample)){
    			ContextUtils.setOpMessage(MessageType.ERROR, "提交失败");
    			return SUCCESS;
    		}
        }
        ContextUtils.setOpMessage(MessageType.SUCCESS, "提交成功");
        return SUCCESS;
	}
	
	public String export1() throws IOException{
		ExportDocUtils edu = new ExportDocUtils();
		Map<String,Object> dataMap=new HashMap<String,Object>();
		
		DateFormat format = new SimpleDateFormat("yyyyMMdd");	     
	    String formatDate = format.format(new Date());
	    
	    String path = ServletActionContext.getServletContext().getRealPath("/");
	    
	    images = new ArrayList<String>(); 
	    PageModel<CheckImage> checkimages = checkImageService.getCheckImageBySendSampleId(sendSample.getId());
//	    System.out.println(checkimages.toString()+"checkimage的值");
	    if(checkimages != null){
		    for(CheckImage checkimage:checkimages.getList()){
				try {
					System.out.println(path + checkimage.getImageurl()+"checkimage的值");
					images.add(getImageBase(path + checkimage.getImageurl()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	    }
	    System.out.println(images.size()+"图片size");
	    sendSample = sendSampleService.getSendSample(sendSample.getId());
	    System.out.println(sendSample.getId());
		sendCheckItem = sendCheckItemService.getSendCheckItemBySendSampleid(sendSample.getId());
		checkItem = checkItemService.getCheckItem(Integer.parseInt(sendCheckItem.getCheckitem()));
		instrument = instrumentService.getInstrument(Integer.parseInt(sendCheckItem.getInstrumentcode()));
		labprocess = labProcessService.getLabProcess(Integer.parseInt(sendCheckItem.getLabprocess()));
		//找检验标准
		sampleRegiste = sampleRegisteService.getSampleRegisteBySampleno(sendSample.getSendsampleno());
		CheckStandard check = checkStandardService.getCheckStandard(sampleRegiste.getCheckstandardid()); 
		
		SubSendSampleNoQrCode subSendSampleNoQrCode = subSendSampleNoQrCodeService.getSubSendSampleNoQrCode(sendCheckItem.getSubsendsampleno());
		String geterimage = subSendSampleNoQrCode.getQrcodeurl();
		erimage = path + geterimage;
		
		System.out.println("拿到的图片地址"+erimage);
		dataMap.put("temperature", sendCheckItem.getTemperature());
		dataMap.put("humidity", sendCheckItem.getHumidity());
		dataMap.put("date", formatDate);
		dataMap.put("checkitem", checkItem.getItemname());
		dataMap.put("standard","");
		dataMap.put("instrumentcode",instrument.getInscode());
		dataMap.put("instrumenttitle",instrument.getInsname());
		dataMap.put("balance",sendCheckItem.getBalance());
		if(sendCheckItem.getBalanceaccuracy()!=null){
			dataMap.put("balanceaccuracy",sendCheckItem.getBalanceaccuracy());
		}
		dataMap.put("labprocess",labprocess.getLabprocesscontent());
		dataMap.put("sendsamplename",sendSample.getSendsamplename());
		dataMap.put("sendsampleno",sendCheckItem.getSubsendsampleno());
		//二维码图片
		try {
			dataMap.put("erimage",getImageBase(erimage));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//循环普通图片
		if(images != null){
			dataMap.put("images",images);
		}
		String name = "";
		if (sendSample!=null)
			name = "TY-001通用";
		edu.exportWord(name,Constants.TEMPLATE_SENDSAMPLE,dataMap,request,response);
		return null;
	}
	
	
	// 导出word    曾智琴
		public String export2() throws IOException{
			ExportDocUtils edu = new ExportDocUtils();
			Map<String,Object> dataMap=new HashMap<String,Object>();
			
			DateFormat format = new SimpleDateFormat("yyyyMMdd");	     
		    String formatDate = format.format(new Date());
	    		      
			sendSample = sendSampleService.getSendSample(sendSample.getId());
			sendCheckItem = sendCheckItemService.getSendCheckItemBySendSampleid(sendSample.getId());
			checkItem = checkItemService.getCheckItem(Integer.parseInt(sendCheckItem.getCheckitem()));
			instrument = instrumentService.getInstrument(Integer.parseInt(sendCheckItem.getInstrumentcode()));
			labprocess = labProcessService.getLabProcess(Integer.parseInt(sendCheckItem.getLabprocess()));
			String path = ServletActionContext.getServletContext().getRealPath("/");
			SubSendSampleNoQrCode subSendSampleNoQrCode = subSendSampleNoQrCodeService.getSubSendSampleNoQrCode(sendCheckItem.getSubsendsampleno());
			String geterimage = subSendSampleNoQrCode.getQrcodeurl();
			erimage = path + geterimage;
			
			//找检验标准
			sampleRegiste = sampleRegisteService.getSampleRegisteBySampleno(sendSample.getSendsampleno());
			CheckStandard check = checkStandardService.getCheckStandard(sampleRegiste.getCheckstandardid()); 
			
			System.out.println("拿到的图片地址"+erimage);
			dataMap.put("temperature", sendCheckItem.getTemperature());
			dataMap.put("humidity", sendCheckItem.getHumidity());
			dataMap.put("date", formatDate);
			dataMap.put("checkitem", checkItem.getItemname());
			dataMap.put("standard","");
			dataMap.put("instrumentcode",instrument.getInscode());
			dataMap.put("instrumenttitle",instrument.getInsname());
			dataMap.put("balance",sendCheckItem.getBalance());
			dataMap.put("balanceaccuracy",sendCheckItem.getBalanceaccuracy());
			dataMap.put("labprocess",labprocess.getLabprocesscontent());
			dataMap.put("sendsamplename",sendSample.getSendsamplename());
			dataMap.put("sendsampleno",sendCheckItem.getSubsendsampleno());
			//二维码图片
			try {
				dataMap.put("erimage",getImageBase(erimage));
			} catch (Exception e) {
				e.printStackTrace();
			}
			String name = "";
			if (sendSample!=null)
				name = "YF-003离子色谱-液相-液质联用";
			edu.exportWord(name,Constants.TEMPLATE_YF003,dataMap,request,response);
			return null;
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
	
	public String getSendCheckItems__bak(){
		PageModel<SendCheckItem> sci = sendCheckItemService.getSendCheckItem(sendSample.getId(),1, Constants.MAX_COUNT);
		CheckItem ci;
		sendCheckList = new HashMap<String,String>();
		for(SendCheckItem one:sci.getList()){
			ci = checkItemService.getCheckItem(Integer.parseInt(one.getCheckitem()));
			sendCheckList.put(one.getCheckitem()+"|"+ci.getItemname(), one.getType()+"|"+one.getStandard());
			sendcheck.add(one.getId());
		}
		return SUCCESS;
	}
	public String getSendCheckItems(){
		PageModel<SendCheckItem> sci = sendCheckItemService.getSendCheckItem(sendSample.getId(),1, Constants.MAX_COUNT);
		PageModel<SendCheckItem> mysci = sendCheckItemService.getSendCheckItem(sendSample.getId(),1, Constants.MAX_COUNT);
		//曾智琴
		SampleRegiste sr = sampleRegisteService.getSampleRegiste(Integer.parseInt(sendSample.getSampleno()));
		CheckStandard cs = checkStandardService.getCheckStandard(sr.getCheckstandardid());
		CheckItem ci;
		// 判断子编号的有多少个
		String tmpno = "";
		for(SendCheckItem one:sci.getList()){
			ci = checkItemService.getCheckItem(Integer.parseInt(one.getCheckitem()));
			one.setCheckitemname(ci.getItemname());
			//one.setStandard(cs.getStandardcode());
			//得到检测仪器名称
			if (one.getInstrumentcode()!=null&&!one.getInstrumentcode().equals("")){
			   Instrument ins = instrumentService.getInstrument(Integer.parseInt(one.getInstrumentcode()));
			   one.setInstrumentname(ins.getInsname());
			}
			//曾智琴
			if(one.getLabprocess()!=null&&!one.getLabprocess().equals("")){
				LabProcess lab = labProcessService.getLabProcess(Integer.parseInt(one.getLabprocess()));
				one.setLabprocesstitle(Integer.parseInt(one.getLabprocess()));
				one.setLabprocess(lab.getLabprocesstitle());
			}
			//---------
			if (one.getSubsendsampleno()!=null&&!tmpno.equals(one.getSubsendsampleno())){
				subno.add(one.getSubsendsampleno());
				mysubno.add(one.getSubsendsampleno());
			}
			sendcheck.add(one.getId());
			if (one.getSubsendsampleno()!=null)
			    tmpno = one.getSubsendsampleno();
		}
		
		//如果有继续派样的，将不属于自己的检验项目去掉
		SendSample ss = new SendSample();
		ss.setSendid(sendSample.getId());
		
		PageModel<SendSample> pss = sendSampleService.getSendSample(ss, 1, Constants.MAX_COUNT);
	
		if (pss!=null&&pss.getList().size()>=1){
				String tmp = "";
				for(SendSample s:pss.getList()){
					 tmp += s.getId()+",";
				}
				if (!tmp.equals(""))
					tmp = tmp.substring(0,tmp.length()-1);
				String[] ids = tmp.split(",");
				PageModel<SendCheckItem> psci = sendCheckItemService.getSendCheckItem(ids, 1, Constants.MAX_COUNT);
				
				for(SendCheckItem item:psci.getList()){
				   for(int k=0;k<sci.getList().size();k++){
					   if(item.getCheckitem().equals(mysci.getList().get(k).getCheckitem())){
						    mysci.getList().remove(k);
						    //7.30 将去除的样品编号做一个再派样的说明
						    for(int i=0;i<mysubno.size();i++){
						    	if (mysubno.get(i).equals(item.getSubsendsampleno())){
						    		SendSample ssa = sendSampleService.getSendSample(item.getSendsampleid());
						    		if (ssa!=null){
						    		    String name = ssa.getExamineuserStr();
						    			mysubno.set(i, mysubno.get(i)+" 已经派样给【 "+name+" 】做检验！");
						    		}
						    	}
						    }
						    break;
					   }
				   }
				}
		}
		sciList = sci.getList();
		mysciList = mysci.getList();
		return SUCCESS;
	}
    /**
     * 通过 ajax 方式，获取页面点击不同的样品登记时，得到检验项目
     * 15.07.22 修改为只显示与选择的检验人员相关的检验项目（指定该检验员的项目和未确定的） 
     * @return
     */
	
    public String getCheckItems(){
    	SampleRegiste sampleRegiste = sampleRegisteService.getSampleRegiste(Integer.parseInt(registeid));
		// 设置检验项目
    	itemList = checkItemService.getAll();
		/*String checkitem = sampleRegiste.getCheckitem();
		checkitem = checkitem.substring(1, checkitem.length()-1);
		String[] ins = checkitem.split("\\|");
		*/
		PageModel<RegisteCheckItem> pageRegisteCheckItem = registeCheckItemService.getRegisteCheckItem(sampleRegiste.getId(), 1, Constants.MAX_COUNT);
		info = "";
		JSONArray jsonArray = new JSONArray();
		Map<String, String> sampleinfo = new HashMap<String, String>(); 
		// 如果是修改的情况下，不增加下面的
		//if (modifyflag!=1){
			sampleinfo.put("id",sampleRegiste.getSampleno());  
			sampleinfo.put("name", sampleRegiste.getSamplename());
			sampleinfo.put("count", sampleRegiste.getSamplecount());
			sampleinfo.put("store", sampleRegiste.getStorecondition());
			System.out.println(sampleRegiste.getSamplecount()+"数量");
			sampleinfo.put("accordingtype", String.valueOf(sampleRegiste.getAccordingtype()));
			sampleinfo.put("according", sampleRegiste.getAccordings());
			
			//4.15 根据样品数量，只显示没有派样的样品数量，页面生成对应数量的检验信息
			//---------------------------------------------------------
			//7.25 查询断号，生成包含断号和最新开始编号的 数组
//			int codestart = getMaxCode(sampleRegiste);			
//			sampleinfo.put("codestart", String.valueOf(codestart));
			//System.out.println("checkuser的值"+checkuser);
			Vector<Integer> vt = getMaxCode(sampleRegiste,checkuser);
			if(vt!=null)
			for(int i=0;i<vt.size();i++){
				//System.out.println("返回的vt"+vt.get(i));
			}
			//按照断号和最新号排序
			String tmp = sort(vt);
			
			sampleinfo.put("codestart", tmp);
			//---------------------------------------------------------
			
			//曾智琴
			Map<Integer,String> checkitems = checkItemService.getAll();
			sampleinfo.put("checkitems",checkitems.toString());
			
			JSONObject jos = JSONObject.fromObject(sampleinfo);  
		    jsonArray.add(jos);   
		//}
		for(int i=0;i<pageRegisteCheckItem.getList().size();i++){
			//增加检验员条件
			if (pageRegisteCheckItem.getList().get(i)!=null&&(pageRegisteCheckItem.getList().get(i).getExamineuser()==null||pageRegisteCheckItem.getList().get(i).getExamineuser()==checkuser)){
			   Map<String, String> itemmap = new HashMap<String, String>(); 
			   itemmap.put("id", String.valueOf(pageRegisteCheckItem.getList().get(i).getCheckitem()));  
		       itemmap.put("name", itemList.get(pageRegisteCheckItem.getList().get(i).getCheckitem()));  
		       itemmap.put("count", "0");  
		       itemmap.put("store", "");  
		       itemmap.put("accordingtype", String.valueOf(sampleRegiste.getAccordingtype()));
		       itemmap.put("according", sampleRegiste.getAccordings());
		       JSONObject jo = JSONObject.fromObject(itemmap);  
		       jsonArray.add(jo);
			}
		}
    	info = jsonArray.toString();
    	return "ajax";  
    }
    //排序和取断号
    private String sort(Vector<Integer> args) {
    	if (args==null) return "1";
    	Object[] s =args.toArray();
    	//冒泡排序
        for (int i = 0; i < args.size() - 1; i++) {
            for (int j = i + 1; j < args.size(); j++) {
                int temp;
                if ((Integer)s[i] > (Integer)s[j]) {
                    temp = (Integer)s[j];
                    s[j] = s[i];
                    s[i] = temp;
                }
            }
        }
        String temp = "";
        int maxnum = (Integer)s[s.length-1];
        for(int j=1;j<=maxnum;j++){
        	boolean flag = false;
	        for(int i=0;i<s.length;i++){
	        	if (j==(Integer)s[i]){
	        		flag = true;
	        		break;
	        	}
	        }
	        if (!flag)
	        	temp += j+",";
        }
        if (temp.equals("")){
        	maxnum++;
        	temp = s.length+","+ String.valueOf(maxnum)+","+String.valueOf(maxnum+1);
        	return temp;
        }
       	temp += String.valueOf((Integer)s[s.length-1]+1);
       	temp = s.length+","+ temp;
        return temp;
    }
    /**
     * 通过 ajax 方式，获取页面选择不同的检验员时，得到登记单
     * @return
     */
    public String getRegistes(){
    	PageModel<SampleRegiste> pageRegiste = sampleRegisteService.getRegisteForExamine(examineUser, 1, Constants.MAX_COUNT);
		info = "";
		JSONArray jsonArray = new JSONArray();
		Map<String, String> sampleinfo = new HashMap<String, String>(); 
        if (pageRegiste!=null&&pageRegiste.getList().size()>0){
        	for(int i=0;i<pageRegiste.getList().size();i++){
				sampleinfo.put("id",pageRegiste.getList().get(i).getId().toString());  
				sampleinfo.put("name", pageRegiste.getList().get(i).getSampleno());
				
				JSONObject jos = JSONObject.fromObject(sampleinfo);  
			    jsonArray.add(jos);   
        	}
        }
    	info = jsonArray.toString();
    	return "ajax";  
    }
    
    //曾智琴
    private Vector<Integer> getMaxCode(SampleRegiste sampleRegiste,int checkuser){
    	try{
    	SendSample ss = new SendSample();
    	ss.setSampleno(String.valueOf(sampleRegiste.getId()));
    	//得到所有该登记单的派样单
    	PageModel<SendSample> pss = sendSampleService.getSendSample(ss, 1, Constants.MAX_COUNT);
    	
    	//曾智琴
    	PageModel<RegisteCheckItem> registeck = registeCheckItemService.getRegisteCheckItem(sampleRegiste.getId(),checkuser, 1, Constants.MAX_COUNT);
    	int registeItemSize = registeck.getList().size();
    	System.out.println(registeItemSize+"登记的份数");
    	
    	
    	if (pss==null||pss.getList().size()==0) return null;
    	String tmp = "";
    	for(int i=0;i<pss.getList().size();i++){
    		tmp += pss.getList().get(i).getId();
    		if(i+1!=pss.getList().size()){
    			tmp += ",";
    		}
    	}
    	//曾智琴
    	String[] ids = tmp.split(",");
    	Vector<PageModel<SendCheckItem>> aa= new Vector<PageModel<SendCheckItem>>();
    	PageModel<SendCheckItem> psci = sendCheckItemService.getSendCheckItem(ids, 1, Constants.MAX_COUNT);
    	for(int i=0;i<ids.length;i++){
    		PageModel<SendCheckItem> psc = sendCheckItemService.getSendCheckItem(ids[i], 1, Constants.MAX_COUNT);
    		aa.add(psc);
    	}
    	//曾智琴
    	int sendItemSize = pss.getList().size();
    	System.out.println(sendItemSize+"派送的份数");
    	String code = "";
    	//保存所有派样的小编号
    	Vector<Integer> vt = new Vector<Integer>();
    	for(SendCheckItem s:psci.getList()){
    		
    		if (code.compareTo(s.getSubsendsampleno())<0){
    			code = s.getSubsendsampleno();
    		}
    		//查询vector中是否存在
    		boolean flag = false;
    		int no = Integer.parseInt(s.getSubsendsampleno().substring(15));
    		for(int i=0;i<vt.size();i++){
    		    if(vt.get(i)==no){
    		    	flag = true;
    		    	break;
    		    }
    		}
    		if (!flag)
    			System.out.println("添加了"+s.getSubsendsampleno().substring(15));
 			   vt.add(Integer.parseInt(s.getSubsendsampleno().substring(15)));
    	}
//    	if (code.equals("")) return 1;
    	//计算最大的号
//    	String num = code.substring(15);
//    	return Integer.parseInt(num)+1;
    	if (code.equals("")) return null;
    	
    	for(int i=0;i<vt.size();i++){
    		System.out.println(vt.get(i)+"返回前的vt");
    	}
    	
    	return vt;
        }catch(Exception e){
    		e.printStackTrace();
    	}
    	return null;
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
	public void setSendSample(SendSample peo) {
		this.sendSample = peo;
	}
	public SendSample getSendSample() {
		return sendSample;
	}
	public String[] getCheckid() {
		return checkid;
	}
	public void setCheckid(String[] checkid) {
		this.checkid = checkid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getRegisteid() {
		return registeid;
	}
	public void setRegisteid(String registeid) {
		this.registeid = registeid;
	}
	public String[] getInputAccording() {
		return inputAccording;
	}
	public void setInputAccording(String[] inputAccording) {
		this.inputAccording = inputAccording;
	}
	public Map<String, String> getSendCheckList() {
		return sendCheckList;
	}
	public void setSendCheckList(Map<String, String> sendCheckList) {
		this.sendCheckList = sendCheckList;
	}
	public Integer getModifyflag() {
		return modifyflag;
	}
	public void setModifyflag(Integer modifyflag) {
		this.modifyflag = modifyflag;
	}
	public List getStoreList() {
		return storeList;
	}
	public void setStoreList(List storeList) {
		this.storeList = storeList;
	}
	public String[] getOptvalue() {
		return optvalue;
	}
	public void setOptvalue(String[] optvalue) {
		this.optvalue = optvalue;
	}
	public List<SendCheckItem> getSciList() {
		return sciList;
	}
	public void setSciList(List<SendCheckItem> sciList) {
		this.sciList = sciList;
	}
	public Map<Integer, String> getInstrumentList() {
		return instrumentList;
	}
	public void setInstrumentList(Map<Integer, String> instrumentList) {
		this.instrumentList = instrumentList;
	}
	public String[] getInstrumentname() {
		return instrumentname;
	}
	public void setInstrumentname(String[] instrumentname) {
		this.instrumentname = instrumentname;
	}
	public String[] getCheckscope() {
		return checkscope;
	}
	public void setCheckscope(String[] checkscope) {
		this.checkscope = checkscope;
	}
	public String[] getUnit() {
		return unit;
	}
	public void setUnit(String[] unit) {
		this.unit = unit;
	}
	public String[] getResult() {
		return result;
	}
	public void setResult(String[] result) {
		this.result = result;
	}
	public String[] getCode() {
		return code;
	}
	public void setCode(String[] code) {
		this.code = code;
	}
	public List getSubno() {
		return subno;
	}
	public void setSubno(List subno) {
		this.subno = subno;
	}
	public int getExamineUser() {
		return examineUser;
	}
	public int getCheckuser() {
		return checkuser;
	}
	public void setCheckuser(int checkuser) {
		this.checkuser = checkuser;
	}
	public void setExamineUser(int examineUser) {
		this.examineUser = examineUser;
	}
	public List<SendCheckItem> getMysciList() {
		return mysciList;
	}
	public void setMysciList(List<SendCheckItem> mysciList) {
		this.mysciList = mysciList;
	}
	public List getMysubno() {
		return mysubno;
	}
	public void setMysubno(List mysubno) {
		this.mysubno = mysubno;
	}
}
