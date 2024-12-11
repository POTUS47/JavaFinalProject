package com.finalproject.model;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "buyer_product_bookmark")
public class BuyerProductBookmark {

    @EmbeddedId
    private BuyerProductBookmarkId id;

    @ManyToOne
    @MapsId("buyerAccountId")
    @JoinColumn(name = "buyer_account_id")
    private Buyer buyer;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    // Getters and Setters
    public BuyerProductBookmarkId getId() {
        return id;
    }

    public void setId(BuyerProductBookmarkId id) {
        this.id = id;
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

@Embeddable
class BuyerProductBookmarkId implements Serializable {
    @Column(name = "buyer_account_id")
    private String buyerAccountId;

    @Column(name = "product_id")
    private String productId;

    // Getters, Setters, hashCode, and equals
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

    @Override
    public int hashCode() {
        return buyerAccountId.hashCode() + productId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BuyerProductBookmarkId that = (BuyerProductBookmarkId) obj;
        return buyerAccountId.equals(that.buyerAccountId) && productId.equals(that.productId);
    }
}
