package com.finalproject.model;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "market_product")
public class MarketProduct {

    @EmbeddedId
    private MarketProductId id;

    @Column(name = "discount_price", nullable = false)
    private BigDecimal discountPrice;

    @ManyToOne
    @MapsId("marketId")
    @JoinColumn(name = "market_id")
    private Market market;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    // Getters and Setters
    public MarketProductId getId() {
        return id;
    }

    public void setId(MarketProductId id) {
        this.id = id;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
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

@Embeddable
class MarketProductId implements Serializable {
    @Column(name = "market_id")
    private String marketId;

    @Column(name = "product_id")
    private String productId;

    // Getters, Setters, hashCode, and equals
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

    @Override
    public int hashCode() {
        return marketId.hashCode() + productId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MarketProductId that = (MarketProductId) obj;
        return marketId.equals(that.marketId) && productId.equals(that.productId);
    }
}
