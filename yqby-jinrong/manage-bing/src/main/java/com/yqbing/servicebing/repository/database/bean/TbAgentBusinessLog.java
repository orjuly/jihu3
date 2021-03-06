package com.yqbing.servicebing.repository.database.bean;

import java.util.Date;

public class TbAgentBusinessLog {
    private Integer id;

    private Long userid;

    private String username;

    private Byte userrole;

    private String mobile;

    private Long storeid;

    private String storename;

    private Long agentid;

    private Short agenttype;

    private Long agentpid;

    private String agentname;

    private String agentmobile;

    private Short businesstype;

    private String businesstypename;

    private Integer businessprice;

    private Integer businessnum;

    private Long provinceid;

    private String provincename;

    private Long cityid;

    private String cityname;

    private Long countyid;

    private String countyname;

    private Date createtime;

    private Byte isbalance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Byte getUserrole() {
        return userrole;
    }

    public void setUserrole(Byte userrole) {
        this.userrole = userrole;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Long getStoreid() {
        return storeid;
    }

    public void setStoreid(Long storeid) {
        this.storeid = storeid;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename == null ? null : storename.trim();
    }

    public Long getAgentid() {
        return agentid;
    }

    public void setAgentid(Long agentid) {
        this.agentid = agentid;
    }

    public Short getAgenttype() {
        return agenttype;
    }

    public void setAgenttype(Short agenttype) {
        this.agenttype = agenttype;
    }

    public Long getAgentpid() {
        return agentpid;
    }

    public void setAgentpid(Long agentpid) {
        this.agentpid = agentpid;
    }

    public String getAgentname() {
        return agentname;
    }

    public void setAgentname(String agentname) {
        this.agentname = agentname == null ? null : agentname.trim();
    }

    public String getAgentmobile() {
        return agentmobile;
    }

    public void setAgentmobile(String agentmobile) {
        this.agentmobile = agentmobile == null ? null : agentmobile.trim();
    }

    public Short getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(Short businesstype) {
        this.businesstype = businesstype;
    }

    public String getBusinesstypename() {
        return businesstypename;
    }

    public void setBusinesstypename(String businesstypename) {
        this.businesstypename = businesstypename == null ? null : businesstypename.trim();
    }

    public Integer getBusinessprice() {
        return businessprice;
    }

    public void setBusinessprice(Integer businessprice) {
        this.businessprice = businessprice;
    }

    public Integer getBusinessnum() {
        return businessnum;
    }

    public void setBusinessnum(Integer businessnum) {
        this.businessnum = businessnum;
    }

    public Long getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(Long provinceid) {
        this.provinceid = provinceid;
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename == null ? null : provincename.trim();
    }

    public Long getCityid() {
        return cityid;
    }

    public void setCityid(Long cityid) {
        this.cityid = cityid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname == null ? null : cityname.trim();
    }

    public Long getCountyid() {
        return countyid;
    }

    public void setCountyid(Long countyid) {
        this.countyid = countyid;
    }

    public String getCountyname() {
        return countyname;
    }

    public void setCountyname(String countyname) {
        this.countyname = countyname == null ? null : countyname.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Byte getIsbalance() {
        return isbalance;
    }

    public void setIsbalance(Byte isbalance) {
        this.isbalance = isbalance;
    }
}