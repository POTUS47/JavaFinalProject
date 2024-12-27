package com.finalproject.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "order_id", nullable = false)
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "account_id", nullable = false,insertable = false, updatable = false)
    private Buyer buyer;  // Assuming there is an Account class for the buyer

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "account_id",insertable = false, updatable = false)
    private Store store;  // Assuming there is an Account class for the store

    @Column(name = "buyer_id",nullable = false, updatable=false)
    private String buyerId;

    @Column(name = "store_id",nullable = false, updatable=false)
    private String storeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "shipping_address", length = 255)
    private String shippingAddress;

    @Column(name = "billing_address", length = 255)
    private String billingAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Column(name = "order_time", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private LocalDateTime orderTime;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "pay_time")
    private LocalDateTime payTime;


    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Account getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Account getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", buyer=" + buyer +
                ", store=" + store +
                ", orderStatus=" + orderStatus +
                ", totalPrice=" + totalPrice +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", billingAddress='" + billingAddress + '\'' +
                ", paymentStatus=" + paymentStatus +
                ", paymentMethod=" + paymentMethod +
                ", orderTime=" + orderTime +
                ", username='" + username + '\'' +
                ", payTime=" + payTime +
                '}';
    }

    public String getBuyerId() {
        return buyerId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    // Enum for OrderStatus
    public enum OrderStatus {
        等待确认, 处理中, 运输中, 已完成, 已取消, 已送达
    }

    // Enum for PaymentStatus
    public enum PaymentStatus {
        等待支付, 已付款 ,付款失败
    }

    // Enum for PaymentMethod
    public enum PaymentMethod {
        支付宝,微信 ,钱包
    }
}
