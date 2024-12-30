package com.finalproject.DTO;

public class InventoryDTOs {
    public static class InventoryDTO {
        private String inventoryId;
        private String size;
        private String color;
        private int quantity;
        private String productId;

        // 构造函数
        public InventoryDTO() {}

        // Getter 和 Setter 方法
        public String getInventoryId() {
            return inventoryId;
        }

        public void setInventoryId(String inventoryId) {
            this.inventoryId = inventoryId;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }
    }
}
