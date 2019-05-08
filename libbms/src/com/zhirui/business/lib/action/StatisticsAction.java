package com.zhirui.business.lib.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhirui.business.common.Constants;
import com.zhirui.business.lib.bean.CheckReport;
import com.zhirui.business.lib.bean.EntrustCompany;
import com.zhirui.business.lib.bean.Prepayment;
import com.zhirui.business.lib.bean.SampleRegiste;
import com.zhirui.business.lib.bean.Statistics;
import com.zhirui.business.lib.bean.SubCheckReport;
import com.zhirui.business.lib.service.EntrustCompanyService;
import com.zhirui.business.lib.service.PrepaymentService;
import com.zhirui.business.lib.service.SampleRegisteService;
import com.zhirui.business.lib.service.StatisticsService;
import com.zhirui.business.utils.ApplicationUtils;
import com.zhirui.business.utils.ExportUtils;

import cn.kepu.base.action.PageAction;
import cn.kepu.utils.PageModel;

@SuppressWarnings("serial")
public class StatisticsAction extends PageAction<Statistics> {

	@Autowired
	private StatisticsService statisticsService;
	@Autowired
	private EntrustCompanyService entrustCompanyService;
	@Autowired
	private SampleRegisteService sampleRegisteService;
	@Autowired
	private PrepaymentService prepaymentService;
	private CheckReport checkReport;
	private Statistics statistics;
	public Map<Integer,String> entrustcompanyList;
	public Map<Integer,String> itemList;
	public Map<String,String> qualificationmap;
	private String[]  selectCheckItems;
	public List<SubCheckReport> checkResultList;
	private List<SampleRegiste> psr;
	public Map<String,String> map;
	//取不同的登记单信息
	private String registeid;
	
	private String[]  checkid;
	private String[]  checkstandard;
	private String[]  instrumentname;
	private String[]  checkresult;

	/**
	 * 登记统计列表功能
	 * @return
	 */
	public String listregiste() {
		qualificationmap = ApplicationUtils.getQualification();
		entrustcompanyList = entrustCompanyService.getAll();
		pageModel = statisticsService.getRegisteStatistics(statistics, pageNo, pageSize);
		return LIST;
	}
	/**
	 * 按单位统计收付款情况功能
	 * @return
	 */
	public String listpay() {
				
		PageModel<SampleRegiste> srs = sampleRegisteService.getNoPayOff(statistics, 1, Constants.MAX_COUNT);
		psr = new ArrayList<SampleRegiste>();
		int entrustid = -1;
		float summoney = 0;
		float payedmoney = 0;
		float paymoney = 0;
		float nopaymoney = 0;
		int i = 0;
		String cname ="";
		for(SampleRegiste sr:srs.getList()){
			if (i==0){
				entrustid=Integer.parseInt(sr.getEntrustcompany());
				cname = entrustCompanyService.getEntrustCompany(Integer.parseInt(sr.getEntrustcompany())).getEntrustcompany();
			}
			
			if (entrustid==Integer.parseInt(sr.getEntrustcompany())){
				summoney += sr.getCheckmoney();
				paymoney += sr.getPrepaymoney();
				nopaymoney += sr.getCheckmoney()-sr.getPrepaymoney();
				
				i++;
				continue;
			}
			SampleRegiste temp = new SampleRegiste();
			//得到预付余款
			Prepayment p = new Prepayment();
			p.setId(entrustid);
			PageModel<Prepayment> pmpp = prepaymentService.getPrepayment(p, 1, Constants.MAX_COUNT);
			for(Prepayment pp :pmpp.getList()){
				payedmoney += pp.getPrepaymoney();
			}
			temp.setEntrustcompanyStr(cname);
			temp.setPayedmoney(payedmoney);
			temp.setCheckmoney(summoney);
			temp.setPrepaymoney(paymoney);
			temp.setBalancemoney(nopaymoney);
						
			//得到预付款余额
			EntrustCompany entrustCompany = entrustCompanyService.getEntrustCompany(entrustid);
			temp.setNotusedmoney(entrustCompany.getPrepaymoney());
			psr.add(temp);
			entrustid=Integer.parseInt(sr.getEntrustcompany());
			cname = entrustCompanyService.getEntrustCompany(entrustid).getEntrustcompany();
			summoney =sr.getCheckmoney();
			paymoney =sr.getPrepaymoney();
			nopaymoney = summoney-paymoney;
			payedmoney = 0;
		    i++;
		}
		//最后一条
		Prepayment p = new Prepayment();
		p.setId(entrustid);
		PageModel<Prepayment> pmpp = prepaymentService.getPrepayment(p, 1, Constants.MAX_COUNT);
		for(Prepayment pp :pmpp.getList()){
			payedmoney += pp.getPrepaymoney();
		}
		
		SampleRegiste temp = new SampleRegiste();
		temp.setEntrustcompanyStr(cname);
		temp.setPayedmoney(payedmoney);
		temp.setCheckmoney(summoney);
		temp.setPrepaymoney(paymoney);
		temp.setBalancemoney(nopaymoney);
		//得到预付款余额
		EntrustCompany entrustCompany = entrustCompanyService.getEntrustCompany(entrustid);
		temp.setNotusedmoney(entrustCompany.getPrepaymoney());
		psr.add(temp);
		return LIST;
	}
	/**
	 * 委托单位统计列表功能
	 * @return
	 */
	public String listcompany() {
		qualificationmap = ApplicationUtils.getQualification();
		entrustcompanyList = entrustCompanyService.getAll();
		pageModel = statisticsService.getEntrustCompanyStatistics(statistics, pageNo, pageSize);
		return LIST;
	}
	
	/**
	 * 员工工作量统计列表功能
	 * @return
	 */
	public String listemployee() {
		map = ApplicationUtils.getExamineUser();
		qualificationmap = ApplicationUtils.getQualification();
		entrustcompanyList = entrustCompanyService.getAll();
		pageModel = statisticsService.getEmployeeStatistics(statistics, pageNo, pageSize);
		return LIST;
	}
	
	// 导出单位统计的excel
	public String exportCompany() throws IOException{
		pageModel = statisticsService.getEntrustCompanyStatistics(statistics, pageNo, pageSize);
		ExportUtils edu = new ExportUtils();
        String[] fieldname = {"委托单位","送样次数","合同金额","已付金额","未付金额","完成检验数量","已开发票数量","已发快递数量"};
		String[] columnname = {"entrustcompany","samples","checkmoney","premoney","balmoney","finished","bills","posts"};
		edu.exportExcel2("委托单位送样统计",fieldname,columnname,pageModel,request,response);
        return null;
	}
	// 导出人员统计的excel
	public String exportEmployee() throws IOException{
		pageModel = statisticsService.getEmployeeStatistics(statistics, pageNo, pageSize);
		ExportUtils edu = new ExportUtils();
        String[] fieldname = {"员工名称","派样数量","收样数量","总完成数量","未完成数量","按时完成数量","超期完成数量"};
		String[] columnname = {"examineuser","sends","receives","finished","nofinished","finishintime","finishovertime"};
		edu.exportExcel2("员工工作量统计",fieldname,columnname,pageModel,request,response);
        return null;
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
	public Statistics getStatistics() {
		return statistics;
	}
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
	public List<SampleRegiste> getPsr() {
		return psr;
	}
	public void setPsr(List<SampleRegiste> psr) {
		this.psr = psr;
	}
	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
}
