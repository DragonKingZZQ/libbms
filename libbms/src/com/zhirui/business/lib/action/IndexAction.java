package com.zhirui.business.lib.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhirui.business.common.Constants;
import com.zhirui.business.common.bean.User;
import com.zhirui.business.lib.bean.CheckReport;
import com.zhirui.business.lib.bean.Groups;
import com.zhirui.business.lib.bean.MenuFunction;
import com.zhirui.business.lib.bean.RegisteCheckItem;
import com.zhirui.business.lib.bean.SampleRegiste;
import com.zhirui.business.lib.bean.SendCheckItem;
import com.zhirui.business.lib.bean.SendSample;
import com.zhirui.business.lib.service.CheckReportService;
import com.zhirui.business.lib.service.EntrustCompanyService;
import com.zhirui.business.lib.service.GroupsService;
import com.zhirui.business.lib.service.MenuFunctionService;
import com.zhirui.business.lib.service.RegisteCheckItemService;
import com.zhirui.business.lib.service.SampleRegisteService;
import com.zhirui.business.lib.service.SendCheckItemService;
import com.zhirui.business.lib.service.SendSampleService;
import com.zhirui.business.utils.BusinessUtils;

import cn.kepu.base.action.PageAction;
import cn.kepu.base.annotation.Authority;
import cn.kepu.utils.PageModel;

/**
 * 首页
 * @author 
 */
@SuppressWarnings("unchecked")
public class IndexAction extends PageAction {

	private static final long serialVersionUID = -8089052635271711058L;
	@Autowired
	private GroupsService groupsService;
	@Autowired
	private MenuFunctionService menuFunctionService;
	@Autowired
	private SendSampleService sendSampleService;
	@Autowired
	private SendCheckItemService sendCheckItemService;
	@Autowired
	private RegisteCheckItemService registeCheckItemService;
	@Autowired
	private EntrustCompanyService entrustCompanyService;
	@Autowired
	private CheckReportService checkReportService;
	@Autowired
	private SampleRegisteService sampleRegisteService;
	PageModel<MenuFunction> menu;
	List<MenuFunction>  topmenu = new ArrayList<MenuFunction>();
	List<MenuFunction>  submenu = new ArrayList<MenuFunction>();
	private User user;
	@Autowired
	private com.zhirui.business.common.service.UserService userService;
	
	private PageModel<SendSample> warnInfo;
	private PageModel<SendSample> warnInfoForLeader;
	private PageModel<SendSample> waitTask;
	private PageModel<SampleRegiste> sendTask;
	private PageModel<SampleRegiste> nopayTask;
	private PageModel<CheckReport> finishReport;
	//提示已经检测完成的数据
	private  String finishedcheckNo = "";
	
	public String index() {
		//获取菜单权限
		user = BusinessUtils.getCurrentUser();
		int group = user.getGroupid();
		Groups groups = groupsService.getGroups(group);
		
		String rights = groups.getPrivillage();
		
		menu = menuFunctionService.getMenuFunction(null, 1, Constants.MAX_COUNT);
		
		int i =0;
		int len = menu.getList().size();
		while(i<len){
			// 去掉无权限的
			if (rights.indexOf("|"+menu.getList().get(i).getCode()+"|")<0){
				   i++;
				   continue;
			}
			//去掉子菜单
			if (menu.getList().get(i).getCode().indexOf("-")>=0){
				if (!menu.getList().get(i).getCode().substring(menu.getList().get(i).getCode().indexOf("-")+1).equals("0")){
				   //增加子菜单
				   submenu.add(menu.getList().get(i));
				}else{
					topmenu.add(menu.getList().get(i));
				}
			}else{
			    topmenu.add(menu.getList().get(i));
			}
			i++;
		}
		warnInfo = this.getWarnTask();
		warnInfoForLeader = this.getWarnTaskForLeader();
		return INDEX;
	}
	
	public String showinfo(){
		user = BusinessUtils.getCurrentUser();
		waitTask = this.getMySendSample();
		warnInfo = this.getWarnTask();
		finishReport = this.getMyCheckReport();
		sendTask = this.getNoFinishTask();
		nopayTask = this.getNewNoPayTask();
		// 显示检验完成的提示
        this.getCheckFinished();
		return "showinfo";
	}
	
	private String getCheckFinished(){
		//判断登记单的检验项目是否完成
		//找出所有状态是检验完成的登记单（有多个派样单会有多个检验，状态不能准备表示检验都已经完成，只表示有完成的 ）
		SampleRegiste sr = new SampleRegiste();
		sr.setStatus(Constants.STATUS_FINISHCHECK);
		PageModel<SampleRegiste> psr = sampleRegisteService.getSampleRegiste(sr, 1, Constants.MAX_COUNT);
		int count = 0;
		//List<String> list = new ArrayList<String>();
		boolean flag = false;
		for(SampleRegiste r : psr.getList()){
			//得到具体的检验项目
			PageModel<RegisteCheckItem> prci = registeCheckItemService.getRegisteCheckItem(r.getId(), 1, Constants.MAX_COUNT);
			//查询检验完成的派样单信息，是否包含了全部的检验项目
			SendSample ss = new SendSample();
			ss.setStatus(Constants.STATUS_FINISHCHECK);
			PageModel<SendSample> pss = sendSampleService.getSendSample(ss, 1, Constants.MAX_COUNT);
			
			//遍历是否检验完成
			for(RegisteCheckItem rci:prci.getList()){
				flag = false;
				for(SendSample s : pss.getList()){
				     PageModel<SendCheckItem> psci = sendCheckItemService.getSendCheckItem(s.getId(), 1, Constants.MAX_COUNT);
				     
				     //是否登记单中的检验项目在派样单检验项目中都存在
				     for(SendCheckItem sci:psci.getList()){
				    	  if (String.valueOf(rci.getCheckitem()).equals(sci.getCheckitem())){
				    		   flag = true;
				    		   break;
				    	  }
				     }
				     if (flag) break;
				}
				//有检验项目没有完成的情况
				if (!flag){
					break;
				}
			}
			//如果都检验完成,记录本登记单
			if (flag){
				//list.add(r.getSampleno());
				finishedcheckNo += r.getSampleno();
				count ++;
				
				//如果超过5个，就不在取了，提示就可以了
				if (count>=5) break;
			}
		}
		return finishedcheckNo;
	}
	/**检验员得到为检验完成的任务的提示（提前2天）*/
	public PageModel<SendSample> getWarnTask(){
		warnInfo = sendSampleService.getWarnSendSample(pageNo, pageSize);
		return warnInfo;
	}
	/**组长得到为检验完成的任务的提示（提前1天）*/
	public PageModel<SendSample> getWarnTaskForLeader(){
		warnInfoForLeader = sendSampleService.getWarnSendSampleForLeader(pageNo, pageSize);
		return warnInfoForLeader;
	}
    /**不同用户登录得到不同的任务
     * 检验员得到派样任务
     */
	public PageModel<SendSample> getMySendSample(){
		waitTask = sendSampleService.getMySendSample(pageNo, pageSize);
		return waitTask;
	}
	
