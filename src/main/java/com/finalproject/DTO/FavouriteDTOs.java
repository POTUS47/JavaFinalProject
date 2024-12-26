package com.finalproject.DTO;
import java.util.List;
import com.finalproject.DTO.ImageModels.*;
import java.math.BigDecimal;
public class FavouriteDTOs {
    // 商品DTO
    public static class ProductDTO {
        private String productId;
        private BigDecimal productPrice;
        private String productPic; // 图片ID
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

    // 用户收藏的所有商品的DTO
    public static class FavouriteProductsDTO {
        private String buyerId;
        private String storeId;
        private String productId;
        private BigDecimal productPrice;
        private Integer quantity;
        private String tag;
        private String productPic;
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

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
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


    // 用户收藏的所有店铺的DTO
    public static class FavouriteStoresDTO {
        private String buyerId;
        private String storeId;
        private String storeName;
        private BigDecimal storeScore;
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

    // 店铺收藏DTO
    public static class StoreIdDTO {
        private String storeId;

        // Getter 和 Setter
        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }
    }

    // 商品收藏DTO
    public static class ProductIdDTO {
        private String productId;

        // Getter 和 Setter
        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }
    }

}
