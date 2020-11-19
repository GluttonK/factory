package com.nobugs.entity;

import java.util.Date;

public class TFactory {
    private Integer id;

    private Integer flag;

    private Date createTime;

    private Integer createUserid;

    private Date updateTime;

    private Integer updateUserid;

    private String bak;

    private String factoryName;

    private String factoryImgUrl;

    private String factoryAddr;

    private String factoryUrl;

    private Integer factoryWorker;

    private Integer factoryStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(Integer createUserid) {
        this.createUserid = createUserid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUserid() {
        return updateUserid;
    }

    public void setUpdateUserid(Integer updateUserid) {
        this.updateUserid = updateUserid;
    }

    public String getBak() {
        return bak;
    }

    public void setBak(String bak) {
        this.bak = bak == null ? null : bak.trim();
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName == null ? null : factoryName.trim();
    }

    public String getFactoryImgUrl() {
        return factoryImgUrl;
    }

    public void setFactoryImgUrl(String factoryImgUrl) {
        this.factoryImgUrl = factoryImgUrl == null ? null : factoryImgUrl.trim();
    }

    public String getFactoryAddr() {
        return factoryAddr;
    }

    public void setFactoryAddr(String factoryAddr) {
        this.factoryAddr = factoryAddr == null ? null : factoryAddr.trim();
    }

    public String getFactoryUrl() {
        return factoryUrl;
    }

    public void setFactoryUrl(String factoryUrl) {
        this.factoryUrl = factoryUrl == null ? null : factoryUrl.trim();
    }

    public Integer getFactoryWorker() {
        return factoryWorker;
    }

    public void setFactoryWorker(Integer factoryWorker) {
        this.factoryWorker = factoryWorker;
    }

    public Integer getFactoryStatus() {
        return factoryStatus;
    }

    public void setFactoryStatus(Integer factoryStatus) {
        this.factoryStatus = factoryStatus;
    }
}