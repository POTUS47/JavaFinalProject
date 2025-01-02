package com.finalproject.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "complain")
public class Complain implements Serializable {

    @Id
    @Column(name = "item_id", nullable = false, length = 100)
    private String itemId;

    @Column(name = "buyer_reason", length = 200)
    private String buyerReason;

    @Column(name = "seller_reason", length = 200)
    private String sellerReason;

    @Column(name = "is_complain_success")
    private Boolean isComplainSuccess=false;

    @Enumerated(EnumType.STRING)
    @Column(name = "complain_status")
    private ComplainStatus complainStatus;

    @Column(name = "complain_time")
    private LocalDateTime complainTime;

    @Column(name = "result_reason")
    private String resultReason;

    @Column(name = "handler_id")
    private String handlerId;

    @ManyToOne
    @JoinColumn(name = "handler_id", referencedColumnName = "account_id", insertable = false, updatable = false)
    private Administrator handler;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "item_id", insertable = false, updatable = false)
    private OrderItem orderItem;

    // Getters and Setters

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getBuyerReason() {
        return buyerReason;
    }

    public void setBuyerReason(String buyerReason) {
        this.buyerReason = buyerReason;
    }

    public String getSellerReason() {
        return sellerReason;
    }

    public void setSellerReason(String sellerReason) {
        this.sellerReason = sellerReason;
    }

    public Boolean getIsComplainSuccess() {
        return isComplainSuccess;
    }

    public void setIsComplainSuccess(Boolean isComplainSuccess) {
        this.isComplainSuccess = isComplainSuccess;
    }

    public ComplainStatus getComplainStatus() {
        return complainStatus;
    }

    public void setComplainStatus(ComplainStatus complainStatus) {
        this.complainStatus = complainStatus;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public LocalDateTime getComplainTime() {
        return complainTime;
    }

    public void setComplainTime(LocalDateTime complainTime) {
        this.complainTime = complainTime;
    }
    public String getResultReason() {
        return resultReason;
    }
    public void setResultReason(String resultReason) {
        this.resultReason = resultReason;
    }
    public String getHandlerId() {
        return handlerId;
    }
    public void setHandlerId(String handlerId) {
        this.handlerId = handlerId;
    }

    // Enum for complainStatus
    public enum ComplainStatus {
        待审核,
        审核完成
    }
}
