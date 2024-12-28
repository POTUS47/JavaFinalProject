package com.finalproject.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Entity
@Table(name = "bookmark_store",
        uniqueConstraints = @UniqueConstraint(columnNames = {"buyer_id", "store_id"}))
@EqualsAndHashCode(of = {"buyerId", "storeId"})
public class BookmarkStore implements Serializable {

//    @ManyToOne
//    @JoinColumn(name = "buyer_id", referencedColumnName = "account_id", insertable = false, updatable = false)
//    private Buyer buyer;
//
//    @ManyToOne
//    @JoinColumn(name = "store_id", referencedColumnName = "account_id", insertable = false, updatable = false)
//    private Store store;

    @Column(name = "buyer_id", nullable = false)
    private String buyerId;

    @Id
    @Column(name = "store_id", nullable = false)
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

//    public Buyer getBuyer() {
//        return buyer;
//    }
//
//    public void setBuyer(Buyer buyer) {
//        this.buyer = buyer;
//    }
//
//    public Store getStore() {
//        return store;
//    }
//
//    public void setStore(Store store) {
//        this.store = store;
//    }
}