	 /**不同用户登录得到不同的任务
     * 组长得到检验报告
     */
	public PageModel<CheckReport> getMyCheckReport(){
		finishReport = checkReportService.getMyCheckReport(pageNo, pageSize);
		return finishReport;
	}
	
	/**不同用户登录得到不同的任务
     * 组长得到到期未完成的检验任务
     * 只能以登记单位准，不能以派样单或检验报告为准，因为可以多次派样
     */
	public PageModel<SampleRegiste> getNoFinishTask(){
		sendTask = sampleRegisteService.getNoFinishTask(pageNo, pageSize);
		for(SampleRegiste sr : sendTask.getList()){
			 String tmp = entrustCompanyService.getEntrustCompany(Integer.parseInt(sr.getEntrustcompany())).getEntrustcompany();
			 sr.setEntrustcompanyStr(tmp);
		}
		return sendTask;
	}
	
	/**得到已经登记确认，但是没有付款的样品登记
     */
	public PageModel<SampleRegiste> getNewNoPayTask(){
		nopayTask = sampleRegisteService.getNewNoPayTask(pageNo, pageSize);
		for(SampleRegiste sr : nopayTask.getList()){
			 String tmp = entrustCompanyService.getEntrustCompany(Integer.parseInt(sr.getEntrustcompany())).getEntrustcompany();
			 sr.setEntrustcompanyStr(tmp);
		}
		return nopayTask;
	}
	//点击我知道了
	public void gotit(){
		 //判断用户类型 
		if (BusinessUtils.getCurrentUser().getAuthority().indexOf(Constants.RIGHT_MEMBER)>=0){
			warnInfo = sendSampleService.getWarnSendSample(pageNo, pageSize);
			for(SendSample ss : warnInfo.getList()){
				// 修改为接收者是当前人
				if (ss.getExamineuser()==BusinessUtils.getCurrentUser().getUid()){
				   ss.setReceivewarnflag("1");
				   sendSampleService.updateSendSample(ss);
				}
			}
		}else{
			warnInfoForLeader = sendSampleService.getWarnSendSampleForLeader(pageNo, pageSize);
			for(SendSample ss : warnInfoForLeader.getList()){
				// 修改为接收者是当前人
				if (ss.getExamineuser()==BusinessUtils.getCurrentUser().getUid()){
				   ss.setReceivewarnflag("1");
				   sendSampleService.updateSendSample(ss);
				}
			}
		}
	}
	@Authority
	public String changeinfo() {
		user = userService.getUser(BusinessUtils.getCurrentUser().getUid());
		return "change_info";
	}
	
	public PageModel<MenuFunction> getMenu() {
		return menu;
	}

	public void setMenu(PageModel<MenuFunction> menu) {
		this.menu = menu;
	}

	public List<MenuFunction> getSubmenu() {
		return submenu;
	}

	public void setSubmenu(List<MenuFunction> submenu) {
		this.submenu = submenu;
	}

	public List<MenuFunction> getTopmenu() {
		return topmenu;
	}

	public void setTopmenu(List<MenuFunction> topmenu) {
		this.topmenu = topmenu;
	}
	public PageModel<SendSample> getWarnInfo() {
		return warnInfo;
	}
	public void setWarnInfo(PageModel<SendSample> warnInfo) {
		this.warnInfo = warnInfo;
	}
	public PageModel<SendSample> getWaitTask() {
		return waitTask;
	}
	public void setWaitTask(PageModel<SendSample> waitTask) {
		this.waitTask = waitTask;
	}
	public PageModel<SampleRegiste> getSendTask() {
		return sendTask;
	}
	public void setSendTask(PageModel<SampleRegiste> sendTask) {
		this.sendTask = sendTask;
	}
	public PageModel<CheckReport> getFinishReport() {
		return finishReport;
	}
	public void setFinishReport(PageModel<CheckReport> finishReport) {
		this.finishReport = finishReport;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PageModel<SampleRegiste> getNopayTask() {
		return nopayTask;
	}

	public void setNopayTask(PageModel<SampleRegiste> nopayTask) {
		this.nopayTask = nopayTask;
	}

	public String getFinishedcheckNo() {
		return finishedcheckNo;
	}

	public void setFinishedcheckNo(String finishedcheckNo) {
		this.finishedcheckNo = finishedcheckNo;
	}

}
