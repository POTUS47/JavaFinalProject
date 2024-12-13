package com.finalproject.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "buyer_product_bookmark",
        uniqueConstraints = @UniqueConstraint(columnNames = {"buyer_account_id", "product_id"}))
public class BuyerProductBookmark implements Serializable {

    @ManyToOne
    @JoinColumn(name = "buyer_account_id", referencedColumnName = "account_id", insertable = false, updatable = false)
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    private Product product;

    @Id
    @Column(name = "buyer_account_id", nullable = false, updatable = false)
    private String buyerAccountId;

    @Column(name = "product_id", nullable = false, updatable = false)
    private String productId;

    public String getBuyerAccountId() {
        return buyerAccountId;
    }

    public void setBuyerAccountId(String buyerAccountId) {
        this.buyerAccountId = buyerAccountId;
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