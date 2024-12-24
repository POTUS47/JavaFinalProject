package com.finalproject.DTO;

public class AccountDTOs {
    public static class UserRegisterDTO {
        private String username;
        private String password;
        private String email;
        private String verificationCode;
        private String identity; // 用户身份 (ADMIN, SELLER, BUYER)

        // Getters and Setters
        public String getIdentity(){
            return this.identity;
        }
        public String getUsername(){
            return this.username;
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
}
