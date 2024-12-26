package com.finalproject.DTO;

import java.math.BigDecimal;

public class ProductDTOs {

    public static class addProductDTO{
        private String storeId ;
        private String ProductName;
        private BigDecimal ProductPrice ;
        private String Tag ;
        private String SubTag ;
        private String Description;
        private String StoreTag ;
        private Integer Quantity ;
//        private List<IFormFile>? ProductImages ;
//        private List<PicDes>? PicDes ;

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getProductName() {
            return ProductName;
        }
        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }
        public BigDecimal getProductPrice() {
            return ProductPrice;
        }
        public void setProductPrice(BigDecimal ProductPrice) {
            this.ProductPrice = ProductPrice;
        }
        public String getTag() {
            return Tag;
        }
        public void setTag(String Tag) {
            this.Tag = Tag;
        }
        public String getSubTag() {
            return SubTag;
        }
        public void setSubTag(String SubTag) {
            this.SubTag = SubTag;
        }
        public String getDescription() {
            return Description;
        }
        public void setDescription(String Description) {
            this.Description = Description;
        }
        public String getStoreTag() {
            return StoreTag;
        }
        public void setStoreTag(String StoreTag) {
            this.StoreTag = StoreTag;
        }
        public Integer getQuantity() {
            return Quantity;
        }
        public void setQuantity(Integer Quantity) {
            this.Quantity = Quantity;
        }

    }
}
