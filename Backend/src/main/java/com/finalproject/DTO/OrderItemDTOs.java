package com.finalproject.DTO;
import java.math.BigDecimal;

public class OrderItemDTOs {

    // 更新订单项评分评价
    public static class UpdateOrderItemRemarkDTO {

        private String orderItemId;
        private BigDecimal score;
        private String remark;

        // Getters and Setters
        public String getOrderItemId() {
            return orderItemId;
        }

        public void setOrderItemId(String orderItemId) {
            this.orderItemId = orderItemId;
        }

        public BigDecimal getScore() {
            return score;
        }

        public void setScore(BigDecimal score) {
            this.score = score;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

    // 获取店铺评价
    public static class GetStoreRemarkDTO {

        private String orderId;
        private String buyerName;
        private String buyerAvatar;
        private BigDecimal orderScore;
        private String orderRemark;

        // Getters and Setters
        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getBuyerName() {
            return buyerName;
        }

        public void setBuyerName(String buyerName) {
            this.buyerName = buyerName;
        }

        public String getBuyerAvatar() {
            return buyerAvatar;
        }

        public void setBuyerAvatar(String buyerAvatar) {
            this.buyerAvatar = buyerAvatar;
        }

        public BigDecimal getOrderScore() {
            return orderScore;
        }

        public void setOrderScore(BigDecimal orderScore) {
            this.orderScore = orderScore;
        }

        public String getOrderRemark() {
            return orderRemark;
        }

        public void setOrderRemark(String orderRemark) {
            this.orderRemark = orderRemark;
        }
    }

    // 每个订单项返回信息
    public static class OrderItemDTO {
        private String itemId;
        private String productId;
        private String productName;
        private String productImage;
        private BigDecimal productPrice;

        // Getters and Setters
        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        public BigDecimal getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(BigDecimal productPrice) {
            this.productPrice = productPrice;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }
    }

    public static class PaymentAndIdDTO{
        private String orderId;
        private BigDecimal actualPay;

        public String getOrderId() {
            return orderId;
        }
        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }
        public BigDecimal getActualPay() {
            return actualPay;
        }
        public void setActualPay(BigDecimal actualPay) {
            this.actualPay = actualPay;
        }
    }

    public static class BuyerShopperIdDTO{
        private String storeId;
        private String buyerId;

        public String getStoreId() {
            return storeId;
        }
        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }
        public String getBuyerId() {
            return buyerId;
        }
        public void setBuyerId(String buyerId) {
            this.buyerId = buyerId;
        }
    }

}
