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

    @Column(name = "return_status")
    private String returnStatus;

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

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public OrderItem getItem() {
        return item;
    }

    public void setItem(OrderItem item) {
        this.item = item;
    }
}