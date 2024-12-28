package com.finalproject.DTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.finalproject.DTO.ImageModels.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class AdministratorDTOs {

    public static class ShowAuthenticationDTO {
        private String storeId;
        private String authentication;
        private String status;
        private ImageModels.AuthImageModel photo;

        public ShowAuthenticationDTO(String storeId, String authentication, String status, ImageModels.AuthImageModel photo) {
            this.storeId = storeId;
            this.authentication = authentication;
            this.status = status;
            this.photo = photo;
        }


        // Getters and Setters
        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getAuthentication() {
            return authentication;
        }

        public void setAuthentication(String authentication) {
            this.authentication = authentication;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public ImageModels.AuthImageModel getPhoto() {
            return photo;
        }

        public void setPhoto(ImageModels.AuthImageModel photo) {
            this.photo = photo;
        }
    }

    public static class ShowMarketDTO {
        private String marketId;
        private String theme;
        private Date startTime;
        private Date endTime;
        private String detail;
        private MarketImageModel image;

        // Getters and Setters
        public String getMarketId() {
            return marketId;
        }

        public void setMarketId(String marketId) {
            this.marketId = marketId;
        }

        public String getTheme() {
            return theme;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }

        public Date getStartTime() {
            return startTime;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }

        public Date getEndTime() {
            return endTime;
        }

        public void setEndTime(Date endTime) {
            this.endTime = endTime;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public MarketImageModel getImage() {
            return image;
        }

        public void setImage(MarketImageModel image) {
            this.image = image;
        }
    }

    public static class ShowReportDTO {
        private String reportId;
        private String buyerAccountId;
        private Date reportingTime;
        private Date postTime;
        private String reportingReason;
        private String postContent;
        private String postTitle;
        private String auditResults;
        private List<PostImageModel> postImages;

        // Getters and Setters
        public String getReportId() {
            return reportId;
        }

        public void setReportId(String reportId) {
            this.reportId = reportId;
        }

        public String getBuyerAccountId() {
            return buyerAccountId;
        }

        public void setBuyerAccountId(String buyerAccountId) {
            this.buyerAccountId = buyerAccountId;
        }

        public Date getReportingTime() {
            return reportingTime;
        }

        public void setReportingTime(Date reportingTime) {
            this.reportingTime = reportingTime;
        }

        public Date getPostTime() {
            return postTime;
        }

        public void setPostTime(Date postTime) {
            this.postTime = postTime;
        }

        public String getReportingReason() {
            return reportingReason;
        }

        public void setReportingReason(String reportingReason) {
            this.reportingReason = reportingReason;
        }

        public String getPostContent() {
            return postContent;
        }

        public void setPostContent(String postContent) {
            this.postContent = postContent;
        }

        public String getPostTitle() {
            return postTitle;
        }

        public void setPostTitle(String postTitle) {
            this.postTitle = postTitle;
        }

        public String getAuditResults() {
            return auditResults;
        }

        public void setAuditResults(String auditResults) {
            this.auditResults = auditResults;
        }

        public List<PostImageModel> getPostImages() {
            return postImages;
        }

        public void setPostImages(List<PostImageModel> postImages) {
            this.postImages = postImages;
        }
    }
    public static class GAAModel{
        private String adminId;

        public String getAdminId() {
            return adminId;
        }
        public void setAdminId(String adminId) {
            this.adminId = adminId;
        }
    }

    public static class AMModel{
        private String adminId;
        private String theme;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime startTime;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endTime;

        private String detail;
        private MarketImageModel image;

        public AMModel(String adminId, String theme, LocalDateTime startTime, LocalDateTime endTime, String detail, MarketImageModel image) {
            this.adminId = adminId;
            this.theme = theme;
            this.startTime = startTime;
            this.endTime = endTime;
            this.detail = detail;
            this.image = image;
        }
        public String getAdminId() {
            return adminId;
        }
        public String getTheme() {
            return theme;
        }
        public LocalDateTime getStartTime() {
            return startTime;
        }
        public LocalDateTime getEndTime() {
            return endTime;
        }
        public String getDetail() {
            return detail;
        }
        public MarketImageModel getImage() {
            return image;
        }

    }

    public static class DMModel{
        private String marketId;

        DMModel(String marketId) {
            this.marketId = marketId;
        }

        public String getMarketId() {
            return marketId;
        }
    }

    public static class USAModel {
        private String storeId;
        private String adminId;
        private boolean result;

        // Getters and setters
        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getAdminId() {
            return adminId;
        }

        public void setAdminId(String adminId) {
            this.adminId = adminId;
        }

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }
    }

    public static class ISModel{
        private String marketId;
        private String inviteTag;

        ISModel(String marketId, String inviteTag) {
            this.marketId = marketId;
            this.inviteTag = inviteTag;
        }
        public String getMarketId() {
            return marketId;
        }
        public String getInviteTag() {
            return inviteTag;
        }
    }

}
