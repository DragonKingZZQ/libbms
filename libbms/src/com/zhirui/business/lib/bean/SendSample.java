package com.zhirui.business.lib.bean;

import java.sql.Timestamp;
import java.util.Date;

import cn.kepu.utils.StringUtils;

import com.zhirui.business.utils.ApplicationUtils;


/**
 * SendSample entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SendSample  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String sampleno;
     private String sendsampleno;
     private Integer sendid;
     private String sendsamplename;
     private String storecondition;
     private Date startdate;
     private Date enddate;
     private Integer leader;
     private String leaderStr;
     private String receiveflag;
     private Integer receiveuser;
     private String receiveuserStr;
     private Date receivedate;
     private Integer examineuser;
     private String examineuserStr;
     private String finishflag;
     private Integer finishexaminuser;
     private String finishexaminuserStr;
     private Date finishdate;
     private Integer createuser;
     private Date createdate;
     private String status;
     private String statusname;
     private Date statuschangedate;
     private String samplecount;
     private String remark;
     private String receivewarnflag;
     //查询用的属性
     private String checkitem;

     private String fieldorder;
     
     //曾智琴  查询用的属性
     private String checkstandardname;
     //曾智琴 弹窗用的属性
     private String returnvalue;
    // Constructors

    /** default constructor */
    public SendSample() {
    }

	/** minimal constructor */
    public SendSample(String sampleno, String sendsampleno, String sendsamplename) {
        this.sampleno = sampleno;
        this.sendsampleno = sendsampleno;
        this.sendsamplename = sendsamplename;
    }
    
    /** full constructor */
    public SendSample(String sampleno, String sendsampleno, String sendsamplename, String storecondition, Date startdate, Date enddate, Integer leader, String receiveflag, Integer receiveuser, Date receivedate, Integer examineuser, String finishflag, Integer finishexaminuser, Date finishdate, Integer createuser, Timestamp createdate, String status, Timestamp statuschangedate,String samplecount,String remark,String returnvalue) {
        this.sampleno = sampleno;
        this.sendsampleno = sendsampleno;
        this.sendsamplename = sendsamplename;
        this.storecondition = storecondition;
        this.startdate = startdate;
        this.enddate = enddate;
        this.leader = leader;
        this.receiveflag = receiveflag;
        this.receiveuser = receiveuser;
        this.receivedate = receivedate;
        this.examineuser = examineuser;
        this.finishflag = finishflag;
        this.finishexaminuser = finishexaminuser;
        this.finishdate = finishdate;
        this.createuser = createuser;
        this.createdate = createdate;
        this.status = status;
        this.statuschangedate = statuschangedate;
        this.samplecount = samplecount;
        this.remark = remark;
        this.returnvalue = returnvalue;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
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

    public String getSendsampleno() {
        return this.sendsampleno;
    }
    
    public void setSendsampleno(String sendsampleno) {
        this.sendsampleno = sendsampleno;
    }

    public String getSendsamplename() {
        return this.sendsamplename;
    }
    
    public void setSendsamplename(String sendsamplename) {
        this.sendsamplename = sendsamplename;
    }

    public String getStorecondition() {
        return this.storecondition;
    }
    
    public void setStorecondition(String storecondition) {
        this.storecondition = storecondition;
    }

    public Date getStartdate() {
        return this.startdate;
    }
    
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return this.enddate;
    }
    
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Integer getLeader() {
        return this.leader;
    }
    
    public void setLeader(Integer leader) {
        this.leader = leader;
    }

    public String getReceiveflag() {
        return this.receiveflag;
    }
    
    public void setReceiveflag(String receiveflag) {
        this.receiveflag = receiveflag;
    }

    public Integer getReceiveuser() {
        return this.receiveuser;
    }
    
    public void setReceiveuser(Integer receiveuser) {
        this.receiveuser = receiveuser;
    }

    public Date getReceivedate() {
        return this.receivedate;
    }
    
    public void setReceivedate(Date receivedate) {
        this.receivedate = receivedate;
    }

    public Integer getExamineuser() {
        return this.examineuser;
    }
    
    public void setExamineuser(Integer examineuser) {
        this.examineuser = examineuser;
    }

    public String getFinishflag() {
        return this.finishflag;
    }
    
    public void setFinishflag(String finishflag) {
        this.finishflag = finishflag;
    }

    public Integer getFinishexaminuser() {
        return this.finishexaminuser;
    }
    
    public void setFinishexaminuser(Integer finishexaminuser) {
        this.finishexaminuser = finishexaminuser;
    }

    public Date getFinishdate() {
        return this.finishdate;
    }
    
    public void setFinishdate(Date finishdate) {
        this.finishdate = finishdate;
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

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStatuschangedate() {
        return this.statuschangedate;
    }
    
    public void setStatuschangedate(Date statuschangedate) {
        this.statuschangedate = statuschangedate;
    }

	public String getStatusname() {
		if(StringUtils.isEmpty(this.status)) return "";
		return ApplicationUtils.getBaseStatusMap().get(this.status);
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}

	public String getExamineuserStr() {
		if (examineuser==null) return "";
		examineuserStr = ApplicationUtils.getExamineUser().get(String.valueOf(examineuser));
		return examineuserStr;
	}

	public void setExamineuserStr(String examineuserStr) {
		this.examineuserStr = examineuserStr;
	}

	public String getFinishexaminuserStr() {
		if (finishexaminuser==null) return "";
		finishexaminuserStr = ApplicationUtils.getExamineUser().get(String.valueOf(finishexaminuser));

		return finishexaminuserStr;
	}

	public void setFinishexaminuserStr(String finishexaminuserStr) {
		this.finishexaminuserStr = finishexaminuserStr;
	}

	public String getCheckitem() {
		return checkitem;
	}

	public void setCheckitem(String checkitem) {
		this.checkitem = checkitem;
	}

	public String getSamplecount() {
		return samplecount;
	}

	public void setSamplecount(String samplecount) {
		this.samplecount = samplecount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReceivewarnflag() {
		return receivewarnflag;
	}

	public void setReceivewarnflag(String receivewarnflag) {
		this.receivewarnflag = receivewarnflag;
	}

	public Integer getSendid() {
		return sendid;
	}

	public void setSendid(Integer sendid) {
		this.sendid = sendid;
	}

	public String getReceiveuserStr() {
		if (receiveuser==null) return "";
		receiveuserStr = ApplicationUtils.getExamineUser().get(String.valueOf(receiveuser));

		return receiveuserStr;
	}

	public void setReceiveuserStr(String receiveuserStr) {
		this.receiveuserStr = receiveuserStr;
	}

	public String getFieldorder() {
		return fieldorder;
	}

	public void setFieldorder(String fieldorder) {
		this.fieldorder = fieldorder;
	}

	public String getLeaderStr() {
		if (leader==null) return "";
		leaderStr = ApplicationUtils.getExamineUser().get(String.valueOf(leader));
		return leaderStr;
	}

	public void setLeaderStr(String leaderStr) {
		this.leaderStr = leaderStr;
	}

	public String getCheckstandardname() {
		return checkstandardname;
	}

	public void setCheckstandardname(String checkstandardname) {
		this.checkstandardname = checkstandardname;
	}

	public String getReturnvalue() {
		return returnvalue;
	}

	public void setReturnvalue(String returnvalue) {
		this.returnvalue = returnvalue;
	}
	
	
}