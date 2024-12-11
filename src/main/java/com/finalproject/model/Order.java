package com.finalproject.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "total_pay", nullable = false)
    private BigDecimal totalPay;

    @Column(name = "actual_pay", nullable = false)
    private BigDecimal actualPay;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "receiving_time")
    private LocalDateTime receivingTime;

    @Column(name = "delivery_number")
    private String deliveryNumber;

    @Column(name = "score")
    private BigDecimal score;

    @Column(name = "remark")
    private String remark;

    @Column(name = "bonus_credits")
    private int bonusCredits;

    @Column(name = "return_or_not")
    private Boolean returnOrNot;

    @Column(name = "buyer_account_id")
    private String buyerAccountId;

    @Column(name = "store_account_id")
    private String storeAccountId;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "username")
    private String username;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "buyer_account_id", insertable = false, updatable = false)
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "store_account_id", insertable = false, updatable = false)
    private Store store;

    // Getters and Setters

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(BigDecimal totalPay) {
        this.totalPay = totalPay;
    }

    public BigDecimal getActualPay() {
        return actualPay;
    }

    public void setActualPay(BigDecimal actualPay) {
        this.actualPay = actualPay;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(LocalDateTime receivingTime) {
        this.receivingTime = receivingTime;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getBonusCredits() {
        return bonusCredits;
    }

    public void setBonusCredits(int bonusCredits) {
        this.bonusCredits = bonusCredits;
    }

    public Boolean getReturnOrNot() {
        return returnOrNot;
    }

    public void setReturnOrNot(Boolean returnOrNot) {
        this.returnOrNot = returnOrNot;
    }

    public String getBuyerAccountId() {
        return buyerAccountId;
    }

    public void setBuyerAccountId(String buyerAccountId) {
        this.buyerAccountId = buyerAccountId;
    }

    public String getStoreAccountId() {
        return storeAccountId;
    }

    public void setStoreAccountId(String storeAccountId) {
        this.storeAccountId = storeAccountId;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}