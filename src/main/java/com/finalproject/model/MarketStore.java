package com.finalproject.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "market_store")
public class MarketStore {

    @Id
    @Column(name = "market_id")
    private String marketId;

    @Column(name = "store_account_id")
    private String storeAccountId;

    @Column(name = "in_or_not")
    private boolean inOrNot;

    @ManyToOne
    @JoinColumn(name = "store_account_id", insertable = false, updatable = false)
    private Store store;

    @ManyToOne
    @JoinColumn(name = "market_id", insertable = false, updatable = false)
    private Market market;

    // Getters and Setters
    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getStoreAccountId() {
        return storeAccountId;
    }

    public void setStoreAccountId(String storeAccountId) {
        this.storeAccountId = storeAccountId;
    }

    public boolean isInOrNot() {
        return inOrNot;
    }

    public void setInOrNot(boolean inOrNot) {
        this.inOrNot = inOrNot;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }
}