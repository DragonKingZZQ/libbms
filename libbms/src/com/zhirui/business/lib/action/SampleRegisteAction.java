package com.zhirui.business.lib.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhirui.business.common.Constants;
import com.zhirui.business.common.bean.User;
import com.zhirui.business.common.service.UserService;
import com.zhirui.business.lib.bean.CheckItem;
import com.zhirui.business.lib.bean.CheckStandard;
import com.zhirui.business.lib.bean.EntrustCompany;
import com.zhirui.business.lib.bean.Prepayment;
import com.zhirui.business.lib.bean.RegisteCheckItem;
import com.zhirui.business.lib.bean.RelationUser;
import com.zhirui.business.lib.bean.SampleRegiste;
import com.zhirui.business.lib.bean.Statistics;
import com.zhirui.business.lib.service.CheckItemService;
import com.zhirui.business.lib.service.CheckStandardService;
import com.zhirui.business.lib.service.EntrustCompanyService;
import com.zhirui.business.lib.service.PrepaymentService;
import com.zhirui.business.lib.service.RegisteCheckItemService;
import com.zhirui.business.lib.service.RelationUserService;
import com.zhirui.business.lib.service.SampleRegisteService;
import com.zhirui.business.utils.ApplicationUtils;
import com.zhirui.business.utils.ExportDocUtils;
import com.zhirui.business.utils.BusinessUtils;
import com.zhirui.business.utils.ExportUtils;

import cn.kepu.base.MessageType;
import cn.kepu.base.action.PageAction;
import cn.kepu.utils.ContextUtils;
import cn.kepu.utils.PageModel;
import cn.kepu.utils.StringUtils;

public class SampleRegisteAction extends PageAction<SampleRegiste> {
	private static final long serialVersionUID = -7432714922557451149L;
	@Autowired
	private SampleRegisteService sampleRegisteService;
	@Autowired
	private RelationUserService relationUserService;
	@Autowired
	private CheckItemService checkItemService;
	@Autowired
	private PrepaymentService prepaymentService;
	@Autowired
	private RegisteCheckItemService registeCheckItemService;
	@Autowired
	private EntrustCompanyService entrustCompanyService;
	@Autowired
	private UserService userService;
	@Autowired
	private CheckStandardService checkStandardSerice;   //检验标准 曾智琴
	private SampleRegiste sampleRegiste;
	private CheckStandard checkstandard;
	public Map<String,String> map;
	private Map<Integer,String> userMap;
	public Map<Integer,String> entrustcompanyList;
	public Map<String,String> qualificationmap;
	public Map<String,String> samplecategorymap;  //样品分类 曾智琴
	public Map<Integer, String> checkstandardmap;   //检测标准
	public Map<Integer,String> userList;
	public Map<Integer,String> itemList;
	private int[] ids;
	private PageModel<RegisteCheckItem> pageRegisteCheckItem;
	private String[]  inputCheckItems;
	private String[]  selectCheckItems;
	private String[]  selectExamineusers;
	private String[]  inputExamineusers;
	private String  addEntrustCompany;
	private String  selectsenduser;
	private String userid;
	private String info;
	private String cid;
	private List storeList = new ArrayList();
	private List talentList = new ArrayList();
	private List checkitemList = new ArrayList();
    //判断是否重复的主键值
	private String regid;
	private String regcode;
	
	private int pagerecorders;
	private User acceptpeople;
	String acceptpeople3;
	
	public int getPagerecorders() {
		return pagerecorders;
	}
	public void setPagerecorders(int pagerecorders) {
		this.pagerecorders = pagerecorders;
	}
	private static final Log log = LogFactory.getLog(SampleRegisteAction.class);
	
