package com.finalproject.DTO;

import java.time.LocalDateTime;

public class AfterSellDTOs {

    public static class ReturnRequestDTO {
        private final String reason;
        public ReturnRequestDTO(String reason) {
            this.reason = reason;
        }
        public String getReason() {
            return reason;
        }
    }

    public static class ComplainRequestDTO {
        private final String reason;
        public ComplainRequestDTO(String returnId, String reason) {
            this.reason = reason;
        }
        public String getReason() {
            return reason;
        }
    }

    public static class approveReturnDTO{
        private final boolean isApproved;
        private final String reason;
        public approveReturnDTO(String reason, boolean isApproved) {
            this.reason = reason;
            this.isApproved = isApproved;
        }
        public boolean getResult() {
            return isApproved;
        }
        public String getReason() {
            return reason;
        }
    }

    public static class approveComplainDTO{
        private final boolean isApproved;
        private final String reason;
        public approveComplainDTO(String reason, boolean isApproved) {
            this.reason = reason;
            this.isApproved = isApproved;
        }
        public boolean getResult() {
            return isApproved;
        }
        public String getReason() {
            return reason;
        }
    }

    public static class ReturnDTO {
        private String itemId;
        private String returnReason;
        private LocalDateTime returnDate;
        private String shippingNumber;
        private String returnStatus;
        private String resultReason;

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public void setReturnStatus(String returnStatus) {
            this.returnStatus = returnStatus;
        }

        public void setShippingNumber(String shippingNumber) {
            this.shippingNumber = shippingNumber;
        }

        public void setReturnDate(LocalDateTime returnDate) {
            this.returnDate = returnDate;
        }

        public void setReturnReason(String returnReason) {
            this.returnReason = returnReason;
        }

        public String getReturnStatus() {
            return returnStatus;
        }

        public String getShippingNumber() {
            return shippingNumber;
        }

        public LocalDateTime getReturnDate() {
            return returnDate;
        }

        public String getReturnReason() {
            return returnReason;
        }

        public String getItemId() {
            return itemId;
        }

        public String getResultReason() {
            return resultReason;
        }

        public void setResultReason(String resultReason) {
            this.resultReason = resultReason;
        }
    }

    public static class ComplainDTO {
        private String itemId;
        private String buyerReason;
        private String sellerReason;
        private LocalDateTime complainDate;
        private String adminId;
        private String complainStatus;
        private boolean isComplainSuccess;
        private String resultReason;
        public String getItemId() {
            return itemId;
        }
        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public String getResultReason() {
            return resultReason;
        }

        public void setResultReason(String resultReason) {
            this.resultReason = resultReason;
        }

        public boolean isComplainSuccess() {
            return isComplainSuccess;
        }

        public void setComplainSuccess(boolean complainSuccess) {
            isComplainSuccess = complainSuccess;
        }


        public String getAdminId() {
            return adminId;
        }

        public void setAdminId(String adminId) {
            this.adminId = adminId;
        }

        public LocalDateTime getComplainDate() {
            return complainDate;
        }

        public void setComplainDate(LocalDateTime complainDate) {
            this.complainDate = complainDate;
        }

        public String getSellerReason() {
            return sellerReason;
        }

        public void setSellerReason(String sellerReason) {
            this.sellerReason = sellerReason;
        }

        public String getBuyerReason() {
            return buyerReason;
        }

        public void setBuyerReason(String buyerReason) {
            this.buyerReason = buyerReason;
        }

        public String getComplainStatus() {
            return complainStatus;
        }

        public void setComplainStatus(String complainStatus) {
            this.complainStatus = complainStatus;
        }
    }



}
