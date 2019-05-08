package com.zhirui.business.lib.bean;
// default package



/**
 * MenuFunction entity. @author MyEclipse Persistence Tools
 */

public class MenuFunction  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String code;
     private String menuname;
     private String url;
     private String icon;
     private Integer order;
     private String remark;


    // Constructors

    /** default constructor */
    public MenuFunction() {
    }

	/** minimal constructor */
    public MenuFunction(String code, String menuname) {
        this.code = code;
        this.menuname = menuname;
    }
    
    /** full constructor */
    public MenuFunction(String code, String menuname, String url, String icon, Integer order, String remark) {
        this.code = code;
        this.menuname = menuname;
        this.url = url;
        this.icon = icon;
        this.order = order;
        this.remark = remark;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public String getMenuname() {
        return this.menuname;
    }
    
    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return this.icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getOrder() {
        return this.order;
    }
    
    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   
}