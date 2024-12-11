package com.finalproject.model;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "buyer_store_bookmark")
public class BuyerStoreBookmark {

    @EmbeddedId
    private BuyerStoreBookmarkId id;

    @ManyToOne
    @MapsId("buyerAccountId")
    @JoinColumn(name = "buyer_account_id")
    private Buyer buyer;

    @ManyToOne
    @MapsId("storeAccountId")
    @JoinColumn(name = "store_account_id")
    private Store store;

    // Getters and Setters
    public BuyerStoreBookmarkId getId() {
        return id;
    }

    public void setId(BuyerStoreBookmarkId id) {
        this.id = id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}

@Embeddable
class BuyerStoreBookmarkId implements Serializable {
    @Column(name = "buyer_account_id")
    private String buyerAccountId;

    @Column(name = "store_account_id")
    private String storeAccountId;

    // Getters, Setters, hashCode, and equals
    public String getBuyerAccountId() {
        return buyerAccountId;
    }

    public void setBuyerAccountId(String buyerAccountId) {
        this.buyerAccountId = buyerAccountId;
    }

    public String getStoreAccountId() {
        return storeAccountId;
    }

    public void setStoreAccountId(String storeAccountId) {
        this.storeAccountId = storeAccountId;
    }

    @Override
    public int hashCode() {
        return buyerAccountId.hashCode() + storeAccountId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BuyerStoreBookmarkId that = (BuyerStoreBookmarkId) obj;
        return buyerAccountId.equals(that.buyerAccountId) && storeAccountId.equals(that.storeAccountId);
    }
}
