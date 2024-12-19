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
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", nullable = false)
    private Account store;  // Store entity representing the store owner (Account)

    @Column(name = "store_tag", length = 50)
    private String storeTag;

    @ManyToOne
    @JoinColumn(name = "sub_tag", referencedColumnName = "subcategory_id", nullable = false)
    private SubCategory subCategory;  // SubCategory entity representing the product's sub-category

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

    public Account getStore() {
        return store;
    }

    public void setStore(Account store) {
        this.store = store;
    }

    public String getStoreTag() {
        return storeTag;
    }

    public void setStoreTag(String storeTag) {
        this.storeTag = storeTag;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
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
}
