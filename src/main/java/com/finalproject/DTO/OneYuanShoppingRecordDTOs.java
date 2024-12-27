package com.finalproject.DTO;

import java.time.LocalDateTime;

public class OneYuanShoppingRecordDTOs {
    public static class OneYuanShoppingRecordDTO {
    private String recordId;
    private String productId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int minParticipants;
    private boolean isDrawn;
    private String result;
    private String productPic; // 图片ID
    private String productName;

    // 构造函数
    public OneYuanShoppingRecordDTO() {}

    // Getter 和 Setter 方法
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

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
       this.productName = productName;
    }
    }
}