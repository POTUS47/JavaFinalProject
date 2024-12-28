package com.finalproject.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.List;

public class OrderDTOs {

    // 商家视角订单获取
    public static class GetOrdersDTO {
        private String orderID;
        private BigDecimal totalPrice;
        private BigDecimal actualPrice;
        private String orderStatus;
        private Timestamp createTime;
        private String shippingNumber;

        // 全参构造函数
        public GetOrdersDTO(String orderID, BigDecimal totalPrice, BigDecimal actualPrice,
                            String orderStatus, Timestamp createTime, String shippingNumber) {
            this.orderID = orderID;
            this.totalPrice = totalPrice;
            this.actualPrice = actualPrice;
            this.orderStatus = orderStatus;
            this.createTime = createTime;
            this.shippingNumber = shippingNumber;
        }

        // Getter and Setter for orderID
        public String getOrderID() {
            return orderID;
        }
        public void setOrderID(String orderID) {
            this.orderID = orderID;
        }

        // Getter and Setter for totalPrice
        public BigDecimal getTotalPrice() {
            return totalPrice;
        }
        public void setTotalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
        }

        // Getter and Setter for actualPrice
        public BigDecimal getActualPrice() {
            return actualPrice;
        }
        public void setActualPrice(BigDecimal actualPrice) {
            this.actualPrice = actualPrice;
        }

        // Getter and Setter for orderStatus
        public String getOrderStatus() {
            return orderStatus;
        }
        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        // Getter and Setter for createTime
        public Timestamp getCreateTime() {
            return createTime;
        }
        public void setCreateTime(Timestamp createTime) {
            this.createTime = createTime;
        }

        // Getter and Setter for shippingNumber
        public String getShippingNumber() {
            return shippingNumber;
        }
        public void setShippingNumber(String shippingNumber) {
            this.shippingNumber = shippingNumber;
        }
    }

    // 初步确认订单
    public static class OrderDTO {
        private String productId;

        // getters and setters
        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }
    }

    public static class OrderIdDTO {
        private String orderId;

        // getters and setters
        public String getOrderId() {
            return orderId;
        }

        public void setoOrderId(String productId) {
            this.orderId = productId;
        }
    }


    // 确认订单并支付
    public static class OrderConfirmDTO {
        private String orderId;
        private String username;
        private String orderAddress;
        private BigDecimal actualPay;
        private BigDecimal totalPay;

        // 无参构造函数
        public OrderConfirmDTO() {
        }

        // 全参构造函数
        public OrderConfirmDTO(String orderId, String username, String orderAddress, BigDecimal actualPay, BigDecimal totalPay) {
            this.orderId = orderId;
            this.username = username;
            this.orderAddress = orderAddress;
            this.actualPay = actualPay;
            this.totalPay = totalPay;
        }

        // Getter和Setter
        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getOrderAddress() {
            return orderAddress;
        }

        public void setOrderAddress(String orderAddress) {
            this.orderAddress = orderAddress;
        }

        public BigDecimal getActualPay() {
            return actualPay;
        }

        public void setActualPay(BigDecimal actualPay) {
            this.actualPay = actualPay;
        }

        public BigDecimal getTotalPay() {
            return totalPay;
        }

        public void setTotalPay(BigDecimal totalPay) {
            this.totalPay = totalPay;
        }
    }

    public static class OrderRelatedDTO {
        private String orderId;
        private String username;
        private String address;
        private String createTime;
        private boolean isPaid;
        private List<OrderItemDTOs.OrderItemDTO> orderItems;

        // 无参构造函数
        public OrderRelatedDTO() {
        }

        // Getter和Setter
        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public boolean isPaid() {
            return isPaid;
        }

        public void setPaid(boolean isPaid) {
            this.isPaid = isPaid;
        }

        public List<OrderItemDTOs.OrderItemDTO> getOrderItems() {
            return orderItems;
        }

        public void setOrderItems(List<OrderItemDTOs.OrderItemDTO> orderItems) {
            this.orderItems = orderItems;
        }
    }

    public static class OrderCenterDTO {
        private String orderId;
        private String username;
        private String storeName;
        private String address;
        private String createTime;
        private boolean isPaid;
        private BigDecimal totalPay;
        private String orderStatus;
        private List<OrderItemDTOs.OrderItemDTO> orderItems;


        // 无参构造函数
        public OrderCenterDTO() {
        }

        // Getter和Setter
        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public boolean isPaid() {
            return isPaid;
        }

        public void setPaid(boolean isPaid) {
            this.isPaid = isPaid;
        }

        public List<OrderItemDTOs.OrderItemDTO> getOrderItems() {
            return orderItems;
        }

        public void setOrderItems(List<OrderItemDTOs.OrderItemDTO> orderItems) {
            this.orderItems = orderItems;
        }

        public BigDecimal getTotalPay() {
            return totalPay;
        }

        public void setTotalPay(BigDecimal totalPay) {
            this.totalPay = totalPay;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }
    }

    // 用户上传需要购买的商品
    public static class ProductIdsDTO {
        List<String> productIds;

        public List<String> getProductIds() {
            return productIds;
        }

        public void setProductIds(List<String> productIds) {
            this.productIds = productIds;
        }
    }

    // 用户修改收货信息
    public static class ChangeNameAndAddressDTO{
        private String orderId;
        private String name;
        private String address;

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }
    }

    public static class OrderItemIdDTO{
        private String orderItemId;

        public String getOrderItemId() {
            return orderItemId;
        }

        public void setOrderItemId(String orderItemId) {
            this.orderItemId = orderItemId;
        }
    }

    public static class OrderStatisticsDTO {
        private int orderCount;
        private BigDecimal totalAmount;

        // getters and setters
        public int getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(int orderCount) {
            this.orderCount = orderCount;
        }

        public BigDecimal getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
        }
    }

    public static class OrderDeliveryDTO {

        private String deliveryNumber;
        private String orderId;

        // Getters and Setters
        public String getDeliveryNumber() {
            return deliveryNumber;
        }

        public void setDeliveryNumber(String deliveryNumber) {
            this.deliveryNumber = deliveryNumber;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }
    }

    public static class CreditsDTO{
        Integer bonus;
        public Integer getBonus() {
            return bonus;
        }
        public void setBonus(Integer bonus) {
            this.bonus = bonus;
        }
    }

    public static class MoneyDTO{
        String orderId;
        Integer usedCredits;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public Integer getUsedCredits() {
            return usedCredits;
        }

        public void setUsedCredits(Integer usedCredits) {
            this.usedCredits = usedCredits;
        }
    }

}
