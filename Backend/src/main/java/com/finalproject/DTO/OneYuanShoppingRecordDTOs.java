package com.finalproject.DTO;

import java.time.LocalDateTime;

public class OneYuanShoppingRecordDTOs {
    public static class OneYuanShoppingRecordDTO {
        private String recordId;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private int minParticipants;
        private int currentParticipants;
        private boolean isDrawn;
        private String result;

        // 构造函数
        public OneYuanShoppingRecordDTO() {}

        // Getter 和 Setter 方法
        public String getRecordId() {
            return recordId;
        }

        public void setRecordId(String recordId) {
            this.recordId = recordId;
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

        public int getCurrentParticipants() {return currentParticipants;}

        public void setCurrentParticipants(int currentParticipants) {this.currentParticipants = currentParticipants;}

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

    public static class CreateOneYuanDTO {
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private int minParticipants;
        private int currentParticipants;
        private boolean isDrawn;
        private String result;

        // 构造函数
        public CreateOneYuanDTO() {}

        // Getter 和 Setter 方法

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

        public int getCurrentParticipants() {return currentParticipants;}

        public void setCurrentParticipants(int currentParticipants) {this.currentParticipants = currentParticipants;}

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
}