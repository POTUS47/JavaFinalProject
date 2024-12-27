package com.finalproject.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "product_name", nullable = false, length = 50)
    private String productName;

    @Column(name = "product_price", nullable = false, precision = 7, scale = 2)
    private BigDecimal productPrice;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "tag", nullable = false, length = 50)
    private String tag;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", insertable=false,updatable = false)
    private Store store;  // Store entitsy representing the store owner (Account)

    @Column(name = "account_id", nullable = false,updatable = false)
    private String storeId;

    @Column(name = "store_tag", length = 50)
    private String storeTag;

    @Column(name = "sub_tag", nullable = false, length = 50)
    private String subCategory;  // SubCategory entity representing the product's sub-category

    public Product(String productId,String productName,
                   BigDecimal productPrice, int quantity,
                   String tag,String description,
                   String subCategory,Store store ,
                   String storeId) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.tag = tag;
        this.description = description;
        this.subCategory = subCategory;
        this.store = store;
        this.storeId = storeId;
    }

    public Product() {

    }

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

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getStoreTag() {
        return storeTag;
    }

    public void setStoreTag(String storeTag) {
        this.storeTag = storeTag;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", quantity=" + quantity +
                ", tag='" + tag + '\'' +
                ", description='" + description + '\'' +
                ", store=" + store +
                ", storeTag='" + storeTag + '\'' +
                ", subCategory=" + subCategory +
                '}';
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
