package com.zhirui.business.lib.bean;
// default package

import java.util.Date;


/**
 * Groups entity. @author MyEclipse Persistence Tools
 */

public class Offices  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;


    // Constructors

    /** default constructor */
    public Offices() {
    }

	/** minimal constructor */
    public Offices(String name) {
        this.name = name;
    }
    
   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

   
}