	/**
	 * 初始化 检验用户 委托单位 检验项目 资质等
	 */
	private void initData(){
		//Map<String,String> startmap = ApplicationUtils.getExamineUser();
		// 只查询出本部门的人员
		/*
		map = new LinkedHashMap<String, String>();
		int corp = BusinessUtils.getCurrentUser().getOffice();
		for (Object o : startmap.keySet()) {
			   User user = userService.getUser(Integer.parseInt((String)o));
			   if(corp==user.getOffice()){
				   String s = startmap.get(o);
				   map.put((String)o, s);
			   }
		}
		*/
		map = ApplicationUtils.getExamineUser();
		qualificationmap = ApplicationUtils.getQualification();
		samplecategorymap = ApplicationUtils.getSampleCategory();   //样品分类  曾智琴
		checkstandardmap = checkStandardSerice.getAll();  //检验标准 曾智琴
		entrustcompanyList = entrustCompanyService.getAll();  
		
		userList = relationUserService.getAll();
		itemList = checkItemService.getAll();
	}
	/**
	 * 列表功能
	 * @return
	 */
	public String list() {
		try{
		entrustcompanyList = entrustCompanyService.getAll();
		if (pagerecorders<=0){
			pagerecorders=10;
		}
		pageModel = sampleRegisteService.getSampleRegiste(sampleRegiste, pageNo, pagerecorders);
		for(SampleRegiste sr : pageModel.getList()){
		    sr.setEntrustcompanyStr(entrustcompanyList.get(Integer.parseInt(sr.getEntrustcompany())));
		    System.out.println(sr.getSamplename()+"样品名称"); 
		    System.out.println(pageModel.getList().toString()+"pageModel的值");
			String tmp="";
			PageModel<RegisteCheckItem> pagerci = registeCheckItemService.getRegisteCheckItem(sr.getId(), 1, Constants.MAX_COUNT);
			for(RegisteCheckItem it:pagerci.getList()){
				CheckItem ci = checkItemService.getCheckItem(it.getCheckitem());
				if(it.getExamineuser()!=null){
					User u = userService.getUser(it.getExamineuser());
					tmp +="检验项目："+ci.getItemname()+"，检验员："+u.getShowname()+"<br>";
				}else{
					tmp +="检验项目："+ci.getItemname()+"，检验员：未确定<br>";
				}
			}
			
			sr.setCheckitems(tmp);
			
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return LIST;
		
	}
	/**
	 * 计算样品的编码
	 * @return
	 */
    private String getSampleCode(){
    	String code = "";
    	//取得年月日
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
    	code = sdf.format(date);
    	//取得系统中当日最大的流水号
    	code = sampleRegisteService.getSerilsNo(code);
    	return code;
    }
    /**
     * 通过 ajax 方式，获取页面点击不同的单位时，得到用户、地址和联系方式
     * @return
     */
    public String getusers(){
    	if (cid==null||cid.equals("")){
    		info = "";
    		return "ajax";
    	}
    	RelationUser ru = new RelationUser();
    	ru.setEntrustcompanyid(Integer.parseInt(cid));
    	//ru.setType(1);
    	PageModel<RelationUser> pru = relationUserService.getRelationUser(ru,1,Constants.MAX_COUNT);
    	
    	if (pru==null||pru.getList().size()==0) return "ajax";
    	JSONArray jsonArray = new JSONArray();
    	Map<String, String> usermap = new HashMap<String, String>(); 
    	for(RelationUser u:pru.getList()){
    		usermap.put("userid", u.getId().toString());  
	    	usermap.put("user", u.getSenduser());  
	    	usermap.put("address", u.getAddress());  
	    	usermap.put("tel", u.getTel());  
	    	usermap.put("email", u.getEmail());  
	    	JSONObject jo = JSONObject.fromObject(usermap);
	    	jsonArray.add(jo);   
    	}
    	info = jsonArray.toString();
    	return "ajax";  
    }
    /**
     * 通过 ajax 方式，获取页面点击不同的用户时，得到地址和联系方式
     * @return
     */
    public String getUserInfo(){
    	if (userid==null||userid.equals("")){
    		info = "";
    		return "ajax";
    	}
    		
    	RelationUser ru = relationUserService.getRelationUser(Integer.parseInt(userid));
    	if (ru==null) return "ajax";
  	
    	Map<String, String> usermap = new HashMap<String, String>(); 
    	usermap.put("user", ru.getSenduser());  
    	usermap.put("address", ru.getAddress());  
    	usermap.put("tel", ru.getTel());  
    	usermap.put("email", ru.getEmail());  
    	JSONObject jo = JSONObject.fromObject(usermap);  
    	info = jo.toString();  
    	return "ajax";  
    }
    /**
     * 通过 ajax 方式，判断编号是否重复
     * @return
     */
    public String isExists(){
    	//判断后三位是不是流水号 
    	/*
    	String tmp = regcode.substring(regcode.lastIndexOf("-")+1);
    	boolean flag = false;
    	try{
    		int in= Integer.parseInt(tmp);
    	}catch(Exception e){
    		flag = true;
    	}
    	if (flag){
    		info = "{res:true}";
			return "exists";
    	} 
    	*/
    	if (regid==null||regid.equals("")){
    		SampleRegiste sr = new SampleRegiste();
    	    sr.setSampleno(regcode);
    	
	    	if (sampleRegisteService.isExists(sr)){
				info = "{res:true}";
				return "exists";
			}
    	}else{
    		SampleRegiste sr = new SampleRegiste();
    		sr.setId(Integer.parseInt(regid));
    	    sr.setSampleno(regcode);
    	
	    	if (sampleRegisteService.isExists(sr)){
				info = "{res:true}";
				return "exists";
			}
    	}
    	info = "{res:false}";
    	return "exists";  
    }
	public String add() {
		initData();
		itemList = checkItemService.getAllValid();
		//生成编号
		sampleRegiste = new SampleRegiste();
		sampleRegiste.setSampleno(getSampleCode());
		return ADD;
	}
	public String remove() {
		if(sampleRegiste == null) {
			log.warn("用户非法访问");
			ContextUtils.setOpMessage(MessageType.WARN, "用户非法访问");
			return ERROR;
		}
		if(sampleRegisteService.removeSampleRegiste(sampleRegiste.getId())) {
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据删除成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据删除失败");
		return ERROR;
	}
	public String batchremove() {
		if(sampleRegisteService.removeSampleRegistes(ids)) {
				ContextUtils.setOpMessage(MessageType.SUCCESS, "数据批量删除成功");
				return SUCCESS;
		}
		
		ContextUtils.setOpMessage(MessageType.ERROR, "数据批量删除失败");
		return SUCCESS;
	}
	public String modify() {
		initData();
		
		sampleRegiste = sampleRegisteService.getSampleRegiste(sampleRegiste.getId());
		String[] store = sampleRegiste.getStorecondition().split(",");
		for(int i=0;i<store.length;i++){
			storeList.add(store[i].trim());
		}
		
		String[] talent = sampleRegiste.getTalent().split(",");
		for(int i=0;i<talent.length;i++){
			talentList.add(talent[i].trim());
		}
		
		
		//曾智琴  得到检验标准名称
		checkstandard =  checkStandardSerice.getCheckStandard(sampleRegiste.getCheckstandardid());
		sampleRegiste.setCheckstandardname("");
		
		// 设置检验项目
/*		String checkitem = sampleRegiste.getCheckitem();
		checkitem = checkitem.substring(1, checkitem.length()-1);
		String[] ins = checkitem.split("\\|");
		Map<Integer,String> checkitemMap = new HashMap<Integer,String>();
		for(int i=0;i<ins.length;i++){
			if (ins[i]!=null&&ins[i]!=""){
			   checkitemMap.put(Integer.parseInt(ins[i]),itemList.get(Integer.parseInt(ins[i])));
			}
		}
		sampleRegiste.setCheckitemMap(checkitemMap);
*/
		pageRegisteCheckItem = registeCheckItemService.getRegisteCheckItem(sampleRegiste.getId(), 1, Constants.MAX_COUNT);
		String corp = entrustCompanyService.getAll().get(Integer.parseInt(sampleRegiste.getEntrustcompany()));
		sampleRegiste.setEntrustcompanyStr(corp);
		//设置联系人的id
//		 for (int key : userList.keySet()) {
//			if (userList.get(key).equals(sampleRegiste.getSenduser())){
//			     sampleRegiste.setSenduserid(key);
//			     sampleRegiste.setSenduser(sampleRegiste.getSenduser());
//			     sampleRegiste.setSenduser(String.valueOf(key));
//			     break;
//		    }
//	    }
		return "modify";
	}
	public String save() {
		//再次检查是否有同一编号存在
		SampleRegiste sr = new SampleRegiste();
	    sr.setSampleno(sampleRegiste.getSampleno());
	
    	if (sampleRegisteService.isExists(sr)){
    		ContextUtils.setOpMessage(MessageType.INFO, "编号已经存在！请重新添加。");
			return SUCCESS;
		}
    	
		if (!saveRelationData().equals("")) return SUCCESS;
		sampleRegiste.setCheckstandardid(1);
		if((sampleRegiste=sampleRegisteService.addSampleRegiste(sampleRegiste)) != null) {
			//保存检验项目及检验人员
			if (selectCheckItems!=null){
				int i = 0;
				for(String ckitem :selectCheckItems){
					RegisteCheckItem rci = new RegisteCheckItem();
					rci.setSampleregisteid(sampleRegiste.getId());
					rci.setCheckitem(Integer.parseInt(ckitem));
					//rci.setExamineuser(Integer.parseInt(selectExamineusers[i]));
					if (!StringUtils.isEmpty(selectExamineusers[i])){
					    rci.setExamineuser(Integer.parseInt(selectExamineusers[i]));
					}else{
						rci.setExamineuser(null);
					}
					registeCheckItemService.addRegisteCheckItem(rci);
					i++;
				}
			}
			if (inputCheckItems!=null){
				int i = 0;
				for(String ckitem :inputCheckItems){
					RegisteCheckItem rci = new RegisteCheckItem();
					rci.setSampleregisteid(sampleRegiste.getId());
					//rci.setCheckitem(Integer.parseInt(ckitem));
					rci.setCheckitem((Integer)checkitemList.get(i));
					if (!StringUtils.isEmpty(inputExamineusers[i])){
					    rci.setExamineuser(Integer.parseInt(inputExamineusers[i]));
					}else{
						rci.setExamineuser(null);
					}
					registeCheckItemService.addRegisteCheckItem(rci);
					i++;
				}
			}
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据添加成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据添加失败");
		return SUCCESS;
	}
	public String update() {
		if (!saveRelationData().equals("")) return SUCCESS;
		sampleRegiste.setCheckstandardid(1);
		if(sampleRegisteService.updateSampleRegiste(sampleRegiste) != null) {
			//保存检验项目及检验人员
			PageModel<RegisteCheckItem> tmp = registeCheckItemService.getRegisteCheckItem(sampleRegiste.getId(), 1, Constants.MAX_COUNT);
			for(RegisteCheckItem ck : tmp.getList()){
				registeCheckItemService.removeRegisteCheckItem(ck.getId());
			}
			
			if (selectCheckItems!=null){
				int i = 0;
				for(String ckitem :selectCheckItems){
					RegisteCheckItem rci = new RegisteCheckItem();
					rci.setSampleregisteid(sampleRegiste.getId());
					rci.setCheckitem(Integer.parseInt(ckitem));
					//rci.setExamineuser(Integer.parseInt(selectExamineusers[i]));
					if (!StringUtils.isEmpty(selectExamineusers[i])){
					    rci.setExamineuser(Integer.parseInt(selectExamineusers[i]));
					}else{
						rci.setExamineuser(null);
					}
					registeCheckItemService.addRegisteCheckItem(rci);
					i++;
				}
			}
			if (inputCheckItems!=null){
				int i = 0;
				for(String ckitem :inputCheckItems){
					RegisteCheckItem rci = new RegisteCheckItem();
					rci.setSampleregisteid(sampleRegiste.getId());
					//rci.setCheckitem(Integer.parseInt(ckitem));
					rci.setCheckitem((Integer)checkitemList.get(i));
					//rci.setExamineuser(Integer.parseInt(inputExamineusers[i]));
					if (!StringUtils.isEmpty(inputExamineusers[i])){
					    rci.setExamineuser(Integer.parseInt(inputExamineusers[i]));
					}else{
						rci.setExamineuser(null);
					}
					registeCheckItemService.addRegisteCheckItem(rci);
					i++;
				}
			}
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据更新成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据更新失败");
		return ERROR;
	}
	public String detail() {
		if(sampleRegiste == null) {
			log.warn("用户非法访问");
			ContextUtils.setOpMessage(MessageType.WARN, "用户非法访问");
			return ERROR;
		}
		sampleRegiste = sampleRegisteService.getSampleRegiste(sampleRegiste.getId());     //张嘉星
		int acceptpeopel2 = Integer.parseInt(sampleRegiste.getAcceptpeople());
		acceptpeople = userService.getUser(acceptpeopel2);
		acceptpeople3 = acceptpeople.getShowname();
		request.setAttribute("acceptpeople3",acceptpeople3);
		int checkstandard = sampleRegiste.getCheckstandardid(); //获取id
		CheckStandard checkstandard2= checkStandardSerice.getCheckStandard(checkstandard);//获取对象
		//String checkstandard3 = checkstandard2.getStandardcode();
		
		int category = sampleRegiste.getSamplecategoryid();
		String categoryString = ApplicationUtils.getSampleCategory().get(String.valueOf(category));
		request.setAttribute("checkstandard3","");
		request.setAttribute("categoryString",categoryString);
		if (sampleRegiste.getExamineuser()!=null){
			String examuser = userService.getUser(sampleRegiste.getExamineuser()).getShowname();
			sampleRegiste.setExamineuserStr(examuser);
	    }
		String tal=ApplicationUtils.getQualification().get(String.valueOf(sampleRegiste.getTalent()));
		sampleRegiste.setTalentStr(tal);
		
		String corp = entrustCompanyService.getAll().get(Integer.parseInt(sampleRegiste.getEntrustcompany()));
		sampleRegiste.setEntrustcompanyStr(corp);
		itemList = checkItemService.getAll();
		/*
		String checkitem = sampleRegiste.getCheckitem();
		checkitem = checkitem.substring(1, checkitem.length()-1);
		String[] ins = checkitem.split("\\|");
		Map<Integer,String> checkitemMap = new HashMap<Integer,String>();
		for(int i=0;i<ins.length;i++){
			if (ins[i]!=null&&ins[i]!=""){
				   checkitemMap.put(Integer.parseInt(ins[i]),itemList.get(i));
			}
		}
		sampleRegiste.setCheckitemMap(checkitemMap);
		*/
		pageRegisteCheckItem = registeCheckItemService.getRegisteCheckItem(sampleRegiste.getId(), 1, Constants.MAX_COUNT);
		for(RegisteCheckItem rci : pageRegisteCheckItem.getList()){
			CheckItem ci = checkItemService.getCheckItem(rci.getCheckitem());
			rci.setCheckitemname(ci.getItemname());
			if (!StringUtils.isEmpty(rci.getExamineuser())){
			    User user = userService.getUser(rci.getExamineuser());
			    rci.setExamineusername(user.getShowname());
			}else{
				rci.setExamineusername("");
			}
		}
		if(sampleRegiste == null) {
			ContextUtils.setOpMessage(MessageType.WARN, "用户访问的数据不存在");
			return ERROR;
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
		if(sampleRegiste == null) {
			log.warn("用户非法访问");
			ContextUtils.setOpMessage(MessageType.VALIDATE_FAILED, "用户非法访问");
			return ;
		}
	}
   
   private String saveRelationData(){	
		// 查询样品编号是否有重复的
//		if (sampleRegisteService.isExists(sampleRegiste)){
//			ContextUtils.setOpMessage(MessageType.ERROR, "样品编号已经存在，请重新修改！");
//			return SUCCESS;
//		}
	    boolean tmpAddUser = false;
		boolean tmpAddCompany = false;
//		String items = "";
		User user = BusinessUtils.getCurrentUser();
		//委托单位标识
		int ecid = -1;
		//保存新增的委托单位
		if (sampleRegiste.getEntrustcompanyid()==null||sampleRegiste.getEntrustcompanyid().equals("")){
			tmpAddCompany = true;
			//如果已经存在了，就不再保存
			EntrustCompany ec=entrustCompanyService.getEntrustCompany(sampleRegiste.getEntrustcompany());
			if (ec==null){
				ec = new EntrustCompany();
				ec.setEntrustcompany(sampleRegiste.getEntrustcompany());
				ec.setCreateuser(user.getUid());
				ec.setCreatedate(new Date());
				
				if ((ec=entrustCompanyService.addEntrustCompany(ec))==null){
					ContextUtils.setOpMessage(MessageType.ERROR, "添加输入的委托单位失败");
					return "-1";
				}
				ecid = ec.getId();
			}
			//sampleRegiste.setEntrustcompany(addEntrustCompany);
			sampleRegiste.setEntrustcompany(String.valueOf(ec.getId()));
		}
        if (ecid==-1){
        	ecid = sampleRegiste.getEntrustcompanyid();
        }
		//保存新增的联系人
		//if (sampleRegiste.getAddrelationuser().equals("1")){
		if (sampleRegiste.getSenduserid()==null||sampleRegiste.getSenduserid().equals("")){
			tmpAddUser = true;
			RelationUser ru = new RelationUser();
			ru.setSenduser(sampleRegiste.getSenduser());
			ru.setAddress(sampleRegiste.getAddress());
			ru.setTel(sampleRegiste.getRelation());
			ru.setEmail(sampleRegiste.getEmail());
			ru.setType(1);
			ru.setBalance(0f);
			ru.setCreateuser(user.getUid());
			ru.setCreatedate(new Date());
			ru.setEntrustcompanyid(ecid);
			if ((ru=relationUserService.addRelationUser(ru))==null){
				ContextUtils.setOpMessage(MessageType.ERROR, "添加输入的联系人失败");
				return "-2";
			}
			sampleRegiste.setSenduserid(ru.getId());
			sampleRegiste.setSenduser(sampleRegiste.getSenduser());
		}else{
			// 如果有修改的话，直接修改
			RelationUser ru = relationUserService.getRelationUser(sampleRegiste.getSenduserid());
			if (ru!=null){
				ru.setAddress(sampleRegiste.getAddress());
				ru.setTel(sampleRegiste.getRelation());
				ru.setEmail(sampleRegiste.getEmail());
				if ((ru=relationUserService.updateRelationUser(ru))==null){
					ContextUtils.setOpMessage(MessageType.ERROR, "修改联系人信息失败");
					return "-3";
				}
			}
		}
		//保存新增的检验项目
		if (inputCheckItems!=null){
			for(String item :inputCheckItems){
				CheckItem ck = new CheckItem();
				ck.setItemname(item);
				ck.setCreateuser(user.getUid());
				ck.setCreatedate(new Date());
				ck.setValidflag(1);
				//items += "|"+item;
				//增加校验，看数据库中是否已经存在
				int tmpid = checkItemService.isExists(-1,item);
				if (tmpid==-1){
					if ((ck=checkItemService.addCheckItem(ck))!=null){
						checkitemList.add(ck.getId());
						//记录下新增的检验项目，为后续添加到注册表中用
					}else{
						ContextUtils.setOpMessage(MessageType.ERROR, "添加输入的检验项目失败");
						return "-4";
					}
//					items +="|"+ck.getId();
				}else{
					checkitemList.add(tmpid);
				}
			}
		}
		
		/* -------------- 准备保存登记主表 --------------*/
		//查询到选择的检验项目
//		if (selectCheckItems!=null){
//			for(String ckitem :selectCheckItems){
//				//items +="|" + checkItemService.getCheckItem(Integer.parseInt(ckitem)).getItemname();
//				items += "|"+ckitem;
//			}
//		}
//		items += "|";
		//查询选择的联系人
		if (!tmpAddUser){
			RelationUser ru = relationUserService.getRelationUser(sampleRegiste.getSenduserid());
			sampleRegiste.setSenduser(ru.getSenduser());
			sampleRegiste.setAddress(ru.getAddress());
			sampleRegiste.setRelation(ru.getTel());
		}
		//查询选择的委托单位
		if (!tmpAddCompany){
			//EntrustCompany ec = entrustCompanyService.getEntrustCompany(Integer.parseInt(selectsenduser));
			//sampleRegiste.setEntrustcompany(ec.getEntrustcompany());
			//sampleRegiste.setEntrustcompany(String.valueOf(ec.getId()));
			sampleRegiste.setEntrustcompany(String.valueOf(sampleRegiste.getEntrustcompanyid()));
		}
		//添加默认值
		//11.26 判断检测费用的增减情况
		if (sampleRegiste.getId()==null){
			sampleRegiste.setPrepaymoney(0f);
			sampleRegiste.setBalancemoney(sampleRegiste.getCheckmoney());
		}else{
			SampleRegiste sr = sampleRegisteService.getSampleRegiste(sampleRegiste.getId());
			if (sr!=null){
			    sampleRegiste.setPrepaymoney(sr.getPrepaymoney());
			    sampleRegiste.setBalancemoney(sampleRegiste.getCheckmoney()-sr.getPrepaymoney());
			}else{
				sampleRegiste.setPrepaymoney(0f);
				sampleRegiste.setBalancemoney(sampleRegiste.getCheckmoney());
			}
		}
		sampleRegiste.setTaskdays(0);
		sampleRegiste.setCreateuser(user.getUid());
		sampleRegiste.setCreatedate(new Date());
		sampleRegiste.setStatus("A");
        //根据原状态设置，判断是否是修改
		if(sampleRegiste.getId()!=null){
			SampleRegiste newsr = sampleRegisteService.getSampleRegiste(sampleRegiste.getId());
			String status = newsr.getStatus();
			sampleRegiste.setStatus(status);
		}
		//		sampleRegiste.setCheckitem(items);
		//		sampleRegiste.setBalancemoney(sampleRegiste.getPrepaymoney()-sampleRegiste.getCheckmoney());
		return "";
	}
   /**
	 * 点击完成派样 功能
	 * @return
	 */
	public String sendOK() {
		if(sampleRegiste == null) {
			ContextUtils.setOpMessage(MessageType.WARN, "用户非法访问");
			return SUCCESS;
		}
		sampleRegiste = sampleRegisteService.getSampleRegiste(sampleRegiste.getId());
		sampleRegiste.setStatus(Constants.STATUS_FINISHEDSAMPLE);
		if (!sampleRegisteService.setStatus(sampleRegiste)){
			ContextUtils.setOpMessage(MessageType.ERROR, "提交失败");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.SUCCESS, "提交成功");
		return SUCCESS;
	}
	/**
	 * 提交审核
	 * @return
	 */
	public String submit() {
		if(sampleRegiste == null) {
			ContextUtils.setOpMessage(MessageType.WARN, "用户非法访问");
			return SUCCESS;
		}
		sampleRegiste = sampleRegisteService.getSampleRegiste(sampleRegiste.getId());
		sampleRegiste.setStatus(Constants.STATUS_REGISTEVERIFY);
		if (!sampleRegisteService.setStatus(sampleRegiste)){
			ContextUtils.setOpMessage(MessageType.ERROR, "提交失败");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.SUCCESS, "提交成功");
		return SUCCESS;
	}
	public String batchsubmit() {
        for(int id:ids){
        	sampleRegiste = sampleRegisteService.getSampleRegiste(id);
        	sampleRegiste.setStatus(Constants.STATUS_REGISTEVERIFY);
        	if (!sampleRegisteService.setStatus(sampleRegiste)){
    			ContextUtils.setOpMessage(MessageType.ERROR, "提交失败");
    			return SUCCESS;
    		}
        }
        ContextUtils.setOpMessage(MessageType.SUCCESS, "提交成功");
        return SUCCESS;
	}
	//完成登记单的所以任务
	public String finish(){
		sampleRegiste = sampleRegisteService.getSampleRegiste(sampleRegiste.getId());
		sampleRegiste.setFinishdate(new Date());
		sampleRegiste.setStatus(Constants.STATUS_FINISH);
		sampleRegiste.setStatuschangedate(new Date());
		sampleRegiste.setOvertimeflag(0);
		if(sampleRegisteService.updateSampleRegiste(sampleRegiste) != null) {
			ContextUtils.setOpMessage(MessageType.SUCCESS, "数据更新成功");
			return SUCCESS;
		}
		ContextUtils.setOpMessage(MessageType.ERROR, "数据更新失败");
		return LIST;
	}
	// 导出word
	public String export() throws IOException{
		ExportDocUtils edu = new ExportDocUtils();
		String name = "";
		if (sampleRegiste!=null)
		    name = sampleRegiste.getSampleno()+sampleRegiste.getSamplename();
		edu.exportWord(name,Constants.TEMPLATE_SAMPLE,null,request,response);
		return null;
	}
	// 导出excel
	public String excelExport() throws IOException{
		ExportUtils edu = new ExportUtils();
        String[] fieldname = {"样品编号","样品名称","样品数量","收样日期","受理人","委托方","委托人","地址","联系方式","邮箱","检测项目","检测费用","已付款","报告","检验员","备注","派样时间"};
		String[] columnname = {"sampleno","samplename","samplecount","receivedate","receiveuser","entrustcompanyStr","senduser","address","relation","email","checkitems","checkmoney","prepay","","checkuser","",""};
		PageModel<SampleRegiste> pageModel=sampleRegisteService.getSampleRegiste(sampleRegiste, 1, Constants.MAX_COUNT);
		for(SampleRegiste sr:pageModel.getList()){
			User user = userService.getUser(sr.getCreateuser());
			sr.setReceiveuser(user.getShowname());
			String tmp="",items = "",users="";
			PageModel<RegisteCheckItem> pagerci = registeCheckItemService.getRegisteCheckItem(sr.getId(), 1, Constants.MAX_COUNT);
			for(RegisteCheckItem it:pagerci.getList()){
				CheckItem ci = checkItemService.getCheckItem(it.getCheckitem());
				items += ci.getItemname()+"|";
				if(it.getExamineuser()!=null){
					User u = userService.getUser(it.getExamineuser());
					users += u.getShowname()+"|";
					tmp +="检验项目："+ci.getItemname()+"，检验员："+u.getShowname()+"|";
				}else{
					tmp +="检验项目："+ci.getItemname()+"，检验员：未确定|";
				}
			}
			
			sr.setCheckitems(items);
			sr.setCheckuser(users);
			
			EntrustCompany ec = entrustCompanyService.getEntrustCompany(Integer.parseInt(sr.getEntrustcompany()));
			sr.setEntrustcompanyStr(ec.getEntrustcompany());
		}
        edu.exportExcel2("样品登记单",fieldname,columnname,pageModel,request,response);
		return null;
	}
	//调用结算页面
	public String cash(){
		sampleRegiste = sampleRegisteService.getSampleRegiste(sampleRegiste.getId());
        EntrustCompany ec = entrustCompanyService.getEntrustCompany(Integer.parseInt(sampleRegiste.getEntrustcompany()));
		if (ec!=null)
           sampleRegiste.setEntrustcompanyStr(ec.getEntrustcompany());
		//获取单位预付款余额
		EntrustCompany entrustCompany = entrustCompanyService.getEntrustCompany(ec.getId());
		float payedmoney = entrustCompany.getPrepaymoney();

        //获取联系人
		getUsers();
		if (payedmoney==0){
			sampleRegiste.setPayedmoney(0f);
		}else{
			sampleRegiste.setPayedmoney(payedmoney);
		}
		return "cash";
	}
	//11.26 得到预付款联系人
	private String getUsers(){
        RelationUser ru = new RelationUser();
        ru.setEntrustcompanyid(Integer.parseInt(sampleRegiste.getEntrustcompany()));
        //ru.setType(2);
        userMap = new HashMap<Integer,String>();
        PageModel<RelationUser> pru = relationUserService.getRelationUser(ru,1, 9999);
        //得到选择的预付款人
        if (pru!=null&&pru.getList().size()>0){
             for(RelationUser u:pru.getList()){
            	 userMap.put(u.getId(), u.getSenduser());
             }
        }
		return SUCCESS;
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
	public void setSampleRegiste(SampleRegiste peo) {
		this.sampleRegiste = peo;
	}
	public SampleRegiste getSampleRegiste() {
		return sampleRegiste;
	}
	public String[] getInputCheckItems() {
		return inputCheckItems;
	}
	public void setInputCheckItems(String[] inputCheckItems) {
		this.inputCheckItems = inputCheckItems;
	}
	public String[] getSelectCheckItems() {
		return selectCheckItems;
	}
	public void setSelectCheckItems(String[] selectCheckItems) {
		this.selectCheckItems = selectCheckItems;
	}
	public String getAddEntrustCompany() {
		return addEntrustCompany;
	}
	public void setAddEntrustCompany(String addEntrustCompany) {
		this.addEntrustCompany = addEntrustCompany;
	}
	public String getSelectsenduser() {
		return selectsenduser;
	}
	public void setSelectsenduser(String selectsenduser) {
		this.selectsenduser = selectsenduser;
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
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getRegcode() {
		return regcode;
	}
	public void setRegcode(String regcode) {
		this.regcode = regcode;
	}
	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	public String[] getSelectExamineusers() {
		return selectExamineusers;
	}
	public void setSelectExamineusers(String[] selectExamineusers) {
		this.selectExamineusers = selectExamineusers;
	}
	public PageModel<RegisteCheckItem> getPageRegisteCheckItem() {
		return pageRegisteCheckItem;
	}
	public void setPageRegisteCheckItem(
			PageModel<RegisteCheckItem> pageRegisteCheckItem) {
		this.pageRegisteCheckItem = pageRegisteCheckItem;
	}
	public String[] getInputExamineusers() {
		return inputExamineusers;
	}
	public void setInputExamineusers(String[] inputExamineusers) {
		this.inputExamineusers = inputExamineusers;
	}
	public List getStoreList() {
		return storeList;
	}
	public void setStoreList(List storeList) {
		this.storeList = storeList;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public List getTalentList() {
		return talentList;
	}
	public void setTalentList(List talentList) {
		this.talentList = talentList;
	}
	public Map<Integer, String> getUserMap() {
		return userMap;
	}
	public void setUserMap(Map<Integer, String> userMap) {
		this.userMap = userMap;
	}
}
