package com.finalproject.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "buyer_store_bookmark",
        uniqueConstraints = @UniqueConstraint(columnNames = {"buyer_account_id", "store_account_id"}))
public class BookmarkStore implements Serializable {

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "account_id", insertable = false, updatable = false)
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "account_id", insertable = false, updatable = false)
    private Store store;

    @Id
    @Column(name = "buyer_id", nullable = false, updatable = false)
    private String buyerId;

    @Column(name = "store_id", nullable = false, updatable = false)
    private String storeId;

    // Getters and Setters
    public String getBuyerAccountId() {
        return buyerId;
    }

    public void setBuyerAccountId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getStoreAccountId() {
        return storeId;
    }

    public void setStoreAccountId(String storeId) {
        this.storeId = storeId;
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