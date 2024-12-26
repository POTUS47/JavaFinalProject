package com.finalproject.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "market_store",
        uniqueConstraints = @UniqueConstraint(columnNames = {"market_id", "store_id"}))
public class MarketStore implements Serializable {

    @Id
    @Column(name = "market_id")
    private String marketId;

    @Column(name = "in_or_not")
    private boolean inOrNot;

    @Column(name = "store_id")
    private String storeAccountId;

    // 如果你需要直接操作 Store 和 Market 实体，保留以下映射；否则可以移除。
    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "account_id", insertable = false, updatable = false)
    private Store store;

    @ManyToOne
    @JoinColumn(name = "market_id", referencedColumnName = "market_id", insertable = false, updatable = false)
    private Market market;

    // Getters and Setters

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public boolean isInOrNot() {
        return inOrNot;
    }

    public void setInOrNot(boolean inOrNot) {
        this.inOrNot = inOrNot;
    }

    public String getStoreAccountId() {
        return storeAccountId;
    }

    public void setStoreAccountId(String storeAccountId) {
        this.storeAccountId = storeAccountId;
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