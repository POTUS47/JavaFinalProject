package com.finalproject.DTO;

public class ImageModels {
    public static class ImageModel {
        private String imageId;

        public String getImageId() {
            return imageId;
        }

        public void setImageId(String imageId) {
            this.imageId = imageId;
        }

        public String getImageUrl() {
            return String.format("http://47.97.59.189:5173/api/images/product/%s", imageId);
        }
    }

    public static class PostImageModel {
        private String imageId;

        public String getImageId() {
            return imageId;
        }

        public void setImageId(String imageId) {
            this.imageId = imageId;
        }

        public String getImageUrl() {
            return String.format("http://47.97.59.189:5173/api/images/post/%s", imageId);
        }
    }

    public static class MarketImageModel {
        private String imageId;

        public String getImageId() {
            return imageId;
        }

        public void setImageId(String imageId) {
            this.imageId = imageId;
        }

        public String getImageUrl() {
            return String.format("http://47.97.59.189:5173/api/images/market/%s", imageId);
        }
    }

    public static class AuthImageModel {
        private String imageId;

        public AuthImageModel(String imageId) {
            this.imageId = imageId;
        }

        public String getImageId() {
            return imageId;
        }

        public void setImageId(String imageId) {
            this.imageId = imageId;
        }

        public String getImageUrl() {
            return String.format("http://47.97.59.189:5173/api/images/authentication/%s", imageId);
        }
    }
}

