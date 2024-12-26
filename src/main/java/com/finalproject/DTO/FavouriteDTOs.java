package com.finalproject.DTO;

import java.util.List;
import java.math.BigDecimal;
public class FavouriteDTOs {
    // 商品DTO
    public static class ProductDTO {
        private String productId;
        private BigDecimal productPrice;
        private ImageModels.ImageModel productPicId; // 图片ID
        private String productName;

        // Getter 和 Setter 方法

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public BigDecimal getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(BigDecimal productPrice) {
            this.productPrice = productPrice;
        }

        public ImageModels.ImageModel getProductPicId() {
            return productPicId;
        }

        public void setProductPicId(ImageModels.ImageModel productPicId) {
            this.productPicId = productPicId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }
    }

    // 用户收藏的所有商品的DTO
    public static class FavouriteProductsDTO {
        private String buyerId;
        private String storeId;
        private String productId;
        private BigDecimal productPrice;
        private Boolean saleOrNot;
        private String tag;
        private ImageModels.ImageModel productPicId;
        private String productName;

        public String getBuyerId() {
            return buyerId;
        }

        public void setBuyerId(String buyerId) {
            this.buyerId = buyerId;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public BigDecimal getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(BigDecimal productPrice) {
            this.productPrice = productPrice;
        }

        public Boolean getSaleOrNot() {
            return saleOrNot;
        }

        public void setSaleOrNot(Boolean saleOrNot) {
            this.saleOrNot = saleOrNot;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public ImageModels.ImageModel getProductPicId() {
            return productPicId;
        }

        public void setProductPicId(ImageModels.ImageModel productPicId) {
            this.productPicId = productPicId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }
    }


    // 用户收藏的所有店铺的DTO
    public static class FavouriteStoresDTO {
        private String buyerId;
        private String storeId;
        private String storeName;
        private BigDecimal storeScore;
        private String storePicId; // 图片ID
        private List<ProductDTO> products;

        // Getter 和 Setter 方法

        public String getBuyerId() {
            return buyerId;
        }

        public void setBuyerId(String buyerId) {
            this.buyerId = buyerId;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public BigDecimal getStoreScore() {
            return storeScore;
        }

        public void setStoreScore(BigDecimal storeScore) {
            this.storeScore = storeScore;
        }

        public String getStorePicId() {
            return storePicId;
        }

        public void setStorePicId(String storePicId) {
            this.storePicId = storePicId;
        }

        public List<ProductDTO> getProducts() {
            return products;
        }

        public void setProducts(List<ProductDTO> products) {
            this.products = products;
        }
    }

    // 这是干嘛的
    public static class GFPModel {
        private String userId;

        public String getUserId() {
            return userId;
        }
    }

    public static class BookmarkStoreDTO {
        private String userId;
        private String storeId;

        // Getters and Setters
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

    }

    public static class BookmarkProductDTO {
        private String userId;
        private String productId;

        // Getters and Setters
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

    }

}
