package com.finalproject.model;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "return")
public class Return implements Serializable {

    @Id
    @Column(name = "item_id")
    private String itemId;

    @Column(name = "return_time")
    private LocalDateTime returnTime;

    @Column(name = "return_reason")
    private String returnReason;

    @Enumerated(EnumType.STRING)
    @Column(name = "return_status")
    private ReturnStatus returnStatus;

    @Column(name = "shipping_number")
    private String shippingNumber;

    @Column(name = "result_reason")
    private String resultReason;

    @ManyToOne
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    private OrderItem item;

    // Getters and Setters

    public String getOrderId() {
        return itemId;
    }

    public void setOrderId(String itemId) {
        this.itemId = itemId;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public ReturnStatus getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(ReturnStatus returnStatus) {
        this.returnStatus = returnStatus;
    }

    public OrderItem getItem() {
        return item;
    }

    public void setItem(OrderItem item) {
        this.item = item;
    }

    public String getShippingNumber() {
        return shippingNumber;
    }
    public void setShippingNumber(String shippingNumber) {
        this.shippingNumber = shippingNumber;
    }

    public String getResultReason() {
        return resultReason;
    }
    public void setResultReason(String resultReason) {
        this.resultReason = resultReason;
    }

    public enum ReturnStatus {
        待审核, 审核通过, 申请被拒绝,已退货,已收货,已退款
    }

}