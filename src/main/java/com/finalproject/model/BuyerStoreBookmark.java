package com.finalproject.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "buyer_store_bookmark",
        uniqueConstraints = @UniqueConstraint(columnNames = {"buyer_account_id", "store_account_id"}))
public class BuyerStoreBookmark implements Serializable {

    @ManyToOne
    @JoinColumn(name = "buyer_account_id", referencedColumnName = "account_id", insertable = false, updatable = false)
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "store_account_id", referencedColumnName = "account_id", insertable = false, updatable = false)
    private Store store;

    @Id
    @Column(name = "buyer_account_id", nullable = false, updatable = false)
    private String buyerAccountId;

    @Column(name = "store_account_id", nullable = false, updatable = false)
    private String storeAccountId;

    // Getters and Setters
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