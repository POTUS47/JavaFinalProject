package com.finalproject.model;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "return")
public class Return implements Serializable {

    @Id
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "return_time")
    private LocalDateTime returnTime;

    @Column(name = "return_reason")
    private String returnReason;

    @Column(name = "return_status")
    private String returnStatus;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order orders;

    // Getters and Setters

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public Order getOrders() {
        return orders;
    }

    public void setOrders(Order orders) {
        this.orders = orders;
    }
}