package com.finalproject.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "one_yuan_orders")
public class OneYuanShoppingRecord {

    @Id
    @Column(name = "record_time", nullable = false)
    private String recordId;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @OneToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "min_participants", nullable = false)
    private int minParticipants;

    @Column(name = "current_participants", nullable = false)
    private int currentParticipants;

    @Column(name = "is_drawn", nullable = false)
    private boolean isDrawn;

    @Column(name = "result")
    private String result;

    // 构造函数
    public OneYuanShoppingRecord() {}

    // Getter 和 Setter
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        if (product != null) {
            this.productId = product.getProductId();
        }
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getMinParticipants() {
        return minParticipants;
    }

    public void setMinParticipants(int minParticipants) {
        this.minParticipants = minParticipants;
    }

    public int getCurrentParticipants() {
        return currentParticipants;
    }

    public void setCurrentParticipants(int currentParticipants) {
        this.currentParticipants = currentParticipants;
    }

    public boolean isDrawn() {
        return isDrawn;
    }

    public void setDrawn(boolean isDrawn) {
        this.isDrawn = isDrawn;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    // 便捷方法
    public void incrementParticipants() {
        this.currentParticipants++;
    }

    public boolean canAddParticipants() {
        return this.currentParticipants < this.minParticipants;
    }
}