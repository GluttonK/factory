package com.nobugs.entity;

import java.util.Date;

public class TProductPlan {

    private Integer id;

    private Integer flag;

    private Date createTime;

    private Integer createUserid;

    private Date updateTime;

    private Integer updateUserid;

    private String planSeq;


    private Integer orderId;

    private Integer productId;

    private Integer planCount;

    private Date deliveryDate;

    private Date planStartDate;

    private Date planEndDate;

    private Integer planStatus;

    private Integer factoryId;

    private String orderSeq;

    private String productName;



    public TProductPlan() {
    }

    public TProductPlan(String planSeq, String orderSeq) {
        this.planSeq = planSeq;
        this.orderSeq = orderSeq;
    }

    public TProductPlan(String planSeq, Integer planCount, Date deliveryDate, Date planStartDate, Date planEndDate, String orderSeq, String productName) {
        this.planSeq = planSeq;
        this.planCount = planCount;
        this.deliveryDate = deliveryDate;
        this.planStartDate = planStartDate;
        this.planEndDate = planEndDate;
        this.orderSeq = orderSeq;
        this.productName = productName;
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

    public String getPlanSeq() {
        return planSeq;
    }

    public void setPlanSeq(String planSeq) {
        this.planSeq = planSeq;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPlanCount() {
        return planCount;
    }

    public void setPlanCount(Integer planCount) {
        this.planCount = planCount;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(Date planStartDate) {
        this.planStartDate = planStartDate;
    }

    public Date getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(Date planEndDate) {
        this.planEndDate = planEndDate;
    }

    public Integer getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(Integer planStatus) {
        this.planStatus = planStatus;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public String getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(String orderSeq) {
        this.orderSeq = orderSeq;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "TProductPlan{" +
                "id=" + id +
                ", flag=" + flag +
                ", createTime=" + createTime +
                ", createUserid=" + createUserid +
                ", updateTime=" + updateTime +
                ", updateUserid=" + updateUserid +
                ", planSeq='" + planSeq + '\'' +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", planCount=" + planCount +
                ", deliveryDate=" + deliveryDate +
                ", planStartDate=" + planStartDate +
                ", planEndDate=" + planEndDate +
                ", planStatus=" + planStatus +
                ", factoryId=" + factoryId +
                ", orderSeq='" + orderSeq + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }
}