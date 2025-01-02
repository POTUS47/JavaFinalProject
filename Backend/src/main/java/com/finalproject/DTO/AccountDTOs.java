package com.finalproject.DTO;

public class AccountDTOs {
    public static class UserRegisterDTO {
        private String password;
        private String email;
        private String verificationCode;
        private String identity; // 用户身份 (ADMIN, SELLER, BUYER)

        // Getters and Setters
        public String getIdentity() {
            return this.identity;
        }

        public String getPassword() {
            return this.password;
        }

        public String getEmail() {
            return this.email;
        }

        public String getVerificationCode() {
            return this.verificationCode;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }

        public void setVerificationCode(String verificationCode) {
            this.verificationCode = verificationCode;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class LoginDTO {
        private String identifier;
        private String password;

        // Getters and Setters
        public String getIdentifier() {
            return this.identifier;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }
    }

    public static class ChangePasswordDTO {
        private String email;
        private String password;
        private String verificationCode;

        // Getters and Setters
        public String getEmail() {
            return this.email;
        }

        public String getPassword() {
            return this.password;
        }

        public String getVerificationCode() {
            return this.verificationCode;
        }

        public void setVerificationCode(String verificationCode) {
            this.verificationCode = verificationCode;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setEmail(String email) {
            this.email = email;
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

    public static class BuyerInfoDTO {
        private String userId;
        private String userName;
        private String email;
        private String photoId;
        private String description;
        private String gender;
        private int age;
        private int totalCredits;
        private String address;
        private String photo_url;

        public void setAddress(String address) {
            this.address = address;
        }

        public void setTotalCredits(int totalCredits) {
            this.totalCredits = totalCredits;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setPhotoId(String photoId) {
            this.photoId = photoId;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photo_url = photoUrl;
        }

        public String getAddress() {
            return address;
        }

        public int getTotalCredits() {
            return totalCredits;
        }

        public int getAge() {
            return age;
        }

        public String getGender() {
            return gender;
        }

        public String getDescription() {
            return description;
        }

        public String getPhotoId() {
            return photoId;
        }

        public String getEmail() {
            return email;
        }

        public String getUserName() {
            return userName;
        }

        public String getUserId() {
            return userId;
        }

        public String getPhotoUrl() {
            return photo_url;
        }
    }

    public static class StoreInfoDTO {
        private String userId;
        private String userName;
        private String email;
        private String photoId;
        private String description;
        private String storeName;
        private float storeScore;
        private int hasCertificate;
        private String address;

        public void setAddress(String address) {
            this.address = address;
        }

        public void setHasCertificate(int hasCertificate) {
            this.hasCertificate = hasCertificate;
        }

        public void setStoreScore(float storeScore) {
            this.storeScore = storeScore;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setPhotoId(String photoId) {
            this.photoId = photoId;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getAddress() {
            return address;
        }

        public int getHasCertificate() {
            return hasCertificate;
        }

        public float getStoreScore() {
            return storeScore;
        }

        public String getStoreName() {
            return storeName;
        }

        public String getDescription() {
            return description;
        }

        public String getPhotoId() {
            return photoId;
        }

        public String getEmail() {
            return email;
        }

        public String getUserName() {
            return userName;
        }

        public String getUserId() {
            return userId;
        }
    }

    public static class PandDDTO {
        private String imageUrl;
        private String describtion;

        public String getImageUrl() {
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
