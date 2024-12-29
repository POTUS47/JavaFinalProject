package com.finalproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bookmark_product",
        uniqueConstraints = @UniqueConstraint(columnNames = {"buyer_id", "product_id"}))
public class BookmarkProduct implements Serializable {

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "account_id", insertable = false, updatable = false)
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    private Product product;


    @Column(name = "buyer_id", nullable = false, updatable = false)
    private String buyerId;

    @Id
    @Column(name = "product_id", nullable = false, updatable = false)
    private String productId;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}