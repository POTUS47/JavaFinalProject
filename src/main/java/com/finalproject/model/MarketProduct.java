package com.finalproject.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "market_product",
        uniqueConstraints = @UniqueConstraint(columnNames = {"market_id", "product_id"}))
public class MarketProduct implements Serializable {

    @Column(name = "discount_price", nullable = false)
    private BigDecimal discountPrice;

    @ManyToOne
    @JoinColumn(name = "market_id", referencedColumnName = "market_id", insertable = false, updatable = false)
    private Market market;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    private Product product;

    @Column(name = "market_id", nullable = false, updatable = false)
    private String marketId;

    @Id
    @Column(name = "product_id", nullable = false, updatable = false)
    private String productId;

    // Getters and Setters

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}