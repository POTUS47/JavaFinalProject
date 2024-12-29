package com.finalproject.DTO;

public class AccountDTOs {
    public static class UserRegisterDTO {
        private String password;
        private String email;
        private String verificationCode;
        private String identity; // 用户身份 (ADMIN, SELLER, BUYER)

        // Getters and Setters
        public String getIdentity(){
            return this.identity;
        }
        public String getPassword(){
            return this.password;
        }
        public String getEmail(){
            return this.email;
        }
        public String getVerificationCode(){
            return this.verificationCode;
        }
    }

    public static class LoginDTO {
        private String identifier;
        private String password;

        // Getters and Setters
        public String getIdentifier(){
            return this.identifier;
        }
        public String getPassword(){
            return this.password;
        }
    }

    public static class UserInfoDTO {
        private String userId;
        private String userName;
        private String email;
        private String photoId;
        private String type;

        // 构造函数
        public UserInfoDTO(String userId, String userName,
                           String email, String photoId, String type) {
            this.userId = userId;
            this.userName = userName;
            this.email = email;
            this.photoId = photoId;
            this.type = type;
        }
        // Getter 和 Setter
        public void setUserId(String userId) {
            this.userId = userId;
        }
        public void setUserName(String userName) {
            this.userName = userName;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public void setPhotoId(String photoId) {
            this.photoId = photoId;
        }
        public void setType(String type) {
            this.type = type;
        }
        public String getType() {
            return type;
        }
        public String getUserId() {
            return userId;
        }
        public String getUserName() {
            return userName;
        }
        public String getEmail() {
            return email;
        }
        public String getPhotoId() {
            return photoId;
        }
    }

    public static class PandDDTO{
        private String imageUrl;
        private String describtion;
        public String getImageUrl(){
            return this.imageUrl;
        }
        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getDescribtion() {
            return describtion;
        }
        public void setDescribtion(String describtion) {
            this.describtion = describtion;
        }
    }


}
