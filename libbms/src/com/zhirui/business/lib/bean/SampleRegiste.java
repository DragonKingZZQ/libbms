package com.zhirui.business.lib.bean;
// default package

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import cn.kepu.utils.StringUtils;

import com.zhirui.business.common.Constants;
import com.zhirui.business.utils.ApplicationUtils;


/**
 * SampleRegiste entity. @author MyEclipse Persistence Tools
 */

public class SampleRegiste  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String sampleno;
     private String samplename;
     private String entrustcompany;
     private String entrustcompanyStr;
     private Integer entrustcompanyid;
     private String productionunit;
     private Date receivedate;
     private Date finishdate;
     private String senduser;
     private String sendusername;
	 private Integer senduserid;
     private String address;
     private String relation;
     private String email;
	 private Float checkmoney;
     private Integer payway;
     private String paydes;
     private Float prepaymoney;
     private Float balancemoney;
     private Float payedmoney;
     private Float notusedmoney;
     private String samplestatus;
     private Integer samplehandle;
     private String billischeck;
     private String billno;
     private String reportno;
     private Integer examineuser;
     private String examineuserStr;
     private String talent;
     private String talentStr;
     private String remark;
     private Integer createuser;
     private Date createdate;
     private Integer taskdays;
     private String status;
     private String statusName;
     private Date statuschangedate;
     private Map<Integer,String> checkitemMap;
     private String checkitemname;

     private Integer overtimeflag;
    // Constructors
    private String addrelationuser;
    
    private String samplecount;
    private String samplemodel;
    private Integer taxtype;
    
    
    
    public Float getPayedmoney() {
		return payedmoney;
	}

	public void setPayedmoney(Float payedmoney) {
		this.payedmoney = payedmoney;
	}

	private Integer reporttype;
    private Integer accordingtype;
    private String accordings;
    private String storecondition;
    
    private String fieldorder;

    //////////以下为导出做的属性////////////
    private String receiveuser; //受理人
    private String checkitems;  //检验项目
    private String checkuser;   //检验员
    //////////////////////////////////
    
    //曾智琴
    private String acceptpeople; //新加字段对接人
    private String resource;  //新加字段来源
    private Integer samplecategoryid;  //新加样品分类
    private Integer checkstandardid;  //新加检验标准
    private String checkstandardname; //
    
	/** default constructor */
    public SampleRegiste() {
    }

	/** minimal constructor */
    public SampleRegiste(String sampleno, String samplename) {
        this.sampleno = sampleno;
        this.samplename = samplename;
    }
    
    /** full constructor */
    
    
   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
   

	public Integer getCheckstandardid() {
		return checkstandardid;
	}

	public void setCheckstandardid(Integer checkstandardid) {
		this.checkstandardid = checkstandardid;
	}

	

	public SampleRegiste(Integer id, String sampleno, String samplename,
			String entrustcompany, String entrustcompanyStr,
			Integer entrustcompanyid, String productionunit, Date receivedate,
			Date finishdate, String senduser, String sendusername,
			Integer senduserid, String address, String relation, String email,
			Float checkmoney, Integer payway, String paydes, Float prepaymoney,
			Float balancemoney, Float payedmoney, Float notusedmoney,
			String samplestatus, Integer samplehandle, String billischeck,
			String billno, String reportno, Integer examineuser,
			String examineuserStr, String talent, String talentStr,
			String remark, Integer createuser, Date createdate,
			Integer taskdays, String status, String statusName,
			Date statuschangedate, Map<Integer, String> checkitemMap,
			String checkitemname, Integer overtimeflag, String addrelationuser,
			String samplecount, String samplemodel, Integer taxtype,
			Integer reporttype, Integer accordingtype, String accordings,
			String storecondition, String fieldorder, String receiveuser,
			String checkitems, String checkuser, String acceptpeople,
			String resource, Integer samplecategoryid, Integer checkstandardid,String checkstandardname) {
		super();
		this.id = id;
		this.sampleno = sampleno;
		this.samplename = samplename;
		this.entrustcompany = entrustcompany;
		this.entrustcompanyStr = entrustcompanyStr;
		this.entrustcompanyid = entrustcompanyid;
		this.productionunit = productionunit;
		this.receivedate = receivedate;
		this.finishdate = finishdate;
		this.senduser = senduser;
		this.sendusername = sendusername;
		this.senduserid = senduserid;
		this.address = address;
		this.relation = relation;
		this.email = email;
		this.checkmoney = checkmoney;
		this.payway = payway;
		this.paydes = paydes;
		this.prepaymoney = prepaymoney;
		this.balancemoney = balancemoney;
		this.payedmoney = payedmoney;
		this.notusedmoney = notusedmoney;
		this.samplestatus = samplestatus;
		this.samplehandle = samplehandle;
		this.billischeck = billischeck;
		this.billno = billno;
		this.reportno = reportno;
		this.examineuser = examineuser;
		this.examineuserStr = examineuserStr;
		this.talent = talent;
		this.talentStr = talentStr;
		this.remark = remark;
		this.createuser = createuser;
		this.createdate = createdate;
		this.taskdays = taskdays;
		this.status = status;
		this.statusName = statusName;
		this.statuschangedate = statuschangedate;
		this.checkitemMap = checkitemMap;
		this.checkitemname = checkitemname;
		this.overtimeflag = overtimeflag;
		this.addrelationuser = addrelationuser;
		this.samplecount = samplecount;
		this.samplemodel = samplemodel;
		this.taxtype = taxtype;
		this.reporttype = reporttype;
		this.accordingtype = accordingtype;
		this.accordings = accordings;
		this.storecondition = storecondition;
		this.fieldorder = fieldorder;
		this.receiveuser = receiveuser;
		this.checkitems = checkitems;
		this.checkuser = checkuser;
		this.acceptpeople = acceptpeople;
		this.resource = resource;
		this.samplecategoryid = samplecategoryid;
		this.checkstandardid = checkstandardid;
		this.checkstandardname = checkstandardname;
	}

	public Integer getSamplecategoryid() {
		return samplecategoryid;
	}

	public void setSamplecategoryid(Integer samplecategoryid) {
		this.samplecategoryid = samplecategoryid;
	}

	public String getAcceptpeople() {
		return acceptpeople;
	}

	public void setAcceptpeople(String acceptpeople) {
		this.acceptpeople = acceptpeople;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	

	public void setId(Integer id) {
        this.id = id;
    }

    public String getSampleno() {
        return this.sampleno;
    }
    
    public void setSampleno(String sampleno) {
        this.sampleno = sampleno;
    }

    public String getSamplename() {
        return this.samplename;
    }
    
    public void setSamplename(String samplename) {
        this.samplename = samplename;
    }

    public String getEntrustcompany() {
        return this.entrustcompany;
    }
    
    public void setEntrustcompany(String entrustcompany) {
        this.entrustcompany = entrustcompany;
    }

    public String getProductionunit() {
        return this.productionunit;
    }
    
    public void setProductionunit(String productionunit) {
        this.productionunit = productionunit;
    }

    public Date getReceivedate() {
        return this.receivedate;
    }
    
    public void setReceivedate(Date receivedate) {
        this.receivedate = receivedate;
    }

    public Date getFinishdate() {
        return this.finishdate;
    }
    
    public void setFinishdate(Date finishdate) {
        this.finishdate = finishdate;
    }

    public String getSenduser() {
        return this.senduser;
    }
    
    public void setSenduser(String senduser) {
        this.senduser = senduser;
    }

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public String getRelation() {
        return this.relation;
    }
    
    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Float getCheckmoney() {
        return this.checkmoney;
    }
    
    public void setCheckmoney(Float checkmoney) {
        this.checkmoney = checkmoney;
    }

    public Float getPrepaymoney() {
        return this.prepaymoney;
    }
    
    public void setPrepaymoney(Float prepaymoney) {
        this.prepaymoney = prepaymoney;
    }
    public Float getBalancemoney() {
        return this.balancemoney;
    }
    
    public void setBalancemoney(Float balancemoney) {
        this.balancemoney = balancemoney;
    }
    public String getSamplestatus() {
        return this.samplestatus;
    }
    
    public void setSamplestatus(String samplestatus) {
        this.samplestatus = samplestatus;
    }

    public String getBillischeck() {
        return this.billischeck;
    }
    
    public void setBillischeck(String billischeck) {
        this.billischeck = billischeck;
    }

    public String getBillno() {
        return this.billno;
    }
    
    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getReportno() {
        return this.reportno;
    }
    
    public void setReportno(String reportno) {
        this.reportno = reportno;
    }

    public Integer getExamineuser() {
        return this.examineuser;
    }
    
    public void setExamineuser(Integer examineuser) {
        this.examineuser = examineuser;
    }

    public String getTalent() {
        return this.talent;
    }
    
    public void setTalent(String talent) {
        this.talent = talent;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCreateuser() {
        return this.createuser;
    }
    
    public void setCreateuser(Integer createuser) {
        this.createuser = createuser;
    }

    public Date getCreatedate() {
        return this.createdate;
    }
    
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getTaskdays() {
        return this.taskdays;
    }
    
    public void setTaskdays(Integer taskdays) {
        this.taskdays = taskdays;
    }

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusname() {
		if(StringUtils.isEmpty(this.status)) return "";
		return ApplicationUtils.getBaseStatusMap().get(this.status);
	}

	public String getAddrelationuser() {
		return addrelationuser;
	}

	public void setAddrelationuser(String addrelationuser) {
		this.addrelationuser = addrelationuser;
	}

	public Date getStatuschangedate() {
		return statuschangedate;
	}

	public void setStatuschangedate(Date statuschangedate) {
		this.statuschangedate = statuschangedate;
	}

	public Map<Integer, String> getCheckitemMap() {
		return checkitemMap;
	}

	public void setCheckitemMap(Map<Integer, String> checkitemMap) {
		this.checkitemMap = checkitemMap;
	}

	public Integer getEntrustcompanyid() {
		return entrustcompanyid;
	}

	public void setEntrustcompanyid(Integer entrustcompanyid) {
		this.entrustcompanyid = entrustcompanyid;
	}

	public Integer getSenduserid() {
		return senduserid;
	}

	public void setSenduserid(Integer senduserid) {
		this.senduserid = senduserid;
	}

	public String getEntrustcompanyStr() {
		return entrustcompanyStr;
	}

	public void setEntrustcompanyStr(String entrustcompanyStr) {
		this.entrustcompanyStr = entrustcompanyStr;
	}

	public Integer getOvertimeflag() {
		if (statuschangedate==null||status.compareTo(Constants.STATUS_FINISHCHECK)>=0) return 0;
		//Calendar start = Calendar.getInstance();
		//Calendar end = Calendar.getInstance();
		//start.setTime(receivedate);
		//end.setTime(finishdate);
		/*
		long l=end.getTimeInMillis()-start.getTimeInMillis();
		int days=new Long(l/(1000*60*60*24)).intValue();

		Calendar now = Calendar.getInstance();
		now.setTime(statuschangedate);
		//判断是上午还是下午
		int hour = now.get(Calendar.HOUR_OF_DAY);
		if (hour>12)
		    now.add(Calendar.DATE,days+1);
		else
			now.add(Calendar.DATE,days);
			*/
		Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYY-MM-DD");
		String now = sdf.format(date);
		String fdate = sdf.format(finishdate);

		if (now.compareTo(fdate)>0)	return 1;
		return 0;
	}

	public void setOvertimeflag(Integer overtimeflag) {
		this.overtimeflag = overtimeflag;
	}

	public String getExamineuserStr() {
		return examineuserStr;
	}

	public void setExamineuserStr(String examineuserStr) {
		this.examineuserStr = examineuserStr;
	}

	public String getTalentStr() {
		return talentStr;
	}

	public void setTalentStr(String talentStr) {
		this.talentStr = talentStr;
	}

	public Integer getSamplehandle() {
		return samplehandle;
	}

	public void setSamplehandle(Integer samplehandle) {
		this.samplehandle = samplehandle;
	}

	public String getSamplecount() {
		return samplecount;
	}

	public void setSamplecount(String samplecount) {
		this.samplecount = samplecount;
	}

	public String getSamplemodel() {
		return samplemodel;
	}

	public void setSamplemodel(String samplemodel) {
		this.samplemodel = samplemodel;
	}

	public Integer getTaxtype() {
		return taxtype;
	}

	public void setTaxtype(Integer taxtype) {
		this.taxtype = taxtype;
	}

	public Integer getReporttype() {
		return reporttype;
	}

	public void setReporttype(Integer reporttype) {
		this.reporttype = reporttype;
	}

	public Integer getAccordingtype() {
		return accordingtype;
	}

	public void setAccordingtype(Integer accordingtype) {
		this.accordingtype = accordingtype;
	}

	public String getAccordings() {
		return accordings;
	}

	public void setAccordings(String accordings) {
		this.accordings = accordings;
	}

	public String getStorecondition() {
		return storecondition;
	}

	public void setStorecondition(String storecondition) {
		this.storecondition = storecondition;
	}

	public String getCheckitemname() {
		return checkitemname;
	}

	public void setCheckitemname(String checkitemname) {
		this.checkitemname = checkitemname;
	}

	public Integer getPayway() {
		return payway;
	}

	public void setPayway(Integer payway) {
		this.payway = payway;
	}

	public String getPaydes() {
		return paydes;
	}

	public void setPaydes(String paydes) {
		this.paydes = paydes;
	}

	public String getFieldorder() {
		return fieldorder;
	}

	public void setFieldorder(String fieldorder) {
		this.fieldorder = fieldorder;
	}
	public String getSendusername() {
		return sendusername;
	}

	public void setSendusername(String sendusername) {
		this.sendusername = sendusername;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReceiveuser() {
		return receiveuser;
	}

	public void setReceiveuser(String receiveuser) {
		this.receiveuser = receiveuser;
	}

	public String getCheckitems() {
		return checkitems;
	}

	public void setCheckitems(String checkitems) {
		this.checkitems = checkitems;
	}

	public String getCheckuser() {
		return checkuser;
	}

	public void setCheckuser(String checkuser) {
		this.checkuser = checkuser;
	}

	public Float getNotusedmoney() {
		return notusedmoney;
	}

	public void setNotusedmoney(Float notusedmoney) {
		this.notusedmoney = notusedmoney;
	}

	public String getCheckstandardname() {
		return checkstandardname;
	}

	public void setCheckstandardname(String checkstandardname) {
		this.checkstandardname = checkstandardname;
	}
	
	
}