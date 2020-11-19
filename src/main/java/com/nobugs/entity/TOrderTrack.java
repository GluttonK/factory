package com.nobugs.entity;

import java.util.Date;

public class TOrderTrack {
    private Integer id;

    private Integer flag;

    private Date createTime;

    private Integer createUserid;

    private Date updateTime;

    private Integer updateUserid;

    private Integer scheduleId;

    private Integer planId;

    private String planSeq;

    private Integer productId;

    private String productName;

    private Integer workingCount;

    private Integer qualifiedCount;

    private Integer factoryId;

    private String scheduleSeq;

    private Integer scheduleStatus;

    private Integer scheduleCount;

    private Integer equipmentID;

    private String equipmentSeq;

    private Date startDate;

    private Date endDate;

    private Integer orderId;
    private String orderSeq;
    private Integer unqualifiedCout;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(String orderSeq) {
        this.orderSeq = orderSeq;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(Integer equipmentID) {
        this.equipmentID = equipmentID;
    }

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

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getWorkingCount() {
        return workingCount;
    }

    public void setWorkingCount(Integer workingCount) {
        this.workingCount = workingCount;
    }

    public Integer getQualifiedCount() {
        return qualifiedCount;
    }

    public void setQualifiedCount(Integer qualifiedCount) {
        this.qualifiedCount = qualifiedCount;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public String getScheduleSeq() {
        return scheduleSeq;
    }

    public void setScheduleSeq(String scheduleSeq) {
        this.scheduleSeq = scheduleSeq;
    }

    public String getPlanSeq() {
        return planSeq;
    }

    public void setPlanSeq(String planSeq) {
        this.planSeq = planSeq;
    }

    public Integer getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(Integer scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    public Integer getScheduleCount() {
        return scheduleCount;
    }

    public void setScheduleCount(Integer scheduleCount) {
        this.scheduleCount = scheduleCount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getEquipmentSeq() {
        return equipmentSeq;
    }

    public void setEquipmentSeq(String equipmentSeq) {
        this.equipmentSeq = equipmentSeq;
    }

    public Integer getUnqualifiedCout() {
        return unqualifiedCout;
    }

    public void setUnqualifiedCout(Integer unqualifiedCout) {
        this.unqualifiedCout = unqualifiedCout;
    }

    @Override
    public String toString() {
        return "TOrderTrack{" +
                "id=" + id +
                ", flag=" + flag +
                ", createTime=" + createTime +
                ", createUserid=" + createUserid +
                ", updateTime=" + updateTime +
                ", updateUserid=" + updateUserid +
                ", scheduleId=" + scheduleId +
                ", planId=" + planId +
                ", planSeq='" + planSeq + '\'' +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", workingCount=" + workingCount +
                ", qualifiedCount=" + qualifiedCount +
                ", factoryId=" + factoryId +
                ", scheduleSeq='" + scheduleSeq + '\'' +
                ", scheduleStatus=" + scheduleStatus +
                ", scheduleCount=" + scheduleCount +
                ", equipmentID=" + equipmentID +
                ", equipmentSeq='" + equipmentSeq + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", orderId=" + orderId +
                ", orderSeq='" + orderSeq + '\'' +
                ", unqualifiedCout=" + unqualifiedCout +
                '}';
    }
}