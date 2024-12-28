package com.finalproject.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "one_yuan_orders")
public class OneYuanShoppingRecord {

    @Id
    @Column(name = "record_id", nullable = false)
    private String recordId;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @ManyToOne
    @JoinColumn(name = "saler_id", referencedColumnName = "account_id", nullable = false)
    private Store saler;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @ElementCollection
    @Column(name = "participants")
    private List<String> participants; // 参与者的ID列表

    @Column(name = "min_participants", nullable = false)
    private int minParticipants; // 开奖的最小参与者数量

    @Column(name = "is_drawn", nullable = false)
    private boolean isDrawn; // 是否已开奖

    @Column(name = "result")
    private String result; // 开奖结果（获胜者ID）

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

    public Store getsaler() {
        return saler;
    }

    public void setsaler(Store saler) {
        this.saler = saler;
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

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public int getMinParticipants() {
        return minParticipants;
    }

    public void setMinParticipants(int minParticipants) {
        this.minParticipants = minParticipants;
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
}